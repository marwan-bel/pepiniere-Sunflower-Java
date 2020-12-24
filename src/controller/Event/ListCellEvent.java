/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Event;

import Entities.Evenement;
import controller.ItemCommentController;
import javafx.scene.control.ListCell;

/**
 *
 * @author gazzah
 */
public class ListCellEvent extends ListCell<Evenement>{
    @Override
    public void updateItem(Evenement c, boolean empty)
    {
        super.updateItem(c,empty);
        if(c != null)
        {
            ITemEventController data = new ITemEventController();
            data.setInfo(c);
            setGraphic(data.getBox());
        }
    }
    
}
