/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.SessionManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nasri
 */
public class HomeAdminController implements Initializable {
   
   @FXML
           private Button btn_codep;
   @FXML
           private Button btn_utilisateur;
  
   
   @Override
    public void initialize(URL url, ResourceBundle rb) {}
        
           @FXML
     private void btn_gestionadmin (ActionEvent event) {
    try {
    Parent root = FXMLLoader.load(getClass().getResource("GestionAdmin.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  @FXML
        private void btn_gestioncp (ActionEvent event) {
    try {
    Parent root = FXMLLoader.load(getClass().getResource("GestionCp.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    private void btnDeconnecterAction(ActionEvent event) {
        SessionManager.getInstance().setCurrentUser(null);
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("ConnexionUser.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

        }
    }
     
    }    
    

