package modelos;
import util.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Error;

public class UsuarioDAO {
    //*******************************
    // Mostrar un usuario (por id)
    //*******************************
    public static Usuario mostrar (String usuarioId) throws SQLException, ClassNotFoundException, Error {
        // Declarar el SELECT statement
        String selectStmt = "SELECT * FROM usuarios WHERE id=" + usuarioId;
        
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
            usuario.setCedula(resultUsr.getInt("cedula"));
            usuario.setUsername(resultUsr.getString("username"));
            usuario.setContraseña(resultUsr.getString("contraseña"));
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
            ObservableList<Usuario> listaUsuarios = getListaUsuarios(resultUsuarios);
 
            return listaUsuarios;
            
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Usuario> getListaUsuarios(ResultSet rsUsuarios) throws SQLException {
        // Declaración de una observable List con los objetos Preguntas
        ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
 
        while (rsUsuarios.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rsUsuarios.getInt("id"));
            usuario.setCedula(rsUsuarios.getInt("cedula"));
            usuario.setUsername(rsUsuarios.getString("username"));
            usuario.setContraseña(rsUsuarios.getString("contraseña"));
            usuario.setPuntaje(rsUsuarios.getDouble("puntaje"));

            listaUsuarios.add(usuario);
        }
        // Retorna la lista de preguntas 
        return listaUsuarios;
    }
    
    //*************************************
    // Actualizar usuario (por id)
    //*************************************
    public static void actualizarUsername (String usuarioId, String username) throws SQLException, ClassNotFoundException, Error {
        // Declarando el UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE usuarios\n" +
                        "      SET username = '" + username + "'\n" +
                        "    WHERE id = " + usuarioId + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
 
        try {
            Conexion.ejecutarUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Ocurrio un error durante la operacion UPDATE: " + e);
            throw e;
        }
    }
    
    //*************************************
    //  Eliminar un usuario (por ID)
    //*************************************
    public static void eliminar (String usuarioId) throws SQLException, ClassNotFoundException, Error {
        // Declarando el DELETE statement
        String deleteStmt =
                "BEGIN\n" +
                        "   DELETE FROM usuarios\n" +
                        "         WHERE id ="+ usuarioId +";\n" +
                        "   COMMIT;\n" +
                        "END;";
 
        try {
            Conexion.ejecutarUpdate(deleteStmt);
        } catch (SQLException e) {
            System.out.print("Ocurrio un error durante la operacion DELETE: " + e);
            throw e;
        }
    }
    
    //*************************************
    // Crear un usuario
    //*************************************
    public static void insertar (String cedula, String username, String contraseña, String puntaje) throws SQLException, ClassNotFoundException, Error {
        // Declarando el INSERT statement
        String insertStmt =
                "BEGIN\n" +
                        "INSERT INTO preguntas\n" +
                        "(cedula, username, contraseña, puntaje)\n" +
                        "VALUES\n" +
                        "('" + cedula + ", " + username + ", " + contraseña + ", " + puntaje + "' );\n" +
                        "END;";
 
        try {
            Conexion.ejecutarUpdate(insertStmt);
        } catch (SQLException e) {
            System.out.print("Ocurrio un error durante la operacion INSERT: " + e);
            throw e;
        }
    }
}
