
package Entities;

import java.sql.Date;
import java.sql.Statement;



public class Commentaire {
    
    
    private int id_commentaire;
    private int id_user;  
    private String nom_client;
    private int id_sujet;
    private String nom_sujet;
    private String commentaire;
    private Date date_commentaire; 
    private int nbr_signal;
    private int nbr_like;

    public int getNbr_like() {
        return nbr_like;
    }
//id_commentaire,id_client,id_sujet,nom_client,nom_sujet,commentaires,date_commentaire,nbre_signal,likee
   
    public void setNbr_like(int nbr_like) {
        this.nbr_like = nbr_like;
    }

    public Commentaire(int id_commentaire, int id_user, int id_sujet,String nom_client, String nom_sujet, String commentaire, Date date_commentaire, int nbr_signal, int nbr_like) {
        this.id_commentaire = id_commentaire;
        this.id_user = id_user;
        this.nom_client = nom_client;
        this.id_sujet = id_sujet;
        this.nom_sujet = nom_sujet;
        this.commentaire = commentaire;
        this.date_commentaire = date_commentaire;
        this.nbr_signal = nbr_signal;
        this.nbr_like = nbr_like;
    }

            
    public Commentaire( String nom_sujet,int id_user, String nom_client, int id_sujet, String commentaire) {
        this.id_user = id_user;
        this.nom_client = nom_client;
        this.id_sujet = id_sujet;
        this.nom_sujet = nom_sujet;
        this.commentaire = commentaire;
    }
public Commentaire(int id_commentaire, int id_user, String nom_client, String commentaire, int nbr_signal) {
        this.id_commentaire = id_commentaire;
        this.id_user = id_user;
        this.nom_client = nom_client;
        this.commentaire = commentaire;
        this.nbr_signal = nbr_signal;
    }
    
    
    

    public Commentaire(int id_user, int id_sujet, String nom_sujet, String commentaire) {
        this.id_user = id_user;
        this.id_sujet = id_sujet;
        this.nom_sujet = nom_sujet;
        this.commentaire = commentaire;
       
    }
    
            

    public Commentaire() {
    }

    public Commentaire(int id_commentaire, int id_user, int id_sujet ,String nom_client, String nom_sujet, String commentaire, Date date_commentaire, int nbr_signal) {
        this.id_commentaire = id_commentaire;
        this.id_user = id_user;
        this.nom_client = nom_client;
        this.id_sujet = id_sujet;
        this.nom_sujet = nom_sujet;
        this.commentaire = commentaire;
        this.date_commentaire = date_commentaire;
        this.nbr_signal = nbr_signal;
    }

    public Commentaire(int id_user, int id_sujet, String commentaire, int nbr_signal) {
        this.id_user = id_user;
        this.id_sujet = id_sujet;
        this.commentaire = commentaire;
        this.nbr_signal = nbr_signal;
    }

    
    
    

    public Commentaire(int id_user, String nom_client, int id_sujet, String nom_sujet, String commentaire, Date date_commentaire, int nbr_signal) {
        this.id_user = id_user;
        this.nom_client = nom_client;
        this.id_sujet = id_sujet;
        this.nom_sujet = nom_sujet;
        this.commentaire = commentaire;
        this.date_commentaire = date_commentaire;
        this.nbr_signal = nbr_signal;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

  

    public int getId_user() {
        return id_user;
    }

    public String getNom_client() {
        return nom_client;
    }

    public int getId_sujet() {
        return id_sujet;
    }

    public String getNom_sujet() {
        return nom_sujet;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Date getDate_commentaire() {
        return date_commentaire;
    }

    public int getNbr_signal() {
        return nbr_signal;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public void setId_sujet(int id_sujet) {
        this.id_sujet = id_sujet;
    }

    public void setNom_sujet(String nom_sujet) {
        this.nom_sujet = nom_sujet;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setDate_commentaire(Date date_commentaire) {
        this.date_commentaire = date_commentaire;
    }

    public void setNbr_signal(int nbr_signal) {
        this.nbr_signal = nbr_signal;
    }

}