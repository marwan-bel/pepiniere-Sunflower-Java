/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.DetailedController;
import service.ServiceReclamation;
import Entities.Claims;
import javax.mail.Service.*;

import Entities.Reclamation;
import Entities.User;
import java.io.File;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author Marouan
 */
public class InterfaceClientController implements Initializable {

    @FXML
    private ImageView optionTailleParDefaut;
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
    @FXML
    private Button menuArbitre121;
    @FXML
    private AnchorPane GestionReclamation;
    @FXML
    private TableView<Claims>tableView;
    @FXML
    private TableColumn<Claims, String> id_reclamation;
    @FXML
    private TableColumn<Claims, String> nom_reclamation;
    @FXML
    private TableColumn<Claims,String> nom_client;
    @FXML
    private TableColumn<Claims, String> tcp;
    @FXML
    private TableColumn<Claims,String> tp;
    @FXML
    private TableColumn<Claims, String> date_reclamation;
    @FXML
    private TableColumn<Claims, String> message;
    @FXML
    private TableColumn<Claims, String> rp;
 
    @FXML
    private TableColumn<Claims, String> etat;
    private TextField name;
    private TextField titre;
    private TextField agent;
    private TextArea mess;
    private TextField tél;
     private  String etasString="Non Traité";

    
    
     private Button detailedReclamationViewButton;
         @FXML
    private Button ButtonModifier;
    private Parent tableViewParent;
    private TextField name1;
    @FXML
    private Button ButtonSupprimer;
    @FXML
    private Button btn98;
    
    private String path;
    
    private ImageView ima;
    @FXML
    private Button btn99;
    
    @FXML
    private  Button btn166;
    
    private int id_client;
    
     
   


    /**
     * Initializes the controller class.
     * @param event
     * @param url
     * @throws java.io.IOException
     */
    
      
    

    @FXML
            public void changeSceneToDetailedReclamationView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/Detailed.fxml"));
           // System.out.println(getClass().getResource("Interfaces/Detailed.fxml"));
            Parent tableViewParent = loader.load();
            
            Scene tableViewScene = new Scene(tableViewParent);
            
            //access the controller and call a method
            DetailedController controller = loader.getController();
         //   controller.initData(tableView.getSelectionModel().getSelectedItem());
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //GestionReclamation.setVisible(false);
       
       aff();
       
        
    }

    /**
     *
     */
   /* public void userClickedOnTable()
    {
        this.detailedReclamationViewButton.setDisable(false);
    
    }*/
      /*  public void changeSceneToDetailedPersonView(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PersonView.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        AccueilController controller = loader.getController();
        controller.initData(tableView.getSelectionModel().getSelectedItem());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    */
   
          Connection c=Maconnexion.getinstance().getConn();
    @FXML
    private void Reclamation(MouseEvent event) {
         GestionReclamation.setVisible(true);
    tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      //  editableCols();
    
    
    }
    
    public void initTable(){
        initCols();
    }
    
    public void initCols(){
          

      
        
       // editableCols(); 
    }
    public void aff(){
            
       id_reclamation.setCellValueFactory(new PropertyValueFactory<>("Claims_id"));

      
       nom_client.setCellValueFactory(new PropertyValueFactory<>("Claim_Productref"));
              message.setCellValueFactory(new PropertyValueFactory<>("Claim_Desc")); 
    
       date_reclamation.setCellValueFactory(new PropertyValueFactory<>("Claim_Date"));

    
  
       
       etat.setCellValueFactory(new PropertyValueFactory<>("eta"));   
       
      
   
       /*  try {
           Statement st17=c.createStatement();
       
            String req1="select id_user from user WHERE username = 'user01'";
            
            System.out.println("+++++++++++++++++"+req1);
            ResultSet rs19=st17.executeQuery(req1);
            String id_clients="";
            while(rs19.next())
            {
                System.out.println(rs19.getString(1));
                id_client=rs19.getInt(1);
               
            } 
            
             } catch (SQLException ex) {
            Logger.getLogger(InterfaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
          */
  
   
    
        ObservableList<Claims> rec = FXCollections.observableArrayList();
          Connection c=Maconnexion.getinstance().getConn();
        
        
       
           
     
            
    
    Statement stm;
       try {
              String req_conn="select claims_id,claim_productref,claim_desc,claim_date,claim_etat from claims ";
              
              stm=c.createStatement();
              ResultSet rs=stm.executeQuery(req_conn);
            System.out.println("///////////"+req_conn);
            Claims aa =new Claims();
              while(rs.next()){
                  if (rs.getInt(5)==0){
                        etasString="Non Traité";
                      
                  }else{
                      etasString="Traité";
                  }
                    aa.setEta(etasString);
                  
                 
                      
                      rec.add(new Claims(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getTimestamp(4),etasString));
                      System.out.println("++++++++"+etasString);
                  
                  // System.out.println("//"+(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getTimestamp(4),rs.getInt(5)));
                  System.out.println("///////////"+rs.getString(3));
                     
              }
              
          } 
          
          catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   
        tableView.setItems(rec);}
               
    

    private void addRecc(MouseEvent event) {
         try {
           
            Statement st1=c.createStatement();
            String req1="select id_user from user where pseudo='" +name1.getText()+"' ";
            ResultSet rs1=st1.executeQuery(req1);
            String id_client="";
            while(rs1.next())
            {
                System.out.println(rs1.getString(1));
                id_client=rs1.getString(1);
            } 
            
            
            
            
           Statement st2=c.createStatement();
            String req2="select id_user from user where nom_pepiniere='" +agent.getText()+"' ";
            ResultSet rs2=st2.executeQuery(req2);
            String id_agent="";
            while(rs2.next())
            {
                System.out.println(rs2.getString(1));
                id_agent=rs2.getString(1);
            } 
            
            
         Statement  st = c.createStatement();
        String req=("insert into reclamation (id_client,id_agent,nom_reclamation,nom_client,nom_pepiniere,tel,image,message) values ('"
                +id_client+"','"+id_agent+"','"+titre.getText()+"','"+name.getText()+"','"+agent.getText()+"','"+tél.getText()+"','"+path+"','"+mess.getText()+"')");
            System.out.println("-*********"+req);
    st.executeUpdate(req);
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    JOptionPane.showMessageDialog(null,"Ajouter avec succée");
    
    String imageU = new File(path).toURI().toString();
    Image imag = new Image(imageU);
        System.out.println("path");
ima.setImage(imag);
         
    } 
    
    @FXML
    private void ShowOnClick() {
          try {
            Claims re = (Claims) tableView.getSelectionModel().getSelectedItem();
           
      
           
            this.detailedReclamationViewButton.setDisable(false);
        } catch (Exception e) {
            
        }
             tableView.setEditable(true);
        
        nom_client.setCellFactory(TextFieldTableCell.forTableColumn());
       
        message.setCellFactory(TextFieldTableCell.forTableColumn());



   // private void detailedReclamationViewButton(ActionEvent event) {
    //}

    }

    @FXML
    private void LogOut(MouseEvent event) {
    }

    @FXML
     private void Supprimer_rec(ActionEvent event) throws SQLException  {
    Claims  a =  (Claims)tableView.getSelectionModel().getSelectedItem();
       ServiceReclamation serv = new ServiceReclamation();
        int i = tableView.getSelectionModel().getSelectedItem().getClaims_id();
       
         serv.supprimer(i);
            JOptionPane.showMessageDialog(null,"Supprimer avec succée");
         aff();
         
         
      /*   
         Statement st6=c.createStatement();
            String req5="select nbr from user where nom_pepiniere='" +f+"' ";
            ResultSet rs5=st6.executeQuery(req5);
          int nbr=0;
       
            while(rs5.next())
            {
                System.out.println("/////////////////////////"+rs5.getString(1));
          
              nbr=rs5.getInt(1);
              nbr--;
               System.out.println("//////////////"+nbr);
             
              
            } 
           
            System.out.println("//////////////"+nbr);
            PreparedStatement pt=c.prepareStatement("update user set nbr = ?  where nom_pepiniere=?");
                 User u= new User();
   
                 pt.setInt(1,nbr);
                 
                 pt.setString(2, f);
                 
                 pt.executeUpdate();
*/
         
    }
  
       
       
    
        



        
        
        
    
    
    public void imp(ActionEvent event){
        FileChooser fc = new FileChooser();
        File SelectedFiles = fc.showOpenDialog(null);
        if (SelectedFiles !=null){
           // Files.setText(SelectedFiles.);
            path= SelectedFiles.toString();
       }else{
            System.out.println("Invalid Files");
        }
    }

    private void addRec(ActionEvent event) throws SQLException {
         try {
           
            Statement st1=c.createStatement();
            String req1="select id_user from user where pseudo='" +name1.getText()+"' ";
            ResultSet rs1=st1.executeQuery(req1);
            String id_client="";
            while(rs1.next())
            {
                System.out.println(rs1.getString(1));
                id_client=rs1.getString(1);
            } 
            
            
            
            
           Statement st2=c.createStatement();
            String req2="select id_user from user where nom_pepiniere='" +agent.getText()+"' ";
            ResultSet rs2=st2.executeQuery(req2);
            String id_agent="";
            while(rs2.next())
            {
                System.out.println(rs2.getString(1));
                id_agent=rs2.getString(1);
            } 
            
            
         Statement  st = c.createStatement();
        String req=("insert into reclamation (id_client,id_agent,nom_reclamation,nom_client,nom_pepiniere,tel,image,message) values ('"
                +id_client+"','"+id_agent+"','"+titre.getText()+"','"+name.getText()+"','"+agent.getText()+"','"+tél.getText()+"','"+path+"','"+mess.getText()+"')");
            System.out.println("-*********"+req);
    st.executeUpdate(req);
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    JOptionPane.showMessageDialog(null,"Ajouter avec succée");
    
  
         Statement st6=c.createStatement();
            String req5="select nbr from user where nom_pepiniere='" +agent.getText()+"' ";
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
                 
                 pt.setString(2, agent.getText());
                 
                 pt.executeUpdate();

       aff();
        }
                 
 
    
   /* 
    String imageU = new File(path).toURI().toString();
    Image imag = new Image(imageU);
        System.out.println("path");
ima.setImage(imag);
         */
    
    @FXML
    private void handlebuttonAction(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/EnvoyerReclamation.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.setScene(new Scene(root1));
            stage.show();
          
      
           
          
         
        } catch (IOException ex) {
            Logger.getLogger(InterfaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
        @FXML
    private void handlebuttonActionMod(ActionEvent event){
        /* Claims  a =  (Claims)tableView.getSelectionModel().getSelectedItem();
            String n=  String.valueOf(a.getClaims_id());
            
           a.setClaim_Productref(String.valueOf(a.getClaim_Productref()));
            a.setClaim_Desc(String.valueOf(a.getClaim_Desc()));
           
            
            
           
                ServiceReclamation se = new ServiceReclamation();
                se.modifier(Integer.parseInt(n), a);
       JOptionPane.showMessageDialog(null,"Modifier avec succée");          
       aff();
        */
  
         
    }
   
        
        public void editableCols(){
          
    
    
    nom_client.setCellFactory(TextFieldTableCell.forTableColumn());
    nom_client.setOnEditCommit(e->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setClaim_Productref(e.getNewValue());
        
    });
    
    message.setCellFactory(TextFieldTableCell.forTableColumn());
    message.setOnEditCommit(e->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setClaim_Desc(e.getNewValue());
    });
    
   
    
    
    tableView.setEditable(true);
}
        
   
            
        }
                
                
                
    
    
    

    

