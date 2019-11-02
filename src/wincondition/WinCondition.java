package wincondition;

import unsw.dungeon.Dungeon;

public interface WinCondition {
	
	/**
	 * checks if a certain sub goal condition of the dungeon has been reached
	 * @param dungeon reference of the dungeon
	 * @return return if subgoal is complete, false otherwise.
	 */
	boolean canWin(Dungeon dungeon);
}
