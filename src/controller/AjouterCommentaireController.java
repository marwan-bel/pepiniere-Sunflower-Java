/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Commentaire;
import Entities.Sujet;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import controller.ListCellSujetController;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import service.ServiceCommentaire;
import service.ServiceSujet;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class AjouterCommentaireController implements Initializable {
     
    @FXML
    private TitledPane acord;
    @FXML
    private VBox vboxPlantation;
    @FXML
    private Button Planter;
    @FXML
    private Button planter1;
    @FXML
    private Button planter2;
    @FXML
    private Button planter3;
    @FXML
    private VBox vboxEntretien;
    @FXML
    private Button Entretien;
    @FXML
    private Button Entretien1;
    @FXML
    private Button Entretien2;
    @FXML
    private VBox vboxConseils;
    @FXML
    private Button Conseils;
    @FXML
    private AnchorPane espaceCom;
    @FXML
    public AnchorPane anchorComm;

    @FXML
    private TextField nom_sujet;
    @FXML
    private TextArea description;
    @FXML
    private ImageView addcomment;
    @FXML
    private Button partager_comment;
    @FXML
    private Button confirmer;
    @FXML
    private VBox Vcomm;
    @FXML
    private HBox Hcomm;
    @FXML
    private TextArea commentaires;
    @FXML
    private VBox VcommB;
    @FXML
    private ImageView signal_comment;
    @FXML
    private Button supp_comment;
    @FXML
    private Button modif_comment;
    @FXML
    private Label id_sujet;
    @FXML
    public AnchorPane anchorsujet;

    @FXML
    private JFXTextArea textareacom;
    @FXML
    private ListView<Commentaire> listViewService;
    
    public ListView<Commentaire> listViewService2;
    
    private Button voir_sujet;
Commentaire com;
    ObservableList<Sujet> data;
    List<Sujet> ls;
 ObservableList<Commentaire> datacommm;
    ObservableList<Commentaire> datacomm;
    List<Commentaire> lscomm;
     List<Commentaire> lscommm;
    String cssLayout = "-fx-border-color: #2f4f4f;\n"
            + "-fx-padding: 15;\n"
            + "-fx-spacing: 10;\n";
    @FXML
    private ListView<Sujet> sujetListView;
    @FXML
    private HBox Hbox;
    @FXML
    private Text txt_nomsujet;
    @FXML
    private JFXTextArea txt_desc;
    @FXML
    private JFXTextArea txt_commentaire;
    @FXML
    private ImageView btn_addcomment;
    @FXML
    private JFXComboBox sujetCombo;
    ObservableList<String> ls_sujet;
static int x=0;
Connection conn=Maconnexion.getinstance().getConn();
    @FXML
    private RadioButton btn_arbuste;
    @FXML
    private RadioButton btn_arbre;
    @FXML
    private RadioButton btn_arbre_fruitier;
    @FXML
    private RadioButton btn_rosier;
    @FXML
    private RadioButton btn_arrosage;
    @FXML
    private RadioButton btn_culture;
    @FXML
    private RadioButton btn_taille;
    @FXML
    private RadioButton btn_jardinage;
            ServiceSujet servS = new ServiceSujet();
            
        ServiceCommentaire servC = new ServiceCommentaire();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       affichage();
       filter(btn_arbuste);
        filter(btn_arbre);
        filter(btn_arbre_fruitier); 
        filter(btn_rosier);
        filter(btn_arrosage);
        filter(btn_culture);
        filter(btn_taille);
        filter(btn_jardinage);
                       datacommm = FXCollections.observableArrayList();
                         lscommm = servC.afficherCommentaire(x);
                            txt_commentaire.clear();
                            
                            lscommm.forEach((m) -> {
                        datacommm.add(m);

        });  

    }
    
    
           
 
public void filter(RadioButton c){ 
         
         ObservableList<Sujet> oblist = FXCollections.observableArrayList();
         ls_sujet = FXCollections.observableArrayList();
          
         c.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 try {
                     oblist.clear();
                     ls_sujet.clear();
                      oblist.removeAll();
                      supp_filtr();
                     c.setSelected(true);
                      if (!c.isPressed()){
                         ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM sujet WHERE nom_categorie_sujet='"+c.getText()+"'");
                         while (rs.next()) {
                             oblist.add(new Sujet(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10)));
                          }
                         
                    
                     data = FXCollections.observableArrayList();
                     oblist.forEach((j) -> {data.add(j);
                     
                                            ls_sujet.add(j.getNom_sujet());
                                            });
                             sujetCombo.setItems(ls_sujet);

                     
                     
                      sujetListView.setItems(data);
                     sujetListView.setCellFactory((ListView<Sujet> param) -> new ListCellSujetController());
                     } } catch (SQLException ex) { }
                  
             
         }; 
         });    
    
}
    private void supp_filtr() {
        btn_arbre.setSelected(false);
        btn_arbuste.setSelected(false);
        btn_arbre_fruitier.setSelected(false); 
        btn_rosier.setSelected(false);
        btn_arrosage.setSelected(false);
        btn_culture.setSelected(false);
        btn_taille.setSelected(false); 
        btn_jardinage.setSelected(false);
         
    }
    
public void affichage ()
  {
         
        data = FXCollections.observableArrayList();
        ls_sujet = FXCollections.observableArrayList();
        ls = servS.afficherSujet();
        
        ls.forEach((j) -> {
            data.add(j);
            ls_sujet.add(j.getNom_sujet());

        });
        sujetCombo.setItems(ls_sujet);

        sujetListView.setItems(data);
        sujetListView.setCellFactory((ListView<Sujet> param) -> new ListCellSujetController());

        sujetCombo.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {

            anchorsujet.setVisible(false);

            anchorComm.setVisible(true);
            
            datacomm = FXCollections.observableArrayList();
            
            lscomm = servC.afficherCommentaire();
            
            lscomm.forEach((j) -> {
               if (j.getNom_sujet().equals(sujetCombo.getSelectionModel().getSelectedItem())) {
                       datacomm.add(j);
                       txt_nomsujet.setText(j.getNom_sujet());
                        ls.forEach((i) -> {
                                x=j.getId_sujet();
                        System.out.println("yyyyyyyyyyhhhhhhhhhhh="+x);
                       if (j.getId_sujet()==(i.getId_sujet())) 
                       {
                       txt_desc.setText(i.getDescription());
                   
                       }
            }); 
                        listViewService.getItems().clear();
                 listViewService.setItems(datacommm);
            listViewService.setCellFactory((ListView<Commentaire> param) -> new ListCellCommentController());
            
                    }
               btn_addcomment.setOnMouseClicked((event) -> {
                   if(txt_commentaire.getText().length()!=0)
                 {    datacommm = FXCollections.observableArrayList();
                         servC.ajouterCommentaire(txt_commentaire.getText(),x);
                         lscommm = servC.afficherCommentaire(x);
                            txt_commentaire.clear();
                            
                            lscommm.forEach((m) -> {
                        datacommm.add(m);
            

        });  
            listViewService.setItems(datacommm);
            listViewService.setCellFactory((ListView<Commentaire> param) -> new ListCellCommentController());
                   }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
            alert.setContentText("Vous devez remplir le champ commentaire");
            alert.show();}
                    });
               
 
            });
            listViewService.getItems().clear();
            listViewService.setItems(datacomm);
            listViewService.setCellFactory((ListView<Commentaire> param) -> new ListCellCommentController());
        });

  
  }
  
  public void testText(Commentaire com)
  {this.com=com;
  txt_commentaire.setText(com.getCommentaire());
      ////SelectedComment=cm;
     // txt_commentaire.setText(SelectedComment.getCommentaire());
      System.out.println("commentaire: "+this.txt_commentaire.getText());
      //txt_commentaire.setText(txt_commentairee);
  }
  



    /*############################################################################################*/
    /**
     * ********************S******Affichage liste des
     * commentaires********************************
     */
    /*############################################################################################*/
    public void afficher_commentaire(List<Commentaire> l) {
        Vcomm.getChildren().clear();
        ServiceCommentaire servC = new ServiceCommentaire();

        for (Commentaire cmt : l) {

            TextArea comment = new TextArea(cmt.getCommentaire());
            comment.setEditable(false);
            comment.setPrefWidth(320);
            VBox vboxb = new VBox();
            VBox vbox1 = new VBox();
            VBox vbox2 = new VBox();
            HBox hbox = new HBox();
            HBox hbox1 = new HBox();
            TextField client = new TextField(cmt.getNom_client());
            TextField date_creationCom = new TextField(cmt.getDate_commentaire() + "");
            TextField nbr_signal = new TextField(cmt.getNbr_signal() + "");
            vboxb.setSpacing(10.0);

            modif_comment = new Button();
            modif_comment.setText("modifier Commentaire");
            modif_comment.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    textareacom.setText(comment.getText());
                    confirmer.setVisible(true);
//                   id_cc = cmt.getId_commentaire(); 

                }
            });

            supp_comment = new Button();
            supp_comment.setText("supprimer Commentaire");

            supp_comment.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ServiceCommentaire servC = new ServiceCommentaire();
                    servC.supprimerCommentaire(cmt.getId_commentaire());
                    afficher_commentaire(servC.afficherCommentaire());
                    confirmer.setVisible(false);
                }
            });

            /* signal_comment = new Button();
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
             });*/
            client.setEditable(false);
            date_creationCom.setEditable(false);
            nbr_signal.setEditable(false);
            hbox1.getChildren().addAll(client, date_creationCom, nbr_signal);
            vbox1.getChildren().addAll(hbox1);
            vbox2.getChildren().addAll(comment, hbox1);
            vboxb.getChildren().addAll(modif_comment, supp_comment, signal_comment);
            hbox.getChildren().addAll(vbox2, vboxb, vbox1);
            Vcomm.getChildren().addAll(hbox);

        }
    }

    /*############################################################################################*/
    /**
     * **************************Affichage 1 ere
     * catégorie*********************************
     */
    /*############################################################################################*/
 /* @FXML
    private void btn_Planter(MouseEvent event) throws MalformedURLException {
           affich(Planter);
           anchorsujet.setVisible(true);
           espaceCom.setVisible(false);
    }
    
    @FXML
    private void btn_planter1(MouseEvent event) throws MalformedURLException {
         affich(planter1);
         anchorsujet.setVisible(true);
         espaceCom.setVisible(false); 
     }
    @FXML
    private void btn_planter2(MouseEvent event) throws MalformedURLException {
        affich(planter2);
         anchorsujet.setVisible(true);
         espaceCom.setVisible(false);
    }
     @FXML
    private void btn_planter3(MouseEvent event) throws MalformedURLException {
         affich(planter3);
          anchorsujet.setVisible(true);
          espaceCom.setVisible(false);
    }
     @FXML
    private void btn_Entretien(MouseEvent event) throws MalformedURLException {
         affich(Entretien);
          anchorsujet.setVisible(true);
         espaceCom.setVisible(false);
         
    }
    
    @FXML
    private void btn_Entretien1(MouseEvent event) throws MalformedURLException {
        affich(Entretien1);
         anchorsujet.setVisible(true);
         espaceCom.setVisible(false);
    }

    @FXML
    private void btn_Entretien2(MouseEvent event) throws MalformedURLException {
        affich(Entretien2); 
        anchorsujet.setVisible(true);
         espaceCom.setVisible(false);
         
        
    }
     @FXML
    private void btn_Conseils(MouseEvent event) throws MalformedURLException {
         affich(Conseils);
          anchorsujet.setVisible(true);
         espaceCom.setVisible(false);
    }
     */
 /* public void affich(Button b) throws MalformedURLException
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
     */
 /*############################################################################################*/
 /*#######################Ajout d'un commentaire################################################/
     /*############################################################################################*/
    @FXML
    private void addcomment(MouseEvent event) throws SQLException {

        ServiceCommentaire servC = new ServiceCommentaire();

        int x = servC.get_id_user();
        String y = id_sujet.getText();
        System.out.println("id sujet" + y);
        int c = Integer.parseInt(y);

        Commentaire Com = new Commentaire(c, x, nom_sujet.getText(), textareacom.getText());

//        servC.ajouterCommentaire(Com, c);

        commentaires.setVisible(true);
        TextArea test = new TextArea(textareacom.getText());
        Vcomm.getChildren().add(test);
        textareacom.setText(null);
        Vcomm.getChildren().clear();
//          afficher_commentaire (servC.afficherCommentaire(id_sj));
        signal_comment.setVisible(false);
        
    }

    Connection C = Maconnexion.getinstance().getConn();

    @FXML
    private void signal_comment(MouseEvent event) throws SQLException {

    }

    @FXML
    private void confirmer_modif(MouseEvent event) {
        Commentaire Com = new Commentaire();
        ServiceCommentaire sc = new ServiceCommentaire();
//           sc.modifierCommentaire(textareacom.getText(), id_cc);
        Vcomm.getChildren().clear();
        ServiceCommentaire servC = new ServiceCommentaire();
        afficher_commentaire(servC.afficherCommentaire());
        confirmer.setVisible(false);
        textareacom.setText(null);
    }

    @FXML
    private void btn_Planter(MouseEvent event) {
    }

    @FXML
    private void btn_planter1(MouseEvent event) {
    }

    @FXML
    private void btn_planter2(MouseEvent event) {
    }

    @FXML
    private void btn_planter3(MouseEvent event) {
    }

    @FXML
    private void btn_Entretien(MouseEvent event) {
    }

    @FXML
    private void btn_Entretien1(MouseEvent event) {
    }

    @FXML
    private void btn_Entretien2(MouseEvent event) {
    }

    @FXML
    private void btn_Conseils(MouseEvent event) {
    }

 

}
