package unsw.dungeon;

public interface Subject {
	
	void notifyEntities(String fileName);
	
	void addObserver(Observer obs);

	void deleteObserver(Observer obs);
}
