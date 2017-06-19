package fxml_controller;

import java.io.IOException;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import sound.MP3handler;

public class MenuHandler implements EventHandler<MouseEvent> {
	private Node base;

	FXMLLoader loader = new FXMLLoader();
	
	public Node getBase() {
		return base;
	}


	public void setBase(Node base) {
		this.base = base;
	}


	@Override
	public void handle(MouseEvent event) {
		loader.setLocation(getClass().getResource("/fxml/MainMenu/Menu.fxml"));
		try {
			MP3handler.stopbackground();
			Parent root = loader.load();
			base.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
