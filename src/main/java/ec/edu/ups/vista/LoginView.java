package ec.edu.ups.vista;

import javax.swing.*;

public class LoginView extends JFrame {

    private JPanel panelPrincipal;
    private JButton registrarseButton;
    private JButton iniciarSesiónButton;
    private JTextField usuarioText;
    private JTextField contraText;

    public LoginView() {
        setContentPane(panelPrincipal);
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
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

    public JTextField getContraText() {
        return contraText;
    }

    public void setContraText(JTextField contraText) {
        this.contraText = contraText;
    }
}
