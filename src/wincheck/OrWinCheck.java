package wincheck;

import unsw.dungeon.Dungeon;
import wincondition.WinCondition;

public class OrWinCheck implements WinCheck{

	@Override
	public boolean canWin(Dungeon dungeon) {
		boolean result = false;
    	for(WinCondition e: dungeon.getChecks()) {
    		result = (result || e.canWin(dungeon));
    	}
    	return result;
	}

}
