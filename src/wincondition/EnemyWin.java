package wincondition;

import unsw.dungeon.Dungeon;

public class EnemyWin implements WinCondition{

	/**
	 * See if enemies are killed
	 * @return : boolean representing if all enemies are killed
	 */
	@Override
	public boolean canWin(Dungeon dungeon) {
		return dungeon.allEnemiesKilled();
	}

}
