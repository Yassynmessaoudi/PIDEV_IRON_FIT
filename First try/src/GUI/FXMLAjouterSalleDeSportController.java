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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.SalleDeSport;
import services.ServicesSalleDeSport;



public class FXMLAjouterSalleDeSportController implements Initializable{
    @FXML
    private TextField adresse;
    @FXML
    private Button btnAjouterSS;
    @FXML
    private TextField capacite;
    @FXML
    private TextField nom;
    @FXML
    private TextField specialite;
       
        @Override
        public void initialize(URL url, ResourceBundle rb) {
        btnAjouterSS.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               ServicesSalleDeSport ss = new ServicesSalleDeSport();
               int Capacite = Integer.parseInt(capacite.getText());
            SalleDeSport salleDeSport = new SalleDeSport(nom.getText(), adresse.getText(), Capacite, specialite.getText());
            try {
            ss.ajouterSalleDeSport(salleDeSport);
            Parent root= FXMLLoader.load(getClass().getResource("FXMLafficheSalleDeSport.fxml"));
            nom.getScene().setRoot(root);
             } catch (IOException ex) {
                   Logger.getLogger(FXMLAjouterSalleDeSportController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }

       });
        }
}
