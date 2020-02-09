package util;
import java.sql.*;
import com.sun.rowset.CachedRowSetImpl;

public class Conexion {

    protected static Connection conn = null;
    private static Conexion conecto;
    private final static String connStr = "jdbc:mysql://localhost/juego";

    public Conexion() {
    }
    
    public static Conexion getInstance() {
        if(conecto == null){
            conecto = new Conexion();
        }
        return conecto;
    }
    
    
    // Conexión a DB
    public static Connection Conectar() throws Error  {
        try{
            conn = DriverManager.getConnection(connStr,"exroot","nevera123"); 
        }
        catch(SQLException ex){
//            System.out.println("Connection Failed! Check output console" + e);
//            ex.printStackTrace();
//            throw ex;
            throw new Error(1);
        }
        return conn;
    }
    
    // Desconexión a DB
    public static void Desconectar() throws Error {
        try {
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException ex) {
           throw new Error(2);
        }
    }


    // Ejecutar consulta en DB
    public static ResultSet ejecutarQuery(String queryStmt) throws SQLException, ClassNotFoundException, Error {
        // Declarar statement, resultSet and CachedResultSet como null
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            // Conectar a DB
            Conectar();
            
            // Crear statement
            statement = conn.createStatement();
 
            // Ejecutar operación (query)
            resultSet = statement.executeQuery(queryStmt);
            
            // Implementación de CachedRowSet
            // A manera de prevenir el error "java.sql.SQLRecoverableException: Closed Connection: next"
            // Usaremos CachedRowSet
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Un problema ocurrió con la operación executeQuery." + e);
            throw e;
        } finally {
            if (resultSet != null) {
                // Cerrar resultSet
                resultSet.close();
            }
            if (statement != null) {
                // Cerrar statement
                statement.close();
            }
            // Cerrar conexión
            Desconectar();
        }
        // Retornar CachedRowSet
        return crs;
    }
    
    // Ejecutar actualización (Para operaciones con Update/Insert/Delete)
    public static void ejecutarUpdate(String sqlStmt) throws SQLException, ClassNotFoundException, Error {
        // Declarar statement como null
        Statement statement = null;
        try {
            //  Conectar a DB
            Conectar();
            // Crear statement
            statement = conn.createStatement();
            // Correr operación executeUpdate con el sql statement
            statement.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Un problema ocurrió con la operación executeUpdate." + e);
            throw e;
        } finally {
            if (statement != null) {
                // Cerrar statement
                statement.close();
            }
            // Cerrar conexión
            Desconectar();
        }
    }
}
