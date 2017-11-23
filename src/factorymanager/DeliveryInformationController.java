/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WIN
 */
public class DeliveryInformationController implements Initializable {

    @FXML
    private TableView<DeliveryModal> exportationTableView;
    @FXML
    private TableColumn<DeliveryModal, String> companyNameColumn;
    @FXML
    private TableColumn<DeliveryModal, String> pointOfContactColumn;
    @FXML
    private TableColumn<DeliveryModal, Integer> countryCodeColumn;
    @FXML
    private TableColumn<DeliveryModal, Long> phoneNumberColumn;
    @FXML
    private TableColumn<DeliveryModal, String> emailColumn;
    @FXML
    private TableColumn<DeliveryModal, String> addressColumn;
    @FXML
    private TableColumn<DeliveryModal, String> cityColumn;
    @FXML
    private TableColumn<DeliveryModal, String> stateColumn;
    @FXML
    private TableColumn<DeliveryModal, Integer> zipCodeColumn;
    @FXML
    private TableColumn<DeliveryModal, Date> dateColumn;
    @FXML
    private TableColumn<DeliveryModal, Double> containerPricePerUnitColumn;
    @FXML
    private TableColumn<DeliveryModal, Integer> containerQuantityColumn;
    @FXML
    private TableColumn<DeliveryModal, Double> shipmentPriceColumn;
    @FXML
    private TableColumn<DeliveryModal, Double> totalPriceColumn;
    @FXML
    private Button exitButton;
    @FXML
    private Button deleteRecordButton;
    @FXML
    private Button editRecordButton;
    @FXML
    private Button addRecordButton;
    
    
    private static Connection connection;
    
    private Stage stage;
    private Parent deliveryRoot;
    private Scene deliveryScene;
    private Scene deliveryInformationScene;
    private FXMLLoader deliveryLoader;
    private DeliveryController deliveryController;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickExitButton(ActionEvent event) {
        if(stage.isShowing()){
            stage.close();
        }
    }

    @FXML
    private void clickDeleteRecordButton(ActionEvent event) {
    }

    @FXML
    private void clickEditRecordButton(ActionEvent event) {
    }

    @FXML
    private void clickAddRecordButton(ActionEvent event) {
    }
    
    public void setupView(Stage stage) {
        this.stage = stage;
        this.deliveryInformationScene = stage.getScene();
    }
    
    public static void setConnection(Connection connection) {
        DeliveryInformationController.connection = connection;
    }
    
    public void fillTable(){
    }
    
}
