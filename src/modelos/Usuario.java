package modelos;
import javafx.beans.property.*;

public class Usuario {
    private IntegerProperty id;
    private IntegerProperty cedula;
    private StringProperty username;
    private StringProperty contraseña;
    private DoubleProperty puntaje;
    
    public Usuario() {
        this.id = new SimpleIntegerProperty();
        this.cedula = new SimpleIntegerProperty();
        this.username = new SimpleStringProperty();
        this.contraseña = new SimpleStringProperty();
        this.puntaje = new SimpleDoubleProperty();
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
    
    public Integer getCedula() {
        return cedula.get();
    }

    public IntegerProperty cedulaProperty() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula.set(cedula);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }
    
    public String getContraseña() {
        return contraseña.get();
    }

    public StringProperty contraseñaProperty() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña.set(contraseña);
    }

    public Double getPuntaje() {
        return puntaje.get();
    }
    public DoubleProperty puntajeProperty() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje.set(puntaje);
    }
}
