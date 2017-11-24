/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WIN
 */
public class DeliveryInformationController implements Initializable {

    @FXML
    private TableView<DeliveryModal> deliveryTableView;
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
        if(deliveryTableView.getSelectionModel().getSelectedItem() != null){
            
            DeliveryModal delivery = new DeliveryModal();
            
            delivery.setDeliveryID(deliveryTableView.getSelectionModel().getSelectedItem().getDeliveryID());
                        
            DeliveryBLL deliveryBLL = new DeliveryBLL(connection);
            
            deliveryBLL.deleteDeliveryRecord(delivery.getDeliveryID());
            
            fillTable();
        }
    }

    @FXML
    private void clickEditRecordButton(ActionEvent event) {
        if(deliveryTableView.getSelectionModel().getSelectedItem() != null){
            
            DeliveryModal delivery = new DeliveryModal();
            
            delivery.setDeliveryID(deliveryTableView.getSelectionModel().getSelectedItem().getDeliveryID());
            delivery.setRegistrationID(deliveryTableView.getSelectionModel().getSelectedItem().getRegistrationID());
            delivery.setExportationID(deliveryTableView.getSelectionModel().getSelectedItem().getExportationID());
            delivery.setCompanyName(deliveryTableView.getSelectionModel().getSelectedItem().getCompanyName());
            delivery.setPointOfContact(deliveryTableView.getSelectionModel().getSelectedItem().getPointOfContact());
            delivery.setCountryCode(deliveryTableView.getSelectionModel().getSelectedItem().getCountryCode());
            delivery.setPhoneNumber(deliveryTableView.getSelectionModel().getSelectedItem().getPhoneNumber());
            delivery.setEmail(deliveryTableView.getSelectionModel().getSelectedItem().getEmail());
            delivery.setAddress(deliveryTableView.getSelectionModel().getSelectedItem().getAddress());
            delivery.setCity(deliveryTableView.getSelectionModel().getSelectedItem().getCity());
            delivery.setState(deliveryTableView.getSelectionModel().getSelectedItem().getState());
            delivery.setZipCode(deliveryTableView.getSelectionModel().getSelectedItem().getZipCode());
            delivery.setCurrentDate(deliveryTableView.getSelectionModel().getSelectedItem().getCurrentDate());
            delivery.setShipmentPrice(deliveryTableView.getSelectionModel().getSelectedItem().getShipmentPrice());
            delivery.setContainerPricePerUnit(deliveryTableView.getSelectionModel().getSelectedItem().getContainerPricePerUnit());
            delivery.setContainerQuantity(deliveryTableView.getSelectionModel().getSelectedItem().getContainerQuantity());
            delivery.setTotalPrice(deliveryTableView.getSelectionModel().getSelectedItem().getTotalPrice());
            
            try {
                if(deliveryScene == null){
                    deliveryLoader = new FXMLLoader(getClass().getResource("Delivery.fxml"));
                    deliveryRoot = (Parent) deliveryLoader.load();
                    deliveryController = deliveryLoader.getController();

                    deliveryController.setStage(stage);
                    deliveryController.setDeliveryInformationScene(deliveryInformationScene);
                    deliveryController.setDeliveryInformationController(this);

                    deliveryScene = new Scene(deliveryRoot);
                }

                deliveryController.setupAllDeliveryFields(delivery);
                stage.setScene(deliveryScene);
                stage.setTitle("Delivery");
                stage.sizeToScene();
                stage.centerOnScreen();

            } catch (IOException ex) {
                Logger.getLogger(DeliveryInformationController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }
    }

    @FXML
    private void clickAddRecordButton(ActionEvent event) {
        try {
            if(deliveryScene == null){
                deliveryLoader = new FXMLLoader(getClass().getResource("Delivery.fxml"));
                deliveryRoot = (Parent) deliveryLoader.load();
                deliveryController = deliveryLoader.getController();

                deliveryController.setStage(stage);
                deliveryController.setDeliveryInformationScene(deliveryInformationScene);
                deliveryController.setDeliveryInformationController(this);

                deliveryScene = new Scene(deliveryRoot);
            }
            
            deliveryController.clearAllDeliveryFields();
            deliveryController.fillComboBox();
            stage.setScene(deliveryScene);
            stage.setTitle("Delivery");
            stage.sizeToScene();
            stage.centerOnScreen();
                                    
        } catch (IOException ex) {
            Logger.getLogger(DeliveryInformationController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void setupView(Stage stage) {
        this.stage = stage;
        this.deliveryInformationScene = stage.getScene();
    }
    
    public static void setConnection(Connection connection) {
        DeliveryInformationController.connection = connection;
    }
    
    public void fillTable(){
        
        
        DeliveryBLL deliveryBLL = new DeliveryBLL(connection);
        
        ObservableList<DeliveryModal> deliveries = deliveryBLL.getDeliveryInventory(User.getRegistrationID());
        
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        pointOfContactColumn.setCellValueFactory(new PropertyValueFactory<>("pointOfContact"));
        countryCodeColumn.setCellValueFactory(new PropertyValueFactory<>("countryCode"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("currentDate"));
        shipmentPriceColumn.setCellValueFactory(new PropertyValueFactory<>("shipmentPrice"));
        containerPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("containerPricePerUnit"));
        containerQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("containerQuantity"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        
        companyNameColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        pointOfContactColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        countryCodeColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        phoneNumberColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        emailColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        addressColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        cityColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        stateColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        zipCodeColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        dateColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        shipmentPriceColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        containerPricePerUnitColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        containerQuantityColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        totalPriceColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        
        shipmentPriceColumn.setCellFactory(tableColumn -> new TableCell<DeliveryModal, Double>(){
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty) ;
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%#.2f", value.doubleValue()));                    
                }
            }            
        });

        containerPricePerUnitColumn.setCellFactory(tableColumn -> new TableCell<DeliveryModal, Double>(){
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty) ;
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%#.2f", value.doubleValue()));                    
                }
            }            
        });
        
        totalPriceColumn.setCellFactory(tableColumn -> new TableCell<DeliveryModal, Double>(){
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty) ;
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%#.2f", value.doubleValue()));                    
                }
            }            
        });
        
        deliveryTableView.getItems().clear();
        deliveryTableView.setItems(deliveries);
        deliveryTableView.refresh();
    }
    
}
