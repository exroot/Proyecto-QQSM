package modelos;
import util.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Error;

public class RespuestaDAO {
    //*******************************
    // Mostrar una respuesta (por id)
    //*******************************
    public static Respuesta mostrar (String respuestaId) throws SQLException, ClassNotFoundException, Error {
        // Declarar el SELECT statement
        String selectStmt = "SELECT * FROM respuestas WHERE id=" + respuestaId;
        
        try {
            // Obtener ResultSet del método ejecutarQuery
            ResultSet resultRespuesta = Conexion.ejecutarQuery(selectStmt);
 
            // Enviar ResultSet a el metodo getRespuesta (de ResultSet) y obtener el objeto respuesta
            Respuesta respuesta = getRespuesta(resultRespuesta);
 
            return respuesta;
            
        } catch (SQLException e) {
            System.out.println("Ocurrio un error durante la operacion SELECT * FROM preguntas WHERE: " + e);
            throw e;
        }
    }

    // Usa el ResultSet de la DB como parametro, establece los atributos del objeto respuesta y retorna dicho objeto
    private static Respuesta getRespuesta(ResultSet resultRes) throws SQLException {
        Respuesta respuesta = null;
        if (resultRes.next()) {
            respuesta = new Respuesta();
            respuesta.setId(resultRes.getInt("id"));
            respuesta.setRespuesta(resultRes.getString("respuesta"));
            respuesta.setEsCorrecta(resultRes.getBoolean("esCorrecta"));
            respuesta.setPregunta(resultRes.getInt("pregunta_id"));
        }
        return respuesta;
    }
    
    //*******************************
    // Mostrar TODAS las respuesta
    //*******************************
    public static ObservableList<Respuesta> getRespuestas() throws SQLException, ClassNotFoundException, Error {
        // Declarando el SELECT statement
        String selectStmt = "SELECT * FROM respuestas";
       
        try {
            // Obtener ResultSet del metodo ejecutarQuery
            ResultSet resultRespuestas = Conexion.ejecutarQuery(selectStmt);
 
            // Enviar el ResultSet a el metodo getListaPreguntas
            ObservableList<Respuesta> listaRespuestas = getListaRespuestas(resultRespuestas);
 
            return listaRespuestas;
            
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Respuesta> getListaRespuestas(ResultSet rsRespuestas) throws SQLException {
        // Declaración de una observable List con los objetos Preguntas
        ObservableList<Respuesta> listaRespuestas = FXCollections.observableArrayList();
 
        while (rsRespuestas.next()) {
            Respuesta respuesta = new Respuesta();
            respuesta.setId(rsRespuestas.getInt("id"));
            respuesta.setRespuesta(rsRespuestas.getString("respuesta"));
            respuesta.setEsCorrecta(rsRespuestas.getBoolean("esCorrecta"));
            respuesta.setPregunta(rsRespuestas.getInt("pregunta_id"));
            
            // Añadir pregunta a la ObservableList
            listaRespuestas.add(respuesta);
        }
        // Retorna la lista de preguntas 
        return listaRespuestas;
    }
    
    //*************************************
    // Actualizar respuesta (por id)
    //*************************************
    public static void actualizarRespuesta (String respuestaId, String respuesta) throws SQLException, ClassNotFoundException, Error {
        // Declarando el UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE respuestas\n" +
                        "      SET respuesta = '" + respuesta + "'\n" +
                        "    WHERE id = " + respuestaId + ";\n" +
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
    //  Eliminar una respuesta (por ID)
    //*************************************
    public static void eliminar (String respuestaId) throws SQLException, ClassNotFoundException, Error {
        // Declarando el DELETE statement
        String deleteStmt =
                "BEGIN\n" +
                        "   DELETE FROM respuestas\n" +
                        "         WHERE id ="+ respuestaId +";\n" +
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
    // Crear una respuesta
    //*************************************
    public static void insertar (String respuesta, String esCorrecta, String preguntaId) throws SQLException, ClassNotFoundException, Error {
        // Declarando el INSERT statement
        String insertStmt =
                "BEGIN\n" +
                        "INSERT INTO preguntas\n" +
                        "(respuesta, esCorrecta, pregunta_id)\n" +
                        "VALUES\n" +
                        "('" + respuesta + ", " + esCorrecta + ", " + preguntaId + "' );\n" +
                        "END;";
 
        try {
            Conexion.ejecutarUpdate(insertStmt);
        } catch (SQLException e) {
            System.out.print("Ocurrio un error durante la operacion INSERT: " + e);
            throw e;
        }
    }
}
