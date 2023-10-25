/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commande;
import entities.Livraison;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.livraisonService;

/**
 * FXML Controller class
 *
 * @author gheri
 */
public class Afficher_livraisonController implements Initializable {

    @FXML
    private Button mod;
    @FXML
    private Button supprimer;
    @FXML
    private ListView<Livraison> afficherlivraison;

     static int ref_livraison;
   static String date_livraison;
        static int refCommande;
    @FXML
    private TextField fx_datel;
    @FXML
    private TextField fx_refc;
    @FXML
    private Button ajouter;
    @FXML
    private Button exit;
    @FXML
    private AnchorPane bord;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fx_refc.setText(Integer.toString(Afficher_commandeController.refCommande));
                fx_refc.setText(Integer.toString(Afficher_livraisonController.refCommande));

          if (data != null) {
            fx_refc.setText(Integer.toString(data.getRefCommande()));
        }
          
        ListView<Livraison> list1= afficherlivraison;
        livraisonService inter = new livraisonService();
        List<Livraison> list2 = inter.afficher();
        for (int i = 0; i < list2.size(); i++) {
            Livraison A = list2.get(i);
            list1.getItems().add(A);

        }  
        
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
        
           ListView<Livraison> list= afficherlivraison;
        livraisonService inter = new livraisonService();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        Livraison A = list.getSelectionModel().getSelectedItem();
        ref_livraison=A.getRef_livraison();
        date_livraison= A.getDate_livraison();
refCommande=A.getRefCommande();


        try {

            
            Parent page1
                    = FXMLLoader.load(getClass().getResource("modifierlivraison.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
                        refreshListView(); // Rafraîchit la ListView après la modif

        } catch (IOException ex) {
            Logger.getLogger(Location_commandeController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void supprimer_livraison(ActionEvent event) {
        
          ListView<Livraison> list1 = afficherlivraison;
        livraisonService inter = new livraisonService();
        int selectedIndex = list1.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Livraison A = list1.getSelectionModel().getSelectedItem();
            System.out.println(A.getRef_livraison());
            inter.supprimerLivraison(A.getRef_livraison());
            list1.getItems().remove(selectedIndex);
                        refreshListView(); // Rafraîchit la ListView après la suppression
    showAlert("Succès", "Livraison supprimée avec succès.");

        } else {
            showAlert("Erreur","Veuillez sélectionner une livraison à supprimer.");
        }

    }

    @FXML
    private void ajouter(ActionEvent event) {
        
         livraisonService inter = new livraisonService();
        Integer refCommande = Integer.parseInt(fx_refc.getText());
    String dateLivraison = fx_datel.getText();

    try {
        refCommande = Integer.parseInt(fx_refc.getText());
    } catch (NumberFormatException e) {
        afficherAlerte("Erreur", "Le champ 'Référence Commande' doit être un nombre valide.");
        return; // Arrête la méthode en cas d'erreur
    }

    if (dateLivraison.isEmpty()) {
        afficherAlerte("Erreur", "Veuillez entrer une date de livraison.");
    } else if (refCommande < 0) {
        afficherAlerte("Erreur", "La référence de commande doit être positive.");
    } else {
        Livraison livraison = new Livraison(dateLivraison, refCommande);
        livraisonService crud = new livraisonService();
        crud.ajouterLivraison(livraison);
        afficherAlerte("Succès", "Livraison affectée avec succès!");
                    refreshListView(); // Rafraîchit la ListView après la ajout

    }
}

private void afficherAlerte(String titre, String contenu) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.show();
}

 private void refreshListView() {
        afficherlivraison.getItems().clear(); // Efface tous les éléments actuels
        livraisonService inter = new livraisonService();
        List<Livraison> list2 = inter.afficher();
        afficherlivraison.getItems().addAll(list2); // Ajoute les nouvelles données
    }
 
  public void setRefCommande(int refCommande) {
        fx_refc.setText(Integer.toString(refCommande));
    }
  
  
  private Commande data;

    // ...

    public void setData(Commande data) {
        this.data = data;
    }

    @FXML
    private void exit(ActionEvent event) {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("location_commande.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
