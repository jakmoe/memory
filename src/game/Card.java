package game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * @author D067928
 * Die Kartenklasse welche die Rectangleform aus JavaFX erweitert. Dies ist sowohl die Anzeige als auch die Backendrepräsentation
 * der Karten, welche im Spiel umgedreht werden können.
 */
public class Card extends Rectangle {
	private boolean turned;
	private boolean matched;
	private boolean animationlock = false;
	private int card_id;

	/**
	 * Erstellt eine Karte zusammen mit einer Weite und Höhe sowie einem Offset.
	 * @param x offset x-Koordinate
	 * @param y offset y-Koordinate
	 * @param width 
	 * @param height
	 */
	public Card(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	
	/**
	 * Füllt die Karte mit einem Bild welches zu einem ImagePattern umkonvertiert wird. Das ImagePattern ist die Repräsentation
	 * eines Bildes (Image), welche auf eine Form (Rectangle, Circle) abgepasst werden kann.
	 * 
	 * @param img - Das Image das im Rectangle angezeigt werden soll.
	 * 
	 * WARNUNG: Set fill ist eine recht komplexe Funktion und sollte wenn möglich nicht in einer Gameloop aufgerufen werden.
	 * Gameloop bezeichnet hier einen aufruf der Methode pro angezeigtem Frame auf dem UI (im JavaFX Umfeld)
	 */
	public void fillCard(Image img) {
		this.setFill(new ImagePattern(img));
	}

	/**
	 * Die Card ID spricht dabei das entsprechende Bild im IMGhandler an. So wird je nach ID ein anderes Bild geladen. 2 Bilder
	 * mit der selben ID können gematched werden, da sie das selbe Bild (bzw. die selbe ID) besitzen.
	 * 
	 * @return card_id - Die Karten ID - wichtig für das angezeigte Bild
	 */
	public int getCard_Id() {
		return card_id;
	}

	
	/**
	 * @return matched - Ist die Karte bereits mit einer anderen gematched worden - Dann ist sie nicht klickbar bzw. ausgegraut
	 */
	public boolean isMatched() {
		return matched;
	}

	/**
	 * @return turned - Ist die Karte umgedreht? Dann muss die Karte im Spiel anders behandelt werden. Näheres im GameEventhandler
	 */
	public boolean isTurned() {
		return turned;
	}

	/**
	 * Die Card ID spricht dabei das entsprechende Bild im IMGhandler an. So wird je nach ID ein anderes Bild geladen. 2 Bilder
	 * mit der selben ID können gematched werden, da sie das selbe Bild (bzw. die selbe ID) besitzen.
	 * 
	 * @param card_id - Setzt die Karten ID - wichtig für das angezeigte Bild
	 */
	public void setCard_Id(int card_id) {
		this.card_id = card_id;
	}

	/**
	 * @param matched - Setzt die Karte auf matched oder nicht matched - auf True zu setzen, falls die Karte mit seinem Äquivalent
	 * gepaart wurde.
	 */
	public void setMatched(boolean matched) {
		this.matched = matched;
	}

	/**
	 * @param turned - Setzt die Karte auf turned oder nicht turned - auf True zu setzen, falls die Karte als umgedreht (sichtbar)
	 * angezeigt werden soll
	 */
	public void setTurned(boolean turned) {
		this.turned = turned;
	}

	/**
	 * Karten müssen gelocked sein, damit der User nicht die Karte in einer Animation zum Beispiel vom umdrehen, nochmal umdrehen
	 * kann. Dies würde dazu führen, dass die Karten auf dem UI falsch angezeigt werden, da es Synchronisationsprobleme
	 * mit dem Erfassen der Koordination für die Animation gibt.
	 * 
	 * @return animationlock - Zeigt an ob die Karte momentan in einer Animation "gelocked" / "gefangen" ist. Sie ist dann
	 * unnerreichbar für jedwede Userinteraktion.
	 */
	public boolean inAnimation() {
		return animationlock;
	}

	
	/**
	 *  Locked die Karte. Wird aufgerufen wenn die Karte in einer Animation ist.
	 */
	public void lock() {
		this.animationlock = true;
	}

	/**
	 * Unlocked die Karte. Wird aufgerufen sobald die Karte nicht mehr in einer Animation ist.
	 */
	public void unlock() {
		this.animationlock = false;
	}
}
