/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;


/**
 *
 * @author shmhi
 */
public class GestionServices {
    
  private int id_service ;
  private int id_agent ;
  private int Prix ;
  private String nom_service;
  private String nom_agent ;
  private String Region;
  private String Etat ;
  private String description ;
  private String type ;
   private String contact ;
   private String Image ;
   
   private Float global_rating;

    public Float getGlobal_rating() {
        return global_rating;
    }

    public void setGlobal_rating(Float global_rating) {
        this.global_rating = global_rating;
    }
   
   

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
  

  

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

  
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public int getId_agent() {
        return id_agent;
    }

    public void setId_agent(int id_agent) {
        this.id_agent = id_agent;
    }

    public String getNom_service() {
        return nom_service;
    }

    public void setNom_service(String nom_service) {
        this.nom_service= nom_service;
    }


    public String getNom_agent() {
        return nom_agent;
    }

    public void setNom_agent(String nom_agent) {
        this.nom_agent = nom_agent;
    }

 

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GestionServices() {
    }
  

  /*  public GestionServices(int id_service,  String nom_service, int Prix ,String Etat, String type,String Region, String description) {
        this.id_service = id_service;
        this.Prix = Prix;
        this.nom_service = nom_service;
        this.Region = Region;
        this.Etat = Etat;
        this.description = description;
        this.type = type;
    }
    */
    /*

    public GestionServices(int id_service, int id_agent,  String nom_service, String nom_agent, int Prix,String Region, String Etat, String description, String type) {
        this.id_service = id_service;
        this.id_agent = id_agent;
        this.Prix = Prix;
        this.nom_service = nom_service;
        this.nom_agent = nom_agent;
        this.Region = Region;
        this.Etat = Etat;
        this.description = description;
        this.type = type;
    }

    public GestionServices(int id_service,String nom_service, String type, int Prix,  String Region,  String description,String Etat) {
        this.id_service = id_service;
        this.Prix = Prix;
        this.nom_agent = nom_agent;
        this.Region = Region;
        this.Etat = Etat;
        this.description = description;
        this.type = type;
    }
    
 */

    public GestionServices(String nom_service, int Prix ,String Etat, String type,String Region, String description ,String contact ) {
        this.Prix = Prix;
        this.nom_service = nom_service;
        this.Region = Region;
        this.Etat = Etat;
        this.description = description;
        this.type = type;
        this.contact= contact ;
   
    }

    public GestionServices(int id_service,String nom_service, int Prix, String Etat, String type,  String Region,  String description, String contact) {
        this.id_service = id_service;
        this.Prix = Prix;
        this.nom_service = nom_service;
        this.Region = Region;
        this.Etat = Etat;
        this.description = description;
        this.type = type;
        this.contact = contact;
      
    }

    public GestionServices(int id_service,String nom_service, int Prix, String Etat, String type,  String Region,  String description, String contact ,String Image) {
        this.id_service = id_service;
        this.Prix = Prix;
        this.nom_service = nom_service;
        this.nom_agent = nom_agent;
        this.Region = Region;
        this.Etat = Etat;
        this.description = description;
        this.type = type;
        this.contact = contact;
        this.Image = Image;
    }
   
    
    

    
    @Override
    public int hashCode() {
        int hash = 5;
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
        final GestionServices other = (GestionServices) obj;
        if (this.id_service != other.id_service) {
            return false;
        }
        return Objects.equals(this.nom_service, other.nom_service);
    }

    @Override
    public String toString() {
        return "GestionServices{" + "id_service=" + id_service + ", id_agent=" + id_agent + ", Prix=" + Prix + ", nom_service=" + nom_service + ", nom_agent=" + nom_agent + ", Region=" + Region + ", Etat=" + Etat + ", description=" + description + ", type=" + type +
                ",image = '"+Image+"'}'";
    }
    
}

    

    
   
  
  
  
    
    
    

