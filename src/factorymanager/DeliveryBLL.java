/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Objects;
import javafx.collections.ObservableList;

/**
 *
 * @author WIN
 */
public class DeliveryBLL {
    
    private DeliveryDAL deliveryDAL;
    private static Connection connection;

    public DeliveryBLL(Connection connection) {
        deliveryDAL = new DeliveryDAL(connection);
        this.connection = connection;
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
    
    public HashMap<Integer, String> loadDeliveryInventory(Integer registrationID){
        
        ExportationBLL exportationBLL = new ExportationBLL(connection);
        ObservableList<ExportationModal> exportationList = exportationBLL.getExportationInventory(registrationID);
        
        ObservableList<DeliveryModal> deliveryList = deliveryDAL.getDeliveryInventory(registrationID);
        HashMap<Integer, String> hashMap = new HashMap<>(); 
        
        for(int i=0; i<deliveryList.size(); i++){
            for(int j=0; j<exportationList.size(); j++){
                if(deliveryList.get(i).getExportationID().equals(exportationList.get(j).getExportationID())){
                    exportationList.remove(j);
                    j--;
                }
            }
        }
        
        for(int i = 0; i<exportationList.size(); i++){
            hashMap.put(exportationList.get(i).getExportationID(), exportationList.get(i).getCompanyName() + "   |   " + exportationList.get(i).getCurrentDate() + "   |   " + exportationList.get(i).getProduct() + "   |   " + exportationList.get(i).getTotalPrice());
        }
        
        return hashMap;
    }
}
