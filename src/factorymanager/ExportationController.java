/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author WIN
 */
public class ExportationController implements Initializable {

    @FXML
    private TextField companyName;
    @FXML
    private TextField pointOfContact;
    @FXML
    private TextField email;
    @FXML
    private TextField countryCode;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private TextField state;
    @FXML
    private TextField zipCode;
    @FXML
    private TextField date;
    @FXML
    private TextField product;
    @FXML
    private TextField pricePerUnit;
    @FXML
    private TextField quantity;
    @FXML
    private Label invalidTextInTextField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;
    
    
    private static Connection connection;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickCancelButton(ActionEvent event) {
    }

    @FXML
    private void clickSubmitButton(ActionEvent event) {
    }

    public static void setConnection(Connection connection) {
        ExportationController.connection = connection;
    }
    
}
