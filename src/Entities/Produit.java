package Entities; 
 
//nom_categorie,type,prix,quantite,description,id_produit,nom_produit

public class Produit {
    private int Id_prod; 
    int id_user;
    int id_cat; 
    private String Nom_prod; 
    private String Type_prod;
    private float Prix_prod;
    private int Qte_prod; 
    private String img;    
    private String Desc_prod;
    private String Nom_agent;
    private String Nom_categorie;

    public Produit(String Nom_categorie,String Type_prod, float Prix_prod, int Qte_prod,String Desc_prod ,int Id_prod,String Nom_prod  ) {
        this.Id_prod = Id_prod;
        this.Nom_prod = Nom_prod;
        this.Type_prod = Type_prod;
        this.Prix_prod = Prix_prod;
        this.Qte_prod = Qte_prod;
        this.Desc_prod = Desc_prod;
        this.Nom_categorie = Nom_categorie;
    }
    
    public Produit() {}
    public String getNom_agent() {return Nom_agent;}
    public String getNom_categorie() {return Nom_categorie;}
    public int getId_user() {return id_user;}
    
    public Produit(int id_ag,  String Nom_prod,  float Prix_prod, int Qte_prod, String img, String Desc_prod, String Nom_categorie) {
        this.id_user=id_ag;
        this.Nom_prod = Nom_prod;   
        this.Prix_prod = Prix_prod;
        this.Qte_prod = Qte_prod;
        this.img = img;
        this.Desc_prod = Desc_prod;
        this.Nom_categorie = Nom_categorie;
    }

    public void setId_prod(int Id_prod) {this.Id_prod = Id_prod;}

    public void setId_user(int id_user) {this.id_user = id_user;}

    public void setId_cat(int id_cat) {this.id_cat = id_cat;}

    

    public void setNom_prod(String Nom_prod) {this.Nom_prod = Nom_prod;}

    public void setType_prod(String Type_prod) {this.Type_prod = Type_prod;}

    public void setPrix_prod(float Prix_prod) {this.Prix_prod = Prix_prod;}

    public void setQte_prod(int Qte_prod) {this.Qte_prod = Qte_prod;}

    public void setImg(String img) {this.img = img;}

    public void setDesc_prod(String Desc_prod) {this.Desc_prod = Desc_prod;}

    public void setNom_agent(String Nom_agent) {this.Nom_agent = Nom_agent;}

    public void setNom_categorie(String Nom_categorie) {this.Nom_categorie = Nom_categorie;}

    public Produit(int Id_prod, int id_us, int id_cat, String Nom_prod,String Nom_agent,String Nom_categorie, String Type_prod, float Prix_prod, int Qte_prod, String img, String Desc_prod) {
        this.Id_prod = Id_prod;
        this.id_user = id_us;
        this.id_cat = id_cat;
        this.Nom_prod = Nom_prod;
        this.Nom_agent = Nom_agent;
        this.Nom_categorie = Nom_categorie;
        this.Type_prod = Type_prod;
        this.Prix_prod = Prix_prod;
        this.Qte_prod = Qte_prod;
        this.img = img;
        this.Desc_prod = Desc_prod;
    }
    
    
    public Produit(int Id_prod) {
        this.Id_prod = Id_prod;
    }

 

  
 
    public int getId_prod() {return Id_prod;}
    public int getIdUs() {return id_user;}
    
    
    public int getId_cat() {
        return id_cat;
    }
    
    
    
    public String getNom_prod() {return Nom_prod;}
    public String getType_prod() {return Type_prod;}
    public float  getPrix_prod() {return Prix_prod;}
    public int getQte_prod() {return Qte_prod;}
    public String getDesc_prod() {return Desc_prod;} 
    public String getImg() {return img;}

    @Override
    public String toString() {
        return "Produit{" + "Id_prod=" + Id_prod + ", id_user=" + id_user + ", id_cat=" + id_cat + ", Nom_prod=" + Nom_prod + ", Type_prod=" + Type_prod + ", Prix_prod=" + Prix_prod + ", Qte_prod=" + Qte_prod + ", img=" + img + ", Desc_prod=" + Desc_prod + ", Nom_agent=" + Nom_agent + ", Nom_categorie=" + Nom_categorie + '}';
    }

 
 
    
}
