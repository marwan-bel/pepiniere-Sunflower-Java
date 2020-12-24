/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.TableColumn;

/**
 *
 * @author starmedia
 */
public class Evenement {
   private int id_evenement,id_user,max;
   private User u;
   private String nom,path,nom_evenement,pseudo_admin,type,lieu,date_debut,date_fin,description;

    public Evenement(String nom) {
        this.nom = nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id_evenement;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id_evenement != other.id_evenement) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_evenement=" + id_evenement + ", id_user=" + id_user + ", u=" + u + ", path=" + path + ", nom_evenement=" + nom_evenement + ", pseudo_admin=" + pseudo_admin + ", type=" + type + ", lieu=" + lieu + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", description=" + description + '}';
    }

    public Evenement(int id_evenement, int id_user, String nom_evenement,String pseudo_admin,  String type, String lieu, String date_debut, String date_fin, String description, String path,int max) {
        this.max = max;
        this.id_evenement = id_evenement;
        this.id_user = id_user;
        this.path = path;
        this.nom_evenement = nom_evenement;
        this.pseudo_admin = pseudo_admin;
        this.type = type;
        this.lieu = lieu;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public Evenement(int max,String path,int id_user, String nom_evenement, String type, String lieu, String date_debut, String date_fin, String description) {
        this.max = max;
        this.path = path;
        this.id_user = id_user;
        this.nom_evenement = nom_evenement;
        this.type = type;
        this.lieu = lieu;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }
 public Evenement( String nom_evenement, String pseudo_admin, String type, String lieu, String date_debut, String date_fin, String description,int id_evenement,int max) {
       this.max = max;
        this.id_evenement = id_evenement;
        this.nom_evenement = nom_evenement;
        this.pseudo_admin = pseudo_admin;
        this.type = type;
        this.lieu = lieu;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }

    public Evenement( String nom_evenement, String pseudo_admin, String type, String lieu, String date_debut, String date_fin, String description,int id_evenement) {
        this.id_evenement = id_evenement;
        this.nom_evenement = nom_evenement;
        this.pseudo_admin = pseudo_admin;
        this.type = type;
        this.lieu = lieu;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }

    public Evenement(String nom_evenement,String pseudo_admin,  String type, String lieu, String date_debut, String date_fin, String description) {
        this.pseudo_admin = pseudo_admin;
        this.nom_evenement = nom_evenement;
        this.type = type;
        this.lieu = lieu;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }

    public Evenement(User u, String nom_evenement, String type, String lieu, String date_debut, String date_fin, String description) {
        this.u = u;
        this.nom_evenement = nom_evenement;
        this.type = type;
        this.lieu = lieu;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }

    public Evenement( String nom_evenement, String type, String lieu, String date_debut, String date_fin, String description) {
        
        this.nom_evenement = nom_evenement;
        this.type = type;
        this.lieu = lieu;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }

    public Evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }


    public Evenement() {
    
    }

    
    public int getId_evenement() {
        return id_evenement;
    }

    public User getU() {
        return u;
    }

    public String getPseudo_admin() {
        return pseudo_admin;
    }
    public String getNom_evenement() {
        return nom_evenement;
    }

    public String getType() {
        return type;
    }

    public String getLieu() {
        return lieu;
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

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setU(User u) {
        this.u = u;
    }

        public void setPseudo_admin(String pseudo_admin) {
        this.pseudo_admin = pseudo_admin;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
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

    
   
    
}
