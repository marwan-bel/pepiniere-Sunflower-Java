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
public class GestionAdminController implements Initializable {

    @FXML
    private AnchorPane Anchorgestadmin;
    @FXML
    private JFXButton btn_addAmin;
    @FXML
    private JFXButton btn_editAdmin;
    @FXML
    private JFXButton btn_deleteAdmin;
    @FXML
    private TableView<User> tableViewAdmin;
    @FXML
    private TableColumn<?, ?> id_admin;
    @FXML
    private TableColumn<?, ?> pseudo;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> adresse;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> role_admin;
    @FXML
    private JFXTextField txt_login;
    @FXML
    private JFXPasswordField txt_password;
    @FXML
    private JFXTextField txt_adress;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXTextField txt_prenom;
    @FXML
    private JFXTextField txt_nom;
    @FXML
    private TextField chercher;
    @FXML
    private Label lbl_id;

    /**
     * Initializes the controller class.
     */
    Connection conn=Maconnexion.getinstance().getConn();
    Statement stm;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affAdmin();
    }    
 public void affAdmin()
    {       pseudo.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            id_admin.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            role_admin.setCellValueFactory(new PropertyValueFactory<>("role"));
            ObservableList<User> oblist=FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT pseudo,nom,prenom,"
                    +"adresse,email,role,id_user FROM user where role='admin'");
            while (rs.next()) {
               oblist.add(new User(rs.getString(1),  rs.getString(2),rs.getString(3),rs.getString(4),  rs.getString(5), rs.getString(6),  rs.getInt(7)));
                System.out.println("  " +rs.getString(1)+" "+  rs.getString(2)+" "+rs.getString(3));
            }

        } catch (SQLException ex) {
            System.err.println("ERROR" + ex);

        }
        tableViewAdmin.setItems(oblist);        
    }
 @FXML
    private void rechercheAdmin(KeyEvent key) {
          pseudo.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            
              ObservableList<User> oblistu=FXCollections.observableArrayList();
           
             if(chercher.getText().length()!=0)
            {  
        try {
           
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM user WHERE role='admin' and pseudo LIKE '%" + chercher.getText() + "%'"
                        + "UNION SELECT * FROM user WHERE role='admin' and nom LIKE '%" + chercher.getText() + "%'"
                        + "UNION SELECT * FROM user WHERE role='admin' and prenom LIKE '%" + chercher.getText() + "%'"
                        + "UNION SELECT * FROM user WHERE role='admin' and adresse LIKE '%" + chercher.getText() + "%'"
                        + "UNION SELECT * FROM user WHERE role='admin' and email LIKE '%" + chercher.getText() + "%'");
            while (rs.next()) {
               oblistu.add(new User(rs.getString(2),  rs.getString(4),rs.getString(5),rs.getString(8),  rs.getString(10)));
            }

        } catch (SQLException ex) {
            System.err.println("ERROR" + ex);

        }
        tableViewAdmin.setItems(oblistu);
        }
             else{affAdmin();}
    }

    @FXML
    private void addAdmin(MouseEvent event) {
         boolean status = Validate.ValidateEmail(txt_email.getText());
     
        if (status) {
                User c = new User();
                c.setPseudo(txt_login.getText());
                c.setNom(txt_nom.getText());
                c.setPrenom(txt_prenom.getText());
                c.setEmail(txt_email.getText());
                c.setPassword(txt_password.getText());
                c.setAdresse(txt_adress.getText());
                c.setRole("admin");
                DaoUsers uDAO = new DaoUsers();
                uDAO.insertUser(c);
                System.out.println("insertion de l'agent pépinier avec succes");
            } 
        else{JOptionPane.showMessageDialog(null,"Vérifier votre information");}
        affAdmin();
    }

    @FXML
    private void editAdmin(MouseEvent event) {
        User us = new User();

       int i = Integer.parseInt(lbl_id.getText());
        us.setId_client(i);
        us.setNom(txt_nom.getText());
        us.setPrenom(txt_prenom.getText());
        us.setEmail(txt_email.getText());
        us.setPseudo(txt_login.getText());
        us.setPassword(txt_password.getText());
        us.setAdresse(txt_adress.getText());
        us.setRole("admin");
        DaoUsers uDAO = new DaoUsers();
        uDAO.modifUser(us);
        affAdmin();
    }

    @FXML
    private void deleteAdmin(MouseEvent event) {
          User us = new User();
            DaoUsers uDAO = new DaoUsers();        
            int i = tableViewAdmin.getSelectionModel().getSelectedItem().getId_client();
        uDAO.deleteUser(i);
        affAdmin();
        
    }
    
    @FXML
    private void ShowOnClick(MouseEvent event) {
         try {
            User usr = (User) tableViewAdmin.getSelectionModel().getSelectedItem();
            txt_nom.setText(usr.getNom());
            txt_prenom.setText(usr.getPrenom());
            txt_email.setText(usr.getEmail());
            txt_login.setText(usr.getPseudo());
            txt_adress.setText(usr.getAdresse());
           
               String a = String.valueOf(usr.getId_client());
            lbl_id.setText(a);
        } catch (Exception e) {
            
        }
    }

   
    
}
