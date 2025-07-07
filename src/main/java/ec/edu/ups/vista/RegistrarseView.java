package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class RegistrarseView extends JFrame {

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
    private JComboBox<String> comboBox1;
    private JTextField fechaNacimientotxtF;
    private JLabel cedulaLabel;
    private JLabel generoLabel;
    private JLabel fechaDeNacimientoLabel;
    private JLabel usuarioLabel;
    private JLabel contraseniaLabel;
    private JLabel registrarUsuarioLabel;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public RegistrarseView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("registrarse.titulo"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(false);

        URL listaURL = LoginView.class.getClassLoader().getResource("imagenes/add1.png");
        if (listaURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(listaURL);
            registrarButton.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }

        cargarDatos();

        // Agregar menú para cambio de idioma
        setJMenuBar(new MenuLogin(mensajeHandler, this));

        actualizarTextos();
    }

    private void cargarDatos() {
        comboBox1.removeAllItems();

        comboBox1.addItem(mensajeHandler.get("genero.masculino"));
        comboBox1.addItem(mensajeHandler.get("genero.femenino"));
        comboBox1.addItem(mensajeHandler.get("genero.prefiero_no_decirlo"));
    }

    public void actualizarTextos() {
        setTitle(mensajeHandler.get("registrarse.titulo"));

        registrarUsuarioLabel.setText(mensajeHandler.get("registrarse.titulo"));
        nombreLabel.setText(mensajeHandler.get("registrarse.nombre"));
        apellidoLabel.setText(mensajeHandler.get("registrarse.apellido"));
        cedulaLabel.setText(mensajeHandler.get("registrarse.cedula"));
        generoLabel.setText(mensajeHandler.get("registrarse.genero"));
        fechaDeNacimientoLabel.setText(mensajeHandler.get("registrarse.fechaNacimiento"));
        usuarioLabel.setText(mensajeHandler.get("registrarse.usuario"));
        contraseniaLabel.setText(mensajeHandler.get("registrarse.contrasenia"));
        registrarButton.setText(mensajeHandler.get("registrarse.boton.registrar"));

        // Actualizar items del comboBox para género
        cargarDatos();
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

    public JComboBox<String> getComboBox1() {
        return comboBox1;
    }

    public void setComboBox1(JComboBox<String> comboBox1) {
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

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
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
