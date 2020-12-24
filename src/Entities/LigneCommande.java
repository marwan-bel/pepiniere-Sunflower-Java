/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author gazzah
 */

public class LigneCommande {
    int id_produit,id_cmd,quantiter,id_ligne_cmd;
	
    public LigneCommande() {
    }

    public LigneCommande(int id_ligne_cmd, int quantiter) {
        this.id_ligne_cmd = id_ligne_cmd;
        this.quantiter = quantiter;
    }

    public LigneCommande(int id_produit, int id_cmd, int quantiter) {
        this.id_produit = id_produit;
        this.id_cmd = id_cmd;
        this.quantiter = quantiter;
    }
 
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id_produit;
        hash = 29 * hash + this.id_cmd;
        hash = 29 * hash + this.quantiter;
        hash = 29 * hash + this.id_ligne_cmd;
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
        final LigneCommande other = (LigneCommande) obj;
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (this.id_cmd != other.id_cmd) {
            return false;
        }
        if (this.quantiter != other.quantiter) {
            return false;
        }
        if (this.id_ligne_cmd != other.id_ligne_cmd) {
            return false;
        }
        return true;
    }

    public int getId_ligne_cmd() {
        return id_ligne_cmd;
    }

    public void setId_ligne_cmd(int id_ligne_cmd) {
        this.id_ligne_cmd = id_ligne_cmd;
    }

   
    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_cmd() {
        return id_cmd;
    }

    public void setId_cmd(int id_cmd) {
        this.id_cmd = id_cmd;
    }

    public int getQuantiter() {
        return quantiter;
    }

    public void setQuantiter(int quantiter) {
        this.quantiter = quantiter;
    }
    
}

