package wincondition;

import unsw.dungeon.Dungeon;

public class GoldAndSwitch implements WinCondition{
	
	/**
	 * See if all gold is collected and all switches are pressed
	 * @return : boolean representing if all gold is collected and switches pressed
	 */
	@Override
	public boolean canWin(Dungeon dungeon) {
		return dungeon.allGoldCollected() && dungeon.allSwitchesActivated();
	}
}
