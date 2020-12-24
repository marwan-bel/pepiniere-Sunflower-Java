/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Produit;
import fxml.ITemLignecmdController;
import javafx.scene.control.ListCell;

/**
 *
 * @author gazzah
 */
public class ListCellLign_cmd extends ListCell<Produit>{
    @Override
    public void updateItem(Produit c, boolean empty)
    {
        super.updateItem(c,empty);
        if(c != null)
        {
            ITemLignecmdController data = new ITemLignecmdController();
            data.setInfo(c);
            setGraphic(data.getBox());
        }
    } 
    
}
