package memory;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author D067928
 */
public class JFXApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLDoc.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("Test");
            stage.setResizable(false);
            stage.setMaximized(true);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	launch(args);
        
    }
    
}
