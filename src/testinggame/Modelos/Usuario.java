package testinggame.Modelos;

import javafx.beans.property.*;

public class Usuario {
    private IntegerProperty id;
    private StringProperty nombre_y_apellido;
    private StringProperty username;
    private StringProperty contraseña;
    private StringProperty fecha_de_nacimiento;
    private StringProperty sexo;
    private DoubleProperty puntaje;
    
    public Usuario() {
        this.id = new SimpleIntegerProperty();
        this.nombre_y_apellido = new SimpleStringProperty();
        this.username = new SimpleStringProperty();
        this.contraseña = new SimpleStringProperty();
        this.fecha_de_nacimiento = new SimpleStringProperty();
        this.sexo = new SimpleStringProperty();
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
    
    
    public String getNombreApellido() {
        return nombre_y_apellido.get();
    }

    public StringProperty nombreApellidoProperty() {
        return nombre_y_apellido;
    }
    
    public void setNombreApellido(String nombreApellido) {
        this.nombre_y_apellido.set(nombreApellido);
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
    
    public String getFecha() { 
        return fecha_de_nacimiento.get();
    }
    
    public StringProperty fechaProperty() {
        return fecha_de_nacimiento;
    }
    
    public void setFecha(String fecha) {
        this.fecha_de_nacimiento.set(fecha);
    }
    
    public String getSexo() {
        return sexo.get();
    }
    
    public StringProperty sexoProperty() {
        return sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo.set(sexo);
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
