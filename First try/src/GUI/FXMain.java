package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file for the AjouterPlanning form
Parent root = FXMLLoader.load(getClass().getResource("/GUI/statistique.fxml"));
            
            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);
            
            // Set the scene for the primary stage and show it
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
          launch(args);
    }
    }
    

