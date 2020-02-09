package testinggame.Controladores;

import testinggame.utils.Context;

import animatefx.animation.Bounce;
import animatefx.animation.BounceOut;
import animatefx.animation.FadeOut;
import animatefx.animation.JackInTheBox;
import animatefx.animation.Pulse;
import animatefx.animation.Shake;
import animatefx.animation.Tada;
import animatefx.animation.ZoomOut;
import animatefx.animation.ZoomOutDown;
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

public class VentanaDeSeleccionController implements Initializable {

    @FXML
    private AnchorPane PanelC, Alerta;

    @FXML
    private Button bttnIniciarJuego, bttnTablaPuntaje, bttnConfiguraciones, bttnSalir;
    
    @FXML
    private Button alertAceptar, alertCancelar; 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alerta.setVisible(false);
        
        IniciarJuego();
        TablaPuntaje();
        Configuraciones();
        Salir();
        
    }
    
    private void IniciarJuego(){
        
        bttnIniciarJuego.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                try {
                    
                    Parent root= FXMLLoader.load(getClass().getResource("/testinggame/Vistas/VentanaJuego.fxml"));
                    Scene SCENE=new Scene(root);
                    PanelC.getChildren().clear();
                    PanelC.getChildren().add(root);
                    
                } catch (IOException ex) {
                    System.out.println("culo");
                }
                
            }
        });
        
        bttnIniciarJuego.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
                new Pulse(bttnIniciarJuego).play();
                
            }
            
        });
        
    }
    
    private void TablaPuntaje(){
        
        bttnTablaPuntaje.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                try {
                    
                    Parent root= FXMLLoader.load(getClass().getResource("/testinggame/Vistas/TablaClasificacion.fxml"));
                    Scene SCENE=new Scene(root);
                    PanelC.getChildren().clear();
                    PanelC.getChildren().add(root);
                    
                } catch (IOException ex) {
                    System.out.println("culo");
                }
                
            }
        });
        
        bttnTablaPuntaje.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
                new Pulse(bttnTablaPuntaje).play();
                
            }
            
        });
        
    }
    
    private void Configuraciones(){
        
        bttnConfiguraciones.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                try {
                    
                    Parent root= FXMLLoader.load(getClass().getResource("/testinggame/Vistas/Configuraciones.fxml"));
                    Scene SCENE=new Scene(root);
                    PanelC.getChildren().clear();
                    PanelC.getChildren().add(root);
                    
                } catch (IOException ex) {
                    System.out.println("culo");
                }
                
            }
        });
        
        bttnConfiguraciones.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
                new Pulse(bttnConfiguraciones).play();
                
            }
            
        });
        
    }
    
    private void Salir(){
        
        bttnSalir.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                Alerta.setVisible(true);
                new JackInTheBox(Alerta).play();
                
                alertAceptar.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        try {

                            Parent root= FXMLLoader.load(getClass().getResource("/testinggame/Vistas/Login.fxml"));
                            Scene SCENE=new Scene(root);
                            PanelC.getChildren().clear();
                            PanelC.getChildren().add(root);
                            Context.clear();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                });

                alertCancelar.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                        public void handle(ActionEvent event) {

                            Alerta.setVisible(false);

                        }

                });
                
            }
        });
        
        bttnSalir.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
                new Pulse(bttnSalir).play();
                
            }
            
        });
        
    }
    
}


