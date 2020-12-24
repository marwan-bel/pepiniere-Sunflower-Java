/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXComboBox;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import Entities.GestionServices;
import service.ServicePep;
/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class ClientServiceController{

  @FXML
    private ListView<GestionServices> listViewService;
    @FXML
    private JFXComboBox<String> comboType;

    @FXML
    private JFXComboBox<String> comboRegion;

    ObservableList<GestionServices> data;
    List<GestionServices> ls;
    ObservableList<String> ls_type;
    ObservableList<String> ls_region;

    /**
     * Initializes the controller class.
     */
   public void initialize() {
        data = FXCollections.observableArrayList();
        ls_type = FXCollections.observableArrayList();
        ls_region = FXCollections.observableArrayList();
        service.ServicePep cs = new ServicePep();
        ls_region.add("all");
        ls_type.add("all");
        ls = cs.Afficher_servicef();
        
        ls.forEach((j) -> {
            data.add(j);
            if (!ls_type.contains(j.getType())) {
                ls_type.add(j.getType());
            }
            if (!ls_region.contains(j.getRegion())) {
                ls_region.add(j.getRegion());
            }
        });
        comboRegion.setItems(ls_region);
        comboType.setItems(ls_type);
        
        comboRegion.getSelectionModel().select(0);
        comboType.getSelectionModel().select(0);
        
        listViewService.setItems(data);
        listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellServiceController());
        comboRegion.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {
            data = FXCollections.observableArrayList();
            
//filter by 2 ( region + type )
            if (!c.equals("all") && !comboType.getSelectionModel().getSelectedItem().equals("all")) {
                ls.forEach((j) -> {
                    if (j.getRegion().equals(c) && j.getType().equals(comboType.getSelectionModel().getSelectedItem())) {
                        data.add(j);
                    }
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellServiceController());
              //filter by REgion 
            } else if ((!c.equals("all")) && comboType.getSelectionModel().getSelectedItem().equals("all")) {
                ls.forEach((j) -> {
                    if (j.getRegion().equals(comboRegion.getSelectionModel().getSelectedItem())) {
                        data.add(j);
                    }
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellServiceController());
              //filter by type 
            } else if (c.equals("all") && !comboType.getSelectionModel().getSelectedItem().equals("all")) {;
                ls.forEach((j) -> {
                    if (j.getType().equals(comboType.getSelectionModel().getSelectedItem())) {
                        data.add(j);
                    }
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellServiceController());
            //No filter
            } else {
                ls.forEach((j) -> {
                    data.add(j);
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellServiceController());
            }

        });
        comboType.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {
            data = FXCollections.observableArrayList();
            //filter by 2 ( region + type )
            if (!comboRegion.getSelectionModel().getSelectedItem().equals("all") && !comboType.getSelectionModel().getSelectedItem().equals("all")) {
                ls.forEach((j) -> {
                    if (j.getRegion().equals(comboRegion.getSelectionModel().getSelectedItem()) && j.getType().equals(comboType.getSelectionModel().getSelectedItem())) {
                        data.add(j);
                    }
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellServiceController());
            //filter by REgion 
            } else if (!comboRegion.getSelectionModel().getSelectedItem().equals("all") && comboType.getSelectionModel().getSelectedItem().equals("all")) {
                ls.forEach((j) -> {
                    if (j.getRegion().equals(comboRegion.getSelectionModel().getSelectedItem())) {
                        data.add(j);
                    }
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellServiceController());
            //filter by type 
            } else if (comboRegion.getSelectionModel().getSelectedItem().equals("all") && !comboType.getSelectionModel().getSelectedItem().equals("all")) {
                ls.forEach((j) -> {
                    if (j.getType().equals(comboType.getSelectionModel().getSelectedItem())) {
                        data.add(j);
                    }
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellServiceController());
            // No filter
            } else {
                ls.forEach((j) -> {
                    data.add(j);
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellServiceController());
            }
        });
    }  
    
}
