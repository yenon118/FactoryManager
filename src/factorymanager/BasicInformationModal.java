/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

/**
 *
 * @author WIN
 */
public class BasicInformationModal {
    
    private double[] earning;
    private double[] spending;
    
    private double totalEarning;
    private double totalSpending;
    private double balance;

    public BasicInformationModal() {
        earning = new double[12];
        spending = new double[12];
        totalEarning = 0;
        totalSpending = 0;
        balance = 0;
    }

    public double[] getEarning() {
        return earning;
    }

    public void setEarning(double[] earning) {
        this.earning = earning;
    }

    public double[] getSpending() {
        return spending;
    }

    public void setSpending(double[] spending) {
        this.spending = spending;
    }

    public double getTotalEarning() {
        for(double tempEarning : earning){
            totalEarning = totalEarning + tempEarning;
        }
        return totalEarning;
    }
    
    public double getTotalSpending() {
        for(double tempSpending : spending){
            totalSpending = totalSpending + tempSpending;
        }
        return totalSpending;
    }
    
    public double getBalance() {
        balance = totalEarning - totalSpending;
        return balance;
    }
    
}
