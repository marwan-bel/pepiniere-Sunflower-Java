/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Produit;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import service.Services_produit;

/**
 * FXML Controller class
 *
 * @author beerus
 */
public class Ajouter_produitController implements Initializable {

    @FXML    private AnchorPane zone_ajout_prod;
    @FXML    private Button btn_add;
    @FXML    private ImageView show_img;
    @FXML    private JFXTextField nom_prod_ajout;
    @FXML    private JFXTextField prix_prod_ajout;
    @FXML    private JFXTextField qte_prod_ajout;
    @FXML    private JFXComboBox<String> cbtype;
    @FXML    private JFXComboBox<String> cbcateg;
    @FXML    private JFXTextArea descrip;
             private String path;
             Services_produit sp=new Services_produit();
             ObservableList<String> lst =FXCollections.observableArrayList("Plantes","Equipements","Engrais");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbtype.setItems(lst);  
    }    

    @FXML
    private void ajouter_prod(ActionEvent event) { 
        Produit  p=new Produit(sp.get_id_user(),nom_prod_ajout.getText(),Float.parseFloat(prix_prod_ajout.getText()), Integer.parseInt(qte_prod_ajout.getText()),  path, descrip.getText(), cbcateg.getValue());
      // if ( nom_prod_ajout.getText().matches("[A-Za-z]"))&&(prix_prod_ajout.getText().matches("[0-9]*"))&&(qte_prod_ajout.getText().matches("[0-9]*"))&&(path!=null)&&(!descrip.getText().matches("[A-Za-z]"))) 
       sp.Ajouter_Produit(p);  
      /* cbcateg.setValue("");
       cbtype.setValue("");
       nom_prod_ajout.setText("");
       prix_prod_ajout.setText("");
       qte_prod_ajout.setText("");
       descrip.setText("");*/
    }

@FXML
    private void choose() throws MalformedURLException {
        FileChooser f=new FileChooser(); 
        File f2=   f.showOpenDialog(null);   
        String x=f2.toString().replaceAll("C:\\\\wamp64\\\\www\\\\final_pepiniere\\\\web\\\\uploads\\\\images" , "");  
 
 
        Image imgc = new Image (f2.toURI().toURL().toString());
         
         show_img.setImage(imgc); 
         path=x;
        }
 

    @FXML
    private void testprix(KeyEvent event) {
          if (prix_prod_ajout.getText().matches("[0-9]*")==false)
       {prix_prod_ajout.setStyle("-fx-border-color: red;");}
        else{   prix_prod_ajout.setStyle("-fx-border-color: #0ad200;");}
    }

    @FXML
    private void categ_chng_add(ActionEvent event) {
                  cbtype.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                cbcateg.setItems(sp.get_categorie(cbtype.getValue()));
            }
    });
    }
    
    @FXML
    private void testqte(KeyEvent event) {   
        if ((qte_prod_ajout.getText().matches("[0-9]*")==false))
            {qte_prod_ajout.setStyle("-fx-border-color: red;");}
        else{qte_prod_ajout.setStyle("-fx-border-color: #0ad200;");} 

    }
    
}
