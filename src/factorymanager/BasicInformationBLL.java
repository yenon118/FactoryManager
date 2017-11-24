/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author WIN
 */
public class BasicInformationBLL {
    
    private static Connection connection;

    public BasicInformationBLL(Connection connection) {
        this.connection = connection;
    }
    
    public BasicInformationModal processGraphDataByMonth(Integer year){
        
        ImportationBLL importationBLL = new ImportationBLL(connection);
        DeliveryBLL deliveryBLL = new DeliveryBLL(connection);
        ExportationBLL exportationBLL = new ExportationBLL(connection);
        
        
        ObservableList<ImportationModal> importations = importationBLL.getImportationInventory(User.getRegistrationID());
        ObservableList<DeliveryModal> deliveries = deliveryBLL.getDeliveryInventory(User.getRegistrationID());
        ObservableList<ExportationModal> exportations = exportationBLL.getExportationInventory(User.getRegistrationID());
        
        Calendar calendar = Calendar.getInstance();
        
//        int currentYear = calendar.get(Calendar.YEAR);
        
        BasicInformationModal basic = new BasicInformationModal();
        double[] earning = new double[12];
        double[] spending = new double[12];
        
        for(int i=0; i<importations.size(); i++){
            calendar.setTime(importations.get(i).getCurrentDate());
            if(calendar.get(Calendar.YEAR) != year){
                importations.remove(i);
                i--;
            }
        }
        for(int i=0; i<importations.size(); i++){
            calendar.setTime(importations.get(i).getCurrentDate());
            earning[calendar.get(Calendar.MONTH)] = earning[calendar.get(Calendar.MONTH)] + importations.get(i).getTotalPrice();
        }
        
        for(int i=0; i<deliveries.size(); i++){
            calendar.setTime(deliveries.get(i).getCurrentDate());
            if(calendar.get(Calendar.YEAR) != year){
                deliveries.remove(i);
                i--;
            }
        }
        for(int i=0; i<deliveries.size(); i++){
            calendar.setTime(deliveries.get(i).getCurrentDate());
            spending[calendar.get(Calendar.MONTH)] = spending[calendar.get(Calendar.MONTH)] + deliveries.get(i).getTotalPrice();
        }
        
        for(int i=0; i<exportations.size(); i++){
            calendar.setTime(exportations.get(i).getCurrentDate());
            if(calendar.get(Calendar.YEAR) != year){
                exportations.remove(i);
                i--;
            }
        }
        for(int i=0; i<exportations.size(); i++){
            calendar.setTime(exportations.get(i).getCurrentDate());
            spending[calendar.get(Calendar.MONTH)] = spending[calendar.get(Calendar.MONTH)] + exportations.get(i).getTotalPrice();
        }
        
        basic.setEarning(earning);
        basic.setSpending(spending);
        
        return basic;
        
    }
    
    
}
