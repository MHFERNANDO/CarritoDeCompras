package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {

    private int id;
    private String pregunta;

    public Pregunta(int id, String pregunta) {
        this.id = id;
        this.pregunta = pregunta;
    }

    public int getId() {
        return id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public static List<Pregunta> crearPreguntas(){
        String[] preguntas = {
                "¿Cuál es tu color favorito?",
                "¿Cuál es el nombre de tu primera mascota?",
                "¿Cuál es tu película favorita?",
                "¿Cuál es tu canción favorita?",
                "¿Cuál es tu ciudad favorita a visitar?",
                "¿Cuál es tu deporte favorito?",
                "¿Cuál es tu serie favorita?",
                "¿Cuál es tu héroe favorito?",
                "¿Cuál es tu banda/artista favorito?",
                "¿Cuál es tu videojuego favorito?"
        };
        List<Pregunta> preguntasList = new ArrayList<>();
        for (int i = 0; i < preguntas.length; i++) {
            preguntasList.add(new Pregunta(i + 1, preguntas[i]));
        }
        return preguntasList;
    }
}
