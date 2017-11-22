/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
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
public class ImportationInformationController implements Initializable {

    
    @FXML
    private TableView<ImportationModal> importationTableView;
    @FXML
    private TableColumn<ImportationModal, String> companyNameColumn;
    @FXML
    private TableColumn<ImportationModal, String> pointOfContactColumn;
    @FXML
    private TableColumn<ImportationModal, Integer> countryCodeColumn;
    @FXML
    private TableColumn<ImportationModal, Long> phoneNumberColumn;
    @FXML
    private TableColumn<ImportationModal, String> emailColumn;
    @FXML
    private TableColumn<ImportationModal, String> addressColumn;
    @FXML
    private TableColumn<ImportationModal, String> cityColumn;
    @FXML
    private TableColumn<ImportationModal, String> stateColumn;
    @FXML
    private TableColumn<ImportationModal, Integer> zipCodeColumn;
    @FXML
    private TableColumn<ImportationModal, Date> dateColumn;
    @FXML
    private TableColumn<ImportationModal, String> productColumn;
    @FXML
    private TableColumn<ImportationModal, Double> pricePerUnitColumn;
    @FXML
    private TableColumn<ImportationModal, Integer> quantityColumn;
    @FXML
    private TableColumn<ImportationModal, Double> totalPriceColumn;
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
    private Parent importationRoot;
    private Scene importationScene;
    private Scene importationInformationScene;
    private FXMLLoader importationLoader;
    private ImportationController importationController;
    
    
        
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
        
        if(importationTableView.getSelectionModel().getSelectedItem() != null){
            
            ImportationModal importation = new ImportationModal();
            
            importation.setImportationID(importationTableView.getSelectionModel().getSelectedItem().getImportationID());
                        
            ImportationBLL importationBLL = new ImportationBLL(connection);
            
            importationBLL.deleteImportationRecord(importation.getImportationID());
            
            fillTable();
        }
    }

    @FXML
    private void clickEditRecordButton(ActionEvent event) {
                
        if(importationTableView.getSelectionModel().getSelectedItem() != null){
            
            ImportationModal importation = new ImportationModal();
            
            importation.setImportationID(importationTableView.getSelectionModel().getSelectedItem().getImportationID());
            importation.setRegistrationID(importationTableView.getSelectionModel().getSelectedItem().getRegistrationID());
            importation.setCompanyName(importationTableView.getSelectionModel().getSelectedItem().getCompanyName());
            importation.setPointOfContact(importationTableView.getSelectionModel().getSelectedItem().getPointOfContact());
            importation.setCountryCode(importationTableView.getSelectionModel().getSelectedItem().getCountryCode());
            importation.setPhoneNumber(importationTableView.getSelectionModel().getSelectedItem().getPhoneNumber());
            importation.setEmail(importationTableView.getSelectionModel().getSelectedItem().getEmail());
            importation.setAddress(importationTableView.getSelectionModel().getSelectedItem().getAddress());
            importation.setCity(importationTableView.getSelectionModel().getSelectedItem().getCity());
            importation.setState(importationTableView.getSelectionModel().getSelectedItem().getState());
            importation.setZipCode(importationTableView.getSelectionModel().getSelectedItem().getZipCode());
            importation.setCurrentDate(importationTableView.getSelectionModel().getSelectedItem().getCurrentDate());
            importation.setProduct(importationTableView.getSelectionModel().getSelectedItem().getProduct());
            importation.setPricePerUnit(importationTableView.getSelectionModel().getSelectedItem().getPricePerUnit());
            importation.setQuantity(importationTableView.getSelectionModel().getSelectedItem().getQuantity());
            importation.setTotalPrice(importationTableView.getSelectionModel().getSelectedItem().getTotalPrice());
            
            try {
                if(importationScene == null){
                    importationLoader = new FXMLLoader(getClass().getResource("Importation.fxml"));
                    importationRoot = (Parent)importationLoader.load();
                    importationController = importationLoader.getController();

                    importationController.setStage(stage);
                    importationController.setImportationInformationScene(importationInformationScene);
                    importationController.setImportationInformationController(this);

                    importationScene = new Scene(importationRoot);
                }

                importationController.setupAllImportationFields(importation);
                stage.setScene(importationScene);
                stage.setTitle("Importation");
                stage.sizeToScene();
                stage.centerOnScreen();

            } catch (IOException ex) {
                Logger.getLogger(ImportationInformationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            fillTable();
        }
    }
    
    @FXML
    private void clickAddRecordButton(ActionEvent event) {
        try {
            if(importationScene == null){
                importationLoader = new FXMLLoader(getClass().getResource("Importation.fxml"));
                importationRoot = (Parent)importationLoader.load();
                importationController = importationLoader.getController();

                importationController.setStage(stage);
                importationController.setImportationInformationScene(importationInformationScene);
                importationController.setImportationInformationController(this);

                importationScene = new Scene(importationRoot);
            }
            
            importationController.clearAllImportationFields();
            stage.setScene(importationScene);
            stage.setTitle("Importation");
            stage.sizeToScene();
            stage.centerOnScreen();
            
        } catch (IOException ex) {
            Logger.getLogger(ImportationInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void setupView(Stage stage) {
        this.stage = stage;
        this.importationInformationScene = stage.getScene();
    }

    public static void setConnection(Connection connection) {
        ImportationInformationController.connection = connection;
    }
    
    public void fillTable(){
        
        ImportationBLL importationBLL = new ImportationBLL(connection);
        
        ObservableList<ImportationModal> importations = importationBLL.getImportationInventory(User.getRegistrationID());
        
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
        
//        deleteColumn.setCellFactory(new Callback<TableColumn<ImportationModal, Boolean>, TableCell<ImportationModal, Boolean>>() {
//            @Override
//            public TableCell<ImportationModal, Boolean> call(TableColumn<ImportationModal, Boolean> param) {
//                return new DeleteTableCell(stage, importationTableView);
//            }
//        });
//        
//        editColumn.setCellFactory(new Callback<TableColumn<ImportationModal, Boolean>, TableCell<ImportationModal, Boolean>>() {
//            @Override
//            public TableCell<ImportationModal, Boolean> call(TableColumn<ImportationModal, Boolean> param) {
//                return new EditTableCell(stage, importationTableView);
//            }
//        });
        
        pricePerUnitColumn.setCellFactory(tableColumn -> new TableCell<ImportationModal, Double>(){
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
        
        totalPriceColumn.setCellFactory(tableColumn -> new TableCell<ImportationModal, Double>(){
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
        
//        importationTableView.setOnMousePressed(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                columnNumber = importationTableView.getSelectionModel().getSelectedIndex();
//            }
//        });
        
        importationTableView.getItems().clear();
        importationTableView.setItems(importations);
        importationTableView.refresh();
        
    }
    
    
}
