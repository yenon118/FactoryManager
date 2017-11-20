/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WIN
 */
public class ImportationController implements Initializable {

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
    
    private static Stage stage;
//    private Parent root;
    private static Scene scene;
//    private FXMLLoader loader;
    
    private static ImportationInformationController importationInformationController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void clickCancelButton(ActionEvent event) {
        try {
            Parent root = (Parent)FXMLLoader.load(getClass().getResource("ImportationInformation.fxml"));
            
            scene.setRoot(root);
            stage.setScene(scene);
            stage.setTitle("Importation Information");
            stage.sizeToScene();
            stage.centerOnScreen();
                        
            importationInformationController.fillTable();
                                    
        } catch (IOException ex) {
            Logger.getLogger(ImportationInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickSubmitButton(ActionEvent event) {
        try {
            Parent root = (Parent)FXMLLoader.load(getClass().getResource("ImportationInformation.fxml"));
            
            scene.setRoot(root);
            stage.setScene(scene);
            stage.setTitle("Importation Information");
            stage.sizeToScene();
            stage.centerOnScreen();
            
            importationInformationController.fillTable();
            
        } catch (IOException ex) {
            Logger.getLogger(ImportationInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setupView(Stage stage, Scene scene) {
        ImportationController.stage = stage;
        ImportationController.scene = scene;
    }

    public static void setConnection(Connection connection) {
        ImportationController.connection = connection;
    }

    public static void setImportationInformationController(ImportationInformationController importationInformationController) {
        ImportationController.importationInformationController = importationInformationController;
    }
    
    
    
}
