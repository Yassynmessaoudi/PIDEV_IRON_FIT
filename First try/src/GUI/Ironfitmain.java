/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ANIS
 */
public class Ironfitmain extends Application {
    
     @Override
    public void start(Stage primaryStage) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().
            getResource("ConnexionUser.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Home");
            primaryStage.setScene(scene);
            primaryStage.show();
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(InscriptionUserController.class.getName()).log(Level.SEVERE, null, ex);

        } 
    }

    public static void main(String[] args) {
       launch(args);
     
    }
     
    
}
