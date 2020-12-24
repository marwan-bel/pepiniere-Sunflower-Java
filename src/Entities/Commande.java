/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;
import java.util.Objects;
import javafx.scene.control.Button;

/**
 *
 * @author gazzah
 */
public class Commande {
  
    int id_cmd,id_client;
    String etat_cmd,mode_p,lieu_liv;
float montant;
Timestamp date_cmd;

    public Commande() {
    }
 //id_client,etat_cmd,mode_p,lieu_liv,montant

    public Commande(int id_client, String etat_cmd, String mode_p, String lieu_liv) {
        this.id_client = id_client;
        this.etat_cmd = etat_cmd;
        this.mode_p = mode_p;
        this.lieu_liv = lieu_liv;
    }
    public Commande(int id_client, String etat_cmd, String mode_p, String lieu_liv,float montant) {
        this.id_client = id_client;
        this.etat_cmd = etat_cmd;
        this.mode_p = mode_p;
        this.lieu_liv = lieu_liv;
        this.montant = montant;
    }
    public Commande(String etat_cmd, String mode_p, String lieu_liv,float montant) {
        this.etat_cmd = etat_cmd;
        this.mode_p = mode_p;
        this.lieu_liv = lieu_liv;
        this.montant = montant;
    }
    
  public Commande( String etat_cmd, String mode_p, String lieu_liv, float montant,Timestamp date_cmd) {
        
        this.etat_cmd = etat_cmd;
        this.mode_p = mode_p;
        this.lieu_liv = lieu_liv;
        this.montant = montant;
        this.date_cmd = date_cmd;
      
    }


    public Commande(int id_cmd, int id_client, String etat_cmd, String mode_p, String lieu_liv, float montant, Timestamp date_cmd) {
        this.id_cmd = id_cmd;
        this.id_client = id_client;
        this.etat_cmd = etat_cmd;
        this.mode_p = mode_p;
        this.lieu_liv = lieu_liv;
        this.montant = montant;
        this.date_cmd = date_cmd;
    }
  


    public Commande(int id_cmd) {
        this.id_cmd = id_cmd;
    }

   
    
      
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id_cmd;
        hash = 29 * hash + this.id_client;
        hash = 29 * hash + Objects.hashCode(this.etat_cmd);
        hash = 29 * hash + Objects.hashCode(this.mode_p);
        hash = 29 * hash + Objects.hashCode(this.lieu_liv);
        hash = 29 * hash + Float.floatToIntBits(this.montant);
        hash = 29 * hash + Objects.hashCode(this.date_cmd);
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
        final Commande other = (Commande) obj;
        if (this.id_cmd != other.id_cmd) {
            return false;
        }
        if (this.id_client != other.id_client) {
            return false;
        }
        if (Float.floatToIntBits(this.montant) != Float.floatToIntBits(other.montant)) {
            return false;
        }
        if (!Objects.equals(this.etat_cmd, other.etat_cmd)) {
            return false;
        }
        if (!Objects.equals(this.mode_p, other.mode_p)) {
            return false;
        }
        if (!Objects.equals(this.lieu_liv, other.lieu_liv)) {
            return false;
        }
        if (!Objects.equals(this.date_cmd, other.date_cmd)) {
            return false;
        }
        return true;
    }

    public int getId_cmd() {
        return id_cmd;
    }

    public void setId_cmd(int id_cmd) {
        this.id_cmd = id_cmd;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getEtat_cmd() {
        return etat_cmd;
    }

    public void setEtat_cmd(String etat_cmd) {
        this.etat_cmd = etat_cmd;
    }

    public String getMode_p() {
        return mode_p;
    }

    public void setMode_p(String mode_p) {
        this.mode_p = mode_p;
    }

    public String getLieu_liv() {
        return lieu_liv;
    }

    public void setLieu_liv(String lieu_liv) {
        this.lieu_liv = lieu_liv;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Timestamp getDate_cmd() {
        return date_cmd;
    }

    public void setDate_cmd(Timestamp date_cmd) {
        this.date_cmd = date_cmd;
    }

}
