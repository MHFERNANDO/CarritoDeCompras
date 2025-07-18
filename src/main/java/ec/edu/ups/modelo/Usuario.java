package ec.edu.ups.modelo;

import ec.edu.ups.util.CedulaException;
import ec.edu.ups.util.PasswordException;

import java.util.GregorianCalendar;
import java.util.List;

public class Usuario {
    private String username;
    private String contrasenia;
    private Rol rol;
    private List<Respuesta> respuestas;
    private String nombre;
    private String apellido;
    private String cedula;
    private String genero;
    private GregorianCalendar fechaNac;


    public Usuario() {

    }

    public Usuario(String username, String contrasenia, Rol rol, List<Respuesta> respuestas, String nombre, String apellido, String cedula, String genero, GregorianCalendar fechaNac) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.respuestas = respuestas;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.genero = genero;
        this.fechaNac = fechaNac;
    }

    public Usuario(String nombreDeUsuario, String contrasenia, Rol rol, List<Respuesta> respuestas) {
        this.username = nombreDeUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.respuestas = respuestas;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) throws PasswordException {
        if (!esPasswordValida(contrasenia)) {
            throw new PasswordException("La contraseña no cumple con los requisitos.");
        }
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) throws CedulaException {
        if (!esCedulaValida(cedula)) {
            throw new CedulaException("La cédula ecuatoriana no es válida.");
        }
        this.cedula = cedula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public GregorianCalendar getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(GregorianCalendar fechaNac) {
        this.fechaNac = fechaNac;
    }

    private boolean esPasswordValida(String password) {
        if (password == null || password.length() < 8) return false;
        if (!password.matches(".*[A-Z].*")) return false;
        if (!password.matches(".*[a-z].*")) return false;
        if (!password.matches(".*\\d.*")) return false;
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) return false;

        return true;
    }



    private boolean esCedulaValida(String cedula) {
        if (cedula == null || cedula.length() != 10) {
            return false;
        }
        for (char c : cedula.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        int provincia = Integer.parseInt(cedula.substring(0, 2));
        int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
        if (provincia < 1 || provincia > 24 || tercerDigito >= 6) {
            return false;
        }

        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int valor = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                valor *= 2;
                if (valor > 9) valor -= 9;
            }
            suma += valor;
        }

        int digitoVerificador = Integer.parseInt(cedula.substring(9));
        int resultado = (10 - (suma % 10)) % 10;

        return resultado == digitoVerificador;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreDeUsuario='" + username + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                '}';
    }
}
