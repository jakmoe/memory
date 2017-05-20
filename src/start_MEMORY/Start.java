package start_MEMORY;
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
import sound.MP3handler;

/**
 *
 * @author D067928
 */
public class Start extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/FXML_Test.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("Test");
            stage.setResizable(false);
            stage.setMaximized(true);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            
            
            //initialize MP3Handler 
            MP3handler.play(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	launch(args);
        
    }
    
}
