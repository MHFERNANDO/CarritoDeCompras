package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class MenuLogin extends JFrame {

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    private JMenuBar menuBar;
    private JMenu menuIdioma;
    private JMenuItem menuEspanol;
    private JMenuItem menuEnglish;
    private JMenuItem menuFranc;
    private JMenuItem menuItalian;

    public MenuLogin(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        menuBar = new JMenuBar();
        menuIdioma = new JMenu("Idioma");
        menuEspanol = new JMenuItem("Español");
        menuEnglish = new JMenuItem("Ingles");
        menuFranc = new JMenuItem("Frances");
        menuItalian = new JMenuItem("Italiano");
    }
}