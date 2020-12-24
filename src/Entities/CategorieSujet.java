
package Entities;


public class CategorieSujet {
    
    private String nom_categorie_sujet;
    private String type;

    public CategorieSujet(String nom_categorie_sujet, String type) {
        this.nom_categorie_sujet = nom_categorie_sujet;
        this.type = type;
    }

    

    public String getNom_categorie_sujet() {
        return nom_categorie_sujet;
    }

    public String getType() {
        return type;
    }

   
    public void setNom_categorie_sujet(String nom_categorie_sujet) {
        this.nom_categorie_sujet = nom_categorie_sujet;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
    
}




