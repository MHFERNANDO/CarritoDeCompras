package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;

public class LoginView extends JFrame {

    private JPanel panelPrincipal;
    private JButton registrarseButton;
    private JButton iniciarSesiónButton;
    private JTextField usuarioText;
    private JPasswordField passwordField1;
    private JButton olvideMiContraseñaButton;
    private JLabel iniciarSesionLabel;
    private JLabel usuarioLabel;
    private JLabel contraseniaLabel;
    private JTextField contraText;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public LoginView(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.mensajeHandler = mensajeInternacionalizacionHandler;

        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("login.titulo"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        URL iconURL = getClass().getResource("icons8-usuario-30.png");
        ImageIcon icon = new ImageIcon(iconURL);
        iniciarSesiónButton.setIcon(icon);

        setJMenuBar(new MenuLogin(mensajeHandler, this));
        actualizarTextos();
    }

    public void actualizarTextos() {
        setTitle(mensajeHandler.get("login.titulo"));
        iniciarSesionLabel.setText(mensajeHandler.get("login.iniciar"));
        usuarioLabel.setText(mensajeHandler.get("login.usuario"));
        contraseniaLabel.setText(mensajeHandler.get("login.contrasena"));
        iniciarSesiónButton.setText(mensajeHandler.get("login.boton.iniciar"));
        registrarseButton.setText(mensajeHandler.get("login.boton.registrarse"));
        olvideMiContraseñaButton.setText(mensajeHandler.get("login.boton.olvido"));
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getRegistrarseButton() {
        return registrarseButton;
    }

    public void setRegistrarseButton(JButton registrarseButton) {
        this.registrarseButton = registrarseButton;
    }

    public JButton getIniciarSesiónButton() {
        return iniciarSesiónButton;
    }

    public void setIniciarSesiónButton(JButton iniciarSesiónButton) {
        this.iniciarSesiónButton = iniciarSesiónButton;
    }

    public JTextField getUsuarioText() {
        return usuarioText;
    }

    public void setUsuarioText(JTextField usuarioText) {
        this.usuarioText = usuarioText;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public void setPasswordField1(JPasswordField passwordField1) {
        this.passwordField1 = passwordField1;
    }

    public JButton getOlvideMiContraseñaButton() {
        return olvideMiContraseñaButton;
    }

    public void setOlvideMiContraseñaButton(JButton olvideMiContraseñaButton) {
        this.olvideMiContraseñaButton = olvideMiContraseñaButton;
    }

    public JLabel getIniciarSesionLabel() {
        return iniciarSesionLabel;
    }

    public void setIniciarSesionLabel(JLabel iniciarSesionLabel) {
        this.iniciarSesionLabel = iniciarSesionLabel;
    }

    public JLabel getUsuarioLabel() {
        return usuarioLabel;
    }

    public void setUsuarioLabel(JLabel usuarioLabel) {
        this.usuarioLabel = usuarioLabel;
    }

    public JLabel getContraseniaLabel() {
        return contraseniaLabel;
    }

    public void setContraseniaLabel(JLabel contraseniaLabel) {
        this.contraseniaLabel = contraseniaLabel;
    }

    public JTextField getContraText() {
        return contraText;
    }

    public void setContraText(JTextField contraText) {
        this.contraText = contraText;
    }

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    public void limpiar(){
        getUsuarioText().setText("");
        getPasswordField1().setText("");
    }
    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
    }
}
