package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;

public class UsuarioActualizarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JButton actualizarButton;
    private JButton limpiarButton;
    private JTextField textField1;
    private JButton buscarButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JLabel usuarioLabel;
    private JLabel actualizarUsuarioLabel;
    private JLabel usuario2Label;
    private JLabel contraseniaLabel;
    private JLabel usuario3Label;
    private JLabel contrasenia2Label;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public UsuarioActualizarView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        initComponents();

        URL buscarURL = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (buscarURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(buscarURL);
            buscarButton.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL acURL = LoginView.class.getClassLoader().getResource("imagenes/actualizar.png");
        if (acURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(buscarURL);
            actualizarButton.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }
        URL limpiarURL = LoginView.class.getClassLoader().getResource("imagenes/x.png");
        if (limpiarURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(limpiarURL);
            limpiarButton.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }

    }

    private void initComponents() {
        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("usuarioAct.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setSize(500, 500);
        setVisible(false);

        actualizarUsuarioLabel.setText(mensajeHandler.get("usuarioAct.label.actualizar"));
        usuarioLabel.setText(mensajeHandler.get("usuarioAct.label.codigo"));
        usuario2Label.setText(mensajeHandler.get("usuarioAct.label.usuario"));
        contraseniaLabel.setText(mensajeHandler.get("usuarioAct.label.contrasenia"));
        usuario3Label.setText(mensajeHandler.get("usuarioAct.label.nuevoUsuario"));
        contrasenia2Label.setText(mensajeHandler.get("usuarioAct.label.nuevaContrasenia"));

        actualizarButton.setText(mensajeHandler.get("botonActualizar.actualizar"));
        limpiarButton.setText(mensajeHandler.get("botonActualizar.limpiar"));
        buscarButton.setText(mensajeHandler.get("botonActualizar.buscar"));
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("usuarioAct.titulo"));
        actualizarUsuarioLabel.setText(mensajeHandler.get("usuarioAct.label.actualizar"));
        usuarioLabel.setText(mensajeHandler.get("usuarioAct.label.codigo"));
        usuario2Label.setText(mensajeHandler.get("usuarioAct.label.usuario"));
        contraseniaLabel.setText(mensajeHandler.get("usuarioAct.label.contrasenia"));
        usuario3Label.setText(mensajeHandler.get("usuarioAct.label.nuevoUsuario"));
        contrasenia2Label.setText(mensajeHandler.get("usuarioAct.label.nuevaContrasenia"));

        actualizarButton.setText(mensajeHandler.get("botonActualizar.actualizar"));
        limpiarButton.setText(mensajeHandler.get("botonActualizar.limpiar"));
        buscarButton.setText(mensajeHandler.get("botonActualizar.buscar"));
    }

    // Getters y Setters

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getActualizarButton() {
        return actualizarButton;
    }

    public void setActualizarButton(JButton actualizarButton) {
        this.actualizarButton = actualizarButton;
    }

    public JButton getLimpiarButton() {
        return limpiarButton;
    }

    public void setLimpiarButton(JButton limpiarButton) {
        this.limpiarButton = limpiarButton;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public void setTextField4(JTextField textField4) {
        this.textField4 = textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public void setTextField5(JTextField textField5) {
        this.textField5 = textField5;
    }

    public JLabel getUsuarioLabel() {
        return usuarioLabel;
    }

    public void setUsuarioLabel(JLabel usuarioLabel) {
        this.usuarioLabel = usuarioLabel;
    }

    public JLabel getActualizarUsuarioLabel() {
        return actualizarUsuarioLabel;
    }

    public void setActualizarUsuarioLabel(JLabel actualizarUsuarioLabel) {
        this.actualizarUsuarioLabel = actualizarUsuarioLabel;
    }

    public JLabel getUsuario2Label() {
        return usuario2Label;
    }

    public void setUsuario2Label(JLabel usuario2Label) {
        this.usuario2Label = usuario2Label;
    }

    public JLabel getContraseniaLabel() {
        return contraseniaLabel;
    }

    public void setContraseniaLabel(JLabel contraseniaLabel) {
        this.contraseniaLabel = contraseniaLabel;
    }

    public JLabel getUsuario3Label() {
        return usuario3Label;
    }

    public void setUsuario3Label(JLabel usuario3Label) {
        this.usuario3Label = usuario3Label;
    }

    public JLabel getContrasenia2Label() {
        return contrasenia2Label;
    }

    public void setContrasenia2Label(JLabel contrasenia2Label) {
        this.contrasenia2Label = contrasenia2Label;
    }

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }
    public void limpiar(){
        getTextField1().setText("");
        getTextField2().setText("");
        getTextField3().setText("");
        getTextField4().setText("");
        getTextField5().setText("");
    }
    public void mostrarMensaje (String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
    }
}
