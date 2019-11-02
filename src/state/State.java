package state;

public interface State {
	void exit();
	void collectGold();
	void activateSwitch();
	void deactivateSwitch();
	void die();
}
