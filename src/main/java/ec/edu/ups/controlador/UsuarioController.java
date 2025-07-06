package ec.edu.ups.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.*;

import javax.swing.*;

public class UsuarioController {
    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private final AnadirUsuarioView anadirUsuarioView;
    private final ListarUsuarioView listarUsuarioView;
    private final UsuarioActualizarView usuarioActualizarView;
    private final RegistrarseView registrarseView;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, AnadirUsuarioView anadirUsuarioView, ListarUsuarioView listarUsuarioView, UsuarioActualizarView usuarioActualizarView, RegistrarseView registrarseView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.anadirUsuarioView = anadirUsuarioView;
        this.listarUsuarioView = listarUsuarioView;
        this.usuarioActualizarView = usuarioActualizarView;
        this.registrarseView = registrarseView;
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

            }
        });


    }
    private void buscarUsuario(){
        String username= listarUsuarioView.getTextNombre().getText();

        if (listarUsuarioView.getTextNombre().getText().isEmpty()){
            listarUsuarioView.mostrarMensajes("Llenar todos los campos");
            return ;
        }

        if(username.isEmpty()){
            listarUsuarioView.mostrarMensajes("Inserte un usuario a buscar");

            return;
        }

        List<Usuario> usuarios = usuarioDAO.buscarPorUsername(username);
        listarUsuarioView.cargarDatos(usuarios);
    }

    private void buscarUsuarioAc(){
        String username= usuarioActualizarView.getTextField1().getText();

        if(username.isEmpty()){
            usuarioActualizarView.mostrarMensaje("Inserte un usuario a buscar");
            return;
        }

        Usuario usuario = usuarioDAO.buscarPorUserEspecifico(username);
        usuarioActualizarView.getTextField2().setText(usuario.getUsername());
        usuarioActualizarView.getTextField3().setText(usuario.getContrasenia());
    }


    private void addUsuario(){
        String rol = anadirUsuarioView.getComboBox1().getSelectedItem().toString();
        String username = anadirUsuarioView.getTextField1().getText();
        String password = anadirUsuarioView.getTextField2().getText();
        String[] respuestas = {anadirUsuarioView.getTextField3().getText(),
                                anadirUsuarioView.getTextField4().getText(),
                                anadirUsuarioView.getTextField5().getText()};
        if (rol.isEmpty()||username.isEmpty()||password.isEmpty()){
            anadirUsuarioView.mostrarMensaje("dhjvhjf");
            return ;
        }
        if (rol.equalsIgnoreCase("USUARIO")){
            Usuario usuarioNuevo = new Usuario(username,password,Rol.USUARIO,respuestas);
            usuarioDAO.crear(usuarioNuevo);
        }else if (rol.equalsIgnoreCase("ADMINISTRADOR")){
            Usuario usuarioNuevo = new Usuario(username,password,Rol.ADMINISTRADOR,respuestas);
            usuarioDAO.crear(usuarioNuevo);
        }
        anadirUsuarioView.mostrarMensaje("Usuario creado correctamente");
        anadirUsuarioView.limpiar();
        System.out.println(usuarioDAO.listarTodos());

    }
    private void limpiarAddUser(){
        anadirUsuarioView.getTextField1().setText("");
        anadirUsuarioView.getTextField2().setText("");
    }

    private void autenticar(){
        String username = loginView.getUsuarioText().getText();
        String contrasenia = loginView.getPasswordField1().getText();
        usuario = usuarioDAO.autenticar(username, contrasenia);
        if(usuario == null){
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
        }else{
            loginView.dispose();
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

        String username = registrarseView.getUserTexfld().getText();
        String contrasenia = registrarseView.getPasswordTexfld().getText();
        String[] respuestas = {registrarseView.getQ1textField().getText(),
                                registrarseView.getQ2textField().getText(),
                                registrarseView.getQ3textField().getText()};

        Usuario usuarioNuevo= new Usuario(username, contrasenia, Rol.USUARIO,respuestas);
        usuarioDAO.crear(usuarioNuevo);
    }
    private void listarUsuarios(){
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        listarUsuarioView.cargarDatos(usuarios);
    }
    private void actualizarUser(){
        String username = usuarioActualizarView.getTextField1().getText();
        if(username.isEmpty()){
            usuarioActualizarView.mostrarMensaje("Llenar campo a buscar");
            return ;
        }
        Usuario usuarioEn = usuarioDAO.buscarPorUserEspecifico(username);
        usuarioActualizarView.getTextField2().setText(usuarioEn.getUsername());
        usuarioActualizarView.getTextField3().setText(usuarioEn.getContrasenia());

        String nUsername = usuarioActualizarView.getTextField4().getText();
        String nPassword = usuarioActualizarView.getTextField5().getText();

        if (nUsername.isEmpty() || nPassword.isEmpty()){
            usuarioActualizarView.mostrarMensaje("Llene todos los campos");
            return ;
        }

        int conf = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas actualizar", "Actualizar Usuario", JOptionPane.YES_NO_OPTION);

        if (conf == JOptionPane.YES_OPTION){
            Usuario usuarioAc = new Usuario(nUsername, nPassword,usuarioEn.getRol(), usuarioEn.getRespuestas());
            usuarioDAO.actualizar(usuarioEn.getUsername(),usuarioAc);
            usuarioActualizarView.mostrarMensaje("Usuario actualizado correctamente");
            usuarioActualizarView.limpiar();
        }else {
            usuarioActualizarView.mostrarMensaje("Actualizacion cancelada correctamente");
            usuarioActualizarView.limpiar();
        }



    }
    public void logout() {
        this.usuario = null;           // olvidar al usuario autenticado
        loginView.limpiar();           // limpia los campos de texto
        loginView.setVisible(true);    // muestra de nuevo la ventana de login
    }

}
