
package service;

import Entities.Sujet;
import java.sql.*;
import utils.Maconnexion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
 

public class ServiceSujet  {
    
Connection C = Maconnexion.getinstance().getConn();



    public void ajouterSujet(Sujet S)
    {  
        try {
            Statement st1 = C.createStatement();
            String req="select id_categorie_sujet from categorie_sujet where nom_categorie_sujet= '"+S.getNom_categorie_sujet()+"'";
              ResultSet rs=st1.executeQuery(req);
            int id=0;
             while(rs.next())
            {
              // System.out.println(rs.getString(1));
                id=rs.getInt(1);
            } 
            Statement st2 = C.createStatement();
            String req1="select nom from user where id =1 ";
            ResultSet rs1=st2.executeQuery(req1);
            String nom1="";
            while(rs1.next())
            {
                System.out.println(rs1.getString(1));
                nom1=rs1.getString(1);
            } 
                 Statement st = C.createStatement();
            String req2 = "insert into sujet (nom_sujet,nom_categorie_sujet,description,"
                    + "type,id_agent,nom_agent,image,id_categorie_sujet) "
                +   "VALUES (?, ?, ?, ?, ? ,? ,?,?)";               
            PreparedStatement prt = C.prepareStatement(req2);
            prt.setString(1, S.getNom_sujet());
            prt.setString(2, S.getNom_categorie_sujet());
            prt.setString(3, S.getDescription());
            prt.setString(4 ,S.getType());
            prt.setInt(5,1);
            prt.setString(6,nom1);
            prt.setString(7,S.getImage());
            prt.setInt(8,id);
            prt.executeUpdate();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Ajout d'un nouveau sujet");
          alert.setHeaderText("Vous avez ajouté un nouveau sujet");
          alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public List <Sujet> afficherSujet()
    {
         Sujet s;
        List <Sujet> ls = new ArrayList<>();
        try {
             Statement st = C.createStatement();
            String req = "select * from sujet";
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                s = new Sujet(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10));
            ls.add(s);
             }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    return ls;
    }
    
    public void modifierSujet(Sujet S)
    {
        try {
             PreparedStatement pt = C.prepareStatement("update sujet set description = ?,nom_sujet = ? ,image = ? where id_sujet=?");
             pt.setString(1,S.getDescription());//parametre fil requete
             pt.setString(2,S.getNom_sujet());
             pt.setString(3,S.getImage());
             pt.setInt(4,S.getId_sujet());
             pt.executeUpdate();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Modifier un sujet");
          alert.setHeaderText(null);
          alert.setContentText("modification du sujet établie avec succès");
          alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public void supprimerSujet(String s)
    {
        try {
            PreparedStatement pt = C.prepareStatement("delete from sujet where nom_sujet=?");
            pt.setString(1,s);
            pt.executeUpdate();
         } catch (SQLException ex) {
            System.out.println("test "+ex);
        }
    } 
 
   public List <Sujet> afficher_Sujet(Button x)
    {
         Sujet s;
         List <Sujet> ls = new ArrayList<>();
        try { 
            
            Statement st1 = C.createStatement();
            String req="select nom_sujet,description,nom_agent,date_creation,image,id_sujet from sujet where  nom_categorie_sujet  = '"+x.getText()+"'";
            
        
            ResultSet rs=st1.executeQuery(req);
            
            while(rs.next()){
                s = new Sujet(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getInt(6));
            ls.add(s);
             }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    return ls;
    }
   
   /**********************************Récuperation de l'email utilisateur******************************/
  public String get_email_user()
{
    String email="";
 try {
            Statement st1 = C.createStatement();
            String req="select email from user where connected ="+ true ;
            ResultSet rs=st1.executeQuery(req);
            
            //JOptionPane.showMessageDialog(null,"Votre sujet a été ajouté avec succès");
            while(rs.next())
            {
                 email=rs.getString(1);
               
            }      
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }   
    return email;
}
  
 /**************************Recuperation du NomSujet **************************************/
   public String get_nomSujet(int i)
  {
       String nom="";
      try {
      
            Statement st1 = C.createStatement();
            String req="select nom_sujet from sujet where id_sujet ="+i;
            ResultSet rs=st1.executeQuery(req);
            //System.out.println("65656565656565656"+req); 
            //JOptionPane.showMedssageDialog(null,"Votre sujet a été ajouté avec succès");
            while(rs.next())
            {
              nom=rs.getString(1);
               
            }      
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }  
     
    return nom;
  }
   
   /************************************Envoie d'un email **********************/
   public void envoyerMail(String i)
   {
       ServiceSujet servS = new ServiceSujet();
       String mail=servS.get_email_user();
          Emailer em = new Emailer (mail,i);   
          em.sendmail();  
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("mail");
          alert.setHeaderText("your mail is sent");
          alert.showAndWait();
   
   }
       
   
        
}
