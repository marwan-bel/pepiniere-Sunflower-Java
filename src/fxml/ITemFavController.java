/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;
 
import Entities.Produit;
import controller.Avis_prdController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.DAOLigneCommande;
import service.DaoUsers;
import service.Services_favoris;
import service.Services_produit;

/**
 * FXML Controller class
 *
 * @author beerus
 */
public class ITemFavController implements Initializable {

    @FXML    private HBox Hbox;
    @FXML    private ImageView img;
    @FXML    private Text txtnom;
    @FXML    private Text lblnompep;
    @FXML    private TextArea txtdesc;
    @FXML    private Label lblPrix;
    @FXML    private Label lblDispo;
    @FXML    private ImageView btn_delfavorit;
    @FXML    private ImageView btn_addtocart;
    @FXML    private ImageView btn_detailview;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public ITemFavController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iTemFav.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }  

        Services_produit sp=new Services_produit();
        public void setInfo(Produit p) {
        txtnom.setText(p.getNom_prod());
        txtdesc.setText(p.getDesc_prod());
        lblnompep.setText(sp.get_nom_pep(p));
        if (p.getQte_prod()==0) {lblDispo.setTextFill(Color.RED);lblDispo.setText("Indisponible");}
   else if (p.getQte_prod()>0) {lblDispo.setTextFill(Color.GREEN);lblDispo.setText("Disponible");}
        lblPrix.setText(p.getPrix_prod()+ " TND");
        javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/uploads/" + p.getImg());
        img.setFitWidth(152);
        img.setFitHeight(130);
        img.setImage(image);
        
       
        btn_delfavorit.setOnMouseClicked((event) -> {
        Services_favoris sf=new Services_favoris();
        sf.Supprimer_favoris(p.getId_prod());
 
            });
 
          btn_addtocart.setOnMouseClicked((event) -> {
            try {
                DAOLigneCommande lcmd=new DAOLigneCommande();
                DaoUsers user =new DaoUsers();
                int id_user=0,x=0;
                id_user=user.ConectedUser();
                x=lcmd.CountPanier(p.getId_user());
                
                boolean existe=lcmd.verifContenu(id_user, p.getId_prod());
                if (existe)
                {
                    lcmd.AjouterLigneCmd(p.getId_prod(),1,id_user,p.getPrix_prod());
                 
                }
                else {JOptionPane.showMessageDialog(null,"Produit deja existe dans votre panier merci de la consulter");}
                
            } catch (SQLException ex) {
                System.out.println("erreur add to panier "+ex.getMessage());
            }
                 }); 
       
                   btn_detailview.setOnMouseClicked((event) -> {
            try {
                    FXMLLoader loader5 = new FXMLLoader();
                    loader5.setLocation(getClass().getResource("/fxml/Avis_prd.fxml"));
                    Stage dialogStage = new Stage();
                    AnchorPane page = (AnchorPane) loader5.load();
                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);
                    Avis_prdController ac =loader5.getController();
                    ac.setp(p);
                    dialogStage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
             
        });
          
          
          
    }

    public HBox getBoxf() {
        return Hbox;
    }
}
