package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class Actualizar extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton cancelarButton;
    private JButton modificarButton;
    private JButton buscarButton;
    private JTextField textNombreShow;
    private JTextField textPrecioShow;
    private JLabel actualizarProductoLabel;
    private JLabel idLabel;
    private JLabel nombreLabel;
    private JLabel precioLabel;
    private JLabel actualizarLabel;
    private JLabel nombre2Label;
    private JLabel precio2Label;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public Actualizar(MensajeInternacionalizacionHandler mensajeHandler){
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("productoAc.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(false);

        cambiarIdioma();
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("productoAc.titulo"));
        actualizarProductoLabel.setText(mensajeHandler.get("productoAc.titulo"));
        idLabel.setText(mensajeHandler.get("productoAc.codigo"));
        nombreLabel.setText(mensajeHandler.get("productoAc.nombre"));
        precioLabel.setText(mensajeHandler.get("productoAc.precio"));
        actualizarLabel.setText(mensajeHandler.get("productoAc.actualizar"));
        nombre2Label.setText(mensajeHandler.get("productoAc.nombre2"));
        precio2Label.setText(mensajeHandler.get("productoAc.precio2"));

        modificarButton.setText(mensajeHandler.get("botonAModificar"));
        cancelarButton.setText(mensajeHandler.get("botonACancelar"));
        buscarButton.setText(mensajeHandler.get("botonABuscar"));
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JTextField getTextNombreShow() {
        return textNombreShow;
    }

    public void setTextNombreShow(JTextField textNombreShow) {
        this.textNombreShow = textNombreShow;
    }

    public JTextField getTextPrecioShow() {
        return textPrecioShow;
    }

    public void setTextPrecioShow(JTextField textPrecioShow) {
        this.textPrecioShow = textPrecioShow;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
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

    public JButton getCancelarButton() {
        return cancelarButton;
    }

    public void setCancelarButton(JButton cancelarButton) {
        this.cancelarButton = cancelarButton;
    }

    public JButton getModificarButton() {
        return modificarButton;
    }

    public void setModificarButton(JButton modificarButton) {
        this.modificarButton = modificarButton;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }
}
