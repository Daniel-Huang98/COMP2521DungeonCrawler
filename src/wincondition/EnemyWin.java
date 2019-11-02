package wincondition;

import unsw.dungeon.Dungeon;

public class EnemyWin implements WinCondition{

	@Override
	public boolean canWin(Dungeon dungeon) {
		return dungeon.allEnemiesKilled();
	}

}
