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
public class DeliveryBLL {
    
    private DeliveryDAL deliveryDAL;

    public DeliveryBLL(Connection connection) {
        deliveryDAL = new DeliveryDAL(connection);
    }
    
    public ObservableList<DeliveryModal> getDeliveryInventory(Integer registrationID){
        return deliveryDAL.getDeliveryInventory(registrationID);
    }
    
    public void addDeliveryRecord(DeliveryModal delivery){
        deliveryDAL.addDeliveryRecord(delivery);
    }
    
    public void deleteDeliveryRecord(Integer deliveryID){
        deliveryDAL.deleteDeliveryRecord(deliveryID);
    }
    
    public void updateDeliveryRecord(DeliveryModal delivery){
        deliveryDAL.updateDeliveryRecord(delivery);
    }
}
