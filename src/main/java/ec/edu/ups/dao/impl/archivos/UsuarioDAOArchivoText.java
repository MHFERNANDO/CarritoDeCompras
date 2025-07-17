package ec.edu.ups.dao.impl.archivos;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.modelo.Respuesta;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class UsuarioDAOArchivoText implements UsuarioDAO {

    private String ruta;
    private static final String SEPARADOR = "\\|";
    private static final String SEP_RESPUESTAS = ";";
    private static final String SEP_ID_RESP = ",";

    public UsuarioDAOArchivoText(String ruta) {
        this.ruta = ruta;
        try {
            new FileWriter(ruta, true).close();
        } catch (IOException e) {
            System.err.println("Error inicializando archivo: " + e.getMessage());
        }

        if (buscarPorUserEspecifico("Fer") == null) {
            crear(new Usuario("Fer", "12345", Rol.ADMINISTRADOR, null, "Fernando", "Martinez", "0150064442", "Masculino", null));
        }

        if (buscarPorUserEspecifico("user") == null) {
            crear(new Usuario("user", "12345", Rol.USUARIO, null, "Usuario", "Ejemplo", "1234567890", "Femenino", null));
        }

    }

    @Override
    public void crear(Usuario usuario) {
        if (buscarPorUserEspecifico(usuario.getUsername()) != null) {
            System.err.println("Usuario ya existe: " + usuario.getUsername());
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(usuarioToString(usuario));
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al crear usuario: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> buscarPorUsername(String username) {
        List<Usuario> encontrados = new ArrayList<>();
        for (Usuario u : listarTodos()) {
            if (u.getUsername().equals(username)) {
                encontrados.add(u);
            }
        }
        return encontrados;
    }

    @Override
    public Usuario buscarPorUserEspecifico(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(username + "|")) {
                    return stringToUsuario(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        Usuario u = buscarPorUserEspecifico(username);
        if (u != null && u.getContrasenia().equals(contrasenia)) {
            return u;
        }
        return null;
    }

    @Override
    public void actualizar(String antiguoUsername, Usuario usuarioNuevo) {
        List<Usuario> usuarios = listarTodos();
        boolean modificado = false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(antiguoUsername)) {
                usuarios.set(i, usuarioNuevo);
                modificado = true;
                break;
            }
        }
        if (modificado) {
            sobrescribirArchivo(usuarios);
        }
    }

    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listarTodos();
        boolean eliminado = usuarios.removeIf(u -> u.getUsername().equals(username));
        if (eliminado) {
            sobrescribirArchivo(usuarios);
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Usuario u = stringToUsuario(linea);
                if (u != null) {
                    usuarios.add(u);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : listarTodos()) {
            if (u.getRol() == rol) {
                filtrados.add(u);
            }
        }
        return filtrados;
    }

    // Métodos auxiliares

    private void sobrescribirArchivo(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false))) {
            for (Usuario u : usuarios) {
                bw.write(usuarioToString(u));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String usuarioToString(Usuario u) {
        StringBuilder sb = new StringBuilder();
        sb.append(u.getUsername()).append("|");
        sb.append(u.getContrasenia()).append("|");
        sb.append(u.getRol()).append("|");
        sb.append(u.getNombre()).append("|");
        sb.append(u.getApellido()).append("|");
        sb.append(u.getCedula()).append("|");
        sb.append(u.getGenero()).append("|");
        // Guardamos fecha en millis o 0 si es null
        sb.append(u.getFechaNac() != null ? u.getFechaNac().getTimeInMillis() : 0);

        // Guardar respuestas, formato idPregunta,respuesta;idPregunta,respuesta;...
        if (u.getRespuestas() != null && !u.getRespuestas().isEmpty()) {
            sb.append("|");
            for (int i = 0; i < u.getRespuestas().size(); i++) {
                Respuesta r = u.getRespuestas().get(i);
                sb.append(r.getId()).append(",").append(r.getRespuesta());
                if (i < u.getRespuestas().size() - 1) {
                    sb.append(";");
                }
            }
        }

        return sb.toString();
    }

    private Usuario stringToUsuario(String linea) {
        String[] parts = linea.split("\\|", -1);
        if (parts.length < 8) return null;

        try {
            String username = parts[0];
            String contrasenia = parts[1];
            Rol rol = Rol.valueOf(parts[2]);
            String nombre = parts[3];
            String apellido = parts[4];
            String cedula = parts[5];
            String genero = parts[6];
            long fechaMillis = Long.parseLong(parts[7]);
            GregorianCalendar fechaNac = null;
            if (fechaMillis > 0) {
                fechaNac = new GregorianCalendar();
                fechaNac.setTimeInMillis(fechaMillis);
            }

            List<Respuesta> respuestas = new ArrayList<>();
            if (parts.length > 8 && !parts[8].isEmpty()) {
                String[] respuestasArr = parts[8].split(";");
                for (String rStr : respuestasArr) {
                    String[] respParts = rStr.split(",");
                    if (respParts.length == 2) {
                        int idPregunta = Integer.parseInt(respParts[0]);
                        String respTexto = respParts[1];
                        Pregunta p = new Pregunta(idPregunta, "");
                        respuestas.add(new Respuesta(p.getId(), respTexto));
                    }
                }
            }

            return new Usuario(username, contrasenia, rol, respuestas, nombre, apellido, cedula, genero, fechaNac);

        } catch (Exception e) {
            System.err.println("Error parseando usuario: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
