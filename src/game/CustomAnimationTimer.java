package game;

import javafx.animation.AnimationTimer;

public class CustomAnimationTimer extends AnimationTimer {
    private long startTime ;
    private double currentTime;
    
    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        super.start();
    }

    @Override
    public void handle(long timestamp) {
        long now = System.currentTimeMillis();
        currentTime = (now - startTime) / 1000.0;
    }
    
    public void reset() {
    	currentTime = 0;
    }
    
    public double getCurrent() {
		return currentTime;
    	
    }
		
}
