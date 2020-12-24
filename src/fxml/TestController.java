/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.jfoenix.controls.JFXButton;
import controller.Prod_pepController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.DAOLigneCommande;
import service.DaoUsers;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class TestController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXButton btnProduit;
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

     AnchorPane services, home,forum,service,panier,produit,evente,promotion,favoris;
    @FXML
    private Label temp;
    @FXML
    private Label notif;
    @FXML
    private JFXButton btnPromotion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             Date actuelle = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dat = dateFormat.format(actuelle);
                temp.setText(dat);
        
                      try {
            produit = FXMLLoader.load(getClass().getResource("/fxml/ClientProduit.fxml"));
            
            setNode(produit);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
            
       
          
        
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
    private void Produit(MouseEvent event) {
                try {
            produit = FXMLLoader.load(getClass().getResource("/fxml/ClientProduit.fxml"));
            
            setNode(produit);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void Service(MouseEvent event) {
        
                  try {
            service = FXMLLoader.load(getClass().getResource("/fxml/ClientService.fxml"));
            
            setNode(service);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void event(MouseEvent event) {
               try {
            evente = FXMLLoader.load(getClass().getResource("/fxml/Event/AcceuilEvenement.fxml"));
            
            setNode(evente);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }


    @FXML
    private void forum(MouseEvent event) {
         try {
            forum = FXMLLoader.load(getClass().getResource("/fxml/AjouterCommentaire.fxml"));
            
            setNode(forum);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }


    @FXML
    private void CheckPanier(MouseEvent event) {
          try {
            panier = FXMLLoader.load(getClass().getResource("/fxml/PanierCmd.fxml"));
            
            setNode(panier);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }


    @FXML
    private void Promotion(MouseEvent event) {
         try {
            promotion = FXMLLoader.load(getClass().getResource("/fxml/Event/AcceuilPromotion.fxml"));
            
            setNode(promotion);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void show_fav(MouseEvent event) {
                  try {
              
            favoris = FXMLLoader.load(getClass().getResource("/fxml/ClientFavoris.fxml")); 
            setNode(favoris);
        } catch (IOException ex) {
             System.out.println("dnt work: "+ex.getMessage());
        }
    }

    @FXML
    private void reclamation(MouseEvent event) {
               try {
            produit = FXMLLoader.load(getClass().getResource("/fxml/InterfaceClient.fxml"));
            
            setNode(produit);
        } catch (IOException ex) {
             System.out.println("errer: "+ex.getMessage());
        }
    }

    @FXML
    private void Logout(MouseEvent event) {
                DaoUsers usr=new DaoUsers();
        usr.LogoutUser();
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
