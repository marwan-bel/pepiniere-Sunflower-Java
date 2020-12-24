/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.GestionServices;
import Entities.User;
import com.jfoenix.controls.JFXTextArea;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import service.DaoUsers;
import service.Emailer;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class ServiceEmailerController  {

   @FXML
    private Label lblnom;
    @FXML
    private Label lblType;
    @FXML
    private ImageView img;
    @FXML
    private Label lblRegion;
    @FXML
    private Label lblDescription;
   
    @FXML
    private Label lblPrice;
    
    
    @FXML
    private JFXTextArea mailtext;
    
    GestionServices gt ;
    @FXML
    private Label lblemail;
      

    public void setGt(GestionServices gt) {
        this.gt = gt;
        if (this.gt != null) {
            lblnom.setText(gt.getNom_service());
            lblType.setText(gt.getType());
            lblRegion.setText(gt.getRegion());
            javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/uploads/" + gt.getImage());
            img.setImage(image);
            lblPrice.setText(""+gt.getPrix());
            lblDescription.setText(gt.getDescription());
            lblemail.setText(gt.getContact());
           
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

        if (mailtext.getText() == null || mailtext.getText().length() == 0) {
            errorMessage += "No valid  text!\n";
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
    private void sendmail() throws SQLException{
        User gs = new User();
        DaoUsers dao = new DaoUsers();
      dao.affcl();
         

         
        if(isInputValid()){
    
             Emailer em = new Emailer (gt.getContact(),dao.affcl()+mailtext.getText());
             
         em.sendmail();
         
        
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          
            alert.setTitle("mail");
            alert.setHeaderText("your mail is sent");
            
           alert.showAndWait();
     
    
  
          }
         
            
    // do what you have to do
    //s.close();
        }  
        
}
