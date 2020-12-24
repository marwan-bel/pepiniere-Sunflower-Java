/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agent;

import Entities.GestionServices;
import service.ServicePep;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shmhi
 */
public class AgentServiceController {

    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton btnDelete;
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
    
    
  

    public void initialize() {
        data = FXCollections.observableArrayList();
        ls_type = FXCollections.observableArrayList();
        ls_region = FXCollections.observableArrayList();

        ls_region.add("all");
        ls_type.add("all");
        
        btnDelete.setVisible(false);
        update.setVisible(false);
        ServicePep cs = new ServicePep();
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
        listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellagentController());
        listViewService.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {
            btnDelete.setVisible(true);
            update.setVisible(true);
        });
        comboRegion.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {
            data = FXCollections.observableArrayList();
            if (!c.equals("all") && !comboType.getSelectionModel().getSelectedItem().equals("all")) {
                ls.forEach((j) -> {
                    if (j.getRegion().equals(c) && j.getType().equals(comboType.getSelectionModel().getSelectedItem())) {
                        data.add(j);
                    }
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellagentController());
                //filter by REgion 
            } else if ((!c.equals("all")) && comboType.getSelectionModel().getSelectedItem().equals("all")) {
                ls.forEach((j) -> {
                    if (j.getRegion().equals(comboRegion.getSelectionModel().getSelectedItem())) {
                        data.add(j);
                    }
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellagentController());
                //filter by type 
            } else if (c.equals("all") && !comboType.getSelectionModel().getSelectedItem().equals("all")) {;
                ls.forEach((j) -> {
                    if (j.getType().equals(comboType.getSelectionModel().getSelectedItem())) {
                        data.add(j);
                    }
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellagentController());
                //No filter
            } else {
                ls.forEach((j) -> {
                    data.add(j);
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellagentController());
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
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellagentController());
                //filter by REgion 
            } else if (!comboRegion.getSelectionModel().getSelectedItem().equals("all") && comboType.getSelectionModel().getSelectedItem().equals("all")) {
                ls.forEach((j) -> {
                    if (j.getRegion().equals(comboRegion.getSelectionModel().getSelectedItem())) {
                        data.add(j);
                    }
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellagentController());
                //filter by type 
            } else if (comboRegion.getSelectionModel().getSelectedItem().equals("all") && !comboType.getSelectionModel().getSelectedItem().equals("all")) {
                ls.forEach((j) -> {
                    if (j.getType().equals(comboType.getSelectionModel().getSelectedItem())) {
                        data.add(j);
                    }
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellagentController());
                // No filter
            } else {
                ls.forEach((j) -> {
                    data.add(j);
                });
                listViewService.getItems().clear();
                listViewService.setItems(data);
                listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellagentController());
            }
        });
    }

    @FXML
    private void ShowAdd(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Agent/AddService.fxml"));
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
                        btnDelete.setVisible(false);
            update.setVisible(false);
            ServicePep cs = new ServicePep();
            ls = cs.Afficher_servicef();
            ls.forEach((j) -> {
                data.add(j);
            });
            listViewService.setItems(data);
            listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellagentController());
            listViewService.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {
                btnDelete.setVisible(true);
                update.setVisible(true);
            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void showUpdate(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Agent/EditService.fxml"));
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            EditServiceController es = loader.getController();
            es.setS(listViewService.getSelectionModel().getSelectedItem());
            dialogStage.showAndWait();
            btnDelete.setVisible(false);
            update.setVisible(false);
            ServicePep cs = new ServicePep();
            ls = cs.Afficher_servicef();
            ls.forEach((j) -> {
                data.add(j);
            });
            listViewService.setItems(data);
            listViewService.setCellFactory((ListView<GestionServices> param) -> new ListCellagentController());
            listViewService.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {
                btnDelete.setVisible(true);
                update.setVisible(true);
            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void DeleteService(ActionEvent event) {
        int selectedIndex = listViewService.getSelectionModel().getSelectedIndex();
        GestionServices selectedService = listViewService.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirmation Dialog");
            a.setHeaderText("Really ?");
            a.setContentText("Are you ok with this?");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) {
                listViewService.getItems().remove(selectedIndex);
                ServicePep sp = new ServicePep();
                sp.supprimer(selectedService.getId_service());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Service Selected");
            alert.setContentText("Please select a Service in the table.");
            alert.showAndWait();
        }
    }

}
