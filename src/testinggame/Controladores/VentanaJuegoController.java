package testinggame.Controladores;
import testinggame.utils.Context;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.Pulse;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.util.Duration;
import testinggame.Modelos.Pregunta;
import testinggame.Modelos.PreguntaDAO;
import testinggame.utils.Error;

public class VentanaJuegoController implements Initializable {
    
    @FXML
    private Button cincuentaCincuenta;
    
    @FXML
    private Label PreguntaLabel, queryOptionA, queryOptionB, queryOptionC, queryOptionD;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Integer ronda = Context.getRonda();
        Start();
        Seleccionar();
    }
  
    public void Seleccionar() {
        queryOptionA.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                queryOptionA.setText("Seleccionado.");
            }
        });
        queryOptionB.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                queryOptionB.setText("Seleccionado.");
            }
        });
        queryOptionC.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                queryOptionC.setText("Seleccionado.");
            }
        });
        queryOptionD.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                queryOptionD.setText("Seleccionado.");
            }
        });
    }
    
    public void Start() {
        String[] categorias = { "2", "3", "4" };
        Context.setCategorias(categorias);
        Pregunta p = null;
        try {
            p = PreguntaDAO.mostrarAleatoria(Context.getCategorias(), "3");
        } catch (SQLException ex) {
            Logger.getLogger(VentanaJuegoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VentanaJuegoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Error ex) {
            Logger.getLogger(VentanaJuegoController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        PreguntaLabel.setText(p.getPregunta());
        cincuentaCincuenta.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                new Pulse(cincuentaCincuenta).play();
            }
        });
    }
    
}
