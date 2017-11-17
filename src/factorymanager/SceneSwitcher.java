/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author WIN
 */
public abstract class SceneSwitcher {
    
    private Parent root;
    private static Scene scene;
    private static final HashMap<String, SceneSwitcher> controllers = new HashMap<>();
    
    public static void switchScene(String sceneName) {
        SceneSwitcher controller = controllers.get(sceneName);
        
        if(controller == null){
            controller = add(sceneName);
            if(scene != null){
                scene.setRoot(controller.getRoot());
            }
        }
        else{
            if(scene != null){
                scene.setRoot(controller.getRoot());
            }
        }
    }
    
    public static SceneSwitcher add(String sceneName) {
        SceneSwitcher controller = controllers.get(sceneName);
        
        if(controller == null){
            try {
                FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(sceneName + ".fxml"));
                Parent root = loader.load();
                
                controller = loader.getController();
                
                controller.setRoot(root);
                
                controllers.put(sceneName, controller);
                
            } catch (IOException ex) {
                Logger.getLogger(SceneSwitcher.class.getName()).log(Level.SEVERE, null, ex);
                controller = null;
            }
        }
        return controller;
    }
    
    public void setRoot(Parent root) {
        this.root = root;
    }
    
    public Parent getRoot() {
        return root;
    }

    public static void setScene(Scene scene) {
        SceneSwitcher.scene = scene;
    }

    public static SceneSwitcher getControllerBySceneName(String sceneName) {
        return controllers.get(sceneName);
    }
    
}
