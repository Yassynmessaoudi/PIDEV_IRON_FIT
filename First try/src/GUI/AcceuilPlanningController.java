package GUI;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;
import models.Planning;
import Services.servicePlanning;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.ObservableList;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class AcceuilPlanningController implements Initializable {

    @FXML
    private ListView<Planning> planninglist;
   

    @FXML
    private Button modifierPBtm;
    @FXML
    private Button SupprimerPBtm;

    private servicePlanning planningService = new servicePlanning();
    @FXML
    private Button Rdvbtm;
    @FXML
    private Button ajouterPBtm;
    @FXML
    private TextField rechercherPChamp;
    @FXML
    private Button rechercherPBtm;
    @FXML
    private Button excel;
    @FXML
    private Button back;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Planning> planning = FXCollections.observableArrayList(planningService.afficherPlanning());
        planninglist.setItems(planning);
    }  
    
   
    @FXML
    public void handle_ajouterPBtm(ActionEvent event) {
    try {
        // Load the FXML file for the ajouterPlanning interface
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/ajouterPlanning.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the current stage
        stage.setScene(new Scene(root));
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
public void handle_Rdvbtm(ActionEvent event) {
    try {
        // Load the FXML file for the acceuilRDV interface
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/acceuilRendez.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the current stage
        stage.setScene(new Scene(root));
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    public void handle_modifierPBtm(ActionEvent event) {
    // Get the selected planning
    Planning selectedPlanning = planninglist.getSelectionModel().getSelectedItem();

    // Check if a planning is selected
    if (selectedPlanning != null) {
        // Set the planning to modify
        ModifierPlanningController.planningToModify = selectedPlanning;

        try {
            // Load the FXML file for the modifierPlanning interface
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/modifierPlanning.fxml"));

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the current stage
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Please select a planning to modify.");
    }
}

    @FXML
    private void handle_SupprimerPBtm(ActionEvent event) {
        
        
        Planning selectedPlanning = planninglist.getSelectionModel().getSelectedItem();
        planningService.supprimerPlanning(selectedPlanning.getIdPlanning());
        planninglist.getItems().remove(selectedPlanning);
    }
  
    @FXML
    private void handle_planninglist(ContextMenuEvent event) {
            
  // Get the idPlanning from the searchField
    int idPlanning = Integer.parseInt(rechercherPChamp.getText());

    // Search for the planning in the database
    Planning planning = planningService.getOne(idPlanning);

    // Check if a planning was found
    if (planning != null) {
        // Display the found planning in the ListView
        planninglist.setItems(FXCollections.observableArrayList(planning));
    } else {
        // Clear the ListView if no planning was found
        planninglist.getItems().clear();
    }
    }

    @FXML
    private void field_rechercherPBtm(ActionEvent event) {
    try {
        int idPlanning = Integer.parseInt(rechercherPChamp.getText());
        Optional<Planning> planningOptional = Optional.ofNullable(planningService.getOne(idPlanning));
        if (planningOptional.isPresent()) {
            planninglist.setItems(FXCollections.observableArrayList(planningOptional.get()));
        } else {
            planninglist.getItems().clear();
            System.out.println( "No planning was found with the specified ID.");
        }
    } catch (NumberFormatException e) {
        System.out.println( "Please enter a valid ID.");
    }}

@FXML
private void field_rechercherPChamp(ActionEvent event) {
  // Create an instance of ServicePlanning
servicePlanning servicePlanningInstance = new servicePlanning();
    

// Get the search text from the searchField and convert it to lowercase
    String searchText = rechercherPChamp.getText().toLowerCase();

    // Get all plannings from the servicePlanning
    List<Planning> allPlannings = servicePlanningInstance.afficherPlanning();


    // Create a new list to store the filtered plannings
    List<Planning> filteredPlannings = new ArrayList<>();

    // Iterate over all plannings
    for (Planning planning : allPlannings) {
        // Check if the programme of the planning contains the search text
        if (planning.getProgramme().toLowerCase().contains(searchText)) {
            // If it does, add the planning to the list of filtered plannings
            filteredPlannings.add(planning);
        }
    }

    // Set the items of the planninglist to the filtered plannings
    planninglist.setItems(FXCollections.observableArrayList(filteredPlannings));
}

@FXML
    private void btnexcel(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        
         // Set extension filter for Excel files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (.xlsx)", ".xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        // Show save file dialog
        File file = fileChooser.showSaveDialog(excel.getScene().getWindow());
         if (file != null) {
            try {
                // Create new Excel workbook and sheet
                Workbook workbook = new XSSFWorkbook();

                Sheet sheet = workbook.createSheet("Planning");

                // Create header row
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("ID Planning");
                headerRow.createCell(1).setCellValue("Niveau Programme");
                headerRow.createCell(2).setCellValue("Programme");     
                headerRow.createCell(3).setCellValue("ID Coach");
                headerRow.createCell(4).setCellValue("Prix"); 
                 headerRow.createCell(5).setCellValue("Video Link");
                headerRow.createCell(6).setCellValue("Description"); 
                // Add data rows
                servicePlanning sp = new servicePlanning();
               List<Planning> planning = sp.afficherPlanning();
                for (int i = 0; i < planning.size(); i++) {
                  Row row = sheet.createRow(i + 1);
                   row.createCell(0).setCellValue(planning.get(i).getIdPlanning());
                   row.createCell(1).setCellValue(planning.get(i).getNiveauProgramme());
                   row.createCell(2).setCellValue(planning.get(i).getProgramme());
                   row.createCell(3).setCellValue(planning.get(i).getId_coach());
                   row.createCell(4).setCellValue(planning.get(i).getPrix());
                   row.createCell(5).setCellValue(planning.get(i).getVideoLink());
                   row.createCell(6).setCellValue(planning.get(i).getDescription());
                }
                   // Write to file
                FileOutputStream fileOut = new FileOutputStream(file);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Export Successful");
                alert.setHeaderText(null);
                alert.setContentText("Events exported to Excel file.");
                alert.showAndWait();
                } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Export Failed");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while exporting events to Excel file.");
                alert.showAndWait();
            }
        }
    }   

    @FXML
    private void btnBack(ActionEvent event) {
    }
    }


   
   
   

  

 
 
    

