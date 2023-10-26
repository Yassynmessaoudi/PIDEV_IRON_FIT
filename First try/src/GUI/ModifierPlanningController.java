/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Planning;
import Services.servicePlanning;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifierPlanningController implements Initializable {
    public static Planning planningToModify;
    @FXML
    private TextField modP;
    @FXML
    private TextField coachid;
    @FXML
    private TextField prix;
    @FXML
    private RadioButton debutRD;
    @FXML
    private RadioButton avRB;
    @FXML
    private Button modifierp;

    // Create a new servicePlanning object
    private servicePlanning planningService = new servicePlanning();

    // Create a Planning object to hold the planning to be modified
   
    @FXML
    private Button annulerp;
    @FXML
    private TextField video;
   
    @FXML
    private TextArea description;

  @Override
public void initialize(URL url, ResourceBundle rb) {
    if (planningToModify != null) {
        // Convert numerical values to String
        String idCoachString = Integer.toString(planningToModify.getId_coach());
        String prixString = Float.toString(planningToModify.getPrix());

        // Fill the text fields with the planning data
        modP.setText(planningToModify.getProgramme());
        coachid.setText(idCoachString);
        prix.setText(prixString);
        video.setText(planningToModify.getVideoLink());  // Set the video link field
        description.setText(planningToModify.getDescription());  // Set the description field

        // Check the corresponding radio button according to the niveauProgramme
        if (planningToModify.getNiveauProgramme().equals("debutant")) {
            debutRD.setSelected(true);
        } else if (planningToModify.getNiveauProgramme().equals("avance")) {
            avRB.setSelected(true);
        }
    }
}


    @FXML
    private void handle_annulerp() {
       try {
           // Load the FXML file for the acceuilPlanning interface
           Parent root = FXMLLoader.load(getClass().getResource("/GUI/acceuilPlanning.fxml"));

           // Get the current stage
           Stage stage = (Stage) annulerp.getScene().getWindow();

           // Set the new scene on the current stage
           stage.setScene(new Scene(root));
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    @FXML
private void handle_modP(ActionEvent event) {
    // This method is triggered when the modP component is interacted with.
    // You can add your specific code here.
    System.out.println("modP action occurred");
}

@FXML
private void handle_coachid(ActionEvent event) {
    // This method is triggered when the coachid component is interacted with.
    // You can add your specific code here.
    System.out.println("coachid action occurred");
}

@FXML
private void handle_prix(ActionEvent event) {
    // This method is triggered when the prix component is interacted with.
    // You can add your specific code here.
    System.out.println("prix action occurred");
}

@FXML
private void handle_debutRD(ActionEvent event) {
    // This method is triggered when the debutRD component is interacted with.
    // You can add your specific code here.
    System.out.println("debutRD action occurred");
}

@FXML
private void handle_avRB(ActionEvent event) {
    // This method is triggered when the avRB component is interacted with.
    // You can add your specific code here.
    System.out.println("avRB action occurred");
}

@FXML
public void handle_modifierp(ActionEvent event) {
    if (planningToModify != null) {
        // Get the updated values from your fields here
        String programme = modP.getText();
        int idCoach = Integer.parseInt(coachid.getText());
        float prixValue = Float.parseFloat(prix.getText());
        String niveauProgramme = debutRD.isSelected() ? "debutant" : "avance";
        String videoLink = video.getText();  // Get the video link from the TextField
        String descriptionText = description.getText();  // Get the description from the TextArea

        // Update the planning object with these values
        planningToModify.setProgramme(programme);
        planningToModify.setId_coach(idCoach);
        planningToModify.setPrix(prixValue);
        planningToModify.setNiveauProgramme(niveauProgramme);
        planningToModify.setVideoLink(videoLink);  // Set the video link in the Planning object
        planningToModify.setDescription(descriptionText);  // Set the description in the Planning object

        // Update the planning in the database
        planningService.modifierPlanning(planningToModify);

       try {
           // Switch back to the acceuilPlanning interface
           Parent root = FXMLLoader.load(getClass().getResource("/GUI/acceuilPlanning.fxml"));
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.setScene(new Scene(root));
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}

    @FXML
    private void handle_videoField(ActionEvent event) {
    }


    @FXML
    private void handle_descriptionArea(MouseEvent event) {
    }






}


