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

import sound.MP3handler;

public class JSONhandler {
	private static final String s = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator
			+ "Memory" + File.separator + "MemorySave.json";
	Gson gson = new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).setPrettyPrinting()
			.setVersion(1.0).create();

	private JSONModel model = new JSONModel();

	public void deleteinfo(int id) {
		try (Reader reader = new FileReader(s)) {
			model = gson.fromJson(reader, JSONModel.class);
			model.removePlayer(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PlayerSave readPlayerinfo(int id) {
		if (model.getPlayer(id) == null) {
			writePlayerinfo(PlayerSave.getDummy(id));
		}
		return model.getPlayer(id);
	}

	public void writePlayerinfo(PlayerSave ps) {
		model.newPlayer(ps);
		commit();
	}

	public void readVolume() {
		MP3handler.setVolumebg(model.getInfo().getVolume_music());
		MP3handler.setVolumefx(model.getInfo().getVolume_effects());
	}

	public void pull() {
		try (Reader reader = new FileReader(s)) {
			model = gson.fromJson(reader, JSONModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void commit() {
		try (FileWriter writer = new FileWriter(s)) {
			gson.toJson(model, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JSONModel getModel() {
		return model;
	}

	public void setModel(JSONModel model) {
		this.model = model;
	}
}
