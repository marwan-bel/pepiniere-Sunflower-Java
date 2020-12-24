/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Agent.*;
import Entities.GestionServices;
import javafx.scene.control.ListCell;

/**
 * FXML Controller class
 *
 * @author shmhi
 */
public class ListCellagentController extends ListCell<GestionServices>{

@Override
    public void updateItem(GestionServices c, boolean empty)
    {
        super.updateItem(c,empty);
        if(c != null)
        {
            ItemService_1Controller data = new ItemService_1Controller();
            data.setInfo(c);
            setGraphic(data.getBox()); 
        }
    } 
}
