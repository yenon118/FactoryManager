/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ycth8factorymanager;

import java.sql.Connection;
import java.util.HashMap;
import javafx.collections.ObservableList;

/**
 *
 * @author WIN
 */
public class ExportationBLL {
    
    private ExportationDAL exportationDAL;
    
    public ExportationBLL(Connection connection) {
        exportationDAL = new ExportationDAL(connection);
    }
    
    public ObservableList<ExportationModal> getExportationInventory(Integer registrationID){
        return exportationDAL.getExportationInventory(registrationID);
    }
    
    public void addExportationRecord(ExportationModal exportation){
        exportationDAL.addExportationRecord(exportation);
    }
    
    public void deleteExportationRecord(Integer exportationID){
        exportationDAL.deleteExportationRecord(exportationID);
    }
    
    public void updateExportationRecord(ExportationModal exportation){
        exportationDAL.updateExportationRecord(exportation);
    }
    
    
}
