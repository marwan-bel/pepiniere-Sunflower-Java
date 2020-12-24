
package service;
import Entities.Commentaire;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.Maconnexion;


public class ServiceCommentaire {
Connection C=  Maconnexion.getinstance().getConn();

   
     public int idsujet () throws SQLException
     {
     
           Statement st1 = C.createStatement();
           String req="select id_sujet from sujet where id_agent="+get_id_user() ;
           ResultSet rs=st1.executeQuery(req);
           int id_sujet=0;
           
           while(rs.next())
           {
               id_sujet=rs.getInt(1);
              // System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*/*"+id_sujet);    
           }
           return id_sujet;
       }
     
     public int idcommentaire() throws SQLException
     {
          Statement st1 = C.createStatement();
          String req="select id_commentaire from commentaire where id_client= "+get_id_user() ;
          ResultSet rs=st1.executeQuery(req);
           int id_commentaire=0;
           while(rs.next())
           {
               id_commentaire=rs.getInt(1);
              // System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*/*"+id_commentaire);    
           }
       return id_commentaire;
         
     }
     
     
     public void ajouterCommentaire(String Com,int x)
     {
       try {
          // System.out.println("id sujetttttt"+idsujet());
           Statement st1 = C.createStatement();
            String req="select nom_sujet from sujet where id_sujet="+x;
            ResultSet rs=st1.executeQuery(req);
            //System.out.println("11111111111111"+req);
            String nom="";
            //JOptionPane.showMessageDialog(null,"Votre commentaire a été ajouté avec succès");
            while(rs.next())
            {
                nom=rs.getString(1);   
                           // System.out.println("11111111111111"+rs.getString(1));

            }
             
       Statement st2 = C.createStatement();
            String req1="select nom from user where id_user = "+get_id_user();
            ResultSet rs1=st2.executeQuery(req1);
            String nom1="";
            //System.out.println("222222222222222"+req1);
            while(rs1.next())
            {
                 nom1=rs1.getString(1);  
            } 
           
          
           String req2 = "insert into commentaire (nom_sujet,id_client,nom_client,id_sujet,commentaires) "
                +   "VALUES (?, ?, ?, ?,?)";
          // System.out.println("+*+*+*+*+*+*+*+*"+req2);
           PreparedStatement prt = C.prepareStatement(req2);
           prt.setString(1,nom);
           prt.setInt(2,get_id_user());
           prt.setString(3,nom1);
           prt.setInt(4,x);
           prt.setString(5,Com);
           prt.executeUpdate();
          // System.out.println("-------------"+prt);
           JOptionPane.showMessageDialog(null,"ajout avec succes");
       } catch (SQLException ex) {
           Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
       }
       
     }
     
     

public int get_id_user()
{
    int id=0;
 try {
            Statement st1 = C.createStatement();
            String req="select id_user from user where connected ="+ true ;
            ResultSet rs=st1.executeQuery(req);
             while(rs.next())
            {
                 id=rs.getInt(1);               
            } 
      
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }   
    return id;
}


    public List <Commentaire> afficherCommentaire(int i)
    {
        Commentaire c;
        List <Commentaire> lc = new ArrayList<>();
    
        try {      
            
      
            Statement st = C.createStatement();
            String req = "select * from commentaire where id_sujet="+i;
           ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             
                c = new Commentaire( rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getInt(8));
            lc.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return lc;
    }
    
    
     public List <Commentaire> afficherCommentaire()
    {
        Commentaire c;
        List <Commentaire> lc = new ArrayList<>();
    
        try {      
            Statement st = C.createStatement();
            String req = "select id_commentaire,id_client,id_sujet,nom_client,nom_sujet,commentaires,date_commentaire,nbre_signal,likee from commentaire";
           ResultSet rs = st.executeQuery(req);
             while(rs.next()){
                //c = new Commentaire(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getInt(8),rs.getInt(9));
                c = new Commentaire(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getInt(8),rs.getInt(9));
                 System.out.println("affichage commentaire"+c);
                lc.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("teest:::"+ex.getMessage());
            
        }
       return lc;
    }
    
    public void modifierCommentaire(String s,int i )
    {
        try {
             PreparedStatement pt = C.prepareStatement("update commentaire set commentaires = ? where id_commentaire=?");        
            pt.setString(1,s);
            pt.setInt(2,i); 
             JOptionPane.showMessageDialog(null,"Votre commentaire a été modifié avec succès");
             pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    
    public void supprimerCommentaire(int id)
    {
        try {
            PreparedStatement pt = C.prepareStatement("delete from commentaire where id_commentaire = ?");
            pt.setInt(1,id);
            pt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Votre commentaire a été supprimé avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void signalerClient(int id_cmt) throws SQLException
    {                 
            
           
           String req2 = "UPDATE commentaire set nbre_signal = nbre_signal +1 where id_commentaire = ? AND id_client = ? ";
           //System.out.println("+*+*+*+*+*+*+*+*"+req2);
           PreparedStatement prt = C.prepareStatement(req2);
           
           prt.setInt(1,id_cmt);
           prt.setInt(2,get_id_user());
           prt.executeUpdate();
           //System.out.println("3030303030303033030"+prt);
    }
    
   public int nbrSignalCom(int x) throws SQLException
   {
       int i=0;
       ServiceCommentaire servC = new ServiceCommentaire();
            Statement st2 = C.createStatement();
            String req1="select nbre_signal from commentaire where id_commentaire = "+x;
            ResultSet rs=st2.executeQuery(req1);
   
             while(rs.next())
             {
                i=rs.getInt(1);
                 if (rs.getInt(1) >= 5)
                  {
                    servC.supprimerCommentaire(x);
                    servC.afficherCommentaire(i);
                  }
            
             } 
             return i;
   }
    
   public int countlike(int id_cmt) throws SQLException
    {
            int i=0;
            Statement st = C.createStatement();
            String req = "select likee from commentaire where id_commentaire="+id_cmt;
           ResultSet rs = st.executeQuery(req);
             while(rs.next()){
                i=rs.getInt(1);
            }
             return i;
    }
   public void addlike(int id_cmt) 
    {
            
    try {
        Statement st = C.createStatement();
        String req = "update commentaire set likee = likee + 1  where id_commentaire="+id_cmt;
        PreparedStatement prt = C.prepareStatement(req);
        prt.executeUpdate();
        System.out.println("add like succes: "+prt.executeUpdate());
    } catch (SQLException ex) {
        System.out.println("errre"+ex.getMessage());
    }

    }
}
   
    

