package service;
import Entities.LigneCommande;
import Entities.Produit;
import interfaces.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.Maconnexion;
/**
 *
 * @author gazzah
 */
public class DAOLigneCommande implements IdaoLigneCommande{
Connection conn=  Maconnexion.getinstance().getConn();

    @Override
    public void AjouterLigneCmd(int id_prod, int qte, int id_client,float montant) {
        
        try {
        String req_conn="insert into ligne_cmd(id_produit,quantite,id_client,montant)"
                +   " VALUES (?,?,?,?)";
       
  PreparedStatement prt = conn.prepareStatement(req_conn);
            prt.setInt(1, id_prod);
            prt.setInt(3, id_client);
            prt.setInt(2,qte);
            prt.setFloat(4,montant);
            
            System.out.println("****"+prt);
            prt.executeUpdate();
            {JOptionPane.showMessageDialog(null,"Ligne Commande Ajouter en succes");}
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Erreur!");}
        System.out.println("Erreur dans Ligne_commande AjouterCmd"+s);
    }
    }

    @Override
    public void SupprimerLigneCmd(LigneCommande Lcmd) {
         try {
        String req_conn="delete from ligne_cmd where id_ligne_cmd=?";
       
  PreparedStatement prt = conn.prepareStatement(req_conn);
            
            prt.setInt(1, Lcmd.getId_ligne_cmd());
            prt.executeUpdate();
            {JOptionPane.showMessageDialog(null,"Commande annuler");}
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Verifier votre information!");}
        System.out.println("Erreur dans Suprimer ligne de cmd");
    }
    }

    @Override
    public void AfficherLigneCmd(LigneCommande Lcmd) {
             try {
        Statement stm=conn.createStatement();
        String req_conn="select * from ligne_cmd ";
       
        ResultSet rs = stm.executeQuery(req_conn);
        while (rs.next())
        {// id_produit,id_cmd,quantiter,id_ligne_cmd;
            System.out.println("Ligne commande id: "+rs.getInt(1)
                                +" id produit: "+ rs.getString(2)
                                +" id cmd: "+rs.getString(3)
                                +" quantiter: "+rs.getString(3)
                                );
        }
    } catch (SQLException ex) {
        System.out.println("erreur dafichage du ligne commande"+ ex);
    }
    }

    @Override
    public void ModifierLigneCmd(LigneCommande Lcmd) {
            try {
        String req_conn="update ligne_cmd set quantite=? where id_ligne_cmd="+Lcmd.getId_ligne_cmd();
                
       
  PreparedStatement prt = conn.prepareStatement(req_conn);
            
              
            prt.setInt(1, Lcmd.getQuantiter());
           
            prt.executeUpdate();
                    System.out.println("***"+prt);
            {JOptionPane.showMessageDialog(null,"Modifier en succes");}
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Erreur!");}
        System.out.println("Erreur dans Modifier ligne de Cmd");
    }
    }      

    public void EditQteLigneCmd(int id_prod) {
            try {
                int x=1;
             
              
        String req_conn="update ligne_cmd set quantite=quantite+1 where id_produit="+id_prod+" and id_client="+x;
       String req_conn2="update ligne_cmd,produit set ligne_cmd.montant=produit.prix*ligne_cmd.quantite where produit.id_produit="+id_prod+" and ligne_cmd.id_produit="+id_prod;
        PreparedStatement prt = conn.prepareStatement(req_conn);
        PreparedStatement prt2 = conn.prepareStatement(req_conn2);
            prt.executeUpdate();
            prt2.executeUpdate();
            System.out.println("***"+prt);
            
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Erreur!");}
        System.out.println("Erreur dans Modifier ligne de Cmd");
    }     
    }
     
     public void EditQteLigneCmd2(int id_prod) {
            try {
                int x=1;
              
        String req_conn="update ligne_cmd set quantite=quantite-1 where id_produit="+id_prod+" and id_client="+x;
        String req_conn2="update ligne_cmd,produit set ligne_cmd.montant=produit.prix*ligne_cmd.quantite where produit.id_produit="+id_prod+" and ligne_cmd.id_produit="+id_prod;
        PreparedStatement prt = conn.prepareStatement(req_conn);
                PreparedStatement prt2 = conn.prepareStatement(req_conn2);
            prt.executeUpdate();
            prt2.executeUpdate();
            System.out.println("***"+prt);
            
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Erreur!");}
        System.out.println("Erreur dans Modifier ligne de Cmd");
    }     
    }
    
     
    public List<Produit> Afficher_LigneCmd(){
         List<Produit> Lcmd = new ArrayList<>();
        Produit p; 
        
            int i=0,x=1;
             try { 
              
            Statement st=conn.createStatement();
         
          String req2="SELECT * FROM produit,ligne_cmd WHERE ligne_cmd.id_client = "+x+" AND ligne_cmd.id_produit=produit.id_produit";
          ResultSet rs=st.executeQuery(req2);
             while (rs.next()){
                p=new Produit(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getInt(9),rs.getString(10),rs.getString(11));
                         Lcmd.add(p); 
             }
             
         } catch (SQLException ex) { 
             System.out.println("err"+ex.getMessage());
         }
        return Lcmd;
         }
    
     public void deleteLigneCmd(int Lcmd) {
         try {
        String req_conn="delete from ligne_cmd where id_produit=?";
       
  PreparedStatement prt = conn.prepareStatement(req_conn);
            
            prt.setInt(1, Lcmd);
            prt.executeUpdate();
            {JOptionPane.showMessageDialog(null,"produit supprimer de votre panier");}
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Verifier votre information!");}
        System.out.println("Erreur dans Suprimer ligne de cmd");
    }
    }
      public void deleteLigneCmdwhenconfirmed(int Lcmd) {
         try {
        String req_conn="delete from ligne_cmd where id_client=?";
       
  PreparedStatement prt = conn.prepareStatement(req_conn);
            
            prt.setInt(1, Lcmd);
            prt.executeUpdate();
            {JOptionPane.showMessageDialog(null,"produit supprimer de votre panier");}
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Verifier votre information!");}
        System.out.println("Erreur dans Suprimer ligne de cmd");
    }
    }
     
     public int CountPanier(int id) throws SQLException
     { 
         int i=0;
         
                 ResultSet rs1 = conn.createStatement().executeQuery("SELECT COUNT(*) FROM ligne_cmd where id_client="+id);
                         while (rs1.next()) {i=rs1.getInt(1);}
                  
          return i;
     }
     
     public boolean verifContenu(int id_user, int prod_id) throws SQLException
     {
         ResultSet rs1 = conn.createStatement().executeQuery("SELECT * FROM ligne_cmd where id_client="+id_user+" and id_produit="+prod_id);
                        boolean existe=true;
                             while (rs1.next()) {existe=false;}
                             return existe;
     }
     
     public int newprix (int prod_id) throws SQLException
     {int i=0;
         ResultSet rs1 = conn.createStatement().executeQuery("SELECT montant FROM ligne_cmd where id_produit="+prod_id);
                        
                             while (rs1.next()) {i=rs1.getInt(1);}
                             return i;
     }
     public void remiseTotale(LigneCommande Lcmd) {
            try {
        String req_conn="update ligne_cmd set montant=? where id_ligne_cmd="+Lcmd.getId_ligne_cmd();
                
       
  PreparedStatement prt = conn.prepareStatement(req_conn);
            
              
            prt.setInt(1, Lcmd.getQuantiter());
           
            prt.executeUpdate();
                    System.out.println("***"+prt);
            {JOptionPane.showMessageDialog(null,"Modifier en succes");}
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Erreur!");}
        System.out.println("Erreur dans Modifier ligne de Cmd");
    }
    }  
     public boolean Verifqte2(int prod_id) throws SQLException
     {
         int i=0,x=0;
        boolean existe=false;
        ResultSet rs1 = conn.createStatement().executeQuery("SELECT produit.quantite,ligne_cmd.quantite FROM produit,ligne_cmd where produit.id_produit="+prod_id+" and ligne_cmd.id_produit="+prod_id);
         
        while (rs1.next()) {
            i=rs1.getInt(1);
            x=rs1.getInt(2);
        } 
        if(i!=0 && i>x)existe=true;
        else existe=false;
        
       return existe;
   
     }
      public boolean Verifqte(int prod_id) throws SQLException
     {
         int i=0;
        boolean existe=false;
        ResultSet rs1 = conn.createStatement().executeQuery("SELECT quantite FROM produit where id_produit="+prod_id);
         
        while (rs1.next()) {
            i=rs1.getInt(1);
           
        } 
        if(i!=0)existe=true;
        else existe=false;
        
       return existe;
   
     }
       public boolean Verifqte3(int prod_id) throws SQLException
     {
         int i=0;
        boolean existe=false;
        ResultSet rs1 = conn.createStatement().executeQuery("SELECT quantite FROM ligne_cmd where id_produit="+prod_id);
         
        while (rs1.next()) {
            i=rs1.getInt(1);
           
        } 
        if(i>1)existe=true;
        else existe=false;
        
       return existe;
   
     }
        public boolean VerifPanier() throws SQLException
     {
         int i=0;
        boolean existe=false;
        ResultSet rs1 = conn.createStatement().executeQuery("SELECT count(*) FROM ligne_cmd");
         
        while (rs1.next()) {
            i=rs1.getInt(1);
           
        } 
        if(i>0)existe=true;
        else existe=false;
        
       return existe;
   
     }
            public int CalculeQte(int prod_id) throws SQLException
     {
         int i=0;
       
        ResultSet rs1 = conn.createStatement().executeQuery("SELECT quantite FROM ligne_cmd where id_produit="+prod_id);
         
        while (rs1.next()) {
            i=rs1.getInt(1);
           
        }        
       return i;
   
     }
}
