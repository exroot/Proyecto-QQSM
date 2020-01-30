package modelos;

import javafx.beans.property.*;

public class Dificultad {
    private IntegerProperty id;
    private StringProperty dificultad;
    
    public Dificultad () {
        this.id = new SimpleIntegerProperty();
        this.dificultad = new SimpleStringProperty();
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

    public String getDificultad() {
        return dificultad.get();
    }
        
    public StringProperty dificultadProperty() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad.set(dificultad);
    }    
}
