package testinggame.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class Error extends Exception {
    static String titulo = "Sistema de Mensaje";
    
    public Error(String mensaje) {
        super(mensaje);
    }
    
    public Error(Integer codigo) {
        super(codigo.toString());
    }
    
    public static void Mensaje(String cadena) {
        Alert x = new Alert(Alert.AlertType.ERROR);
        x.setTitle(titulo);
        x.setContentText(cadena);
        x.setGraphic(null);
        x.setHeaderText(null);
        ButtonType _Ok = new ButtonType("OK",ButtonData.OK_DONE);  
        x.getButtonTypes().setAll(_Ok);
        x.showAndWait();        
    }    
    
}