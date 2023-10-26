package GUI;

import java.io.IOException;
import javax.mail.internet.*;
import javax.mail.MessagingException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.EmailManager;

public class MailController implements Initializable {

    @FXML
    private TextField mailField;
    @FXML
    private Label msg;
    @FXML
    private Button envoyer;
    @FXML
    private Button back;

    @FXML
    private void handle_mailField(ActionEvent event) {
    }

    @FXML
    private void handle_msg(MouseEvent event) {
    }

    @FXML
    private void handle_envoyer(ActionEvent event) throws AddressException, MessagingException {
        EmailManager emailManager = new EmailManager();
      String welcomeMessage = "Welcome to our FITNESS WORLD !\n\n\n"
               
                + "Cher(e) CLIENT ,\n\n\n"
                + "J'espère que vous allez bien. Ce message est pour vous informer de la disponibilité de votre coach assigné.\n\n\n"
                + "Votre coach est disponible de 9H à 18H tous les jours sauf dimanche. Il est dédié à vous aider à atteindre vos objectifs de fitness et attend avec impatience votre prochaine séance.\n\n\n"
                + "N'hésitez pas à nous contacter si vous avez des questions ou si vous souhaitez planifier une séance pendant ces horaires. Nous sommes là pour vous soutenir dans votre parcours de fitness.\n\n\n"
                + "Cordialement,\n\n"
                + "Med Amine Lahmer \n\n"
                + " Responsable du Planning ";


        String clientEmail = mailField.getText(); // get email from TextField
        emailManager.sendEmail(clientEmail, welcomeMessage);
        msg.setText("Email envoyé !"); // display success message
    }

      @FXML
private void handle_back(ActionEvent event) {
    try {
        // Load the FXML file for the PlanningController
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/planning.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        // Get the current stage and set the new scene
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(root));
    } catch (IOException e) {
        e.printStackTrace();
    }
}
   @Override
   public void initialize(URL url, ResourceBundle rb) {
       // TODO: Add your initialization logic here.
   }
}
