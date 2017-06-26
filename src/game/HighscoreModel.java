package game;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HighscoreModel {
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty mintime;
    private final SimpleIntegerProperty highscore;
    private final SimpleIntegerProperty attempts;
    private final SimpleBooleanProperty sp;
    
	public HighscoreModel( String name, double mintime, int highscore, int attempts, boolean sp) {
    	this.name = new SimpleStringProperty(name);
    	this.mintime = new SimpleDoubleProperty(mintime);
    	this.highscore = new SimpleIntegerProperty(highscore);
    	this.attempts = new SimpleIntegerProperty(attempts);
    	this.sp = new SimpleBooleanProperty(sp);
    }

	public String getName() {
		return name.get();
	}

	public Double getMintime() {
		return mintime.get();
	}

	public Integer getHighscore() {
		return highscore.get();
	}

	public Integer getAttempts() {
		return attempts.get();
	}

	public String getSp() {
		if (sp.get()) {
			return "X";
		} else {
			return "";
		}
	}
 
}
