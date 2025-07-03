package ec.edu.ups.vista;

import javax.swing.*;

public class RegistrarseView extends JFrame{
    private JPanel panelPrincipal;
    private JTextField q1textField;
    private JTextField passwordTexfld;
    private JTextField q2textField;
    private JTextField q3textField;
    private JButton registrarButton;
    private JTextField userTexfld;

    public RegistrarseView(){
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
}
