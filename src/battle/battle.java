package battle;

import unsw.dungeon.Player;

/**
 * Battle interface which determines how a player
 * fights enemies
 */
public interface battle {
	boolean attacked(Player player);
}
