package wincondition;

import unsw.dungeon.Dungeon;

public class GoldWin implements WinCondition{
	/**
	 * See if all gold is collected
	 * @return : boolean representing if all gold is collected
	 */
	@Override
	public boolean canWin(Dungeon dungeon) {
		return dungeon.allGoldCollected();
	}
	
}
