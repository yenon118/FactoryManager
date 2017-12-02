/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ycth8factorymanager;

import java.sql.Connection;

/**
 *
 * @author WIN
 */
public class RegistrationBLL {
    
    private RegistrationDAL registrationDAL;
    
    public RegistrationBLL() {
        registrationDAL = new RegistrationDAL();
    }
    
    public RegistrationBLL(Connection connection) {
        registrationDAL = new RegistrationDAL(connection);
    }
    
    public Connection connectToDatabase(){
        return registrationDAL.connectToDatabase();
    }
    
    public boolean addRegistrationRecord(RegistrationModal registration){
        return registrationDAL.addRegistrationRecord(registration);
    }
    
    public boolean userLogin(RegistrationModal registration){
        return registrationDAL.userLogin(registration);
    }
    
}
