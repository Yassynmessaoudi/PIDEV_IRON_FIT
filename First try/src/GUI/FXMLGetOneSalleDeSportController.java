package GUI;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import models.SalleDeSport;
import services.ServicesSalleDeSport;

public class FXMLGetOneSalleDeSportController implements Initializable {
    @FXML
    private TextField idSalleDeSport; 
    @FXML
    private Button btnGetOneSS;
    @FXML
    private Text resultText; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnGetOneSS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ServicesSalleDeSport ss = new ServicesSalleDeSport();

                try {
                    int id = Integer.parseInt(idSalleDeSport.getText());

                    // Vérifiez d'abord si l'ID existe dans la base de données
                    SalleDeSport salleExistante = ss.getOneSalleDeSportById(id);
                    if (salleExistante != null) {
                        // Affichez les détails de la salle de sport
                        resultText.setText("Nom: " + salleExistante.getNom() + "\n"
                            + "Adresse: " + salleExistante.getAdresse() + "\n"
                            + "Capacité: " + salleExistante.getCapacite() + "\n"
                            + "Spécialité: " + salleExistante.getSpecialite());
                    } else {
                        // Affichez un message d'erreur si l'ID n'existe pas
                        afficherAlerte("ID non trouvé", "L'ID de la salle de sport n'existe pas dans la base de données.");
                    }
                } catch (NumberFormatException ex) {
                    // Gérez une erreur si l'ID n'est pas un nombre valide
                    afficherAlerte("Erreur de saisie", "Veuillez saisir un ID de salle de sport valide.");
                }
            }
        });
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
