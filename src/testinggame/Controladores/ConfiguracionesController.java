package testinggame.Controladores;

import testinggame.Modelos.Usuario;
import testinggame.Modelos.UsuarioDAO;

import testinggame.utils.Context;
import testinggame.utils.Error;
import testinggame.utils.Respuesta;

import animatefx.animation.Pulse;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ConfiguracionesController implements Initializable {

    @FXML
    private TextField tfUsuario;
    
    @FXML
    private TextField tfNombre_Apellido;
    
    @FXML
    private ComboBox<String> cbxSexo;
    
    @FXML
    private AnchorPane PanelE;
    
    @FXML
    private Button bttnVolver;
            
    @FXML
    private Button bttnActualizar;
    
    @FXML
    private Label lbError;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbError.setVisible(false);
        cbxSexo.getItems().addAll(
            "F",
            "M"
        );
        Usuario usuario = Context.getUsuario();
        tfUsuario.setText(usuario.getUsername());
        tfNombre_Apellido.setText(usuario.getNombreApellido());
        cbxSexo.setValue(usuario.getSexo());
        Volver();
        Actualizar();
    }    
    
    private void Volver(){
        bttnVolver.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                        Parent root= FXMLLoader.load(getClass().getResource("/testinggame/Vistas/VentanaDeSeleccion.fxml"));
                        Scene SCENE=new Scene(root);
                        PanelE.getChildren().clear();
                        PanelE.getChildren().add(root);
                        
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
    
    public void Actualizar() {
        bttnActualizar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Usuario usuario = Context.getUsuario();
                String nuevoUsername =  tfUsuario.getText();
                String nuevoNombreApellido = tfNombre_Apellido.getText();
                String nuevoSexo = cbxSexo.getValue();
                
                try {
                    Respuesta r = UsuarioDAO.actualizar(usuario.getId().toString(), nuevoUsername, nuevoNombreApellido, nuevoSexo);
                    if (r.getStatus() == 2) {
                        lbError.setText("El nombre de usuario ya existe.");
                        lbError.setVisible(true);
                        return;
                    }
                    usuario = UsuarioDAO.mostrar(nuevoUsername);
                    Context.setUsuario(usuario);
                    Parent root= FXMLLoader.load(getClass().getResource("/testinggame/Vistas/VentanaDeSeleccion.fxml"));
                    Scene SCENE=new Scene(root);
                    PanelE.getChildren().clear();
                    PanelE.getChildren().add(root);
                } catch (SQLException ex) {
                    Logger.getLogger(ConfiguracionesController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ConfiguracionesController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Error ex) {
                    Logger.getLogger(ConfiguracionesController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ConfiguracionesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
    }
}
