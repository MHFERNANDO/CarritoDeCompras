package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;

public class RegistrarPreguntaView extends JFrame {

    private JPanel panelPrincipal;
    private JTextField textField1;
    private JLabel pregunta1Label;
    private JLabel pregunta2Label;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JButton registrarButton;
    private JLabel pregunta4Label;
    private JLabel pregunta5Label;
    private JLabel pregunta6Label;
    private JLabel pregunta7Label;
    private JLabel pregunta8Label;
    private JLabel pregunta9Label;
    private JTextField textField10;
    private JLabel pregunta10Label;
    private JLabel pregunta3Label;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public RegistrarPreguntaView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setVisible(false);

        setTitle(mensajeHandler.get("registrarPreguntas.titulo"));

        // Añadir menú para cambiar idioma
        setJMenuBar(new MenuLogin(mensajeHandler, this)); // Adaptar el constructor según tu MenuLogin

        actualizarTextos();

        URL listaURL = LoginView.class.getClassLoader().getResource("imagenes/add1.png");
        if (listaURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(listaURL);
            registrarButton.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }

    }

    public void actualizarTextos() {
        setTitle(mensajeHandler.get("registrarPreguntas.titulo"));

        pregunta1Label.setText(mensajeHandler.get("pregunta.1"));
        pregunta2Label.setText(mensajeHandler.get("pregunta.2"));
        pregunta3Label.setText(mensajeHandler.get("pregunta.3"));
        pregunta4Label.setText(mensajeHandler.get("pregunta.4"));
        pregunta5Label.setText(mensajeHandler.get("pregunta.5"));
        pregunta6Label.setText(mensajeHandler.get("pregunta.6"));
        pregunta7Label.setText(mensajeHandler.get("pregunta.7"));
        pregunta8Label.setText(mensajeHandler.get("pregunta.8"));
        pregunta9Label.setText(mensajeHandler.get("pregunta.9"));
        pregunta10Label.setText(mensajeHandler.get("pregunta.10"));

        registrarButton.setText(mensajeHandler.get("registrarPreguntas.boton.registrar"));
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

    public JLabel getPregunta1Label() {
        return pregunta1Label;
    }

    public void setPregunta1Label(JLabel pregunta1Label) {
        this.pregunta1Label = pregunta1Label;
    }

    public JLabel getPregunta2Label() {
        return pregunta2Label;
    }

    public void setPregunta2Label(JLabel pregunta2Label) {
        this.pregunta2Label = pregunta2Label;
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

    public JTextField getTextField6() {
        return textField6;
    }

    public void setTextField6(JTextField textField6) {
        this.textField6 = textField6;
    }

    public JTextField getTextField7() {
        return textField7;
    }

    public void setTextField7(JTextField textField7) {
        this.textField7 = textField7;
    }

    public JTextField getTextField8() {
        return textField8;
    }

    public void setTextField8(JTextField textField8) {
        this.textField8 = textField8;
    }

    public JTextField getTextField9() {
        return textField9;
    }

    public void setTextField9(JTextField textField9) {
        this.textField9 = textField9;
    }

    public JButton getRegistrarButton() {
        return registrarButton;
    }

    public void setRegistrarButton(JButton registrarButton) {
        this.registrarButton = registrarButton;
    }

    public JLabel getPregunta4Label() {
        return pregunta4Label;
    }

    public void setPregunta4Label(JLabel pregunta4Label) {
        this.pregunta4Label = pregunta4Label;
    }

    public JLabel getPregunta5Label() {
        return pregunta5Label;
    }

    public void setPregunta5Label(JLabel pregunta5Label) {
        this.pregunta5Label = pregunta5Label;
    }

    public JLabel getPregunta6Label() {
        return pregunta6Label;
    }

    public void setPregunta6Label(JLabel pregunta6Label) {
        this.pregunta6Label = pregunta6Label;
    }

    public JLabel getPregunta7Label() {
        return pregunta7Label;
    }

    public void setPregunta7Label(JLabel pregunta7Label) {
        this.pregunta7Label = pregunta7Label;
    }

    public JLabel getPregunta8Label() {
        return pregunta8Label;
    }

    public void setPregunta8Label(JLabel pregunta8Label) {
        this.pregunta8Label = pregunta8Label;
    }

    public JLabel getPregunta9Label() {
        return pregunta9Label;
    }

    public void setPregunta9Label(JLabel pregunta9Label) {
        this.pregunta9Label = pregunta9Label;
    }

    public JTextField getTextField10() {
        return textField10;
    }

    public void setTextField10(JTextField textField10) {
        this.textField10 = textField10;
    }

    public JLabel getPregunta10Label() {
        return pregunta10Label;
    }

    public void setPregunta10Label(JLabel pregunta10Label) {
        this.pregunta10Label = pregunta10Label;
    }

    public JLabel getPregunta3Label() {
        return pregunta3Label;
    }

    public void setPregunta3Label(JLabel pregunta3Label) {
        this.pregunta3Label = pregunta3Label;
    }

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
