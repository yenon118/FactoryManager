/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author WIN
 */
public class ImportationBLL {
    
    private ImportationDAL importationDAL;

    public ImportationBLL(Connection connection) {
        importationDAL = new ImportationDAL(connection);
    }
    
    public ArrayList<Importation> getImportationInventory(Integer registrationID){
        return importationDAL.getImportationInventory(registrationID);
    }
    
}
