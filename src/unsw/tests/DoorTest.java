package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.playerObserver;

class DoorTest {
	/*
	 * Each key is linked to a corresponding door. While holding 
	 * the correct key and the player touches the corresponding door, 
	 * the door will unlock.
	 */
	@Test
	void testOpen() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Door door = new Door(2,0);
		Key key = new Key(1,0);
		player.addObserver((playerObserver)key);
		player.addObserver((playerObserver)door);
		assertEquals(player.getKey(),false);
		player.moveRight();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(player.getKey(),true);
		player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 0);
		assertEquals(door.getOpened(), true);
	}
	
	/*
	 * 	A player should not be able to go through a locked door
	 */
	@Test
	void testNoKey() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Door door = new Door(1,0);
		player.addObserver((playerObserver)door);
		assertEquals(player.getKey(),false);
		player.moveRight();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(door.getOpened(), false);
	}
}