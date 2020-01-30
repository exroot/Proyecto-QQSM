package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// https://www.swtestacademy.com/database-operations-javafx/

public class Conexion {

    protected Connection conn = null;
    private static Conexion conecto;
    private static String connStr = "jdbc:mysql://localhost/newdatabase";

    public Conexion() {
    }
    
    public static Conexion getInstance() {
        if(conecto == null){
            conecto = new Conexion();
        }
        return conecto;
    }    
    
    public Connection Conectar() throws Error  {
        try{
            conn = DriverManager.getConnection(connStr,"exroot",""); 
        }
        catch(SQLException ex){
//            System.out.println("Connection Failed! Check output console" + e);
//            ex.printStackTrace();
//            throw ex;
            throw new Error(1);
        }
        return conn;
    }
    
    public void Desconectar() throws Error {
        try {
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException ex) {
           throw new Error(2);
        }
    }    
}
