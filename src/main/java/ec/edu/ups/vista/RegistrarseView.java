package ec.edu.ups.vista;

import javax.swing.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class RegistrarseView extends JFrame{
    private JPanel panelPrincipal;
    private JTextField q1textField;
    private JTextField passwordTexfld;
    private JTextField q2textField;
    private JTextField q3textField;
    private JButton registrarButton;
    private JTextField userTexfld;
    private JTextField nombretxtF;
    private JTextField apellidotxtF;
    private JLabel apellidoLabel;
    private JLabel nombreLabel;
    private JTextField cedulatxtF;
    private JComboBox comboBox1;
    private JTextField fechaNacimientotxtF;
    private JLabel cedulaLabel;
    private JLabel generoLabel;
    private JLabel fechaDeNacimientoLabel;
    private JLabel usuarioLabel;
    private JLabel contraseniaLabel;
    private JLabel registrarUsuarioLabel;

    public RegistrarseView(){
        setContentPane(panelPrincipal);
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(false);
        cargarDatos();
    }

    private void cargarDatos(){
        comboBox1.removeAllItems();

        comboBox1.addItem("Masculino");
        comboBox1.addItem("Femenino");
        comboBox1.addItem("Prefiero no decirlo");
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getQ1textField() {
        return q1textField;
    }

    public void setQ1textField(JTextField q1textField) {
        this.q1textField = q1textField;
    }

    public JTextField getPasswordTexfld() {
        return passwordTexfld;
    }

    public void setPasswordTexfld(JTextField passwordTexfld) {
        this.passwordTexfld = passwordTexfld;
    }

    public JTextField getQ2textField() {
        return q2textField;
    }

    public void setQ2textField(JTextField q2textField) {
        this.q2textField = q2textField;
    }

    public JTextField getQ3textField() {
        return q3textField;
    }

    public void setQ3textField(JTextField q3textField) {
        this.q3textField = q3textField;
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }

    public void setRegistrarButton(JButton registrarButton) {
        this.registrarButton = registrarButton;
    }

    public JTextField getUserTexfld() {
        return userTexfld;
    }

    public void setUserTexfld(JTextField userTexfld) {
        this.userTexfld = userTexfld;
    }

    public JTextField getNombretxtF() {
        return nombretxtF;
    }

    public void setNombretxtF(JTextField nombretxtF) {
        this.nombretxtF = nombretxtF;
    }

    public JTextField getApellidotxtF() {
        return apellidotxtF;
    }

    public void setApellidotxtF(JTextField apellidotxtF) {
        this.apellidotxtF = apellidotxtF;
    }

    public JLabel getApellidoLabel() {
        return apellidoLabel;
    }

    public void setApellidoLabel(JLabel apellidoLabel) {
        this.apellidoLabel = apellidoLabel;
    }

    public JLabel getNombreLabel() {
        return nombreLabel;
    }

    public void setNombreLabel(JLabel nombreLabel) {
        this.nombreLabel = nombreLabel;
    }

    public JTextField getCedulatxtF() {
        return cedulatxtF;
    }

    public void setCedulatxtF(JTextField cedulatxtF) {
        this.cedulatxtF = cedulatxtF;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public void setComboBox1(JComboBox comboBox1) {
        this.comboBox1 = comboBox1;
    }

    public JTextField getFechaNacimientotxtF() {
        return fechaNacimientotxtF;
    }

    public void setFechaNacimientotxtF(JTextField fechaNacimientotxtF) {
        this.fechaNacimientotxtF = fechaNacimientotxtF;
    }

    public JLabel getCedulaLabel() {
        return cedulaLabel;
    }

    public void setCedulaLabel(JLabel cedulaLabel) {
        this.cedulaLabel = cedulaLabel;
    }

    public JLabel getGeneroLabel() {
        return generoLabel;
    }

    public void setGeneroLabel(JLabel generoLabel) {
        this.generoLabel = generoLabel;
    }

    public JLabel getFechaDeNacimientoLabel() {
        return fechaDeNacimientoLabel;
    }

    public void setFechaDeNacimientoLabel(JLabel fechaDeNacimientoLabel) {
        this.fechaDeNacimientoLabel = fechaDeNacimientoLabel;
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

    public JLabel getRegistrarUsuarioLabel() {
        return registrarUsuarioLabel;
    }

    public void setRegistrarUsuarioLabel(JLabel registrarUsuarioLabel) {
        this.registrarUsuarioLabel = registrarUsuarioLabel;
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }
    public GregorianCalendar obtenerFechaNacimiento() {
        String fechaTexto = this.fechaNacimientotxtF.getText();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        ParsePosition pos = new ParsePosition(0);
        Date fecha = formato.parse(fechaTexto, pos);
        if (fecha == null) {
            return null;
        }
        GregorianCalendar calendario = new GregorianCalendar();
        calendario.setTime(fecha);
        return calendario;
    }
}
