/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Commande;
import Entities.Commentaire;
import Entities.Favoris;
import Entities.Produit;
import Entities.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.DAOLigneCommande;
import service.DaoCommande;
import service.DaoUsers;
import service.ServiceCommentaire;
import service.ServiceSujet;
import service.Services_favoris;
import service.Services_produit;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class AccueilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Label temp;
    private RadioButton btn_arbres;
    private RadioButton btn_dec;
    private RadioButton btn_fruit;
    private RadioButton btn_arbustes;
    private RadioButton btn_arr;
    private RadioButton btn_net;
    private RadioButton btn_plant;
    private RadioButton btn_orgm;
    private RadioButton btn_org;
    private RadioButton btn_min;
    private TextField txt_rech; 
    String cssLayout = "-fx-border-color: #2f4f4f;\n" +
                   "-fx-padding: 15;\n" +
                   "-fx-spacing: 10;\n" ;

String cssLayout2 = "-fx-border-color: #2f4f4f;\n" +
                    "-fx-padding: 5;\n" +
                    "-fx-spacing: 5;\n" ;
    private Label notif;
    Connection conn=Maconnexion.getinstance().getConn();
    
    private AnchorPane PanierAnchor;
    private AnchorPane FinalisserAnchor;
    private Label nomprnm;
    private Label adressuser;
    private Label teluser;
    private AnchorPane FinalisserAnchor1;
    private Label totale;
    private JFXTextField coupon;
    private JFXButton btn_coupon;
    private JFXButton btn_confirmation;
    private AnchorPane zone_aff_prods;
    private AnchorPane zone_aff_fav;    
    @FXML   private AnchorPane zone_acceuil_prods;
    @FXML    private HBox hbprom;
    @FXML    private Label open_promotion;
    @FXML    private HBox hbnv;
    @FXML   private Label open_promotion1;
String txt =     " -fx-background-color: transparent ;\n" ;
    private VBox vboo1;
    private VBox vboo;
    private VBox vboo2;
    private AnchorPane anchorForum;
    private AnchorPane espaceCom;
    private TextField nom_sujet;
    private TextArea description;
    private Button confirmer;
    private JFXTextArea textareacom;
    private VBox Vcomm;
    private TextArea commentaires;
    private Button signal_comment;
    private Button supp_comment;
    private Button modif_comment;
    private Label id_sujet;
    private Button Planter;
    private Button planter1;
    private Button planter2;
    private Button planter3;
    private Button Entretien;
    private Button Entretien1;
    private Button Entretien2;
    private Button Conseils;
    private AnchorPane anchorsujet;
    private VBox listSujet;
       
 private int id_cc; 
    private int id_sj;
    private Button voir_sujet;
    private JFXTextField txt_nomcl;
    private JFXTextField txt_prncl;
    private JFXTextField txt_adrcl;
    private JFXTextField txt_numcl;
    private JFXTextField txt_mailcl;
 
   @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
                Date actuelle = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dat = dateFormat.format(actuelle);
                temp.setText(dat);     
    
           
        try {
            notif();
        } catch (SQLException ex) {
            System.out.println("erreur notif"+ex.getMessage());
        }
          Services_produit sp=new Services_produit(); 
        
        try {
            afficher_accueil_promotion(sp.Afficher_Produit());
            afficher_accueil_newest(sp.Afficher_Produit());
  
           
        
        } catch (MalformedURLException ex) {
            System.out.println("affficher accueil "+ex.getMessage());
        }  

    }
     //*********Gazzah firas*******************\\
    private void LogOut(MouseEvent event) throws IOException {
        DaoUsers us=new DaoUsers();
        us.LogoutUser();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1));  
    stage.show();
    stage.setTitle("Login"); 
    ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    //********mouheddine Hamila******************\\
    private void rech_prod_cl(KeyEvent event) throws MalformedURLException {
        
 
        vboo.getChildren().clear();
        Statement stm;
       
            Services_produit sp=new Services_produit();
            for (Produit aIterator : sp.Afficher_Produit())
               {if (aIterator.getNom_prod().contains(txt_rech.getText())){
                   
              TextField  nomprod = new TextField(aIterator.getNom_prod().toUpperCase());
              nomprod.setPrefWidth(300);
              nomprod.setStyle(txt+"\n-fx-font-size: 16px;-fx-font-weight:bold;");
              nomprod.setMinWidth(300);
              nomprod.setMaxWidth(300);
              nomprod.setEditable(false); 
              
              TextArea  desc = new TextArea(aIterator.getDesc_prod());
              
               desc.setStyle( "\n-fx-font-size: 12px; -fx-text-inner-color: black;-fx-font-weight:bold;");
              desc.setPrefWidth(350);
              desc.setBorder(null);
              desc.setEditable(false); 
              
             
              TextField  prix=  new TextField(""+aIterator.getPrix_prod()+"DT");
              prix.setPrefWidth(150);
              prix.setStyle(txt+"\n -fx-font-size: 14px;-fx-font-weight:bold;");
              prix.setMinWidth(150);
              prix.setMaxWidth(150);     
              prix.setEditable(false);
              TextField  dispo;
              if (aIterator.getQte_prod()>0)
              {dispo=  new TextField("Disponible");
              dispo.setStyle(txt+"\n -fx-font-size: 14px;-fx-font-weight:bold;-fx-text-inner-color: green;");
              }
              
              else{ dispo=  new TextField("Indisponible");
              dispo.setStyle(txt+"\n -fx-font-size: 14px;-fx-text-inner-color: red; -fx-font-weight:bold;");
              }
              dispo.setPrefWidth(150);
              dispo.setMinWidth(150);
              
              dispo.setMaxWidth(150);     
              dispo.setEditable(false);  
             TextField nom_pep= new TextField(sp.get_nom_pep(aIterator));
              nom_pep.setPrefWidth(150);
              nom_pep.setStyle(txt+"\n -fx-font-size: 12px;-fx-font-weight:bold;");
              nom_pep.setMinWidth(150);
              nom_pep.setMaxWidth(150);     
              nom_pep.setEditable(false);
              String st=aIterator.getImg().replaceAll(":",":\\\\\\\\");
                File f = new File(st);
           
                ImageView imgprod5 = new ImageView(f.toURI().toURL().toString());      
                
                imgprod5.setFitHeight(100);
                imgprod5.setFitWidth(100);
                imgprod5.setLayoutY(200);
                imgprod5.setLayoutX(218/4);
                /**//**//**//**//**///--------------FIRAS GAZZAH--------AJOUTER AU PANIER--------\\
              Button  addtopanier=new Button();
                 ImageView iv2 = new ImageView("file:C:\\cart.png");
                iv2.setFitWidth(35);
                iv2.setPreserveRatio(true);
                iv2.setSmooth(true);
                iv2.setCache(true);
                addtopanier.setStyle("-fx-cursor : hand;");
                addtopanier.setGraphic(iv2);
                addtopanier.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                         try {       
                        AddToPanier(aIterator.getId_prod(),aIterator.getPrix_prod());
                        notif();
                    } catch (SQLException ex) {
                        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
             }
     } );
              Button  addtofav=new Button();
               ImageView iv = new ImageView("file:C:\\favorite.png");
                iv.setFitWidth(35);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                addtofav.setGraphic(iv);
                addtofav.setStyle("-fx-cursor : hand;");
                addtofav.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    Services_favoris sf=new Services_favoris();
                    Favoris f=new Favoris(aIterator,2);
                    sf.Ajouter_favoris(aIterator.getId_prod(), f);
             }
     } );
                   
            HBox hblab2=new HBox();
            VBox vblab1=new VBox();
            VBox vblab2=new VBox();
            VBox vblab3=new VBox();
            VBox vblab4=new VBox();
            hblab2.setStyle(cssLayout);
            vblab2.getChildren().addAll(nomprod,desc);
            vblab3.getChildren().addAll(nom_pep,prix,dispo);
            vblab4.setSpacing(5);
            vblab4.getChildren().addAll(addtofav,addtopanier);
            hblab2.getChildren().addAll(imgprod5,vblab2,vblab3,vblab4);
            vboo.getChildren().addAll(hblab2); 
            }
         
               }
                                  
                 
    }
 
    
    //********mouheddine Hamila******************\\
    public void filter(RadioButton c){ 
         
         ObservableList<Produit> oblist = FXCollections.observableArrayList();
        
         c.setOnAction((ActionEvent event) -> {
             try{
                 
                 supp_filtr();
                 c.setSelected(true);
                 if (!c.isPressed()){
                     ResultSet rs1 = conn.createStatement().executeQuery("SELECT * FROM Produit WHERE  nom_categorie='"+c.getText()+"'"); 
                     while (rs1.next()) {
                         oblist.add(new Produit(rs1.getInt(1),rs1.getInt(2),rs1.getInt(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getFloat(8),rs1.getInt(9),rs1.getString(10),rs1.getString(11)));
                     }
                     
                     afficher_prods(oblist);
                     oblist.clear();
                 }  } catch (SQLException ex) {
                     System.out.println("erreur"+ex.getMessage());} catch (MalformedURLException ex) {
                         
                     }
         });    
    }

    //********mouheddine Hamila******************\\
     private void supp_filtr() {
        btn_arbres.setSelected(false);
        btn_arbustes.setSelected(false);
        btn_arr.setSelected(false);
        btn_dec.setSelected(false);
        btn_fruit.setSelected(false);
        btn_min.setSelected(false);
        btn_net.setSelected(false);
        btn_org.setSelected(false);
        btn_orgm.setSelected(false);
        btn_plant.setSelected(false);
         
    }
     
 
       //*********Gazzah firas*******************\\
      public void AddToPanier( int prod_id, float montant) throws SQLException
      {
           DAOLigneCommande lcmd=new DAOLigneCommande();
           DaoUsers user =new DaoUsers();
                    int id_user=0,x=0;
                    id_user=user.ConectedUser();
                    x=lcmd.CountPanier(id_user);
                    notif.setText(Integer.toString(x));
                        boolean existe=lcmd.verifContenu(id_user, prod_id);
                      
                        if (existe)
                        lcmd.AjouterLigneCmd(prod_id,1,id_user,montant);
                        else {JOptionPane.showMessageDialog(null,"Produit deja existe dans votre panier merci de la consulter");}
      }
      
     //********mouheddine Hamila******************\\
       public void main_produit() throws MalformedURLException{
       
            Services_produit sp=new Services_produit();
            afficher_prods(sp.Afficher_Produit());
            filter(btn_arbres);
            filter(btn_arbustes);
            filter(btn_arr);
            filter(btn_dec);
            filter(btn_fruit);
            filter(btn_min);
            filter(btn_net);
            filter(btn_org);
            filter(btn_orgm);
            filter(btn_plant);
       
        
    }
      
//********mouheddine Hamila******************\\
      public void afficher_prods (List<Produit> l) throws MalformedURLException{
        vboo.getChildren().clear();
        
       
            for (Produit aIterator : l)
               {
                Services_produit sp=new Services_produit(); 
              TextField  nomprod = new TextField(aIterator.getNom_prod().toUpperCase());
              nomprod.setPrefWidth(300);
              nomprod.setStyle(txt+"\n-fx-font-size: 16px;-fx-font-weight:bold;");
              nomprod.setMinWidth(300);
              nomprod.setMaxWidth(300);
              nomprod.setEditable(false); 
              
              TextArea  desc = new TextArea(aIterator.getDesc_prod());
              
               desc.setStyle( "\n-fx-font-size: 12px; -fx-text-inner-color: black;-fx-font-weight:bold;");
              desc.setPrefWidth(350);
              desc.setBorder(null);
              desc.setEditable(false); 
              
             
              TextField  prix=  new TextField(""+aIterator.getPrix_prod()+"DT");
              prix.setPrefWidth(150);
              prix.setStyle(txt+"\n -fx-font-size: 14px;-fx-font-weight:bold;");
              prix.setMinWidth(150);
              prix.setMaxWidth(150);     
              prix.setEditable(false);
              TextField  dispo;
              if (aIterator.getQte_prod()>0)
              {dispo=  new TextField("Disponible");
              dispo.setStyle(txt+"\n -fx-font-size: 14px;-fx-font-weight:bold;-fx-text-inner-color: green;");
              }
              
              else{ dispo=  new TextField("Indisponible");
              dispo.setStyle(txt+"\n -fx-font-size: 14px;-fx-text-inner-color: red; -fx-font-weight:bold;");
              }
              dispo.setPrefWidth(150);
              dispo.setMinWidth(150);
              
              dispo.setMaxWidth(150);     
              dispo.setEditable(false);  
             TextField nom_pep= new TextField(sp.get_nom_pep(aIterator));
              nom_pep.setPrefWidth(150);
              nom_pep.setStyle(txt+"\n -fx-font-size: 12px;-fx-font-weight:bold;");
              nom_pep.setMinWidth(150);
              nom_pep.setMaxWidth(150);     
              nom_pep.setEditable(false);
              String st=aIterator.getImg().replaceAll(":",":\\\\\\\\");
                File f = new File(st);
           
                ImageView imgprod5 = new ImageView(f.toURI().toURL().toString());      
                
                imgprod5.setFitHeight(100);
                imgprod5.setFitWidth(100);
                imgprod5.setLayoutY(200);
                imgprod5.setLayoutX(218/4);
                /**//**//**//**//**///--------------FIRAS GAZZAH--------AJOUTER AU PANIER--------\\
              Button  addtopanier=new Button();
                 ImageView iv2 = new ImageView("file:C:\\cart.png");
                iv2.setFitWidth(35);
                iv2.setPreserveRatio(true);
                iv2.setSmooth(true);
                iv2.setCache(true);
                addtopanier.setStyle("-fx-cursor : hand;");
                addtopanier.setGraphic(iv2);
                addtopanier.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            DAOLigneCommande lcmd=new DAOLigneCommande();
                            boolean existe=lcmd.Verifqte(aIterator.getId_prod());
                            if(existe)
                            {
                                AddToPanier(aIterator.getId_prod(),aIterator.getPrix_prod());
                                notif();
                            }
                            else{JOptionPane.showMessageDialog(null,"Produit épuisé de notre stock");}
                        } catch (SQLException ex) {
                            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   
             }
     } );
              Button  addtofav=new Button();
               ImageView iv = new ImageView("file:C:\\favorite.png");
                iv.setFitWidth(35);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                addtofav.setGraphic(iv);
                addtofav.setStyle("-fx-cursor : hand;");
                addtofav.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    Services_favoris sf=new Services_favoris();
                    Favoris f=new Favoris(aIterator,2);
                    sf.Ajouter_favoris(aIterator.getId_prod(), f);
             }
     } );
                   
            HBox hblab2=new HBox();
            VBox vblab1=new VBox();
            VBox vblab2=new VBox();
            VBox vblab3=new VBox();
            VBox vblab4=new VBox();
            hblab2.setStyle(cssLayout);
            vblab2.getChildren().addAll(nomprod,desc);
            vblab3.getChildren().addAll(nom_pep,prix,dispo);
            vblab4.setSpacing(5);
            vblab4.getChildren().addAll(addtofav,addtopanier);
            hblab2.getChildren().addAll(imgprod5,vblab2,vblab3,vblab4);
            vboo.getChildren().addAll(hblab2); 
            }
       
    
    }
      
      
      
 //*********Gazzah firas*******************\\
  public void afficher_LigneCmd (List<Produit> l) throws MalformedURLException
    {Produit p = new Produit();
        vboo1.getChildren().clear();
        Statement stm;
         float x=0;
    try { 
            for (Produit aIterator : l)
               {
               
                //stm=conn.createStatement();   
                 TextField  nomprod1 = new TextField(aIterator.getNom_prod().toUpperCase());
              nomprod1.setPrefWidth(300);
              nomprod1.setStyle(txt+"\n-fx-font-size: 16px;-fx-font-weight:bold;-fx-alignement:center;");
              nomprod1.setMinWidth(300);
              nomprod1.setMaxWidth(300);
              nomprod1.setEditable(false); 
                TextArea  desc1 = new TextArea(aIterator.getDesc_prod());
              desc1.setPrefWidth(350);
              desc1.setBorder(null); 
              desc1.setStyle("\n-fx-font-size: 12px;-fx-font-weight:bold; -fx-alignement:center;");
              desc1.setEditable(false); 
              TextArea  prix1=  new TextArea("Prix Unitaire: \n"+aIterator.getPrix_prod()+" DT");
              prix1.setPrefWidth(150);
              prix1.setPrefWidth(50);
              prix1.setStyle(txt+"\n-fx-font-size: 14px;-fx-font-weight:bold;-fx-alignement:center;");
              prix1.setMinWidth(150);
              prix1.setMaxWidth(150); 
              prix1.setMaxHeight(50);
              prix1.setMinHeight(50);
              prix1.setEditable(false);
               
                x=x+aIterator.getPrix_prod();
                //Ajouter image
                String st=aIterator.getImg().replaceAll(":",":\\\\\\\\");         
                File f = new File(st);
                ImageView imgprod5 = new ImageView(f.toURI().toURL().toString());      
                imgprod5.setFitHeight(100);
                imgprod5.setFitWidth(100);
                imgprod5.setLayoutY(200);
                imgprod5.setLayoutX(218/4); 
                //creation des vbox et hbox pour contenir la list des produits
                
                //******Ajouter boutton pour augmenter la quantiter
                Button addqte=new Button();
                
                addqte.setStyle("-fx-background-color: #32CD32;"+" -fx-cursor : hand;");
                ImageView iv2 = new ImageView("file:C:\\plus.png");
                iv2.setFitWidth(20);
                iv2.setPreserveRatio(true);
                iv2.setSmooth(true);
                iv2.setCache(true);
                addqte.setGraphic(iv2);
                String y=Float.toString(x);
                Label prix2= new Label();
                Label qte=new Label();
                try {
                DAOLigneCommande sp=new DAOLigneCommande();
                        
                        int pri=sp.newprix(aIterator.getId_prod());
                       
                               prix2.setText("Total:\n "+pri+" DT");
                               prix2.setStyle("\n-fx-font-size: 14px;-fx-font-weight:bold;-fx-alignement:center;");
                    } catch (SQLException ex) {
                        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                 int qtt1=0;
                DAOLigneCommande sp=new DAOLigneCommande();
                qtt1=sp.CalculeQte(aIterator.getId_prod());
                qte.setText("Quantité:"+qtt1);
                qte.setStyle("\n-fx-font-size: 14px;-fx-font-weight:bold;-fx-alignement:center;");
                addqte.setOnAction((ActionEvent event) -> 
                {
                  
                    try {
                       
                            boolean existe=sp.Verifqte2(aIterator.getId_prod());
                            int qtt=0;
                            if(existe)
                            {
                                sp.EditQteLigneCmd(aIterator.getId_prod());
                                int i=sp.newprix(aIterator.getId_prod());
                                qtt=sp.CalculeQte(aIterator.getId_prod());
                                qte.setText("Quantité:"+qtt);
                                qte.setStyle("\n-fx-font-size: 14px;-fx-font-weight:bold;-fx-alignement:center;");
                                prix2.setText("Total:\n "+i+" DT");
                                prix2.setStyle("\n-fx-font-size: 14px;-fx-font-weight:bold;-fx-alignement:center;");
                            }
                             else{JOptionPane.showMessageDialog(null,"Vous pouvez pas ajouter de quantité notre stock est limite");}
                    } catch (SQLException ex) {
                        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }   
);
                  
                //******Ajouter boutton pour deminiuer la quantiter
                
                Button removeqte=new Button();
                removeqte.setStyle("-fx-background-color: #FF0000;"+" -fx-cursor : hand;");
                    ImageView iv = new ImageView("file:C:\\moin.png");
                    iv.setFitWidth(20);
                    iv.setPreserveRatio(true);
                    iv.setSmooth(true);
                    iv.setCache(true);
                    removeqte.setGraphic(iv);
                    removeqte.setOnAction((ActionEvent event) -> 
                    {
                    try {
                         int qtt=0;
                            boolean existe=sp.Verifqte3(aIterator.getId_prod());
                            if(existe)
                            {
                        sp.EditQteLigneCmd2(aIterator.getId_prod());
                        int i=sp.newprix(aIterator.getId_prod());
                        qtt=sp.CalculeQte(aIterator.getId_prod());
                                qte.setText("Quantité:"+qtt);
                                qte.setStyle("\n-fx-font-size: 14px;-fx-font-weight:bold;-fx-alignement:center;");
                        prix2.setText("Total:\n "+i+" DT");
                         prix2.setStyle("\n-fx-font-size: 14px;-fx-font-weight:bold;-fx-alignement:center;");
                            }
                    } catch (SQLException ex) {
                        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 
                    });
                //******Ajouter boutton pour supprimer de la ligne du commande
                
               
                    ImageView iv3 = new ImageView("file:C:\\garbage.png");
                    iv3.setFitWidth(50);
                    iv3.setPreserveRatio(true);
                    iv3.setSmooth(true);
                    iv3.setCache(true);
                    iv3.setStyle("-fx-cursor : hand;");
                   
                    iv3.setOnMouseClicked((event) -> {
                        try {
                        
                        sp.deleteLigneCmd(aIterator.getId_prod());
                        afficher_LigneCmd(sp.Afficher_LigneCmd());
                       notif();
                        
                    
                    } catch (MalformedURLException | SQLException ex) {
                        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    });
                    
  //-----------------------------------------FIRAS GAZZAH----AJOUTER AU PANIER------------\\
                
                HBox hblab2=new HBox();
                VBox vblab1=new VBox();
                VBox vblab2=new VBox();
                VBox vblab3=new VBox();
                VBox vblab4=new VBox();
                HBox hblab3=new HBox();
                Label li =new Label("                                    ");
                hblab2.setStyle(cssLayout);
               vblab3.setSpacing(5);
                vblab1.getChildren().addAll(imgprod5);
                vblab2.getChildren().addAll(nomprod1,desc1);
                hblab2.setBorder(Border.EMPTY);
                vblab2.setAlignment(Pos.CENTER);
                vblab3.setAlignment(Pos.CENTER);
                vblab3.getChildren().addAll(prix1,qte);
                vblab4.getChildren().addAll(addqte,qte,removeqte);
                vblab4.setSpacing(10);
                hblab2.setSpacing(10);
                hblab3.getChildren().addAll(li,iv3);
                hblab2.getChildren().addAll(vblab1,vblab2,vblab3,vblab4,prix2,hblab3);
                vboo1.getChildren().addAll(hblab2); 
                vboo1.setAlignment(Pos.CENTER);
            }
        } catch (SQLException ex) { System.out.println("erreur"+ex.getMessage());
    }
    
    }
  
  //*********Gazzah firas*******************\\
    public void notif() throws SQLException
    {
        int id_user=0,x=0;
        DaoUsers user =new DaoUsers();
        id_user=user.ConectedUser();
        DAOLigneCommande lcmd=new DAOLigneCommande();
        x=lcmd.CountPanier(id_user);
        notif.setText(Integer.toString(x));

    }
    
     //*********Gazzah firas*******************\\
    private void CheckPanier(MouseEvent event) {
         try {
        
            anchorForum.setVisible(false);
             FinalisserAnchor.setVisible(false);
            PanierAnchor.setVisible(true);
            zone_aff_fav.setVisible(false);
            zone_acceuil_prods.setVisible(false);
            zone_aff_prods.setVisible(false);
        FinalisserAnchor1.setVisible(false);
            DAOLigneCommande sp=new DAOLigneCommande();
            afficher_LigneCmd(sp.Afficher_LigneCmd());
        } catch (MalformedURLException ex) {
            System.out.println("check panier: "+ex);
       
       
    }
    }
 //*********Gazzah firas*******************\\
       boolean remisee=false;
       public void finalCmd()
       {
            try {
              
        DAOLigneCommande lcmd =new DAOLigneCommande();
       boolean existe= lcmd.VerifPanier();
       if(existe){
            float somme=0;
            FinalisserAnchor.setVisible(true);
            PanierAnchor.setVisible(false);
              
            zone_aff_fav.setVisible(false);
            zone_acceuil_prods.setVisible(false);
            zone_aff_prods.setVisible(false);
        FinalisserAnchor1.setVisible(false);
            ResultSet rs = conn.createStatement().executeQuery("SELECT adresse,nom,prenom,sexe FROM user where id=1");           
            while (rs.next()) {rs.getString(1);
                    nomprnm.setText(rs.getString(2)+" "+rs.getString(3));
                    adressuser.setText(rs.getString(1));
                    teluser.setText("+216 "+rs.getString(4));
            }
            
            ResultSet rs2 = conn.createStatement().executeQuery("SELECT montant FROM ligne_cmd");           
            while (rs2.next()) {
                    somme=somme+rs2.getFloat(1);
                   
            }
              String y=Float.toString(somme);
              final float montants=somme;
            totale.setText("Totale="+y+" DT");
          
           float x=0;
            final float sum= somme*0.1f;
            final float prix3=montants;
            final float prix4=montants-sum;
            btn_coupon.setOnAction((ActionEvent event2) -> 
                    { 
                        if("123456".equals(coupon.getText()))
                            
                    totale.setText("Totale="+prix4+" DT  félicitation vous avez un remise de 10%");
                    remisee=true; 
                   
                    });
              
               DaoUsers user = new DaoUsers();
                   
                    DaoCommande cmd=new DaoCommande();
            btn_confirmation.setOnAction((ActionEvent event2) -> 
                    {
                       
                try {
                    final int id=1;
                    if(remisee)
                    {
                    Commande commande=new Commande(id,"commande non confirmer","paiment a la livraison",user.gestUserAdr(),prix4);
                    cmd.AjouterCmd(commande);
                    }
                    else{
                         Commande commande=new Commande(id,"commande non confirmer","paiment a la livraison",user.gestUserAdr(),montants);
                    cmd.AjouterCmd(commande);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                       
                 
                    });
       }else{JOptionPane.showMessageDialog(null,"Il faut ajouter des produits au Panier pour finaliser votre commande");}
        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    private void finaliserCmd(MouseEvent event) {
       finalCmd();
                        
    }
//********mouheddine Hamila******************\\
    private void show_fav(MouseEvent event) throws MalformedURLException {
        zone_aff_fav.setVisible(true);
        zone_aff_prods.setVisible(false);
        zone_acceuil_prods.setVisible(false);
        FinalisserAnchor1.setVisible(false);
        FinalisserAnchor.setVisible(false);
        PanierAnchor.setVisible(false);
       anchorForum.setVisible(false);
        Services_produit sp=new Services_produit();
        afficher_favs(sp.Afficher_Produit()); 
    }
    //********mouheddine Hamila******************\\
    public void afficher_favs (List<Produit> l) throws MalformedURLException{

        vboo2.getChildren().clear();
        Statement stm;
        Services_favoris sf=new Services_favoris();
        Services_produit sp=new Services_produit();
        for (Favoris aIterator : sf.Afficher_favoris()){
             
            int id_prod=aIterator.getId_prod();
            Produit p=sf.get_prod(id_prod);
            TextField  nomprod = new TextField(p.getNom_prod().toUpperCase());
            //nomprod.fontProperty().
            nomprod.setPrefWidth(100);
            nomprod.setStyle(txt+"\n-fx-font-size: 16px;-fx-font-weight:bold;");
            nomprod.setMinWidth(100);
            nomprod.setMaxWidth(100);
            nomprod.setEditable(false);
            
            TextArea  desc = new TextArea(p.getDesc_prod());
            desc.setPrefWidth(350);
            desc.setStyle("\n-fx-font-size: 12px; -fx-text-inner-color: black;-fx-font-weight:bold;");
            desc.setEditable(false);
            
            TextField  prix=  new TextField(""+p.getPrix_prod()+"DT");
            prix.setPrefWidth(150);
            prix.setMinWidth(150);
            prix.setStyle(txt+"\n-fx-font-size: 14px;-fx-font-weight:bold;");
            prix.setMaxWidth(150);
            prix.setEditable(false);
            
            TextField  dispo;
            if (p.getQte_prod()>0)
              {dispo=  new TextField("Disponible");
              dispo.setStyle(txt+"\n -fx-font-size: 14px;-fx-font-weight:bold;-fx-text-inner-color: green;");
              }
              
              else{ dispo=  new TextField("Indisponible");
              dispo.setStyle(txt+"\n -fx-font-size: 14px;-fx-text-inner-color: red; -fx-font-weight:bold;");
              }
            
            dispo.setPrefWidth(150);
            dispo.setMinWidth(150);
            dispo.setMaxWidth(150);
            dispo.setEditable(false);
            String st=p.getImg().replaceAll(":",":\\\\\\\\");
            
            TextField nom_pep= new TextField(sp.get_nom_pep(p));
            nom_pep.setPrefWidth(150);
            nom_pep.setMinWidth(150);
            nom_pep.setStyle(txt+"\n-fx-font-size: 12px;-fx-font-weight:bold;");
            nom_pep.setMaxWidth(150);
            nom_pep.setEditable(false);
            
            File f = new File(st);
            
            ImageView imgprod5 = new ImageView(f.toURI().toURL().toString());
        
            imgprod5.setFitHeight(100);
            imgprod5.setFitWidth(100);
            imgprod5.setLayoutY(200);
            imgprod5.setLayoutX(218/4);
           Button addtopanier=new Button();
            ImageView iv2 = new ImageView("file:C:\\cart.png");
                iv2.setFitWidth(35);
                iv2.setPreserveRatio(true);
                iv2.setSmooth(true);
                iv2.setCache(true);
                addtopanier.setStyle("-fx-cursor : hand;");
                addtopanier.setGraphic(iv2);
         
            addtopanier.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                    //AddToPanier(aIterator.getId_prod(),aIterator.getPrix_prod());
                   
                        notif();
                    } catch (SQLException ex) {
                        System.out.println("panier favorite"+ex.getMessage());
                    }
                }
            } );
                        
            Button supprimer=new Button();
              ImageView iv = new ImageView("file:C:\\garbage.png");
                iv.setFitWidth(35);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                supprimer.setStyle("-fx-cursor : hand;");
                supprimer.setGraphic(iv);
            
            supprimer.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    sp.Supprimer_Produit(aIterator.getId_fav());
                    try {
                        afficher_favs(sp.Afficher_Produit());
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } );
            
            HBox fhblab2=new HBox();
            VBox fvblab1=new VBox();
            VBox fvblab2=new VBox();
            VBox fvblab3=new VBox();
            VBox fvblab4=new VBox();
             
            //vblab1.setStyle(cssLayout);
            // vblab2.setStyle(cssLayout);
            // vblab3.setStyle(cssLayout);
            // vblab4.setStyle(cssLayout2);
            fhblab2.setStyle(cssLayout);
            //vboo.setStyle(cssLayout);

            fvblab1.getChildren().addAll(imgprod5);
            fvblab2.getChildren().addAll(nomprod,desc);
            fvblab3.getChildren().addAll(nom_pep,prix,dispo);
            fvblab4.getChildren().addAll(addtopanier,supprimer);
            fhblab2.getChildren().addAll(fvblab1,fvblab2,fvblab3,fvblab4);
            vboo2.getChildren().addAll(fhblab2);
       
        }
    }  
//********mouheddine Hamila******************\\
    public void afficher_accueil_promotion(List<Produit> l) throws MalformedURLException{
         

        hbprom.getChildren().clear();
        Statement stm;
        try {
        Services_produit sp=new Services_produit();
        for (Produit aIterator : sp.Afficher_Produit_prom())
           { 

          stm=conn.createStatement();    

          TextField  nomprodp = new TextField(aIterator.getNom_prod().toUpperCase());
          nomprodp.setPrefWidth(150);
          nomprodp.setStyle(txt+"\n -fx-text-inner-color: red; -fx-font-size: 16px;-fx-font-weight:bold;");
          nomprodp.setEditable(false); 

          TextField  oldp=  new TextField("prix_hab"+sp.get_x("prix_hab",aIterator.getId_prod())+"DT");
          oldp.setPrefWidth(75);
          oldp.setStyle(txt+"-fx-text-inner-color: red;");
          oldp.setMinWidth(75);
          oldp.setMaxWidth(75);     
          oldp.setEditable(false);

          TextField  newp=  new TextField(sp.get_x("prix_promo",aIterator.getId_prod())+"DT");
          newp.setPrefWidth(75);
          newp.setStyle(txt);
          
          newp.setMinWidth(75);
          newp.setMaxWidth(75);     
          newp.setEditable(false);
 
 

          String st=aIterator.getImg().replaceAll(":",":\\\\\\\\");

          File f = new File(st);

            ImageView imgprodprom = new ImageView(f.toURI().toURL().toString());      
            StackPane pane = new StackPane();    
            Text pourcentage = new Text("     -"+sp.get_x("pourcentage",aIterator.getId_prod())+"%");
            pourcentage.setFill(Color.RED);
            pourcentage.setRotate(45);
            pourcentage.setFont(Font.font(28)); 
            pane.getChildren().add(imgprodprom);
            pane.getChildren().add(pourcentage);
            pane.setAlignment(Pos.TOP_RIGHT);    
           
            imgprodprom.setFitHeight(125);
            imgprodprom.setFitWidth(150);
       
           Button addtopanier=new Button(); 
            ImageView iv = new ImageView("file:C:\\cart.png");
                iv.setFitWidth(30);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                addtopanier.setStyle("-fx-cursor : hand;");
                addtopanier.setGraphic(iv);
            addtopanier.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                      try {
                    AddToPanier(aIterator.getId_prod(),aIterator.getPrix_prod());
                   
                        notif();
                    } catch (SQLException ex) {
                        System.out.println("panier promotion"+ex.getMessage());
                    }
         }
 } );
            Button addtofav=new Button();
              ImageView iv2 = new ImageView("file:C:\\favorite.png");
                iv2.setFitWidth(30);
                iv2.setPreserveRatio(true);
                iv2.setSmooth(true);
                iv2.setCache(true);
                addtofav.setStyle("-fx-cursor : hand;");
                addtofav.setGraphic(iv2);
          
            addtofav.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    Services_favoris sf=new Services_favoris();
                    Favoris f=new Favoris(aIterator,2);
                    sf.Ajouter_favoris(aIterator.getId_prod(), f);
             }
     } );
             TextArea  desc = new TextArea(aIterator.getDesc_prod());
            desc.setPrefWidth(350);   
            desc.setStyle(txt);
            desc.setEditable(false);   
            
            HBox hblab2p=new HBox();  
            HBox hblab3p=new HBox();
            HBox hblab4p=new HBox();
            VBox vblab4p=new VBox();
              
            hblab2p.setStyle(cssLayout); 
            
            
            hblab3p.getChildren().addAll(oldp,newp);
            hblab4p.getChildren().addAll(addtofav,addtopanier);
            
             
            vblab4p.getChildren().addAll(pane,nomprodp,hblab3p,hblab4p);
            hbprom.getChildren().addAll(vblab4p);
            } 
        } catch (SQLException ex) {
            System.out.println("erreur"+ex.getMessage());
        }
                                       
        
    }
    //********mouheddine Hamila******************\\
    public void afficher_accueil_newest(List<Produit> l) throws MalformedURLException{
         
  
        hbnv.getChildren().clear();
        Statement stm;
        try {
        Services_produit sp=new Services_produit();
        for (Produit aIterator : sp.Afficher_Produit_new())
           { 

          stm=conn.createStatement();    

          TextField  nomprodp = new TextField(aIterator.getNom_prod().toUpperCase());
          
          nomprodp.setStyle(txt+"\n -fx-text-inner-color: red; -fx-font-size: 16px;-fx-font-weight:bold;");
          nomprodp.setPrefWidth(150);
          nomprodp.setEditable(false); 
 
          String st=aIterator.getImg().replaceAll(":",":\\\\\\\\");

          File f = new File(st);

          ImageView imgprodprom = new ImageView(f.toURI().toURL().toString());      
          StackPane pane = new StackPane();    
          Text   newp=  new Text (aIterator.getPrix_prod()+"DT");
          newp.setFill(Color.RED);
            
          newp.setFont(Font.font(28)); 
          pane.getChildren().add(imgprodprom);
          pane.getChildren().add(newp);
          pane.setAlignment(Pos.TOP_LEFT);    
           
          imgprodprom.setFitHeight(125);
          imgprodprom.setFitWidth(150);
       
         Button addtopanier=new Button(); 
          ImageView iv2 = new ImageView("file:C:\\cart.png");
                iv2.setFitWidth(30);
                iv2.setPreserveRatio(true);
                iv2.setSmooth(true);
                iv2.setCache(true);
                addtopanier.setStyle("-fx-cursor : hand;");
                addtopanier.setGraphic(iv2);
          addtopanier.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                     try {
                    AddToPanier(aIterator.getId_prod(),aIterator.getPrix_prod());
                   
                        notif();
                    } catch (SQLException ex) {
                        System.out.println("panier promotion"+ex.getMessage());
                    }
         }
 } );
           Button addtofav=new Button();
             ImageView iv = new ImageView("file:C:\\favorite.png");
                iv.setFitWidth(30);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                addtofav.setStyle("-fx-cursor : hand;");
                addtofav.setGraphic(iv);
            addtofav.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    Services_favoris sf=new Services_favoris();
                    Favoris f=new Favoris(aIterator,2);
                    sf.Ajouter_favoris(aIterator.getId_prod(), f);
             }
     } );
                    
            HBox hblab4p=new HBox();
            VBox vblab4p=new VBox();
            
            hblab4p.getChildren().addAll(addtofav,addtopanier);
            vblab4p.getChildren().addAll(pane,nomprodp,hblab4p);
            hbnv.getChildren().addAll(vblab4p);
            } 
        } catch (SQLException ex) {
            System.out.println("erreur"+ex.getMessage());
        } 
    }
 

    @FXML
    private void show_promotion(MouseEvent event) {
    }

    private void show_all_prods(MouseEvent event) throws MalformedURLException {

        main_produit();
    }

    private void Home(MouseEvent event) {
  
        Services_produit sp=new Services_produit(); 
        
        try {
            afficher_accueil_promotion(sp.Afficher_Produit());
            afficher_accueil_newest(sp.Afficher_Produit());
            main_produit();
           
        
        } catch (MalformedURLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    private void abs(Event event) {
        try {
            vboo.getChildren().clear();
            
            supp_filtr();
            Services_produit sp=new Services_produit();
            afficher_prods(sp.Afficher_Produit());
        } catch (MalformedURLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Forum(MouseEvent event) {
  
    }

 /*############################################################################################*/
     /****************************Affichage liste des commentaires*********************************/
    /*############################################################################################*/
    public void afficher_commentaire (List <Commentaire> l )
    {
            Vcomm.getChildren().clear(); 
        ServiceCommentaire servC = new ServiceCommentaire();
          
      
        for (Commentaire cmt : l ) { 
            
        TextArea comment = new TextArea(cmt.getCommentaire());
        comment.setEditable(false);
        comment.setPrefWidth(320);
        VBox vboxb = new VBox();
        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();
        HBox hbox = new HBox();
        HBox hbox1 =new HBox();
        TextField client = new TextField(cmt.getNom_client());
        TextField date_creationCom = new TextField(cmt.getDate_commentaire()+"");
        TextField nbr_signal  = new TextField(cmt.getNbr_signal()+"");
        vboxb.setSpacing(10.0);
        
     
     modif_comment = new Button();
     modif_comment.setText("modifier Commentaire");
     modif_comment.setOnAction(new javafx.event.EventHandler<ActionEvent>()
             {  
                 @Override
                 public void handle(ActionEvent event)
                 { 
                   textareacom.setText(comment.getText());                     
                   confirmer.setVisible(true);
                   id_cc = cmt.getId_commentaire(); 
               
                 }
             });

     
     supp_comment = new Button();
     supp_comment.setText("supprimer Commentaire");
     
     supp_comment.setOnAction(new javafx.event.EventHandler<ActionEvent>()
             {
                 @Override
                 public void handle(ActionEvent event)
                 {
                     ServiceCommentaire servC = new ServiceCommentaire();
                     servC.supprimerCommentaire(cmt.getId_commentaire());            
                     afficher_commentaire (servC.afficherCommentaire());
                     confirmer.setVisible(false);
                 }
             });
         
     signal_comment = new Button();
     signal_comment.setText("Signaler Commentaire");
     signal_comment.setOnAction(new javafx.event.EventHandler<ActionEvent>()
             {
                 @Override
                 public void handle(ActionEvent event)
                 {             
                     try {
                         String x=id_sujet.getText();
       
                         confirmer.setVisible(false);
                         id_cc = cmt.getId_commentaire();
                         
                         servC.signalerClient(id_cc);
                         afficher_commentaire (servC.afficherCommentaire(Integer.parseInt(x)));
                          signal_comment.setVisible(false);
                          servC.nbrSignalCom(id_cc);
                          
                         
                     } catch (SQLException ex) {
                         System.out.println("ereur signal: "+ex.getMessage());
                     }
                      
                 }
             });
     client.setEditable(false);
     date_creationCom.setEditable(false);
     nbr_signal.setEditable(false);
     hbox1.getChildren().addAll(client,date_creationCom,nbr_signal);
     vbox1.getChildren().addAll(hbox1);
     vbox2.getChildren().addAll(comment,hbox1);
     vboxb.getChildren().addAll(modif_comment,supp_comment,signal_comment);
     hbox.getChildren().addAll(vbox2,vboxb,vbox1);
     Vcomm.getChildren().addAll(hbox);
     
     
        }   
    }

    /*############################################################################################*/
    /****************************Affichage 1 ere catégorie**********************************/
    /*############################################################################################*/
    
    private void btn_Planter(MouseEvent event) throws MalformedURLException {
           affich(Planter);
           anchorsujet.setVisible(true);
           espaceCom.setVisible(false);
    }
    
    private void btn_planter1(MouseEvent event) throws MalformedURLException {
         affich(planter1);
         anchorsujet.setVisible(true);
         espaceCom.setVisible(false); 
     }
    private void btn_planter2(MouseEvent event) throws MalformedURLException {
        affich(planter2);
         anchorsujet.setVisible(true);
         espaceCom.setVisible(false);
    }
    private void btn_planter3(MouseEvent event) throws MalformedURLException {
         affich(planter3);
          anchorsujet.setVisible(true);
          espaceCom.setVisible(false);
    }
    private void btn_Entretien(MouseEvent event) throws MalformedURLException {
         affich(Entretien);
          anchorsujet.setVisible(true);
         espaceCom.setVisible(false);
         
    }
    
    private void btn_Entretien1(MouseEvent event) throws MalformedURLException {
        affich(Entretien1);
         anchorsujet.setVisible(true);
         espaceCom.setVisible(false);
    }

    private void btn_Entretien2(MouseEvent event) throws MalformedURLException {
        affich(Entretien2); 
        anchorsujet.setVisible(true);
         espaceCom.setVisible(false);
         
        
    }
    private void btn_Conseils(MouseEvent event) throws MalformedURLException {
         affich(Conseils);
          anchorsujet.setVisible(true);
         espaceCom.setVisible(false);
    }
 
    public void affich(Button b) throws MalformedURLException
    {
        listSujet.getChildren().clear(); 
        ServiceSujet ss=new ServiceSujet();
        for (Sujet suj : ss.afficher_Sujet(b) ) { 

      
     HBox hbox = new HBox();
     HBox hbox2 = new HBox();
     VBox vbox = new VBox();
     TextField nom = new TextField(suj.getNom_sujet());
     TextArea desc = new TextArea(suj.getDescription());    
     TextField nom_pep = new TextField(suj.getNom_agent());
     TextField date_ajout = new TextField(""+suj.getDate_creation());
     
      String st=suj.getImage().replaceAll(":",":\\\\");
           //System.out.println("///////////"+st);
           File f = new File(st);
            
           ImageView image = new ImageView(f.toURI().toURL().toString());
           image.setFitHeight(100);
           image.setFitWidth(100);
                 
           nom.setEditable(false);
           desc.setEditable(false);
           nom_pep.setEditable(false);
           date_ajout.setEditable(false);
                  
        voir_sujet = new Button();
        voir_sujet.setText("Voir sujet");
        id_sujet.setText(Integer.toString(suj.getId_sujet()));
        voir_sujet.setOnAction(new javafx.event.EventHandler<ActionEvent>()
             {  
                 @Override
                 public void handle(ActionEvent event)
                 { 
                   
                      id_sj =suj.getId_sujet();
                    Commentaire Com =new Commentaire();
                    ServiceCommentaire servC = new ServiceCommentaire();
                    afficher_commentaire (servC.afficherCommentaire(id_sj));
                    nom_sujet.setText(Com.getNom_sujet());
                    description.setText(Com.getCommentaire());
                    id_cc = suj.getId_sujet();
                    nom_sujet.setText(suj.getNom_sujet());
                    description.setText(suj.getDescription());
                     espaceCom.setVisible(true);
                     anchorsujet.setVisible(false);
                     id_sujet.setText(Integer.toString(suj.getId_sujet()));
                 }
             });
    
     hbox.setStyle(cssLayout);
     hbox2.getChildren().addAll(nom,nom_pep,date_ajout,voir_sujet);
     vbox.getChildren().addAll(hbox2,desc);
     hbox.getChildren().addAll(vbox,image);
     listSujet.getChildren().addAll(hbox);
        }   
    }
    
     /*############################################################################################*/
     /*#######################Ajout d'un commentaire################################################/
     /*############################################################################################*/
    

    private void addcomment(MouseEvent event) throws SQLException {
        
         ServiceCommentaire servC = new ServiceCommentaire();
        
         int x=servC.get_id_user();
        String y=id_sujet.getText();
        System.out.println("id sujet"+y);
        int c=Integer.parseInt(y);
        
            Commentaire Com = new Commentaire(c,x,nom_sujet.getText(),textareacom.getText());

//          servC.ajouterCommentaire(Com,c);
          
        commentaires.setVisible(true);
         TextArea test = new TextArea(textareacom.getText());
         Vcomm.getChildren().add(test);
         textareacom.setText(null);
         Vcomm.getChildren().clear();
          afficher_commentaire (servC.afficherCommentaire(id_sj));
           signal_comment.setVisible(false);
    }

Connection C=  Maconnexion.getinstance().getConn();



    private void confirmer_modif(MouseEvent event) {
           Commentaire Com =new Commentaire();
           ServiceCommentaire sc=new ServiceCommentaire();
           sc.modifierCommentaire(textareacom.getText(), id_cc);
           Vcomm.getChildren().clear();
           ServiceCommentaire servC = new ServiceCommentaire();
            afficher_commentaire (servC.afficherCommentaire());
           confirmer.setVisible(false);
           textareacom.setText(null);
    }

    private void editAdr(MouseEvent event) throws SQLException {
        FinalisserAnchor.setVisible(false);
        FinalisserAnchor1.setVisible(true);
         ResultSet rs = conn.createStatement().executeQuery("SELECT nom,prenom,email,telephone,adresse FROM user where connected=1");  
        while (rs.next()) 
            {
                txt_nomcl.setText(rs.getString(1));
               txt_prncl.setText(rs.getString(2));
               txt_mailcl.setText(rs.getString(3));
               txt_numcl.setText(rs.getString(4));
               txt_adrcl.setText(rs.getString(5));
            }
        
    }

    private void cancelChange(MouseEvent event) {
         FinalisserAnchor.setVisible(true);
        FinalisserAnchor1.setVisible(false);
    }

    private void changeAdresse(MouseEvent event) throws SQLException {
        Validate v=new Validate();   
        
        String nom=txt_nomcl.getText();
        String prn=txt_prncl.getText();
        String num=txt_numcl.getText();
        String mail= txt_mailcl.getText();
        String adr=txt_adrcl.getText();  
        boolean status=v.ValidateEmail(mail);
       Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("vous ete sur de changer votre adresse vers");
        alert.setContentText(adr);
            if (status)
            {
 
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
      try {
        String req_conn="update user set nom=? ,prenom=?"
                +",adresse=?,telephone=?,email=? where connected=1";
  PreparedStatement prt = conn.prepareStatement(req_conn);
           
            prt.setString(1, nom);
            prt.setString(2, prn);
            prt.setString(4, num);
            prt.setString(5, mail);
            prt.setString(3, adr);
            prt.executeUpdate();
               finalCmd();
    } catch (SQLException s) {}
    }
 else {
    // ... user chose CANCEL or closed the dialog
}
            }
            else{JOptionPane.showMessageDialog(null,"Verifier votre E-mail");}
    }      
       
}
