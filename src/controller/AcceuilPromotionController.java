/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Evenement;
import Entities.Participer;
import Entities.Promotion;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import service.ServiceEvenement;
import service.ServiceParticiper;
import service.ServicePromotion;

/**
 * FXML Controller class
 *
 * @author starmedia
 */
public class AcceuilPromotionController implements Initializable {

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
    private TextField rech;
    @FXML
    private ComboBox com;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ServicePromotion ser=new ServicePromotion();
            afficher_pro(ser.afficher_liste());
        } catch (MalformedURLException ex) {
            Logger.getLogger(AcceuilPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                  ObservableList<String> corech = 
            FXCollections.observableArrayList(
                "all", "nom_produit",
                "nom_agent"
                
                
            );
   
        com.setItems(corech);
            
    }    
    
    
    
    
    
    
    
    
    
    
    
     public void afficher_pro (List<Promotion> l) throws MalformedURLException
    {
    
        for (Promotion e : l) {
            
           ServicePromotion s=new ServicePromotion();
           System.out.println(e.getNom_promotion());
     String p=   s.afficher_p(e.getId_produit());
            
                     String st=p.replace("C:UsersstarmediaDesktop","C:\\\\Users\\starmedia\\Desktop\\");
                File f = new File(st);
                
           
                ImageView img1 = new ImageView(f.toURI().toURL().toString());      
                
                img1.setFitHeight(100);
                img1.setFitWidth(100);
                img1.setLayoutY(200);
                img1.setLayoutX(218/4);
            
            
            
            
            
            
 
                             Label nomm= new Label("Nom promo");
              nomm.setPrefWidth(100);
              nomm.setMinWidth(100);
              nomm.setMaxWidth(100); 
           nomm.setFont(Font.font ("Verdana", 15));
        
           
     
              nomm.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");
            
            
          Label nom_p= new Label(e.getNom_promotion());
              nom_p.setPrefWidth(100);
              nom_p.setMinWidth(100);
              nom_p.setMaxWidth(100);     
            
            //  nom_eve1.setStyle("  width: 150px; display: block;  margin-bottom: 10px; background-color: yellow;");
           nom_p.setFont(Font.font ("Tahoma", 20));
 //nom_p.setStyle(" -fx-font-weight: bold; -fx-font: 16px  ; -fx-text-fill: #0d0d0d; -fx-alignment: CENTER;   -fx-padding: 10;");
       nom_p.setStyle("-fx-alignment: CENTER; ");     
     Label nomp= new Label("Nom produit");
              nomp.setPrefWidth(100);
              nomp.setMinWidth(100);
              nomp.setMaxWidth(100);
              nomp.setFont(Font.font ("Verdana", 15));  
              
              nomp.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");
           
           Label nom_pr= new Label(e.getNom_produit());
              nom_pr.setPrefWidth(100);
              nom_pr.setMinWidth(100);
              nom_pr.setMaxWidth(100);     
           
                nom_pr.setStyle("-fx-alignment: CENTER; ");
                
                
                
                
                     Label nom_ag= new Label("Nom Agent");
              nom_ag.setPrefWidth(100);
              nom_ag.setMinWidth(100);
              nom_ag.setMaxWidth(100);
              nom_ag.setFont(Font.font ("Verdana", 15));  
              
              nom_ag.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");
           
           Label nom_a= new Label(e.getNom_agent());
              nom_a.setPrefWidth(100);
              nom_a.setMinWidth(100);
              nom_a.setMaxWidth(100);     
           
                nom_a.setStyle("-fx-alignment: CENTER; ");
                
        
              
              
           Label prix= new Label("Prix_hab");
              prix.setPrefWidth(100);
              prix.setMinWidth(100);
              prix.setMaxWidth(100);
              prix.setFont(Font.font ("Verdana", 15));  
              
              prix.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");     
              
              
              
              Label prix_h= new Label(e.getPrix_hab().toString());
              prix_h.setPrefWidth(100);
              prix_h.setMinWidth(100);
              prix_h.setMaxWidth(100);
              
              prix_h.setStyle("-fx-alignment: CENTER; ");
              
   Label prixx= new Label("Prix_promo");
              prixx.setPrefWidth(100);
              prixx.setMinWidth(100);
              prixx.setMaxWidth(100);   
           prixx.setFont(Font.font ("Verdana", 15));     
              
             prixx.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; "); 
          
                           Label prix_pr= new Label(e.getPrix_promo().toString());
              prix_pr.setPrefWidth(100);
              prix_pr.setMinWidth(100);
              prix_pr.setMaxWidth(100);
              
              prix_pr.setStyle("-fx-alignment: CENTER; ");

              
              
              Label pou= new Label("Pourcentage");
              pou.setPrefWidth(100);
              pou.setMinWidth(100);
              pou.setMaxWidth(100);   
              
         pou.setFont(Font.font ("Verdana", 15));        
              
         pou.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");
              
              
            String n=  String.valueOf(e.getPourcentage());
              
        Label pour= new Label(n);
              pour.setPrefWidth(100);
              pour.setMinWidth(100);
              pour.setMaxWidth(100);
    pour.setFont(Font.font ("Verdana", 30)); 
          pour.setStyle("-fx-alignment: CENTER; ");    
              

              
      Label dated= new Label("Date Début");
              dated.setPrefWidth(100);
              dated.setMinWidth(100);
              dated.setMaxWidth(100);
              
                dated.setFont(Font.font ("Verdana", 15));        
              
         dated.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");
         
              
              
              
              
          Label date_d= new Label(e.getDate_debut());
              date_d.setPrefWidth(100);
              date_d.setMinWidth(100);
              date_d.setMaxWidth(100);
              
              date_d.setStyle("-fx-alignment: CENTER; ");
              
    

        Label datef= new Label("Date Fin");
              datef.setPrefWidth(100);
              datef.setMinWidth(100);
              datef.setMaxWidth(100);
              
                datef.setFont(Font.font ("Verdana", 15));        
              
         datef.setStyle("-fx-background-color: #a19d9c;-fx-alignment: CENTER; ");
         
              
              
              
              
          Label date_f= new Label(e.getDate_fin());
              date_f.setPrefWidth(100);
              date_f.setMinWidth(100);
              date_f.setMaxWidth(100);
              
              date_f.setStyle("-fx-alignment: CENTER; ");
              
              
              
              
                
              
              
              
          Label des= new Label(e.getDescription());
              des.setPrefWidth(100);
              des.setMinWidth(100);
              des.setMaxWidth(100);
              
              des.setStyle("-fx-alignment: CENTER; ");
                            
              
              
              
 
               HBox hbox11=new HBox();    
              VBox vbox11=new VBox();
                 VBox vbox22=new VBox();
                  VBox vbox222=new VBox();
                 VBox vbox33=new VBox();
                 VBox vbox44=new VBox();
               VBox vbox55=new VBox();
                 vbox11.getChildren().addAll(nomm,nom_p);
                  vbox222.getChildren().addAll(nomp,nom_pr,nom_ag,nom_a);
                  vbox22.getChildren().addAll(prix,prix_h,prixx,prix_pr);
                 vbox33.getChildren().addAll(dated,date_d,datef,date_f);
                  vbox44.getChildren().addAll(des);
                   vbox55.getChildren().addAll(pou,pour);
        vbox11.setMinWidth(110);
       
        vbox22.setMinWidth(110); vbox222.setMinWidth(110);
        
        vbox33.setMinWidth(110);
        
        vbox44.setMinWidth(110);
         vbox55.setMinWidth(110);
         
                  vbox11.setStyle(cssLayout2);
           vbox22.setStyle(cssLayout2);vbox222.setStyle(cssLayout2);
           vbox33.setStyle(cssLayout2);
           vbox44.setStyle(cssLayout2);
             vbox55.setStyle(cssLayout2);
                  hbox11.setStyle(cssLayout);
                    hbox11.getChildren().addAll(img1,vbox11,vbox222,vbox55,vbox22,vbox33,vbox44);
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
       @FXML
    private void rechercher(KeyEvent event) {
        
       if(com.getSelectionModel().getSelectedItem().equals("all")){
           
               try {
            // TODO
                vbox.getChildren().clear();
            ServicePromotion ser=new ServicePromotion();
            afficher_pro(ser.afficher_liste());
        } catch (MalformedURLException ex) {
            Logger.getLogger(AcceuilPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
       }   
       
       
       
       
       
              if(com.getSelectionModel().getSelectedItem().equals("nom_produit")){
           
               try {
            // TODO
            ServicePromotion ser=new ServicePromotion();
               vbox.getChildren().clear();
            afficher_pro(ser.afficher_liste_pro(rech.getText()));
          
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(AcceuilPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
       } 
       
              
              
              
                            if(com.getSelectionModel().getSelectedItem().equals("nom_agent")){
           
               try {
            // TODO
            ServicePromotion ser=new ServicePromotion();
               vbox.getChildren().clear();
            //   int i=Integer.parseInt(rech.getText());
            afficher_pro(ser.afficher_liste_pou(rech.getText()));
          
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(AcceuilPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
       } 
              
       
        
    }
    
    
    
    
    
    
    
    
}
