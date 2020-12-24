package Entities;

/**
 *
 * @author gazzah
 */
public class User {	
String pseudo,password,nom,prenom,sexe,date_de_naissance,adresse,telephone,email,etat,role,cin,nom_pepiniere,image;
int id_client,nbr;
//nom,prenom,email,telephone,adresse

    public User( String nom_pepiniere,String nom, int nbr) {
        this.nom = nom;
        this.nom_pepiniere = nom_pepiniere;
        this.nbr = nbr;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }


    public User(String nom, String prenom, String adresse, String email, String role, String etat, int id_client, String cin, String nom_pepiniere) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.etat = etat;
        this.role = role;
        this.cin = cin;
        this.nom_pepiniere = nom_pepiniere;
        this.id_client = id_client;
    }
 
    public User( String nom, String prenom ,String email, String telephone,String adresse) {
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
    }

    public User(String pseudo, String nom, String prenom, String adresse, String email, String role, int id_client) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.role = role;
        this.id_client = id_client;
    }
    

    public User(String pseudo, String nom, String prenom, String adresse, String email, String role, String etat, int id_client) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.etat = etat;
        this.role = role;
        this.id_client = id_client;
    }

    public User(String pseudo, String nom, String prenom, String adresse, String email, String role, String etat , int id_client, String cin, String nom_pepiniere) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.etat = etat;
        this.role = role;
        this.cin = cin;
        this.nom_pepiniere = nom_pepiniere;
        this.id_client=id_client;
    }
    public User(int id_client) {
        this.id_client = id_client;
    }

    public User(String pseudo, String nom, String prenom, String sexe, String date_de_naissance, String adresse, String telephone, String email, String cin, String nom_pepiniere) {
        this.pseudo = pseudo;
       
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.date_de_naissance = date_de_naissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.cin = cin;
        this.nom_pepiniere = nom_pepiniere;
    }

    public User(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public User() {

    }

    public User(String pseudo, String password, String nom) {
        this.pseudo=pseudo;
        this.password=password;
        this.nom=nom;
        
    }

    

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public User(String pseudo, String password, String nom, String prenom, String sexe, String date_de_naissance, String adresse, String telephone, String email, String etat, String role, String cin, String nom_pepiniere, String image, int id_client) {
        this.pseudo = pseudo;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.date_de_naissance = date_de_naissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.etat = etat;
        this.role = role;
        this.cin = cin;
        this.nom_pepiniere = nom_pepiniere;
        this.image = image;
        this.id_client = id_client;
    }
    public User(String pseudo, String password, String nom, String prenom, String sexe, String date_de_naissance, String adresse, String telephone, String email, String etat, String role, String cin, String nom_pepiniere, String image) {
        this.pseudo = pseudo;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.date_de_naissance = date_de_naissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.etat = etat;
        this.role = role;
        this.cin = cin;
        this.nom_pepiniere = nom_pepiniere;
        this.image = image;
    }

   

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom_pepiniere() {
        return nom_pepiniere;
    }

    public void setNom_pepiniere(String nom_pepiniere) {
        this.nom_pepiniere = nom_pepiniere;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
