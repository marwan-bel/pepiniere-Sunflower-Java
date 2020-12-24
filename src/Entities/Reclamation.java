/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;
import javafx.scene.image.Image;

/**
 *
 * @author Marouan
 */
public class Reclamation {
    
    private int id_client,id_produit,id_reclamation;
    private String nom_reclamation,message,nom_client,pseudo,nom_pepiniere,email;
    private String etat,tel,reponse,tt;
    private Timestamp date_reclamation;
    private Image image;

    
    
    public Reclamation(int id_reclamation, String nom_client, String nom_reclamation, String nom_pepiniere,  String message ,String etat) {
        this.id_reclamation = id_reclamation;
        
        
        this.nom_client = nom_client;
        this.nom_reclamation = nom_reclamation;
        this.nom_pepiniere =  nom_pepiniere ;
        this.message = message;
        this.etat = etat;
    }

    public Reclamation() {
    }

    /*public Reclamation(int id_client, int id_produit, String nom_reclamation, String message, String etat) {
        this.id_client = id_client;
        this.id_produit = id_produit;
        this.nom_reclamation =new SimpleStringProperty(nom_reclamation);
        this.message =  new SimpleStringProperty(message);
        this.etat = etat;
    }*/
//id_reclamation,nom_reclamation,nom_produit,date_reclamation,message,etat
     public Reclamation(String nom_client ,String  email,String nom_reclamation, String nom_pepiniere,String tel ,String message,String reponse ) {
       
         this.nom_client = nom_client;
         this.email= email;
        this.nom_reclamation = nom_reclamation;
        this.nom_pepiniere = nom_pepiniere;
        this.tel = tel;
  
        this.message = message;
     }

    public Reclamation( String reponse,String etat) {
        
        this.reponse = reponse;
        this.etat = etat;
    }
    
    
    
    public Reclamation(int id_reclamation,String nom_client,String nom_reclamation,String nom_pepiniere,String tel,Timestamp date_reclamation ,String message,String reponse ,String etat) {
        this.id_reclamation = id_reclamation;
        this.nom_client = nom_client;
        this.nom_reclamation = nom_reclamation;
        
        this.nom_pepiniere =  nom_pepiniere;
        this.tel= tel;
        this.date_reclamation = date_reclamation;
       
        this.message = message;
        this.reponse= reponse;
        this.etat = etat;
       
        
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_produit() {
        return id_produit;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public String getTel() {
        return tel;
    }

    public String getNom_reclamation() {
        return nom_reclamation;
    }

    public String getMessage() {
        return message;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getNom_agent() {
        return nom_pepiniere;
    }

    public String getEtat() {
        return etat;
    }

    public Timestamp getDate_reclamation() {
        return date_reclamation;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setNom_reclamation(String nom_reclamation) {
        this.nom_reclamation = nom_reclamation;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public void setNom_agent(String nom_agent) {
        this.nom_pepiniere = nom_pepiniere;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDate_reclamation(Timestamp date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public String getNom_pepiniere() {
        return nom_pepiniere;
    }

    public void setNom_pepiniere(String nom_pepiniere) {
        this.nom_pepiniere = nom_pepiniere;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }
    
    
    

  
        
  

   
}