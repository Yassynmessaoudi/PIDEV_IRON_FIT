/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Livraison;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.livraisonService;

/**
 * FXML Controller class
 *
 * @author gheri
 */
public class ModifierlivraisonController implements Initializable {

    @FXML
    private TextField datel;
    @FXML
    private TextField refc;
    @FXML
    private Button mod;
    @FXML
    private Button exit;
    @FXML
    private AnchorPane bord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         datel.setText(Afficher_livraisonController.date_livraison);
        refc.setText(Integer.toString(Afficher_livraisonController.refCommande));
    }    
private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.show();
}
    @FXML
    private void mod(ActionEvent event) {
            livraisonService inter = new livraisonService();
        String date_livraison = datel.getText();        
        Integer refCommande = Integer.parseInt(refc.getText());
        Livraison A = new Livraison(Afficher_livraisonController.ref_livraison,date_livraison,refCommande);
        inter.modifierLivraison(A);
    try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("afficher_livraison.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
                                    showAlert("Succès", "Livraison modifiée avec succès.");

        } catch (IOException ex) {
            Logger.getLogger(Location_commandeController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void exit(ActionEvent event) {
        
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_livraison.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
