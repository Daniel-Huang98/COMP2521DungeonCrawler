package unsw.dungeon;

/**
 * Observer interface
 */
public interface playerObserver {
	/**
	 * Called when a subject is notifying all observers of change
	 * @param player subject reference
	 * @param dX change in coordinate of x position
	 * @param dY change in coordinate of y position
	 */
	void update(playerSubject player, int dX, int dY);
	
	/**
	 * Sets the delete boolean of the observer to be true so it is no longer notified
	 */
	void delete();
	
	/**
	 * Check if the observer can been deleted
	 * @return True is the observer is deleted, false else. 
	 */
	boolean isDeleted();
}
