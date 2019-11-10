package wincheck;

import java.util.ArrayList;

import compositecheck.CompositeCheck;
import unsw.dungeon.Dungeon;
import wincondition.WinCondition;

public class AndWinCheck implements WinCheck{


	@Override
	public boolean canWin(ArrayList<CompositeCheck> SubChecks) {
		boolean result = true;
    	for(CompositeCheck e: SubChecks) {
    		result = (result && e.check());
    	}
    	return result;
	}

}
