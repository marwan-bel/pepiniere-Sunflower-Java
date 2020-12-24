/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Event;

import Entities.Evenement;
import Entities.Participer;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.EmailSend;
import service.ServiceEvenement;
import service.ServiceParticiper;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author starmedia
 */
public class AcceuilEvenementController implements Initializable {
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
    private ImageView img_view;
    @FXML
    private VBox vbox;
    @FXML
    private HBox hbox;
    @FXML
    private ImageView img;
    @FXML
    private VBox vbox1;
    @FXML
    private Label nom_eve;
    @FXML
    private Label pseudo_admin;
    @FXML
    private VBox vbox2;
    @FXML
    private Label type;
    @FXML
    private Label lieu;
    @FXML
    private VBox vbox3;
    @FXML
    private Label date_debut;
    @FXML
    private Label date_fin;
    @FXML
    private VBox vbox4;
    @FXML
    private Label description;
    String cssLayout = "-fx-border-color: #2f4f4f;\n" +
                   "-fx-padding: 15;\n" +
                   "-fx-spacing: 10;\n" ;
String cssLayout2 = "-fx-border-color: #2f4f4f;\n" +
                    "-fx-padding: 5;\n" +
                    "-fx-spacing: 5;\n" ;
  
    
    @FXML
    private Button supprimer_filtre;
        @FXML
    private RadioButton btn_animation;
                @FXML
    private RadioButton btn_competition;
                        @FXML
    private RadioButton btn_visite;
                                @FXML
    private RadioButton btn_dispo;
                                        @FXML
    private RadioButton btn_non;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ServiceEvenement ser=new ServiceEvenement();
            afficher_eve(ser.afficher_liste());
            
              filter(btn_visite);
              filter(btn_animation);
                filter(btn_competition);
              filter(btn_dispo);
              filter(btn_non);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(AcceuilEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }    
    
    
    
     public void afficher_eve (List<Evenement> l) throws MalformedURLException
    {
        //  Connection conn=MyConnection.getInstance().getConn();
        //  vbox.getChildren().clear();
        // Statement stm;
        for (Evenement e : l) {
            //       ServiceEvenement sp=new ServiceEvenement();
            //   stm=conn.createStatement();
            
            //hbox.setAlignment(Pos.CENTER);
            
            
                   String st=e.getPath().replace("C:UsersstarmediaDesktop","C:\\\\Users\\starmedia\\Desktop\\");
                File f = new File(st);
           
                ImageView img1 = new ImageView(f.toURI().toURL().toString());      
                
                img1.setFitHeight(100);
                img1.setFitWidth(100);
                img1.setLayoutY(200);
                img1.setLayoutX(218/4);
            
            
            
            
            
          Label nom_eve1= new Label(e.getNom_evenement());
              nom_eve1.setPrefWidth(100);
              nom_eve1.setMinWidth(100);
              nom_eve1.setMaxWidth(100);     
            
            //  nom_eve1.setStyle("  width: 150px; display: block;  margin-bottom: 10px; background-color: yellow;");
           nom_eve1.setFont(Font.font ("Tahoma", 20));
 nom_eve1.setStyle(" -fx-font-weight: bold;-fx-font: 16px  ;-fx-text-fill:#0d0d0d; -fx-alignment: CENTER;   -fx-padding: 10;");
           
                   Label nomm= new Label("Ajoute par :");
              nomm.setPrefWidth(100);
              nomm.setMinWidth(100);
              nomm.setMaxWidth(100); 
           nomm.setFont(Font.font ("Verdana", 15));
        
           
     
              nomm.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");
           
           Label pseudo_admin1= new Label(e.getPseudo_admin());
              pseudo_admin1.setPrefWidth(100);
              pseudo_admin1.setMinWidth(100);
              pseudo_admin1.setMaxWidth(100);     
           
                pseudo_admin1.setStyle("-fx-alignment: CENTER; ");
        
              
              
               Label typen= new Label("Type");
              typen.setPrefWidth(100);
              typen.setMinWidth(100);
              typen.setMaxWidth(100);
              typen.setFont(Font.font ("Verdana", 15));  
              
              typen.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");
              
              Label type1= new Label(e.getType());
              type1.setPrefWidth(100);
              type1.setMinWidth(100);
              type1.setMaxWidth(100);
              
              type1.setStyle("-fx-alignment: CENTER; ");
              
      Label lieun= new Label("Lieu");
              lieun.setPrefWidth(100);
              lieun.setMinWidth(100);
              lieun.setMaxWidth(100);
              lieun.setFont(Font.font ("Verdana", 15));  
              
              lieun.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");
              
        Label lieu1= new Label(e.getLieu());
              lieu1.setPrefWidth(100);
              lieu1.setMinWidth(100);
              lieu1.setMaxWidth(100);
  
          lieu1.setStyle("-fx-alignment: CENTER; ");    
              
 Label date_debutn= new Label("Date Début");
              date_debutn.setPrefWidth(100);
              date_debutn.setMinWidth(100);
              date_debutn.setMaxWidth(100);   
           date_debutn.setFont(Font.font ("Verdana", 15));     
              
             date_debutn.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; "); 
              
         Label date_debut1= new Label(e.getDate_debut());
              date_debut1.setPrefWidth(100);
              date_debut1.setMinWidth(100);
              date_debut1.setMaxWidth(100);
           
              date_debut1.setStyle("-fx-alignment: CENTER; "); 
              
Label date_finn= new Label("Date Fin");
              date_finn.setPrefWidth(100);
              date_finn.setMinWidth(100);
              date_finn.setMaxWidth(100);   
              
         date_finn.setFont(Font.font ("Verdana", 15));        
              
         date_finn.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");
              
              
              
              
              
          Label date_fin1= new Label(e.getDate_fin());
              date_fin1.setPrefWidth(100);
              date_fin1.setMinWidth(100);
              date_fin1.setMaxWidth(100);
              
              date_fin1.setStyle("-fx-alignment: CENTER; ");
              
           Label description1= new Label(e.getDescription());
              description1.setPrefWidth(100);
              description1.setMinWidth(100);
              description1.setMaxWidth(100);
              
              
                            
          Label par= new Label("participons");
              par.setPrefWidth(100);
              par.setMinWidth(100);
              par.setMaxWidth(100);
              
                par.setFont(Font.font ("Verdana", 15));        
              
         par.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");
         
         
                   Label ma= new Label("MAX");
              ma.setPrefWidth(100);
              ma.setMinWidth(100);
              ma.setMaxWidth(100);
              
                ma.setFont(Font.font ("Verdana", 15));      
 
              
         ma.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");
         
         String na=  String.valueOf(e.getMax());
                   Label maa= new Label(na);
              maa.setPrefWidth(100);
              maa.setMinWidth(100);
              maa.setMaxWidth(100);
              
              maa.setStyle("-fx-alignment: CENTER; ");
         
              
               ServiceParticiper paa=new ServiceParticiper();
               int t=  paa.total_par(e.getId_evenement());
               String n=  String.valueOf(t);
                     Label to= new Label(n);
              to.setPrefWidth(100);
              to.setMinWidth(100);
              to.setMaxWidth(100);
                to.setStyle("-fx-alignment: CENTER; ");
              
              
              
              
              
             Button   add=new Button();
             
                                     ServiceEvenement s=new ServiceEvenement();
                  int i= s.get_id_user();
           
       int d=e.getId_evenement();
       
       ServiceParticiper pa=new ServiceParticiper();
       if(pa.rech(d, i)!=0){ add.setText("annuler");
      
       }else{add.setText("Participer");}
             
               
                add.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                 
                        ServiceEvenement s=new ServiceEvenement();
                  int i= s.get_id_user();
           
       int d=e.getId_evenement();
       
       ServiceParticiper pa=new ServiceParticiper();
       Participer pp=new Participer(d,i);
          if(pa.rech(d, i)==0){
               int tt=  pa.total_par(e.getId_evenement());
              if(tt<e.getMax()){
                  
                         pa.ajouter(pp); 
                add.setText("annuler");
                
                       
                        ServiceEvenement ss=new ServiceEvenement();
                  String ii= ss.get_mail(i);
                  String iii= ss.get_nom(i);
                
                                       EmailSend ee= new EmailSend(ii,iii);
        ee.mail();
      

                
                  Notifications no=Notifications.create()
                .title("Evenement")
                .text("je suis intéressée")
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
        
                
                
                
                   int t=  pa.total_par(e.getId_evenement());
               String n=  String.valueOf(t);
                to.setText(n);
              }else{
                   JOptionPane.showMessageDialog(null,"Désole MAX atteint");  
              }
              
              
          }
          else
          {
              pa.supprimer(pa.rech(d, i));
              add.setText("Participer");
                   int t=  pa.total_par(e.getId_evenement());
               String n=  String.valueOf(t);
                to.setText(n);
          }
          
                        
             }
     } );
              
                
              
              
               HBox hbox11=new HBox();    
              VBox vbox11=new VBox();
                 VBox vbox22=new VBox();
                 VBox vbox33=new VBox();
                 VBox vbox44=new VBox();
                 VBox vbox55=new VBox();
                 vbox11.getChildren().addAll(nom_eve1,nomm,pseudo_admin1);
                  vbox22.getChildren().addAll(typen,type1,lieun,lieu1);
                 vbox33.getChildren().addAll(date_debutn,date_debut1,date_finn,date_fin1);
                  vbox44.getChildren().addAll(description1);
     vbox55.getChildren().addAll(add,par,to,ma,maa);
        vbox11.setMinWidth(110);
       
        vbox22.setMinWidth(110);
        
        vbox33.setMinWidth(110);
        
        vbox44.setMinWidth(110);
         vbox55.setMinWidth(110);
                  vbox11.setStyle(cssLayout2);
           vbox22.setStyle(cssLayout2);
           vbox33.setStyle(cssLayout2);
           vbox44.setStyle(cssLayout2);
            // vbox55.setStyle(cssLayout2);
                  hbox11.setStyle(cssLayout);
                    hbox11.getChildren().addAll(img1,vbox11,vbox22,vbox33,vbox44,vbox55);
            vbox.getChildren().addAll(hbox11);
                  
       /*        HBox hblab2=new HBox();
            VBox vblab1=new VBox();
            VBox vblab2=new VBox();
            vblab1.getChildren().addAll(nom_pep);
            vblab1.getChildren().addAll(nom);
            hblab2.getChildren().addAll(vblab1);
            vbox.getChildren().addAll(hblab2);
*/
            //  vbox1.getChildren().addAll(nom_eve);
            //   hbox.getChildren().addAll(img,vbox1,vbox2,vbox3,vbox4);
            // vbox.getChildren().addAll(hbox);
        }
    
    }
    
    
    /*******************/
     
     
     
     
     
     
     
     
     
      public void filter(RadioButton c){ 
            Connection conn=Maconnexion.getinstance().getConn();
         ObservableList<Evenement> oblist = FXCollections.observableArrayList();
         supprimer_filtre.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) { 
                 try {
                     vbox.getChildren().clear();
                     supp_filtr();
                     ServiceEvenement sp=new ServiceEvenement(); 
                     afficher_eve(sp.afficher_liste());
                      
                 } catch (MalformedURLException ex) {
                     Logger.getLogger(AcceuilEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                 }}});  
         c.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) { 
                 try{  
                     vbox.getChildren().clear();
                     supp_filtr();
                     c.setSelected(true);
                     if (!c.isPressed()){  
                         ResultSet rs = conn.createStatement().executeQuery("select id_evenement,id_admin,nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,path,max from evenement where type ='"+c.getText()+"'   "); 
                     while (rs.next()) {
                         oblist.add(new Evenement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11)));
                 
                     
                     
                     }
                             
                         afficher_eve(oblist);
                         oblist.clear();
                 }  } catch (SQLException ex) {
                         System.out.println("erreur"+ex.getMessage());} catch (MalformedURLException ex) {
                     Logger.getLogger(AcceuilEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                 }}});    
    }
       
    private void supp_filtr() {
        btn_animation.setSelected(false);
        btn_competition.setSelected(false);
        btn_visite.setSelected(false);
        btn_dispo.setSelected(false);
        btn_non.setSelected(false);
    
         
    }
     
    
    
    
    
    
    
    
    
    
    
    
    
    
}
