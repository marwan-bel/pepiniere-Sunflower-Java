/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Event;

import Entities.Evenement;
import Entities.GestionServices;
import Entities.Participer;
import com.jfoenix.controls.JFXButton;
import controller.ServiceDetailsController;
import controller.ServiceEmailerController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.EmailSend;
import service.ServiceEvenement;
import service.ServiceParticiper;

/**
 * FXML Controller class
 *
 * @author gazzah
 */
public class ITemEventController  {

    @FXML
    private HBox Hbox;
    @FXML
    private ImageView img;
    @FXML
    private Text txt_nompromo;
    @FXML
    private Label txt_type;
    @FXML
    private Label txt_datdebut;
    @FXML
    private Text txt_addpar;
    @FXML
    private Label txt_lieu;
    @FXML
    private Label txt_datfin;
    @FXML
    private TextArea txt_desc;
    @FXML
    private JFXButton btn_participer;
    @FXML
    private Label txt_participant;
    @FXML
    private Label txt_maxParticip;

    /**
     * Initializes the controller class.
     */
     public ITemEventController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Event/ITemEvent.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
     public void setInfo(Evenement string) {
        txt_nompromo.setText(string.getNom_evenement());
        txt_desc.setText(string.getDescription());
        txt_datfin.setText(string.getDate_fin());
        txt_datdebut.setText(string.getDate_debut());
        txt_lieu.setText(string.getLieu());
        txt_type.setText(string.getType());
        txt_maxParticip.setText(""+string.getMax());
        System.out.println(string);
       javafx.scene.image.Image image = new javafx.scene.image.Image("http://localhost/uploads/" + string.getPath());
        img.setImage(image);
        ServiceParticiper sp=new ServiceParticiper();
                 ServiceEvenement s=new ServiceEvenement();
                  int i= s.get_id_user();
           
       int d=string.getId_evenement();
       
       ServiceParticiper pa=new ServiceParticiper();
       if(pa.rech(d, i)!=0){ btn_participer.setText("annuler");
      
       }else{btn_participer.setText("Participer");}
       txt_participant.setText(""+sp.total_par(string.getId_evenement()));
        
        
  
        btn_participer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ServiceEvenement s=new ServiceEvenement();
                  int i= s.get_id_user();
           
       int d=string.getId_evenement();
       
       ServiceParticiper pa=new ServiceParticiper();
       Participer pp=new Participer(d,i);
          if(pa.rech(d, i)==0){
               int tt=  pa.total_par(string.getId_evenement());
              if(tt<string.getMax()){
                  
                         pa.ajouter(pp); 
                btn_participer.setText("annuler");
                
                       
                        ServiceEvenement ss=new ServiceEvenement();
                  String ii= ss.get_mail(i);
                  String iii= ss.get_nom(i);
                
                                       EmailSend ee= new EmailSend(ii,iii);
        ee.mail();
      

                
                  Notifications no=Notifications.create()
                .title("Evenement")
                .text("je suis intéressée")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                  
                    public void handle (ActionEvent event){
                        System.out.println("a");
                    }
                    
                });
             no.darkStyle();
        no.showConfirm();
        
                
                
                
                   int t=  pa.total_par(string.getId_evenement());
               String n=  String.valueOf(t);
                txt_participant.setText(n);
              }else{
                   JOptionPane.showMessageDialog(null,"Désole MAX atteint");  
              }
              
              
          }
          else
          {
              pa.supprimer(pa.rech(d, i));
              btn_participer.setText("Participer");
                   int t=  pa.total_par(string.getId_evenement());
               String n=  String.valueOf(t);
                txt_participant.setText(n);
          }
                
            }
        });
      
    }

    public HBox getBox() {
        return Hbox;
    }
    
   
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

