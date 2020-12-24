/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import java.sql.*;
import Entities.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.*;

/**
 *
 * @author Marouan
 */
public class Service_Avisprod {
   Connection c=  Maconnexion.getinstance().getConn();
 public int get_id_user()
    {   int id_user=0;
        try {   
            Statement st1=c.createStatement();
            String req1="select id_user from user where connected= "+true;
            ResultSet rs1=st1.executeQuery(req1);
             
            while(rs1.next()){
                id_user=rs1.getInt(1);}         
                } catch (SQLException e) {
             System.out.println("err "+ e.getMessage());
         } 
        return id_user;
    }
         
 public void ajouter(Avis_prod a)
    {
        try {
            Statement st1=c.createStatement();
            String req1="select nom from user where id_user= "+get_id_user();
            ResultSet rs1=st1.executeQuery(req1);
            String nom_client="";
            while(rs1.next())
            {
                nom_client=rs1.getString(1);
            } 
            
            
            
            
            Statement st2=c.createStatement();
            String req2="select nom_produit from produit where id_produit= "+a.getId_produit()+"";
            ResultSet rs2=st2.executeQuery(req2);
            String nom_produit="";
            while(rs2.next())
            {
                System.out.println(rs2.getString(1));
                nom_produit=rs2.getString(1);
            } 
            
         Statement  st = c.createStatement();
        String req="insert into avis_produit (id_client,id_produit,nom_client,nom_produit,commentaire,rating) values ("
                +get_id_user()+","+a.getId_produit()+",'"+nom_client+"','"+nom_produit+"','"+a.getCommentaire()+"',"+a.getRating()+")";
             
    st.executeUpdate(req);
          
        } catch (SQLException ex) {
            Logger.getLogger(Service_Avisprod.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    
    
    }
    //************************************************************
    
    
        public void afficher()
    {
        try {
            Statement st=c.createStatement();
            String req="select * from avis";
            ResultSet rs=st.executeQuery(req);
            System.out.println("////////////////"+req);
            
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+rs.getInt(2)+rs.getInt(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7)+rs.getString(8));
            } } catch (SQLException ex) {
                Logger.getLogger(Service_Avisprod.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
             
        public void supprimer(int id)
    {
        try {
            PreparedStatement pt=c.prepareStatement("delete from avis where id_produit="+id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Service_Avisprod.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Service_Avisprod.class.getName()).log(Level.SEVERE, null, ex);
            
        }
     
    
        
    
    
    
}}

      
    

