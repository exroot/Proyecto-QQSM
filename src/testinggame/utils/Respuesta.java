package testinggame.utils;

public class Respuesta {
    private String respuesta;
    private Integer status;
    
    public Respuesta(Integer status, String respuesta) {
        this.respuesta = respuesta;
        this.status = status;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
