
package modelos;
import javafx.beans.property.*;

public class Respuesta {
    private IntegerProperty id;
    private IntegerProperty pregunta_id;
    private StringProperty respuesta;
    private BooleanProperty esCorrecta;
    
    public Respuesta() {
        this.id = new SimpleIntegerProperty();
        this.pregunta_id = new SimpleIntegerProperty();
        this.respuesta = new SimpleStringProperty();
        this.esCorrecta = new SimpleBooleanProperty();
    }
    
     public Integer getId() {
        return id.get();
    }
    
    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public Integer getPreguntaId() {
        return pregunta_id.get();
    }
    
    public IntegerProperty preguntaIdProperty() {
        return pregunta_id;
    }

    public void setPregunta(Integer pregunta_id) {
        this.pregunta_id.set(pregunta_id);
    }

    public String getRespuesta() {
        return respuesta.get();
    }
    
    public StringProperty respuestaProperty() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta.set(respuesta);
    }
    
    public Boolean getEsCorreta() {
        return esCorrecta.get();
    }

    public BooleanProperty esCorrectaProperty() {
        return esCorrecta;
    }

    public void setEsCorrecta(Boolean esCorrecta) {
        this.esCorrecta.set(esCorrecta);
    }
}
