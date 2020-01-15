package proyecto.qqsm.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
 protected Connection linea = null;
    private static Conexion conecto;
    private static String dbname = "datos";
    private static String dbURL = String.format("jdbc:mysql://localhost/%s", dbname);
    private static String dbUsername = "root";
    private static String dbPassword = "123456";
    
    public Conexion() {
    }
    
    public static Conexion getInstance() {
        if(conecto == null){
            conecto = new Conexion();
        }
        return conecto;
    }    
    
    public Connection Conectar()  {
        try{
            linea = DriverManager.getConnection(dbURL, dbUsername, dbPassword); 
        }
        catch(SQLException ex){
       //     throw new Error(1);
        }
        return linea;
    }
    
    public void Desconectar() {
        if(linea != null){
            try {
                if(!linea.isClosed()){
                    linea.close();
                }
            } catch (SQLException ex) {
       //        throw new Error(2);
            }
        }
    }    
}
