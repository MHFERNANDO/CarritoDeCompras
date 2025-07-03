package ec.edu.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MensajeInternacionalizacionHandler {

    private ResourceBundle bundle;
    private Locale locale;

    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        setLenguaje(lenguaje, pais);
    }

    public void setLenguaje(String lenguaje, String pais) {
        locale = new Locale(lenguaje, pais);
        bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    public String get(String key) {
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            return "!" + key + "!";
        }
    }

    public Locale getLocale() {
        return locale;
    }
}
