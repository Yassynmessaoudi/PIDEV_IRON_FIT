/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commande;
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
import services.commandeService;

/**
 * FXML Controller class
 *
 * @author gheri
 */
public class ModifiercommandeController implements Initializable {

    @FXML
    private Button modifier_commande;
    @FXML
    private TextField fx_delias;
    @FXML
    private TextField fx_frais;
    @FXML
    private TextField fx_montant;
    @FXML
    private Button exit;
    @FXML
    private AnchorPane bord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

 fx_delias.setText(Afficher_commandeController.delaisLivraison);
        fx_frais.setText(Double.toString(Afficher_commandeController.FraisdePort));
        fx_montant.setText(Double.toString(Afficher_commandeController.montant));
    }
private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.show();
}
    @FXML
    private void modifier_commande(ActionEvent event) {
        
           commandeService inter = new commandeService();
        String delaisLivraison = fx_delias.getText();        
        Double FraisdePort = Double.parseDouble(fx_frais.getText());
        Double montant = Double.parseDouble(fx_montant.getText());
        Commande A = new Commande(Afficher_commandeController.refCommande,delaisLivraison, FraisdePort,montant);
        inter.modifier(A);

    try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("afficher_commande.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
                        showAlert("Succès", "Commande modifiée avec succès.");

        } catch (IOException ex) {
            Logger.getLogger(Location_commandeController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void exit(ActionEvent event) {
        
             FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher_commande.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    
}
