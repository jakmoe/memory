package fxml_controller;

import java.io.IOException;

import game.ExceptionHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import sound.MP3handler;

/**
 * @author D067928
 * Erstellt einen MenuHandler, verantwortlich um eine Navigation zurück zum Hauptmenü zu organisieren. Dieser ist verpackt
 * in einem Handler
 */
public class MenuHandler implements EventHandler<MouseEvent> {
	private Node base;

	FXMLLoader loader = new FXMLLoader();

	/**
	 * @return base - Die Node auf die das Event geprüft wird
	 */
	public Node getBase() {
		return base;
	}

	/**
	 * @param base - Die Node auf die das Event geprüft wird
	 */
	public void setBase(Node base) {
		this.base = base;
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
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
