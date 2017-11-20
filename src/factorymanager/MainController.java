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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
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
    
    
    private static Connection connection;
    
    private static Stage importationStage;
    private static Stage exportationStage;
    private static Stage deliveryStage;
    
    private static Parent importationRoot;
    private static Parent deliveryRoot;
    private static Parent exportationRoot;
    
    private static Scene importationScene;
    private static Scene deliveryScene;
    private static Scene exportationScene;
    
    private static FXMLLoader importationLoader;
    private static FXMLLoader deliveryLoader;
    private static FXMLLoader exportationLoader;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        generateMainStages();
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
    private void clickImportationButton(ActionEvent event) {
        if(importationStage.isShowing() == false){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ImportationInformation.fxml"));
                Parent root = (Parent)loader.load();
                importationLoader = loader;
                importationRoot = root;

                importationScene.setRoot(importationRoot);
                importationStage.setScene(importationScene);
                importationStage.setTitle("Importation Information");
                importationStage.show();
                importationStage.centerOnScreen();
                                
                ImportationInformationController.setupView(importationStage, importationRoot, importationScene);
                ImportationController.setupView(importationStage, importationScene);
                ImportationInformationController.setConnection(connection);
                ImportationController.setConnection(connection);
                
                ImportationInformationController importationInformationController = (ImportationInformationController) importationLoader.getController();
                importationInformationController.fillTable();
                
                ImportationController.setImportationInformationController(importationInformationController);
                                
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ImportationInformation.fxml"));
                Parent root = (Parent)loader.load();
                deliveryLoader = loader;
                deliveryRoot = root;
//                deliveryLoader.getClass().getResource("Exportation.fxml");
//                deliveryRoot = (Parent)FXMLLoader.load(getClass().getResource("Exportation.fxml"));
                
                deliveryScene.setRoot(deliveryRoot);
                deliveryStage.setScene(deliveryScene);
                deliveryStage.setTitle("Delivery Information");
                deliveryStage.show();
                deliveryStage.centerOnScreen();
                
//                DeliveryInformationController.setupView(deliveryStage, deliveryRoot, deliveryScene);
//                DeliveryController.setupView(deliveryStage, deliveryRoot, deliveryScene);
                
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ImportationInformation.fxml"));
                Parent root = (Parent)loader.load();
                exportationLoader = loader;
                exportationRoot = root;
//                exportationLoader.getClass().getResource("Exportation.fxml");
//                exportationRoot = (Parent)FXMLLoader.load(getClass().getResource("Exportation.fxml"));
                
                exportationScene.setRoot(exportationRoot);
                exportationStage.setScene(exportationScene);
                exportationStage.setTitle("Exportation Information");
                exportationStage.show();
                exportationStage.centerOnScreen();
                
//                ExportationInformationController.setupView(exportationStage, exportationRoot, exportationScene);
//                ExportationController.setupView(exportationStage, exportationRoot, exportationScene);
                
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

    public Stage getImportationStage() {
        return importationStage;
    }

    public Stage getExportationStage() {
        return exportationStage;
    }
    
    public static boolean generateMainStages(){
        if(importationStage == null){
            importationStage = new Stage();
            importationLoader = new FXMLLoader();
            if(importationRoot == null){
                importationRoot = new Parent() {};
            }
            if(importationScene == null){
                importationScene = new Scene(importationRoot);
            }
            importationStage.setScene(importationScene);
        }
        if(deliveryStage == null){
            deliveryStage = new Stage();
            deliveryLoader = new FXMLLoader();
            if(deliveryRoot == null){
                deliveryRoot = new Parent() {};
            }
            if(deliveryScene == null){
                deliveryScene = new Scene(deliveryRoot);
            }
            deliveryStage.setScene(deliveryScene);
        }
        if(exportationStage == null){
            exportationStage = new Stage();
            exportationLoader = new FXMLLoader();
            if(exportationRoot == null){
                exportationRoot = new Parent() {};
            }
            if(exportationScene == null){
                exportationScene = new Scene(exportationRoot);
            }
            exportationStage.setScene(exportationScene);
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
            if(importationLoader != null){
                importationLoader = null;
            }
            if(importationScene != null){
                importationScene = null;
            }
            if(importationRoot != null){
                importationRoot = null;
            }
            importationStage = null;
        }
        if(deliveryStage != null){
            if(deliveryStage.isShowing()){
                deliveryStage.close();
            }
            if(deliveryLoader != null){
                deliveryLoader = null;
            }
            if(deliveryScene != null){
                deliveryScene = null;
            }
            if(deliveryRoot != null){
                deliveryRoot = null;
            }
            deliveryStage = null;
        }
        if(exportationStage != null){
            if(exportationStage.isShowing()){
                exportationStage.close();
            }
            if(exportationLoader != null){
                exportationLoader = null;
            }
            if(exportationScene != null){
                exportationScene = null;
            }
            if(exportationRoot != null){
                exportationRoot = null;
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
    
}
