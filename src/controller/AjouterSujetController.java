/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Sujet;
import javafx.scene.chart.*;

import service.ServiceSujet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.Maconnexion;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author achraf
 */
public class AjouterSujetController implements Initializable {
Connection C = Maconnexion.getinstance().getConn();
    @FXML
    private TextField rech;
    @FXML
    private Button Button_ajout_sujet;
    @FXML
    private Button Button_modifier_sujet;
    @FXML
    private Button Button_supprimer_sujet;
    @FXML
    private Label labb;
    @FXML
    private TextField textfieldSujet;
    @FXML
    private ComboBox ComboboxtypeSujet;
    @FXML
    private ComboBox ComboboxcatSujet;
    @FXML
    private TextArea textareaDescription;
    @FXML
    private Button btnImage;
    @FXML
    private TableView<Sujet> tab_sujet;
    @FXML
    private TableColumn<Sujet,Button> col_id;
    @FXML
    private TableColumn<Sujet, String> col_nom_sujet;
    @FXML
    private TableColumn<Sujet, String> col_cat_sujet;
    @FXML
    private TableColumn<Sujet, String> col_date_creation;
    @FXML
    private TableColumn<Sujet, String> col_description;
    @FXML
    private TableColumn<Sujet, String> col_type;
    @FXML
    private TableColumn<Sujet, Button> col_image;
    @FXML
    private AnchorPane zon_ajout_sujet;
    @FXML
    private Label label_sujet; 
    @FXML
    private AnchorPane anchormodifsujet;
    @FXML
    private TextField sujet;
    @FXML
    private ComboBox type;
    @FXML
    private ComboBox nom_cat;
    @FXML
    private TextArea description;
    @FXML
    private Button boutonModifier;
    @FXML
    private Button boutonAnnuler;
    @FXML
    private Button boutonImage;
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
    private Button menuJoueur1;
    @FXML
    private Button menuJoueur11;
    @FXML
    private Button menuArbitre121;
    private String img;
    @FXML
    private Button Button_stat;
    @FXML
    private Pane pane_stat;
    @FXML
    private AnchorPane anchor_tabV;
 
     private int id_sj;
    @FXML
    private ImageView retour;
    
  

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Aff();
      anchormodifsujet.setVisible(false);
      zon_ajout_sujet.setVisible(true);
      retour.setVisible(false);
         ObservableList<String> typeSujet = 
            FXCollections.observableArrayList(
                "Plantation",
                "Entretien",
                "Conseils"
            );
   
        ComboboxtypeSujet.setItems(typeSujet);
        
        ObservableList<String> catSujet = 
            FXCollections.observableArrayList(
                "Planter un arbuste",
                "Planter un arbre",
                "Plantation d`un arbre fruitier",
                "Planter un rosier",
                ".................",
                "Arrosage",
                "Techniques de culture",
                "Taille",
                ".................",
                "Jardinage pratique"
            );
        ComboboxcatSujet.setItems(catSujet);
    }

    @FXML
    private void ModifierSujet(MouseEvent event) throws IOException {
 
        anchormodifsujet.setVisible(true);
        zon_ajout_sujet.setVisible(false);
        
        Sujet gets=tab_sujet.getSelectionModel().getSelectedItem();
        sujet.setText(gets.getNom_sujet());
        description.setText(""+gets.getDescription());
        type.setValue(gets.getType());
        nom_cat.setValue(gets.getNom_categorie_sujet());
       
        
    }
    
    public List<Number> l=new ArrayList<Number>();
 private void Aff() {
     
     col_id.setCellValueFactory(new PropertyValueFactory<>("id_sujet")); 
     col_nom_sujet.setCellValueFactory(new PropertyValueFactory<>("nom_sujet"));
          col_cat_sujet.setCellValueFactory(new PropertyValueFactory<>("nom_categorie_sujet"));
               col_date_creation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
          col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
               col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
               col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
                 
        ObservableList<Sujet> oblist=FXCollections.observableArrayList();
    
  
         try {
             Statement st = C.createStatement();
            String req = "select * from sujet";
             ResultSet rs = st.executeQuery(req);
              while(rs.next()){
                  oblist.add(new Sujet(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10))); 
                  l.add(rs.getInt(1));
              }                 
          } 
          
          catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   
               tab_sujet.setItems(oblist);
    }
    
    @FXML
    private void SupprimerSujet(MouseEvent event) {
        
         Sujet S =  (Sujet)tab_sujet.getSelectionModel().getSelectedItem();
        ServiceSujet serv = new ServiceSujet();
        String s = tab_sujet.getSelectionModel().getSelectedItem().getNom_sujet();
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Suppression Sujet ");
        alert.setHeaderText("");
        alert.setContentText("Voulez vous supprimer ce sujet?");
        
        Optional<ButtonType> result = alert.showAndWait(); 
        if (result.get() == ButtonType.OK){
            serv.supprimerSujet(S.getNom_sujet().toString());
            Aff(); 
            }
        else{
            Aff();
        }
        
    }
 
    

    @FXML
    private void modifier(MouseEvent event) {///valider modification
        Sujet S = (Sujet) tab_sujet.getSelectionModel().getSelectedItem();
        int i=tab_sujet.getSelectionModel().getSelectedItem().getId_sujet();
        S.setId_sujet(i);
        S.setNom_sujet(sujet.getText());
        S.setDescription(description.getText());
        S.setImage(img);
        ServiceSujet serv = new ServiceSujet();
      serv.modifierSujet(S);
         Aff();
        
    }

    @FXML
    private void boutonAnnuler(MouseEvent event) {
        anchormodifsujet.setVisible(false);
        zon_ajout_sujet.setVisible(true);
         
    }
    
    

    @FXML
    private void rechercheSujet(KeyEvent event) {
        
        col_nom_sujet.setCellValueFactory(new PropertyValueFactory<>("nom_sujet"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            
              ObservableList<Sujet> oblist=FXCollections.observableArrayList();
           
              
        try {
            ResultSet rs = C.createStatement().executeQuery("SELECT * FROM sujet WHERE nom_sujet LIKE '%" + rech.getText() + "%'"
                        + " UNION SELECT * FROM sujet WHERE description LIKE '%" + rech.getText() + "%'"
                        + " UNION SELECT * FROM sujet WHERE nom_categorie_sujet LIKE '%" + rech.getText() + "%'"
                        + " UNION SELECT * FROM sujet WHERE type LIKE '%" + rech.getText() + "%'");
            while (rs.next()) {
               oblist.add(new Sujet(rs.getString(4),rs.getString(8),rs.getString(5),rs.getString(9)));
               
               // System.out.println("  " +rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));
            }

        } catch (SQLException ex) {
            System.err.println("ERROR" + ex);

        }
        tab_sujet.setItems(oblist);
    }

    @FXML
    private void LogOut(MouseEvent event) {
    }


    @FXML
    private void AjouterSujet(MouseEvent event) {
 
        
        if(textfieldSujet.getText().isEmpty() || textareaDescription.getText().isEmpty())
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Champ manquant");
            alert.setHeaderText("");
            alert.setContentText("Vous devez remplir ces champs");
            alert.showAndWait();
        }
        else
        {
            Sujet S = new Sujet();
            S.setNom_categorie_sujet((String) ComboboxcatSujet.getSelectionModel().getSelectedItem());
            System.out.println("nom categori::"+(String) ComboboxcatSujet.getSelectionModel().getSelectedItem());
            S.setType((String) ComboboxtypeSujet.getSelectionModel().getSelectedItem());
            S.setDescription(textareaDescription.getText());
            S.setNom_sujet(textfieldSujet.getText());
            S.setImage(img);
            textfieldSujet.setText(null);
            ComboboxtypeSujet.setValue(null);
            ComboboxcatSujet.setValue(null);
            textareaDescription.setText(null);
            type.setValue(null);
            ServiceSujet serv = new ServiceSujet();
            serv.ajouterSujet(S);
            Aff();
            serv.envoyerMail(S.getNom_sujet());
        }
        
    } 

    @FXML
    private void uploadImage(MouseEvent event) {
         FileChooser f=new FileChooser();
        File f2=   f.showOpenDialog(null);
        String x=f2.toString();   
         img=x;
    }

    @FXML
    private void uploadImage_modif(MouseEvent event) {
            FileChooser f=new FileChooser();
        File f2=   f.showOpenDialog(null);
        String x=f2.toString();   
         img=x;
    }

    @FXML
    private void Modifier(MouseEvent event) {
    }

    @FXML
    private void voir_S(MouseEvent event) throws SQLException {
        
        pane_stat.setVisible(true);
        anchor_tabV.setVisible(false);
        Button_supprimer_sujet.setVisible(false);        
        Button_modifier_sujet.setVisible(false);
        zon_ajout_sujet.setVisible(false);
        rech.setVisible(false);
        retour.setVisible(true);
        
        
        
          
             Number id=0;
        ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
        for(int i=0; i<l.size();i++)
        {
           id=l.get(i);
        
        Statement st1 = C.createStatement();
            String req="select nom_sujet from sujet where id_sujet ="+id;
              ResultSet rs=st1.executeQuery(req);
            String nom="";
             while(rs.next())
            {
               System.out.println(rs.getString(1));
                nom=rs.getString(1);
            }
             
        Statement st2 = C.createStatement();
            String req1="select count(commentaires) from commentaire where id_sujet="+id;//+id_sujet;
              ResultSet rs1=st2.executeQuery(req1);
             
            int nbr=0;
             while(rs1.next())
            {
                nbr=rs1.getInt(1);
                pieChartData.add(new PieChart.Data(nom ,nbr));
            }
        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Liste des Sujets");
        pane_stat.getChildren().add(chart);
}

    @FXML
    private void btn_retour(MouseEvent event) {
        pane_stat.setVisible(false);
        anchor_tabV.setVisible(true);
         anchor_tabV.setVisible(true);
        Button_supprimer_sujet.setVisible(true);        
        Button_modifier_sujet.setVisible(true);
        zon_ajout_sujet.setVisible(true);
        rech.setVisible(true);
    }

}
