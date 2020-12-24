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
public class GestionUserController implements Initializable {

    @FXML
    private AnchorPane Anchorgestclient;
    @FXML
    private JFXButton btn_addClient;
    @FXML
    private JFXButton btn_editClient;
    @FXML
    private JFXButton btn_deleteClient;
    @FXML
    private TableView<User> tableViewAdmin1;
    @FXML
    private TableColumn<?, ?> id_admin1;
    @FXML
    private TableColumn<?, ?> pseudo1;
    @FXML
    private TableColumn<?, ?> nom1;
    @FXML
    private TableColumn<?, ?> prenom1;
    @FXML
    private TableColumn<?, ?> adresse1;
    @FXML
    private TableColumn<?, ?> email1;
    @FXML
    private TableColumn<?, ?> role_admin1;
    @FXML
    private TableColumn<?, ?> etatClient;
    @FXML
    private JFXTextField txt_login1;
    @FXML
    private JFXPasswordField txt_password1;
    @FXML
    private JFXTextField txt_adress1;
    @FXML
    private JFXTextField txt_email1;
    @FXML
    private JFXTextField txt_prenom1;
    @FXML
    private JFXTextField txt_nom1;
    @FXML
    private TextField chercher1;
    @FXML
    private Label lbl_id1;
    
    Connection conn=Maconnexion.getinstance().getConn();
    Statement stm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affUser();
    }    
 public void affUser()
    {
    pseudo1.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       id_admin1.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        adresse1.setCellValueFactory(new PropertyValueFactory<>("adresse"));
      role_admin1.setCellValueFactory(new PropertyValueFactory<>("role"));
        email1.setCellValueFactory(new PropertyValueFactory<>("email"));
       // cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        etatClient.setCellValueFactory(new PropertyValueFactory<>("etat"));
        //nom_pepiniere.setCellValueFactory(new PropertyValueFactory<>("nom_pepiniere"));
                          
            ObservableList<User> oblist=FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT pseudo,nom,prenom,"
                    +"adresse,email,role,etat,id_user FROM user where role='client'");
            while (rs.next()) {
               oblist.add(new User(rs.getString(1),  rs.getString(2),rs.getString(3),rs.getString(4),  rs.getString(5),rs.getString(6),rs.getString(7),
               rs.getInt(8)));
                System.out.println("  " +rs.getString(1)+" "+  rs.getString(2)+" "+rs.getString(3));
            }

        } catch (SQLException ex) {
            System.err.println("ERROR" + ex);

        }
        tableViewAdmin1.setItems(oblist);
    }
   @FXML
    private void addClient(MouseEvent event) {
        boolean status = Validate.ValidateEmail(txt_email1.getText());
     
        if (status) {
                User c = new User();
                c.setPseudo(txt_login1.getText());
                c.setNom(txt_nom1.getText());
                c.setPrenom(txt_prenom1.getText());
                c.setEmail(txt_email1.getText());
                c.setPassword(txt_password1.getText());
                c.setAdresse(txt_adress1.getText());
                c.setRole("client");
                c.setEtat("non confirmer");
                DaoUsers uDAO = new DaoUsers();
                uDAO.insertUser(c);
                System.out.println("insertion de client avec succes");
                
            } 
        else{JOptionPane.showMessageDialog(null,"Vérifier votre information");}
                affUser();

    }

    @FXML
    private void editClient(MouseEvent event) {
         User us = new User();

         int i = Integer.parseInt(lbl_id1.getText());
        us.setId_client(i);
        us.setNom(txt_nom1.getText());
        us.setPrenom(txt_prenom1.getText());
        us.setEmail(txt_email1.getText());
        us.setPseudo(txt_login1.getText());
        us.setPassword(txt_password1.getText());
        us.setAdresse(txt_adress1.getText());
        us.setRole("client");
        DaoUsers uDAO = new DaoUsers();
        uDAO.modifUser(us);
                affUser();

        
    }

    @FXML
    private void deleteClient(MouseEvent event) {
        User us = new User();
            DaoUsers uDAO = new DaoUsers();        
            int i = tableViewAdmin1.getSelectionModel().getSelectedItem().getId_client();
        uDAO.deleteUser(i);
        affUser();
    }
 


    @FXML
    private void ShowOnClick2(MouseEvent event) {
           try {
            User usr = (User) tableViewAdmin1.getSelectionModel().getSelectedItem();
            txt_nom1.setText(usr.getNom());
            txt_prenom1.setText(usr.getPrenom());
            txt_email1.setText(usr.getEmail());
            txt_login1.setText(usr.getPseudo());
            txt_adress1.setText(usr.getAdresse());
               String a = String.valueOf(usr.getId_client());
            lbl_id1.setText(a);
        } catch (Exception e) {
            
        }
    }

    @FXML
    private void rechercheUser(KeyEvent event) {
    }
    
}
