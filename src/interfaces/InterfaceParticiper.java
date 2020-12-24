/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Entities.Participer;

/**
 *
 * @author starmedia
 */
public interface InterfaceParticiper {
    
    
     public void ajouter(Participer p);
      public void supprimer(int id);
       public int rech(int id_eve,int id_user);
    
}
