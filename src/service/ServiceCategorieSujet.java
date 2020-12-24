
package service;
import Entities.CategorieSujet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Maconnexion;



public class ServiceCategorieSujet {
    
Connection C=  Maconnexion.getinstance().getConn();
   
   public void ajouterCategorieSujet(CategorieSujet Cs )
    {  
        try {
            Statement st = C.createStatement();
            String req = "insert into categorie_sujet (nom_categorie_sujet,type) values ("+"'"+Cs.getNom_categorie_sujet()+"','"+Cs.getType()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   public void afficherCategorieSujet()
    {
        try {
            //requete simple
            Statement st = C.createStatement();
            String req = "select * from categorie_sujet";
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                //System.out.println("nom_categorie_sujet : " + rs.getString(2)+" type : "+rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
   
    public void modifierCategorieSujet(CategorieSujet Cs, int i)
    {
        try {
            //requete pre compiler
            PreparedStatement pt = C.prepareStatement("update categorie_sujet set nom_categorie_sujet = ?,type = ? where id_categorie_sujet=?");
            pt.setString(1,Cs.getNom_categorie_sujet());//parametre fil requete
            pt.setString(2,Cs.getType());           
            pt.setInt(3,i);
            //System.out.println("/////////"+Com.getId_commentaire());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    
    public void supprimerCategorieSujet(int id)
    {
        try {
            PreparedStatement pt = C.prepareStatement("delete from categorie_sujet where id_categorie_sujet = ?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
