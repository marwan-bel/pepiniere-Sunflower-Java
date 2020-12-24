/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author shmhi
 */
public class Avis {
   private int id_avis ;
   private int  id_client;	
   private int id_service ;
   private String nom_avis;
   private String nom_client;
   private String nom_service;
   private String commentaire	;
   private float rating ;

    public int getId_avis() {
        return id_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public String getNom_avis() {
        return nom_avis;
    }

    public void setNom_avis(String nom_avis) {
        this.nom_avis = nom_avis;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getNom_service() {
        return nom_service;
    }

    public void setNom_service(String nom_service) {
        this.nom_service = nom_service;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Avis() {
    }

    public Avis( String  nom_service , String nom_avis, String nom_client, String commentaire, float rating) {
        this.nom_avis = nom_avis;
        this.nom_client = nom_client;
        this.nom_service = nom_service;
        this.commentaire = commentaire;
        this.rating = rating;
    }
    

    public Avis(int id_avis, int id_client, int id_service, String nom_avis, String nom_client, String nom_service, String commentaire, float rating) {
        this.id_avis = id_avis;
        this.id_client = id_client;
        this.id_service = id_service;
        this.nom_avis = nom_avis;
        this.nom_client = nom_client;
        this.nom_service = nom_service;
        this.commentaire = commentaire;
        this.rating = rating;
    }

    public Avis(int id_client, int id_service, String nom_avis, String nom_client, String nom_service, String commentaire) {
        this.id_client = id_client;
        this.id_service = id_service;
        this.nom_avis = nom_avis;
        this.nom_client = nom_client;
        this.nom_service = nom_service;
        this.commentaire = commentaire;
    }
    

    @Override
    public String toString() {
        return "Avis{" + "id_avis=" + id_avis + ", id_client=" + id_client + ", id_service=" + id_service + ", nom_avis=" + nom_avis + ", nom_client=" + nom_client + ", nom_service=" + nom_service + ", commentaire=" + commentaire + ", rating=" + rating + '}';
    }

                    
    
}
