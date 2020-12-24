/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Reclamation;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class DetailedController implements Initializable {

    @FXML
    private TextField ms;
    @FXML
    private Label nc;
    @FXML
    private Label tr;
    @FXML
    private Label np;
    @FXML
    private Label tp;
    @FXML
    private Button btn88;
 private Reclamation selectedReclamation;
    private Label id;
    /**
     * Initializes the controller class.
     */
   
        public String conv(Timestamp date_reclamation ){
      String tt = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
       return tt;
    }
        
        
        
      
    
        public void initData(Reclamation rec) throws MalformedURLException
    {
        selectedReclamation = rec;
     
       String n=  String.valueOf(rec.getId_reclamation());
        nc.setText(selectedReclamation.getNom_client());
        tr.setText(selectedReclamation.getNom_reclamation());
        np.setText(selectedReclamation.getNom_pepiniere());
 
    
        tp.setText(selectedReclamation.getTel());
        ms.setText(selectedReclamation.getMessage());
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    
}
