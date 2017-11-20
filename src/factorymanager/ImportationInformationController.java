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
    private TableColumn<?, ?> deleteColumn;
    @FXML
    private TableColumn<?, ?> editColumn;
    @FXML
    private TableColumn<ImportationModal, String> companyNameColumn;
    @FXML
    private TableColumn<ImportationModal, String> pointOfContactColumn;
    @FXML
    private TableColumn<ImportationModal, Integer> countryCodeColumn;
    @FXML
    private TableColumn<ImportationModal, Integer> phoneNumberColumn;
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
    private TableColumn<ImportationModal, String> quantityColumn;
    @FXML
    private TableColumn<ImportationModal, Double> totalPriceColumn;
    @FXML
    private Button exitButton;
    @FXML
    private Button addRecordButton;
    
    
    private static Connection connection;
    
    private static Stage stage;
    private static Parent root;
    private static Scene scene;
    private static FXMLLoader loader;
    
    
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
    private void clickAddRecordButton(ActionEvent event) {
        try {
            root = (Parent)FXMLLoader.load(getClass().getResource("Importation.fxml"));
            
            scene.setRoot(root);
            stage.setScene(scene);
            stage.setTitle("Importation");
            stage.sizeToScene();
            stage.centerOnScreen();
                        
            
        } catch (IOException ex) {
            Logger.getLogger(ImportationInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setupView(Stage stage, Parent root, Scene scene) {
        ImportationInformationController.stage = stage;
        ImportationInformationController.scene = scene;
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
        
        importationTableView.setItems(importations);
        
        
        
    }
    
}
