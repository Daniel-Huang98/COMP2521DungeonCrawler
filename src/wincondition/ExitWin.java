package wincondition;

import unsw.dungeon.Dungeon;

public class ExitWin implements WinCondition{

	/**
	 * See if exit is reached
	 * @return : boolean representing if exit is reached
	 */
	@Override
	public boolean canWin(Dungeon dungeon) {
		return dungeon.exitted();
	}

}
