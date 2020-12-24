/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.ServiceReclamation;
import Entities.Reclamation;
import Entities.User;
import java.io.IOException;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author Marouan
 */

public class InterfaceAdminController implements Initializable {

    @FXML
    private Button menuDashboard;
    @FXML
    private Button menuArbitre;
    @FXML
    private Button menuMedecin;
    @FXML
    private Button menuJoueur;
    @FXML
    private Button menuJoueur11;
   final ObservableList options= FXCollections.observableArrayList();
        @FXML
    private ComboBox co;
        
@FXML
    private TableView<User>tableView;
        @FXML
    private TableColumn<?, ?> np;   
        @FXML
    private TableColumn<?, ?> nag;
    @FXML
    private TableColumn<User, Integer> nomb;
    @FXML
   private Button btn66;
    
    
    
Connection c=Maconnexion.getinstance().getConn();
    @FXML
    private Button decon;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
     ComboBox combobox = new ComboBox(options);
     combobox.setMaxHeight(5000);
    // combobox.setPromptText("Select Nom Pepinière");
     fillcombobox();
     affi();
     
    }
    
    public  void fillcombobox(){
        
        String query = "Select nom_pepiniere from user" ;
        try {
         PreparedStatement   pt= c.prepareStatement(query);
        ResultSet rs9 = pt.executeQuery(query);
        
        while(rs9.next()){
            options.add(rs9.getString("nom_pepiniere"));
            co.setItems(options);
        }
        pt.close();
        rs9.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void Reclamation(MouseEvent event) {
        
        
        
    }
    
 private void affi(){
            np.setCellValueFactory(new PropertyValueFactory<>("nom_pepiniere"));

       nag.setCellValueFactory(new PropertyValueFactory<>("nom"));  
       nomb.setCellValueFactory(new PropertyValueFactory<>("nbr"));
       
       
       
        ObservableList<User> us = FXCollections.observableArrayList();
            Connection conn=Maconnexion.getinstance().getConn();
    
    Statement stm;
       try {
              String req_conn="select nom_pepiniere,nom,nbr from user";
              
              stm=conn.createStatement();
              ResultSet rs=stm.executeQuery(req_conn);
            System.out.println("///////////"+req_conn);
              while(rs.next()){
                  us.add(new User(rs.getString(1),rs.getString(2),rs.getInt(3))); 
                   System.out.println(rs.getString(1)+rs.getString(2)+rs.getInt(3));
              }                
          } 
          
          catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   
        tableView.setItems(us);}
     
    @FXML
     private void modEtat() throws SQLException{
         
         
      User  uu =  (User)tableView.getSelectionModel().getSelectedItem();
           String n=  String.valueOf(uu.getNom_pepiniere());
           np.setText(n);
           // System.out.println("//////////"+n);
        //id_reclamation.setText(n);
                
                
                
                ServiceReclamation se = new ServiceReclamation();
                se.modifierEtat(uu);
                
                
                
                         
         Statement st11=c.createStatement();
            String req7="select email from user where nom_pepiniere='" +uu.getNom_pepiniere()+"' ";
            ResultSet rs11=st11.executeQuery(req7);
            String email="";
                while(rs11.next())
            {
                System.out.println(rs11.getString(1));
                email=rs11.getString(1);
            } 
        
                
                
                System.out.println("+++++++++++++++++++"+email);
        service.Emailer e = new service.Emailer(email, "Votre compte a été Bloqué veuillez conctacter l'admin pour plus d info");
        e.sendmail();
          JOptionPane.showMessageDialog(null,"Bloqué avec succée");     
       affi();
       
     
 }
     @FXML
private void filtre(){
    Connection c=Maconnexion.getinstance().getConn();
     ObservableList<User> oblist=FXCollections.observableArrayList();
   /// oblist.add(new String("a"));
               co.getSelectionModel().getSelectedItem();
               String nn=String.valueOf(co.getSelectionModel().getSelectedItem());
               np.setText(nn);
               System.out.println("//////////////++"+nn);
               
            Statement stm;
             
                try {
              String req_conn="select nom_pepiniere,nom,nbr from user where nom_pepiniere='" +nn+"' ";;
              
              stm=c.createStatement();
              ResultSet rs=stm.executeQuery(req_conn);
            System.out.println("///////////"+req_conn);
              while(rs.next()){
                  oblist.add(new User(rs.getString(1),rs.getString(2),rs.getInt(3))); 
                   System.out.println(rs.getString(1)+rs.getString(2)+rs.getInt(3));
              }                
          } 
          
          catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   
        tableView.setItems(oblist);}

    @FXML
    private void btn_acceuil(MouseEvent event)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GestionAdmin.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1));  
    stage.show();
    stage.setTitle("Login"); 
    ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void btn_produit(MouseEvent event) {
    }

    @FXML
    private void btn_service(MouseEvent event) {
    }

    @FXML
    private void btn_evenement(MouseEvent event) {
    }

    @FXML
    private void btn_forum(MouseEvent event) {
    }


    @FXML
    private void dec(MouseEvent event)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1));  
    stage.show();
    stage.setTitle("Login"); 
    ((Node)(event.getSource())).getScene().getWindow().hide();
    }
               
 
}
    
