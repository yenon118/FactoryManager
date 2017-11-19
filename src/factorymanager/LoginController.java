/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static final String driver = "mysql";
    private static final String hostname = "masterdbinstance.ckwkopdxavud.us-west-2.rds.amazonaws.com";
    private static final int port = 3306;
    private static final String dbName = "FactoryManager";
    private static final String dbUsername = "yenon118";
    private static final String dbPassword = "masterpass1234";
    private String jdbcUrl;
    
    
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
        try {
            String query = "SELECT * FROM Registration WHERE Username = ? AND Password = PASSWORD(?)";
            PreparedStatement dbPreparedStatement = connection.prepareStatement(query);
            dbPreparedStatement.setString(1, username.getText());
            dbPreparedStatement.setString(2, password.getText());
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
                connection.close();
                SceneSwitcher.switchScene("Main");
                MainController.generateMainStages();
            }
            else{
                clearAllLoginTextFields();
                invalidLoginText.setText("Login Fail");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
        boolean validConnection = connectToDatabase();
        
        if(validConnection == true || connection != null){
            databaseConnectionLabel.setText("Database Connection Success");
            databaseConnectionLabel.setTextFill(Color.GREEN);
        }
        else{
            databaseConnectionLabel.setText("Database Connection Fail");
            databaseConnectionLabel.setTextFill(Color.RED);
        }
    }
    
    private boolean connectToDatabase(){
        try {
            if(connection == null || connection.isClosed()){
                jdbcUrl = "jdbc:" + driver + "://" + hostname + ":" + port + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
                connection = (Connection) DriverManager.getConnection(jdbcUrl);
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Connection getConnection() {
        return connection;
    }
    
    public void clearAllLoginTextFields() {
        username.clear();
        password.clear();
        invalidLoginText.setText("");
    }
    
}
