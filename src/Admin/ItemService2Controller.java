/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Entities.GestionServices;
import com.jfoenix.controls.JFXButton;
import controller.ServiceDetailsController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author shmhi
 */
public class ItemService2Controller implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private ImageView img;
    @FXML
    private Text txtnom;
    @FXML
    private Text txtdesc;
    @FXML
    private Text txtRegsion;
    @FXML
    private Rating rating;
    @FXML
    private Label lblPrix;
    @FXML
    private JFXButton btndetails;
     public ItemService2Controller() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("itemService_1.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(GestionServices string) {
        txtnom.setText(string.getNom_service());
        txtdesc.setText(string.getDescription());
        txtRegsion.setText(string.getRegion());
        lblPrix.setText(string.getPrix() + " TND");
        System.out.println(string);
        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/uploads/" + string.getImage());
        img.setImage(image);
        rating.setRating(string.getGlobal_rating());
        
        
        
  

        btndetails.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader2 = new FXMLLoader();
                    loader2.setLocation(getClass().getResource("ServiceDetails.fxml"));
                    Stage dialogStage = new Stage();
                    AnchorPane page = (AnchorPane) loader2.load();
                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);
                    ServiceDetailsController controller = loader2.getController();
                    controller.setGs(string);
                    dialogStage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });
      
    }
  public HBox getBox() {
        return Hbox;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
