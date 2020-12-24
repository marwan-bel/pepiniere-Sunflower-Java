/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Entities.Produit;
import com.jfoenix.controls.JFXButton;
import controller.AccueilController;
import controller.AjouterCommentaireController;
import controller.ListCellLign_cmd;
import controller.PanierCmdController;
import controller.ServiceEmailerController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;
import service.DAOLigneCommande;
import service.DaoUsers;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class ITemLignecmdController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private ImageView img;
    @FXML
    private Text txtnom;

    @FXML
    private Label lblPrix;
    @FXML
    private Text txtqte;
    @FXML
    private ImageView btn_del;
    @FXML
    private ImageView btn_addqte;
    @FXML
    private ImageView btn_delqte;
    AnchorPane panier, holderPane;
    @FXML
    private Label txt_prix2;

        
 ObservableList<Produit> data;
    List<Produit> ls;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public ITemLignecmdController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ITemLignecmd.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(Produit string) {
        txtnom.setText(string.getNom_prod());

        txt_prix2.setText(string.getPrix_prod() + " TND");
        System.out.println(string);
        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/uploads/" + string.getImg());
        img.setImage(image);
        TestController notif = new TestController();
        DAOLigneCommande sp = new DAOLigneCommande();
        try {
            lblPrix.setText(sp.newprix(string.getId_prod()) + " TND");
        } catch (SQLException ex) {

        }
        btn_addqte.setOnMouseClicked((event) -> {
            try {
                int qtt1 = 0;
                boolean existe = sp.Verifqte2(string.getId_prod());
                int qtt = 0;
                if (existe) {
                    sp.EditQteLigneCmd(string.getId_prod());
                    int i = sp.newprix(string.getId_prod());
                    qtt = sp.CalculeQte(string.getId_prod());
                    txtqte.setText("" + qtt);
                    lblPrix.setText(i + " TND");
                  
                } else {
                    JOptionPane.showMessageDialog(null, "Vous pouvez pas ajouter de quantité notre stock est limite");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        int x = 0;
        try {
            x = sp.CalculeQte(string.getId_prod());
        } catch (SQLException ex) {

        }
        txtqte.setText(x + "");
        btn_delqte.setOnMouseClicked((event) -> {
            try {
                int qtt = 0;
                boolean existe = sp.Verifqte3(string.getId_prod());
                if (existe) {

                    sp.EditQteLigneCmd2(string.getId_prod());
                    int i = sp.newprix(string.getId_prod());
                    qtt = sp.CalculeQte(string.getId_prod());
                    txtqte.setText(qtt + "");
                    lblPrix.setText(i + " TND");
                    
                }
            } catch (SQLException ex) {
                
            }

        });
        btn_del.setOnMouseClicked((event) -> {
        
     
                sp.deleteLigneCmd(string.getId_prod());
        
        });

    }

    //*********Gazzah firas*******************\\
    public void notif() throws SQLException {
        int id_user = 0, x = 0;
        DaoUsers user = new DaoUsers();
        id_user = user.ConectedUser();
        DAOLigneCommande lcmd = new DAOLigneCommande();
        x = lcmd.CountPanier(id_user);
        //notif.setText(Integer.toString(x));

    }

    public HBox getBox() {
        return Hbox;
    }

}
