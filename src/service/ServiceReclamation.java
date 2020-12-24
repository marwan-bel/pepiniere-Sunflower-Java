/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import Entities.Reclamation;
import Entities.User;
import java.sql.*;
import Entities.*;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.*;


/**
 *
 * @author Marouan
 */
public class ServiceReclamation {
    
    Connection c=Maconnexion.getinstance().getConn();
    
    
    
    
        public void ajouter(Reclamation r)
    {
       
   
        try {
        
       
            Statement st1=c.createStatement();
            String req1="select id_user from user where nom_client= "+r.getNom_client();
            ResultSet rs1=st1.executeQuery(req1);
            String id_client="";
            while(rs1.next())
            {
                System.out.println(rs1.getString(1));
                id_client=rs1.getString(1);
            } 
            
            
            
            
           Statement st2=c.createStatement();
            String req2="select id_agent from produit where nom_agent= "+r.getNom_agent();
            ResultSet rs2=st2.executeQuery(req2);
            String id_agent="";
            while(rs2.next())
            {
                System.out.println(rs2.getString(1));
                id_agent=rs2.getString(1);
            } 
            
         Statement  st = c.createStatement();
        String req="insert into reclamation (id_client,id_agent,nom_reclamation,nom_client,nom_agent,message) values ("
                +id_client+","+id_agent+",'"+r.getNom_reclamation()+"','"+r.getNom_client()+"','"+r.getNom_agent()+"','"+r.getMessage()+"',)";
            System.out.println("-*********"+req);
    st.executeUpdate(req);
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    
    
    }
    //************************************************************
    
    
       /* public void afficher()
    {
        try {
            Statement st=c.createStatement();
            String req="select * from reclamation";
            ResultSet rs=st.executeQuery(req);
            System.out.println("////////////////"+req);
            
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+rs.getInt(2)+rs.getInt(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7)+rs.getString(8)+rs.getString(9));
            } } catch (SQLException ex) {
                Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      
    }
        */   
        
       public void supprimer(int id) throws SQLException
    {
        try {
            PreparedStatement pt=c.prepareStatement("delete from claims where claims_id= ? ");
            
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        Reclamation r= new Reclamation();
         Statement st7=c.createStatement();
            String req7="select nbr from user where nom_pepiniere='" +r.getNom_pepiniere()+"' ";
            ResultSet rs6=st7.executeQuery(req7);
          int nbr=0;
       
            while(rs6.next())
            {
                System.out.println("/////////////////////////"+rs6.getString(1));
          
              nbr=rs6.getInt(1);
              nbr--;
               System.out.println("//////////////"+nbr);
             
              
            } 
           
            System.out.println("//////////////"+nbr);
            PreparedStatement pt=c.prepareStatement("update user set nbr = ?  where nom_pepiniere=?");
                 User u= new User();
   
                 pt.setInt(1,nbr);
                 
                 pt.setString(2, r.getNom_pepiniere());
                 
                 pt.executeUpdate();
        /*
        Reclamation r= new Reclamation();
         Statement st7=c.createStatement();
            String req7="select nbr from user where nom_pepiniere='" +r.getNom_pepiniere()+"' ";
            ResultSet rs6=st7.executeQuery(req7);
          int nbr=0;
       
            while(rs6.next())
            {
                System.out.println("/////////////////////////"+rs6.getString(1));
          
              nbr=rs6.getInt(1);
              nbr--;
               System.out.println("//////////////"+nbr);
             
              
            } 
           
            System.out.println("//////////////"+nbr);
            PreparedStatement pt=c.prepareStatement("update user set nbr = ?  where nom_pepiniere=?");
                 User u= new User();
   
                 pt.setInt(1,nbr);
                 
                 pt.setString(2, r.getNom_pepiniere());
                 
                 pt.executeUpdate();
        */

        
    }
    //***************************************************************/
      
     public void modifier(int id_rec, Claims r )
    {
        try {
            PreparedStatement pt=c.prepareStatement("update claims set claim_productref=?,claim_desc = ? where 	claims_id=?");

            
            
            
   
            
            pt.setString(1,r.getClaim_Productref());
            pt.setString(2,r.getClaim_Desc());
           
            
          
            
           pt.setInt(3,r.getClaims_id());
            System.out.println("/////"+pt);
            
           pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void modifierR(int id_rec, Reclamation r ) throws SQLException 
    {
        try {
            PreparedStatement pt=c.prepareStatement("update reclamation set reponse=?,etat = ? where id_reclamation=?");

            
            
            
   
            
            pt.setString(1,r.getReponse());
           
            pt.setString(2,"traité");
            
            
          
            
            
          
            
           pt.setInt(3,r.getId_reclamation());
            System.out.println("/////"+pt);
            
           pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
     
         Statement st6=c.createStatement();
            String req5="select nbr from user where nom_pepiniere='" +r.getNom_pepiniere()+"' ";
            ResultSet rs5=st6.executeQuery(req5);
          int nbr=0;
       
            while(rs5.next())
            {
                System.out.println("/////////////////////////"+rs5.getString(1));
          
              nbr=rs5.getInt(1);
              nbr--;
               System.out.println("//////////////"+nbr);
             
              
            } 
           
            System.out.println("//////////////"+nbr);
            PreparedStatement pt=c.prepareStatement("update user set nbr = ?  where nom_pepiniere=?");
                 User u= new User();
   
                 pt.setInt(1,nbr);
                 
                 pt.setString(2, r.getNom_pepiniere());
                 
                 pt.executeUpdate();

    
}
    
    public void modifierEtat(User u ) throws SQLException 
    {
        try {
            PreparedStatement pt=c.prepareStatement("update user set etat = ? where nom_pepiniere=?");

            
            
            
   
            
            pt.setString(1,"Bloqué");
           
            pt.setString(2,u.getNom_pepiniere());
            
            
          
            
            
          
            
          
            System.out.println("/////"+pt);
            
           pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
     public void supprimerprod(int id)
    {
        try {
            PreparedStatement pt=c.prepareStatement("delete from reclamation where id_produit="+id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAvis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

    

