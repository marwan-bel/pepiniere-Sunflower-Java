/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import Entities.Promotion;
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
public class ServicePromotion {
    private int idp;
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
     
    public void ajouter(Promotion p,int id,float prix)
    {
       
   
        try {
        
       
            Statement st1=c.createStatement();
            String req1="select nom from user where id_user= "+p.getId_user()+"";
            ResultSet rs1=st1.executeQuery(req1);
            String nom="";
            while(rs1.next())
            {
                System.out.println(rs1.getString(1));
                nom=rs1.getString(1);
            } 
            
            
            
            
           Statement st2=c.createStatement();
            String req2="select nom_produit from produit where id_produit= "+p.getId_produit()+"";
            ResultSet rs2=st2.executeQuery(req2);
            String nom_pro="";
            while(rs2.next())
            {
                System.out.println(rs2.getString(1));
                nom_pro=rs2.getString(1);
            } 
            
         Statement  st = c.createStatement();
        String req="insert into promotion (id_agent,id_produit,nom_promotion,nom_agent,nom_produit,prix_hab,prix_promo,pourcentage,date_debut,date_fin,description) values ("+p.getId_user()+","+p.getId_produit()+",'"+p.getNom_promotion()+"','"+nom+"','"+nom_pro+"','"+p.getPrix_hab()+"','"+p.getPrix_promo()+"','"+p.getPourcentage()+"','"+p.getDate_debut()+"','"+p.getDate_fin()+"','"+p.getDescription()+"')";
    
     st.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
         ResultSet rs3= st.getGeneratedKeys();
         int id_pro=0;    
         if (rs3.next()){
             id_pro=rs3.getInt(1);
                  System.out.println(rs3.getInt(1));
        }
           
    p.setId_promotion(id_pro);
    
          
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
    

            try {
            PreparedStatement pt=c.prepareStatement("update produit set prix = ? where id_produit= ?");
            
            pt.setFloat(1,prix);
       
            pt.setInt(2,id);
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    //************************************************************//
    
    
        public void afficher()
    {
        try {
            Statement st=c.createStatement();
            String req="select nom_promotion,nom_agent,nom_produit,prix_hab,prix_promo,pourcentage,date_debut,date_fin,description from promotion";
            ResultSet rs=st.executeQuery(req);
            
            while(rs.next())
            {
                System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getFloat(4)+rs.getFloat(5)+rs.getInt(6)+rs.getString(7)+rs.getString(8)+rs.getString(9));
            } } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      
    }
        
   //***************************************************************//
        
        
     public void modifier(int id,Promotion p)
    {
        try {
            PreparedStatement pt=c.prepareStatement("update promotion set nom_promotion = ? ,prix_hab = ? ,prix_promo = ? ,pourcentage = ?,date_debut = ?,date_fin= ? ,description = ? where id_promotion= ?");
            
            pt.setString(1,p.getNom_promotion());
            pt.setFloat(2,p.getPrix_hab());
            pt.setFloat(3,p.getPrix_promo());
            pt.setInt(4,p.getPourcentage());
            pt.setString(5,p.getDate_debut());
            pt.setString(6,p.getDate_fin());
            pt.setString(7,p.getDescription());
            pt.setInt(8,id);
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
                    try {
            Statement st=c.createStatement();
            String req="select id_produit from promotion where id_promotion="+id +"";
            
    
    ResultSet rs=st.executeQuery(req);
            int idd=0;
            while(rs.next())
            {
                idd=rs.getInt(1);this.idp=idd;
               
            }  } 
            catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
                 try {
            PreparedStatement pt=c.prepareStatement("update produit set prix = ? where id_produit= ?");
            
            pt.setFloat(1,p.getPrix_promo());
       
            pt.setInt(2,idp);
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
      
        
        
        
        
       
        
        
        
        
        
     
    }
        
   //*************************************************************************//     
        
       public void supprimer(int id)
    {
     
       try {
          
            
            int ii=0;
            float pp=0;
            
                Statement st2=c.createStatement();
            String req2="select id_produit,prix_hab from promotion where id_promotion="+id+"";
            ResultSet rs2=st2.executeQuery(req2);
            
            while(rs2.next())
            {   
             ii=  rs2.getInt(1);
                    pp=  rs2.getFloat(2);
            }      
                                     
            PreparedStatement pt=c.prepareStatement("update produit set prix = ? where id_produit= ?");
            
            pt.setFloat(1,pp);
       
            pt.setInt(2,ii);
            
            pt.executeUpdate();
                
                
                
                           PreparedStatement ptt=c.prepareStatement("delete from promotion where id_promotion= ? ");
            
            ptt.setInt(1, id);
            ptt.executeUpdate();
                
      
      
                
                
                
                
                
                
           
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    } 
    
    
    /*----------------------------------------------------------*/
       
               
       public void supprimer_auto()
    {
        try {
          
            
            
            
                Statement st2=c.createStatement();
            String req2="select id_produit,date_fin,prix_hab,id_promotion from promotion where date_fin<SYSDATE()";
            ResultSet rs2=st2.executeQuery(req2);
            String nom_pro="";
            while(rs2.next())
            {    System.out.println(rs2.getInt(3));
                System.out.println(rs2.getString(2));
                nom_pro=rs2.getString(2);
                
                
                                     
            PreparedStatement pt=c.prepareStatement("update produit set prix = ? where id_produit= ?");
            
            pt.setFloat(1,rs2.getFloat(3));
       
            pt.setInt(2,rs2.getInt(1));
            
            pt.executeUpdate();
                
                
                
                           PreparedStatement ptt=c.prepareStatement("delete from promotion where id_promotion= ? ");
            
            ptt.setInt(1, rs2.getInt(4));
            ptt.executeUpdate();
                
      
      
                
                
                
                
                
                
            } 
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
       
       
      /**********************/
       
       
       
       
       
       
       
        
            public List<Promotion> afficher_liste()
    {
            List<Promotion> Lev = new ArrayList<>();
        Promotion ev; 
        
        try {
            Statement st=c.createStatement();
            String req="select id_produit,nom_promotion,nom_produit,prix_hab,prix_promo,pourcentage,date_debut,date_fin,description,nom_agent from promotion";
            ResultSet rs=st.executeQuery(req);
            
            while(rs.next())
            {
                        
            ev=new Promotion(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getFloat(4), rs.getFloat(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)); 
            
                 Lev.add(ev); 
            
            } } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Lev;
      
      
      
    }
  /**********************/
           
       public String afficher_p(int id){
           
            String ev=""; 
        
        try {
            Statement st=c.createStatement();
            String req="select image from produit where id_produit= "+id+" ";
            ResultSet rs=st.executeQuery(req);
              System.out.println(id);
           
            while(rs.next())
            {
         ev= rs.getString(1);
             //  System.out.println("a");
           
            }return ev; } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ev;
          
           
           
       } 
       
     
       /****************/
       
       
       
       
               public List<Promotion> afficher_liste_pro(String rech)
    {
            List<Promotion> Lev = new ArrayList<>();
        Promotion ev; 
        
        try {
            Statement st=c.createStatement();
            String req="select id_produit,nom_promotion,nom_produit,prix_hab,prix_promo,pourcentage,date_debut,date_fin,description,nom_agent from promotion where nom_produit  LIKE '%" + rech + "%'"; 
            ResultSet rs=st.executeQuery(req);
               System.out.println(rech);
            while(rs.next())
            {
                        
            ev=new Promotion(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getFloat(4), rs.getFloat(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)); 
            System.out.println("a"+rs.getString(2));
                 Lev.add(ev); 
            
            } } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Lev;
      
      
      
    }
     /******************************/
               
               
               
                        public List<Promotion> afficher_liste_pou(String rech)
    {
            List<Promotion> Lev = new ArrayList<>();
        Promotion ev; 
        
        try {
            Statement st=c.createStatement();
            String req="select id_produit,nom_promotion,nom_produit,prix_hab,prix_promo,pourcentage,date_debut,date_fin,description,nom_agent from promotion where nom_agent = '"+rech+"' "; 
            ResultSet rs=st.executeQuery(req);
               //System.out.println(rech);
            while(rs.next())
            {
                        
            ev=new Promotion(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getFloat(4), rs.getFloat(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)); 
           // System.out.println("a"+rs.getString(2));
                 Lev.add(ev); 
            
            } } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Lev;
      
      
      
    }
       
    public void supprimerprod(int id)
    {
        try {
            PreparedStatement pt=c.prepareStatement("delete from promotion where id_produit="+id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAvis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
