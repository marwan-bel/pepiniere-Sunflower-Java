package service;

import Entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utils.Maconnexion;


public class Services_favoris {
    Connection C= Maconnexion.getinstance().getConn();
 public boolean verifexistance( int prod_id) throws SQLException
     {
         ResultSet rs1 = C.createStatement().executeQuery("SELECT * FROM favoris where id_client=1 and id_produit="+prod_id);
                        boolean existe=true;
                             while (rs1.next()) {existe=false;}
                             return existe;
     }  
      public List<String> get_tel(int id_p) {
        List<String> str=new ArrayList<>();
    {      String n;
    
        int i=0;
        try {   
            Statement st1=C.createStatement();
            String req1="select id_client from favoris where id_produit= "+id_p;
            ResultSet rs1=st1.executeQuery(req1);
             
            while(rs1.next()){
                i=rs1.getInt(1);
                    
            
            Statement st=C.createStatement();
            String req="select telephone from user where id_user= "+i;
            ResultSet rs=st.executeQuery(req);
             
            while(rs.next()){
                n=rs.getString(1);
                str.add(n); 
            }    }        
                } catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         } 
        return str;
    }     
    }
   
   public int get_id_user()
    {   int id_user=0;
        try {   
            Statement st1=C.createStatement();
            String req1="select * from user where connected= "+true;
            ResultSet rs1=st1.executeQuery(req1);
             
            while(rs1.next()){
                id_user=rs1.getInt(1);}         
                } catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         } 
        return id_user;
    } 
   
   public Produit get_prod(int i){
       Produit p = null;
        try {   
            Statement st1=C.createStatement();
            String req1="select * from Produit where id_produit= "+i;
            ResultSet rs1=st1.executeQuery(req1);
             
            while(rs1.next()){
                p=new Produit(rs1.getInt(1), rs1.getInt(2), rs1.getInt(3), rs1.getString(4), rs1.getString(5), rs1.getString(6), rs1.getString(7), rs1.getFloat(8), rs1.getInt(9), rs1.getString(10), rs1.getString(11));
                        }         
                } catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         } 
        return p;
    } 
      
   public String get_nom_pep(Produit p)
    {   String n="";
        try {   
            Statement st1=C.createStatement();
            String req1="select nom_pepiniere from user where id_user= "+p.getId_user();
            ResultSet rs1=st1.executeQuery(req1);
             
            while(rs1.next()){
                n=rs1.getString(1);}         
                } catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         } 
        return n;
    } 
   
   public void Ajouter_favoris(int p ,Favoris f) {
        
         try {
            Statement st1=C.createStatement();
            String req1="select nom_produit,quantite from Produit where"
                    + " id_produit= "+p ;
            ResultSet rs1=st1.executeQuery(req1);
            String nom_prod="";
            int qte=0;
            while(rs1.next()){nom_prod=rs1.getString(1);
                    
                     qte=rs1.getInt(2);}
            
          
            boolean dispo=false;
            if (qte>0)dispo=true;
            if(verifexistance(p)){
            Statement st=C.createStatement();
            String req ="insert into favoris values("
                     +f.getId_fav()+","+p
                     +",1,'"+nom_prod
                     +"','"+dispo+"')";
             System.out.println("doneeeeee "+req);
             st.executeUpdate(req);   }
         else {JOptionPane.showMessageDialog(null,"Produit deja existant dans votre liste des favoris!");}
         } catch (SQLException e) {
             System.out.println("erdr      "+ e.getMessage());
         }}  
     
   public List<Favoris> Afficher_favoris(){
         List<Favoris> lf=new ArrayList<>();
        Favoris f;
         try {
     
         Statement st=C.createStatement();
                 String q;
           
        q="select * from Favoris where id_client=1";
         
         ResultSet rs=st.executeQuery(q);
         
             while (rs.next()){
                
                 f=new Favoris(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                 lf.add(f);
             }
         } catch (SQLException ex) { 
             System.out.println("er'r"+ex.getMessage());
         }
         return lf;
         }
     
          public void Modifier_favoris(Favoris f,int idf,int idp){
         try {
            Statement st2=C.createStatement();
            String req2="select role from user where id_user= "
                    +get_id_user();
            ResultSet rs2=st2.executeQuery(req2);
            String role_ag="";
            while(rs2.next()){              
                role_ag=rs2.getString(1);}  
             Statement st1=C.createStatement();
            String req1="select nom_produit from Produit where"
                    + " id_produit= "+idp/*f.getIdProd()*/+"";
            ResultSet rs1=st1.executeQuery(req1);
            String nom_prod="";
            while(rs1.next()){nom_prod=rs1.getString(1);} 
           
         if(role_ag.equals("client")){
            PreparedStatement ps = C.prepareStatement("update favoris set "
                 + "id_produit=?  ,"
                 + "nom_produit=?,disponibilite=? where id_favoris=?");
  
         ps.setInt    (1, idp);
         ps.setString (3, nom_prod);
         ps.setString (4, f.getDispo());
         ps.setInt    (5, idf);
         System.out.println("--------- "+ps);
         ps.executeUpdate();
         }
         else {JOptionPane.showMessageDialog(null,"Erreur!");}
        
         } catch (SQLException ex) {
             System.out.println("err "+ex.getMessage());
         }}
        
         public void Supprimer_favoris(int id){
         try {
         PreparedStatement ps=C.prepareStatement("delete from favoris where id_favoris=?");
          
            ps.setInt(1, id);
             System.out.println("******** "+ ps);
            ps.executeUpdate(); 
         } catch (SQLException ex) {
             System.out.println("err "+ex.getMessage());
         } }
}
