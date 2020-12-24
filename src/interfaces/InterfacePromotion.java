package interfaces;



import Entities.Promotion;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author starmedia
 */
public interface InterfacePromotion {
    public int get_id_user();
    public void ajouter(Promotion p,int id,float prix);
    public void afficher();
    public void modifier(int id,Promotion p);
           public void supprimer(int id);
            public void supprimer_auto();
             
            public List<Promotion> afficher_liste();
            public String afficher_p(int id);
              public List<Promotion> afficher_liste_pro(String rech);
                  public List<Promotion> afficher_liste_pou(String rech);
            
}
