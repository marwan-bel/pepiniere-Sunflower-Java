
package controller;

import Entities.Avis;
import Entities.GestionServices;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;
import service.ServiceAvis;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class ServiceDetailsController {

   
    @FXML
    private Label lblnom;
    @FXML
    private ImageView img;
    @FXML
    private Label lblType;
    GestionServices gs;
    @FXML
    private Label lblRegion;
    @FXML
    private Label lblDescription;
    @FXML
    private Rating rating;
    @FXML
    private JFXTextField txtComment;
    @FXML
    private Label lblPrice;

    public void setGs(GestionServices gs) {
        this.gs = gs;
        if (this.gs != null) {
            lblnom.setText(gs.getNom_service());
            lblType.setText(gs.getType());
            lblRegion.setText(gs.getRegion());
            javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/uploads/" + gs.getImage());
            img.setImage(image);
            lblPrice.setText(""+gs.getPrix());
            lblDescription.setText(gs.getDescription());
        }
    }

    public void initialize() {

    }
        /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (txtComment.getText() == null || txtComment.getText().length() == 0) {
            errorMessage += "No valid  Comment!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
    
    
    @FXML
    private void AddRate() {
        if(isInputValid()){
            Avis a = new Avis(gs.getNom_service(), "", "", txtComment.getText(), (float )rating.getRating());
        ServiceAvis as = new ServiceAvis();
        as.ajouterAvis(a);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(rating.getScene().getWindow());
            alert.setTitle("Rating Added");
            alert.setHeaderText("Rating Added");
            alert.show();
            
        }
    }

}
