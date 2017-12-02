/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ycth8factorymanager;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
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
public class ExportationInformationController implements Initializable {

    @FXML
    private TableView<ExportationModal> exportationTableView;
    @FXML
    private TableColumn<ExportationModal, String> companyNameColumn;
    @FXML
    private TableColumn<ExportationModal, String> pointOfContactColumn;
    @FXML
    private TableColumn<ExportationModal, Integer> countryCodeColumn;
    @FXML
    private TableColumn<ExportationModal, Long> phoneNumberColumn;
    @FXML
    private TableColumn<ExportationModal, String> emailColumn;
    @FXML
    private TableColumn<ExportationModal, String> addressColumn;
    @FXML
    private TableColumn<ExportationModal, String> cityColumn;
    @FXML
    private TableColumn<ExportationModal, String> stateColumn;
    @FXML
    private TableColumn<ExportationModal, Integer> zipCodeColumn;
    @FXML
    private TableColumn<ExportationModal, Date> dateColumn;
    @FXML
    private TableColumn<ExportationModal, String> productColumn;
    @FXML
    private TableColumn<ExportationModal, Double> pricePerUnitColumn;
    @FXML
    private TableColumn<ExportationModal, Integer> quantityColumn;
    @FXML
    private TableColumn<ExportationModal, Double> totalPriceColumn;
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
    private Parent exportationRoot;
    private Scene exportationScene;
    private Scene exportationInformationScene;
    private FXMLLoader exportationLoader;
    private ExportationController exportationController;

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
        if(exportationTableView.getSelectionModel().getSelectedItem() != null){
            
            ExportationModal exportation = new ExportationModal();
            
            exportation.setExportationID(exportationTableView.getSelectionModel().getSelectedItem().getExportationID());
                        
            ExportationBLL exportationBLL = new ExportationBLL(connection);
            
            exportationBLL.deleteExportationRecord(exportation.getExportationID());
            
            fillTable();
        }
    }

    @FXML
    private void clickEditRecordButton(ActionEvent event) {
        
        if(exportationTableView.getSelectionModel().getSelectedItem() != null){
            
            ExportationModal exportation = new ExportationModal();
            
            exportation.setExportationID(exportationTableView.getSelectionModel().getSelectedItem().getExportationID());
            exportation.setRegistrationID(exportationTableView.getSelectionModel().getSelectedItem().getRegistrationID());
            exportation.setCompanyName(exportationTableView.getSelectionModel().getSelectedItem().getCompanyName());
            exportation.setPointOfContact(exportationTableView.getSelectionModel().getSelectedItem().getPointOfContact());
            exportation.setCountryCode(exportationTableView.getSelectionModel().getSelectedItem().getCountryCode());
            exportation.setPhoneNumber(exportationTableView.getSelectionModel().getSelectedItem().getPhoneNumber());
            exportation.setEmail(exportationTableView.getSelectionModel().getSelectedItem().getEmail());
            exportation.setAddress(exportationTableView.getSelectionModel().getSelectedItem().getAddress());
            exportation.setCity(exportationTableView.getSelectionModel().getSelectedItem().getCity());
            exportation.setState(exportationTableView.getSelectionModel().getSelectedItem().getState());
            exportation.setZipCode(exportationTableView.getSelectionModel().getSelectedItem().getZipCode());
            exportation.setCurrentDate(exportationTableView.getSelectionModel().getSelectedItem().getCurrentDate());
            exportation.setProduct(exportationTableView.getSelectionModel().getSelectedItem().getProduct());
            exportation.setPricePerUnit(exportationTableView.getSelectionModel().getSelectedItem().getPricePerUnit());
            exportation.setQuantity(exportationTableView.getSelectionModel().getSelectedItem().getQuantity());
            exportation.setTotalPrice(exportationTableView.getSelectionModel().getSelectedItem().getTotalPrice());
            
            try {
                if(exportationScene == null){
                    exportationLoader = new FXMLLoader(getClass().getResource("Exportation.fxml"));
                    exportationRoot = (Parent)exportationLoader.load();
                    exportationController = exportationLoader.getController();

                    exportationController.setStage(stage);
                    exportationController.setExportationInformationScene(exportationInformationScene);
                    exportationController.setExportationInformationController(this);

                    exportationScene = new Scene(exportationRoot);
                }

                exportationController.setupAllExportationFields(exportation);
                stage.setScene(exportationScene);
                stage.setTitle("Exportation");
                stage.sizeToScene();
                stage.centerOnScreen();

            } catch (IOException ex) {
                Logger.getLogger(ExportationInformationController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }
    }

    @FXML
    private void clickAddRecordButton(ActionEvent event) {
        try {
            if(exportationScene == null){
                exportationLoader = new FXMLLoader(getClass().getResource("Exportation.fxml"));
                exportationRoot = (Parent)exportationLoader.load();
                exportationController = exportationLoader.getController();

                exportationController.setStage(stage);
                exportationController.setExportationInformationScene(exportationInformationScene);
                exportationController.setExportationInformationController(this);

                exportationScene = new Scene(exportationRoot);
            }
            
            exportationController.clearAllExportationFields();
            stage.setScene(exportationScene);
            stage.setTitle("Exportation");
            stage.sizeToScene();
            stage.centerOnScreen();
            
        } catch (IOException ex) {
            Logger.getLogger(ExportationInformationController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void setupView(Stage stage) {
        this.stage = stage;
        this.exportationInformationScene = stage.getScene();
    }
    
    public static void setConnection(Connection connection) {
        ExportationInformationController.connection = connection;
    }
    
    public void fillTable(){
        
        ExportationBLL exportationBLL = new ExportationBLL(connection);
        
        ObservableList<ExportationModal> exportations = exportationBLL.getExportationInventory(User.getRegistrationID());
        
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
        productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        pricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
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
        productColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        pricePerUnitColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        quantityColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        totalPriceColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
                
        pricePerUnitColumn.setCellFactory(tableColumn -> new TableCell<ExportationModal, Double>(){
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
        
        totalPriceColumn.setCellFactory(tableColumn -> new TableCell<ExportationModal, Double>(){
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
        
        exportationTableView.getItems().clear();
        exportationTableView.setItems(exportations);
        exportationTableView.refresh();
    }
}
