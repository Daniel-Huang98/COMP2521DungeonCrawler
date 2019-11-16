package wincondition;

import unsw.dungeon.Dungeon;

public class SwitchWin implements WinCondition{

	/**
	 * See if all switches are pressed
	 * @return : boolean representing if all switches are pressed
	 */
	@Override
	public boolean canWin(Dungeon dungeon) {
		return dungeon.allSwitchesActivated();
	}

}
