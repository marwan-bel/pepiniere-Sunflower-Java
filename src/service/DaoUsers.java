package service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import utils.Maconnexion;
import Entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gazzah
 */
public class DaoUsers{
Connection conn=  Maconnexion.getinstance().getConn();
 Statement stm;  
    public String get_role(){   
        String role="";  
        try {   
            Statement st1=conn.createStatement();
            String req1="select email from user where connected= "+true;
            ResultSet rs1=st1.executeQuery(req1);
            while(rs1.next()){
                role=rs1.getString(1);}         
                } catch (SQLException e) {
             System.out.println("errrr "+ e.getMessage());
         } 
        return role;
    }
    
    public void insertUser(User user) {
        System.out.println("hhhhhh");
       try {
        String req_conn="insert into user(password,nom,prenom,sexe,adresse,email,email_canonical,username,username_canonical,enabled,roles) "
                +   "VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?)";
       System.out.println(req_conn);
  PreparedStatement prt = conn.prepareStatement(req_conn);
            
            prt.setString(1, user.getPassword());
            prt.setString(2, user.getNom());
            prt.setString(3, user.getPrenom());
            prt.setString(4, user.getSexe());
            prt.setString(5, user.getAdresse());
            prt.setString(6, user.getEmail());
             prt.setString(7, user.getEmail());
            prt.setString(8, user.getNom());
            prt.setString(9, user.getNom());
            prt.setInt(10, 1);
            prt.setString(11, "a:0:{}");
            
            System.out.println(prt);
            prt.executeUpdate();
             
            {JOptionPane.showMessageDialog(null,"Ajout en succes");}
    } catch (SQLException s) {
        
        System.out.println("Erreur dans insertion Clinet"+s);
    }
    }

   
    public void deleteUser(int i) {
          try {
        String req_conn="delete from user where id_user=?";
       
  PreparedStatement prt = conn.prepareStatement(req_conn);
            
            prt.setInt(1, i);
            prt.executeUpdate();
            {JOptionPane.showMessageDialog(null,"user a été supprimer");}
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Verifier votre information!");}
        System.out.println("Erreur dans Supprimer user");
    }

    }

   
    public void afficheUser(User user) {
                  
    try {
        
        String req_conn="select * from user ";
        
        ResultSet rs = stm.executeQuery(req_conn);
        while (rs.next())   
        {
            user.setId_client(rs.getInt(1));
            user.setPseudo(rs.getString(2));
             user.setPassword(rs.getString(3));
             user.setNom(rs.getString(4));
             user.setPrenom(rs.getString(5));
             user.setSexe(rs.getString(6));
             user.setDate_de_naissance(rs.getString(7));
            user.setAdresse(rs.getString(8));
            user.setTelephone(rs.getString(9));
            user.setEmail(rs.getString(10));
            user.setEtat(rs.getString(11));
            user.setRole(rs.getString(13));
            user.setCin(rs.getString(14));
            user.setNom_pepiniere(rs.getString(15));
            user.setImage(rs.getString(16));
           
            System.out.println("User id: "+rs.getInt(1)+" pseudo: "+ rs.getString(2)+" password: "+rs.getString(3) +" nom: "+rs.getString(4)
                                +" prenom: "+rs.getString(5)+" sexe: "+rs.getString(6)+" date_de_naissance : "+rs.getString(7)
                                +" adresse : "+rs.getString(8) +" telfone : "+rs.getString(9) +" mail : "+rs.getString(10)
                                +" etat : "+rs.getString(11)+" date de creation : "+rs.getString(12)+" role : "+rs.getString(13)
                                +" cin : "+rs.getString(14)+" nom_pepiniere : "+rs.getString(15)+" image : "+rs.getString(16)
                    
                                );
        }
    } catch (SQLException ex) {
        System.out.println("erreur dafichage du commande"+ ex);
    }
    }


    public void modifUser(User user) {
          try {
        String req_conn="update user set pseudo=?, password=?, nom=? ,prenom=?,sexe=?,date_de_naissance=?"
                +",adresse=?,telephone=?,email=?,etat=?,role=?,cin=?,nom_pepiniere=?,image=? where id_user=?";
  PreparedStatement prt = conn.prepareStatement(req_conn);
            prt.setString(1, user.getPseudo());
            prt.setString(2, user.getPassword());
            prt.setString(3, user.getNom());
            prt.setString(4, user.getPrenom());
            prt.setString(5, user.getSexe());
            prt.setString(6, user.getDate_de_naissance());
            prt.setString(7, user.getAdresse());
            prt.setString(8, user.getTelephone());
            prt.setString(9, user.getEmail());
            prt.setString(10, user.getEtat());
            prt.setString(11, user.getRole());
            prt.setString(12, user.getCin());
            prt.setString(13, user.getNom_pepiniere());
            prt.setString(14, user.getImage());
            prt.setInt(15, user.getId_client());
            prt.executeUpdate();
                    System.out.println("***"+prt);
            {JOptionPane.showMessageDialog(null,"Modifier en succes");}
    } catch (SQLException s) {
        {JOptionPane.showMessageDialog(null,"Erreur!");}
        System.out.println("Erreur dans ModifierCmd");
    }
    }
    

      public void rechercherUser() {
                  User user= new User();
    try {
     
        String req_conn="SELECT * FROM user WHERE pseudo LIKE '%?%'"
                        + "UNION SELECT * FROM user WHERE nom LIKE '%?%'"
                        + "UNION SELECT * FROM user WHERE prenom LIKE '%?%'"
                        + "UNION SELECT * FROM user WHERE email LIKE '%?%'"
                        + "UNION SELECT * FROM user WHERE cin LIKE '%?%'";
        
        ResultSet rs = stm.executeQuery(req_conn);
        while (rs.next())   
        {
            user.setId_client(rs.getInt(1));
            user.setPseudo(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setNom(rs.getString(4));
            user.setPrenom(rs.getString(5));
            user.setSexe(rs.getString(6));
            user.setDate_de_naissance(rs.getString(7));
            user.setAdresse(rs.getString(8));
            user.setTelephone(rs.getString(9));
            user.setEmail(rs.getString(10));
            user.setEtat(rs.getString(11));
            user.setRole(rs.getString(13));
            user.setCin(rs.getString(14));
            user.setNom_pepiniere(rs.getString(15));
            user.setImage(rs.getString(16));
           
            System.out.println("User id: "+rs.getInt(1)+" pseudo: "+ rs.getString(2)+" password: "+rs.getString(3) +" nom: "+rs.getString(4)
                                +" prenom: "+rs.getString(5)+" sexe: "+rs.getString(6)+" date_de_naissance : "+rs.getString(7)
                                +" adresse : "+rs.getString(8) +" telfone : "+rs.getString(9) +" mail : "+rs.getString(10)
                                +" etat : "+rs.getString(11)+" date de creation : "+rs.getString(12)+" role : "+rs.getString(13)
                                +" cin : "+rs.getString(14)+" nom_pepiniere : "+rs.getString(15)+" image : "+rs.getString(16)
                    
                                );
        }
    } catch (SQLException ex) {
        System.out.println("erreur dafichage du commande"+ ex);
    }
      } 
      
      public int ConectedUser() throws SQLException
      {int x=0;
             ResultSet rs = conn.createStatement().executeQuery("SELECT id_user FROM user where connected=1");
                        while (rs.next()) {x=rs.getInt(1);}           
                        return x;
                        
      
    
} 
       public void LoginUser(int i) 
      {
             
    try {
        PreparedStatement prt = conn.prepareStatement("Update user set connected=1 where id_user="+i);
        prt.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(DaoUsers.class.getName()).log(Level.SEVERE, null, ex);
    }
}  
      public void LogoutUser() 
      {
             
    try {
        PreparedStatement prt = conn.prepareStatement("Update user set connected=0 where connected=1");
        prt.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(DaoUsers.class.getName()).log(Level.SEVERE, null, ex);
    }
}
      
       public String gestUserAdr() throws SQLException
        {String x="";
             ResultSet rs = conn.createStatement().executeQuery("SELECT adresse FROM user where id=1");
                        while (rs.next()) {x=rs.getString(1);}           
                        return x;
        } 
       public List<User> affcl() throws SQLException
       {List<User> l=new ArrayList<>();
    
        ResultSet rs = conn.createStatement().executeQuery("SELECT nom,prenom,email,telephone,adresse FROM user where id=1");  
        while (rs.next()) {User e=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
        l.add(e);
        }
            return l;
       }
         
}