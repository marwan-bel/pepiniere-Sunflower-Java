/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml.Agent;

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
public class AgentController implements Initializable {

    @FXML
    private JFXButton btnService;
    @FXML
    private JFXButton btnEvenement;
    @FXML
    private JFXButton btnForum;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXButton btnLogout1;
    @FXML
    private AnchorPane holderPane;

      AnchorPane service;
    @FXML
    private JFXButton btnLogout11;
    @FXML
    private JFXButton btnLogout111;
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
            service = FXMLLoader.load(getClass().getResource("/Agent/AgentService.fxml"));
            
            setNode(service);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void GestionProduit(MouseEvent event) {
           try {
            service = FXMLLoader.load(getClass().getResource("/fxml/Prod_pep.fxml"));
            
            setNode(service);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }


    @FXML
    private void GestionAstuce(MouseEvent event) {
           try {
            service = FXMLLoader.load(getClass().getResource("/fxml/AjouterSujet.fxml"));
            
            setNode(service);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void GEstionRec(MouseEvent event) {
         try {
            service = FXMLLoader.load(getClass().getResource("/fxml/InterfaceAgent.fxml"));
            
            setNode(service);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void GestionEvent(MouseEvent event) {
           try {
            service = FXMLLoader.load(getClass().getResource("/fxml/Evenement.fxml"));
            
            setNode(service);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
        
    }

    @FXML
    private void Gestionpromotion(MouseEvent event) {
         try {
            service = FXMLLoader.load(getClass().getResource("/fxml/Promotion.fxml"));
            
            setNode(service);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void deconnection(MouseEvent event) {
        DaoUsers usr=new DaoUsers();
        usr.LogoutUser();
         try {
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
     
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.setScene(new Scene(root1));
            stage.show();
             ((Node) (event.getSource())).getScene().getWindow().hide();
               } catch (IOException ex) {
            Logger.getLogger(Prod_pepController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
