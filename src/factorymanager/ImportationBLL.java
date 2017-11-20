/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.sql.Connection;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author WIN
 */
public class ImportationBLL {
    
    private ImportationDAL importationDAL;
    
    public ImportationBLL(Connection connection) {
        importationDAL = new ImportationDAL(connection);
    }
    
    public ObservableList<ImportationModal> getImportationInventory(Integer registrationID){
        return importationDAL.getImportationInventory(registrationID);
    }
}
