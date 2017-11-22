/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.sql.Connection;
import javafx.collections.ObservableList;


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
    
    public void addImportationRecord(ImportationModal importation){
        importationDAL.addImportationRecord(importation);
        
    }
    
    public void deleteImportationRecord(Integer importationID){
        importationDAL.deleteImportationRecord(importationID);
    }
    
    public void updateImportationRecord(ImportationModal importation){
        importationDAL.updateImportationRecord(importation);
    }
}
