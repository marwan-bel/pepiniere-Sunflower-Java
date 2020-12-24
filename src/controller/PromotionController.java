/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Evenement;
import Entities.Produit;
import Entities.Promotion;
import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.ServiceEvenement;
import service.ServicePromotion;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author starmedia
 */
public class PromotionController implements Initializable {
Connection c = Maconnexion.getinstance().getConn();
    @FXML
    private ImageView optionTailleParDefaut;
    @FXML
    private Button menuAcceuil;
    @FXML
    private Button menuProduit;
    @FXML
    private Button menuService;
    @FXML
    private Button menuEvenement;
    @FXML
    private Button menuReclamation;
    @FXML
    private Button menuForum;
    @FXML
    private Button menuDeconnexion;
    @FXML
    private ImageView optionTailleAgrandir;
    @FXML
    private AnchorPane zone_ajout_eve;
    @FXML
    private Label label_pro;
    @FXML
    private Label label2;
    @FXML
    private Label idm;
    @FXML
    private TextField prix_hab_pro;
    @FXML
    private Button Button_Valider;
    @FXML
    private DatePicker date_fin_pro;
    @FXML
    private TextArea description_pro;
    @FXML
    private ImageView img_view;
    @FXML
    private Label nom_produit;
    @FXML
    private TextField nom_pro;
    @FXML
    private DatePicker date_debut_pro;
    @FXML
    private TextField pourcentage_pro;
    @FXML
    private AnchorPane zone_modifier;
    @FXML
    private Label nom_produit_mod;
    @FXML
    private Label idpr;
    @FXML
    private TextField pourcentage_mod;
    @FXML
    private DatePicker date_debut_mod;
    @FXML
    private DatePicker date_fin_mod;
    @FXML
    private TextArea description_mod;
    @FXML
    private Button Valider_Mod;
    @FXML
    private Button Button_annuler;
    @FXML
    private TextField id_mo;
    @FXML
    private TextField prix_hab_mod;
    @FXML
    private TextField nom_mod;
    @FXML
    private TableView<Promotion> table;
    @FXML
    private TableView<Produit> table2;
    @FXML
    private TableColumn<?, ?> nommm;
    @FXML
    private TableColumn<?, ?> nom_categorieee;
    @FXML
    private TableColumn<?, ?> typeee;
    @FXML
    private TableColumn<?, ?> prixxx;
    @FXML
    private TableColumn<?, ?> quantiteee;
    @FXML
    private TableColumn<?, ?> descriptionnn;
    @FXML
    private TableColumn<?, ?> id_produittt;
    @FXML
    private TableColumn<?, ?> nom_produitt;
    @FXML
    private TableColumn<?, ?> nom_produittt;
    @FXML
    private TableColumn<?, ?> prix_habb;
    @FXML
    private TableColumn<?, ?> prix_promoo;
    @FXML
    private TableColumn<?, ?> pourcentagee;
    @FXML
    private TableColumn<?, ?> date_debutt;
    @FXML
    private TableColumn<?, ?> date_finn;
    @FXML
    private TableColumn<?, ?> descriptionn;
    @FXML
    private TableColumn<?, ?> id_promotionn;
    @FXML
    private Button ButtonSupprimer;
    @FXML
    private Button ButtonModifier;
    @FXML
    private TextField rech;
    @FXML
    private ComboBox comrech;
    @FXML
    private Button menuForum1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ServicePromotion s=new ServicePromotion();
        s.supprimer_auto();
        
        
                  ObservableList<String> corech = 
            FXCollections.observableArrayList(
                "all", "nom_promo",
                "nom_produit",
                "pourcentage"
                
            );
   
        comrech.setItems(corech);
        
        
         zone_modifier.setVisible(false); 
     
        Aff();
        Afff();
    }    

    @FXML
    private void Ajouter_pro(ActionEvent event) {
           
          ServicePromotion s=new ServicePromotion();
          int i= s.get_id_user();
              
          
           String nom_con =nom_pro.getText().toString();
           int  pou =Integer.parseInt(pourcentage_pro.getText());
           String des_con =description_pro.getText().toString();

           if(nom_con.equals("")){
               JOptionPane.showMessageDialog(null,"vérifier formulaire");return;
           }
               if(!(pou>0)&&(pou<100)){
               JOptionPane.showMessageDialog(null,"vérifier formulaire");return;
           }
                  if(des_con.equals("")){
               JOptionPane.showMessageDialog(null,"vérifier formulaire");return;
           }
    
                  
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            System.out.println(dateFormat.format(cal.getTime()));
          if( dateFormat.format(cal.getTime()).compareTo(date_debut_pro.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))   >0 )
           {
               JOptionPane.showMessageDialog(null,"vérifier date debut<date aujourd'hui");return;
           }
                  
          if( dateFormat.format(cal.getTime()).compareTo(date_fin_pro.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))   >0 )
          {
               JOptionPane.showMessageDialog(null,"vérifier date fin<date aujourd'hui");return;
          }           

          if(date_debut_pro.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).compareTo(date_fin_pro.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))<0)
          {
            float f=Float.parseFloat(prix_hab_pro.getText())-((Float.parseFloat(prix_hab_pro.getText())*Integer.parseInt(pourcentage_pro.getText()))/100);

            Promotion pr=new Promotion(Integer.parseInt(pourcentage_pro.getText()), i,Integer.parseInt(idpr.getText()) , nom_pro.getText(), date_debut_pro.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), date_fin_pro.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), description_pro.getText(),Float.parseFloat(prix_hab_pro.getText()) , f);
            ServicePromotion serv=new ServicePromotion();
            serv.ajouter(pr,Integer.parseInt(idpr.getText()) ,f);
        
            Aff();
            Afff();
        //JOptionPane.showMessageDialog(null,"Ajouter avec succée");
 
             Notifications no=Notifications.create()
                .title("Ajouter")
                .text("Promotion est ajouté avec succée")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                  
                    public void handle (ActionEvent event){
                        System.out.println("a");
                    }
                    
                });
           no.darkStyle();
           no.showConfirm();
        
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("succée");
        alert.setHeaderText(null);
        alert.setContentText("Ajouter avec succée");
        alert.showAndWait();
        
        }
          else
          {
              JOptionPane.showMessageDialog(null,"echec vérifier Date fin<Date debut");
          }
            
            
    }

    /**************************************************/ 
    
    @FXML
    private void valider_mod(ActionEvent event) {
        
           String nom_con =nom_mod.getText().toString();
           int  pou =Integer.parseInt(pourcentage_mod.getText());
           String des_con =description_mod.getText().toString();
           
           if(nom_con.equals("")){
               JOptionPane.showMessageDialog(null,"vérifier formulaire");return;
           }
               if(!(pou>0)&&(pou<100)){
               JOptionPane.showMessageDialog(null,"vérifier formulaire");return;
           }
                  if(des_con.equals("")){
               JOptionPane.showMessageDialog(null,"vérifier formulaire");return;
           }
    
                  
           DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           Calendar cal = Calendar.getInstance();
           System.out.println(dateFormat.format(cal.getTime()));
          if( dateFormat.format(cal.getTime()).compareTo(date_debut_mod.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))   >0 )
           {
                 JOptionPane.showMessageDialog(null,"vérifier date debut<date aujourd'hui");return;
           }
                  
          if( dateFormat.format(cal.getTime()).compareTo(date_fin_mod.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))   >0 )
          {
                 JOptionPane.showMessageDialog(null,"vérifier date fin<date aujourd'hui");return;
          }           
          
          if(date_debut_mod.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).compareTo(date_fin_mod.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))<0)
          {
            
              Promotion eve=new Promotion();
              ServicePromotion ser=new ServicePromotion();
   
              eve.setNom_promotion(nom_mod.getText());
              eve.setPrix_hab(Float.parseFloat(prix_hab_mod.getText()));
              eve.setPourcentage(Integer.parseInt(pourcentage_mod.getText()));
              eve.setDescription(description_mod.getText());
              eve.setDate_debut(date_debut_mod.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
              eve.setDate_fin(date_fin_mod.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      
              float f=Float.parseFloat(prix_hab_mod.getText())-((Float.parseFloat(prix_hab_mod.getText())*Integer.parseInt(pourcentage_mod.getText()))/100);
              eve.setPrix_promo(f);
      
             int i=Integer.parseInt(idm.getText());
             ser.modifier(i, eve);
                // JOptionPane.showMessageDialog(null,"Modifier avec succée");
             Notifications no=Notifications.create()
                .title("Ajouter")
                .text("Promotion est modifié avec succée")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                  
                    public void handle (ActionEvent event){
                        System.out.println("a");
                    }
                    
                });
           no.darkStyle();
           no.showConfirm();
        
            Aff(); 
            Afff();
               
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("succée");
             alert.setHeaderText(null);
             alert.setContentText("Modifier avec succée");
             alert.showAndWait();
                
        }else
        {
            JOptionPane.showMessageDialog(null,"echec vérifier Date");
        }
   
        
    }
    
/**************************************************/ 
    @FXML
    private void Annuler_mod(ActionEvent event) {
            zone_modifier.setVisible(false);
    }
/**************************************************/ 
    @FXML
    private void Supprimer_pro(ActionEvent event) 
    {
        Promotion S =  (Promotion)table.getSelectionModel().getSelectedItem();
        ServicePromotion serv = new ServicePromotion();
        int i = table.getSelectionModel().getSelectedItem().getId_promotion();
        serv.supprimer(i);
       //JOptionPane.showMessageDialog(null,"supprimer");
        Aff(); 
        Afff();
             Notifications no=Notifications.create()
                .title("Supprimer")
                .text("Promotion est supprimé avec succée")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                  
                    public void handle (ActionEvent event){
                        System.out.println("a");
                    }
                    
                });
           no.darkStyle();
           no.showConfirm();
        
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("succée");
            alert.setHeaderText(null);
            alert.setContentText("Supprimer avec succée");
            alert.showAndWait();
           
    }

    /**************************************************/ 
    
    @FXML
    private void Modifier_pro(ActionEvent event) 
    {
      zone_modifier.setVisible(true);
       
      Promotion gets=table.getSelectionModel().getSelectedItem();
      String n=  String.valueOf(gets.getId_promotion());
      idm.setText(n);
      nom_produit_mod.setText(gets.getNom_produit());
      nom_mod.setText(gets.getNom_promotion());
      prix_hab_mod.setText(gets.getPrix_hab().toString());
      String nn=  String.valueOf(gets.getPourcentage());
      pourcentage_mod.setText(nn); 
      description_mod.setText(gets.getDescription());
          
    }
    
    /**************************************************/ 

    @FXML
    private void rechercher(KeyEvent event)
    {
    
       nommm.setCellValueFactory(new PropertyValueFactory<>("nom_promotion"));
       id_promotionn.setCellValueFactory(new PropertyValueFactory<>("id_promotion"));
       nom_produitt.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
       prix_habb.setCellValueFactory(new PropertyValueFactory<>("prix_hab"));
       prix_promoo.setCellValueFactory(new PropertyValueFactory<>("prix_promo"));
       pourcentagee.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
       date_debutt.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
       date_finn.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
       descriptionn.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        ObservableList<Promotion> oblistt=FXCollections.observableArrayList();

          ServicePromotion s=new ServicePromotion();
          int i= s.get_id_user();
        
         try {
             Statement st =  c.createStatement();
             
              if(comrech.getSelectionModel().getSelectedItem().equals("all"))
              {
             
              String req = "select nom_promotion,prix_hab,prix_promo,pourcentage,date_debut,date_fin,description,id_promotion,nom_produit from promotion where id_agent= "+i+" ";
              ResultSet rs = st.executeQuery(req);
           
              while(rs.next())
              {
                 oblistt.add(new Promotion(rs.getString(1), rs.getFloat(2),rs.getFloat(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getString(9))); 
       
              }
              }
              
            if(comrech.getSelectionModel().getSelectedItem().equals("nom_promo")){
             
            String req = "select nom_promotion,prix_hab,prix_promo,pourcentage,date_debut,date_fin,description,id_promotion,nom_produit from promotion where nom_promotion LIKE '%" + rech.getText() + "%' and id_agent= "+i+" "; 
            ResultSet rs = st.executeQuery(req);
           
            while(rs.next())
            {
                  
            oblistt.add(new Promotion(rs.getString(1), rs.getFloat(2),rs.getFloat(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getString(9))); 
                  
              }
              }
               
            if(comrech.getSelectionModel().getSelectedItem().equals("nom_produit")){
             
            String req = "select nom_promotion,prix_hab,prix_promo,pourcentage,date_debut,date_fin,description,id_promotion,nom_produit from promotion where nom_produit LIKE '%" + rech.getText() + "%' and id_agent= "+i+" "; 
            ResultSet rs = st.executeQuery(req);
           
              while(rs.next()){
                  
            oblistt.add(new Promotion(rs.getString(1), rs.getFloat(2),rs.getFloat(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getString(9))); 
                  
              }
              }
                         
                         
            if(comrech.getSelectionModel().getSelectedItem().equals("pourcentage")){
             
            String req = "select nom_promotion,prix_hab,prix_promo,pourcentage,date_debut,date_fin,description,id_promotion,nom_produit from promotion where pourcentage LIKE '%" + rech.getText() + "%' and id_agent= "+i+" "; 
            ResultSet rs = st.executeQuery(req);
           
              while(rs.next()){
                  
            oblistt.add(new Promotion(rs.getString(1), rs.getFloat(2),rs.getFloat(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getString(9))); 
                  
              }
              }
              
              
          } 
          
 
         catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   
   table.setItems(oblistt);
 
        
    }
    
    /**************************************************/ 
    
     private void Aff()
     { 
       nommm.setCellValueFactory(new PropertyValueFactory<>("nom_promotion"));
       id_promotionn.setCellValueFactory(new PropertyValueFactory<>("id_promotion"));
       nom_produitt.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
       prix_habb.setCellValueFactory(new PropertyValueFactory<>("prix_hab"));
       prix_promoo.setCellValueFactory(new PropertyValueFactory<>("prix_promo"));
       pourcentagee.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
       date_debutt.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
       date_finn.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
       descriptionn.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        ObservableList<Promotion> oblistt=FXCollections.observableArrayList();

         try 
         {
             ServicePromotion s=new ServicePromotion();
             int i= s.get_id_user();
             Statement st =  c.createStatement();
             String req = "select nom_promotion,prix_hab,prix_promo,pourcentage,date_debut,date_fin,description,id_promotion,nom_produit from promotion where id_agent= " + i + " ";
             ResultSet rs = st.executeQuery(req);
           
              while(rs.next()){
                  
            oblistt.add(new Promotion(rs.getString(1), rs.getFloat(2),rs.getFloat(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getString(9))); 
                  
              }                 
          } 
          
          catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   

        
  table.setItems(oblistt);
             
    }
     
  /**************************************************/    
     
      private void Afff() { 
    
       
        
               nom_categorieee.setCellValueFactory(new PropertyValueFactory<>("Nom_categorie"));
               typeee.setCellValueFactory(new PropertyValueFactory<>("Type_prod"));
               prixxx.setCellValueFactory(new PropertyValueFactory<>("Prix_prod"));
               quantiteee.setCellValueFactory(new PropertyValueFactory<>("Qte_prod"));
               descriptionnn.setCellValueFactory(new PropertyValueFactory<>("Desc_prod"));
               id_produittt.setCellValueFactory(new PropertyValueFactory<>("Id_prod"));
               nom_produittt.setCellValueFactory(new PropertyValueFactory<>("Nom_prod"));
      
               ObservableList<Produit> oblisttt=FXCollections.observableArrayList();
    
  
         try 
         { 
              ServicePromotion s=new ServicePromotion();
              int i= s.get_id_user();
              System.out.println(i);
              Statement st =  c.createStatement();
              String req = "select nom_categorie,type,prix,quantite,description,id_produit,nom_produit from produit where id_agent= " + i + "";
              ResultSet rs = st.executeQuery(req);
           
              while(rs.next()){
                  oblisttt.add(new Produit( rs.getString(1), rs.getString(2),rs.getFloat(3), rs.getInt(4), rs.getString(5), rs.getInt(6),rs.getString(7))); 
                  
              }                 
          } 
          
          catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   

        
   table2.setItems(oblisttt);
               
                
    }
     
  /**************************************************/   
      
    @FXML
    private void aaa(MouseEvent event)
    {
      
      Promotion S =  (Promotion)table.getSelectionModel().getSelectedItem();
       
     int i = table.getSelectionModel().getSelectedItem().getId_promotion();
       
        try 
        {   
            Statement st1=c.createStatement();
            String req1="select nom_produit from promotion where id_promotion= "+i+"";
            ResultSet rs1=st1.executeQuery(req1);
            String nom="";
            
            while(rs1.next()){
                nom=rs1.getString(1);}         
               label2.setText(nom);} catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         }  
    }

 /**************************************************/    
    
    @FXML
    private void aa(MouseEvent event) {
      
       Produit S =  (Produit)table2.getSelectionModel().getSelectedItem();
       
        int i = table2.getSelectionModel().getSelectedItem().getId_prod();
        float ii =  table2.getSelectionModel().getSelectedItem().getPrix_prod();
        String nn=  String.valueOf(ii);
        prix_hab_pro.setText(nn);
        String n=  String.valueOf(i);
     
        idpr.setText(n);
        nom_produit.setText(S.getNom_prod());
  
    }

    @FXML
    private void btn_acceuil(MouseEvent event) {
    }

       @FXML
    private void btn_produit(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Prod_pep.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1));  
    stage.show();
    stage.setTitle("Login"); 
    ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void btn_service(MouseEvent event) {
    }

    @FXML
    private void btn_evenement(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Evenement.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1));  
    stage.show();
    stage.setTitle("Login"); 
    ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void btn_reclamation(MouseEvent event)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/InterfaceAgent.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1));  
    stage.show();
    stage.setTitle("Login"); 
    ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void btn_forum(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/interfaces/AjouterSujet.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1));  
    stage.show();
    stage.setTitle("Login"); 
    ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void btn_promotion(KeyEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Promotion.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1));  
    stage.show();
    stage.setTitle("Login"); 
    ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void btn_promotion(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Promotion.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1));  
    stage.show();
    stage.setTitle("Login"); 
    ((Node)(event.getSource())).getScene().getWindow().hide();
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
