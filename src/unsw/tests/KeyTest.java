package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.playerObserver;

class KeyTest {

	/**
	 * When a player walks over a key, the key becomes held by the player
	 */
	@Test
	void testOpen() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Key key = new Key(1,0);
		player.addObserver((playerObserver)key);
		assertEquals(player.getKey(),false, "player does not have key");
		player.moveRight();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(player.getKey(),true, "player has key");
	}
}