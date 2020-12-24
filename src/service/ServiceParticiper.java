/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.sql.*;
import Entities.Participer;
import interfaces.InterfaceEvenement;
import interfaces.InterfaceParticiper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.*;
/**
 *
 * @author starmedia
 */
public class ServiceParticiper{
    
    
    
    
    
Connection c= Maconnexion.getinstance().getConn();        
    public void ajouter(Participer p)
    {
        try { 
        Statement  st = c.createStatement();
        String req2="insert into participer(id_evenement,id_user) values("+p.getId_evenement()+","+p.getId_user()+")" ;
    
    st.executeUpdate(req2, Statement.RETURN_GENERATED_KEYS);
         ResultSet rs3= st.getGeneratedKeys();
         int id_p=0;    
         if (rs3.next()){
             id_p=rs3.getInt(1);
                //  System.out.println(rs3.getInt(1));
        }
            
    p.setId_participer(id_p);
    
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticiper.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
}
/*-------------------------------*/
    
       public void supprimer(int id)
    {
        try {
            PreparedStatement pt=c.prepareStatement("delete from participer where id_participer= ? ");
            
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticiper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
       
      
    /*---------------------------------------------*/
       
       
       
       public int rech(int id_eve,int id_user)
           {
       int idd=0;
        try {
            Statement st=c.createStatement();
            String req="select id_participer from participer where id_evenement="+id_eve+" and id_user="+id_user+" ";
            ResultSet rs=st.executeQuery(req);
            int id=0;
            while(rs.next())
            {
                id=rs.getInt(1);
                
                
            }
            return id; 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticiper.class.getName()).log(Level.SEVERE, null, ex);
        }
   return idd;
      
 }
    
      /*************************************/
       
       
       
          public int total_par(int id_eve)
           {
       int idd=0;
        try {
            Statement st=c.createStatement();
            String req="select id_participer from participer where id_evenement="+id_eve+" ";
            ResultSet rs=st.executeQuery(req);
            int id=0;
            while(rs.next())
            {
                id+=1;
                
                
            }
            return id; 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticiper.class.getName()).log(Level.SEVERE, null, ex);
        }
   return idd;
      
 }
  
       

}
