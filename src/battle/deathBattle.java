package battle;

import unsw.dungeon.Player;

/**
 * Strategy for when player is vulnerable
 */
public class deathBattle implements battle{

	@Override
	public boolean attacked(Player player) {
		player.die();
		return false;
	}

}
