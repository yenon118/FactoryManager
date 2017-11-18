/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author WIN
 */
public class User {
    
    private static Integer registrationID;
    private static String username;
    private static String email;
    private static String firstName;
    private static String lastName;
    private static String companyName;
    private static String address;
    private static String city;
    private static String state;
    private static Integer zipCode;

    public User() {
    }

    public static Integer getRegistrationID() {
        return registrationID;
    }

    public static String getUsername() {
        return username;
    }

    public static String getEmail() {
        return email;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getCompanyName() {
        return companyName;
    }

    public static String getAddress() {
        return address;
    }

    public static String getCity() {
        return city;
    }

    public static String getState() {
        return state;
    }

    public static Integer getZipCode() {
        return zipCode;
    }

    public static void setRegistrationID(Integer registrationID) {
        User.registrationID = registrationID;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static void setFirstName(String firstName) {
        User.firstName = firstName;
    }

    public static void setLastName(String lastName) {
        User.lastName = lastName;
    }

    public static void setCompanyName(String companyName) {
        User.companyName = companyName;
    }

    public static void setAddress(String address) {
        User.address = address;
    }

    public static void setCity(String city) {
        User.city = city;
    }

    public static void setState(String state) {
        User.state = state;
    }

    public static void setZipCode(Integer zipCode) {
        User.zipCode = zipCode;
    }
    
    public void showUserInfo(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("User Information");
        alert.setHeaderText("Show User Information");
        alert.setContentText("Username: " + User.getUsername() + "\n" + 
                                "Email: " + User.getEmail() + "\n" + 
                                "First Name: " + User.getFirstName() + "\n" + 
                                "Last Name: " + User.getLastName() + "\n" + 
                                "Company Name: " + User.getCompanyName() + "\n" + 
                                "Address: " + User.getAddress() + "\n" + 
                                "City: " + User.getCity() + "\n" + 
                                "State: " + User.getState() + "\n" + 
                                "Zip Code: " + User.getZipCode() + "\n");
        alert.showAndWait();
    }
    
}
