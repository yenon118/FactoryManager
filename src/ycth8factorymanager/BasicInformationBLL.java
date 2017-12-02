/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ycth8factorymanager;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        BasicInformationModal basic = new BasicInformationModal();
        double[] earning = new double[12];
        double[] spending = new double[12];

        Thread importationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ImportationBLL importationBLL = new ImportationBLL(connection);
                ObservableList<ImportationModal> importations = importationBLL.getImportationInventory(User.getRegistrationID());
                
                Calendar importationCalendar = Calendar.getInstance();
                
                for(int i=0; i<importations.size(); i++){
                    importationCalendar.setTime(importations.get(i).getCurrentDate());
                    if(importationCalendar.get(Calendar.YEAR) != year){
                        importations.remove(i);
                        i--;
                    }
                }
                for(int i=0; i<importations.size(); i++){
                    importationCalendar.setTime(importations.get(i).getCurrentDate());
                    spending[importationCalendar.get(Calendar.MONTH)] = spending[importationCalendar.get(Calendar.MONTH)] + importations.get(i).getTotalPrice();
                }
            }
        });


        Thread deliveryThread = new Thread(new Runnable() {
            @Override
            public void run() {
                DeliveryBLL deliveryBLL = new DeliveryBLL(connection);
                ObservableList<DeliveryModal> deliveries = deliveryBLL.getDeliveryInventory(User.getRegistrationID());
                
                Calendar deliveryCalendar = Calendar.getInstance();
                
                for(int i=0; i<deliveries.size(); i++){
                    deliveryCalendar.setTime(deliveries.get(i).getCurrentDate());
                    if(deliveryCalendar.get(Calendar.YEAR) != year){
                        deliveries.remove(i);
                        i--;
                    }
                }
                for(int i=0; i<deliveries.size(); i++){
                    deliveryCalendar.setTime(deliveries.get(i).getCurrentDate());
                    spending[deliveryCalendar.get(Calendar.MONTH)] = spending[deliveryCalendar.get(Calendar.MONTH)] + deliveries.get(i).getTotalPrice();
                }
            }
        });

        Thread exportationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ExportationBLL exportationBLL = new ExportationBLL(connection);
                ObservableList<ExportationModal> exportations = exportationBLL.getExportationInventory(User.getRegistrationID());
                
                Calendar exportationCalendar = Calendar.getInstance();
                
                for(int i=0; i<exportations.size(); i++){
                    exportationCalendar.setTime(exportations.get(i).getCurrentDate());
                    if(exportationCalendar.get(Calendar.YEAR) != year){
                        exportations.remove(i);
                        i--;
                    }
                }
                for(int i=0; i<exportations.size(); i++){
                    exportationCalendar.setTime(exportations.get(i).getCurrentDate());
                    earning[exportationCalendar.get(Calendar.MONTH)] = earning[exportationCalendar.get(Calendar.MONTH)] + exportations.get(i).getTotalPrice();
                }
            }
        });
        
        importationThread.start();
        deliveryThread.start();
        exportationThread.start();
        
        try {
            importationThread.join();
            deliveryThread.join();
            exportationThread.join();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(BasicInformationBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        basic.setEarning(earning);
        basic.setSpending(spending);
        
        return basic;
    }
    
    
    public ArrayList<Integer> getYearList(){
        
        ImportationBLL importationBLL = new ImportationBLL(connection);
        DeliveryBLL deliveryBLL = new DeliveryBLL(connection);
        ExportationBLL exportationBLL = new ExportationBLL(connection);
        
        
        ObservableList<ImportationModal> importations = importationBLL.getImportationInventory(User.getRegistrationID());
        ObservableList<DeliveryModal> deliveries = deliveryBLL.getDeliveryInventory(User.getRegistrationID());
        ObservableList<ExportationModal> exportations = exportationBLL.getExportationInventory(User.getRegistrationID());
        
        ArrayList<Integer> yearList = new ArrayList<>();
        
        Calendar calendar = Calendar.getInstance();
        
        Integer tempYear = calendar.get(Calendar.YEAR);
        yearList.add(tempYear);
        
        int check_flag = 0;
        
        for(int i=0; i<importations.size(); i++){
            calendar.setTime(importations.get(i).getCurrentDate());
            tempYear = calendar.get(Calendar.YEAR);
            for(int j=0; j<yearList.size(); j++){
                if(tempYear.equals(yearList.get(j))){
                    check_flag++;
                }
            }
            if(check_flag == 0){
                yearList.add(tempYear);
                check_flag=0;
            }
            else{
                check_flag=0;
            }
        }
        
        for(int i=0; i<deliveries.size(); i++){
            calendar.setTime(deliveries.get(i).getCurrentDate());
            tempYear = calendar.get(Calendar.YEAR);
            for(int j=0; j<yearList.size(); j++){
                if(tempYear.equals(yearList.get(j))){
                    check_flag++;
                }
            }
            if(check_flag == 0){
                yearList.add(tempYear);
                check_flag=0;
            }
            else{
                check_flag=0;
            }
        }
        
        for(int i=0; i<exportations.size(); i++){
            calendar.setTime(exportations.get(i).getCurrentDate());
            tempYear = calendar.get(Calendar.YEAR);
            for(int j=0; j<yearList.size(); j++){
                if(tempYear.equals(yearList.get(j))){
                    check_flag++;
                }
            }
            if(check_flag == 0){
                yearList.add(tempYear);
                check_flag=0;
            }
            else{
                check_flag=0;
            }
        }
        
        Collections.sort(yearList);
        
        return yearList;
    }
    
}
