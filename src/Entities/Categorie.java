package Entities;

public class Categorie {

private int Id_categ;
private String nom_categ;

    public Categorie(String nom_categ) {this.nom_categ = nom_categ;}

    public void setId_categ(int Id_categ) {this.Id_categ = Id_categ;}

    public void setNom_categ(String nom_categ) {this.nom_categ = nom_categ;}

    public int getId_categ() {return Id_categ;}

    public String getNom_categ() {return nom_categ;}

    public Categorie(int Id_categ, String nom_categ) {
        this.Id_categ = Id_categ;
        this.nom_categ = nom_categ;
    }

    public Categorie(int Id_categ) {
        this.Id_categ = Id_categ;
    }

 


}
