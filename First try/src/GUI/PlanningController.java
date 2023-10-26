/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import GUI.Affiche1Controller;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;
import models.Planning;
import Services.servicePlanning;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.input.MouseEvent;
import util.MyConnection;

public class PlanningController implements Initializable {

    @FXML
    private ListView<Planning> planningList;  // Change this to Planning
   
    @FXML
    private Label clock;
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicePlanning sp = new servicePlanning();
        List<Planning> planningList = sp.afficherPlanning();

        this.planningList.setItems(FXCollections.observableArrayList(planningList));
        this.planningList.setCellFactory(lv -> new ListCell<Planning>() {
            @Override
            public void updateItem(Planning item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getNiveauProgramme() + ", " + item.getProgramme() + ", " + item.getPrix());
                }
            }
        });

        Timeline clock = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            this.clock.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }),
        new KeyFrame(javafx.util.Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }





@FXML
private void handle_affiche(MouseEvent event) throws SQLException {
    if (event.getClickCount() == 2) {  // Check for double-click
        // Get the selected planning
        Planning selectedPlanning = planningList.getSelectionModel().getSelectedItem();

        // Increment the views counter
        selectedPlanning.incrementViews();

        // Get the database connection
        Connection cnx = MyConnection.getInstance().getCnx();

        // Prepare the SQL update query
        String updateQuery = "UPDATE Planning SET views = ? WHERE idPlanning = ?";
        PreparedStatement ps = cnx.prepareStatement(updateQuery);
        ps.setInt(1, selectedPlanning.getViews());
        ps.setInt(2, selectedPlanning.getIdPlanning());
        ps.executeUpdate();

        // Check if a planning is selected
        if (selectedPlanning != null) {
            try {
                // Load the FXML file for the AfficheController
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/Affiche1.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Affiche1Controller controller = fxmlLoader.getController();
                controller.setPlanning(selectedPlanning);

                Stage stage = (Stage) planningList.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
@FXML
    private void handle_planningList(ContextMenuEvent event) {
    }
  

private static Map<Planning, Integer> planningCounters = new HashMap<>();

    // Getter pour planningCounters
    public static Map<Planning, Integer> getPlanningCounters() {
        return planningCounters;
    }
    
}
