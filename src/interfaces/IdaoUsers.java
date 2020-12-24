/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import Entities.*;
/**
 *
 * @author gazzah
 */
public interface IdaoUsers {
    //********** INSCRIPTION ***********\\
      public void insertUser(User user);
    
    //********** SUPPRESSION ***********\\
    public void deleteUser(User user);
  
    
    //********** AFFICHAGE **************\\
     public void afficheUser(User user);
    
    
         //********** MODIFIER ***********\\
    public void modifUser(User user);
   
}
