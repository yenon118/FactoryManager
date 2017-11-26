/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    @FXML
    private Button importationButton;
    @FXML
    private Button deliveryButton;
    @FXML
    private Button exportationButton;
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Button refreshButton;
    @FXML
    private Label earningLabel;
    @FXML
    private Label spendingLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private ComboBox<Integer> yearComboBox;
    
    private static Connection connection;
    
    private static Stage importationStage;
    private static Stage exportationStage;
    private static Stage deliveryStage;
    
    private Parent importationInformationRoot;
    private Parent deliveryInformationRoot;
    private Parent exportationInformationRoot;
    
    private Scene importationInformationScene;
    private Scene deliveryInformationScene;
    private Scene exportationInformationScene;
    
    private FXMLLoader importationInformationLoader;
    private FXMLLoader deliveryInformationLoader;
    private FXMLLoader exportationInformationLoader;
    
    
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        generateMainStages();
//        fillComboBox();
//        updateMain(yearComboBox.getSelectionModel().getSelectedItem());
    }    

    @FXML
    private void clickLogOutMenuItem(ActionEvent event) {
        SceneSwitcher.switchScene("Login");
        LoginController loginController = (LoginController) getControllerBySceneName("Login");
        loginController.clearAllLoginTextFields();
        loginController.checkDatabaseConnection();
        destroyMainStages();
    }

    @FXML
    private void clickCloseMenuItem(ActionEvent event) {
        Connection connection;
        Stage stage;
        
        stage = SceneSwitcher.getStage();
        connection = LoginController.getConnection();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Alert on Exiting.");
        alert.setContentText("Are you sure you want to exit?\n\n\n\n\n");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            connection = LoginController.getConnection();
            try {
                if(connection != null || connection.isClosed() == false){
                    connection.close();
                    System.out.println("Database connection is closed.");
                    destroyMainStages();
                    stage.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FactoryManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
        }
    }

    @FXML
    private void clickAboutMenuItem(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Factory Manager");
        alert.setContentText("This application was developed by Yen On Chan for CMP_CS3330 final project.");
        
        VBox vbox = new VBox();
        Label label = new Label();
        label.setText("The Factory Manager is built to help factory manager to manage factory transactions.\n"
                        + "The functionality of this software includes managing inportation records, delivery records, and exportation records.");
        label.setAlignment(Pos.TOP_LEFT);
        label.setPadding(new Insets(10, 10, 10, 10));
        
        alert.setHeight(400);
        alert.setWidth(800);
        vbox.getChildren().addAll(label);
        alert.getDialogPane().setExpandableContent(vbox);
        alert.showAndWait();
    }

    @FXML
    private void clickUserInfoMenuItem(ActionEvent event) {
        User user = new User();
        user.showUserInfo();
    }
    
    @FXML
    private void clickRefreshButton(ActionEvent event) {
        if(yearComboBox.getSelectionModel().getSelectedItem() != null){
            updateMain(yearComboBox.getSelectionModel().getSelectedItem());
            
            int selectedYear = yearComboBox.getSelectionModel().getSelectedItem();
            
            fillComboBox();
            
            yearComboBox.getSelectionModel().select(Integer.valueOf(selectedYear));
        }
    }
    
    @FXML
    private void clickImportationButton(ActionEvent event) {
        if(importationStage.isShowing() == false){
            try {
                importationInformationLoader = new FXMLLoader(getClass().getResource("ImportationInformation.fxml"));
                importationInformationRoot = (Parent)importationInformationLoader.load();
                ImportationInformationController controller = (ImportationInformationController) importationInformationLoader.getController();
                importationInformationScene = new Scene(importationInformationRoot);
                importationStage.setScene(importationInformationScene);
                
                importationStage.setTitle("Importation Information");
                importationStage.show();
                importationStage.centerOnScreen();
                                
                ImportationInformationController.setConnection(connection);
                ImportationController.setConnection(connection);
                
                controller.setupView(importationStage);
                controller.fillTable();
                
                importationStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent WindowEvent) {
                        
                    }
                });

            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void clickDeliveryButton(ActionEvent event) {
        if(deliveryStage.isShowing() == false){
            try {
                deliveryInformationLoader = new FXMLLoader(getClass().getResource("DeliveryInformation.fxml"));
                deliveryInformationRoot = (Parent)deliveryInformationLoader.load();
                DeliveryInformationController controller = (DeliveryInformationController) deliveryInformationLoader.getController();
                deliveryInformationScene = new Scene(deliveryInformationRoot);
                deliveryStage.setScene(deliveryInformationScene);
                
                deliveryStage.setTitle("Delivery Information");
                deliveryStage.show();
                deliveryStage.centerOnScreen();
                
                DeliveryInformationController.setConnection(connection);
                DeliveryController.setConnection(connection);
                
                controller.setupView(deliveryStage);
                controller.fillTable();
                                
                deliveryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent WindowEvent) {
                        
                    }
                });

            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void clickExportationButton(ActionEvent event) {
        if(exportationStage.isShowing() == false){
            try {
                exportationInformationLoader = new FXMLLoader(getClass().getResource("ExportationInformation.fxml"));
                exportationInformationRoot = (Parent) exportationInformationLoader.load();
                ExportationInformationController controller = (ExportationInformationController) exportationInformationLoader.getController();
                exportationInformationScene = new Scene(exportationInformationRoot);
                exportationStage.setScene(exportationInformationScene);
                                
                exportationStage.setTitle("Exportation Information");
                exportationStage.show();
                exportationStage.centerOnScreen();
                
                ExportationInformationController.setConnection(connection);
                ExportationController.setConnection(connection);
                
                controller.setupView(exportationStage);
                controller.fillTable();
                
                exportationStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent WindowEvent) {
                        
                    }
                });

            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    public static boolean generateMainStages(){
        if(importationStage == null){
            importationStage = new Stage();
        }
        if(deliveryStage == null){
            deliveryStage = new Stage();
        }
        if(exportationStage == null){
            exportationStage = new Stage();
        }
        if(importationStage != null && exportationStage != null && deliveryStage != null){
            return true;
        }
        return false;
    }
    
    public static boolean destroyMainStages(){
        
        if(importationStage != null){
            if(importationStage.isShowing()){
                importationStage.close();
            }
            importationStage = null;
        }
        if(deliveryStage != null){
            if(deliveryStage.isShowing()){
                deliveryStage.close();
            }
            deliveryStage = null;
        }
        if(exportationStage != null){
            if(exportationStage.isShowing()){
                exportationStage.close();
            }
            exportationStage = null;
        }
        if(importationStage == null && exportationStage == null && deliveryStage == null){
            return true;
        }
        return false;
    }

    public static void setConnection(Connection connection) {
        MainController.connection = connection;
    }

    public void updateMain(Integer year){
        
        BasicInformationBLL basicInformationBLL = new BasicInformationBLL(connection);
        
        BasicInformationModal basic = basicInformationBLL.processGraphDataByMonth(year);
        
        double[] earning = basic.getEarning();
        double[] spending = basic.getSpending();
        
        XYChart.Series earningSeries = new XYChart.Series();
        earningSeries.setName("Factory Earning");
        
        earningSeries.getData().add(new XYChart.Data("Jan", earning[0]));
        earningSeries.getData().add(new XYChart.Data("Feb", earning[1]));
        earningSeries.getData().add(new XYChart.Data("Mar", earning[2]));
        earningSeries.getData().add(new XYChart.Data("Apr", earning[3]));
        earningSeries.getData().add(new XYChart.Data("May", earning[4]));
        earningSeries.getData().add(new XYChart.Data("Jun", earning[5]));
        earningSeries.getData().add(new XYChart.Data("Jul", earning[6]));
        earningSeries.getData().add(new XYChart.Data("Aug", earning[7]));
        earningSeries.getData().add(new XYChart.Data("Sep", earning[8]));
        earningSeries.getData().add(new XYChart.Data("Oct", earning[9]));
        earningSeries.getData().add(new XYChart.Data("Nov", earning[10]));
        earningSeries.getData().add(new XYChart.Data("Dec", earning[11]));
        
        XYChart.Series spendingSeries = new XYChart.Series();
        spendingSeries.setName("Factory Spending");
        
        spendingSeries.getData().add(new XYChart.Data("Jan", spending[0]));
        spendingSeries.getData().add(new XYChart.Data("Feb", spending[1]));
        spendingSeries.getData().add(new XYChart.Data("Mar", spending[2]));
        spendingSeries.getData().add(new XYChart.Data("Apr", spending[3]));
        spendingSeries.getData().add(new XYChart.Data("May", spending[4]));
        spendingSeries.getData().add(new XYChart.Data("Jun", spending[5]));
        spendingSeries.getData().add(new XYChart.Data("Jul", spending[6]));
        spendingSeries.getData().add(new XYChart.Data("Aug", spending[7]));
        spendingSeries.getData().add(new XYChart.Data("Sep", spending[8]));
        spendingSeries.getData().add(new XYChart.Data("Oct", spending[9]));
        spendingSeries.getData().add(new XYChart.Data("Nov", spending[10]));
        spendingSeries.getData().add(new XYChart.Data("Dec", spending[11]));
        
        lineChart.getData().clear();
        lineChart.getData().addAll(earningSeries, spendingSeries);
        
        earningLabel.setText("Total Earning:  \n" + String.format("%#.2f", basic.getTotalEarning()));
        earningLabel.setTextFill(Color.GREEN);
        
        spendingLabel.setText("Total Spending: \n-" + String.format("%#.2f", basic.getTotalSpending()));
        spendingLabel.setTextFill(Color.RED);
        
        balanceLabel.setText("Balance:        \n" + String.format("%#.2f", basic.getBalance()));
        if(basic.getBalance() < 0){
            balanceLabel.setTextFill(Color.RED);
        }
        else{
            balanceLabel.setTextFill(Color.GREEN);
        }
        
    }
    
    public void fillComboBox(){
        
        BasicInformationBLL basicInformationBLL = new BasicInformationBLL(connection);
        
        ArrayList<Integer> yearList = basicInformationBLL.getYearList();
           
        yearComboBox.getItems().clear();
        yearComboBox.getItems().addAll(yearList);
        
        Calendar calendar = Calendar.getInstance();
        Integer currentYear = calendar.get(Calendar.YEAR);
        
        yearComboBox.getSelectionModel().select(currentYear);
        
    }
    
}
