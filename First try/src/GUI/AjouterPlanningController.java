/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import models.Planning;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Coach;
import Services.servicePlanning;
import util.MyConnection;

public class AjouterPlanningController implements Initializable {
    @FXML
    private TextField programmeField;
    @FXML
    private TextField idcoachField;
    @FXML
    private TextField prixFied;
    @FXML
    private RadioButton debutantBtm;
    @FXML
    private RadioButton avanceBtm;
    @FXML
    private Button confirmerBtm;
    @FXML
    private Button retourBtm;
    @FXML
    private ImageView imageView;

    private final servicePlanning planningService = new servicePlanning();
    @FXML
    private TextField video;
private Connection cnx;
    @FXML
    private Button browser;
    @FXML
    private Label msg;
    @FXML
    private TextField adressImage;
    @FXML
    private TextArea description;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      this.cnx = MyConnection.getInstance().getCnx();
    }    

   
@FXML
private void handle_avanceBtm(ActionEvent event) {
    // Perform some action when the avanceBtm is clicked
    if (avanceBtm.isSelected()) {
        debutantBtm.setSelected(false);
        System.out.println("Advanced button was selected.");
    }
}

@FXML
private void handle_annulerBtm(ActionEvent event) {
    try {
        // Load the FXML file for the acceuilPlanning form
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/acceuilPlanning.fxml"));
        
        // Create a new scene with the loaded FXML file
        Scene scene = new Scene(root);
        
        // Get the current stage and set the scene for it, then show it
        Stage stage = (Stage) retourBtm.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
}


    @FXML
    private void handle_video(ActionEvent event) {
    }


   @FXML
private void handle_programmeField(ActionEvent event) {
    // Perform some action when the programmeField is interacted with
    System.out.println("Programme field was interacted with.");
}


    @FXML
    private void handle_idcoachField(ActionEvent event) {
    }

    @FXML
    private void handle_prixFied(ActionEvent event) {
    }

    @FXML
    private void handle_debutantBtm(ActionEvent event) {
    }

   

    
@FXML
    private void handle_msg(MouseEvent event) {
    }

    @FXML
private void handle_browserBtm(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(null);
    
    if (selectedFile != null) {
        // Display the file path in the TextField
        adressImage.setText(selectedFile.getAbsolutePath());
    }
}


@FXML
private void handle_image(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(null);
    
    if (selectedFile != null) {
        // Load the image and set it to the ImageView
        Image image = new Image(selectedFile.toURI().toString());
        imageView.setImage(image);

        // Display the file path in the TextField
        adressImage.setText(selectedFile.getAbsolutePath());
    }
}

@FXML
private void handle_confirmerBtm(ActionEvent event) {
    String programme = programmeField.getText();
    String idCoachText = idcoachField.getText();
    String prixText = prixFied.getText();
    String videoLink = video.getText();
    String descriptionText = description.getText();  // Get the description from the TextArea

    // Check if any field is empty or incorrectly filled
    if (programme.isEmpty() || idCoachText.isEmpty() || prixText.isEmpty() || videoLink.isEmpty() || descriptionText.isEmpty() ||
        !idCoachText.matches("\\d+") || !prixText.matches("\\d+(\\.\\d+)?")) {
        msg.setText("Vérifier vos données");
        return;
    }

    int idCoach = Integer.parseInt(idCoachText);
    float prix = Float.parseFloat(prixText);
    String niveauProgramme = debutantBtm.isSelected() ? "Debutant" : "Avance";
    byte[] imageBytes = null; 

    try {
        // Read the image file into a byte array
        File imageFile = new File(adressImage.getText());
        imageBytes = Files.readAllBytes(imageFile.toPath());
    } catch (IOException ex) {
        Logger.getLogger(AjouterPlanningController.class.getName()).log(Level.SEVERE, null, ex);
    }

    Planning newPlanning = new Planning(niveauProgramme, programme, idCoach, prix, imageBytes, videoLink, descriptionText);  // Include the description when creating the Planning object
    planningService.AjouterPlanning(newPlanning);
    
    // Clear the fields after adding the planning
    programmeField.clear();
    idcoachField.clear();
    prixFied.clear();
    debutantBtm.setSelected(false);
    avanceBtm.setSelected(false);
    video.clear();
    description.clear();  // Clear the description field
    
    adressImage.clear();

    // Clear the ImageView after adding the planning
    imageView.setImage(null);
    
    msg.setText("");

}



@FXML
private void handle_adressImage(ActionEvent event) {
    // This method can be left empty if you don't need to handle any actions when the TextField is interacted with.
}

    @FXML
    private void handle_descriptionArea(MouseEvent event) {
    }





}