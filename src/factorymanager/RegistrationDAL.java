/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WIN
 */
public class RegistrationDAL {
    
    private Connection connection;
    
    private static final String driver = "mysql";
    private static final String hostname = "masterdbinstance.ckwkopdxavud.us-west-2.rds.amazonaws.com";
    private static final int port = 3306;
    private static final String dbName = "FactoryManager";
    private static final String dbUsername = "yenon118";
    private static final String dbPassword = "masterpass1234";
    private String jdbcUrl;

    public RegistrationDAL() {
    }
    
    public RegistrationDAL(Connection connection) {
        this.connection = connection;
    }
    
    public Connection connectToDatabase(){
        try {            
            jdbcUrl = "jdbc:" + driver + "://" + hostname + ":" + port + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(jdbcUrl);            
        } catch (SQLException ex) { 
            Logger.getLogger(RegistrationDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    public boolean addRegistrationRecord(RegistrationModal registration){
        try {
            String query = "SELECT * FROM Registration WHERE Username = ? AND Password = PASSWORD(?)";
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            dbPreparedStatement.setString(1, registration.getUsername());
            dbPreparedStatement.setString(2, registration.getPassword());
            ResultSet dbResultSet = dbPreparedStatement.executeQuery();
            boolean hasResult = dbResultSet.next();
            
            if(hasResult == false){            
                query = "INSERT INTO Registration (Username, Email, Password, FirstName, LastName, CompanyName, Address, City, State, ZipCode)"
                                + " VALUES (?, ?, PASSWORD(?), ?, ?, ?, ?, ?, ?, ?)";

                dbPreparedStatement = connection.prepareStatement(query);
                dbPreparedStatement.setString(1, registration.getUsername());
                dbPreparedStatement.setString(2, registration.getEmail());
                dbPreparedStatement.setString(3, registration.getPassword());
                dbPreparedStatement.setString(4, registration.getFirstName());
                dbPreparedStatement.setString(5, registration.getLastName());
                dbPreparedStatement.setString(6, registration.getCompanyName());
                dbPreparedStatement.setString(7, registration.getAddress());
                dbPreparedStatement.setString(8, registration.getCity());
                dbPreparedStatement.setString(9, registration.getState());
                dbPreparedStatement.setInt(10, registration.getZipCode());

                dbPreparedStatement.execute();
                
                return true;
            }
            else{
                return false;
            }
            
        } catch (SQLException ex) { 
            Logger.getLogger(RegistrationDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean userLogin(RegistrationModal registration){
        try {
            String query = "SELECT * FROM Registration WHERE Username = ? AND Password = PASSWORD(?)";
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            dbPreparedStatement.setString(1, registration.getUsername());
            dbPreparedStatement.setString(2, registration.getPassword());
            ResultSet dbResultSet = dbPreparedStatement.executeQuery();
            boolean hasResult = dbResultSet.next();
            
            if(hasResult == true){
                User.setRegistrationID(Integer.parseInt(dbResultSet.getString(1)));
                User.setUsername(dbResultSet.getString(2));
                User.setEmail(dbResultSet.getString(3));
                User.setFirstName(dbResultSet.getString(5));
                User.setLastName(dbResultSet.getString(6));
                User.setCompanyName(dbResultSet.getString(7));
                User.setAddress(dbResultSet.getString(8));
                User.setCity(dbResultSet.getString(9));
                User.setState(dbResultSet.getString(10));
                User.setZipCode(Integer.parseInt(dbResultSet.getString(11)));
                
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) { 
            Logger.getLogger(RegistrationDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
