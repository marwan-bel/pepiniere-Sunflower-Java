
package Entities;

import java.sql.Date;
import java.sql.Timestamp;
import javafx.scene.control.Button;

public class Sujet {
    
    private int id_sujet;
    private int id_categorie_sujet;
    private int id_user;
    private String nom_sujet;
    private String nom_categorie_sujet;
    private String nom_agent;
    private Date  date_creation;
    private String description;
    private String type;
    private String image;
    private Button btnS;
    private Button btnM;

    public Sujet(int id_sujet, int id_categorie_sujet, int id_user, String nom_sujet, String nom_categorie_sujet, String nom_agent, Date date_creation, String description, String type, String image) {
        this.id_sujet = id_sujet;
        this.id_categorie_sujet = id_categorie_sujet;
        this.id_user = id_user;
        this.nom_sujet = nom_sujet;
        this.nom_categorie_sujet = nom_categorie_sujet;
        this.nom_agent = nom_agent;
        this.date_creation = date_creation;
        this.description = description;
        this.type = type;
        this.image = image;
    }

    
    public Sujet( String nom_sujet, String description,String nom_agent,Date date_creation, String image,int id_sujet) {
        this.id_sujet = id_sujet;
        this.nom_sujet = nom_sujet;
        this.nom_agent = nom_agent;
        this.date_creation = date_creation;
        this.description = description;
       
        this.image = image;
    }

            
        
    public Sujet(String nom_sujet,String nom_categorie_sujet,String description,String type,int id_user,String nom_agent,String image,int id_categorie_sujet ) {
        this.id_categorie_sujet = id_categorie_sujet;
        this.id_user = id_user;
        this.nom_sujet = nom_sujet;
        this.nom_categorie_sujet = nom_categorie_sujet;
        this.nom_agent = nom_agent;
        this.description = description;
        this.type = type;
    }
public Sujet(String nom_sujet, String description, String nom_agent,Date date_creation,String image) {
        this.nom_sujet = nom_sujet;
        this.nom_agent = nom_agent;
        this.description = description;
        this.date_creation = date_creation;
        this.image=image;
    }
public Sujet(String nom_sujet, String nom_categorie_sujet, String description, String type, String image) {
        this.nom_sujet = nom_sujet;
        this.nom_categorie_sujet = nom_categorie_sujet;
        this.description = description;
        this.type = type;
        this.image = image;
    }
    
    

    public Sujet(String nom_sujet, String nom_categorie_sujet, String description, String type) {
        this.nom_sujet = nom_sujet;
        this.nom_categorie_sujet = nom_categorie_sujet;
        this.description = description;
        this.type = type;
    }

    
    

    public Sujet(String nom_sujet, String nom_categorie_sujet, Date  date_creation, String description,String type) {
        this.nom_sujet = nom_sujet;
        this.nom_categorie_sujet = nom_categorie_sujet;
        this.date_creation = date_creation;
        this.description = description;
       this.type = type;
    }

    public Sujet(int id_sujet, String nom_sujet, String nom_categorie_sujet, Date  date_creation, String description, String type) {
        this.id_sujet = id_sujet;
        this.nom_sujet = nom_sujet;
        this.nom_categorie_sujet = nom_categorie_sujet;
        this.date_creation = date_creation;
        this.description = description;
        this.type = type;
    }

    public Sujet(String nom_sujet, String nom_categorie_sujet, Date  date_creation, String description, String type, Button btnS, Button btnM) 
    {
        this.nom_sujet = nom_sujet;
        this.nom_categorie_sujet = nom_categorie_sujet;
        this.date_creation = date_creation;
        this.description = description;
        this.type = type;
        this.btnS = new Button("Supprimer");
        this.btnM = new Button("Modifier");
        
    }
    

    public Sujet(int id_categorie_sujet, int id_user, String nom_sujet, String description) 
    {
        this.id_categorie_sujet = id_categorie_sujet;
        this.id_user = id_user;
        this.nom_sujet = nom_sujet;
        this.date_creation = date_creation;
        this.description = description;
    }

    public Sujet(String nom_sujet, String description) {
        this.nom_sujet = nom_sujet;
        this.description = description;
    }

    public Sujet() {
    }

   

    
    public int getId_sujet() {
        return id_sujet;
    }

    public int getId_categorie_sujet() {
        return id_categorie_sujet;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNom_sujet() {
        return nom_sujet;
    }

    public String getNom_categorie_sujet() {
        return nom_categorie_sujet;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Button getBtnS() {
        return btnS;
    }

    public Button getBtnM() {
        return btnM;
    }

    public void setId_sujet(int id_sujet) {
        this.id_sujet = id_sujet;
    }

    public void setId_categorie_sujet(int id_categorie_sujet) {
        this.id_categorie_sujet = id_categorie_sujet;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom_sujet(String nom_sujet) {
        this.nom_sujet = nom_sujet;
    }

    public void setNom_categorie_sujet(String nom_categorie_sujet) {
        this.nom_categorie_sujet = nom_categorie_sujet;
    }

    public void setDate_creation(Date  date_creation) {
        this.date_creation = date_creation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBtnS(Button btnS) {
        this.btnS = btnS;
    }

    public void setBtnM(Button btnM) {
        this.btnM = btnM;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNom_agent(String nom_agent) {
        this.nom_agent = nom_agent;
    }

    public String getNom_agent() {
        return nom_agent;
    }

    
}
