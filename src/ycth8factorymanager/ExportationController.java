/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ycth8factorymanager;

import java.net.URL;
import java.sql.Connection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private DatePicker date;
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
    
    private Stage stage;
    private Scene exportationInformationScene;
    private ExportationInformationController exportationInformationController;
        
    private ExportationModal exportation;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickCancelButton(ActionEvent event) {
        stage.setScene(exportationInformationScene);
        exportationInformationController.fillTable();
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
        else if(product.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Product!");
        }
        else if(pricePerUnit.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Price Per Unit!");
        }
        else if(quantity.getText().trim().isEmpty()){
            invalidTextInTextField.setText("Invalid Quantity!");
        }
        else{
            if(ExportationController.connection != null){
                
                if(exportation != null){
                    
                    ExportationBLL exportationBLL = new ExportationBLL(connection);
                    ExportationModal exportationForTransfer = new ExportationModal();
                    
                    exportationForTransfer.setExportationID(exportation.getExportationID());
                    exportationForTransfer.setRegistrationID(exportation.getRegistrationID());
                    exportationForTransfer.setCompanyName(companyName.getText());
                    exportationForTransfer.setPointOfContact(pointOfContact.getText());
                    exportationForTransfer.setCountryCode(Integer.parseInt(countryCode.getText()));
                    exportationForTransfer.setPhoneNumber(Long.parseLong(phoneNumber.getText()));
                    exportationForTransfer.setEmail(email.getText());
                    exportationForTransfer.setAddress(address.getText());
                    exportationForTransfer.setCity(city.getText());
                    exportationForTransfer.setState(state.getText());
                    exportationForTransfer.setZipCode(Integer.parseInt(zipCode.getText()));
                    exportationForTransfer.setCurrentDate(java.sql.Date.valueOf(date.getValue()));
                    exportationForTransfer.setProduct(product.getText());
                    exportationForTransfer.setPricePerUnit(Double.parseDouble(pricePerUnit.getText()));
                    exportationForTransfer.setQuantity(Integer.parseInt(quantity.getText()));
                    exportationForTransfer.setTotalPrice(exportationForTransfer.calculateTotal());
                    
                    exportationBLL.updateExportationRecord(exportationForTransfer);
                    
                    exportation = null;
                }
                else{
                    ExportationBLL exportationBLL = new ExportationBLL(connection);
                    ExportationModal exportationForTransfer = new ExportationModal();
                    
//                    exportationForTransfer.setExportationID(exportation.getExportationID());
                    exportationForTransfer.setRegistrationID(User.getRegistrationID());
                    exportationForTransfer.setCompanyName(companyName.getText());
                    exportationForTransfer.setPointOfContact(pointOfContact.getText());
                    exportationForTransfer.setCountryCode(Integer.parseInt(countryCode.getText()));
                    exportationForTransfer.setPhoneNumber(Long.parseLong(phoneNumber.getText()));
                    exportationForTransfer.setEmail(email.getText());
                    exportationForTransfer.setAddress(address.getText());
                    exportationForTransfer.setCity(city.getText());
                    exportationForTransfer.setState(state.getText());
                    exportationForTransfer.setZipCode(Integer.parseInt(zipCode.getText()));
                    exportationForTransfer.setCurrentDate(java.sql.Date.valueOf(date.getValue()));
                    exportationForTransfer.setProduct(product.getText());
                    exportationForTransfer.setPricePerUnit(Double.parseDouble(pricePerUnit.getText()));
                    exportationForTransfer.setQuantity(Integer.parseInt(quantity.getText()));
                    exportationForTransfer.setTotalPrice(exportationForTransfer.calculateTotal());
                    
                    exportationBLL.addExportationRecord(exportationForTransfer);
                }
                stage.setScene(exportationInformationScene);
                exportationInformationController.fillTable();
            }
            else{
                invalidTextInTextField.setText("Database Connection Fail!");
            }
        }        
    }

    public static void setConnection(Connection connection) {
        ExportationController.connection = connection;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setExportationInformationScene(Scene exportationInformationScene) {
        this.exportationInformationScene = exportationInformationScene;
    }

    public void setExportationInformationController(ExportationInformationController exportationInformationController) {
        this.exportationInformationController = exportationInformationController;
    }
    
    public void clearAllExportationFields(){
        companyName.clear();
        pointOfContact.clear();
        email.clear();
        countryCode.clear();
        phoneNumber.clear();
        address.clear();
        city.clear();
        state.clear();
        zipCode.clear();
        date.setValue(LocalDate.now());
        product.clear();
        pricePerUnit.clear();
        quantity.clear();
        invalidTextInTextField.setText("");
        exportation=null;
    }
    
    public void setupAllExportationFields(ExportationModal exportation){
        Instant instant = Instant.ofEpochMilli(exportation.getCurrentDate().getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        
        companyName.setText(exportation.getCompanyName());
        pointOfContact.setText(exportation.getPointOfContact());
        email.setText(exportation.getEmail());
        countryCode.setText(exportation.getCountryCode().toString());
        phoneNumber.setText(exportation.getPhoneNumber().toString());
        address.setText(exportation.getAddress());
        city.setText(exportation.getCity());
        state.setText(exportation.getState());
        zipCode.setText(exportation.getZipCode().toString());
        date.setValue(localDate);
        product.setText(exportation.getProduct());
        pricePerUnit.setText(exportation.getPricePerUnit().toString());
        quantity.setText(exportation.getQuantity().toString());
        invalidTextInTextField.setText("");
        
        this.exportation = exportation;
    }
}
