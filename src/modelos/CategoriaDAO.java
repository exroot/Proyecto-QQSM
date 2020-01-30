package modelos;
import util.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Error;

public class CategoriaDAO {
    //*******************************
    // Mostrar una categoria (por id)
    //*******************************
    public static Categoria mostrar (String categoriaId) throws SQLException, ClassNotFoundException, Error {
        // Declarar el SELECT statement
        String selectStmt = "SELECT * FROM categorias WHERE id=" + categoriaId;
        
        try {
            // Obtener ResultSet del método ejecutarQuery
            ResultSet resultCategoria = Conexion.ejecutarQuery(selectStmt);
 
            // Enviar ResultSet a el metodo getCategoria (de ResultSet) y obtener el objeto categoría
            Categoria categoria = getCategoria(resultCategoria);
 
            return categoria;
            
        } catch (SQLException e) {
            System.out.println("Buscando la categoria con la id " + categoriaId + " ocurrió el siguiente error: " + e);
            throw e;
        }
    }

    // Usa el ResultSet de la DB como parametro, establece los atributos del objeto categoria y retorna dicho objeto
    private static Categoria getCategoria(ResultSet resultCat) throws SQLException {
         Categoria cat = null;
        if (resultCat.next()) {
            cat = new Categoria();
            cat.setId(resultCat.getInt("id"));
            cat.setCategoria(resultCat.getString("categoria"));
        }
        return cat;
    }
    
    //*******************************
    // Mostrar TODAS las categorias
    //*******************************
    public static ObservableList<Categoria> getCategorias() throws SQLException, ClassNotFoundException, Error {
        // Declarando el SELECT statement
        String selectStmt = "SELECT * FROM categorias";
       
        try {
            // Obtener ResultSet del metodo ejecutarQuery
            ResultSet resultCategorias = Conexion.ejecutarQuery(selectStmt);
 
            // Enviar el ResultSet a el metodo getListaCategorias
            ObservableList<Categoria> listaCategorias = getListaCategorias(resultCategorias);
 
            return listaCategorias;
            
        } catch (SQLException e) {
            System.out.println("Ocurrio un error durante la operacion SELECT *: " + e);
            throw e;
        }
    }

    private static ObservableList<Categoria> getListaCategorias(ResultSet rsCats) throws SQLException {
        // Declaración de una observable List con los objetos Categoria
        ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList();
 
        while (rsCats.next()) {
            Categoria cat = new Categoria();
            cat.setId(rsCats.getInt("id"));
            cat.setCategoria(rsCats.getString("categoria"));
            
            // Añadir categoria a la ObservableList
            listaCategorias.add(cat);
        }
        // Retorna la lista de categorias 
        return listaCategorias;
    }
    
    //*************************************
    // Actualizar categoria (por id)
    //*************************************
    public static void actualizar (String categoriaId, String categoria) throws SQLException, ClassNotFoundException, Error {
        // Declarando el UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE categorias\n" +
                        "      SET categoria = '" + categoria + "'\n" +
                        "    WHERE id = " + categoriaId + ";\n" +
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
    //  Eliminar una categoria
    //*************************************
    public static void eliminar (String categoriaId) throws SQLException, ClassNotFoundException, Error {
        // Declarando el DELETE statement
        String deleteStmt =
                "BEGIN\n" +
                        "   DELETE FROM categorias\n" +
                        "         WHERE id ="+ categoriaId +";\n" +
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
    // Crear una categoria
    //*************************************
    public static void insertar (String categoria) throws SQLException, ClassNotFoundException, Error {
        // Declarando el INSERT statement
        String insertStmt =
                "BEGIN\n" +
                        "INSERT INTO categorias\n" +
                        "(categoria)\n" +
                        "VALUES\n" +
                        "('"+ categoria +"');\n" +
                        "END;";
 
        try {
            Conexion.ejecutarUpdate(insertStmt);
        } catch (SQLException e) {
            System.out.print("Ocurrio un error durante la operacion INSERT: " + e);
            throw e;
        }
    }
}
