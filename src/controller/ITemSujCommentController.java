/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Sujet;
import com.jfoenix.controls.JFXTextArea;
import fxml.TestController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import service.DAOLigneCommande;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class ITemSujCommentController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   public ITemSujCommentController()
    {
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ITemSujComment.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
      public void setInfo(Sujet string) {
        
        txt_nomsujet.setText(""+string.getNom_sujet());
        txt_desc.setText(string.getDescription());
        txt_desc.setEditable(false);
        
        System.out.println(string);
   btn_addcomment.setOnMouseClicked((event) -> {
                                
                   
                    });

    }

    public HBox getBox() {
        return Hbox;
    }
}
