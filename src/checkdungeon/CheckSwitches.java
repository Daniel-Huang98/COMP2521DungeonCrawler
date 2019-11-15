package checkdungeon;

import unsw.dungeon.Dungeon;

public class CheckSwitches implements CheckDungeon{

	@Override
	public boolean checkEntity(Dungeon dungeon) {
		return dungeon.allSwitchesActivated();
	}

}
