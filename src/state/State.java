package state;

public interface State {
	void exit();
	void collectGold();
	void killEnemy();
	void activateSwitch();
	void deactivateSwitch();
	void die();
}
