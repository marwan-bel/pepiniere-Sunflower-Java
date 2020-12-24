package controller;

import Entities.Commande;
import Entities.Produit;
import Entities.Validate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.DAOLigneCommande;
import service.DaoCommande;
import service.DaoUsers;
import service.PDFApi;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class PanierCmdController implements Initializable {
    @FXML
    private AnchorPane PanierAnchor;
    @FXML
    private AnchorPane FinalisserAnchor;
    @FXML
    private Label nomprnm;
    @FXML
    private Label adressuser;
    @FXML
    private Label teluser;
    @FXML
    private Label totale;
    @FXML
    private JFXTextField coupon;
    @FXML
    private JFXButton btn_coupon;
    @FXML
    private JFXButton btn_confirmation;
    @FXML
    private AnchorPane FinalisserAnchor1;
    @FXML
    private JFXTextField txt_nomcl;
    @FXML
    private JFXTextField txt_prncl;
    @FXML
    private JFXTextField txt_adrcl;
    @FXML
    private JFXTextField txt_numcl;
    @FXML
    private JFXTextField txt_mailcl;
    Connection conn = Maconnexion.getinstance().getConn();
    @FXML
    public ListView<Produit> listViewService;
    
 ObservableList<Produit> data;
    List<Produit> ls;
  
    /**
     * Initializes the controller class.
     */

   @Override
    public void initialize(URL url, ResourceBundle rb) {
                PanierAnchor.setVisible(true);
              DAOLigneCommande cs=new DAOLigneCommande();
         
           
              data = FXCollections.observableArrayList();
     
       //Services_produit cs = new Services_produit();
        ls =  cs.Afficher_LigneCmd();
        ls.forEach((j) -> {
            data.add(j);
            
        });
        listViewService.setItems(data);
        listViewService.setCellFactory((ListView<Produit> param) -> new ListCellLign_cmd());
       
        
    }
    boolean remisee = false;

    public void finalCmd() {
        try {

            DAOLigneCommande lcmd = new DAOLigneCommande();
            boolean existe = lcmd.VerifPanier();
            if (existe) {
                float somme = 0;
                FinalisserAnchor.setVisible(true);
                PanierAnchor.setVisible(false);
                FinalisserAnchor1.setVisible(false);
                ResultSet rs = conn.createStatement().executeQuery("SELECT adresse,nom,prenom,sexe FROM user where id=1");
                while (rs.next()) {
                   
                    nomprnm.setText(rs.getString(2) + " " + rs.getString(3));
                    adressuser.setText(rs.getString(1));
                    teluser.setText("+216 " + rs.getString(4));
                }

                ResultSet rs2 = conn.createStatement().executeQuery("SELECT montant FROM ligne_cmd where id_client=1");
                while (rs2.next()) {
                    somme = somme + rs2.getFloat(1);

                }
                String y = Float.toString(somme);
                final float montants = somme;
                totale.setText("Totale=" + y + " DT");
                    PDFApi pdf=new PDFApi();
                float x = 0;
                final float sum = somme * 0.1f;
                final float prix3 = montants;
                final float prix4 = montants - sum;
                btn_coupon.setOnAction((ActionEvent event2)
                        -> {
                    if ("123456".equals(coupon.getText()) || "654321".equals(coupon.getText()) || "0000".equals(coupon.getText())) {
                        totale.setText("Totale=" + prix4 + " DT  félicitation vous avez un remise de 10%");
                    }
                    remisee = true;

                });

                DaoUsers user = new DaoUsers();

                //System.out.println("identifiant user="+id);
                DaoCommande cmd = new DaoCommande();
                //id_client,etat_cmd,mode_p,lieu_liv,montant
                btn_confirmation.setOnAction((ActionEvent event2)
                        -> {

                    try {
                        
                        try {
                            final int id = 1;
                            if (remisee) {
                                Commande commande = new Commande(id, "En attente", "paiment a la livraison", user.gestUserAdr(), prix4);
                                cmd.AjouterCmd(commande);
                                pdf.ImprimerProduitacheter(prix4, sum);
                                lcmd.deleteLigneCmdwhenconfirmed(id);
                                
                            } else {
                                Commande commande = new Commande(id, "commande non confirmer", "paiment a la livraison", user.gestUserAdr(), montants);
                                cmd.AjouterCmd(commande);
                                pdf.ImprimerProduitacheter(montants, 0f);
                                lcmd.deleteLigneCmdwhenconfirmed(id);
                            }
                            
                        } catch (SQLException ex) {
                            
                        } catch (IOException ex) {
                            
                        }
                           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/test.fxml"));
    Parent root = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
                       
                        stage.setScene(new Scene(root));
                        
                        stage.setMaximized(true);
                        
                        stage.getIcons().add(new Image("/Icons/sunflower.png"));
                        stage.setTitle("SunFlower");
                        
                        stage.show();
                        final Stage stage2 = (Stage) btn_confirmation.getScene().getWindow();
    stage2.close();
                    } catch (IOException ex) {
                        Logger.getLogger(PanierCmdController.class.getName()).log(Level.SEVERE, null, ex);
                       
                    }
                    FinalisserAnchor.setVisible(false);
                });
            } else {
                JOptionPane.showMessageDialog(null, "Il faut ajouter des produits au Panier pour finaliser votre commande");
            }
        } catch (SQLException ex) {
            
        }
    }


    @FXML
    private void finaliserCmd(MouseEvent event) {
       
        finalCmd();
    }

    @FXML
    private void editAdr(MouseEvent event) throws SQLException {
        FinalisserAnchor.setVisible(false);
        FinalisserAnchor1.setVisible(true);
        ResultSet rs = conn.createStatement().executeQuery("SELECT nom,prenom,email,telephone,adresse FROM user where connected=1");
        while (rs.next()) {
            txt_nomcl.setText(rs.getString(1));
            txt_prncl.setText(rs.getString(2));
            txt_mailcl.setText(rs.getString(3));
            txt_numcl.setText(rs.getString(4));
            txt_adrcl.setText(rs.getString(5));
        }

    }

    @FXML
    private void ConfirmCMD(MouseEvent event) {
    }

    @FXML
    private void cancelChange(MouseEvent event) {
        FinalisserAnchor.setVisible(true);
        FinalisserAnchor1.setVisible(false);
    }

    @FXML
    private void changeAdresse(MouseEvent event) {
        Validate v = new Validate();

        String nom = txt_nomcl.getText();
        String prn = txt_prncl.getText();
        String num = txt_numcl.getText();
        String mail = txt_mailcl.getText();
        String adr = txt_adrcl.getText();
        boolean status = v.ValidateEmail(mail);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("vous ete sur de changer votre adresse vers");
        alert.setContentText(adr);
        if (status) {
                if(txt_adrcl.getText().length()!=0){
                    
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    String req_conn = "update user set nom=? ,prenom=?"
                            + ",adresse=?,telephone=?,email=? where connected=1";
                    PreparedStatement prt = conn.prepareStatement(req_conn);

                    prt.setString(1, nom);
                    prt.setString(2, prn);
                    prt.setString(4, num);
                    prt.setString(5, mail);
                    prt.setString(3, adr);
                    prt.executeUpdate();
                    finalCmd();
                } catch (SQLException s) {
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }
                }else{ JOptionPane.showMessageDialog(null, "Merci de saisir une adresse de livraison");}
        } else {
            JOptionPane.showMessageDialog(null, "Verifier votre E-mail");
        }
    }



}
