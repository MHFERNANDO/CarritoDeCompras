package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class MenuLogin extends JMenuBar {

    private JMenu menuIdioma;
    private JMenuItem menuEspanol;
    private JMenuItem menuEnglish;
    private JMenuItem menuFranc;
    private JMenuItem menuItalian;

    public MenuLogin(MensajeInternacionalizacionHandler mensajeHandler, LoginView loginView) {
        this(mensajeHandler); // llama al constructor común
        menuEspanol.addActionListener(e -> {
            mensajeHandler.setLenguaje("es", "EC");
            loginView.actualizarTextos();
        });
        menuEnglish.addActionListener(e -> {
            mensajeHandler.setLenguaje("en", "US");
            loginView.actualizarTextos();
        });
        menuFranc.addActionListener(e -> {
            mensajeHandler.setLenguaje("fr", "FR");
            loginView.actualizarTextos();
        });
        menuItalian.addActionListener(e -> {
            mensajeHandler.setLenguaje("it", "IT");
            loginView.actualizarTextos();
        });
    }

    public MenuLogin(MensajeInternacionalizacionHandler mensajeHandler, OlvideContrasenaView olvideView) {
        this(mensajeHandler);
        menuEspanol.addActionListener(e -> {
            mensajeHandler.setLenguaje("es", "EC");
            olvideView.actualizarTextos();
        });
        menuEnglish.addActionListener(e -> {
            mensajeHandler.setLenguaje("en", "US");
            olvideView.actualizarTextos();
        });
        menuFranc.addActionListener(e -> {
            mensajeHandler.setLenguaje("fr", "FR");
            olvideView.actualizarTextos();
        });
        menuItalian.addActionListener(e -> {
            mensajeHandler.setLenguaje("it", "IT");
            olvideView.actualizarTextos();
        });
    }
    public MenuLogin(MensajeInternacionalizacionHandler mensajeHandler, RegistrarseView registrarseView) {
        this(mensajeHandler);
        menuEspanol.addActionListener(e -> {
            mensajeHandler.setLenguaje("es", "EC");
            registrarseView.actualizarTextos();
        });
        menuEnglish.addActionListener(e -> {
            mensajeHandler.setLenguaje("en", "US");
            registrarseView.actualizarTextos();
        });
        menuFranc.addActionListener(e -> {
            mensajeHandler.setLenguaje("fr", "FR");
            registrarseView.actualizarTextos();
        });
        menuItalian.addActionListener(e -> {
            mensajeHandler.setLenguaje("it", "IT");
            registrarseView.actualizarTextos();
        });
    }

    public MenuLogin(MensajeInternacionalizacionHandler mensajeHandler, RegistrarPreguntaView registrarPreguntaView) {
        this(mensajeHandler);
        menuEspanol.addActionListener(e -> {
            mensajeHandler.setLenguaje("es", "EC");
            registrarPreguntaView.actualizarTextos();
        });
        menuEnglish.addActionListener(e -> {
            mensajeHandler.setLenguaje("en", "US");
            registrarPreguntaView.actualizarTextos();
        });
        menuFranc.addActionListener(e -> {
            mensajeHandler.setLenguaje("fr", "FR");
            registrarPreguntaView.actualizarTextos();
        });
        menuItalian.addActionListener(e -> {
            mensajeHandler.setLenguaje("it", "IT");
            registrarPreguntaView.actualizarTextos();
        });
    }

    // Constructor común
    private MenuLogin(MensajeInternacionalizacionHandler mensajeHandler) {
        menuIdioma = new JMenu("Idioma");

        menuEspanol = new JMenuItem("Español");
        menuEnglish = new JMenuItem("Inglés");
        menuFranc = new JMenuItem("Francés");
        menuItalian = new JMenuItem("Italiano");

        menuIdioma.add(menuEspanol);
        menuIdioma.add(menuEnglish);
        menuIdioma.add(menuFranc);
        menuIdioma.add(menuItalian);

        this.add(menuIdioma);
    }
}
