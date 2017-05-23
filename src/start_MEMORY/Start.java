package start_MEMORY;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import XML.XMLhandler;
import image.IMGhandler;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sound.MP3handler;

/**
 * Changes are reported to Slack as well - This is still under Testing
 * 
 * @author Gruppe 6 - Memory DHBW Mannheim
 */
public class Start extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/FXML/Menu.fxml"));
		Parent root = loader.load();

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("Test");
		stage.setResizable(false);
		stage.setMaximized(true);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();

		// initialize MP3Handler
		MP3handler.play(0);

		Task<Void> tk = IMGhandler.initialize(16);
		Thread th = new Thread(tk);
		th.setDaemon(true);
		th.run();
	}

	public static void main(String[] args) {
		XMLhandler.createdoc();
		launch(args);
	}
}
