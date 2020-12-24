/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml.fxmlAdmin;

import Entities.Commande;
import Entities.User;
import com.jfoenix.controls.JFXButton;
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
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import service.DaoCommande;
import utils.Maconnexion;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class GestionCommandeController implements Initializable {

    @FXML
    private AnchorPane AnchorgestCmd;
    @FXML
    private JFXButton btn_editClient11;
    @FXML
    private JFXButton btn_deleteClient11;
    @FXML
    private JFXTextField etat_Cmd;
    @FXML
    private JFXTextField adresscmd;
    @FXML
    private JFXTextField mod_paim1;
    @FXML
    private JFXTextField montantCmd;
    @FXML
    private TextField chercher111;
    @FXML
    private TableView<Commande> TabCmd;
    @FXML
    private TableColumn<?, ?> id_cmd;
    @FXML
    private TableColumn<?, ?> id_client;
    @FXML
    private TableColumn<?, ?> etat_cmd;
    @FXML
    private TableColumn<?, ?> mode_pay;
    @FXML
    private TableColumn<?, ?> lieu_liv;
    @FXML
    private TableColumn<?, ?> dat_cmd;
    @FXML
    private TableColumn<?, ?> montant;
    @FXML
    private Label id_comm;
    @FXML
    private Label id_Client;
    @FXML
    private Pane chartPane;
    
    
DaoCommande cmd=new DaoCommande();
Connection conn=Maconnexion.getinstance().getConn();
    Statement stm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affCmd();
    }    
  @FXML
    private void ShowOnClickCMD(MouseEvent event) {
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
      @FXML
    private void statCMD(MouseEvent event) {
        chartPane.setVisible(true);
        AnchorgestCmd.setVisible(false);
         
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
            Logger.getLogger(controller.GestionAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   

    }

   
    

