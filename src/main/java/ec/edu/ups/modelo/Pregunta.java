package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {

    private int id;
    private String pregunta;

    List<Pregunta> preguntasList = new ArrayList<>();

    public Pregunta(int id, String pregunta) {
        this.id = id;
        this.pregunta = pregunta;
        crearPreguntas();

    }

    public void crearPreguntas(){
        String[] preguntas = {
                "¿Cual es tu color favorito?",
                "¿Cual es el nombre de tu primera mascota?",
                "¿Cual es tu pelicula favorita?",
                "¿Cual es tu cancion favorita?",
                "¿Cual es tu ciudad favorita a visitar?",
                "¿Cual es tu deporte favorito?",
                "¿Cual es tu serie favorita?",
                "¿Cual es tu heroe favorito?",
                "¿Cual es tu banda/artista favorito?",
                "¿Cual es tu videojuego favorito?",};
        for (int i = 0; i <preguntas.length;i++){
            Pregunta pregunta = new Pregunta(i+1,preguntas[i]);
            preguntasList.add(pregunta);
        }





    }
}
