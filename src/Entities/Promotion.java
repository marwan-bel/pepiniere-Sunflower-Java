/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author starmedia
 */
public class Promotion {
    private int id_promotion,pourcentage,id_produit,id_user;
    private User u;
    private Produit p;
    private String nom_promotion,date_debut,date_fin,description;
    private Float prix_hab,prix_promo;
    private String nom_produit,nom_agent;

    public void setNom_agent(String nom_agent) {
        this.nom_agent = nom_agent;
    }

    public String getNom_agent() {
        return nom_agent;
    }
    
        public Promotion(int id_produit, String nom_promotion,String nom_produit,Float prix_hab,Float prix_promo, int pourcentage, String date_debut, String date_fin, String description,String nom_agent) {
        this.nom_agent = nom_agent;
            this.pourcentage = pourcentage;
        this.nom_promotion = nom_promotion;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.prix_hab = prix_hab;
           this.prix_promo = prix_promo;
        this.nom_produit = nom_produit;
         this.id_produit = id_produit;
    }
    
    
    public Promotion(int id_produit, String nom_promotion,String nom_produit,Float prix_hab,Float prix_promo, int pourcentage, String date_debut, String date_fin, String description) {
        this.pourcentage = pourcentage;
        this.nom_promotion = nom_promotion;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.prix_hab = prix_hab;
           this.prix_promo = prix_promo;
        this.nom_produit = nom_produit;
         this.id_produit = id_produit;
    }
    public Promotion( String nom_promotion,String nom_produit,Float prix_hab,Float prix_promo, int pourcentage, String date_debut, String date_fin, String description) {
        this.pourcentage = pourcentage;
        this.nom_promotion = nom_promotion;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.prix_hab = prix_hab;
           this.prix_promo = prix_promo;
        this.nom_produit = nom_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_user() {
        return id_user;
    }
  public Promotion() {
      
    }
    public Promotion(int pourcentage, int id_user, int id_produit, String nom_promotion, String date_debut, String date_fin, String description, Float prix_hab, Float prix_promo) {
        this.pourcentage = pourcentage;
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.nom_promotion = nom_promotion;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.prix_hab = prix_hab;
        this.prix_promo = prix_promo;
    }

    public Promotion(String nom_promotion,Float prix_hab, Float prix_promo,int pourcentage,String date_debut, String date_fin, String description,int id_promotion ,String nom_produit) {
        this.id_promotion = id_promotion;
        this.pourcentage = pourcentage;
          this.nom_produit = nom_produit;
        this.nom_promotion = nom_promotion;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.prix_hab = prix_hab;
        this.prix_promo = prix_promo;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public Promotion( int pourcentage, String nom_promotion, String date_debut, String date_fin, String description, Float prix_hab, Float prix_promo) {
        this.pourcentage = pourcentage;
        this.nom_promotion = nom_promotion;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.prix_hab = prix_hab;
        this.prix_promo = prix_promo;
    }

    public Promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public User getU() {
        return u;
    }

    public Produit getP() {
        return p;
    }

    public String getNom_promotion() {
        return nom_promotion;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrix_hab() {
        return prix_hab;
    }

    public Float getPrix_promo() {
        return prix_promo;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public void setU(User u) {
        this.u = u;
    }

    public void setP(Produit p) {
        this.p = p;
    }

    public void setNom_promotion(String nom_promotion) {
        this.nom_promotion = nom_promotion;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix_hab(Float prix_hab) {
        this.prix_hab = prix_hab;
    }

    public void setPrix_promo(Float prix_promo) {
        this.prix_promo = prix_promo;
    }
   
       
    
}
