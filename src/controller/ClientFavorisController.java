/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import fxml.ListCellFavorisController;
import Entities.Produit;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import service.Services_favoris; 

/**
 * FXML Controller class
 *
 * @author beerus
 */

public class ClientFavorisController implements Initializable {

    @FXML
    private ListView<Produit> listViewFavoris;
        ObservableList<Produit> data;
    List<Produit> ls =new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           data = FXCollections.observableArrayList();
        Services_favoris sf = new Services_favoris();
        
        sf.Afficher_favoris().stream().map((aIterator) -> aIterator.getId_prod()).map((id_prod) -> sf.get_prod(id_prod)).forEachOrdered((p) -> {
            ls.add(p);
        });  
         ls.forEach(new Consumer<Produit>() {
               @Override
               public void accept(Produit j) {
 
                data.add(j);
               }
           }); 
        listViewFavoris.setItems(data);
        listViewFavoris.setCellFactory((ListView<Produit> param) -> new ListCellFavorisController());
       
    }    
    
}
