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

public class JSONhandler {
	private static final String s = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator
			+ "Memory" + File.separator + "MemorySave.json";
	Gson gson = new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).setPrettyPrinting()
			.setVersion(1.0).create();

	private JSONModel model = new JSONModel();

	public void writePlayerinfo(PlayerSave ps) {
		model.updateModel(ps);
		commit();
	}

	public void readVolume() {
		MP3handler.setVolumebg(model.getInfo().getVolume_music());
		MP3handler.setVolumefx(model.getInfo().getVolume_effects());
	}

	public void pull() {
		new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator
				+ "Memory").mkdirs();
		try (Reader reader = new FileReader(s)) {		
			model = gson.fromJson(reader, JSONModel.class);
		} catch (IOException e) {
			commit();
		}
	}

	public void commit() {
		try (FileWriter writer = new FileWriter(s)) {
			gson.toJson(model, writer);
		} catch (IOException e) {			
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "Write Error",
				"Something went wrong with reading or writing the Save.", "Oops");
			exc.showdialog();
		}
	}

	public JSONModel getModel() {
		return model;
	}

	public void setModel(JSONModel model) {
		this.model = model;
	}
}
