package state;
/*
 * This is an interface for the dungeon statemachine
 */
public interface State {
	void exit();
	void collectGold();
	void killEnemy();
	void activateSwitch();
	void deactivateSwitch();
	void die();
}
