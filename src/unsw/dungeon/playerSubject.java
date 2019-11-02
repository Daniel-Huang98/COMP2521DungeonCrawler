package unsw.dungeon;

/**
 * Subject interface
 */
public interface playerSubject {
	void notifyEntities(int dX, int dY);
	void addObserver(playerObserver obs);
	void deleteObserver(playerObserver obs);
}
