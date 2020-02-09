package testinggame.Controladores;

import testinggame.Modelos.Usuario;
import testinggame.Modelos.UsuarioDAO;
import testinggame.utils.Respuesta;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RegistroController implements Initializable {
        
    @FXML
    private AnchorPane PanelB;

    @FXML
    private Button bttnRegistrar, bttnVolver;

    @FXML
    private Label lbError;

    @FXML
    private JFXTextField tfNombre_Apellido, tfUsuario;

    @FXML
    private JFXPasswordField pfContraseña, pfConfirmar_Contraseña;

    @FXML
    private JFXDatePicker dpFecha_Nacimiento;

    @FXML
    private JFXComboBox<String> cbxSexo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxSexo.getItems().addAll(
            "F",
            "M"
        );
        cbxSexo.setPromptText("Seleccionar");
        lbError.setVisible(false);
        Volver();
        Registrar();
        
    }    
    
    private void Volver(){
        bttnVolver.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               try {
                    Parent root= FXMLLoader.load(getClass().getResource("/testinggame/Vistas/Login.fxml"));
                    Scene SCENE=new Scene(root);
                    PanelB.getChildren().clear();
                    PanelB.getChildren().add(root);
                } catch (IOException ex) {
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
    public void Registrar() {
        bttnRegistrar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String nombreApellido = tfNombre_Apellido.getText();
                String username = tfUsuario.getText();
                String contraseña = pfContraseña.getText();
                String sexo = cbxSexo.getValue();
                String puntaje = "0";
                try {
                    Respuesta r = UsuarioDAO.insertar(nombreApellido, username, contraseña, sexo, puntaje);
                    if (r.getStatus() == 2) {
                        lbError.setVisible(true);
                        lbError.setText(r.getRespuesta());
                        return;
                    }
                    Parent root= FXMLLoader.load(getClass().getResource("/testinggame/Vistas/Login.fxml"));
                    Scene SCENE=new Scene(root);
                    PanelB.getChildren().clear();
                    PanelB.getChildren().add(root);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Error ex) {
                    Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (testinggame.utils.Error ex) {
                    Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    
}
