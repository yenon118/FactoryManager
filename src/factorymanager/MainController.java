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
    
    
    private static Stage importationStage;
    private static Stage exportationStage;
    private static Stage deliveryStage;
            
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
                FXMLLoader importationLoader = new FXMLLoader(getClass().getResource("Importation.fxml"));
                Parent importationRoot = (Parent)importationLoader.load();
                ImportationController importationController = (ImportationController)importationLoader.getController();

                Scene importationScene = new Scene(importationRoot);
                importationStage.setScene(importationScene);

                importationStage.show();

//                importationStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                    @Override
//                    public void handle(WindowEvent WindowEvent) {
//                        importationStage.close();
//                    }
//                });

            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void clickDeliveryButton(ActionEvent event) {
        // put your implementation here
    }

    @FXML
    private void clickExportationButton(ActionEvent event) {
        if(exportationStage.isShowing() == false){
            try {
                FXMLLoader exportationLoader = new FXMLLoader(getClass().getResource("Exportation.fxml"));
                Parent exportationRoot = (Parent)exportationLoader.load();
                ExportationController exportationController = (ExportationController)exportationLoader.getController();

                Scene exportationScene = new Scene(exportationRoot);
                exportationStage.setScene(exportationScene);

                exportationStage.show();

//                exportationStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                    @Override
//                    public void handle(WindowEvent WindowEvent) {
//                        exportationStage.close();
//                    }
//                });

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
        }
        if(exportationStage == null){
            exportationStage = new Stage();
        }
        if(deliveryStage == null){
            deliveryStage = new Stage();
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
        if(exportationStage != null){
            if(exportationStage.isShowing()){
                exportationStage.close();
            }
            exportationStage = null;
        }
        if(deliveryStage != null){
            if(deliveryStage.isShowing()){
                deliveryStage.close();
            }
            deliveryStage = null;
        }
        if(importationStage == null && exportationStage == null && deliveryStage == null){
            return true;
        }
        return false;
    }
    
}
