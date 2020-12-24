/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml.fxmlAdmin;

import com.jfoenix.controls.JFXButton;
import controller.Prod_pepController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.DaoUsers;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class AdminController implements Initializable {

    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnProduit;
    @FXML
    private JFXButton btnPromotion;
    @FXML
    private JFXButton btnService;
    @FXML
    private JFXButton btnEvenement;
    @FXML
    private JFXButton btnRec;
    @FXML
    private JFXButton btnForum;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXButton btnLogout1;
    @FXML
    private AnchorPane holderPane;

    AnchorPane promotion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void GestionService(MouseEvent event) {
            try {
            promotion = FXMLLoader.load(getClass().getResource("/Admin/AdminService.fxml"));
            
            setNode(promotion);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }
    
     @FXML
    private void GestionEvent(MouseEvent event) {
            try {
            promotion = FXMLLoader.load(getClass().getResource("/fxml/Evenement.fxml"));
            
            setNode(promotion);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void GestionProduit(MouseEvent event) {
          try {
            promotion = FXMLLoader.load(getClass().getResource("/fxml/Prod_pep.fxml"));
            
            setNode(promotion);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void GestionCmd(MouseEvent event) {
           try {
            promotion = FXMLLoader.load(getClass().getResource("/fxml/fxmlAdmin/GestionCommande.fxml"));
            
            setNode(promotion);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void GestionAstuce(MouseEvent event) {
               try {
            promotion = FXMLLoader.load(getClass().getResource("/fxml/AjouterSujet.fxml"));
            
            setNode(promotion);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void GEstionRec(MouseEvent event) {
           try {
            promotion = FXMLLoader.load(getClass().getResource("/fxml/InterfaceAdmin.fxml"));
            
            setNode(promotion);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void Logout(MouseEvent event) {
       
         try {
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
     
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.setScene(new Scene(root1));
              stage.setMaximized(true);
            stage.show();
             ((Node) (event.getSource())).getScene().getWindow().hide();
            
               } catch (IOException ex) {
            Logger.getLogger(Prod_pepController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
