package game;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author D067928
 *	Dies ist das HighscoreModel in das Daten des PlayerSave geladen und in die Tabelle geschrieben werden.
 *	Da Tabellen in JavaFX nur Propertywerte entgegen nehmen, besteht die Klasse ausschließlich aus diesen.
 */
public class HighscoreModel {
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty mintime;
    private final SimpleIntegerProperty highscore;
    private final SimpleIntegerProperty attempts;
    private final SimpleBooleanProperty sp;
    
	/**
	 * Standardkonstruktor für ein HighscoreModel
	 * @param name
	 * @param mintime
	 * @param highscore
	 * @param attempts
	 * @param sp
	 */
	public HighscoreModel( String name, double mintime, int highscore, int attempts, boolean sp) {
    	this.name = new SimpleStringProperty(name);
    	this.mintime = new SimpleDoubleProperty(Math.floor(mintime));
    	this.highscore = new SimpleIntegerProperty(highscore);
    	this.attempts = new SimpleIntegerProperty(attempts);
    	this.sp = new SimpleBooleanProperty(sp);
    }

	/**
	 * @return Name
	 */
	public String getName() {
		return name.get();
	}

	/**
	 * @return Mintime
	 */
	public Double getMintime() {
		return mintime.get();
	}

	/**
	 * @return Highscore
	 */
	public Integer getHighscore() {
		return highscore.get();
	}

	/**
	 * @return Attempts
	 */
	public Integer getAttempts() {
		return attempts.get();
	}

	/**
	 * @return Sp - "X" falls im Einzelspieler erstelltes Save, "" falls im Mehrspieler
	 */
	public String getSp() {
		if (sp.get()) {
			return "X";
		} else {
			return "";
		}
	}
 
}
