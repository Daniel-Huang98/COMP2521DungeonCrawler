package wincondition;

import unsw.dungeon.Dungeon;

public class GoldWin implements WinCondition{

	@Override
	public boolean canWin(Dungeon dungeon) {
		return dungeon.allGoldCollected();
	}
	
}
