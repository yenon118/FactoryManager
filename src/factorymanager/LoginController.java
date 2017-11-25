/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import com.mysql.jdbc.Connection;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author WIN
 */
public class LoginController extends SceneSwitcher implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button submitButton;
    @FXML
    private Hyperlink registrationLink;
    @FXML
    private Label databaseConnectionLabel;
    @FXML
    private Label invalidLoginText;
    
    
    private static Connection connection;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        checkDatabaseConnection();
    }    

    @FXML
    private void clickSubmitButton(ActionEvent event) {
        RegistrationBLL registrationBLL = new RegistrationBLL(connection);
        
        RegistrationModal registration = new RegistrationModal();
        
        registration.setUsername(username.getText());
        registration.setPassword(password.getText());
        
        boolean validLogin = registrationBLL.userLogin(registration);
        
        if(validLogin == true){
            MainController.setConnection(connection);
            SceneSwitcher.switchScene("Main");
            MainController.generateMainStages();
            MainController mainController = (MainController) getControllerBySceneName("Main");
            mainController.fillComboBox();
            Calendar calendar = Calendar.getInstance();
            mainController.updateMain(calendar.get(Calendar.YEAR));
        }
        else{
            clearAllLoginTextFields();
            invalidLoginText.setText("Login Fail");
        }
    }

    @FXML
    private void clickRegistrationLink(ActionEvent event) {
        SceneSwitcher.switchScene("Registration");
        RegistrationController registrationController = (RegistrationController) getControllerBySceneName("Registration");
        registrationController.clearAllRegistrationTextFields();
        registrationController.setConnection(connection);
    }
    
    public void checkDatabaseConnection(){
        if(connection == null){
            RegistrationBLL registrationBLL = new RegistrationBLL();
            connection = (Connection) registrationBLL.connectToDatabase();
        }
        if(connection != null){
            databaseConnectionLabel.setText("Database Connection Success");
            databaseConnectionLabel.setTextFill(Color.GREEN);
        }
        else{
            databaseConnectionLabel.setText("Database Connection Fail");
            databaseConnectionLabel.setTextFill(Color.RED);
        }
    }
    
    public static Connection getConnection() {
        return connection;
    }
    
    public void clearAllLoginTextFields() {
        username.clear();
        password.clear();
        invalidLoginText.setText("");
    }
    
    public void setInvalidRegistration(){
        invalidLoginText.setText("Invalid registration. Account exist.");
    }
    
}
