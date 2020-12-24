/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Avis_prod;
import Entities.*;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
import service.Service_Avisprod;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author beerus
 */
public class Avis_prdController implements Initializable {

 private Produit ps;
    @FXML
    private Rating rating;
    @FXML
    private JFXTextField txtComment;
    @FXML
    private Label pri;
    @FXML
    private Label des;
        @FXML
    private Label msg;
        @FXML
    private Label namee;
            @FXML
    private ImageView ima;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rating.ratingProperty().addListener(new ChangeListener<Number>(){
          @Override
          public void changed(ObservableValue<? extends Number> observable, Number t, Number t1) {
              
              msg.setText("Rating :"+" "+t1.toString());
              
    }    
                });}

    
   public void setp(Produit ps) {
        this.ps = ps;
         {
            pri.setText(ps.getPrix_prod()+"");
            des.setText(ps.getDesc_prod());
            namee.setText(ps.getNom_prod());
            pri.setText(ps.getPrix_prod()+"");
            des.setText(ps.getDesc_prod());
            javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/uploads/" + ps.getImg());
            ima.setImage(image);
            
          
        }
   }
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
             Avis_prod a;
             
             
   Connection c=  Maconnexion.getinstance().getConn();
    @FXML
    private void add_rate(MouseEvent event) {
        if(isInputValid()){
            a = new Avis_prod(ps.getId_prod(), txtComment.getText(),rating.getRating());
            Service_Avisprod as= new Service_Avisprod();
                         
        try {   
            Statement st1=c.createStatement();
            String req1="select id_client from Avis_produit where id_produit="+ps.getId_prod();
            ResultSet rs1=st1.executeQuery(req1);
            if(rs1.next()){    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(rating.getScene().getWindow());
            alert.setTitle("ERREUR");
            alert.setHeaderText("vous avez deja évalué ce produit");
            alert.show();}
           else {
            as.ajouter(a);     
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(rating.getScene().getWindow());
            alert.setTitle("Rating Added");
            alert.setHeaderText("Rating Added");
            alert.show();
                  } 
                } catch (SQLException e) {
             System.out.println("errrrrrrrrrrrr "+ e.getMessage());
         } 
        
      

            
        }
    }
 

         
        }   
         
        
