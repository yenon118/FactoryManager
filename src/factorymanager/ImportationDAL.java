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
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author WIN
 */
public class ImportationDAL {
    
    private Connection connection;

    public ImportationDAL(Connection connection) {
        this.connection = connection;
    }
    
    public ObservableList<ImportationModal> getImportationInventory(Integer registrationID){
        ArrayList<ImportationModal> importations = new ArrayList<>();
        try {
            String query = "SELECT * FROM Importation WHERE RegistrationID = ?";
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            dbPreparedStatement.setInt(1, registrationID);
            ResultSet dbResultSet = dbPreparedStatement.executeQuery();
            
            while(dbResultSet.next()){
                ImportationModal importation = new ImportationModal();
                
                importation.setImportationID(dbResultSet.getInt(1));
                importation.setRegistrationID(dbResultSet.getInt(2));
                importation.setCompanyName(dbResultSet.getString(3));
                importation.setPointOfContact(dbResultSet.getString(4));
                importation.setCountryCode(dbResultSet.getInt(5));
                importation.setPhoneNumber(dbResultSet.getLong(6));
                importation.setEmail(dbResultSet.getString(7));
                importation.setAddress(dbResultSet.getString(8));
                importation.setCity(dbResultSet.getString(9));
                importation.setState(dbResultSet.getString(10));
                importation.setZipCode(dbResultSet.getInt(11));
                importation.setCurrentDate(dbResultSet.getDate(12));
                importation.setProduct(dbResultSet.getString(13));
                importation.setPricePerUnit(dbResultSet.getDouble(14));
                importation.setQuantity(dbResultSet.getInt(15));
                importation.setTotalPrice(importation.calculateTotal());
                
                importations.add(importation);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ImportationDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(importations, ImportationModal.sortImportationDate);
        ObservableList<ImportationModal> observableList = FXCollections.observableList(importations);
        return observableList;
    }
    
    public void addImportationRecord(ImportationModal importation){
        try {
            String query = "INSERT INTO Importation (RegistrationID, CompanyName, PointOfContact, CountryCode, PhoneNumber, Email, Address, City, State, ZipCode, CurrentDate, Product, PricePerUnit, Quantity)"
                            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            
            dbPreparedStatement.setInt(1, importation.getRegistrationID());
            dbPreparedStatement.setString(2, importation.getCompanyName());
            dbPreparedStatement.setString(3, importation.getPointOfContact());
            dbPreparedStatement.setInt(4, importation.getCountryCode());
            dbPreparedStatement.setLong(5, importation.getPhoneNumber());
            dbPreparedStatement.setString(6, importation.getEmail());
            dbPreparedStatement.setString(7, importation.getAddress());
            dbPreparedStatement.setString(8, importation.getCity());
            dbPreparedStatement.setString(9, importation.getState());
            dbPreparedStatement.setInt(10, importation.getZipCode());
            dbPreparedStatement.setString(11, importation.getCurrentDate().toString());
            dbPreparedStatement.setString(12, importation.getProduct());
            dbPreparedStatement.setDouble(13, importation.getPricePerUnit());
            dbPreparedStatement.setInt(14, importation.getQuantity());

            dbPreparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ImportationDAL.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void deleteImportationRecord(Integer importationID){
        try {
            String query = "DELETE FROM Importation WHERE ImportationID = ?";
            
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            
            dbPreparedStatement.setInt(1, importationID);

            dbPreparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ImportationDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateImportationRecord(ImportationModal importation){
        try {
            String query = "UPDATE Importation SET RegistrationID = ?, CompanyName = ?, PointOfContact = ?, CountryCode = ?, "
                            + "PhoneNumber = ?, Email = ?, Address = ?, City = ?, State = ?, ZipCode = ?, CurrentDate = ?, "
                            + "Product = ?, PricePerUnit = ?, Quantity = ? WHERE ImportationID = ?";
            
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            
            dbPreparedStatement.setInt(1, importation.getRegistrationID());
            dbPreparedStatement.setString(2, importation.getCompanyName());
            dbPreparedStatement.setString(3, importation.getPointOfContact());
            dbPreparedStatement.setInt(4, importation.getCountryCode());
            dbPreparedStatement.setLong(5, importation.getPhoneNumber());
            dbPreparedStatement.setString(6, importation.getEmail());
            dbPreparedStatement.setString(7, importation.getAddress());
            dbPreparedStatement.setString(8, importation.getCity());
            dbPreparedStatement.setString(9, importation.getState());
            dbPreparedStatement.setInt(10, importation.getZipCode());
            dbPreparedStatement.setString(11, importation.getCurrentDate().toString());
            dbPreparedStatement.setString(12, importation.getProduct());
            dbPreparedStatement.setDouble(13, importation.getPricePerUnit());
            dbPreparedStatement.setInt(14, importation.getQuantity());
            dbPreparedStatement.setInt(15, importation.getImportationID());
                        
            dbPreparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ImportationDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
