package GUI;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import models.SalleDeSport;
import services.ServicesSalleDeSport;

public class FXMLSupprimerSalleDeSportController implements Initializable {
    @FXML
    private TextField idSalleDeSport; // Champ pour saisir l'ID de la salle de sport à supprimer
    @FXML
    private Button btnSupprimerSS;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSupprimerSS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ServicesSalleDeSport ss = new ServicesSalleDeSport();

                try {
                    int id = Integer.parseInt(idSalleDeSport.getText());

                    // Vérifiez d'abord si l'ID existe dans la base de données
                    if (idSalleDeSportExiste(id)) {
                        // Appelez la méthode de service pour supprimer la salle de sport
                        ss.supprimerSalleDeSport(id);

                        // Redirigez l'utilisateur vers une interface pour afficher les salles de sport
                        Parent root = FXMLLoader.load(getClass().getResource("FXMLafficheSalleDeSport.fxml"));
                        idSalleDeSport.getScene().setRoot(root);
                    } else {
                        // Affichez un message d'erreur si l'ID n'existe pas
                        afficherAlerte("ID non trouvé", "L'ID de la salle de sport n'existe pas dans la base de données.");
                    }
                } catch (NumberFormatException ex) {
                    // Gérez une erreur si l'ID n'est pas un nombre valide
                    afficherAlerte("Erreur de saisie", "Veuillez saisir un ID de salle de sport valide.");
                } catch (IOException ex) {
                    Logger.getLogger(FXMLSupprimerSalleDeSportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Méthode pour vérifier si l'ID de salle de sport existe dans la base de données
    private boolean idSalleDeSportExiste(int id) {
        ServicesSalleDeSport ss = new ServicesSalleDeSport();
        SalleDeSport salleExistante = ss.getOneSalleDeSportById(id);

        return (salleExistante != null);
    }

    // Méthode pour afficher une alerte
    private void afficherAlerte(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}
