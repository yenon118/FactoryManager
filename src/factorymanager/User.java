/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        
        String userInfoStageTitle = "User Information";
        Stage userInfoStage = new Stage();
        VBox userInfoRoot = new VBox();
        userInfoRoot.setAlignment(Pos.TOP_CENTER);
        Label userInfoLabel = new Label();
        userInfoLabel.setAlignment(Pos.CENTER_LEFT);
        userInfoLabel.setText("Username: " + User.getUsername() + "\n" + 
                                "Email: " + User.getEmail() + "\n" + 
                                "First Name: " + User.getFirstName() + "\n" + 
                                "Last Name: " + User.getLastName() + "\n" + 
                                "Company Name: " + User.getCompanyName() + "\n" + 
                                "Address: " + User.getAddress() + "\n" + 
                                "City: " + User.getCity() + "\n" + 
                                "State: " + User.getState() + "\n" + 
                                "Zip Code: " + User.getZipCode() + "\n\n\n\n");
        Button userInfoButton = new Button();
        userInfoButton.setText("OK");
        userInfoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userInfoStage.close();
            }
        });
        
        userInfoRoot.getChildren().addAll(userInfoLabel, userInfoButton);
        
        Scene scene = new Scene(userInfoRoot, 400, 300);

        userInfoStage.setTitle(userInfoStageTitle);
        userInfoStage.setScene(scene);
        userInfoStage.show();
    }
    
}
