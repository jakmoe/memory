package game;

import javafx.animation.AnimationTimer;

/**
 * @author D067928
 *	Der AnimationTimer sorgt dafür, dass ein Echtzeitzähler im Spiel verfügbar ist, 
 *	durch den Spielerrundenzeiten gemessen werden können.
 */
public class CustomAnimationTimer extends AnimationTimer {
	private long startTime;
	private double currentTime;

	/**
	 * @return currentTime - gibt die aktuelle Zeit im Timer zurück
	 */
	public double getCurrent() {
		return currentTime;
	}

	@Override
	public void handle(long timestamp) {
		//Setzt die Zeit auf now minus der Systemzeit (Startzeit) und teilt diese durch 1000, da sie in Nanosekunden gemessen wird.
		long now = System.currentTimeMillis();
		currentTime = (now - startTime) / 1000.0;
	}

	@Override
	public void start() {
			startTime = System.currentTimeMillis();
			super.start();
	}
}
