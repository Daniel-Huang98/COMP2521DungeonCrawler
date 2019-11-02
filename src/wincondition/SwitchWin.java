package wincondition;

import unsw.dungeon.Dungeon;

public class SwitchWin implements WinCondition{

	@Override
	public boolean canWin(Dungeon dungeon) {
		return dungeon.allSwitchesActivated();
	}

}
