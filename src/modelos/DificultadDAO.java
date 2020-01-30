package modelos;
import util.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Error;

public class DificultadDAO {
    //*******************************
    // Mostrar una dificultad (por id)
    //*******************************
    public static Dificultad mostrar (String dificultadId) throws SQLException, ClassNotFoundException, Error {
        // Declarar el SELECT statement
        String selectStmt = "SELECT * FROM dificultades WHERE id=" + dificultadId;
        
        try {
            // Obtener ResultSet del método ejecutarQuery
            ResultSet resultDificultad = Conexion.ejecutarQuery(selectStmt);
 
            // Enviar ResultSet a el metodo getDificultad (de ResultSet) y obtener el objeto dificultad
            Dificultad dificultad = getDificultad(resultDificultad);
 
            return dificultad;
            
        } catch (SQLException e) {
            System.out.println("Ocurrio un error durante la operacion SELECT * FROM dificultades WHERE: " + e);
            throw e;
        }
    }

    // Usa el ResultSet de la DB como parametro, establece los atributos del objeto dificultad y retorna dicho objeto
    private static Dificultad getDificultad(ResultSet resultDif) throws SQLException {
         Dificultad dificultad = null;
        if (resultDif.next()) {
            dificultad = new Dificultad();
            dificultad.setId(resultDif.getInt("id"));
            dificultad.setDificultad(resultDif.getString("dificultad"));
        }
        return dificultad;
    }
    
    //*******************************
    // Mostrar TODAS las dificultades
    //*******************************
    public static ObservableList<Dificultad> getDificultades() throws SQLException, ClassNotFoundException, Error {
        // Declarando el SELECT statement
        String selectStmt = "SELECT * FROM dificultades";
       
        try {
            // Obtener ResultSet del metodo ejecutarQuery
            ResultSet resultDificultades = Conexion.ejecutarQuery(selectStmt);
 
            // Enviar el ResultSet a el metodo getListaDificultades
            ObservableList<Dificultad> listaDificultades = getListaDificultades(resultDificultades);
 
            return listaDificultades;
            
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Dificultad> getListaDificultades(ResultSet rsDificultades) throws SQLException {
        // Declaración de una observable List con los objetos Dificultades
        ObservableList<Dificultad> listaDificultades = FXCollections.observableArrayList();
 
        while (rsDificultades.next()) {
            Dificultad dificultad = new Dificultad();
            dificultad.setId(rsDificultades.getInt("id"));
            dificultad.setDificultad(rsDificultades.getString("dificultad"));
            
            // Añadir dificultad a la ObservableList
            listaDificultades.add(dificultad);
        }
        // Retorna la lista de dificultades 
        return listaDificultades;
    }
    
    //*************************************
    // Actualizar dificultad (por id)
    //*************************************
    public static void actualizar (String dificultadId, String dificultad) throws SQLException, ClassNotFoundException, Error {
        // Declarando el UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE dificultades\n" +
                        "      SET dificultad = '" + dificultad + "'\n" +
                        "    WHERE id = " + dificultadId + ";\n" +
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
    //  Eliminar una dificultad
    //*************************************
    public static void eliminar (String dificultadId) throws SQLException, ClassNotFoundException, Error {
        // Declarando el DELETE statement
        String deleteStmt =
                "BEGIN\n" +
                        "   DELETE FROM dificultades\n" +
                        "         WHERE id ="+ dificultadId +";\n" +
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
    // Crear una dificultad
    //*************************************
    public static void insertar (String dificultad) throws SQLException, ClassNotFoundException, Error {
        // Declarando el INSERT statement
        String insertStmt =
                "BEGIN\n" +
                        "INSERT INTO dificultades\n" +
                        "(dificultad)\n" +
                        "VALUES\n" +
                        "('"+ dificultad +"');\n" +
                        "END;";
 
        try {
            Conexion.ejecutarUpdate(insertStmt);
        } catch (SQLException e) {
            System.out.print("Ocurrio un error durante la operacion INSERT: " + e);
            throw e;
        }
    }
}
