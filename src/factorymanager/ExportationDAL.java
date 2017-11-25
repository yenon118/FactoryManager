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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author WIN
 */
public class ExportationDAL {
    
    private Connection connection;

    public ExportationDAL(Connection connection) {
        this.connection = connection;
    }
    
    public ObservableList<ExportationModal> getExportationInventory(Integer registrationID){
        ArrayList<ExportationModal> exportations = new ArrayList<>();
        try {
            String query = "SELECT * FROM Exportation WHERE RegistrationID = ?";
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            dbPreparedStatement.setInt(1, registrationID);
            ResultSet dbResultSet = dbPreparedStatement.executeQuery();
            
            while(dbResultSet.next()){
                ExportationModal exportation = new ExportationModal();
                
                exportation.setExportationID(dbResultSet.getInt(1));
                exportation.setRegistrationID(dbResultSet.getInt(2));
                exportation.setCompanyName(dbResultSet.getString(3));
                exportation.setPointOfContact(dbResultSet.getString(4));
                exportation.setCountryCode(dbResultSet.getInt(5));
                exportation.setPhoneNumber(dbResultSet.getLong(6));
                exportation.setEmail(dbResultSet.getString(7));
                exportation.setAddress(dbResultSet.getString(8));
                exportation.setCity(dbResultSet.getString(9));
                exportation.setState(dbResultSet.getString(10));
                exportation.setZipCode(dbResultSet.getInt(11));
                exportation.setCurrentDate(dbResultSet.getDate(12));
                exportation.setProduct(dbResultSet.getString(13));
                exportation.setPricePerUnit(dbResultSet.getDouble(14));
                exportation.setQuantity(dbResultSet.getInt(15));
                exportation.setTotalPrice(exportation.calculateTotal());
                
                exportations.add(exportation);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExportationDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(exportations, ExportationModal.sortExportationDate);
        ObservableList<ExportationModal> observableList = FXCollections.observableList(exportations);
        return observableList;
    }
    
    public void addExportationRecord(ExportationModal exportation){
        try {
            String query = "INSERT INTO Exportation (RegistrationID, CompanyName, PointOfContact, CountryCode, PhoneNumber, Email, Address, City, State, ZipCode, CurrentDate, Product, PricePerUnit, Quantity)"
                            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            
            dbPreparedStatement.setInt(1, exportation.getRegistrationID());
            dbPreparedStatement.setString(2, exportation.getCompanyName());
            dbPreparedStatement.setString(3, exportation.getPointOfContact());
            dbPreparedStatement.setInt(4, exportation.getCountryCode());
            dbPreparedStatement.setLong(5, exportation.getPhoneNumber());
            dbPreparedStatement.setString(6, exportation.getEmail());
            dbPreparedStatement.setString(7, exportation.getAddress());
            dbPreparedStatement.setString(8, exportation.getCity());
            dbPreparedStatement.setString(9, exportation.getState());
            dbPreparedStatement.setInt(10, exportation.getZipCode());
            dbPreparedStatement.setString(11, exportation.getCurrentDate().toString());
            dbPreparedStatement.setString(12, exportation.getProduct());
            dbPreparedStatement.setDouble(13, exportation.getPricePerUnit());
            dbPreparedStatement.setInt(14, exportation.getQuantity());

            dbPreparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ExportationDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteExportationRecord(Integer exportationID){
        try {
            String query = "DELETE FROM Exportation WHERE ExportationID = ?";
            
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            
            dbPreparedStatement.setInt(1, exportationID);

            dbPreparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ExportationDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateExportationRecord(ExportationModal exportation){
        try {
            String query = "UPDATE Exportation SET RegistrationID = ?, CompanyName = ?, PointOfContact = ?, CountryCode = ?, "
                            + "PhoneNumber = ?, Email = ?, Address = ?, City = ?, State = ?, ZipCode = ?, CurrentDate = ?, "
                            + "Product = ?, PricePerUnit = ?, Quantity = ? WHERE ExportationID = ?";
            
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            
            dbPreparedStatement.setInt(1, exportation.getRegistrationID());
            dbPreparedStatement.setString(2, exportation.getCompanyName());
            dbPreparedStatement.setString(3, exportation.getPointOfContact());
            dbPreparedStatement.setInt(4, exportation.getCountryCode());
            dbPreparedStatement.setLong(5, exportation.getPhoneNumber());
            dbPreparedStatement.setString(6, exportation.getEmail());
            dbPreparedStatement.setString(7, exportation.getAddress());
            dbPreparedStatement.setString(8, exportation.getCity());
            dbPreparedStatement.setString(9, exportation.getState());
            dbPreparedStatement.setInt(10, exportation.getZipCode());
            dbPreparedStatement.setString(11, exportation.getCurrentDate().toString());
            dbPreparedStatement.setString(12, exportation.getProduct());
            dbPreparedStatement.setDouble(13, exportation.getPricePerUnit());
            dbPreparedStatement.setInt(14, exportation.getQuantity());
            dbPreparedStatement.setInt(15, exportation.getExportationID());
                        
            dbPreparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ExportationDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
