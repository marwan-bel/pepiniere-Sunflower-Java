/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Commentaire;
import javafx.scene.control.ListCell;
/**
 *
 * @author gazzah
 */
public class ListCellCommentController extends ListCell<Commentaire>{
    @Override
    public void updateItem(Commentaire c, boolean empty)
    {
        super.updateItem(c,empty);
        if(c != null)
        {
            ItemCommentController data = new ItemCommentController();
            data.setInfo(c);
            setGraphic(data.getBox());
        }
    } 
}
