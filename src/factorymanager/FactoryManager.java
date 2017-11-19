/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author WIN
 */
public class FactoryManager extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Scene scene = new Scene(new Parent() {});
        
        SceneSwitcher.setScene(scene);
        SceneSwitcher.setStage(stage);
        SceneSwitcher.switchScene("Login");
        
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest((WindowEvent windowEvent) -> {
            Connection connection;
            
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
                        MainController.destroyMainStages();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FactoryManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                windowEvent.consume();
            }
        });
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
