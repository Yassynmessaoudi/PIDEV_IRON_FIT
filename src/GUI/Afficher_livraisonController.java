/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commande;
import entities.Livraison;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import services.commandeService;
import services.livraisonService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author gheri
 */
public class Afficher_livraisonController implements Initializable {

    @FXML
    private Button mod;
    @FXML
    private Button supprimer;
    @FXML
    private ListView<Livraison> afficherlivraison;

     static int ref_livraison;
   static Date date_livraison;
        static int refCommande;
    private DatePicker fx_datel;
    private TextField fx_refc;
    @FXML
    private Button ajouter;
    @FXML
    private Button exit;
    @FXML
    private AnchorPane bord;
    @FXML
    private ComboBox<String> cat_cb;
        Livraison A = new Livraison();

    
        Livraison livraison = new Livraison();
    livraisonService ss = new livraisonService();
    @FXML
    private DatePicker created_prod;
    @FXML
    private Button btn_tf;
    @FXML
    private TextField rech_tf;
    @FXML
    private Button excel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          //GET CATREGORIES LISTE DEROULANTE FOR JOIN !
                        ObservableList<String>list = FXCollections.observableArrayList();
                        commandeService sc = new commandeService();
                        
                        
                      
                        ObservableList<Commande>obList = FXCollections.observableArrayList();
                        obList =sc.afficherCommande2();

        cat_cb.getItems().clear();
        
        for(Commande nameCat : obList) {
            System.out.println("hii");
            list.add(nameCat.getRefCommande());
                        System.out.println("hii"+list);

                    cat_cb.setItems(list);

        }
//        // TODO
//        fx_refc.setText((Afficher_commandeController.refCommande));
//                fx_refc.setText(Integer.toString(Afficher_livraisonController.refCommande));
//
//          if (data != null) {
//            fx_refc.setText((data.getRefCommande()));
//        }
          
        ListView<Livraison> list1= afficherlivraison;
        livraisonService inter = new livraisonService();
        List<Livraison> list2 = inter.afficher();
        for (int i = 0; i < list2.size(); i++) {
            Livraison A = list2.get(i);
            list1.getItems().add(A);

        }  
        
    }    
private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.show();
}
    @FXML
    private void mod(ActionEvent event) {
        
           ListView<Livraison> list= afficherlivraison;
        livraisonService inter = new livraisonService();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        Livraison A = list.getSelectionModel().getSelectedItem();
        ref_livraison=A.getRef_livraison();
        //created_prod= A.getDate_livraison();
        

        try {

            
            Parent page1
                    = FXMLLoader.load(getClass().getResource("modifierlivraison.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
                        refreshListView(); // Rafraîchit la ListView après la modif

        } catch (IOException ex) {
            Logger.getLogger(Location_commandeController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void supprimer_livraison(ActionEvent event) {
        
          ListView<Livraison> list1 = afficherlivraison;
        livraisonService inter = new livraisonService();
        int selectedIndex = list1.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Livraison A = list1.getSelectionModel().getSelectedItem();
            System.out.println(A.getRef_livraison());
            inter.supprimerLivraison(A.getRef_livraison());
            list1.getItems().remove(selectedIndex);
                        refreshListView(); // Rafraîchit la ListView après la suppression
    showAlert("Succès", "Livraison supprimée avec succès.");

        } else {
            showAlert("Erreur","Veuillez sélectionner une livraison à supprimer.");
        }

    }

//    @FXML
//    private void ajouter(ActionEvent event) {
//
//    livraisonService inter = new livraisonService();
//    
//     Calendar v=Calendar.getInstance();
//        int y=created_prod.getValue().getYear();
//        int m=created_prod.getValue().getMonthValue();
//        int j=created_prod.getValue().getDayOfMonth();
//      
//        Date date = new Date(j,m,y); 
//    livraison.setDate_livraison(date);
//
//        
//     
//        
//        
//
//      
//
//            ss.ajouterLivraison(new Livraison(dateLivraison, cat_cb.getValue()));
//            System.out.println(new Livraison(dateLivraison, cat_cb.getValue()));
//
//                    
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Success Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Article ajouté avec succés !");
//            alert.showAndWait();
//        }
//        
//        
//        
//    
    
    @FXML
private void ajouter(ActionEvent event) {
    // Vous avez créé une instance de `livraisonService` mais utilisé `inter` ici, nous devrions utiliser `inter` ou `ss` (si `ss` est une instance de `livraisonService`) de manière cohérente.
    livraisonService inter = new livraisonService();

    // Utilisez try-catch pour gérer les exceptions liées à la conversion de la date.
    try {
        int y = created_prod.getValue().getYear();
        int m = created_prod.getValue().getMonthValue();
        int j = created_prod.getValue().getDayOfMonth();

        // Utilisez `LocalDate` pour obtenir la date.
        LocalDate localDate = LocalDate.of(y, m, j);
        java.sql.Date date = java.sql.Date.valueOf(localDate);

        // Assurez-vous que `livraison` est correctement initialisé, sinon, vous devrez créer une instance de la classe `Livraison`.
        Livraison livraison = new Livraison(date, cat_cb.getValue());

        // Utilisez `inter` ou `ss` pour appeler la méthode `ajouterLivraison` selon votre configuration.
        inter.ajouterLivraison(livraison);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message de succès");
        alert.setHeaderText(null);
        alert.setContentText("Livraison ajoutée avec succès !");
        alert.showAndWait();
        refreshListView();
    } catch (Exception e) {
        e.printStackTrace();
        // Gérez les erreurs liées à la conversion de la date ici.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur est survenue lors de l'ajout de la livraison.");
        alert.showAndWait();
    }
}

private void afficherAlerte(String titre, String contenu) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(contenu);
    alert.show();
}

 private void refreshListView() {
        afficherlivraison.getItems().clear(); // Efface tous les éléments actuels
        livraisonService inter = new livraisonService();
        List<Livraison> list2 = inter.afficher();
        afficherlivraison.getItems().addAll(list2); // Ajoute les nouvelles données
    }
 
  public void setRefCommande(int refCommande) {
        fx_refc.setText(Integer.toString(refCommande));
    }
  
  
  private Commande data;

    // ...

    public void setData(Commande data) {
        this.data = data;
    }

    @FXML
    private void exit(ActionEvent event) {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("location_commande.fxml"));
        try {
            Parent root = loader.load();
            bord.getChildren().setAll(root);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }


    @FXML
    private void recherche(ActionEvent event) {
        /*
        
          ArrayList<Commande> list = new ArrayList<>();
        ArrayList<Commande> topResults = new ArrayList<>();
        commandeService serE;
        try {
            serE = new commandeService();
            list = serE.read();
            
            topResults = search(list, rech_tf.getText());
            
                 
                ObservableList Ol = FXCollections.observableArrayList(topResults);
            ga_tv.setItems(Ol);
            tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tv_id_auteur.setCellValueFactory(new PropertyValueFactory<>("id_auteur"));
            tv_date.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
            tv_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tv_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
            tv_image.setCellValueFactory(new PropertyValueFactory<>("image_url"));
             ga_tv.getSelectionModel().selectFirst();
            
            
        } catch (Exception e) {

        }*/
    }

    @FXML
    private void excel(ActionEvent event) {
        
     
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

                Sheet sheet = workbook.createSheet("Livraison");

                // Create header row
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("REF LIVRAISON");
                headerRow.createCell(1).setCellValue("DATE LIVRAISON");
                headerRow.createCell(2).setCellValue("REF COMMANDE");     
            ; 
                // Add data rows
                livraisonService liv = new livraisonService();
                List<Livraison> Livraison = liv.afficherLivraison2();
                for (int i = 0; i < Livraison.size(); i++) {
                  Row row = sheet.createRow(i + 1);
                   row.createCell(0).setCellValue(Livraison.get(i).getRef_livraison());
                   row.createCell(1).setCellValue(Livraison.get(i).getDate_livraison());
                   row.createCell(2).setCellValue(Livraison.get(i).getRefCommande());
         
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
                alert.setContentText("Livraison exported to Excel file.");
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
    
    }
