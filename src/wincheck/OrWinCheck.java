package wincheck;

import java.util.ArrayList;

import compositecheck.CompositeCheck;
import compositecheck.NodeCheck;
import unsw.dungeon.Dungeon;
import wincondition.WinCondition;

public class OrWinCheck implements WinCheck{

	/**
	 * Checks if OR conditions are satisfied
	 * @param obj : A NodeCheck object
	 */
	@Override
	public boolean canWin(NodeCheck obj) {
		boolean result = false;
    	for(CompositeCheck e: obj.getSubCheck()) {
    		result = (result || e.check());
    	}
    	return result;
	}

}
