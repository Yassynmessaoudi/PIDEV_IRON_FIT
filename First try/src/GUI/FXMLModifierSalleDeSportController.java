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

public class FXMLModifierSalleDeSportController implements Initializable {
    @FXML
    private TextField adresse;
    @FXML
    private Button btnModifierSS;
    @FXML
    private TextField capacite;
    @FXML
    private TextField nom;
    @FXML
    private TextField specialite;

    private SalleDeSport salleDeSport; // L'objet salle de sport à modifier

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnModifierSS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ServicesSalleDeSport ss = new ServicesSalleDeSport();
                int Capacite = Integer.parseInt(capacite.getText());

                // Mettez à jour les propriétés de la salle de sport
                salleDeSport.setNom(nom.getText());
                salleDeSport.setAdresse(adresse.getText());
                salleDeSport.setCapacite(Capacite);
                salleDeSport.setSpecialite(specialite.getText());

                try {
                    // Vérifiez d'abord si le nouveau nom est déjà utilisé par une autre salle de sport
                    if (!nomExisteDeja(nom.getText(), salleDeSport.getId())) {
                        // Appelez la méthode de service pour mettre à jour la salle de sport
                        ss.modifierSalleDeSport(salleDeSport);

                        // Redirigez l'utilisateur vers une interface pour afficher les salles de sport
                        Parent root = FXMLLoader.load(getClass().getResource("FXMLafficheSalleDeSport.fxml"));
                        nom.getScene().setRoot(root);
                    } else {
                        // Affichez un message d'erreur si le nom existe déjà
                        afficherAlerte("Nom déjà utilisé", "Le nom de la salle de sport existe .");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FXMLModifierSalleDeSportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Méthode pour définir la salle de sport à modifier
    public void setSalleDeSport(SalleDeSport salleDeSport) {
        this.salleDeSport = salleDeSport;
        // Pré-remplissez les champs avec les données de la salle de sport à modifier
        nom.setText(salleDeSport.getNom());
        adresse.setText(salleDeSport.getAdresse());
        capacite.setText(String.valueOf(salleDeSport.getCapacite()));
        specialite.setText(salleDeSport.getSpecialite());
    }

    // Méthode pour vérifier si le nom de salle de sport existe déjà (dans la base de données)
    private boolean nomExisteDeja(String nouveauNom, int idSalleAModifier) {
        ServicesSalleDeSport ss = new ServicesSalleDeSport();
        SalleDeSport salleExistante = ss.getOneSalleDeSport(new SalleDeSport(nouveauNom, "", 0, ""));

        if (salleExistante != null && salleExistante.getId() != idSalleAModifier) {
            return true; // Le nom existe déjà dans une autre salle de sport
        } else {
            return false;
        }
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
