package testinggame.Modelos;

import java.sql.PreparedStatement;
import testinggame.utils.Conexion;
import testinggame.utils.Error;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PreguntaDAO {
    //*******************************
    // Mostrar una pregunta (por id)
    //*******************************
    public static Pregunta mostrar (String preguntaId) throws SQLException, ClassNotFoundException, Error {
        // Declarar el SELECT statement
        String selectStmt = "SELECT * FROM preguntas WHERE id=" + preguntaId;
        
        try {
            // Obtener ResultSet del método ejecutarQuery
            ResultSet resultPregunta = Conexion.ejecutarQuery(selectStmt);
 
            // Enviar ResultSet a el metodo getPregunta (de ResultSet) y obtener el objeto pregunta
            Pregunta pregunta = getPregunta(resultPregunta);
 
            return pregunta;
            
        } catch (SQLException e) {
            System.out.println("Ocurrio un error durante la operacion SELECT * FROM preguntas WHERE: " + e);
            throw e;
        }
    }
        
    public static Pregunta mostrarAleatoria (String[] categorias, String dificultadId) throws SQLException, ClassNotFoundException, Error {
        // Declarar el SELECT statement
        
        // Parseando categorias ( ["1", "3", "5"] = (1, 3, 5) )
        String queryCategorias = "";
        String param = "(";
        for(int i=0;i<categorias.length;i++){
            param = param+ categorias[i] +",";
        }
        param = param.substring(0,param.length()-1);
        param=param+")";

        queryCategorias = queryCategorias + param;
        
        System.out.println(queryCategorias);
        String selectStmt = "SELECT * FROM preguntas WHERE categoria_id IN " + queryCategorias + " AND dificultad_id=" + dificultadId + " ORDER BY RAND() LIMIT 1;";
        
        try {
            // Obtener ResultSet del método ejecutarQuery
            ResultSet resultPregunta = Conexion.ejecutarQuery(selectStmt);
 
            // Enviar ResultSet a el metodo getPregunta (de ResultSet) y obtener el objeto pregunta
            Pregunta pregunta = getPregunta(resultPregunta);
 
            return pregunta;
            
        } catch (SQLException e) {
            System.out.println("Ocurrio un error durante la operacion SELECT * FROM preguntas WHERE: " + e);
            throw e;
        }
    }

    // Usa el ResultSet de la DB como parametro, establece los atributos del objeto pregunta y retorna dicho objeto
    private static Pregunta getPregunta(ResultSet resultPreg) throws SQLException {
        Pregunta pregunta = null;
        if (resultPreg.next()) {
            pregunta = new Pregunta();
            pregunta.setId(resultPreg.getInt("id"));
            pregunta.setPregunta(resultPreg.getString("pregunta"));
            pregunta.setCategoria(resultPreg.getInt("categoria_id"));
            pregunta.setDificultad(resultPreg.getInt("dificultad_id"));
        }
        return pregunta;
    }
    
    //*******************************
    // Mostrar TODAS las preguntas
    //*******************************
    public static ObservableList<Pregunta> getPreguntas() throws SQLException, ClassNotFoundException, Error {
        // Declarando el SELECT statement
        String selectStmt = "SELECT * FROM preguntas";
       
        try {
            // Obtener ResultSet del metodo ejecutarQuery
            ResultSet resultPreguntas = Conexion.ejecutarQuery(selectStmt);
 
            // Enviar el ResultSet a el metodo getListaPreguntas
            ObservableList<Pregunta> listaPreguntas = getListaPreguntas(resultPreguntas);
 
            return listaPreguntas;
            
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Pregunta> getListaPreguntas(ResultSet rsPreguntas) throws SQLException {
        // Declaración de una observable List con los objetos Preguntas
        ObservableList<Pregunta> listaPreguntas = FXCollections.observableArrayList();
 
        while (rsPreguntas.next()) {
            Pregunta pregunta = new Pregunta();
            pregunta.setId(rsPreguntas.getInt("id"));
            pregunta.setPregunta(rsPreguntas.getString("pregunta"));
            pregunta.setCategoria(rsPreguntas.getInt("categoria_id"));
            pregunta.setDificultad(rsPreguntas.getInt("dificultad_id"));
            
            // Añadir pregunta a la ObservableList
            listaPreguntas.add(pregunta);
        }
        // Retorna la lista de preguntas 
        return listaPreguntas;
    }
    
    //*************************************
    // Actualizar pregunta (por id)
    //*************************************
    public static void actualizarPregunta (String preguntaId, String pregunta) throws SQLException, ClassNotFoundException, Error {
        // Declarando el UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE preguntas\n" +
                        "      SET pregunta = '" + pregunta + "'\n" +
                        "    WHERE id = " + preguntaId + ";\n" +
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
    //  Eliminar una pregunta (por ID)
    //*************************************
    public static void eliminar (String preguntaId) throws SQLException, ClassNotFoundException, Error {
        // Declarando el DELETE statement
        String deleteStmt =
                "BEGIN\n" +
                        "   DELETE FROM preguntas\n" +
                        "         WHERE id ="+ preguntaId +";\n" +
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
    // Crear una pregunta
    //*************************************
    public static void insertar (String pregunta, String categoriaId, String dificultadId) throws SQLException, ClassNotFoundException, Error {
        // Declarando el INSERT statement
        String insertStmt =
                "BEGIN\n" +
                        "INSERT INTO preguntas\n" +
                        "(pregunta, categoria_id, dificultad_id)\n" +
                        "VALUES\n" +
                        "('" + pregunta + ", " + categoriaId + ", " + dificultadId + "' );\n" +
                        "END;";
 
        try {
            Conexion.ejecutarUpdate(insertStmt);
        } catch (SQLException e) {
            System.out.print("Ocurrio un error durante la operacion INSERT: " + e);
            throw e;
        }
    }
}

