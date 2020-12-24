/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import Entities.*;
/**
 *
 * @author gazzah
 */
public interface IdaoLigneCommande {
     public void AjouterLigneCmd(int id_prod, int qte,int id_client,float montant);
    public void SupprimerLigneCmd(LigneCommande Lcmd);
    public void AfficherLigneCmd(LigneCommande Lcmd);
    public void ModifierLigneCmd(LigneCommande Lcmd);
}
