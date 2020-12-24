/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceReclamation;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author Marouan
 */
public class InterfaceAgentController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     */
    private String Traité;
     @FXML
    private TableView<Reclamation>tableViews;
    @FXML
    private TableColumn<Reclamation,String> id_reclamation;
    @FXML
    private TableColumn<Reclamation,String> nom_reclamation;
    @FXML
    private TableColumn<Reclamation,String> nom_client;
     @FXML
    private TableColumn<Reclamation,String> tcp;
      @FXML
    private TableColumn<Reclamation,String> tp;
    @FXML
    private TableColumn<Reclamation,String> date_reclamation;
    @FXML
    private TableColumn<Reclamation,String> message;
     @FXML
    private TableColumn<Reclamation,String> rp;
     @FXML
    private TableColumn<Reclamation,String> etat;
        @FXML
    private AnchorPane GestionReclamation;
        
            @FXML
    private TextArea reponse;

    @FXML
    private Button ButtonModifier;
    @FXML
    private Button detailedPersonViewButton;
    @FXML
    private TableColumn<?, ?> imag;
            
    /**
     *
     * @param event
     */
   
     Connection c=Maconnexion.getinstance().getConn();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
         GestionReclamation.setVisible(true);
       id_reclamation.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));

       nom_reclamation.setCellValueFactory(new PropertyValueFactory<>("nom_reclamation"));  
       nom_client.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
       tcp.setCellValueFactory(new PropertyValueFactory<>("nom_pepiniere"));
       date_reclamation.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
     // fich.setCellValueFactory(new PropertyValueFactory<>("image"));
      tp.setCellValueFactory(new PropertyValueFactory<>("tel"));
       message.setCellValueFactory(new PropertyValueFactory<>("message"));   
       rp.setCellValueFactory(new PropertyValueFactory<>("reponse"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));  
       
        ObservableList<Reclamation> rec = FXCollections.observableArrayList();
            Connection conn=Maconnexion.getinstance().getConn();
    
    Statement stm;
       try {
              String req_conn="select id_reclamation,nom_client,nom_reclamation,nom_pepiniere,tel,date_reclamation,message,reponse,etat from reclamation";
              
              stm=conn.createStatement();
              ResultSet rs=stm.executeQuery(req_conn);
            System.out.println("///////////"+req_conn);
              while(rs.next()){
                  rec.add(new Reclamation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getTimestamp(6),rs.getString(7),rs.getString(8),rs.getString(9))); 
                   System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getTimestamp(6)+rs.getString(7)+rs.getString(8)+rs.getString(9));
              }                
          } 
          
          catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   
               tableViews.setItems(rec);
              
       }
  
        @FXML
         public void changeSceneToDetailedReclamationView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Detailed.fxml"));
            
            Parent tableViewParent = loader.load();
            
            Scene tableViewScene = new Scene(tableViewParent);
            
            //access the controller and call a method
            DetailedController controller = loader.getController();
            controller.initData(tableViews.getSelectionModel().getSelectedItem());
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            System.out.println("eroro"+ex.getMessage());
        }
    }
    
     
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {

       
        }
    
     
         
    private void Reclamation(MouseEvent event) {
         GestionReclamation.setVisible(true);
       id_reclamation.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));

       nom_reclamation.setCellValueFactory(new PropertyValueFactory<>("nom_reclamation"));  
       nom_client.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
       tcp.setCellValueFactory(new PropertyValueFactory<>("nom_pepiniere"));
       date_reclamation.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
     // fich.setCellValueFactory(new PropertyValueFactory<>("image"));
      tp.setCellValueFactory(new PropertyValueFactory<>("tel"));
       message.setCellValueFactory(new PropertyValueFactory<>("message"));   
       rp.setCellValueFactory(new PropertyValueFactory<>("reponse"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));  
       
        ObservableList<Reclamation> rec = FXCollections.observableArrayList();
            Connection conn=Maconnexion.getinstance().getConn();
    
    Statement stm;
       try {
              String req_conn="select id_reclamation,nom_client,nom_reclamation,nom_pepiniere,tel,date_reclamation,message,reponse,etat from reclamation";
              
              stm=conn.createStatement();
              ResultSet rs=stm.executeQuery(req_conn);
            System.out.println("///////////"+req_conn);
              while(rs.next()){
                  rec.add(new Reclamation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getTimestamp(6),rs.getString(7),rs.getString(8),rs.getString(9))); 
                   System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getTimestamp(6)+rs.getString(7)+rs.getString(8)+rs.getString(9));
              }                
          } 
          
          catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   
               tableViews.setItems(rec);
              
       }

     @FXML
        private void Modifier_rec(ActionEvent event) throws SQLException  {
            
            Reclamation  a =  (Reclamation)tableViews.getSelectionModel().getSelectedItem();
            String n=  String.valueOf(a.getId_reclamation());
            String x = Traité;
        id_reclamation.setText(n);
        
            a.setReponse(reponse.getText());
                
           
         
             
               a.setEtat("Traité");
                
                
                ServiceReclamation se = new ServiceReclamation();
                se.modifierR(Integer.parseInt(id_reclamation.getText()), a);
                 a.setEtat(x);
                 
         Statement st11=c.createStatement();
            String req7="select email from reclamation where id_reclamation='" +a.getId_reclamation()+"' ";
            ResultSet rs11=st11.executeQuery(req7);
            String email="";
                while(rs11.next())
            {
                System.out.println(rs11.getString(1));
                email=rs11.getString(1);
            } 
        
                
                
                System.out.println("+++++++++++++++++++"+email);
        service.Emailer e = new service.Emailer(email, "Consulter la reponse de votre Reclamation");
        e.sendmail();
                
}
  
    
}

    

