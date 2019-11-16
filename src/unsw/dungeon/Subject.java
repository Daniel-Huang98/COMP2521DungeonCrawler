package unsw.dungeon;

/**
 * Subject interface for UI elements
 * @author damien
 *
 */
public interface Subject {
	
	void notifyEntities(String fileName);
	
	void addObserver(Observer obs);

	void deleteObserver(Observer obs);
}
