/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.DocumentException;
import entities.Commande;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.commandeService;

import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import static javax.swing.text.html.HTML.Tag.OL;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author gheri
 */
public class Afficher_commandeController implements Initializable {

    @FXML
    private ListView<Commande> affichercommande;
    @FXML
    private Button supprimer;
    @FXML
    private Button mod;

    static String refCommande;
    static String delaisLivraison;
    static double FraisdePort;
    static double montant;
    @FXML
    private Button ajouter;
    @FXML
    private TextField fx_delias;
    @FXML
    private TextField fx_frais;
    @FXML
    private TextField fx_montant;
    @FXML
    private AnchorPane bord;
    @FXML
    private Button exit;
    @FXML
    private TextField fx_ref;
    @FXML
    private Button excel;
    @FXML
    private TextField rech_tf;
    @FXML
    private Button btn_tf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ListView<Commande> list1 = affichercommande;
        commandeService inter = new commandeService();
        List<Commande> list2 = inter.afficherCommande();
        for (int i = 0; i < list2.size(); i++) {
            Commande A = list2.get(i);
            list1.getItems().add(A);

        }
    }

    @FXML
    private void supprimer_commande(ActionEvent event) {

        ListView<Commande> list1 = affichercommande;
        commandeService inter = new commandeService();
        int selectedIndex = list1.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Commande A = list1.getSelectionModel().getSelectedItem();
            System.out.println(A.getRefCommande());
            inter.supprimercommande(A.getRefCommande());
            list1.getItems().remove(selectedIndex);
            refreshListView(); // Rafraîchit la ListView après la suppression
            showAlertS("Succès", "Commande supprimée avec succès.");

        } else {
            System.out.println("Veuillez sélectionner une commande à supprimer.");
        }
    }

    @FXML
    private void mod(ActionEvent event) {

        ListView<Commande> list = affichercommande;
        commandeService inter = new commandeService();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        Commande A = list.getSelectionModel().getSelectedItem();

        refCommande = A.getRefCommande();
        delaisLivraison = A.getDelaisLivraison();
        FraisdePort = A.getFraisdePort();
        montant = A.getMontant();
        //Prix_jour= Integer.toString(v.getPrix_jours()) ;

        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("modifiercommande.fxml"));
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
    private void ajouter(ActionEvent event) {
        String delaisLivraison = fx_delias.getText();
        String refCommande = fx_ref.getText();
        double fraisDePort;
        double montant;

        try {
            fraisDePort = Double.parseDouble(fx_frais.getText());
            montant = Double.parseDouble(fx_montant.getText());
        } catch (NumberFormatException e) {
            afficherAlerte("Erreur de saisie", "Les champs Frais de Port et Montant doivent être des nombres valides.");
            return;
        }
        if (refCommande.isEmpty()) {
            afficherAlerte("Erreur de saisie", "Veuillez entrer une refCommande");
        } else if (fraisDePort < 0) {
            afficherAlerte("Erreur de saisie", "Le Frais de Port ne peut pas être négatif.");
        } else if (delaisLivraison.isEmpty()) {
            afficherAlerte("Erreur de saisie", "Veuillez entrer un délai de livraison.");
        } else if (montant < 0) {
            afficherAlerte("Erreur de saisie", "Le montant ne peut pas être négatif.");
        } else {
            Commande commande = new Commande(refCommande, delaisLivraison, fraisDePort, montant);
            commandeService crud = new commandeService();
            crud.ajouterCommande(commande);
            showAlertS("Succès", "Commande insérée avec succès!");
            refreshListView(); // Rafraîchit la ListView après l'ajout
        }
    }

    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlertS(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }

    private void refreshListView() {
        affichercommande.getItems().clear(); // Efface tous les éléments actuels
        commandeService inter = new commandeService();
        List<Commande> list2 = inter.afficherCommande();
        affichercommande.getItems().addAll(list2); // Ajoute les nouvelles données
    }

//  private void affecterlivraison() {
//    ListView<Commande> list = affichercommande;
//    commandeService inter = new commandeService();
//    int selectedIndex = list.getSelectionModel().getSelectedIndex();
//    
//    // Vérifiez si un élément est sélectionné dans la ListView
//    if (selectedIndex >= 0) {
//        Commande A = list.getSelectionModel().getSelectedItem();
//
//        try {
//            Parent page1 = FXMLLoader.load(getClass().getResource("Afficher_livraison.fxml"));
//            Scene scene = new Scene(page1);
//            Stage stage = (Stage) list.getScene().getWindow();  // Utilisez la ListView pour obtenir la fenêtre actuelle
//            stage.setScene(scene);
//            stage.show();
//
//            // Affichez un message de confirmation
//          //  showAlert("Succès", "Livraison affectée avec succès à la commande.");
//        } catch (IOException ex) {
//            Logger.getLogger(Location_commandeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    } else {
//        // Gérez le cas où aucune commande n'a été sélectionnée
//        afficherAlerte("Erreur", "Veuillez sélectionner une commande avant d'affecter une livraison.");
//    }
//}
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

                Sheet sheet = workbook.createSheet("ListeCommandes");

                // Create header row
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("REF COMMANDE");
                headerRow.createCell(1).setCellValue("DELAIS LIVRAISON");
                headerRow.createCell(2).setCellValue("FRAIS DE PORT");
                headerRow.createCell(3).setCellValue("MONTANT");
                ;
                // Add data rows
                commandeService liv = new commandeService();
                List<Commande> Commande = liv.afficherCommande();
                for (int i = 0; i < Commande.size(); i++) {
                    Row row = sheet.createRow(i + 1);
                    row.createCell(0).setCellValue(Commande.get(i).getRefCommande());
                    row.createCell(1).setCellValue(Commande.get(i).getDelaisLivraison());
                    row.createCell(2).setCellValue(Commande.get(i).getFraisdePort());
                    row.createCell(3).setCellValue(Commande.get(i).getMontant());

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


    private ListView<Commande> gaListView;

   
//
//    @FXML
//    void recherche(ActionEvent event) {
//        commandeService serE;
//        try {
//            serE = new commandeService();
//            ArrayList<Commande> list = serE.read();
//
//            ArrayList<Commande> topResults = search(list, rech_tf.getText());
//
//            ObservableList<Commande> ol = FXCollections.observableArrayList(topResults);
//            gaListView.setItems(ol);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Méthode pour rechercher des commandes en fonction de la chaîne de recherche
//    private ArrayList<Commande> search(ArrayList<Commande> commandes, String recherche) {
//        ArrayList<Commande> résultats = new ArrayList<>();
//        for (Commande cmd : commandes) {
//            if (cmd.getRefCommande().toLowerCase().contains(recherche.toLowerCase())) {
//                résultats.add(cmd);
//            }
//        }
//        return résultats;
//    }
    
    @FXML
void recherche(ActionEvent event) {
    commandeService serE;
    try {
        serE = new commandeService();
        ArrayList<Commande> list = serE.afficheroneacount(rech_tf.getText());

        ObservableList<Commande> ol = FXCollections.observableArrayList(list);
        gaListView.setItems(ol);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}

