/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
  
import Entities.Produit; 
import com.jfoenix.controls.JFXTextField; 
import fxml.ListCellProduitController;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView; 
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;
import service.Services_produit;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author beerus
 */
public class ClientProduitController implements Initializable {

 

    ObservableList<Produit> data = FXCollections.observableArrayList();
    List<Produit> ls;
 
    @FXML    private ListView<Produit> listViewProduit;
    @FXML    private RadioButton btn_arbres;
    @FXML    private RadioButton btn_fruit;
    @FXML    private RadioButton btn_arbustes;
    @FXML    private RadioButton btn_arr;
    @FXML    private RadioButton btn_net;
    @FXML    private RadioButton btn_plant;
    @FXML    private RadioButton btn_org;
    @FXML    private RadioButton btn_min;
   
    @FXML
    private JFXTextField rech_prod;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        del_all_filters();
           
        filter(btn_arbres);
        filter(btn_arbustes);
        filter(btn_arr); 
        filter(btn_fruit);
        filter(btn_min);
        filter(btn_net);
        filter(btn_org); 
        filter(btn_plant);  
        tous_les_prods();
    }        
 Connection conn=Maconnexion.getinstance().getConn();
public void filter(RadioButton c){ 
         
         ObservableList<Produit> oblist = FXCollections.observableArrayList();
        
          
         c.setOnAction((ActionEvent event) -> {
             try{oblist.clear();
                    oblist.removeAll();
                 supp_filtr();
                 c.setSelected(true);
                 if (!c.isPressed()){   
                     ResultSet rs1 = conn.createStatement().executeQuery("SELECT * FROM Produit WHERE nom_categorie='"+c.getText()+"'"); 
             
                     while (rs1.next()) {
                         oblist.add(new Produit(rs1.getInt(1),rs1.getInt(2),rs1.getInt(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getFloat(8),rs1.getInt(9),rs1.getString(10),rs1.getString(11)));
                     }data = FXCollections.observableArrayList();
                      oblist.forEach((j) -> {data.add(j);});
                      
                       
                System.out.println(data);
                listViewProduit.setItems(data);
                listViewProduit.setCellFactory((ListView<Produit> param) -> new ListCellProduitController());
                 
                 }  } catch (SQLException ex) {
                     System.out.println("erreur"+ex.getMessage());} 
                
           });    
    }
 
    private void supp_filtr() {
        btn_arbres.setSelected(false);
        btn_arbustes.setSelected(false);
        btn_arr.setSelected(false); 
        btn_fruit.setSelected(false);
        btn_min.setSelected(false);
        btn_net.setSelected(false);
        btn_org.setSelected(false); 
        btn_plant.setSelected(false);
         
    }

    @FXML
    private void del_all_filters() {
        
        supp_filtr();
        Services_produit sp=new Services_produit();
        sp.Afficher_Produitc().forEach((j) -> {data.add(j);});
                      
                       
                System.out.println(data);
                listViewProduit.setItems(data);
                listViewProduit.setCellFactory((ListView<Produit> param) -> new ListCellProduitController());
                 
    }

    @FXML
    private void tous_les_prods() {
        data.clear();
                supp_filtr();
        Services_produit sp=new Services_produit();
        sp.Afficher_Produitc().forEach((j) -> {data.add(j);});
                      
                       
                listViewProduit.setItems(data);
                listViewProduit.setCellFactory((ListView<Produit> param) -> new ListCellProduitController());
          rech_prod.setText("");
    }

    @FXML
    private void rech(KeyEvent event) {
                data.clear();
                Services_produit sp=new Services_produit();
                sp.rech_prodcl(rech_prod.getText()).forEach((j) -> {data.add(j);});
                listViewProduit.setItems(data);
                listViewProduit.setCellFactory((ListView<Produit> param) -> new ListCellProduitController());
                 
    }

 

    }
    
