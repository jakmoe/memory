package JSON;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import javax.swing.filechooser.FileSystemView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import game.ExceptionHandler;
import sound.MP3handler;

/**
 * @author D067928
 *	Der JSONhandler übernimmt das Verwalten des Savegames durch benutzen der Gson Bibliothek. So können Savegames der Spieler auch
 *  bei einem Neustart des Spiels erhalten werden.
 */
public class JSONhandler {
	// Dies ist der Folderstandort in dem die Savegames gespeichert werden. Unter Windwos: MyDocuments. Platformunabhängig durch
	// Verwendung von File.seperator und getDefaultDirectory()
	private static final String s = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator
			+ "Memory" + File.separator + "MemorySave.json";
	
	// Erstellt ein gson, welches mit einer Standardeinstellungen geladen wird und auf PrettyPrinting geschaltet wird um das Save
	// auch außerhalb der Anwendung lesen zu können
	Gson gson = new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).setPrettyPrinting()
			.setVersion(1.0).create();

	// Erstellt das zu speichernde Modell. Das Modell als Klasse wird nachher 1:1 von gson in das JSON Dokument gespeichert bzw. geladen
	private JSONModel model = new JSONModel();

	/**
	 * Eine ConvenienceMethod um nicht auf das Model gehen zu müssen. Commit() sorgt für Speicherung.
	 * @param ps - PlayerSave welches gespeichert werden soll.
	 */
	public void writePlayerinfo(PlayerSave ps) {
		model.updateModel(ps);
		commit();
	}

	/**
	 * Liest die Lautstärkewerte vom letzen Spielstart ab. Ermöglicht außerdem Speichern und abrufen der Lautstärke bei Menüwechseln.
	 */
	public void readVolume() {
		MP3handler.setVolumebg(model.getInfo().getVolume_music());
		MP3handler.setVolumefx(model.getInfo().getVolume_effects());
	}

	/**
	 * Lädt das gespeicherte Savegame aus dem Dokument in das Model.
	 */
	public void pull() {
		new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator
				+ "Memory").mkdirs();
		try (Reader reader = new FileReader(s)) {		
			model = gson.fromJson(reader, JSONModel.class);
		} catch (IOException e) {
			//Falls das Dokument noch nicht existiert wird ein leeres Dokument gespeichert.
			commit();
		}
	}

	/**
	 * Das Model wird in das JSON Dokument gespeichert.
	 */
	public void commit() {
		try (FileWriter writer = new FileWriter(s)) {
			gson.toJson(model, writer);
		} catch (IOException e) {			
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "Write Error",
				"Something went wrong with reading or writing the Save.", "Oops");
			exc.showdialog();
		}
	}

	/**
	 * @return model - gibt das Model zurück
	 */
	public JSONModel getModel() {
		return model;
	}

	/**
	 * @param model - setzt das Model.
	 */
	public void setModel(JSONModel model) {
		this.model = model;
	}
}
