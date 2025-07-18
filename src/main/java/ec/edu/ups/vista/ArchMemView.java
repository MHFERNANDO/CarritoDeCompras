package ec.edu.ups.vista;

import javax.swing.*;

public class ArchMemView extends JFrame {
    private JPanel panel1;
    private JButton memoriaButton;
    private JButton archivosButton;
    private JTextField textField1;

    public ArchMemView() {
        setTitle("Archivos y Memoria");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JButton getMemoriaButton() {
        return memoriaButton;
    }

    public void setMemoriaButton(JButton memoriaButton) {
        this.memoriaButton = memoriaButton;
    }

    public JButton getArchivosButton() {
        return archivosButton;
    }

    public void setArchivosButton(JButton archivosButton) {
        this.archivosButton = archivosButton;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public void limpiarCampos() {
        textField1.setText("");
    }
}
