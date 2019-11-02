package battle;

import unsw.dungeon.Player;

/**
 * Strategy for when player is invincible
 */
public class potionBattle implements battle{

	@Override
	public boolean attacked(Player player) {
		return true;
	}

}
