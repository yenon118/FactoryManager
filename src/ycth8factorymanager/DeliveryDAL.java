/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ycth8factorymanager;

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
public class DeliveryDAL {
    
    private Connection connection;

    public DeliveryDAL(Connection connection) {
        this.connection = connection;
    }
    
    public ObservableList<DeliveryModal> getDeliveryInventory(Integer registrationID){
        ArrayList<DeliveryModal> deliveries = new ArrayList<>();
        try {
            String query = "SELECT * FROM Delivery WHERE RegistrationID = ?";
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            dbPreparedStatement.setInt(1, registrationID);
            ResultSet dbResultSet = dbPreparedStatement.executeQuery();
            
            while(dbResultSet.next()){
                DeliveryModal delivery = new DeliveryModal();
                
                delivery.setDeliveryID(dbResultSet.getInt(1));
                delivery.setRegistrationID(dbResultSet.getInt(2));
                delivery.setExportationID(dbResultSet.getInt(3));
                delivery.setCompanyName(dbResultSet.getString(4));
                delivery.setPointOfContact(dbResultSet.getString(5));
                delivery.setCountryCode(dbResultSet.getInt(6));
                delivery.setPhoneNumber(dbResultSet.getLong(7));
                delivery.setEmail(dbResultSet.getString(8));
                delivery.setAddress(dbResultSet.getString(9));
                delivery.setCity(dbResultSet.getString(10));
                delivery.setState(dbResultSet.getString(11));
                delivery.setZipCode(dbResultSet.getInt(12));
                delivery.setCurrentDate(dbResultSet.getDate(13));
                delivery.setContainerPricePerUnit(dbResultSet.getDouble(14));
                delivery.setContainerQuantity(dbResultSet.getInt(15));
                delivery.setShipmentPrice(dbResultSet.getDouble(16));
                delivery.setTotalPrice(delivery.calculateTotal());
                
                deliveries.add(delivery);
            }
            
        } catch (SQLException ex) {   
            Logger.getLogger(DeliveryDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(deliveries, DeliveryModal.sortDeliveryDate);
        ObservableList<DeliveryModal> observableList = FXCollections.observableList(deliveries);
        return observableList;
    }
    
    public void addDeliveryRecord(DeliveryModal delivery){
        try {
            String query = "INSERT INTO Delivery (RegistrationID, ExportationID, CompanyName, PointOfContact, CountryCode, PhoneNumber, Email, Address, City, State, ZipCode, CurrentDate, ContainerPricePerUnit, ContainerQuantity, ShipmentPrice)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            
            dbPreparedStatement.setInt(1, delivery.getRegistrationID());
            dbPreparedStatement.setInt(2, delivery.getExportationID());
            dbPreparedStatement.setString(3, delivery.getCompanyName());
            dbPreparedStatement.setString(4, delivery.getPointOfContact());
            dbPreparedStatement.setInt(5, delivery.getCountryCode());
            dbPreparedStatement.setLong(6, delivery.getPhoneNumber());
            dbPreparedStatement.setString(7, delivery.getEmail());
            dbPreparedStatement.setString(8, delivery.getAddress());
            dbPreparedStatement.setString(9, delivery.getCity());
            dbPreparedStatement.setString(10, delivery.getState());
            dbPreparedStatement.setInt(11, delivery.getZipCode());
            dbPreparedStatement.setString(12, delivery.getCurrentDate().toString());
            dbPreparedStatement.setDouble(13, delivery.getContainerPricePerUnit());
            dbPreparedStatement.setInt(14, delivery.getContainerQuantity());
            dbPreparedStatement.setDouble(15, delivery.getShipmentPrice());

            dbPreparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void deleteDeliveryRecord(Integer deliveryID){
        try {
            String query = "DELETE FROM Delivery WHERE DeliveryID = ?";
            
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            dbPreparedStatement.setInt(1, deliveryID);
            dbPreparedStatement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDAL.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void updateDeliveryRecord(DeliveryModal delivery){
        try {
            String query = "UPDATE Delivery SET RegistrationID = ?, ExportationID = ?, CompanyName = ?, PointOfContact = ?, CountryCode = ?, "
                            + "PhoneNumber = ?, Email = ?, Address = ?, City = ?, State = ?, ZipCode = ?, CurrentDate = ?, "
                            + "ContainerPricePerUnit = ?, ContainerQuantity = ?, ShipmentPrice = ? WHERE DeliveryID = ?";
            
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            
            dbPreparedStatement.setInt(1, delivery.getRegistrationID());
            dbPreparedStatement.setInt(2, delivery.getExportationID());
            dbPreparedStatement.setString(3, delivery.getCompanyName());
            dbPreparedStatement.setString(4, delivery.getPointOfContact());
            dbPreparedStatement.setInt(5, delivery.getCountryCode());
            dbPreparedStatement.setLong(6, delivery.getPhoneNumber());
            dbPreparedStatement.setString(7, delivery.getEmail());
            dbPreparedStatement.setString(8, delivery.getAddress());
            dbPreparedStatement.setString(9, delivery.getCity());
            dbPreparedStatement.setString(10, delivery.getState());
            dbPreparedStatement.setInt(11, delivery.getZipCode());
            dbPreparedStatement.setString(12, delivery.getCurrentDate().toString());
            dbPreparedStatement.setDouble(13, delivery.getContainerPricePerUnit());
            dbPreparedStatement.setInt(14, delivery.getContainerQuantity());
            dbPreparedStatement.setDouble(15, delivery.getShipmentPrice());
            dbPreparedStatement.setInt(16, delivery.getDeliveryID());
                        
            dbPreparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDAL.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    
}
