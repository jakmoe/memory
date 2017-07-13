package game;

import javafx.animation.Interpolator;
import javafx.animation.Transition;

/**
 * @author D067928
 *	Der TransitionRun ist eine Runnable-Implementierung welche es ermöglicht einen Interpolator zur Animation hinzuzufügen.
 *	Der Interpolator erlaubt flüssige Animation durch "Abrunden" der Transitionsverhältnisse der Koordinaten. Für näheres siehe
 *	Dokumentation der Methode setInterpolator();
 */
public class TransitionRun implements Runnable {
	private Transition anim;

	public Transition getAnim() {
		return anim;
	}

	@Override
	public void run() {
		//Dies setzt den Interpolator um Anfang und Ende der Animation abzurunden, sodass kein plötzlicher Start/End entsteht.
		anim.setInterpolator(Interpolator.EASE_BOTH);
		anim.play();
	}

	/**
	 * @param anim - die Transition / Animation auf die die Interpolation angewendet werden soll.
	 */
	public void setAnim(Transition anim) {
		this.anim = anim;
	}

}
