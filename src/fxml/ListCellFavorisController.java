/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Entities.Produit;
import javafx.scene.control.ListCell;

/**
 *
 * @author beerus
 */
public class ListCellFavorisController extends ListCell<Produit>{
    @Override
    public void updateItem(Produit c, boolean empty)
    {
        super.updateItem(c,empty);
        if(c != null)
        {
            ITemFavController data = new ITemFavController();
            data.setInfo(c);
            setGraphic(data.getBoxf());
        }
    }


} 