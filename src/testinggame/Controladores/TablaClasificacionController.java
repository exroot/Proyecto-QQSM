package testinggame.Controladores;

import animatefx.animation.Pulse;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TablaClasificacionController implements Initializable {

    @FXML
    private AnchorPane PanelF;

    @FXML
    private Button bttnVolver;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Volver();
        
    }   
    
    private void Volver(){
        
        bttnVolver.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                try {
                        
                        Parent root= FXMLLoader.load(getClass().getResource("/testinggame/Vistas/VentanaDeSeleccion.fxml"));
                        Scene SCENE=new Scene(root);
                        PanelF.getChildren().clear();
                        PanelF.getChildren().add(root);
                        
                    }catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            }
            
        });
        
        bttnVolver.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
                new Pulse(bttnVolver).play();
                
            }
            
        });
        
    }
    
}
