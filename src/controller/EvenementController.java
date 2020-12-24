/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Evenement;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.stage.FileChooser;

import javax.swing.JOptionPane;
import service.ServiceEvenement;
import java.sql.*;
import java.text.DateFormat;
import java.util.Calendar;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utils.*;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.ServiceParticiper;

/**
 * FXML Controller class
 *
 * @author starmedia
 */
public class EvenementController implements Initializable {
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
    private Button Button_annuler;
    @FXML
    private Button ButtonSupprimer;
    @FXML
    private Button ButtonModifier;
    @FXML
    private Button ajouter_img;
    @FXML
    private Button Button_Valider;
    @FXML
    private ImageView optionTailleAgrandir;
    @FXML
    private AnchorPane zone_modifier;
    @FXML
    private TextField id_mo;
    @FXML
    private TextField max;
    @FXML
    private TextField rech;
    @FXML
    private TextField nom_mod;
    @FXML
    private TextField lieu_mod;
    @FXML
    private TextField maxsm;
    @FXML
    private ComboBox type_mod;
    @FXML
    private DatePicker date_debut_mod;
    @FXML
    private DatePicker date_fin_mod;
    @FXML
    private TextArea description_mod;
    @FXML
    private TextField nom_eve;
    @FXML             
    private TextField lieu_eve;
    @FXML
    private DatePicker date_debut_eve;
    @FXML
    private DatePicker date_fin_eve;
    @FXML
    private TextArea description_eve;
    @FXML
    private ComboBox type_eve;
    @FXML
    private ComboBox comrech;    
    @FXML
    private ImageView img_view;
    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<?, ?> maxs;
    @FXML
    private TableColumn<?, ?> nomm;
    @FXML
    private TableColumn<?, ?> nom_admin;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> lieu;
    @FXML
    private TableColumn<?, ?> date_debut;
    @FXML
    private TableColumn<?, ?> date_fin;   
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> id_evenement;
    private String path;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableView<Evenement> table_nom;
    @FXML
    private Label num;
    @FXML
    private Button menuForum1;
    @FXML
    private AnchorPane zone_ajout_eve;
    @FXML
    private Label label_eve;
    @FXML
    private Button Valider_Mod;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Aff();
 
        zone_modifier.setVisible(false);
        ObservableList<String> corech =
                FXCollections.observableArrayList(
                        "user", "all", "nom",
                        "pseudo",
                        "lieu",
                        "type",
                        "date_debut",
                        "date_fin",
                        "description"
                );
        
        comrech.setItems(corech);
        ObservableList<String> typeSujet =
                FXCollections.observableArrayList(
                        "Animation",
                        "visite guidé",
                        "compétition"
                );
        
        type_eve.setItems(typeSujet);
        ObservableList<String> typemo =
                FXCollections.observableArrayList(
                        "Animation",
                        "visite guidé",
                        "compétition"
                );
        
        type_mod.setItems(typemo);
        
          
    }
    
    /**************************************************/ 

    @FXML
    private void Ajouter_eve(ActionEvent event) 
    {
        
           ServiceEvenement s=new ServiceEvenement();
           int i= s.get_id_user();
           
           String nom_con =nom_eve.getText().toString();
           String lieu_con =lieu_eve.getText().toString();
           String des_con =description_eve.getText().toString();
 
           if(nom_con.equals("")){
               JOptionPane.showMessageDialog(null,"vérifier formulaire");return;
           }
               if(lieu_con.equals("")){
               JOptionPane.showMessageDialog(null,"vérifier formulaire");return;
           }
                  if(des_con.equals("")){
               JOptionPane.showMessageDialog(null,"vérifier formulaire");return;
           }
    
                  
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            System.out.println(dateFormat.format(cal.getTime()));
            
            if( dateFormat.format(cal.getTime()).compareTo(date_debut_eve.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))   >0 )
            {
                 JOptionPane.showMessageDialog(null,"vérifier date debut<date aujourd'hui");return;
            }
                  
          if( dateFormat.format(cal.getTime()).compareTo(date_fin_eve.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))   >0 )
            {
             JOptionPane.showMessageDialog(null,"vérifier date fin<date aujourd'hui");return;
            }           
            System.out.println(Integer.parseInt(max.getText()));
           
           if(date_debut_eve.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).compareTo(date_fin_eve.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))<0)
           {
             Evenement e=new Evenement(Integer.parseInt(max.getText()) ,path,i, nom_eve.getText(),(String) type_eve.getSelectionModel().getSelectedItem(), lieu_eve.getText(), date_debut_eve.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), date_fin_eve.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), description_eve.getText());
             ServiceEvenement serv=new ServiceEvenement();
             serv.ajouter(e);
             Aff();
 
             String imageURI = new File(path).toURI().toString(); 
             Image image = new Image(imageURI);
             System.out.println(path);
             img_view.setImage(image);

             Notifications no=Notifications.create()
                .title("Ajouter")
                .text("Evenement est ajouté avec succée")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>()
                {
                  
             public void handle (ActionEvent event)
             {
               System.out.println("a");
             }
                    
                });
             no.darkStyle();
             no.showConfirm();
        
             JOptionPane.showMessageDialog(null,"Ajouter avec succée");
             } 
             else
             {
              JOptionPane.showMessageDialog(null,"vérifier Date debut>Date fin");
             }
    }
    
    /**************************************************/ 
    
    @FXML
    private void choose()   
    {
        
        FileChooser f=new FileChooser();
        File f2=   f.showOpenDialog(null);
        String x=f2.toString();
         
         path=x;
    }
        
   /**************************************************/ 
    
    private void Aff()
    { 
      
       nomm.setCellValueFactory(new PropertyValueFactory<>("nom_evenement"));
       id_evenement.setCellValueFactory(new PropertyValueFactory<>("id_evenement"));
       nom_admin.setCellValueFactory(new PropertyValueFactory<>("pseudo_admin"));
       type.setCellValueFactory(new PropertyValueFactory<>("type"));
       lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
       date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
       date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        maxs.setCellValueFactory(new PropertyValueFactory<>("max"));
       
        ObservableList<Evenement> oblist=FXCollections.observableArrayList();
    
         try
         {
             ServiceEvenement s=new ServiceEvenement();
             int i= s.get_id_user();
             
             Statement st =  c.createStatement();
             String req = "select nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,id_evenement,max from evenement";
             ResultSet rs = st.executeQuery(req);
          
              while(rs.next())
              {
                  oblist.add(new Evenement(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9))); 
                  System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+ rs.getString(7));
              }                 
          } 
          
          catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   
      table.setItems(oblist);
    }
    
    /**************************************************/ 
    
    @FXML
    private void Supprimer_eve(ActionEvent event) 
    {
              Evenement S =  (Evenement)table.getSelectionModel().getSelectedItem();
              ServiceEvenement serv = new ServiceEvenement();
              int i = table.getSelectionModel().getSelectedItem().getId_evenement();
              serv.supprimer(i);
              Aff(); 
           
              Notifications no=Notifications.create()
                .title("Supprimer")
                .text("Evenement est supprimé avec succée")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction((ActionEvent event1) -> {
                    System.out.println("a");
              });
             no.darkStyle();
             no.showConfirm();
        
             JOptionPane.showMessageDialog(null,"supprimer");
   
    }
    
    /**************************************************/ 
     @FXML
     private void Modifier_eve(ActionEvent event) 
     {
   
        zone_modifier.setVisible(true);
       
        Evenement gets=table.getSelectionModel().getSelectedItem();
        String n=  String.valueOf(gets.getId_evenement());
        id_mo.setText(n);
        nom_mod.setText(gets.getNom_evenement());
        lieu_mod.setText(""+gets.getLieu());
        type_mod.setValue(gets.getType());
        description_mod.setText(gets.getDescription());

    }
    
     /**************************************************/ 
     
    @FXML
    private void valider_mod(ActionEvent event) 
    {
           String nom_con =nom_mod.getText();
           String lieu_con =lieu_mod.getText();
           String des_con =description_mod.getText();

           if(nom_con.equals("")){
               JOptionPane.showMessageDialog(null,"vérifier formulaire");return;
           }
               if(lieu_con.equals("")){
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
            
                 Evenement eve=new Evenement();
                 ServiceEvenement ser=new ServiceEvenement();
                 eve.setNom_evenement(nom_mod.getText());
                 eve.setLieu(lieu_mod.getText());
                 eve.setDescription(description_mod.getText());
                 eve.setType((String)type_mod.getSelectionModel().getSelectedItem());
                 eve.setDate_debut(date_debut_mod.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                 eve.setDate_fin(date_fin_mod.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                 eve.setMax(Integer.parseInt(maxsm.getText()));
      
                 ser.modifier(Integer.parseInt(id_mo.getText()), eve);
               
                 Aff();
                 JOptionPane.showMessageDialog(null,"Modifier avec succée");    
   
                 Notifications no=Notifications.create()
                .title("Modifier")
                .text("Evenement est modifié avec succée")
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
             

            }
            else
            {
                JOptionPane.showMessageDialog(null,"echec vérifier Date fin<Date debut");
            }
  
    }
    @FXML
    private void Annuler_mod(ActionEvent event) 
    {
           zone_modifier.setVisible(false);
    }
        
     @FXML
    public void rechercher(KeyEvent key) {
        
          nomm.setCellValueFactory(new PropertyValueFactory<>("nom_evenement"));
          id_evenement.setCellValueFactory(new PropertyValueFactory<>("id_evenement"));
          nom_admin.setCellValueFactory(new PropertyValueFactory<>("pseudo_admin"));
          type.setCellValueFactory(new PropertyValueFactory<>("Type"));
          lieu.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
          date_debut.setCellValueFactory(new PropertyValueFactory<>("Date_debut"));
          date_fin.setCellValueFactory(new PropertyValueFactory<>("Date_fin"));
          description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        
          ObservableList<Evenement> oblist=FXCollections.observableArrayList();
    
          try {
              ServiceEvenement s=new ServiceEvenement();
              int i= s.get_id_user();
              Statement st =  c.createStatement();
                         
             if(comrech.getSelectionModel().getSelectedItem().equals("user"))
             {
                 String req = "select nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,id_evenement from evenement where id_admin= " + i + "";
                 ResultSet rs = st.executeQuery(req);
                 while(rs.next())
                 {
                    oblist.add(new Evenement(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8))); 
                    System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+ rs.getString(7));
                 }
             }
             if(comrech.getSelectionModel().getSelectedItem().equals("all"))
             {
                 String req = "select nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,id_evenement from evenement "; 
                 ResultSet rs = st.executeQuery(req);
                  while(rs.next())
                  {
                    oblist.add(new Evenement(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8))); 
                    System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+ rs.getString(7));
                  }
             }
             if(comrech.getSelectionModel().getSelectedItem().equals("nom"))
             {
                 String req = "select nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,id_evenement from evenement where nom_evenement LIKE '%" + rech.getText() + "%'"; 
                 ResultSet rs = st.executeQuery(req);
                 while(rs.next())
                 {
                   oblist.add(new Evenement(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8))); 
                    System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+ rs.getString(7));
                 }
             }
              if(comrech.getSelectionModel().getSelectedItem().equals("type"))
             {
                 String req = "select nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,id_evenement from evenement where type LIKE '%" + rech.getText() + "%'"; 
                 ResultSet rs = st.executeQuery(req);
                 while(rs.next())
                 {
                    oblist.add(new Evenement(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8))); 
                    System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+ rs.getString(7));
                 }
             }
               if(comrech.getSelectionModel().getSelectedItem().equals("pseudo"))
             {
                 String req = "select nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,id_evenement from evenement where pseudo_admin LIKE '%" + rech.getText() + "%'"; 
                 ResultSet rs = st.executeQuery(req);
                 while(rs.next())
                 {
                    oblist.add(new Evenement(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8))); 
                    System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+ rs.getString(7));
                 }
             }
              if(comrech.getSelectionModel().getSelectedItem().equals("lieu"))
             {
                    String req = "select nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,id_evenement from evenement where lieu LIKE '%" + rech.getText() + "%'"; 
                    ResultSet rs = st.executeQuery(req);
                    while(rs.next())
                    {
                    oblist.add(new Evenement(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8))); 
                    System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+ rs.getString(7));
                    }
             }
               if(comrech.getSelectionModel().getSelectedItem().equals("description"))
             {
                 String req = "select nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,id_evenement from evenement where description LIKE '%" + rech.getText() + "%'"; 
                 ResultSet rs = st.executeQuery(req);
                 while(rs.next())
                 {
                    oblist.add(new Evenement(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8))); 
                    System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+ rs.getString(7));
                 }
             }
                if(comrech.getSelectionModel().getSelectedItem().equals("date_debut"))
             {
                 String req = "select nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,id_evenement from evenement where date_debut LIKE '%" + rech.getText() + "%'"; 
                 ResultSet rs = st.executeQuery(req);
                 while(rs.next())
                 {
                    oblist.add(new Evenement(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8))); 
                    System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+ rs.getString(7));
                 }
             }
                   if(comrech.getSelectionModel().getSelectedItem().equals("date_fin"))
             {
                 String req = "select nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,id_evenement from evenement where ddate_fin LIKE '%" + rech.getText() + "%'"; 
                 ResultSet rs = st.executeQuery(req);
                 while(rs.next())
                 {
                    oblist.add(new Evenement(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8))); 
                    System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+ rs.getString(7));
                 }
             }                
                
          } 
          
          catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   
               table.setItems(oblist);
        
    }
    
    /**************************************************/ 
  @FXML
  private void affphoto(MouseEvent event) {  
        
    try {
        Evenement S =  (Evenement)table.getSelectionModel().getSelectedItem();
        int ii = table.getSelectionModel().getSelectedItem().getId_evenement();
        
        Statement st =c.createStatement();
        String req = "select path from evenement where id_evenement="+ii+"";
        ResultSet rs = st.executeQuery(req);
        String pa="";
        while(rs.next())
        {
            pa=rs.getString(1);
        }   System.out.println(pa);
        
        String az=  pa.replace("C:UsersstarmediaDesktop","C:\\\\Users\\starmedia\\Desktop\\");
  // System.out.println(az);
        String imageURI = new File(az).toURI().toString(); 

       Image image = new Image(imageURI);
       img_view.setImage(image);
 
       Aff_nom();
    } catch (SQLException ex) {
        Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
    }  
  }
  
  /******************************************/
  
  
   private void Aff_nom() { 
        
        table_nom.getItems().clear();
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      
        ObservableList<Evenement> oblistt=FXCollections.observableArrayList();

         try 
         {
             ServiceEvenement s=new ServiceEvenement();
             int i= s.get_id_user();
            
             Evenement gets=table.getSelectionModel().getSelectedItem();
             int id_u= gets.getId_evenement();
                 // Participer p=new Participer(id_u, i);
             ServiceParticiper p=new ServiceParticiper();
             String nn=  String.valueOf(p.total_par(id_u));
             num.setText(nn);
                 
             Statement st = c.createStatement();
             String req = "select id_user from participer where id_evenement= " + id_u + "";
             ResultSet rs = st.executeQuery(req);
          
              while(rs.next())
              {
                     Statement stt =c.createStatement();
                     String reqq = "select nom from user where id_user= " + rs.getInt(1)+ "";
                     ResultSet rss = stt.executeQuery(reqq);
                   
                     while(rss.next())
                     {
                       oblistt.add(new Evenement(rss.getString(1))); 
                       System.out.println(rss.getString(1));
                     }  
             
                  table_nom.setItems(oblistt); 
              }                 
          } 
          
          catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   
             
    
   }
       
    
   
        
}
