package service;

import Entities.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List; 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane; 
import utils.Maconnexion;
 
public class Services_produit {
   Connection conn= Maconnexion.getinstance().getConn();
    public int get_id_user(){   
        int id_user=0;
        try {   
            Statement st1=conn.createStatement();
            String req1="select id_user from user where connected= "+true;
            ResultSet rs1=st1.executeQuery(req1);
            while(rs1.next()){
                id_user=rs1.getInt(1);}         
                } catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         } 
        return id_user;
    } 
        
    public String get_nom_pep(Produit p){   
        String n="";
        try {   
            Statement st1=conn.createStatement();
            String req1="select nom_pepiniere from user where id_user= "+p.getId_user();
            ResultSet rs1=st1.executeQuery(req1);
             
            while(rs1.next()){
                n=rs1.getString(1);}         
                } catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         } 
        return n;
    } 
        
    public void Ajouter_Produit(Produit p) {
          try {   
         
            
            Statement st2=conn.createStatement();
            String req2="select id_categorie,type from categorie_produit where nom_categorie= '"+p.getNom_categorie()+"'";
            ResultSet rs2=st2.executeQuery(req2);
            int id_ct = 0;
            String type_ct="";
            while(rs2.next()){id_ct=rs2.getInt(1);
                              type_ct=rs2.getString(2);} 
 
        
             Statement st=conn.createStatement();
             String req ="insert into produit(id_produit,id_categorie,"
                     + "nom_produit,nom_categorie,type,prix,quantite,image,description) "
                     + " values("
             +p.getId_prod()+","+id_ct+",'"
             +p.getNom_prod()+"','"
             +p.getNom_categorie()+"','"+type_ct+"',"
             +p.getPrix_prod() +","+p.getQte_prod()  +",'"
             +p.getImg()+"','"+p.getDesc_prod() +"')";     
             st.executeUpdate(req); 
             System.out.println("/*/*/*/*/*"+req);
         } catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         }}

    public void Modifier_Produit(Produit p,int i){
         try {
            Statement st1=conn.createStatement();
            String req1="select nom,role from user where id_user=  "+get_id_user()+"";
            ResultSet rs1=st1.executeQuery(req1);
            String nom_ag="";
            String role_ag="";
            while(rs1.next()){
                nom_ag=rs1.getString(1);             
                role_ag=rs1.getString(2);} 
            
  
            Statement st2=conn.createStatement();
            String req2="select id_categorie,type from categorie_produit where nom_categorie= '"+p.getNom_categorie()+"'";
            ResultSet rs2=st2.executeQuery(req2);
            int id_ct=0;
            String type_ct="";
           
          while(rs2.next()){id_ct=rs2.getInt(1);
                              type_ct=rs2.getString(2);} 
           if(role_ag.equals("agent")){
                
         PreparedStatement ps = conn.prepareStatement("update produit set  "
                 + "id_categorie=?, nom_produit=? ,"
                 + " nom_categorie=?  ,type=? ,prix=? "
                 + ",quantite=?,image=?,"
                 + "description=? where id_produit=?");
         
         ps.setInt      (1, id_ct);
         ps.setString   (2, p.getNom_prod());
         ps.setString   (3, p.getNom_categorie()); 
         ps.setString   (4, type_ct); 
         ps.setDouble   (5, p.getPrix_prod());
         ps.setInt      (6, p.getQte_prod());
         ps.setString   (7, p.getImg());
         ps.setString   (8, p.getDesc_prod()); 
         ps.setInt      (9, i);
         ps.executeUpdate();
          }else {JOptionPane.showMessageDialog(null,"Erreur!");}
         } catch (SQLException ex) {
             System.out.println("err "+ex.getMessage());
         }}
                
    public List<Produit> Afficher_Produit(){
         List<Produit> Lprods = new ArrayList<>();
            Produit p; 
        try {
           
            Statement st1=conn.createStatement();
            String req1="select role from user where id_user= "+get_id_user();
            ResultSet rs1=st1.executeQuery(req1);
           
            String role_usr="";
            while(rs1.next()){role_usr=rs1.getString(1);}      
           
           
         Statement st=conn.createStatement();
         String q;
         if (role_usr.equals("agent"))   
                q="select * from produit where id_agent="+get_id_user();
         
         else   q="select * from produit";
         
         ResultSet rs=st.executeQuery(q);
         
             while (rs.next()){
                p=new Produit(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getInt(9),rs.getString(10),rs.getString(11));
                         Lprods.add(p); 
             }
         } catch (SQLException ex) { 
             System.out.println("err"+ex.getMessage());
         }
        return Lprods;
         }
     public List<Produit> Afficher_Produitc(){
         List<Produit> Lprods = new ArrayList<>();
            Produit p; 
        try {
           
           
           
         Statement st=conn.createStatement();
         String q;
          q="select * from produit";
         
         ResultSet rs=st.executeQuery(q);
         
             while (rs.next()){
                p=new Produit(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getInt(9),rs.getString(10),rs.getString(11));
                         Lprods.add(p); 
             }
         } catch (SQLException ex) { 
             System.out.println("err"+ex.getMessage());
         }
        return Lprods;
         }
     
    public ObservableList rech_prod(String s){
     
    ObservableList<Produit> oblist = FXCollections.observableArrayList();
           
        try {
            ResultSet rs = conn.createStatement().executeQuery(
                                "SELECT * FROM Produit WHERE  nom_produit   LIKE '%" + s + "%'"
                        + "UNION SELECT * FROM Produit WHERE  nom_categorie LIKE '%" + s + "%'"
                        + "UNION SELECT * FROM Produit WHERE  type          LIKE '%" + s + "%'"
                        + "UNION SELECT * FROM Produit WHERE  prix          LIKE '%" + s + "%'"
                        + "UNION SELECT * FROM Produit WHERE  quantite      LIKE '%" + s + "%'"
                        + "UNION SELECT * FROM Produit WHERE  description   LIKE '%" + s + "%'");
            while (rs.next()) {
                if (rs.getInt(2)==get_id_user())
    oblist.add(new Produit(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getInt(9),rs.getString(10),rs.getString(11)));
               }
                    
            
        } catch (SQLException ex) {
            System.err.println("ERROR" + ex);

        }
        return oblist;
        
    }
    public List<Produit> rech_prodcl(String s){
     
     List<Produit> oblist = new ArrayList();
           
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Produit WHERE  nom_produit   LIKE '%" + s + "%'");
            while (rs.next()) {
    oblist.add(new Produit(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getInt(9),rs.getString(10),rs.getString(11)));
               }
                    
            
        } catch (SQLException ex) {
            System.err.println("ERROR" + ex);

        }
        return oblist;
        
    }
  
    public void Supprimer_Produit(int id){
        DAOLigneCommande sd =new DAOLigneCommande();
       // sd.SupprimerLigneCmd(Lcmd);
        Services_favoris sv=new Services_favoris();
        sv.Supprimer_favoris(id);
        try{
         PreparedStatement pt2=conn.prepareStatement("delete from produit where id_produit="+id);
          pt2.executeUpdate();
         } catch (SQLException ex) {
             System.out.println("err "+ex.getMessage());
         }
         } 

    public List<Produit> Afficher_Produit_prom() throws SQLException {
         
           List<Produit> Lprodsprom = new ArrayList<>();
            Produit pprom; 
        try {
           
            Statement st1=conn.createStatement();
            String req1="select id_produit from promotion where pourcentage>50";
            ResultSet rs1=st1.executeQuery(req1);
            int id_prod=0;
            
            while(rs1.next())
            {id_prod=rs1.getInt(1);
          
            Statement st=conn.createStatement();
            String q ="select * from produit  where id_produit="+id_prod;
            ResultSet rs=st.executeQuery(q);
         
             while (rs.next()){
                pprom=new Produit(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getInt(9),rs.getString(10),rs.getString(11));
                        Lprodsprom.add(pprom);  
             }  }

          
         } catch (SQLException ex) { 
             System.out.println("err"+ex.getMessage());
         }
        return Lprodsprom;
        
        
        }

    public ObservableList get_categorie(String s){
     
    ObservableList<String> oblist = FXCollections.observableArrayList();
           
        try {
            ResultSet rs = conn.createStatement().executeQuery(
             "SELECT nom_categorie FROM categorie_produit WHERE  type= '" + s + "'");
            
            while (rs.next()) {
            oblist.add(rs.getString(1));

            }
        } catch (SQLException ex) {
            System.err.println("ERROR" + ex);

        }
        return oblist;
        
    } 
    
    
 

   /* public List<Produit> Afficher_Produit_rated() throws SQLException{
            List<Produit> Lprodrated = new ArrayList<>();
            Produit pr; 
 
            Statement st1=conn.createStatement();
            String q1 ="select id_produit from avis_produit order by AVG(rating) LIMIT 7";
            ResultSet rs1=st1.executeQuery(q1);
         
            while (rs1.next()){
                pr=new Produit(rs1.getInt(1),rs1.getInt(2),rs1.getInt(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getFloat(8),rs1.getInt(9),rs1.getString(10),rs1.getString(11));
                Lprodrated.add(pr); 
            }            
            
            Statement st=conn.createStatement();
            String q ="select * from produit ORDER BY date_ajout DESC LIMIT 7";
            ResultSet rs=st.executeQuery(q);
         
            while (rs.next()){
                pr=new Produit(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getInt(9),rs.getString(10),rs.getString(11));
                Lprodrated.add(pr); 
            }
             
        return Lprodrated;
    }*/
        public List<Produit> Afficher_Produit_new() throws SQLException{
            List<Produit> Lprodnew = new ArrayList<>();
            Produit pnew; 
 
            Statement st=conn.createStatement();
            String q ="select * from produit ORDER BY date_ajout DESC LIMIT 7";
            ResultSet rs=st.executeQuery(q);
         
             while (rs.next()){
                pnew=new Produit(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getInt(9),rs.getString(10),rs.getString(11));
                        Lprodnew.add(pnew); 
             }
             
        return Lprodnew;
        }
       public String get_x(String x,int id_p) {
        
    {   String n="";
        try {   
            Statement st1=conn.createStatement();
            String req1="select "+x+" from Promotion where id_Produit= "+id_p;
            ResultSet rs1=st1.executeQuery(req1);
             
            while(rs1.next()){
                n=rs1.getString(1);
            }         
                } catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         } 
        return n;
    } 
    
    }
  
}