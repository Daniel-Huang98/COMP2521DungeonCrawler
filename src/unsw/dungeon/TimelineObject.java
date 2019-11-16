package unsw.dungeon;
import java.util.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *Timeline to notify enemies and players to move at given interval
 */
public class TimelineObject implements playerSubject{

	Timeline timeline;
	private List<playerObserver>observers;
	
	/**
	 * Constructs a TimelineObject object
	 * @param seconds : amount of seconds between each movement
	 */
	public TimelineObject (double seconds) {
		this.observers = new ArrayList<playerObserver>();
		this.timeline = new Timeline(new KeyFrame(Duration.millis(seconds*1000), e -> this.notifyEntities(0,0)));
		timeline.setCycleCount(Animation.INDEFINITE);
	}
	
	/**
	 * Starts the timeline
	 */
	public void begin() {
		timeline.play();
	}
	
	/**
	 * Stops the timeline
	 */
	public void end() {
		timeline.stop();
	}
	
	@Override
    public void notifyEntities(int dX, int dY) {  	
    	for(playerObserver e: observers) {
    		e.update(this, dX, dY);
    	}
    }
    
    @Override
    public void addObserver(playerObserver obj) {
    	this.observers.add(obj);
    }
    
    @Override
    public void deleteObserver(playerObserver obj){
    	this.observers.remove(obj);
    }
	
}