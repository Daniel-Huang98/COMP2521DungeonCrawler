package battle;

import unsw.dungeon.Player;

/**
 * Battle interface which determines how a player
 * fights enemies
 */
public interface battle {
	/**
	 * battle behavior of the player
	 * @param player player reference
	 * @return true if player wins, false if player loses
	 */
	boolean attacked(Player player);
}
