package testinggame.Modelos;

import javafx.beans.property.*;

public class Categoria {
    private IntegerProperty id;
    private StringProperty categoria;
    
    public Categoria() {
        this.id = new SimpleIntegerProperty();
        this.categoria = new SimpleStringProperty();
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
    
    public String getCategoria() {
        return categoria.get();
    }

    public StringProperty categoriaProperty() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }
}
