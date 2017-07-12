package game;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;

/**
 * @author D067928
 * Der ExceptionHandler ist dafür verantwortlich Fehlernachrichten sowie den Stack, als der Fehler auftrat, in einem
 * passend dargestellten User Interface darzustellen. Je nach Fehler ist ein anderer Kontext zu füllen.
 */
public class ExceptionHandler {
	Exception ex = null;
	Alert alert = new Alert(AlertType.ERROR);

	/**
	 * Dies ist der Konstruktor für den ExceptionHandler. Er sollte im Idealfall in einer Catch-Bedingung eines Try-Catch-Blocks
	 * aufgerufen werden. So ist sichergestellt, dass entsprechende Fehler im aktuellen Stacklevel abgefangen werden.
	 * @param exception - Die mitgegebene Ausnahme (erstellt vom Try-Catch-Block)
	 * @param title - Der Titel des Anzeigefensters
	 * @param header - Die Überschrift des Anzeigefensters
	 * @param content - Der Inhalt (die Beschreibung des Fensters)
	 * @param message - Die Nachricht, die mit der Error Message ausgegeben wird.
	 */
	public ExceptionHandler(Exception ex, String title, String header, String content, String message) {
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		// Erstellt dynamisch skalierbare Ausnahme im UI.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label(message);

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);
		alert.initModality(Modality.NONE);
	}

	/**
	 * Wenn diese Methode aufgerufen wird, dann wird die zugehörige Exception der ExceptionHandler-Instanz gecalled und auf dem UI
	 * als nonmodales Fenster angezeigt (Nonmodal = kein "Parent"-Fenster, unabhängig vom Rest des Spiels).
	 * @throws NullPointerException
	 */
	public void showdialog() throws NullPointerException {
		if (alert != null) {
			alert.showAndWait();
		} else {
			/*Falls Beim Erstellen des Errorfensters etwas fehlgeschlagen ist, wird eine NullPointerException ausgelöst.
			Dies ist der einzige Errorfall, der vom Spiel nicht abgefangen und auf der Konsole ausgegeben wird.*/
			NullPointerException np = new NullPointerException("Something went wrong while creating the Error dialog!");
			throw np;
		}
	}
}
