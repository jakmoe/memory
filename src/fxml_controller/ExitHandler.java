package fxml_controller;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class ExitHandler implements EventHandler<MouseEvent> {
	private Node base;
	
	
	public Node getBase() {
		return base;
	}


	public void setBase(Node base) {
		this.base = base;
	}


	@Override
	public void handle(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Do you really want to exit the program?");
		alert.initStyle(StageStyle.TRANSPARENT);
		alert.initOwner(base.getScene().getWindow());
		alert.initModality(Modality.WINDOW_MODAL);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			System.exit(0);
		}
	}

}
