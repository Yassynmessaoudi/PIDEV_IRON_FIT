/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Rendez_vous;
import Services.serviceRendez_vous;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AcceuilRendezController implements Initializable {

    @FXML
    private AnchorPane rendez_vous;
    
    
    @FXML
    private ListView<Rendez_vous> rdvlist;
    @FXML
    private Button rdvBtm;
    @FXML
    private TextField rechercherrdvChamp;
    @FXML
    private Button supprimerrdvBtm;
    
private String searchText;


    /**
     * Initializes the controller class.
     */
    private serviceRendez_vous rendezvousService = new serviceRendez_vous();
    private TextField searchField;
    @FXML
    private Button backbtm;
    @FXML
    private Button rechercherrdvrechercherrdvChampBtm;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Rendez_vous> rendezvous = FXCollections.observableArrayList(rendezvousService.afficherRendez_vous());
        rdvlist.setItems(rendezvous);
        
        rechercherrdvChamp.textProperty().addListener((observable, oldValue, newValue) -> {
    searchText = newValue;
});
    }    

    @FXML
    private void handle_ajouterrdvBtm(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/ajouterRDV.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handle_supprimerrdvBtm(ActionEvent event) {
        Rendez_vous selectedRendezvous = rdvlist.getSelectionModel().getSelectedItem();
        rendezvousService.supprimerRendez_vous(selectedRendezvous.getId_RDV());
        rdvlist.getItems().remove(selectedRendezvous);
    }

    

    
    @FXML
    private void handle_backbtm(ActionEvent event) {
        
         try {
        // Load the FXML file for the acceuilPlanning interface
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/acceuilPlanning.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the current stage
        stage.setScene(new Scene(root));
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    

    

    private void field_rechercherPChamp(ContextMenuEvent event) {
    int id_RDV = Integer.parseInt(searchField.getText());
        Rendez_vous rendezvous = rendezvousService.getOne(id_RDV);
        if (rendezvous != null) {
            rdvlist.setItems(FXCollections.observableArrayList(rendezvous));
        } else {
            rdvlist.getItems().clear();
        }}
@FXML
private void handle_rdvlist(ContextMenuEvent event) {
    // This method is called when the user right-clicks on the rdvlist ListView.
    // You can add code here to show a context menu or perform other actions.
}


@FXML
private void field_rechercherrdvChamp(ActionEvent event) {
    int id_RDV = Integer.parseInt(searchText);
    Rendez_vous rendezvous = rendezvousService.getOne(id_RDV);
    if (rendezvous != null) {
        rdvlist.setItems(FXCollections.observableArrayList(rendezvous));
    } else {
        rdvlist.getItems().clear();
    }
}

   @FXML
private void handle_rechercherrdvChamp(ActionEvent event) {
    int id_RDV = Integer.parseInt(rechercherrdvChamp.getText());
    Rendez_vous rendezvous = rendezvousService.getOne(id_RDV);
    if (rendezvous != null) {
        rdvlist.setItems(FXCollections.observableArrayList(rendezvous));
    } else {
        rdvlist.getItems().clear();
    }
}


}
    

