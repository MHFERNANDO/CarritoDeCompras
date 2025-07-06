package ec.edu.ups.vista;

import javax.swing.*;

public class OlvideContrasenaView extends JFrame{
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

    public OlvideContrasenaView(){
        setContentPane(panelPrincipal);
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
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

    public JButton getGuardarContraseñaButton() {
        return guardarContraseñaButton;
    }

    public void setGuardarContraseñaButton(JButton guardarContraseñaButton) {
        this.guardarContraseñaButton = guardarContraseñaButton;
    }

    public JLabel getPreguntaAleatoriaLabel() {
        return preguntaAleatoriaLabel;
    }

    public void setPreguntaAleatoriaLabel(JLabel preguntaAleatoriaLabel) {
        this.preguntaAleatoriaLabel = preguntaAleatoriaLabel;
    }

    public JLabel getNuevaContraLabel() {
        return nuevaContraLabel;
    }

    public void setNuevaContraLabel(JLabel nuevaContraLabel) {
        this.nuevaContraLabel = nuevaContraLabel;
    }

    public JLabel getRecuperarContraLabel() {
        return recuperarContraLabel;
    }

    public void setRecuperarContraLabel(JLabel recuperarContraLabel) {
        this.recuperarContraLabel = recuperarContraLabel;
    }

    public JLabel getUsuarioLabel() {
        return usuarioLabel;
    }

    public void setUsuarioLabel(JLabel usuarioLabel) {
        this.usuarioLabel = usuarioLabel;
    }
}
