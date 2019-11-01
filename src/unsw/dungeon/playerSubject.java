package unsw.dungeon;

public interface playerSubject {
	void notifyEntities(int dX, int dY);
	void deleteObserver(playerObserver obj);
}
