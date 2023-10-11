/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.SalleDeSport;
import services.ServicesSalleDeSport;

public class FXMLAfficheSalleDeSportController implements Initializable{

    @FXML
    private TableColumn<SalleDeSport, String> adressesalledesport;

    @FXML
    private TableColumn<SalleDeSport, Integer> capacitesalledesport;

    @FXML
    private TableColumn<SalleDeSport, Integer> idsalledesport;

    @FXML
    private TableColumn<SalleDeSport, String> nomsalledesport;

    @FXML
    private TableColumn<SalleDeSport, String> specialitesalledesport;

    @FXML
    private TableView<SalleDeSport> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       ServicesSalleDeSport ss = new ServicesSalleDeSport();
       ArrayList<SalleDeSport> salleDeSports;
       salleDeSports = (ArrayList<SalleDeSport>) ss.getAllSalleDeSport();
       ObservableList obs = FXCollections.observableArrayList(salleDeSports);
       table.setItems(obs);
       idsalledesport.setCellValueFactory(new PropertyValueFactory<>("ID"));
       nomsalledesport.setCellValueFactory(new PropertyValueFactory<>("Nom"));
       adressesalledesport.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
       capacitesalledesport.setCellValueFactory(new PropertyValueFactory<>("Capacite"));
       specialitesalledesport.setCellValueFactory(new PropertyValueFactory<>("Specialite"));

}
}
