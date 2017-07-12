package image;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import game.ExceptionHandler;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * @author D067928
 *	Dies ist der IMGhandler, welcher für alle Funktionen des Spieles Bilder und Grafiken zur Verfügung stellt
 */
public class IMGhandler {

	private static ArrayList<ImagePattern> images = new ArrayList<ImagePattern>();
	private static int theme = 1;

	/**
	 * @return Backgroundimage - gibt das Hintergrundbild je nach theme zurück. Standardmäßig light.jpg
	 */
	public static Image getGameBackground() {
		URL url = null;
		switch (theme) {
		case 1:
			url = IMGhandler.class.getResource("/image/background/light.jpg");
			break;
		case 2:
			url = IMGhandler.class.getResource("/image/background/dark.jpg");
			break;
		case 3:
			url = IMGhandler.class.getResource("/image/background/cute.jpg");
			break;
		default:
			break;
		}
		// Gibt Hintergrund in Full HD ohne Resizing mit persistenter(gleichbleibender) Ratio(16:9) zurück.
		Image img = new Image(url.toString(), 1920, 1080, true, true);
		return img;
	}

	/**
	 * @return gibt den Winscreen als Bild zurück
	 */
	public static Image getWinScreen() {
		URL url = IMGhandler.class.getResource("/image/winscreen/win.png");
		Image img = new Image(url.toString(), 1920, 1080, true, true);
		return img;
	}

	/**
	 * @param active - Ist der Spieler an der Reihe?
	 * @return - gibt das entsprechende Icon zurück, je nachdem ob der Spieler an der Reihe ist oder nicht.
	 */
	public static ImagePattern getPlayer(boolean active) {
		URL url = null;
		if (active) {
			url = IMGhandler.class.getResource("/image/player/player_x.png");
		} else {
			url = IMGhandler.class.getResource("/image/player/player.png");
		}
		Image img = new Image(url.toString());
		return new ImagePattern(img);
	}

	/**
	 * @param id - Die Karten-ID entspricht der Bild-ID
	 * @return - gibt das Bild als ImagePattern zurück.
	 */
	public static ImagePattern getImage_card(int id) {
		return images.get(id);
	}

	/**
	 * Initialisiert die Karten indem es alle Bilder lädt, die für das Spiel gebraucht werden
	 * @param cardcount - Anzahl der Karten(Paare)
	 * @return gibt eine Task für Multithreading zurück, welche es erlaubt die Karten parallel laden und in einer Progressbar
	 * darstellen zu lassen
	 */
	public static Task<Void> initialize(int cardcount) {
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				// Background work
				int i;
				for (i = 0; i < 38; i++) {
					//falls der Task abgebrochen werden sollte, wird aus der Schleife gesprungen um Fehler zu vermeiden.
					if (isCancelled()) {
						break;
					}
					URL url = IMGhandler.class.getResource("/image/cards/" + i + ".jpg");
					Image img = new Image(url.toURI().toString(), 500, 500, true, true);
					ImagePattern imgp = new ImagePattern(img);
					images.add(imgp);
					//aktualisiert den Fortschritt des Tasks, aktuell: i, maximum: cardcount
					updateProgress(i, cardcount);
				}
				return null;
			}
		};
		return task;
	}

	/**
	 * @return theme - gibt das aktuelle Theme zurück (als Integerwert)
	 */
	public static int getTheme() {
		return theme;
	}

	/**
	 * @param theme - setzt das neue aktuelle Theme (als Integerwert)
	 */
	public static void setTheme(int theme) {
		IMGhandler.theme = theme;
	}

	/**
	 * gibt den Pfeil als Bild für die Menüführung zurück.
	 * @param width
	 * @param height
	 * @param preserveRatio
	 * @return Image - entsprechender Pfeil
	 */
	public static Image getArrow(double width, double height, boolean preserveRatio) {
		URL url = null;
		Image img = null;
		url = IMGhandler.class.getResource("/image/navigation/arrow_left.png");
		try {
			img = new Image(url.toURI().toString(), width, height, preserveRatio, true);
		} catch (URISyntaxException e) {
			ExceptionHandler exceptionHandler = new ExceptionHandler(e, "Error", "URI Conversion", "URI conversion failed", "Oops");
			exceptionHandler.showdialog();
		}
		return img;
	}

	/**
	 * gibt den Hintergrund des Settingsscreen zurück.
	 * @return Image - settings.png
	 */
	public static Image getSettings() {
		URL url = IMGhandler.class.getResource("/image/navigation/settings.png");
		try {
			Image img = new Image(url.toURI().toString(), 50, 50, true, true);
			return img;
		} catch (URISyntaxException e) {
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "URI Conversion",
					"URI conversion failed", "Oops");
			exc.showdialog();
		}
		return null;
	}

	/**
	 * Gibt die Bilder für die Siegerplatzierung zurück.
	 * @param id - Nummer von 1-4, Erster bis letzter Platz
	 * @return Image - Entsprechendes Bild
	 */
	public static Image getWinNumber(int id) {
		URL url = null;
		Image img = null;

		switch (id) {
		case 1:
			url = IMGhandler.class.getResource("/image/winscreen/1.png");
			break;
		case 2:
			url = IMGhandler.class.getResource("/image/winscreen/2.png");
			break;
		case 3:
			url = IMGhandler.class.getResource("/image/winscreen/3.png");
			break;
		case 4:
			url = IMGhandler.class.getResource("/image/winscreen/4.png");
			break;
		default:
			break;
		}
		try {
			img = new Image(url.toURI().toString(), 100, 100, true, true);
		} catch (URISyntaxException e) {
			ExceptionHandler exceptionHandler = new ExceptionHandler(e, "Error", "URI Conversion", "URI conversion failed", "Oops");
			exceptionHandler.showdialog();
		}
		return img;
	}

	/**
	 * Gibt einen Balken für den Winstack zurück (Jeder Balken für ein Kartenpaar)
	 * @return Image - Stack.jpg 
	 */
	public static Image getStack() {
		URL url = IMGhandler.class.getResource("/image/winscreen/stack.jpg");
		try {
			Image img = new Image(url.toURI().toString(), 50, 50, true, true);
			return img;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
