package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class AnadirUsuarioView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton agregarButton;
    private JButton limpiarButton;
    private JLabel agregarUsuarioLabel;
    private JLabel rolLabel;
    private JLabel usuarioLabel;
    private JLabel contraseniaLabel;
    private JLabel CualesTuColorFavoritoLabel;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JLabel CualEsTuPeliculaFavoritaLabel;
    private JLabel CualEsTuFrutaFavoritaLabel;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public AnadirUsuarioView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("usuarioA.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(false);
        cargarDatos();
        cambiarIdioma();
    }

    private void cargarDatos() {
        comboBox1.removeAllItems();
        comboBox1.addItem(mensajeHandler.get("usuarioA.combo.usuario"));
        comboBox1.addItem(mensajeHandler.get("usuarioA.combo.admin"));
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("usuarioA.titulo"));
        agregarUsuarioLabel.setText(mensajeHandler.get("usuarioA.agregar"));
        rolLabel.setText(mensajeHandler.get("usuarioA.rol"));
        usuarioLabel.setText(mensajeHandler.get("usuarioA.usuario"));
        contraseniaLabel.setText(mensajeHandler.get("usuarioA.contrasenia"));
        agregarButton.setText(mensajeHandler.get("botonUAgregar"));
        limpiarButton.setText(mensajeHandler.get("botonULimpiar"));
        cargarDatos();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public void setComboBox1(JComboBox comboBox1) {
        this.comboBox1 = comboBox1;
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

    public JButton getAgregarButton() {
        return agregarButton;
    }

    public void setAgregarButton(JButton agregarButton) {
        this.agregarButton = agregarButton;
    }

    public JButton getLimpiarButton() {
        return limpiarButton;
    }

    public void setLimpiarButton(JButton limpiarButton) {
        this.limpiarButton = limpiarButton;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JLabel getAgregarUsuarioLabel() {
        return agregarUsuarioLabel;
    }

    public void setAgregarUsuarioLabel(JLabel agregarUsuarioLabel) {
        this.agregarUsuarioLabel = agregarUsuarioLabel;
    }

    public JLabel getRolLabel() {
        return rolLabel;
    }

    public void setRolLabel(JLabel rolLabel) {
        this.rolLabel = rolLabel;
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

    public JLabel getCualesTuColorFavoritoLabel() {
        return CualesTuColorFavoritoLabel;
    }

    public void setCualesTuColorFavoritoLabel(JLabel cualesTuColorFavoritoLabel) {
        CualesTuColorFavoritoLabel = cualesTuColorFavoritoLabel;
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

    public JLabel getCualEsTuPeliculaFavoritaLabel() {
        return CualEsTuPeliculaFavoritaLabel;
    }

    public void setCualEsTuPeliculaFavoritaLabel(JLabel cualEsTuPeliculaFavoritaLabel) {
        CualEsTuPeliculaFavoritaLabel = cualEsTuPeliculaFavoritaLabel;
    }

    public JLabel getCualEsTuFrutaFavoritaLabel() {
        return CualEsTuFrutaFavoritaLabel;
    }

    public void setCualEsTuFrutaFavoritaLabel(JLabel cualEsTuFrutaFavoritaLabel) {
        CualEsTuFrutaFavoritaLabel = cualEsTuFrutaFavoritaLabel;
    }

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    public void limpiar() {
        getTextField1().setText("");
        getTextField2().setText("");
        getTextField3().setText("");
        getTextField4().setText("");
        getTextField5().setText("");
    }
}
