package testinggame.Controladores;

import testinggame.Modelos.Usuario;
import testinggame.Modelos.UsuarioDAO;

import testinggame.utils.Context;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import testinggame.utils.Error;

public class LoginController implements Initializable {
    
    @FXML
    private AnchorPane PanelA;

    @FXML
    private JFXTextField tfUsuario;

    @FXML
    private JFXPasswordField tfContraseña;

   @FXML
   private Button bttnRegistro, bttnEntrar;
   
   @FXML
   private Label lbError;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        lbError.setVisible(false);
        
        Registro();
        Login();
    }

//    private void Entrar(){
//        bttnEntrar.setOnAction(new EventHandler<ActionEvent>(){
//            @Override
//            public void handle(ActionEvent event) {
//                    try {
//                        
//                        Parent root= FXMLLoader.load(getClass().getResource("/testinggame/Vistas/VentanaDeSeleccion.fxml"));
//                        Scene SCENE=new Scene(root);
//                        PanelA.getChildren().clear();
//                        PanelA.getChildren().add(root);
//                        
//                    } catch (IOException ex) {
//                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//            }
//        });
//        
//        bttnEntrar.setOnMouseEntered(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                new Pulse(bttnEntrar).play();
//            }
//        });
//        
//    }
    
    private void Registro(){
        
        bttnRegistro.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root= FXMLLoader.load(getClass().getResource("/testinggame/Vistas/Registro.fxml"));
                    Scene SCENE=new Scene(root);
                    PanelA.getChildren().clear();
                    PanelA.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        bttnRegistro.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                new Pulse(bttnRegistro).play();
            }
        });
    }
    
    public void Login() {
        bttnEntrar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String username = tfUsuario.getText();
                String contraseña = tfContraseña.getText();
                String usuario;
                try {
                    usuario = UsuarioDAO.validarUsuario(username, contraseña);
                    if (usuario == null) {
                        lbError.setText("Credenciales invalidas");
                        lbError.setVisible(true);
                        return;
                    }
                    lbError.setText("");
                    lbError.setVisible(false);
                    
                    Usuario usuarioValidado = UsuarioDAO.mostrar(usuario);
                    Context.setUsuario(usuarioValidado);
                    
                    Parent root = FXMLLoader.load(getClass().getResource("/testinggame/Vistas/VentanaDeSeleccion.fxml"));
                    Scene SCENE=new Scene(root);
                    PanelA.getChildren().clear();
                    PanelA.getChildren().add(root);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Error ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }
}
