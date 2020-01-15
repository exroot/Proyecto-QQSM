package proyecto.qqsm;

import java.net.URL;

import proyecto.qqsm.java.Conexion;
import proyecto.qqsm.utils.Error;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    Conexion p = new Conexion();
    
    @FXML
    private Label label;
    
    @FXML
    private Button actualizarBtn;
    
    @FXML
    private TextField Entrada;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection w = p.Conectar();

        actualizarBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Connection w = p.Conectar();
                PreparedStatement g;
                String n = "UPDATE QUERY";
                try {
                    g = w.prepareStatement(n);
                    g.setInt(1,Integer.parseInt(Entrada.getText()));
                    g.executeUpdate();
                    g.close();
                    p.Desconectar();
                    Error.Mensaje("ACTUALIZACIÓN OK..!");
                } catch(SQLException err) {
                    Error.Mensaje("ERROR EN LA ACTUALIZACIÓN");
                }
                
            }
        });
    }
    
}
