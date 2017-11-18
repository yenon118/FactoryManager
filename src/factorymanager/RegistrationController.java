/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class RegistrationController extends SceneSwitcher implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField companyName;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private TextField state;
    @FXML
    private TextField zipCode;
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;
    @FXML
    private Label invalidTextInTextField;
    
    
    private Connection connection;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        connection = (Connection) LoginController.getConnection();
    }    

    @FXML
    private void clickCancelButton(ActionEvent event) {
        SceneSwitcher.switchScene("Login");
        LoginController loginController = (LoginController) getControllerBySceneName("Login");
        loginController.clearAllLoginTextFields();
        loginController.checkDatabaseConnection();
    }

    @FXML
    private void clickSubmitButton(ActionEvent event) {
        if(username.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Username!");
        }
        else if(email.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Email!");
        }
        else if(password.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Password!");
        }
        else if(firstName.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid First Name!");
        }
        else if(lastName.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Last Name!");
        }
        else if(companyName.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Company Name!");
        }
        else if(address.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Address!");
        }
        else if(city.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid City!");
        }
        else if(state.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid State!");
        }
        else if(zipCode.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Zip Code!");
        }
        else{
            if(connection != null){
                try {
                    String query = "INSERT INTO Registration (Username, Email, Password, FirstName, LastName, CompanyName, Address, City, State, ZipCode)"
                                    + " VALUES (?, ?, PASSWORD(?), ?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
                    dbPreparedStatement.setString(1, username.getText());
                    dbPreparedStatement.setString(2, email.getText());
                    dbPreparedStatement.setString(3, password.getText());
                    dbPreparedStatement.setString(4, firstName.getText());
                    dbPreparedStatement.setString(5, lastName.getText());
                    dbPreparedStatement.setString(6, companyName.getText());
                    dbPreparedStatement.setString(7, address.getText());
                    dbPreparedStatement.setString(8, city.getText());
                    dbPreparedStatement.setString(9, state.getText());
                    dbPreparedStatement.setInt(10, Integer.parseInt(zipCode.getText()));

                    dbPreparedStatement.execute();

                    SceneSwitcher.switchScene("Login");
                    LoginController loginController = (LoginController) getControllerBySceneName("Login");
                    loginController.clearAllLoginTextFields();
                    loginController.checkDatabaseConnection();

                } catch (SQLException ex) {
                    Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                invalidTextInTextField.setText("Database Connection Fail!");
            }
        }
    }

    public void clearAllRegistrationTextFields() {
        username.clear();
        email.clear();
        password.clear();
        firstName.clear();
        lastName.clear();
        companyName.clear();
        address.clear();
        city.clear();
        state.clear();
        zipCode.clear();
        invalidTextInTextField.setText("");
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
}
