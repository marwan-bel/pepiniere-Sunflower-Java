/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import Entities.Commentaire;
import Entities.Produit;
import com.jfoenix.controls.JFXTextArea;
import fxml.TestController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.DAOLigneCommande;
import service.DaoUsers;
import service.ServiceCommentaire;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class ItemCommentController {

    @FXML
    private HBox Hbox;
    @FXML
    private JFXTextArea txt_AddComment;
    @FXML
    private Text txt_nom_client;
    @FXML
    private ImageView btn_jaime;
    @FXML
    private Text txt_jaime;
    @FXML
    private ImageView btn_signal;
    @FXML
    private Text txt_signal;
    @FXML
    private Text txt_date;
    @FXML
    private ImageView btn_deleteComment;
    @FXML
    private ImageView btn_editComment;
    
    /**
     * Initializes the controller class.
     */
  
    public ItemCommentController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ItemComment.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
static int i=0,x=0,l=0;

    public void setInfo(Commentaire string) {
        txt_nom_client.setText(string.getNom_client());
        txt_signal.setText(""+string.getNbr_signal());
        txt_AddComment.setText(string.getCommentaire());
        txt_AddComment.setEditable(false);
        txt_date.setText(string.getDate_commentaire()+"");
        ServiceCommentaire servC = new ServiceCommentaire();
        i=string.getId_commentaire();
        try {
            x = servC.countlike(i);
        } catch (SQLException ex) {
            System.out.println("setInfo itemcomment "+ex.getMessage());
        }
        txt_jaime.setText(x+" J'aime");
        btn_deleteComment.setOnMouseClicked((event) -> {
            
            servC.supprimerCommentaire(string.getId_commentaire());
            
        });
        btn_jaime.setOnMouseClicked((event) -> {
            try {
                
                i=string.getId_commentaire();
                x = servC.countlike(i);
                servC.addlike(i);
                l = servC.countlike(i);
                txt_jaime.setText(l+" J'aime");
            } catch (SQLException ex) {
                System.out.println("btnjaime itemcomment "+ex.getMessage());
            }
            
        });
        
        btn_signal.setOnMouseClicked((event) -> {
            try {
               if(l==0)
               {
                int x=string.getId_sujet();
                servC.signalerClient(string.getId_commentaire());
                
                int i= servC.nbrSignalCom(string.getId_commentaire());
                txt_signal.setText(""+i);
                l++;
               }
            } catch (SQLException ex) {
                
            }
           
        });       
    }
  
    
    public HBox getBox() {
        return Hbox;
    }
}
