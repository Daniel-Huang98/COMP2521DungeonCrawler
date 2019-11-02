package wincheck;

import unsw.dungeon.Dungeon;
import wincondition.WinCondition;

public class AndWinCheck implements WinCheck{

	@Override
	public boolean canWin(Dungeon dungeon) {
		boolean result = true;
    	for(WinCondition e: dungeon.getChecks()) {
    		result = (result && e.canWin(dungeon));
    	}
    	return result;
	}

}
