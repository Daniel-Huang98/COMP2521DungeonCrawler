package checkdungeon;

import unsw.dungeon.Dungeon;

public class CheckEnemy implements CheckDungeon{

	@Override
	public boolean checkEntity(Dungeon dungeon) {
		return dungeon.allEnemiesKilled();
	}

}
