/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WIN
 */
public class DeliveryController implements Initializable {
    
    private static Connection connection;
    
    private Stage stage;
    private Scene deliveryInformationScene;
    private DeliveryInformationController deliveryInformationController;
        
    private DeliveryModal delivery;
    
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
    
    
}
