/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Entities.Evenement;
import java.util.List;

/**
 *
 * @author starmedia
 */
public interface InterfaceEvenement {
       public int get_id_user();
    public void ajouter(Evenement e);
     public void afficher();
      public void modifier(int id,Evenement e);
      public void supprimer(int id);
         public List<Evenement> afficher_liste();
         
}
