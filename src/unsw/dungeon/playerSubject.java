package unsw.dungeon;

/**
 * Subject interface
 */
public interface playerSubject {
	/**
	 * Notifies all observers of the subjects change in position
	 * @param dX Change in x direction
	 * @param dY Change in y direction
	 */
	void notifyEntities(int dX, int dY);
	
	/**
	 * adds observer to subject
	 * @param obs observer reference to be added
	 */
	void addObserver(playerObserver obs);
	
	/**
	 * deletes an observer
	 * @param obs observer reference to be deleted
	 */
	void deleteObserver(playerObserver obs);
}
