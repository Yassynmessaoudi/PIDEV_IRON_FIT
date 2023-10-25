package GUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import entite.CodePromo;
import Services.ServiceCP;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AfficherCPController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button btr7;
       @FXML
    private Button excel;
    @FXML
    private ListView<String> CPListView;

    private List<CodePromo> codepromo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCP ss = new ServiceCP();
        codepromo = ss.getAllcp();
        ObservableList<String> obs = FXCollections.observableArrayList();
        for (CodePromo cp : codepromo) {
            String Stringcp = "Code : " + cp.getCode() +
                ", Description : " + cp.getDescription() +
                ", Date d'expiration : " + cp.getDatedexpiration();
               
            obs.add(Stringcp);
        }
        CPListView.setItems(obs);
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

                Sheet sheet = workbook.createSheet("SalleDeSport");

                // Create header row
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("ID");
                headerRow.createCell(1).setCellValue("Code");
                headerRow.createCell(2).setCellValue("Description");     
                headerRow.createCell(3).setCellValue("Date d'xpiration");
                // Add data rows
                ServiceCP sp = new ServiceCP();
                List<CodePromo> CodePromo = sp.getAllcp();
                for (int i = 0; i < CodePromo.size(); i++) {
                  Row row = sheet.createRow(i + 1);
                   row.createCell(0).setCellValue(CodePromo.get(i).getId_codepromo());
                   row.createCell(1).setCellValue(CodePromo.get(i).getCode());
                   row.createCell(2).setCellValue(CodePromo.get(i).getDescription());
                   row.createCell(3).setCellValue(CodePromo.get(i).getDatedexpiration());
                  
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
    private void Retour(ActionEvent event) {
    
    try {
    Parent root = FXMLLoader.load(getClass().getResource("GestionCp.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();} catch (IOException ex) {
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}