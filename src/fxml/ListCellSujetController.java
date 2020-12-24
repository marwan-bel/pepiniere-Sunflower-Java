/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import controller.*;
import Entities.Sujet;
import javafx.scene.control.ListCell;

/**
 *
 * @author gazzah
 */
public class ListCellSujetController extends ListCell<Sujet>{
    @Override
    public void updateItem(Sujet c, boolean empty)
    {
        super.updateItem(c,empty);
        if(c != null)
        {
            ITemSujetController data = new ITemSujetController();
            data.setInfo(c);
            setGraphic(data.getBox());
        }
    } 
}