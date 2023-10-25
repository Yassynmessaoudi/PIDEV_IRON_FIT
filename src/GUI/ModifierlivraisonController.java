package GUI;

import entities.Livraison;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class ModifierlivraisonController implements Initializable {

    @FXML
    private TextField datel;
    @FXML
    private Button mod;
    @FXML
    private Button exit;
    @FXML
    private AnchorPane bord;
    @FXML
    private ComboBox<String> cat_cb;
    @FXML
    private DatePicker created_prod;

    private int ref_livraison;
    private ListView<Livraison> afficherlivraison;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          //GET CATREGORIES LISTE DEROULANTE FOR JOIN !
                        ObservableList<String>list = FXCollections.observableArrayList();
                        livraisonService sc = new livraisonService();
                        
                        
                      
                        ObservableList<Livraison>obList = FXCollections.observableArrayList();
                        obList =sc.afficherLivraison2();

        cat_cb.getItems().clear();
        
        for(Livraison nameCat : obList) {
            System.out.println("hii");
            list.add(nameCat.getRefCommande());
                        System.out.println("hii"+list);

                    cat_cb.setItems(list);

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
        try {
            livraisonService ss = new livraisonService();
            Livraison s = new Livraison();

            // Récupération de la date sélectionnée
            LocalDate selectedDate = created_prod.getValue();
            if (selectedDate != null) {
                Date date = Date.valueOf(selectedDate);
                s.setDate_livraison(date);
                // Formatage de la date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                datel.setText(dateFormat.format(date));
            } else {
                datel.setText("Date non disponible");
            }

            // Récupération de la référence de la commande depuis la ComboBox
            String refCommande = cat_cb.getValue();
            // Vous devrez peut-être implémenter une méthode pour obtenir l'ID de la commande correspondante

          //  s.setRefCommande(refCommande);
            ss.modifierLivraison(s);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Modification effectuée avec succès !");
            alert.setContentText("La livraison a été modifiée avec succès.");
            alert.showAndWait();

            // Rafraîchissement de la vue
            refreshListView();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérez les erreurs ici
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur est survenue lors de la modification de la livraison.");
            alert.showAndWait();
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        // Retour à la vue précédente
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_livraison.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    // Méthode pour rafraîchir la liste des livraisons
    private void refreshListView() {
          afficherlivraison.getItems().clear(); // Efface tous les éléments actuels
        livraisonService inter = new livraisonService();
        List<Livraison> list2 = inter.afficher();
        afficherlivraison.getItems().addAll(list2); // Ajoute les nouvelles données  }

    // Vous pouvez ajouter d'autres méthodes et attributs nécessaires ici en fonction de votre logique de programme.
}
}
