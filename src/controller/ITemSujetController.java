/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Commentaire;
import Entities.Sujet;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXMLLoader;
import fxml.TestController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.DAOLigneCommande;
import service.ServiceCommentaire;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class ITemSujetController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Text txt_nomagent;
    @FXML
    private Text txt_nomsujet;
    @FXML
    private JFXTextArea txt_desc;
    @FXML
    private Label txt_date;
    @FXML
    private ImageView img;
    @FXML
    private ImageView btn_voirsujet;
    ObservableList<Commentaire> datacomm;
    List<Commentaire> lscomm;
private  AjouterCommentaireController addcontroller;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public ITemSujetController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ITemSujet.fxml"));
        fxmlLoader.setController(this);
        
        
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(Sujet string) {
        txt_nomagent.setText(string.getNom_agent());
        txt_nomsujet.setText("" + string.getNom_sujet());
        txt_desc.setText(string.getDescription());
        txt_desc.setEditable(false);
        txt_date.setText(string.getDate_creation() + "");
       javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/uploads/" + string.getImage());
        img.setImage(image);
        
    }

    public HBox getBox() {
        return Hbox;
    }
    
 
}
