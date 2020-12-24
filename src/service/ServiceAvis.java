/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import Entities.Avis;
import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import utils.Maconnexion;

/**
 *
 * @author shmhi
 */
public class ServiceAvis {
           Connection c= Maconnexion.getinstance().getConn();
    
    public void ajouterAvis(Avis a)
    {
       
   
        try { Statement  st = c.createStatement();

        
        String req2="insert into avis_service (nom_service,nom_avis,nom_client ,commentaire ,rating) values ('"+a.getNom_service()+"','"+a.getNom_avis()+"','"+a.getNom_client()+"','"+a.getCommentaire()+"','"+a.getRating() +"')";
    
    st.executeUpdate(req2);
         System.out.println("Add Done");
          
        } catch (SQLException ex) {
            Logger.getLogger(Avis.class.getName()).log(Level.SEVERE, null, ex);
     
        }
    }

     
        public void afficherAvis()
    {
        try {
            Statement st=c.createStatement();
            String req="select * from avis_service ";
            ResultSet rs=st.executeQuery(req);
            
            while(rs.next())
            {
                System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7)+rs.getString(8));
            } } catch (SQLException ex) {
            Logger.getLogger(Avis.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      
    }
            
     public void modifierAvis(int id_avis , String nom_avis , String commentaire , String rating )
 
 {
        try {
            PreparedStatement pt=c.prepareStatement("update avis_service set nom_avis=?,commentaire=? ,rating=?  where id_avis =?");
            
            
            
            
            pt.setString(1,nom_avis);
            pt.setString(2,commentaire);
            pt.setString(3,rating);
            pt.setInt(4,id_avis) ;
   
         
            
           pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Avis.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
           public void supprimerAvis(int id)
    {
        try {
            PreparedStatement pt=c.prepareStatement("delete from avis_service where id_avis= ? ");
            
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Avis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
              public void supprimer(int id)
    {
        try {
            PreparedStatement pt=c.prepareStatement("delete from avis where id_produit="+id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAvis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        

  
  
    //***************************************************************/
      
     public void modifier(int id_avis, int id_client,int id_produit,String nom_client, String nom_produit, String commentaire,String rating )
    {
        try {
            PreparedStatement pt=c.prepareStatement("update avis set commentaire = ? where id_avis=?");
            
            
            
            pt.setString(1,commentaire);
         
            pt.setInt(2,id_avis);

            
           pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAvis.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    
           
    
}
}
