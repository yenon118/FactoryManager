/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WIN
 */
public class MainController extends SceneSwitcher implements Initializable {

    @FXML
    private MenuItem logOutMenuItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private MenuItem userInfoMenuItem;
    
    private static Stage stage;
    
    private static Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickLogOutMenuItem(ActionEvent event) {
        SceneSwitcher.switchScene("Login");
        LoginController loginController = (LoginController) getControllerBySceneName("Login");
        loginController.clearAllLoginTextFields();
        loginController.checkDatabaseConnection();
    }

    @FXML
    private void clickCloseMenuItem(ActionEvent event) {
        stage = SceneSwitcher.getStage();
        connection = LoginController.getConnection();
        try {
            if(connection != null || connection.isClosed() == false){
                connection.close();
                System.out.println("Database connection is closed.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.close();
    }

    @FXML
    private void clickAboutMenuItem(ActionEvent event) {
    }

    @FXML
    private void clickUserInfoMenuItem(ActionEvent event) {
        User user = new User();
        
        user.showUserInfo();
        
    }
    
}
