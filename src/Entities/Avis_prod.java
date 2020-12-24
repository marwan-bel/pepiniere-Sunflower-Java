/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Marouan
 */
public class Avis_prod {
    private int id_client,id_produit;
    
    private String commentaire,nom_client,nom_produit;
    private double rating;

    public Avis_prod() {
    }

    public Avis_prod(int id_produit, String commentaire, double rating) { 
        this.id_produit = id_produit;
        this.commentaire = commentaire; 
        this.rating = rating;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public Avis_prod(int id_client, int id_produit, String date, String commentaire, double rating) {
        this.id_client = id_client;
        this.id_produit = id_produit;
        
        this.commentaire = commentaire;
        this.rating = rating;
    }

    public Avis_prod(int id_client, int id_produit, String commentaire, double rating) {
        this.id_client = id_client;
        this.id_produit = id_produit;
        this.commentaire = commentaire;
        this.rating = rating;
    }

 
    

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }



    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_produit() {
        return id_produit;
    }


    public String getCommentaire() {
        return commentaire;
    }

    public double getRating() {
        return rating;
    }

    
    
}
