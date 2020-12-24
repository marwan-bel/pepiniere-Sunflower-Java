package controller;
 
import Entities.Produit;  
import utils.Maconnexion;  
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import service.sendSMS;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;  
import javafx.scene.control.TableColumn; 
import javafx.scene.control.TableView; 
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;  
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.*;

public class Prod_pepController implements Initializable {

    @FXML    private TableView<Produit> tab_prod;
    @FXML    private TableColumn<Produit,String> nom_produit;
    @FXML    private TableColumn<Produit,String> nom_categorie;
    @FXML    private TableColumn<Produit,String> type;
    @FXML    private TableColumn<Produit,String> prix;
    @FXML    private TableColumn<Produit,String> quantite;
    @FXML    private TableColumn<Produit,String> description;
    @FXML    private TableColumn<Produit,String> id_produit;
  
    @FXML    private Button show_add;
    @FXML    private Button btn_del;
    @FXML    private TextField btn_rech;
             private String path;
             private int idprd;
    @FXML    private ImageView optionTailleAgrandir;
 
    @FXML    private JFXTextField Prix_mod;
    @FXML    private JFXTextField Nom_produit_mod;
    @FXML    private JFXTextArea Desc_mod;
    @FXML    private JFXTextField Qte_mod;
    @FXML    private JFXComboBox<String> Type_mod;
    @FXML    private JFXComboBox<String> Cat_mod;
 
    ObservableList<String> lst =FXCollections.observableArrayList("Plantes","Equipements","Engrais");
    Services_produit sp=new Services_produit();
    Services_favoris sf=new Services_favoris();
    @FXML    private Button btn_mod;
    @FXML    private ImageView show_img_mod;
    @FXML    private AnchorPane zone_mod_p;
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        afficher_produit();
        Type_mod.setItems(lst);
      }
 Connection conn=Maconnexion.getinstance().getConn(); 
   
     public  void afficher_produit(){
         
          ObservableList<Produit> oblist = FXCollections.observableArrayList();
            id_produit.setCellValueFactory(new PropertyValueFactory<>("Id_prod"));
            nom_produit.setCellValueFactory(new PropertyValueFactory<>("Nom_prod"));
            nom_categorie.setCellValueFactory(new PropertyValueFactory<>("Nom_categorie"));
            type.setCellValueFactory(new PropertyValueFactory<>("Type_prod"));
            prix.setCellValueFactory(new PropertyValueFactory<>("Prix_prod"));
            quantite.setCellValueFactory(new PropertyValueFactory<>("Qte_prod")); 
            description.setCellValueFactory(new PropertyValueFactory<>("Desc_prod"));
            oblist.setAll(sp.Afficher_Produitc());
            tab_prod.setItems(oblist);
        
    }
 
    @FXML
    private void testprix(KeyEvent event) {
        if (Prix_mod.getText().matches("[0-9]*")==false)
       {Prix_mod.setStyle("-fx-border-color: red;");}
        else{Prix_mod.setStyle("-fx-border-color: #0ad200;");}
       }
     @FXML
    private void testqte(KeyEvent event) {
        if (Qte_mod.getText().matches("[0-9]*")==false)
        {Qte_mod.setStyle("-fx-border-color: red;");}
        else{Qte_mod.setStyle("-fx-border-color: #0ad200;");}
    }
    @FXML
    private void delete_prod(ActionEvent event) { 
         Produit p=tab_prod.getSelectionModel().getSelectedItem();
         DAOLigneCommande lc=new DAOLigneCommande();
         ServiceAvis sa=new ServiceAvis();
        ServicePromotion srv=new ServicePromotion();
         ServiceReclamation sr=new ServiceReclamation();
  
         srv.supprimerprod(idprd);
         sr.supprimerprod(idprd);
         sa.supprimer(p.getId_prod());
         lc.deleteLigneCmd(p.getId_prod());
         sp.Supprimer_Produit(p.getId_prod());
         afficher_produit();
    }

    @FXML
    private void showadd(ActionEvent event) {
    try {
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Ajouter_produit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
     
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.setScene(new Scene(root1));
            stage.show();
               } catch (IOException ex) {
            Logger.getLogger(Prod_pepController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rechercheUser(KeyEvent event) {
        afficher_produit(); 
       tab_prod.setItems(sp.rech_prod(btn_rech.getText()));
    }

    @FXML
    private void choose() throws MalformedURLException {
        FileChooser f=new FileChooser(); 
        File f2=   f.showOpenDialog(null);   
        String x=f2.toString().replaceAll("C:\\\\wamp64\\\\www\\\\final_pepiniere\\\\web\\\\uploads\\\\images" , "");  
         
 
        Image imgc = new Image (f2.toURI().toURL().toString());
                   
         show_img_mod.setImage(imgc);
         path=x;
        }
 
    @FXML
    private void on_click_produit(MouseEvent event) { 
       try{
            Produit p = (Produit) tab_prod.getSelectionModel().getSelectedItem();
            Nom_produit_mod.setText(p.getNom_prod());
            Prix_mod.setText(""+p.getPrix_prod());
            Qte_mod.setText(p.getQte_prod()+"");
            Desc_mod.setText(p.getDesc_prod());
            Type_mod.setValue(p.getType_prod());
            Cat_mod.setValue(p.getNom_categorie());   
            idprd=p.getId_prod();
            path=p.getImg(); 
            
         } catch (Exception e) {}   
    }

    @FXML
    private void categ_chng_mod(ActionEvent event) {
        Cat_mod.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Cat_mod.setItems(sp.get_categorie(Type_mod.getValue()));
            }
    });
    }
 

    @FXML
    private void modifier_prod(MouseEvent event) {
        Produit p = new Produit();
        Produit p2 = (Produit) tab_prod.getSelectionModel().getSelectedItem();
        
            String s;
         int q=Integer.parseInt(Qte_mod.getText());
         if((Nom_produit_mod.getText().equals(""))||(Desc_mod.getText().equals(""))||(Qte_mod.getText().matches("[0-9]*")==false))
             {      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERREUR");
                    alert.setHeaderText("Veuillez remplir tous les champs");
                    alert.show();}        
            else{
            p.setNom_prod(Nom_produit_mod.getText());
            p.setPrix_prod(Float.parseFloat(Prix_mod.getText()));
            p.setQte_prod(Integer.parseInt(Qte_mod.getText()));
            if ((p2.getQte_prod()==0)&&(p.getQte_prod()>0))
            {
                  sendSMS SMS=new sendSMS();
                  int lng=sf.get_tel(p2.getId_prod()).size();
                  for (int i = 0; i <lng ; i++) {
                  String msg=     "&message=" + "Le produit "+p2.getNom_prod()+" souhaité a été ajouté dans notre stock, vous pouvez consulter votre liste des souhaits et l'acheter";
                  String sender=  "&sender="  + sp.get_nom_pep(p2) ;
                  String nbr=     "&numbers=216" + sf.get_tel(p2.getId_prod()).get(i);
                  SMS.sendSms(msg,sender,nbr);  
                  System.out.println("*-*-*-*"+sp.get_nom_pep(p2));
            }
        }
        p.setDesc_prod(Desc_mod.getText());
        p.setType_prod(Type_mod.getValue());
        p.setNom_categorie(Cat_mod.getValue());
        p.setImg(path);
                        
        sp.Modifier_Produit(p,idprd);
        Nom_produit_mod.setText("");
        Desc_mod.setText("");
        Cat_mod.setValue("");
        Type_mod.setValue("");
        Prix_mod.setText("");
        Qte_mod.setText("");
        afficher_produit();
    }}

    @FXML
    private void refresh(MouseEvent event) {
        afficher_produit();
    }



 
}
