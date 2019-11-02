package unsw.dungeon;

/**
 * Observer interface
 */
public interface playerObserver {
	void update(playerSubject player, int dX, int dY);
	void delete();
	boolean isDeleted();
}
