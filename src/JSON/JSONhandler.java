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

public class JSONhandler {
    private static final String s = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()
			+ File.separator + "Memory" + File.separator + "MemorySave.json";
    Gson gson = new GsonBuilder()
    	     .serializeNulls()
    	     .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
    	     .setPrettyPrinting()
    	     .setVersion(1.0)
    	     .create();
    
    private JSONModel model = new JSONModel();
    
    public void debug(){
        String jsondebug = gson.toJson(model);
        System.out.println(jsondebug);
        //String jsondebug2 = gson.toJson(model.getPlayer(2));
    }
    
    public void deleteinfo(int id) {
        try (Reader reader = new FileReader(s)) {
        	model = gson.fromJson(reader, JSONModel.class);
        	model.removePlayer(id);
        } catch (IOException e) {
            e.printStackTrace();
		}
	}
    
    public PlayerSave readinfo(int id) {
        try (Reader reader = new FileReader(s)) {
        	model = gson.fromJson(reader, JSONModel.class);
        	if (model.getPlayer(id) == null) {
				writeinfo(PlayerSave.getDummy(id));
			}
            return model.getPlayer(id);
        } catch (IOException e) {
            e.printStackTrace();
		}
        return null;
	}
    
    public void writeinfo(PlayerSave ps) {
    	model.newPlayer(ps);
        try (FileWriter writer = new FileWriter(s)) {
            gson.toJson(model, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
