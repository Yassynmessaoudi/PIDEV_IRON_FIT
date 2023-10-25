/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commande;
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
import services.commandeService;

/**
 * FXML Controller class
 *
 * @author gheri
 */
public class Afficher_commandeController implements Initializable {

    @FXML
    private ListView<Commande> affichercommande;
    @FXML
    private Button supprimer;
    @FXML
    private Button mod;
    
    static int refCommande;
    static String delaisLivraison;
     static double FraisdePort;
    static double montant; 
    @FXML
    private Button ajouter;
    @FXML
    private TextField fx_delias;
    @FXML
    private TextField fx_frais;
    @FXML
    private TextField fx_montant;
  //  @FXML
  //  private Button affecterlivraison;
    @FXML
    private Button affecterlivraison;
    @FXML
    private AnchorPane bord;
    @FXML
    private Button exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
ListView<Commande> list1= affichercommande;
        commandeService inter = new commandeService();
        List<Commande> list2 = inter.afficherCommande();
        for (int i = 0; i < list2.size(); i++) {
            Commande A = list2.get(i);
            list1.getItems().add(A);

        }     }    

    @FXML
    private void supprimer_commande(ActionEvent event) {
        
        ListView<Commande> list1= affichercommande;
        commandeService inter = new commandeService();
        int selectedIndex = list1.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Commande A = list1.getSelectionModel().getSelectedItem();
            System.out.println(A.getRefCommande());
            inter.supprimercommande(A.getRefCommande());
            list1.getItems().remove(selectedIndex);
                        refreshListView(); // Rafraîchit la ListView après la suppression
    showAlertS("Succès", "Commande supprimée avec succès.");

        } else {
            System.out.println("Veuillez sélectionner une commande à supprimer.");
        }
    }

    @FXML
    private void mod(ActionEvent event) {
        
        
          
            ListView<Commande> list = affichercommande;
        commandeService inter = new commandeService();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        Commande A = list.getSelectionModel().getSelectedItem();
        

        refCommande= A.getRefCommande();
        delaisLivraison= A.getDelaisLivraison();
FraisdePort=A.getFraisdePort();
montant=A.getMontant();
        //Prix_jour= Integer.toString(v.getPrix_jours()) ;

        try {

            
            Parent page1
                    = FXMLLoader.load(getClass().getResource("modifiercommande.fxml"));
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
private void ajouter(ActionEvent event) {
    String delaisLivraison = fx_delias.getText();
    double fraisDePort;
    double montant;

    try {
        fraisDePort = Double.parseDouble(fx_frais.getText());
        montant = Double.parseDouble(fx_montant.getText());
    } catch (NumberFormatException e) {
        afficherAlerte("Erreur de saisie", "Les champs Frais de Port et Montant doivent être des nombres valides.");
        return;
    }

    if (fraisDePort < 0) {
        afficherAlerte("Erreur de saisie", "Le Frais de Port ne peut pas être négatif.");
    } else if (delaisLivraison.isEmpty()) {
        afficherAlerte("Erreur de saisie", "Veuillez entrer un délai de livraison.");
    } else if (montant < 0) {
        afficherAlerte("Erreur de saisie", "Le montant ne peut pas être négatif.");
    } else {
        Commande commande = new Commande(delaisLivraison, fraisDePort, montant);
        commandeService crud = new commandeService();
        crud.ajouterCommande(commande);
        showAlertS("Succès", "Commande insérée avec succès!");
        refreshListView(); // Rafraîchit la ListView après l'ajout
    }
}

private void afficherAlerte(String titre, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}




private void showAlertS(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.show();
}
  private void refreshListView() {
        affichercommande.getItems().clear(); // Efface tous les éléments actuels
        commandeService inter = new commandeService();
        List<Commande> list2 = inter.afficherCommande();
        affichercommande.getItems().addAll(list2); // Ajoute les nouvelles données
    }


  @FXML
  private void affecterlivraison() {
    ListView<Commande> list = affichercommande;
    commandeService inter = new commandeService();
    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    
    // Vérifiez si un élément est sélectionné dans la ListView
    if (selectedIndex >= 0) {
        Commande A = list.getSelectionModel().getSelectedItem();
        int refCommande = A.getRefCommande();

        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("Afficher_livraison.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) list.getScene().getWindow();  // Utilisez la ListView pour obtenir la fenêtre actuelle
            stage.setScene(scene);
            stage.show();

            // Affichez un message de confirmation
          //  showAlert("Succès", "Livraison affectée avec succès à la commande.");
        } catch (IOException ex) {
            Logger.getLogger(Location_commandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        // Gérez le cas où aucune commande n'a été sélectionnée
        afficherAlerte("Erreur", "Veuillez sélectionner une commande avant d'affecter une livraison.");
    }
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
    


    



   
    
    