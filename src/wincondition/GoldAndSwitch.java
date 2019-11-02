package wincondition;

import unsw.dungeon.Dungeon;

public class GoldAndSwitch implements WinCondition{
	@Override
	public boolean canWin(Dungeon dungeon) {
		return dungeon.allGoldCollected() && dungeon.allSwitchesActivated();
	}
}
