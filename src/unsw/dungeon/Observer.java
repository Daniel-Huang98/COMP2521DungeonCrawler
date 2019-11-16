package unsw.dungeon;

/**
 * Observer interface for UI elements
 * @author damien
 *
 */
public interface Observer {

	void update(Subject obj, String fileName);
}
