package GUI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import entite.CodePromo;
import Services.ServiceCP;
import com.barcodelib.barcode.Linear;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class AjouterCPController  implements Initializable  {
    
    @FXML
    private Button ajoutercp;
        @FXML
    private Label barcode_read;
        @FXML
    private Button btn_barcoder;

    @FXML
    private Label lb_cp;

    @FXML
    private TextField tf_code;
       @FXML
    private Button ajoutercb;
        @FXML
    private TextField br_path;

    @FXML
    private TextField tf_description;

    @FXML
    private DatePicker dp_datedexpiration;
    
    @Override
 public void initialize(URL url, ResourceBundle rb) {}
    @FXML
    public void ajoutercpAction() {
        CodePromo cp = new CodePromo();
        ServiceCP scp = new ServiceCP();
        // Get the values from the input fields
        
        String code = tf_code.getText();
        String description = tf_description.getText();
        String datedexpiration = dp_datedexpiration.getValue().toString(); // Assuming dp_datedexpiration is a DatePicker
        
        // Create a new CodePromo object
        CodePromo newCodePromo = new CodePromo();
        newCodePromo.setCode(code);
        newCodePromo.setDescription(description);
        newCodePromo.setDatedexpiration(datedexpiration);
        
        // Call the method to add the new code promo to the database
       
        scp.ajoutercp(newCodePromo);

        // Optionally, you can display a success message or navigate to another scene
        lb_cp.setText("Code Promo ajouté avec succès!");
    }
    @FXML
     private void ajoutercbAction(){                                         
        // write Barcode
        
        try {
            
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(tf_code.getText());
            barcode.setI(11.0f);
          
          String fname = tf_code.getText();
          
            barcode.renderBarcode("C:\\Users\\nasri\\OneDrive\\Documents\\" + fname +".png" );
            
            
            
            
            
        } catch (Exception e) {
           
        }
    }   
     @FXML
      private void lirecbAction() {                                         
        // read bar code
        
        try {
             InputStream barInputStream = new FileInputStream(br_path.getText());
             BufferedImage barBufferedImage = ImageIO.read(barInputStream);
             LuminanceSource source = new BufferedImageLuminanceSource(barBufferedImage);
             BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
             Reader reader = new MultiFormatReader();
             Result result = reader.decode(bitmap);
             barcode_read.setText(result.getText());
            
            
            
        } catch (Exception e) {
            System.out.println(e);
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
