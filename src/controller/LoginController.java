/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.Maconnexion;
import service.*;
import Entities.*;
import css.PITransition;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane inscrire;
    @FXML
    private DatePicker dateClient;
    private ComboBox b1;
    @FXML
    private TextField nomClient;
    @FXML
    private TextField prenomClient;
    @FXML
    private TextField mailClient;
    @FXML
    private PasswordField passwdClient;
    private TextField pseudoClient;
    @FXML
    private TextField adressClient;
    @FXML
    private TextField telClient;
    @FXML
    private AnchorPane retour;
    @FXML
    private AnchorPane agent;
    @FXML
    private DatePicker dateagent;
    @FXML
    private ComboBox b;
    @FXML
    private TextField nomagent;
    @FXML
    private TextField prenomagent;
    @FXML
    private TextField mailagent;
    @FXML
    private TextField cinagent;
    @FXML
    private TextField nompepiniere;
    @FXML
    private TextField telagent;
    @FXML
    private PasswordField passwdagent;
    @FXML
    private TextField adressagent;
    @FXML
    private AnchorPane espaceAdmin;
    @FXML
    private TextField id2;
    @FXML
    private PasswordField passwd2;
    @FXML
    private AnchorPane espace;
    Connection conn = Maconnexion.getinstance().getConn();
    Statement stm, stm2;
    @FXML
    private AnchorPane epepiniere;
    @FXML
    private AnchorPane eclient;
    private AnchorPane eadmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        retour.setVisible(false);
        agent.setVisible(false);
        inscrire.setVisible(false);
        espaceAdmin.setVisible(false);
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "Homme",
                        "Femme"
                );

        b.setItems(options);
        PITransition.fadeIn(epepiniere, 3000);
        PITransition.fadeIn(eclient, 3000);

    }

    @FXML
    private void RetourAction(MouseEvent event) {
        espace.setVisible(true);
        retour.setVisible(false);
        espaceAdmin.setVisible(false);
        inscrire.setVisible(false);
        agent.setVisible(false);
        PITransition.fadeIn(epepiniere, 1000);
        PITransition.fadeIn(eclient, 1000);
        PITransition.fadeIn(eadmin, 1000);
    }

    private void AdminAction(MouseEvent event) {
        retour.setVisible(true);
        espaceAdmin.setVisible(true);
        inscrire.setVisible(false);
        agent.setVisible(false);
        espace.setVisible(false);
        PITransition.fadeIn(espaceAdmin, 1000);

    }

    @FXML
    private void AgentAction(MouseEvent event) {
        inscrire.setVisible(false);
        agent.setVisible(true);
        espace.setVisible(false);
        retour.setVisible(true);
        espaceAdmin.setVisible(true);
        PITransition.fadeIn(espaceAdmin, 1000);
        PITransition.fadeIn(retour, 1000);
        PITransition.fadeIn(agent, 1000);

    }

    @FXML
    private void ClientAction(MouseEvent event) {
        agent.setVisible(false);
        inscrire.setVisible(true);
        espace.setVisible(false);
        retour.setVisible(true);
        espaceAdmin.setVisible(true);
        PITransition.fadeIn(inscrire, 1000);
        PITransition.fadeIn(retour, 1000);
        PITransition.fadeIn(espaceAdmin, 1000);

    }

    @FXML
    private void inscritClient(MouseEvent event) {
        boolean status = Validate.ValidateEmail(mailClient.getText());
        String nom = nomClient.getText();
        String prenom = prenomClient.getText();
        String mail = mailClient.getText();
        String passwd = passwdClient.getText();
        String adr = adressClient.getText();

        if (status && nom.length() != 0 && passwdClient.getText().length() != 0) {
            User c = new User();
            c.setNom(nomClient.getText());
            c.setPrenom(prenomClient.getText());
            c.setEmail(mailClient.getText());
            c.setPassword(passwdClient.getText());
            c.setAdresse(adressClient.getText());
            c.setTelephone(telClient.getText());
            c.setRole("client");
            DaoUsers uDAO = new DaoUsers();
            uDAO.insertUser(c);
            mailClient.setText("");
            nomClient.setText("");
            prenomClient.setText("");
            mailClient.setText("");
            passwdClient.setText("");
            adressClient.setText("");
        } else {{ JOptionPane.showMessageDialog(null, "Vérifier votre mail"); }}

    }

    @FXML
    private void inscritAgent(MouseEvent event) {
        boolean status = Validate.ValidateEmail(mailagent.getText());
        boolean statustel = Validate.ValidateTel(telagent.getText());
        boolean statuscin = Validate.ValidateCin(cinagent.getText());
        if (status && statustel && statuscin && passwdagent.getText().length() != 0) {
            User c = new User();

            c.setNom(nomagent.getText());
            c.setPrenom(prenomagent.getText());
            c.setEmail(mailagent.getText());
            c.setCin(cinagent.getText());
            c.setPassword(passwdagent.getText());
            c.setAdresse(adressagent.getText());
            c.setDate_de_naissance(dateagent.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            c.setSexe((String) b.getSelectionModel().getSelectedItem());
            c.setTelephone(telagent.getText());
            c.setNom_pepiniere(nompepiniere.getText());
            c.setRole("agent");
            DaoUsers uDAO = new DaoUsers();
            uDAO.insertUser(c);
            System.out.println("insertion de l'agent pépinier avec succes");
        } else {
            JOptionPane.showMessageDialog(null, "Vérifier votre information");
        }
    }

    @FXML
    private void AdminConnAction(MouseEvent event) throws SQLException, IOException {
        DaoUsers user = new DaoUsers();
        User u = new User();
        String req_conn = "select email,password,id from user where email='" + id2.getText()+"'";
        stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(req_conn);
        String role = "";
        boolean exist = false;
        int i = 0;
        while (rs.next()) {
            exist = true;
            role = rs.getString(1);
            i =rs.getInt(3);
        }
        if (exist) {
            if (null == role) {
                JOptionPane.showMessageDialog(null, "Confirmer votre Compte!");
            } else {
                
                if(role.equals("admin.admin@esprit.tn"))
                {   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/Admin.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.setMaximized(true);

                        stage.getIcons().add(new Image("/Icons/sunflower.png"));
                        stage.setTitle("SunFlower");
                        stage.show();
                        ((Node) (event.getSource())).getScene().getWindow().hide();
                        stage.setTitle("Gestion Administrateur");}
                else
                {   
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/test.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.setMaximized(true);

                        stage.getIcons().add(new Image("/Icons/sunflower.png"));
                        stage.setTitle("SunFlower");
                        stage.show();
                        ((Node) (event.getSource())).getScene().getWindow().hide();
                        stage.setTitle("Gestion Administrateur");}
             
            }
        } else {
            JOptionPane.showMessageDialog(null, "Verifiez votre Login!");
        }
    }

}
