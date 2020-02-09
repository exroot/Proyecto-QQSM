package testinggame.Modelos;

import javafx.beans.property.*;

public class Pregunta {
    private IntegerProperty id;
    private StringProperty pregunta;
    private IntegerProperty categoria_id;
    private IntegerProperty dificultad_id;
    
    public Pregunta() {
        this.id = new SimpleIntegerProperty();
        this.pregunta = new SimpleStringProperty();
        this.categoria_id = new SimpleIntegerProperty();
        this.dificultad_id = new SimpleIntegerProperty();
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

    public String getPregunta() {
        return pregunta.get();
    }
    
    public StringProperty preguntaProperty() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta.set(pregunta);
    }
    
    public Integer getCategoriaId() {
        return categoria_id.get();
    }

    public IntegerProperty categoriaIdProperty() {
        return categoria_id;
    }

    public void setCategoria(Integer categoria_id) {
        this.categoria_id.set(categoria_id);
    }
    
    public Integer getDificultad() {
        return dificultad_id.get();
    }
    
    public IntegerProperty dificultadIdProperty() {
        return dificultad_id;
    }

    public void setDificultad(Integer dificultad_id) {
        this.dificultad_id.set(dificultad_id);
    }
}
