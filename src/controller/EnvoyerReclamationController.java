/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.ServiceReclamation;
import Entities.Reclamation;
import controller.InterfaceClientController;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import Entities.Claims;
import Entities.User;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.Maconnexion;

/**
 *
 * @author Marouan
 */
public class EnvoyerReclamationController  implements Initializable{
    
    
    private Reclamation selectedReclamation;
    @FXML
    private TextField name;
    @FXML
    private TextField ema;
    @FXML
    private TextField titr;
    @FXML
    private TextField nompi;
    @FXML
    private TextField tell;
    @FXML
    private TextArea messa;
    private TableColumn<Reclamation, File> Files;
    
    @FXML
    private  Button btn135;
    boolean f;
    


    @FXML
    private JFXTextField refpro;



    @FXML
    private JFXTextField codp;

    @FXML
    private JFXTextField cit;



        
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
    Connection c=Maconnexion.getinstance().getConn();

     
    
    
    
    
    }
    @FXML
    
    private void addRec(ActionEvent event) throws SQLException {
        
          
        
       
      
        
                 
    
               
        Claims a = new Claims();
             String user="user01";
           try {
             
           Connection c=Maconnexion.getinstance().getConn();
            Statement st1=c.createStatement();
           //  System.out.println("++++++++++++++++++++++++++"+ema.getText());
            String req1="select id,email from user where username='" +user+"'";
            ResultSet rs1=st1.executeQuery(req1);
            String Claim_Iduser="";
            String emil="";
            while(rs1.next())
            {
                System.out.println(rs1.getString(1));
                Claim_Iduser=rs1.getString(1);
                emil=rs1.getString(2);
                System.out.println("+++++++++++++++++++++++"+Claim_Iduser);
    
               
            } 
            
            
            
            
            
            
            
            
            
            Statement st13=c.createStatement();
           String req2="select produitid from produitc where produitref='" +refpro.getText()+"'";
            ResultSet rs2=st13.executeQuery(req2);
            int prodid=0;
           
            while(rs2.next())
            {
                
                prodid=rs2.getInt(1);
               
                System.out.println("+++++++++++++++++++++++"+prodid);
    
               
            } 
            
         
       
            
           
            
            
         Statement  st = c.createStatement();
         int e = 0;
        String req=("insert into claims (claim_username,claim_lastname,claim_email,claim_productref,claim_codepostal,claim_country,phone,claim_iduser,claim_productid,claim_desc,claim_etat) values ('"
                +user+"','"+name.getText()+"','"+emil+"','"+refpro.getText()+"','"+codp.getText()+"','"+cit.getText()+"','"+tell.getText()+"','"+Claim_Iduser+"','"+prodid+"','"+messa.getText()+"','"+e+"')");
           
        System.out.println("-*********"+req);
           st.executeUpdate(req);
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    //JOptionPane.showMessageDialog(null,"Ajouter avec succée");
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(btn135.getScene().getWindow());
            alert.setTitle("Reclamation");
            alert.setHeaderText("votre formulaire à été envoyée avec succès");
            alert.show();
    
 /* Connection c=MyConnection.getInstance().getConn();
         Statement st6=c.createStatement();
            String req5="select nbr from user where nom_pepiniere='" +nompi.getText()+"' ";
            ResultSet rs5=st6.executeQuery(req5);
          int nbr=0;
       
            while(rs5.next())
            {
                System.out.println("/////////////////////////"+rs5.getString(1));
          
              nbr=rs5.getInt(1);
              nbr++;
               System.out.println("//////////////"+nbr);
             
              
            } 
           
            System.out.println("//////////////"+nbr);
            PreparedStatement pt=c.prepareStatement("update user set nbr = ?  where nom_pepiniere=?");
                 User u= new User();
   
                 pt.setInt(1,nbr);
                 
                 pt.setString(2, nompi.getText());
                 
                 pt.executeUpdate();
*/
    
}}
     
 
                 
           
                

    
              
        
        

