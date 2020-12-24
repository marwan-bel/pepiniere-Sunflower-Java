/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import Entities.*;
import java.util.List;
/**
 *
 * @author gazzah
 */
public interface IdaoCommande {
    public void AjouterCmd(Commande cmd);
    public void SupprimerCmd(int i);
    public void AfficherCmd(Commande cmd);
    public void ModifierCmd(Commande cmd);
      //public List<Commande> AfficherCmd();  
}
