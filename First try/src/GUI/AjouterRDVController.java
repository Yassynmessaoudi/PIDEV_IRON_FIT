/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Rendez_vous;
import Services.serviceRendez_vous;
/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjouterRDVController implements Initializable {

    @FXML
    private Button annulerBtm;
    @FXML
    private Button confirmerBtm;
    @FXML
    private TextField coachField;
    @FXML
    private TextField timeField;

    /**
     * Initializes the controller class.
     */
    private serviceRendez_vous rendezvousService = new serviceRendez_vous();
    @FXML
    private DatePicker datepicker;
    @FXML
    private TextField clientField;
    @FXML
    private Label MSG;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: Add any necessary initialization code here
    }    

    @FXML
    private void handle_annulerBtm(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/acceuilRendez.fxml"));
            Stage stage = (Stage) annulerBtm.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   @FXML
private void handle_confirmerBtm(ActionEvent event) {
    // Check if any fields are empty
    if (coachField.getText().isEmpty() || clientField.getText().isEmpty() || datepicker.getValue() == null || timeField.getText().isEmpty()) {
        MSG.setText("Vérifier vos donner");
        return;
    }

    int id_coach = Integer.parseInt(coachField.getText());
    int id_client = Integer.parseInt(clientField.getText());
    
    // Get the date from the DatePicker as a LocalDate, then convert it to a String
    String date_RDV = datepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    
    String time_RDV = timeField.getText();

    Rendez_vous newRendezvous = new Rendez_vous(id_coach, id_client, date_RDV, time_RDV);
    rendezvousService.AjouterRendez_vous(newRendezvous);
    
    coachField.clear();
    clientField.clear();
    datepicker.setValue(null); // Clear the DatePicker
    timeField.clear();

    // Clear the error message if the operation was successful
    MSG.setText("");
}


    @FXML
    private void handle_coachField(ActionEvent event) {
    }

    @FXML
    private void handle_clientField(ActionEvent event) {
    }


    @FXML
    private void handle_timeField(ActionEvent event) {
    }

    @FXML
    private void handle_datepicker(ActionEvent event) {
    }

    @FXML
    private void handle_msg(MouseEvent event) {
    }
}
