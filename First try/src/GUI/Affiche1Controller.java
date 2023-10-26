/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import models.Planning;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Affiche1Controller implements Initializable {

   
    @FXML
    private Label niveau;
    @FXML
    private Label programme;
    @FXML
    private ImageView image;
    @FXML
    private TextFlow description;
    @FXML
    private Hyperlink link;
    @FXML
    private WebView webview;
    @FXML
    private Label prix;
    @FXML
    private Button back;
    @FXML
    private Button contacter;
    @FXML
    private ImageView background;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setPlanning(Planning p) {
        niveau.setText(p.getNiveauProgramme());
        programme.setText(p.getProgramme());
        Image img = new Image(new ByteArrayInputStream(p.getImage()));
        image.setImage(img);
       Text text = new Text();
text.setText(p.getDescription());
description.getChildren().add(text);


        link.setText(p.getVideoLink());
        prix.setText(String.valueOf(p.getPrix()));
        webview.getEngine().load(p.getVideoLink());
    }


    @FXML
    private void handle_niveau(MouseEvent event) {
    }

    @FXML
    private void handle_programme(MouseEvent event) {
    }

    @FXML
    private void handle_image(MouseEvent event) {
    }


    @FXML
    private void handle_link(ActionEvent event) {
    }

    @FXML
    private void handle_webview(MouseEvent event) {
    }

    @FXML
    private void handle_prix(MouseEvent event) {
    }


   @FXML
private void handle_back(ActionEvent event) {
    try {
        // Load the FXML file for the PlanningController
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/planning.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        // Get the current stage and set the new scene
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(root));
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
private void handle_contacter(ActionEvent event) {
    try {
        // Load the FXML file for the MailController
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/mail.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        // Get the current stage and set the new scene
        Stage stage = (Stage) contacter.getScene().getWindow();
        stage.setScene(new Scene(root));
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void handle_description(MouseEvent event) {
    }

    @FXML
    private void background(MouseEvent event) {
    }


    
}
