package fxml_controller;

import java.io.IOException;

import game.ExceptionHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
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
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "Load Error",
					"Something went wrong loading the next screen", "Oops");
			exc.showdialog();
		}
	}

}
