package controller;
import Entities.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Maconnexion;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import service.DaoCommande;
import service.DaoUsers;
/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class GestionAdminController implements Initializable {

    @FXML private JFXButton btn_gestuser;
    @FXML private AnchorPane Anchorgestadmin;
    @FXML private JFXButton btn_addAmin;
    @FXML private JFXButton btn_editAdmin;
    @FXML private JFXButton btn_deleteAdmin;
    @FXML
    private TableView<User> tableViewAdmin;
    private TableColumn<User, String> columnNom;
    private TableColumn<User, String> columnPrenom;
    @FXML private JFXTextField txt_login;
    @FXML private JFXPasswordField txt_password;
    @FXML private JFXTextField txt_email;
    @FXML private JFXTextField txt_prenom;
    @FXML private JFXTextField txt_nom;
    @FXML
    private TableColumn<User, String> pseudo;
    private TableColumn<User, String> password;
    @FXML private TableColumn<User, String> nom;
    @FXML private TextField chercher;
    @FXML
    private TableColumn<?, ?> prenom;
    private TableColumn<?, ?> sexe;
    private TableColumn<?, ?> date_de_naissance;
    @FXML
    private TableColumn<?, ?> adresse;
    private TableColumn<?, ?> telephone;
    @FXML private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> cin;
    private TableColumn<?, ?> nom_pepiniere;
    @FXML
    private JFXTextField txt_adress;
    @FXML private TableColumn<?, ?> id_admin;
    @FXML private TableColumn<?, ?> role_admin;
    @FXML private Label lbl_id;
    @FXML private JFXButton btn_addClient;
    @FXML private JFXButton btn_editClient;
    @FXML private JFXButton btn_deleteClient;
    @FXML private TableView<User> tableViewAdmin1;
    @FXML private TableColumn<?, ?> id_admin1;
    @FXML private TableColumn<?, ?> pseudo1;
    @FXML private TableColumn<?, ?> nom1;
    @FXML private TableColumn<?, ?> prenom1;
    @FXML private TableColumn<?, ?> adresse1;
    @FXML private TableColumn<?, ?> email1;
    @FXML private TableColumn<?, ?> role_admin1;
    @FXML private TableColumn<?, ?> etatClient;
    @FXML private JFXTextField txt_login1;
    @FXML private JFXPasswordField txt_password1;
    @FXML private JFXTextField txt_adress1;
    @FXML private JFXTextField txt_email1;
    @FXML private JFXTextField txt_prenom1;
    @FXML private JFXTextField txt_nom1;
    @FXML private TextField chercher1;
    @FXML private Label lbl_id1;
    @FXML private AnchorPane Anchorgestclient;
    @FXML private AnchorPane Anchorgestclient1;
    @FXML private JFXButton btn_addClient1;
    @FXML private JFXButton btn_editClient1;
    @FXML private JFXButton btn_deleteClient1;
    @FXML private TableView<User> tableViewAdmin11;
    @FXML private TableColumn<?, ?> id_admin11;
    @FXML  private TableColumn<?, ?> nom11;
    @FXML private TableColumn<?, ?> prenom11;
    @FXML private TableColumn<?, ?> adresse11;
    @FXML private TableColumn<?, ?> email11;
    @FXML private TableColumn<?, ?> role_admin11;
    @FXML private TableColumn<?, ?> etatClient1;
    @FXML private TableColumn<?, ?> nom_pep;
    @FXML private JFXTextField txt_login11;
    @FXML private JFXPasswordField txt_password11;
    @FXML private JFXTextField txt_adress11;
    @FXML private JFXTextField txt_email11;
    @FXML private JFXTextField txt_prenom11;
    @FXML private JFXTextField txt_nom11;
    @FXML private TextField chercher11;
    @FXML private Label lbl_id11;
    @FXML private JFXTextField nompep;
    @FXML private JFXButton btn_editClient11;
    @FXML private JFXButton btn_deleteClient11;
    @FXML
    private TextField chercher111;
    private Label lbl_id111;
    @FXML private TableView<Commande> TabCmd;
    @FXML private TableColumn<?, ?> id_cmd;
    @FXML private TableColumn<?, ?> id_client;
    @FXML private TableColumn<?, ?> etat_cmd;
    @FXML private TableColumn<?, ?> mode_pay;
    @FXML private TableColumn<?, ?> lieu_liv;
    @FXML private TableColumn<?, ?> dat_cmd;
    @FXML private TableColumn<?, ?> montant;
    @FXML private AnchorPane AnchorgestCmd;

    Connection conn=Maconnexion.getinstance().getConn();
    Statement stm;
    @FXML private JFXTextField etat_Cmd;
    @FXML private JFXTextField adresscmd;
    @FXML private JFXTextField montantCmd;
    @FXML private JFXTextField mod_paim1;
    /**
     * Initializes the controller class.
     */
     
    @FXML private Label id_Client;
    @FXML private Label id_comm;
    @FXML private Pane chartPane;
    DaoCommande cmd=new DaoCommande();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Anchorgestadmin.setVisible(false);
        Anchorgestclient.setVisible(false);
        Anchorgestclient1.setVisible(false);
        AnchorgestCmd.setVisible(false);
          chartPane.setVisible(false);
        
    }    
      
        
    
    @FXML
    private void LogOut(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root1));  
    stage.show();
    stage.setTitle("Login"); 
    ((Node)(event.getSource())).getScene().getWindow().hide();
    }

     
    @FXML
    private void GestionAdmin(MouseEvent event) 
    {
       
            Anchorgestadmin.setVisible(true);
            Anchorgestclient.setVisible(false);
            Anchorgestclient1.setVisible(false);
            AnchorgestCmd.setVisible(false);
              chartPane.setVisible(false);
            affAdmin();
              
    
   }

    @FXML
    private void GestionUsers(MouseEvent event) 
    {
        Anchorgestadmin.setVisible(false);
        Anchorgestclient.setVisible(true);
        Anchorgestclient1.setVisible(false);
        AnchorgestCmd.setVisible(false);
          chartPane.setVisible(false);

        affUser();
        
    }
    
       @FXML
    private void GestionProduit(MouseEvent event) {
        Anchorgestadmin.setVisible(false);
        Anchorgestclient.setVisible(false);
        Anchorgestclient1.setVisible(false);
        AnchorgestCmd.setVisible(false);
          chartPane.setVisible(false);
    }

    @FXML
    private void GestionService(MouseEvent event) {
         Anchorgestadmin.setVisible(false);
        Anchorgestclient.setVisible(false);
        Anchorgestclient1.setVisible(false);
        AnchorgestCmd.setVisible(false);
          chartPane.setVisible(false);
    }

    @FXML
    private void GestionCommande(MouseEvent event) {
          Anchorgestadmin.setVisible(false);
        Anchorgestclient.setVisible(false);
        Anchorgestclient1.setVisible(false);
        AnchorgestCmd.setVisible(true);
          chartPane.setVisible(false);
        affCmd();
        
      
    }

    @FXML
    private void GestionAstuce(MouseEvent event) {
        Anchorgestadmin.setVisible(false);
        Anchorgestclient.setVisible(false);
        Anchorgestclient1.setVisible(false);
        AnchorgestCmd.setVisible(false);
          chartPane.setVisible(false);
    }

    @FXML
    private void GestionReclamation(MouseEvent event) {
        Anchorgestadmin.setVisible(false);
        Anchorgestclient.setVisible(false);
        Anchorgestclient1.setVisible(false);
        AnchorgestCmd.setVisible(false);
          chartPane.setVisible(false);
    }

    @FXML
    private void GestionEvent(MouseEvent event) {
        Anchorgestadmin.setVisible(false);
        Anchorgestclient.setVisible(false);
        Anchorgestclient1.setVisible(false);
        AnchorgestCmd.setVisible(false);
          chartPane.setVisible(false);
    }
    
    @FXML
    private void GestionPepiniere(MouseEvent event) 
    {
        Anchorgestadmin.setVisible(false);
        Anchorgestclient.setVisible(false);
        Anchorgestclient1.setVisible(true);
        AnchorgestCmd.setVisible(false);
          chartPane.setVisible(false);
        AnchorgestCmd.setVisible(false);
         Anchorgestadmin.setVisible(false);
        Anchorgestclient.setVisible(false);
        Anchorgestclient1.setVisible(false);

        affAgent();
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
    
    public void affCmd()
    {id_cmd.setCellValueFactory(new PropertyValueFactory<>("id_cmd"));
          id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
               etat_cmd.setCellValueFactory(new PropertyValueFactory<>("etat_cmd"));
          mode_pay.setCellValueFactory(new PropertyValueFactory<>("mode_p"));
               lieu_liv.setCellValueFactory(new PropertyValueFactory<>("lieu_liv"));
          dat_cmd.setCellValueFactory(new PropertyValueFactory<>("montant"));
               montant.setCellValueFactory(new PropertyValueFactory<>("date_cmd"));
        
        ObservableList<Commande> oblist=FXCollections.observableArrayList();
    
  
         try {
              String req_conn="select id_cmd,id_client,etat_cmd,mode_p,lieu_liv,montant,date_cmd from Commande";
              
              stm=conn.createStatement();
              ResultSet rs=stm.executeQuery(req_conn);
            
              while(rs.next()){
                  oblist.add(new Commande(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getFloat(6),rs.getTimestamp(7))); 
                    System.out.println(rs.getInt(1)+rs.getInt(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getFloat(6)+rs.getTimestamp(7));
              }                 
          } 
          
          catch (SQLException ex) {
              System.out.println("erreur"+ ex);
          }   
             TabCmd.setItems(oblist);
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
    private void ShowOnClick() {
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
    
       @FXML
    private void ShowOnClick2() {
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
    //*****gazzah firas******\\
   @FXML
    private void ShowOnClickCMD() {
        try {
            Commande usr = (Commande) TabCmd.getSelectionModel().getSelectedItem();
            etat_Cmd.setText(usr.getEtat_cmd());
            mod_paim1.setText(usr.getMode_p());
            adresscmd.setText(usr.getLieu_liv());
           montantCmd.setText(Float.toString(usr.getMontant()));
             String b = String.valueOf(usr.getId_client());
            id_Client.setText(b);
               String a = String.valueOf(usr.getId_cmd());
            id_comm.setText(a);
        } catch (Exception e) {
            
        }

    }
    //*****gazzah firas******\\
    @FXML
    private void Imprimer(MouseEvent event) throws IOException {
      chartPane.setVisible(false);
       cmd.Imprimer();
       
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
    
  //*****gazzah firas******\\
    @FXML
    private void editCMD(MouseEvent event) {
         Commande us = new Commande();

         int i = Integer.parseInt(id_comm.getText());
         int y = Integer.parseInt(id_Client.getText());
        us.setId_client(y);
        us.setId_cmd(i);
        us.setEtat_cmd(etat_Cmd.getText());
        us.setLieu_liv(adresscmd.getText());
        us.setMode_p(mod_paim1.getText());
        us.setMontant(Float.parseFloat(montantCmd.getText().trim()));
        
        DaoCommande uDAO = new DaoCommande();
        uDAO.ModifierCmd(us);
                       affCmd();
       
    }

    //*****gazzah firas******\\
    @FXML
    private void deleteCMD(MouseEvent event) {
         Commande us = new Commande();
            DaoCommande uDAO = new DaoCommande();        
            int i = TabCmd.getSelectionModel().getSelectedItem().getId_client();
        uDAO.SupprimerCmd(i);
               affCmd();
    }

    @FXML
    private void rechercheUser(KeyEvent event) {
    }

    @FXML
    private void rechercheAgent(KeyEvent event) {
    }

    //*****gazzah firas******\\
    @FXML
    private void rechercheCmd(KeyEvent event) {
        id_cmd.setCellValueFactory(new PropertyValueFactory<>("id_cmd"));
          id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
               etat_cmd.setCellValueFactory(new PropertyValueFactory<>("etat_cmd"));
          mode_pay.setCellValueFactory(new PropertyValueFactory<>("mode_p"));
               lieu_liv.setCellValueFactory(new PropertyValueFactory<>("lieu_liv"));
          dat_cmd.setCellValueFactory(new PropertyValueFactory<>("date_cmd"));
          montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        
        ObservableList<Commande> oblist=FXCollections.observableArrayList();
    
           
             if(chercher111.getText().length()!=0)
            {  
        try {
          
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM commande WHERE etat_cmd LIKE '%" + chercher111.getText() + "%'"
                        + "UNION SELECT * FROM commande WHERE mode_p LIKE '%" + chercher111.getText() + "%'"
                        + "UNION SELECT * FROM commande WHERE id_cmd LIKE '%" + chercher111.getText() + "%'"
                        + "UNION SELECT * FROM commande WHERE id_client LIKE '%" + chercher111.getText() + "%'"
                        + "UNION SELECT * FROM commande WHERE date_cmd LIKE '%" + chercher111.getText() + "%'"
                        + "UNION SELECT * FROM commande WHERE lieu_liv LIKE '%" + chercher111.getText() + "%'"
                        + "UNION SELECT * FROM commande WHERE montant LIKE '%" + chercher111.getText() + "%'");
           while(rs.next()){
                  oblist.add(new Commande(rs.getString(3),rs.getString(5),rs.getString(6),rs.getFloat(7),rs.getTimestamp(4))); 
                  
              }   

        } catch (SQLException ex) {
            System.err.println("ERROR" + ex);

        }
        TabCmd.setItems(oblist);
        }
             else{affCmd();}
    }

    //*****gazzah firas******\\
    @FXML
    private void statCMD(MouseEvent event) {
        chartPane.setVisible(true);
        AnchorgestCmd.setVisible(false);
         Anchorgestadmin.setVisible(false);
        Anchorgestclient.setVisible(false);
        Anchorgestclient1.setVisible(false);
       
        try {
        CategoryAxis xAxis=new CategoryAxis();
        
        
            xAxis.setLabel("Date");
            
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Montant en dinar");
            
            LineChart<String,Number> lineChart= new LineChart<String,Number>(xAxis,yAxis);
            lineChart.setTitle("Les Commandes");
            
            XYChart.Series<String,Number> data=new XYChart.Series<>();
            data.setName("Commandes");
            
            String req_conn="select montant,date_cmd from commande ";
            
            ResultSet rs = stm.executeQuery(req_conn);
            while (rs.next())
            {
                data.getData().add(new XYChart.Data<String,Number>(rs.getTimestamp(2).toString(),rs.getFloat(1)));
            }
            
            lineChart.getData().add(data);
            
            chartPane.getChildren().add(lineChart);
    }   catch (SQLException ex) {
            Logger.getLogger(GestionAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
