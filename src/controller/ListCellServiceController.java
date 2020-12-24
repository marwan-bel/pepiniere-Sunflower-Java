/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.GestionServices;
import javafx.scene.control.ListCell;

/**
 * FXML Controller class
 *
 * @author shmhi
 */
public class ListCellServiceController extends ListCell<GestionServices>{

@Override
    public void updateItem(GestionServices c, boolean empty)
    {
        super.updateItem(c,empty);
        if(c != null)
        {
            ITemServiceController data = new ITemServiceController();
            data.setInfo(c);
            setGraphic(data.getBox());
        }
    } 
}
