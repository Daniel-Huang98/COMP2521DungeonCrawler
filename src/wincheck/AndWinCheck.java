package wincheck;

import java.util.ArrayList;

import compositecheck.CompositeCheck;
import compositecheck.NodeCheck;
import unsw.dungeon.Dungeon;
import wincondition.WinCondition;

public class AndWinCheck implements WinCheck{

	/**
	 * Checks if AND conditions are satisfied
	 * @param obj : A NodeCheck object
	 */
	@Override
	public boolean canWin(NodeCheck obj) {
		boolean result = true;
    	for(CompositeCheck e: obj.getSubCheck()) {
    		try {
    		if(e == null) throw new AssertionError("subcheck is null");
    		result = (result && e.check());
    		}catch(AssertionError msg){
    			System.out.println(msg.getMessage());
    		}
    	}
    	return result;
	}

}
