/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import com.mysql.jdbc.Connection;
import java.net.URL;
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
    private Scene importationInformationScene;
    private ImportationInformationController importationInformationController;
        
    private ImportationModal importation;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickCancelButton(ActionEvent event) {
        stage.setScene(importationInformationScene);
        importationInformationController.fillTable();
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
            if(ImportationController.connection != null){
                
                if(importation != null){
                    
                    ImportationBLL importationBLL = new ImportationBLL(connection);
                    ImportationModal importationForTransfer = new ImportationModal();
                    
                    importationForTransfer.setImportationID(importation.getImportationID());
                    importationForTransfer.setRegistrationID(importation.getRegistrationID());
                    importationForTransfer.setCompanyName(companyName.getText());
                    importationForTransfer.setPointOfContact(pointOfContact.getText());
                    importationForTransfer.setCountryCode(Integer.parseInt(countryCode.getText()));
                    importationForTransfer.setPhoneNumber(Long.parseLong(phoneNumber.getText()));
                    importationForTransfer.setEmail(email.getText());
                    importationForTransfer.setAddress(address.getText());
                    importationForTransfer.setCity(city.getText());
                    importationForTransfer.setState(state.getText());
                    importationForTransfer.setZipCode(Integer.parseInt(zipCode.getText()));
                    importationForTransfer.setCurrentDate(java.sql.Date.valueOf(date.getValue()));
                    importationForTransfer.setProduct(product.getText());
                    importationForTransfer.setPricePerUnit(Double.parseDouble(pricePerUnit.getText()));
                    importationForTransfer.setQuantity(Integer.parseInt(quantity.getText()));
                    importationForTransfer.setTotalPrice(importationForTransfer.calculateTotal());
                    
                    importationBLL.updateImportationRecord(importationForTransfer);
                    
                    importation = null;
                }
                else{
                    ImportationBLL importationBLL = new ImportationBLL(connection);
                    ImportationModal importationForTransfer = new ImportationModal();
                    
//                    importationForTransfer.setImportationID(importation.getImportationID());
                    importationForTransfer.setRegistrationID(User.getRegistrationID());
                    importationForTransfer.setCompanyName(companyName.getText());
                    importationForTransfer.setPointOfContact(pointOfContact.getText());
                    importationForTransfer.setCountryCode(Integer.parseInt(countryCode.getText()));
                    importationForTransfer.setPhoneNumber(Long.parseLong(phoneNumber.getText()));
                    importationForTransfer.setEmail(email.getText());
                    importationForTransfer.setAddress(address.getText());
                    importationForTransfer.setCity(city.getText());
                    importationForTransfer.setState(state.getText());
                    importationForTransfer.setZipCode(Integer.parseInt(zipCode.getText()));
                    importationForTransfer.setCurrentDate(java.sql.Date.valueOf(date.getValue()));
                    importationForTransfer.setProduct(product.getText());
                    importationForTransfer.setPricePerUnit(Double.parseDouble(pricePerUnit.getText()));
                    importationForTransfer.setQuantity(Integer.parseInt(quantity.getText()));
                    importationForTransfer.setTotalPrice(importationForTransfer.calculateTotal());
                    
                    importationBLL.addImportationRecord(importationForTransfer);
                }
                stage.setScene(importationInformationScene);
                importationInformationController.fillTable();
            }
            else{
                invalidTextInTextField.setText("Database Connection Fail!");
            }
        }        
    }

    public static void setConnection(Connection connection) {
        ImportationController.connection = connection;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getImportationInformationScene() {
        return importationInformationScene;
    }

    public void setImportationInformationScene(Scene importationInformationScene) {
        this.importationInformationScene = importationInformationScene;
    }

    public ImportationInformationController getImportationInformationController() {
        return importationInformationController;
    }

    public void setImportationInformationController(ImportationInformationController importationInformationController) {
        this.importationInformationController = importationInformationController;
    }

    public void clearAllImportationFields(){
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
        importation = null;
    }
    
    public void setupAllImportationFields(ImportationModal importation){
        Instant instant = Instant.ofEpochMilli(importation.getCurrentDate().getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        
        companyName.setText(importation.getCompanyName());
        pointOfContact.setText(importation.getPointOfContact());
        email.setText(importation.getEmail());
        countryCode.setText(importation.getCountryCode().toString());
        phoneNumber.setText(importation.getPhoneNumber().toString());
        address.setText(importation.getAddress());
        city.setText(importation.getCity());
        state.setText(importation.getState());
        zipCode.setText(importation.getZipCode().toString());
        date.setValue(localDate);
        product.setText(importation.getProduct());
        pricePerUnit.setText(importation.getPricePerUnit().toString());
        quantity.setText(importation.getQuantity().toString());
        invalidTextInTextField.setText("");
        this.importation = importation;
    }
}
