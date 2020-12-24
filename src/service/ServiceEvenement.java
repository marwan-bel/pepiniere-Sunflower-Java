/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import Entities.Evenement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.*;
 

/**
 *
 * @author starmedia
 */
public class ServiceEvenement {
    
    Connection c= Maconnexion.getinstance().getConn();
         public int get_id_user()
    {   int id_user=0;
        try {   
            Statement st1=c.createStatement();
            String req1="select id_user from user where connected= "+true;
            ResultSet rs1=st1.executeQuery(req1);
             
            while(rs1.next()){
                id_user=rs1.getInt(1);}         
                } catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         } 
        return id_user;
    } 
         
                  public String get_mail(int id)
    {   String mail="";
        try {   
            Statement st1=c.createStatement();
            String req1="select email from user where id_user= "+id+" ";
            ResultSet rs1=st1.executeQuery(req1);
             
            while(rs1.next()){
                mail=rs1.getString(1);}         
                } catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         } 
        return mail;
    } 
                  
                                   public String get_nom(int id)
    {   String mail="";
        try {   
            Statement st1=c.createStatement();
            String req1="select nom from user where id_user= "+id+" ";
            ResultSet rs1=st1.executeQuery(req1);
             
            while(rs1.next()){
                mail=rs1.getString(1);}         
                } catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         } 
        return mail;
    } 
                  
    public void ajouter(Evenement e)
    {
       
   
        try { 
        
       
            Statement st2=c.createStatement();
            String req="select pseudo from user where id_user= '"+e.getId_user()+"'";
            ResultSet rs=st2.executeQuery(req);
            String nom="";
            while(rs.next())
            {
                System.out.println(rs.getString(1));
                nom=rs.getString(1);
            } 
            
        Statement  st = c.createStatement();
        String req2="insert into evenement(id_admin,nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,path,max) values ("+e.getId_user()+",'"+e.getNom_evenement()+"','"+nom+"','"+e.getType()+"','"+e.getLieu()+"','"+e.getDate_debut()+"','"+e.getDate_fin()+"','"+e.getDescription()+"','"+e.getPath()+"', "+e.getMax()+"  )";
    
    st.executeUpdate(req2, Statement.RETURN_GENERATED_KEYS);
         ResultSet rs3= st.getGeneratedKeys();
         int id_eve=0;    
         if (rs3.next()){
             id_eve=rs3.getInt(1);
                  System.out.println(rs3.getInt(1));
        }
            
    e.setId_evenement(id_eve);
    
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    
    
    }
    //************************************************************//
    
    
        public void afficher()
    {
        try {
            Statement st=c.createStatement();
            String req="select nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description from evenement";
            ResultSet rs=st.executeQuery(req);
            
            while(rs.next())
            {
                System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7));
            } } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      
    }
        
   //***************************************************************//
        
        
     public void modifier(int id,Evenement e)
    {
        try {
            PreparedStatement pt=c.prepareStatement("update evenement set nom_evenement = ? ,type = ? ,lieu = ? ,date_debut = ?,date_fin= ? ,description = ?,max= ? where id_evenement= ?");
            
            pt.setString(1,e.getNom_evenement());
            pt.setString(2,e.getType());
            pt.setString(3,e.getLieu());
            pt.setString(4,e.getDate_debut());
            pt.setString(5,e.getDate_fin());
            pt.setString(6,e.getDescription());
            pt.setInt(7,e.getMax());
            pt.setInt(8, id);
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
        
   //*************************************************************************//     
        
       public void supprimer(int id)
    {
        try {
            PreparedStatement pt=c.prepareStatement("delete from evenement where id_evenement= ? ");
            
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
       
       
       
       /*------------------------------------------*/
       
       
            public List<Evenement> afficher_liste()
    {
            List<Evenement> Lev = new ArrayList<>();
        Evenement ev; 
        
        try {
            Statement st=c.createStatement();
            String req="select id_evenement,id_admin,nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description,path,max from evenement";
            ResultSet rs=st.executeQuery(req);
            
            while(rs.next())
            {
                
                ev = new Evenement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11));
                 
                 Lev.add(ev); 
            
            } } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Lev;
      
      
      
    }
        
   
               public List<Evenement> afficher_liste2(String type)
    {
            List<Evenement> Lev = new ArrayList<>();
        Evenement ev; 
        
        try {
            Statement st=c.createStatement();
            String req="select * from evenement where type='animation'";
            ResultSet rs=st.executeQuery(req);
            
            while(rs.next())
            {
                
                ev = new Evenement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11));
                 
                 Lev.add(ev); 
            
            } } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Lev;
      
      
      
    }
            
            
            
            
}
