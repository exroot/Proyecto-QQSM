package testinggame.Modelos;

import testinggame.utils.Conexion;
import testinggame.utils.Error;
import testinggame.utils.Respuesta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
    
    //*************************************
    // Simular Login
    //*************************************
    public static String validarUsuario(String username, String contraseña) throws SQLException, ClassNotFoundException, Error {
        try {
            String selectStmt = "SELECT * FROM usuarios WHERE username='" + username + "' AND contraseña='" + contraseña + "';";
            ResultSet results = Conexion.ejecutarQuery(selectStmt);
            if (results.next()) {
                return username;
            }
        } catch (SQLException e) {
            System.out.println("Ocurrio un error durante la operacion validar usuario: " + e);
        }
        // Credenciales invalidas
        return null;
    }
    
    //*******************************
    // Retornar un usuario (por username)
    //*******************************
    public static Usuario mostrar (String username) throws SQLException, ClassNotFoundException, Error {
        // Declarar el SELECT statement
        String selectStmt = "SELECT * FROM usuarios WHERE username='" + username + "';";
        
        try {
            // Obtener ResultSet del método ejecutarQuery
            ResultSet resultUsuario = Conexion.ejecutarQuery(selectStmt);
 
            // Enviar ResultSet a el metodo getUsuario (de ResultSet) y obtener el objeto usuario
            Usuario usuario = getUsuario(resultUsuario);
 
            return usuario;
            
        } catch (SQLException e) {
            System.out.println("Ocurrio un error durante la operacion SELECT * FROM preguntas WHERE: " + e);
            throw e;
        }
    }

    // Usa el ResultSet de la DB como parametro, establece los atributos del objeto respuesta y retorna dicho objeto
    private static Usuario getUsuario(ResultSet resultUsr) throws SQLException {
        Usuario usuario = null;
        if (resultUsr.next()) {
            usuario = new Usuario();
            usuario.setId(resultUsr.getInt("id"));
            usuario.setNombreApellido(resultUsr.getString("nombre_y_apellido"));
            usuario.setUsername(resultUsr.getString("username"));
            usuario.setContraseña(resultUsr.getString("contraseña"));
            usuario.setSexo(resultUsr.getString("sexo"));
            usuario.setPuntaje(resultUsr.getDouble("puntaje"));
        }
        return usuario;
    }
    
    //*******************************
    // Mostrar TODOS los usuarios
    //*******************************
    public static ObservableList<Usuario> getUsuarios() throws SQLException, ClassNotFoundException, Error {
        // Declarando el SELECT statement
        String selectStmt = "SELECT * FROM usuarios";
       
        try {
            // Obtener ResultSet del metodo ejecutarQuery
            ResultSet resultUsuarios = Conexion.ejecutarQuery(selectStmt);
 
            // Enviar el ResultSet a el metodo getListaUsuarios
            ObservableList<Usuario> usuarios = getUsuarios(resultUsuarios);
 
            return usuarios;
            
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Usuario> getUsuarios(ResultSet rsUsuarios) throws SQLException {
        // Declaración de una observable List con los objetos Preguntas
        ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
 
        while (rsUsuarios.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rsUsuarios.getInt("id"));
            usuario.setNombreApellido(rsUsuarios.getString("nombre_y_apellido"));
            usuario.setUsername(rsUsuarios.getString("username"));
            usuario.setContraseña(rsUsuarios.getString("contraseña"));
            usuario.setFecha(rsUsuarios.getString("fecha_de_nacimiento"));
            usuario.setSexo(rsUsuarios.getString("sexo"));
            usuario.setPuntaje(rsUsuarios.getDouble("puntaje"));

            listaUsuarios.add(usuario);
        }
        // Retorna la lista de preguntas 
        return listaUsuarios;
    }
    
    public static List<Usuario> getListaUsuarios() throws SQLException, ClassNotFoundException, Error {
        // Declarando el SELECT statement
        String selectStmt = "SELECT * FROM usuarios";
       
        try {
            // Obtener ResultSet del metodo ejecutarQuery
            ResultSet resultUsuarios = Conexion.ejecutarQuery(selectStmt);
 
            // Enviar el ResultSet a el metodo getListaUsuarios
            List<Usuario> usuarios = getListaUsuarios(resultUsuarios);
 
            return usuarios;
            
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static List<Usuario> getListaUsuarios(ResultSet rsUsuarios) throws SQLException {
        // Declaración de una observable List con los objetos Preguntas
        ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
 
        while (rsUsuarios.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rsUsuarios.getInt("id"));
            usuario.setNombreApellido(rsUsuarios.getString("nombre_y_apellido"));
            usuario.setUsername(rsUsuarios.getString("username"));
            usuario.setContraseña(rsUsuarios.getString("contraseña"));
            usuario.setFecha(rsUsuarios.getString("fecha_de_nacimiento"));
            usuario.setSexo(rsUsuarios.getString("sexo"));
            usuario.setPuntaje(rsUsuarios.getDouble("puntaje"));

            listaUsuarios.add(usuario);
        }
        // Retorna la lista de preguntas 
        return listaUsuarios;
    }
    
    //*************************************
    // Actualizar usuario (por id)
    //*************************************
    public static Respuesta actualizar (String id, String username, String nombreApellido, String sexo) throws SQLException, ClassNotFoundException, Error {
        // Declarando el UPDATE statement
        if (existe(username)) {
            Respuesta r = new Respuesta(2, "El nombre de usuario ya existe.");
            return r;
        }
        String updateStmt = "UPDATE usuarios SET username ='" + username + "', nombre_y_apellido='" + nombreApellido + "', sexo='" + sexo + "' WHERE id = '" + id + "';"; 
        try {
            Conexion.ejecutarUpdate(updateStmt);
            Respuesta r = new Respuesta(1, "Usuario actualizado exitosamente.");
            return r;
        } catch (SQLException e) {
            System.out.print("Ocurrio un error durante la operacion UPDATE: " + e);
            throw e;
        }
    }
    
    //*************************************
    //  Eliminar un usuario (por username)
    //*************************************
    public static void eliminar (String username) throws SQLException, ClassNotFoundException, Error {
        // Declarando el DELETE statement
        String deleteStmt = "DELETE FROM usuarios WHERE username='"+ username +"';";
 
        try {
            Conexion.ejecutarUpdate(deleteStmt);
        } catch (SQLException e) {
            System.out.print("Ocurrio un error durante la operacion DELETE: " + e);
            throw e;
        }
    }
    
    public static boolean existe(String username) {
        // Declarando el SELECT statement
        String selectStmt = "SELECT * FROM usuarios WHERE username='" + username + "';";
            ResultSet results;
        try {
            results = Conexion.ejecutarQuery(selectStmt);
            if (results.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Error ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //*************************************
    // Crear un usuario
    //*************************************
    public static Respuesta insertar (String nombreApellido, String username, String contraseña, String sexo, String puntaje) throws SQLException, ClassNotFoundException, Error {
        // Declarando el INSERT statement
        if (existe(username)) {
            Respuesta r = new Respuesta(2, "El nombre de usuario ya existe.");
            return r;
        }
        String insertStmt = "INSERT INTO usuarios (nombre_y_apellido, username, contraseña, sexo, puntaje) VALUES ('" + nombreApellido + "', '" + username + "', '" + contraseña + "', '" + sexo + "', '"+ puntaje + "' );";
 
        try {
            Conexion.ejecutarUpdate(insertStmt);
            Respuesta r = new Respuesta(1, "Usuario registrado exitosamente.");
            return r;
        } catch (SQLException e) {
            System.out.print("Ocurrio un error durante la operacion INSERT: " + e);
            throw new Error(1);
        }
    }
}
