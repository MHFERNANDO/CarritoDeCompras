package ec.edu.ups.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.modelo.Respuesta;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.*;

import javax.swing.*;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

public class UsuarioController {
    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private final AnadirUsuarioView anadirUsuarioView;
    private final ListarUsuarioView listarUsuarioView;
    private final UsuarioActualizarView usuarioActualizarView;
    private final RegistrarseView registrarseView;
    private final RegistrarPreguntaView registrarPreguntaView;
    private final OlvideContrasenaView olvideContrasenaView;
    private Usuario usuarioRecuperar;
    private Respuesta preguntaActual;
    private Respuesta respuestaAleatoria;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, AnadirUsuarioView anadirUsuarioView, ListarUsuarioView listarUsuarioView, UsuarioActualizarView usuarioActualizarView, RegistrarseView registrarseView, RegistrarPreguntaView registrarPreguntaView, OlvideContrasenaView olvideContrasenaView, MensajeInternacionalizacionHandler mensajeHandler) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.anadirUsuarioView = anadirUsuarioView;
        this.listarUsuarioView = listarUsuarioView;
        this.usuarioActualizarView = usuarioActualizarView;
        this.registrarseView = registrarseView;
        this.registrarPreguntaView = registrarPreguntaView;
        this.olvideContrasenaView = olvideContrasenaView;
        this.mensajeHandler = mensajeHandler;
        this.usuario = null;
        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas(){
        loginView.getIniciarSesiónButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });
        loginView.getRegistrarseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginView.setVisible(false);
                registrarseView.setVisible(true);
            }
        });
        registrarseView.getRegistrarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        anadirUsuarioView.getLimpiarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarAddUser();
            }
        });
        anadirUsuarioView.getAgregarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUsuario();
            }
        });
        listarUsuarioView.getListarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuarios();
            }
        });
        listarUsuarioView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });
        usuarioActualizarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuarioAc();
            }
        });
        usuarioActualizarView.getActualizarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUser();
            }
        });
        usuarioActualizarView.getLimpiarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioActualizarView.limpiar();
            }
        });
        loginView.getOlvideMiContraseñaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginView.setVisible(false);
                olvideContrasenaView.setVisible(true);
            }
        });
        registrarPreguntaView.getRegistrarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarRespuestas();
            }
        });

        olvideContrasenaView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuarioParaRecuperarContrasena();
            }
        });
        olvideContrasenaView.getGuardarContraseñaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarNuevaContrasena();
            }
        });
    }

    private void buscarUsuarioParaRecuperarContrasena() {
        String username = olvideContrasenaView.getTextField1().getText();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, mensajeHandler.get("mensaje.ingreseUsuario"));
            return;
        }

        usuarioRecuperar = usuarioDAO.buscarPorUserEspecifico(username);
        if (usuarioRecuperar == null || usuarioRecuperar.getRespuestas() == null) {
            JOptionPane.showMessageDialog(null, mensajeHandler.get("mensaje.usuarioNoEncontrado"));
            return;
        }

        List<Respuesta> respuestasValidas = new ArrayList<>();
        for (Respuesta r : usuarioRecuperar.getRespuestas()) {
            if (!r.getRespuesta().isEmpty()) {
                respuestasValidas.add(r);
            }
        }

        if (respuestasValidas.size() < 1) {
            JOptionPane.showMessageDialog(null, mensajeHandler.get("mensaje.noPreguntas"));
            return;
        }

        preguntaActual = respuestasValidas.get((int)(Math.random() * respuestasValidas.size()));
        String textoPregunta = Pregunta.crearPreguntas().get(preguntaActual.getId() - 1).getPregunta();
        olvideContrasenaView.getPreguntaAleatoriaLabel().setText(textoPregunta);
    }


    private void guardarNuevaContrasena() {
        if (usuarioRecuperar == null || preguntaActual == null) {
            JOptionPane.showMessageDialog(null, mensajeHandler.get("mensaje.buscarPrimero"));
            return;
        }

        String respuestaIngresada = olvideContrasenaView.getTextField2().getText();
        String nuevaContrasena = olvideContrasenaView.getTextField3().getText();

        if (respuestaIngresada.isEmpty() || nuevaContrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, mensajeHandler.get("mensaje.llenarCampos"));
            return;
        }

        if (preguntaActual.getRespuesta().equalsIgnoreCase(respuestaIngresada.trim())) {
            usuarioRecuperar.setContrasenia(nuevaContrasena);
            usuarioDAO.actualizar(usuarioRecuperar.getUsername(), usuarioRecuperar);
            JOptionPane.showMessageDialog(null, mensajeHandler.get("mensaje.contrasenaActualizada") + ": " + nuevaContrasena);
            olvideContrasenaView.setVisible(false);
            loginView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, mensajeHandler.get("mensaje.respuestaIncorrecta"));

            // Buscar otra pregunta diferente a la actual
            List<Respuesta> respuestasValidas = new ArrayList<>();
            for (Respuesta r : usuarioRecuperar.getRespuestas()) {
                if (!r.getRespuesta().isEmpty() && r.getId() != preguntaActual.getId()) {
                    respuestasValidas.add(r);
                }
            }

            if (respuestasValidas.isEmpty()) {
                JOptionPane.showMessageDialog(null, mensajeHandler.get("mensaje.noMasPreguntas"));
            } else {
                preguntaActual = respuestasValidas.get((int)(Math.random() * respuestasValidas.size()));
                String textoPregunta = Pregunta.crearPreguntas().get(preguntaActual.getId() - 1).getPregunta();
                olvideContrasenaView.getPreguntaAleatoriaLabel().setText(textoPregunta);
            }
        }
    }

    private void buscarUsuario(){
        String username= listarUsuarioView.getTextNombre().getText();

        if (listarUsuarioView.getTextNombre().getText().isEmpty()){
            listarUsuarioView.mostrarMensajes(mensajeHandler.get("mensaje.llenarCampos"));
            return ;
        }

        if(username.isEmpty()){
            listarUsuarioView.mostrarMensajes(mensajeHandler.get("mensaje.inserteUsuario"));
            return;
        }

        List<Usuario> usuarios = usuarioDAO.buscarPorUsername(username);
        listarUsuarioView.cargarDatos(usuarios);
    }

    private void buscarUsuarioAc(){
        String username= usuarioActualizarView.getTextField1().getText();

        if(username.isEmpty()){
            usuarioActualizarView.mostrarMensaje(mensajeHandler.get("mensaje.inserteUsuario"));
            return;
        }

        Usuario usuario = usuarioDAO.buscarPorUserEspecifico(username);
        if (usuario != null) {
            usuarioActualizarView.getTextField2().setText(usuario.getUsername());
            usuarioActualizarView.getTextField3().setText(usuario.getContrasenia());
        }
    }


    private void addUsuario(){
        String rol = anadirUsuarioView.getComboBox1().getSelectedItem().toString();
        String username = anadirUsuarioView.getTextField1().getText();
        String password = anadirUsuarioView.getTextField2().getText();
        String nombre = anadirUsuarioView.getNombreTxtF().getText();
        String apellido = anadirUsuarioView.getApellidoTxtF().getText();
        String cedula = anadirUsuarioView.getCedulaTxtF().getText();
        String genero = anadirUsuarioView.getComboBox2().getSelectedItem().toString();
        GregorianCalendar fechaNacimiento= anadirUsuarioView.obtenerFechaNacimiento();
        if (rol.isEmpty()||username.isEmpty()||password.isEmpty()||nombre.isEmpty()||apellido.isEmpty()||cedula.isEmpty()||genero.isEmpty()||fechaNacimiento==null){
            anadirUsuarioView.mostrarMensaje(mensajeHandler.get("mensaje.llenarCampos"));
            return ;
        }

        if (rol.equalsIgnoreCase("USUARIO")){
            Usuario usuarioNuevo = new Usuario(username,password,Rol.USUARIO,null,nombre,apellido,cedula,genero,fechaNacimiento);
            usuarioDAO.crear(usuarioNuevo);
        }else if (rol.equalsIgnoreCase("ADMINISTRADOR")){
            Usuario usuarioNuevo = new Usuario(username,password,Rol.ADMINISTRADOR,null,nombre,apellido,cedula,genero,fechaNacimiento);
            usuarioDAO.crear(usuarioNuevo);
        }
        anadirUsuarioView.mostrarMensaje(mensajeHandler.get("mensaje.usuarioCreado"));
        anadirUsuarioView.limpiar();
        System.out.println(usuarioDAO.listarTodos());
    }

    private void limpiarAddUser(){
        anadirUsuarioView.getTextField1().setText("");
        anadirUsuarioView.getTextField2().setText("");
    }

    private void autenticar() {
        String username = loginView.getUsuarioText().getText();
        String contrasenia = loginView.getPasswordField1().getText();

        usuario = usuarioDAO.autenticar(username, contrasenia);

        if (usuario == null) {
            loginView.mostrarMensaje(mensajeHandler.get("mensaje.autenticacionIncorrecta"));
        } else {
            List<Respuesta> respuestas = usuario.getRespuestas();
            if (respuestas == null || respuestas.size() < 3) {
                mostrarPreguntasEnVista();
                registrarPreguntaView.setVisible(true);
                loginView.setVisible(false);
            } else {
                loginView.dispose();
            }
        }
    }

    private void limpiar(){
        loginView.getUsuarioText().setText("");
        loginView.getPasswordField1().setText("");
    }

    public Usuario getUsuarioAutenticado(){
        return usuario;
    }

    public void registrarUsuario(){
        String cedula = registrarseView.getCedulaLabel().getText();
        String nombre = registrarseView.getNombreLabel().getText();
        String apellido = registrarseView.getApellidoLabel().getText();
        String genero = registrarseView.getComboBox1().getSelectedItem().toString();
        GregorianCalendar fechaNacimiento = registrarseView.obtenerFechaNacimiento();
        String username = registrarseView.getUserTexfld().getText();
        String pasword = registrarseView.getPasswordTexfld().getText();
        if (cedula.isEmpty()||nombre.isEmpty()||apellido.isEmpty()||genero.isEmpty()||fechaNacimiento==null||username.isEmpty()||pasword.isEmpty()){
            registrarseView.mostrarMensaje(mensajeHandler.get("mensaje.llenarCampos"));
        } else {
            usuario = new Usuario(username,pasword,Rol.USUARIO,null,nombre,apellido,cedula,genero,fechaNacimiento);
            usuarioDAO.crear(usuario);
        }
        registrarseView.setVisible(false);
        mostrarPreguntasEnVista();
        registrarPreguntaView.setVisible(true);
    }

    private void guardarRespuestas() {
        if (usuario == null) {
            registrarPreguntaView.mostrarMensaje(mensajeHandler.get("mensaje.noUsuarioRegistrado"));
            return;
        }

        List<Respuesta> respuestas = new ArrayList<>();
        int respondidas = 0;

        String[] respuestasTexto = {
                registrarPreguntaView.getTextField1().getText(),
                registrarPreguntaView.getTextField2().getText(),
                registrarPreguntaView.getTextField3().getText(),
                registrarPreguntaView.getTextField4().getText(),
                registrarPreguntaView.getTextField5().getText(),
                registrarPreguntaView.getTextField6().getText(),
                registrarPreguntaView.getTextField7().getText(),
                registrarPreguntaView.getTextField8().getText(),
                registrarPreguntaView.getTextField9().getText(),
                registrarPreguntaView.getTextField10().getText()
        };

        for (int i = 0; i < respuestasTexto.length; i++) {
            String texto = respuestasTexto[i];
            if (!texto.equals("")) {
                respondidas++;
            }
            // Se asigna un id del 1 al 10 (i+1)
            respuestas.add(new Respuesta(i + 1, texto));
        }

        if (respondidas < 3) {
            registrarPreguntaView.mostrarMensaje(mensajeHandler.get("mensaje.debeResponderTres"));
            return;
        }

        usuario.setRespuestas(respuestas);
        usuarioDAO.actualizar(usuario.getUsername(), usuario); // actualiza con respuestas nuevas

        registrarPreguntaView.mostrarMensaje(mensajeHandler.get("mensaje.usuarioRegistrado"));
        registrarPreguntaView.setVisible(false);
        loginView.setVisible(true);
    }

    private void mostrarPreguntasEnVista() {
        List<Pregunta> preguntas = Pregunta.crearPreguntas();

        registrarPreguntaView.getPregunta1Label().setText(preguntas.get(0).getPregunta());
        registrarPreguntaView.getPregunta2Label().setText(preguntas.get(1).getPregunta());
        registrarPreguntaView.getPregunta3Label().setText(preguntas.get(2).getPregunta());
        registrarPreguntaView.getPregunta4Label().setText(preguntas.get(3).getPregunta());
        registrarPreguntaView.getPregunta5Label().setText(preguntas.get(4).getPregunta());
        registrarPreguntaView.getPregunta6Label().setText(preguntas.get(5).getPregunta());
        registrarPreguntaView.getPregunta7Label().setText(preguntas.get(6).getPregunta());
        registrarPreguntaView.getPregunta8Label().setText(preguntas.get(7).getPregunta());
        registrarPreguntaView.getPregunta9Label().setText(preguntas.get(8).getPregunta());
        registrarPreguntaView.getPregunta10Label().setText(preguntas.get(9).getPregunta());

    }

    private void listarUsuarios(){
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        listarUsuarioView.cargarDatos(usuarios);
    }
    private void actualizarUser(){
        String username = usuarioActualizarView.getTextField1().getText();
        if(username.isEmpty()){
            usuarioActualizarView.mostrarMensaje(mensajeHandler.get("mensaje.llenarCampoBuscar"));
            return ;
        }
        Usuario usuarioEn = usuarioDAO.buscarPorUserEspecifico(username);
        if (usuarioEn != null) {
            usuarioActualizarView.getTextField2().setText(usuarioEn.getUsername());
            usuarioActualizarView.getTextField3().setText(usuarioEn.getContrasenia());
        }

        String nUsername = usuarioActualizarView.getTextField4().getText();
        String nPassword = usuarioActualizarView.getTextField5().getText();

        if (nUsername.isEmpty() || nPassword.isEmpty()){
            usuarioActualizarView.mostrarMensaje(mensajeHandler.get("mensaje.llenarCampos"));
            return ;
        }

        int conf = JOptionPane.showConfirmDialog(null, mensajeHandler.get("mensaje.confirmarActualizacion"), mensajeHandler.get("usuarioAct.titulo"), JOptionPane.YES_NO_OPTION);

        if (conf == JOptionPane.YES_OPTION){
            Usuario usuarioAc = new Usuario(nUsername, nPassword,usuarioEn.getRol(), usuarioEn.getRespuestas());
            usuarioDAO.actualizar(usuarioEn.getUsername(),usuarioAc);
            usuarioActualizarView.mostrarMensaje(mensajeHandler.get("mensaje.usuarioActualizado"));
            usuarioActualizarView.limpiar();
        } else {
            usuarioActualizarView.mostrarMensaje(mensajeHandler.get("mensaje.actualizacionCancelada"));
            usuarioActualizarView.limpiar();
        }
    }

    public void logout() {
        this.usuario = null;
        loginView.limpiar();
        loginView.setVisible(true);
    }
}
