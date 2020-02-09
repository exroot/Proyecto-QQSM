package testinggame.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BotonesVentanaController implements Initializable {

    @FXML
    private AnchorPane panel;
    
    @FXML
    private Button Cerrar, Maximizar, Minimizar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Cerrar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Stage stage=(Stage)panel.getScene().getWindow();
                stage.close();
            }
            
        });
    }
}