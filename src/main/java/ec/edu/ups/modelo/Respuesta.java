package ec.edu.ups.modelo;

public class Respuesta {

    private int id;
    private String respuesta;

    public Respuesta(int id, String respuesta) {
        this.id = id;
        this.respuesta = respuesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
