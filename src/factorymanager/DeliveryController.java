/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.net.URL;
import java.sql.Connection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WIN
 */
public class DeliveryController implements Initializable {
    
    @FXML
    private TextField companyName;
    @FXML
    private ComboBox<String> exportationComboBox;
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
    private DatePicker date;
    @FXML
    private TextField shipmentPrice;
    @FXML
    private TextField containerPricePerUnit;
    @FXML
    private TextField containerQuantity;
    @FXML
    private Label invalidTextInTextField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;
    
    
    
    private static Connection connection;
    
    private Stage stage;
    private Scene deliveryInformationScene;
    private DeliveryInformationController deliveryInformationController;
        
    private DeliveryModal delivery;
    
    private static HashMap<Integer, String> hashMap;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public static void setConnection(Connection connection) {
        DeliveryController.connection = connection;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setDeliveryInformationScene(Scene deliveryInformationScene) {
        this.deliveryInformationScene = deliveryInformationScene;
    }

    public void setDeliveryInformationController(DeliveryInformationController deliveryInformationController) {
        this.deliveryInformationController = deliveryInformationController;
    }

    @FXML
    private void clickCancelButton(ActionEvent event) {
        stage.setScene(deliveryInformationScene);
        deliveryInformationController.fillTable();
    }

    @FXML
    private void clickSubmitButton(ActionEvent event) {
        if(companyName.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Company Name!");
        }
        else if(pointOfContact.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Point of Contact!");
        }
        else if(email.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Email!");
        }        
        else if(countryCode.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Country Code!");
        }
        else if(phoneNumber.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Phone Number!");
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
        else if(date.getValue().toString().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Date!");
        }
        else if(shipmentPrice.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Shipment Price!");
        }
        else if(containerPricePerUnit.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Container Price Per Unit!");
        }
        else if(containerQuantity.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Container Quantity!");
        }
        else{
            if(DeliveryController.connection != null){
                
                if(delivery != null){
                    
                    DeliveryBLL deliveryBLL = new DeliveryBLL(connection);
                    DeliveryModal deliveryForTransfer = new DeliveryModal();
                    
                    deliveryForTransfer.setDeliveryID(delivery.getDeliveryID());
                    deliveryForTransfer.setRegistrationID(delivery.getRegistrationID());
                    
                    for(int key: hashMap.keySet()){
                        if(hashMap.get(key).equals(exportationComboBox.getSelectionModel().getSelectedItem())){
                            deliveryForTransfer.setExportationID(key);
                            break;
                        }
                    }
                    
                    deliveryForTransfer.setCompanyName(companyName.getText());
                    deliveryForTransfer.setPointOfContact(pointOfContact.getText());
                    deliveryForTransfer.setCountryCode(Integer.parseInt(countryCode.getText()));
                    deliveryForTransfer.setPhoneNumber(Long.parseLong(phoneNumber.getText()));
                    deliveryForTransfer.setEmail(email.getText());
                    deliveryForTransfer.setAddress(address.getText());
                    deliveryForTransfer.setCity(city.getText());
                    deliveryForTransfer.setState(state.getText());
                    deliveryForTransfer.setZipCode(Integer.parseInt(zipCode.getText()));
                    deliveryForTransfer.setCurrentDate(java.sql.Date.valueOf(date.getValue()));
                    deliveryForTransfer.setShipmentPrice(Double.parseDouble(shipmentPrice.getText()));
                    deliveryForTransfer.setContainerPricePerUnit(Double.parseDouble(containerPricePerUnit.getText()));
                    deliveryForTransfer.setContainerQuantity(Integer.parseInt(containerQuantity.getText()));
                    deliveryForTransfer.setTotalPrice(deliveryForTransfer.calculateTotal());
                    
                    deliveryBLL.updateDeliveryRecord(deliveryForTransfer);
                    
                    delivery = null;
                }
                else{
                    DeliveryBLL deliveryBLL = new DeliveryBLL(connection);
                    DeliveryModal deliveryForTransfer = new DeliveryModal();
                    
//                    deliveryForTransfer.setDeliveryID(delivery.getDeliveryID());
                    deliveryForTransfer.setRegistrationID(User.getRegistrationID());
                                        
                    for(int key: hashMap.keySet()){
                        if(hashMap.get(key).equals(exportationComboBox.getSelectionModel().getSelectedItem())){
                            deliveryForTransfer.setExportationID(key);
                            break;
                        }
                    }
                    
                    deliveryForTransfer.setCompanyName(companyName.getText());
                    deliveryForTransfer.setPointOfContact(pointOfContact.getText());
                    deliveryForTransfer.setCountryCode(Integer.parseInt(countryCode.getText()));
                    deliveryForTransfer.setPhoneNumber(Long.parseLong(phoneNumber.getText()));
                    deliveryForTransfer.setEmail(email.getText());
                    deliveryForTransfer.setAddress(address.getText());
                    deliveryForTransfer.setCity(city.getText());
                    deliveryForTransfer.setState(state.getText());
                    deliveryForTransfer.setZipCode(Integer.parseInt(zipCode.getText()));
                    deliveryForTransfer.setCurrentDate(java.sql.Date.valueOf(date.getValue()));
                    deliveryForTransfer.setShipmentPrice(Double.parseDouble(shipmentPrice.getText()));
                    deliveryForTransfer.setContainerPricePerUnit(Double.parseDouble(containerPricePerUnit.getText()));
                    deliveryForTransfer.setContainerQuantity(Integer.parseInt(containerQuantity.getText()));
                    deliveryForTransfer.setTotalPrice(deliveryForTransfer.calculateTotal());
                    
                    deliveryBLL.addDeliveryRecord(deliveryForTransfer);
                    
                }
                stage.setScene(deliveryInformationScene);
                deliveryInformationController.fillTable();
            }
            else{
                invalidTextInTextField.setText("Database Connection Fail!");
            }
        }                
    }
    
    public void clearAllDeliveryFields(){
        companyName.clear();
        
        fillComboBox();
        
        pointOfContact.clear();
        email.clear();
        countryCode.clear();
        phoneNumber.clear();
        address.clear();
        city.clear();
        state.clear();
        zipCode.clear();
        date.setValue(LocalDate.now());
        shipmentPrice.clear();
        containerPricePerUnit.clear();
        containerQuantity.clear();
        invalidTextInTextField.setText("");
        
        delivery = null;
    }
    
    public void fillComboBox(){
        if(hashMap != null && hashMap.isEmpty() == false){
            hashMap.clear();
        }
        DeliveryBLL deliveryBLL = new DeliveryBLL(connection);
        hashMap = deliveryBLL.loadDeliveryInventory(User.getRegistrationID());
        exportationComboBox.getItems().clear();
        exportationComboBox.getItems().addAll(hashMap.values());
    }
    
    public void setupAllDeliveryFields(DeliveryModal delivery){
        Instant instant = Instant.ofEpochMilli(delivery.getCurrentDate().getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        
        companyName.setText(delivery.getCompanyName());
        
        fillComboBox();
        
        ExportationBLL exportationBLL = new ExportationBLL(connection);
        ObservableList<ExportationModal> tempExportations = exportationBLL.getExportationInventory(User.getRegistrationID());
        
        String tempTarget = null;
        
        for(ExportationModal temp : tempExportations){
            if(temp.getExportationID().equals(delivery.getExportationID())){
                hashMap.put(temp.getExportationID(), temp.getCompanyName() + "   |   " + temp.getCurrentDate() + "   |   " + temp.getProduct() + "   |   " + temp.getTotalPrice());
                tempTarget = temp.getCompanyName() + "   |   " + temp.getCurrentDate() + "   |   " + temp.getProduct() + "   |   " + temp.getTotalPrice();
            }
        }
        
        exportationComboBox.getItems().clear();
        exportationComboBox.getItems().addAll(hashMap.values());
        
        int highlight = 0;
        
        for(Integer key: hashMap.keySet()){
            if(hashMap.get(key).equals(tempTarget)){
                exportationComboBox.getSelectionModel().select(highlight);
                break;
            }
            highlight++;
        }
        
        highlight = 0;
        
        pointOfContact.setText(delivery.getPointOfContact());
        email.setText(delivery.getEmail());
        countryCode.setText(delivery.getCountryCode().toString());
        phoneNumber.setText(delivery.getPhoneNumber().toString());
        address.setText(delivery.getAddress());
        city.setText(delivery.getCity());
        state.setText(delivery.getState());
        zipCode.setText(delivery.getZipCode().toString());
        date.setValue(localDate);
        shipmentPrice.setText(delivery.getShipmentPrice().toString());
        containerPricePerUnit.setText(delivery.getContainerPricePerUnit().toString());
        containerQuantity.setText(delivery.getContainerQuantity().toString());
        invalidTextInTextField.setText("");
        
        this.delivery = delivery;
    }
    
}
