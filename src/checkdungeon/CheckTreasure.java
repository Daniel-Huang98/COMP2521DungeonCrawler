package checkdungeon;

import unsw.dungeon.Dungeon;

public class CheckTreasure implements CheckDungeon{

	@Override
	public boolean checkEntity(Dungeon dungeon) {
		return dungeon.allGoldCollected();
	}

}
