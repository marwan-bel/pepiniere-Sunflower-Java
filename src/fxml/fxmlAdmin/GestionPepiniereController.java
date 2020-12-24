/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml.fxmlAdmin;

import Entities.User;
import Entities.Validate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import service.DaoUsers;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class GestionPepiniereController implements Initializable {

    @FXML
    private AnchorPane Anchorgestclient1;
    @FXML
    private JFXButton btn_addClient1;
    @FXML
    private JFXButton btn_editClient1;
    @FXML
    private JFXButton btn_deleteClient1;
    @FXML
    private TableView<User> tableViewAdmin11;
    @FXML
    private TableColumn<?, ?> id_admin11;
    @FXML
    private TableColumn<?, ?> nom11;
    @FXML
    private TableColumn<?, ?> prenom11;
    @FXML
    private TableColumn<?, ?> adresse11;
    @FXML
    private TableColumn<?, ?> email11;
    @FXML
    private TableColumn<?, ?> role_admin11;
    @FXML
    private TableColumn<?, ?> etatClient1;
    @FXML
    private TableColumn<?, ?> cin;
    @FXML
    private TableColumn<?, ?> nom_pep;
    @FXML
    private JFXTextField txt_login11;
    @FXML
    private JFXPasswordField txt_password11;
    @FXML
    private JFXTextField txt_adress11;
    @FXML
    private JFXTextField txt_email11;
    @FXML
    private JFXTextField txt_prenom11;
    @FXML
    private JFXTextField txt_nom11;
    @FXML
    private JFXTextField nompep;
    @FXML
    private TextField chercher11;
    @FXML
    private Label lbl_id11;

    /**
     * Initializes the controller class.
     */
      Connection conn=Maconnexion.getinstance().getConn();
    Statement stm;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affAgent();
    }    
public void affAgent()
    {   nom11.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom11.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       id_admin11.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        adresse11.setCellValueFactory(new PropertyValueFactory<>("adresse"));
      role_admin11.setCellValueFactory(new PropertyValueFactory<>("role"));
        email11.setCellValueFactory(new PropertyValueFactory<>("email"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        etatClient1.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nom_pep.setCellValueFactory(new PropertyValueFactory<>("nom_pepiniere"));
                          
            ObservableList<User> oblist2=FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT nom,prenom,"
                    +"adresse,email,role,etat,id_user,cin,nom_pepiniere FROM user where role='agent'");
            while (rs.next()) {
               oblist2.add(new User(rs.getString(1),  rs.getString(2),rs.getString(3),rs.getString(4),  rs.getString(5),rs.getString(6),rs.getInt(7),
               rs.getString(8),rs.getString(9)));
                System.out.println("  " +rs.getString(1)+" "+  rs.getString(2)+" "+rs.getString(3));
            }

        } catch (SQLException ex) {
            System.err.println("ERROR" + ex);

        }
        tableViewAdmin11.setItems(oblist2);
    }
 @FXML
    private void addAgent(MouseEvent event) {
        boolean status = Validate.ValidateEmail(txt_email11.getText());
     
        if (status) {
                User c = new User();
                c.setCin(txt_login11.getText());
                c.setNom(txt_nom11.getText());
                c.setPrenom(txt_prenom11.getText());
                c.setEmail(txt_email11.getText());
                c.setPassword(txt_password11.getText());
                c.setAdresse(txt_adress11.getText());
                c.setNom_pepiniere(nompep.getText());
                c.setRole("agent");
                c.setEtat("non confirmer");
                DaoUsers uDAO = new DaoUsers();
                uDAO.insertUser(c);
                System.out.println("insertion de l'agent avec succes");
                
            } 
        else{JOptionPane.showMessageDialog(null,"Vérifier votre information");}
                affAgent();
    }

    @FXML
    private void editAgent(MouseEvent event) {
         User us = new User();

         int i = Integer.parseInt(lbl_id11.getText());
        us.setId_client(i);
        us.setNom(txt_nom11.getText());
        us.setPrenom(txt_prenom11.getText());
        us.setEmail(txt_email11.getText());
        us.setCin(txt_login11.getText());
        us.setPassword(txt_password11.getText());
        us.setAdresse(txt_adress11.getText());
        us.setNom_pepiniere(nompep.getText());
        us.setEtat("confirmer");
        us.setRole("agent");
        DaoUsers uDAO = new DaoUsers();
        uDAO.modifUser(us);
                 affAgent();
       
    }

    @FXML
    private void deleteAgent(MouseEvent event) {
         User us = new User();
            DaoUsers uDAO = new DaoUsers();        
            int i = tableViewAdmin11.getSelectionModel().getSelectedItem().getId_client();
        uDAO.deleteUser(i);
         affAgent();
    }
   

    @FXML
    private void ShowOnClick3() {
        try {
            User usr = (User) tableViewAdmin11.getSelectionModel().getSelectedItem();
            txt_nom11.setText(usr.getNom());
            txt_prenom11.setText(usr.getPrenom());
            txt_email11.setText(usr.getEmail());
            txt_login11.setText(usr.getCin());
            txt_adress11.setText(usr.getAdresse());
             nompep.setText(usr.getNom_pepiniere());
               String a = String.valueOf(usr.getId_client());
            lbl_id11.setText(a);
        } catch (Exception e) {
            
        }

    }

    @FXML
    private void rechercheAgent(KeyEvent event) {
    }
    
}
