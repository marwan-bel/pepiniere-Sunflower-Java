package Entities;

public class Favoris {
    private int Id_fav;
    private int Id_prod;
    private Produit p;
    private int id_user;
    private String Nom_prod;
    private String dispo;
   
    public int      getId_fav(){return Id_fav;}

    public int      getId_prod() {return Id_prod;} 
    public String   getNom_prod()   {return Nom_prod;}
    public String   getDispo()      {return dispo;} 

    public int getId_user() {return id_user;}

    public Favoris(int Id_fav, int Id_prod, int id_user, String Nom_prod, String dispo) {
        this.Id_fav = Id_fav;
        this.Id_prod = Id_prod;
        this.id_user = id_user;
        this.Nom_prod = Nom_prod;
        this.dispo = dispo;
        
        
        
    }

    public Favoris(Produit p, int id_user) {
        this.p = p;
        this.id_user = id_user;
    }

 
    
 
 
 
}