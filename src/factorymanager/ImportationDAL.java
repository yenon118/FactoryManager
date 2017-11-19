/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WIN
 */
public class ImportationDAL {
    
    private Connection connection;

    public ImportationDAL(Connection connection) {
        this.connection = connection;
    }
    
    public ArrayList<Importation> getImportationInventory(Integer registrationID){
        ArrayList<Importation> importations = new ArrayList<>();
        try {
            String query = "SELECT * FROM Importation WHERE RegistrationID = ?";
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            dbPreparedStatement.setInt(1, registrationID);
            ResultSet dbResultSet = dbPreparedStatement.executeQuery();
            
            while(dbResultSet.next()){
                Importation importation = new Importation();
                
                importation.setImportationID(dbResultSet.getInt(1));
                importation.setRegistrationID(dbResultSet.getInt(2));
                importation.setCompanyName(dbResultSet.getString(3));
                importation.setPointOfContact(dbResultSet.getString(4));
                importation.setCountryCode(dbResultSet.getInt(5));
                importation.setPhoneNumber(dbResultSet.getInt(6));
                importation.setEmail(dbResultSet.getString(7));
                importation.setAddress(dbResultSet.getString(8));
                importation.setCity(dbResultSet.getString(9));
                importation.setState(dbResultSet.getString(10));
                importation.setZipCode(dbResultSet.getInt(11));
                importation.setCurrentDate(dbResultSet.getDate(12));
                importation.setProduct(dbResultSet.getString(13));
                importation.setPricePerUnit(dbResultSet.getDouble(14));
                importation.setQuantity(dbResultSet.getInt(15));
                importation.calculateTotal();
                
                importations.add(importation);
            }
            
        }   catch (SQLException ex) {
            Logger.getLogger(ImportationDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return importations;
    }
    
    
}
