

package controller;

import Entities.GestionServices;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class ITemServiceController  {

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
    private Label lblPrix;
    @FXML
    private Rating rating;
    @FXML
    private JFXButton btncontact;
    @FXML
    private JFXButton btnimprimer;
    @FXML
    private JFXButton btndetails;

    public ITemServiceController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/iTemService.fxml"));
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
        
        
        
        
   btncontact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/ServiceEmailer.fxml"));
                    Stage dialogStage = new Stage();
                    AnchorPane page = (AnchorPane) loader.load();
                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);
                    ServiceEmailerController controller1 = loader.getController();
                    controller1.setGt(string);
                    dialogStage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });

        btndetails.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader2 = new FXMLLoader();
                    loader2.setLocation(getClass().getResource("/fxml/ServiceDetails.fxml"));
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

    public void initialize() {

    }
    
}
