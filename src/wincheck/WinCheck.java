package wincheck;

import java.util.ArrayList;

import compositecheck.CompositeCheck;

public interface WinCheck {
	
	/**
	 * checks if the dungeon goals have been reached
	 * @param dungeon reference of the dungeon
	 * @return true if all dungeon goals reached, false otherwise
	 */
	boolean canWin(ArrayList<CompositeCheck> SubChecks);
}
