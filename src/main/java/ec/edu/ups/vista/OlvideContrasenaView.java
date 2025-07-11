package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;

public class OlvideContrasenaView extends JFrame {

    private JPanel panelPrincipal;
    private JTextField textField1;
    private JButton buscarButton;
    private JTextField textField2;
    private JTextField textField3;
    private JButton guardarContraseñaButton;
    private JLabel preguntaAleatoriaLabel;
    private JLabel nuevaContraLabel;
    private JLabel recuperarContraLabel;
    private JLabel usuarioLabel;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public OlvideContrasenaView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("olvide.titulo"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);


        setJMenuBar(new MenuLogin(mensajeHandler,this));


        actualizarTextos();

        URL buscarURL = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (buscarURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(buscarURL);
            buscarButton.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }

        URL addURL = LoginView.class.getClassLoader().getResource("imagenes/guardar.png");
        if (addURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(addURL);
            guardarContraseñaButton.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }

    }


    public void actualizarTextos() {
        setTitle(mensajeHandler.get("olvide.titulo"));
        recuperarContraLabel.setText(mensajeHandler.get("olvide.label.titulo"));
        usuarioLabel.setText(mensajeHandler.get("olvide.label.usuario"));
        preguntaAleatoriaLabel.setText(mensajeHandler.get("olvide.label.pregunta"));
        nuevaContraLabel.setText(mensajeHandler.get("olvide.label.nueva"));
        buscarButton.setText(mensajeHandler.get("olvide.boton.buscar"));
        guardarContraseñaButton.setText(mensajeHandler.get("olvide.boton.guardar"));
    }

    // Getters y setters
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public JButton getGuardarContraseñaButton() {
        return guardarContraseñaButton;
    }

    public JLabel getPreguntaAleatoriaLabel() {
        return preguntaAleatoriaLabel;
    }

    public JLabel getNuevaContraLabel() {
        return nuevaContraLabel;
    }

    public JLabel getRecuperarContraLabel() {
        return recuperarContraLabel;
    }

    public JLabel getUsuarioLabel() {
        return usuarioLabel;
    }
}
