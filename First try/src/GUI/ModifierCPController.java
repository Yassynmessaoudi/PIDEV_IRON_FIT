package GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import entite.CodePromo;
import Services.ServiceCP;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.MyDB;

public class ModifierCPController {

    @FXML
    private TextField tf_idCodePromo; // Champ de texte pour l'ID

    @FXML
    private TextField tf_nouveauCode;

    private CodePromo codePromo;

    public void initialize() {
        // ...
    }

    public void setCodePromoToModify(CodePromo codePromo) {
        this.codePromo = codePromo;
    }

    @FXML
    public void modifierCodePromo() {
        String idCodePromo = tf_idCodePromo.getText(); // Récupérez l'ID saisi
        int id = Integer.parseInt(idCodePromo);

        // Utilisez l'ID pour récupérer le CodePromo à modifier
        CodePromo codePromo = getCodePromoById(id);

        if (codePromo != null) {
            String nouveauCode = tf_nouveauCode.getText();
            // Mettez à jour le code promo
            codePromo.setCode(nouveauCode);
            // Appelez le service pour mettre à jour en base de données
            ServiceCP serviceCP = new ServiceCP();
            serviceCP.modifiercp(codePromo);
            // Vous pouvez afficher un message de réussite ou naviguer vers une autre vue
        } else {
            // Affichez un message d'erreur si l'ID n'existe pas
            System.out.println("Code Promo avec ID " + id + " n'existe pas.");
        }
    }

    // Méthode pour récupérer le CodePromo par ID (vous devez l'implémenter)
    private CodePromo getCodePromoById(int id) {
       Connection conn = MyDB.getinstance().getCon();
    CodePromo codePromo = null;

    try {
        // Requête SQL pour récupérer le CodePromo par ID
        String sql = "SELECT * FROM CodePromo WHERE Id_codepromo = ?";

        // Créez une déclaration préparée
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id); // Remplacez le premier point d'interrogation par l'ID

        // Exécutez la requête
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Si un enregistrement est trouvé, créez un objet CodePromo
            codePromo = new CodePromo();
            codePromo.setId_codepromo(resultSet.getInt("Id_codepromo"));
            codePromo.setCode(resultSet.getString("code"));
            codePromo.setDescription(resultSet.getString("description"));
            codePromo.setDatedexpiration(resultSet.getString("datedexpiration"));
        }

    } catch (SQLException e) {
        // Gérez les exceptions SQLException (par exemple, les erreurs de connexion)
        e.printStackTrace();
    }

    return codePromo;
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
