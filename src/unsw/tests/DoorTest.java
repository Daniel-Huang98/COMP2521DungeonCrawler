package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.playerObserver;

class DoorTest {
	/**
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
		assertEquals(player.getKey(),false, "Open Door Test  - player does not have key");
		player.moveRight();
		assertEquals(player.getX(), 1, "Open Door Test");
		assertEquals(player.getY(), 0, "Open Door Test");
		assertEquals(player.getKey(),true, "Open Door Test - player has key");
		player.moveRight();
		assertEquals(player.getX(), 2, "Open Door Test");
		assertEquals(player.getY(), 0, "Open Door Test");
		assertEquals(door.getOpened(), true, "Open Door Test - door unlocked");
	}
	
	/**
	 * If a player walks over a door and does not hold the corresponding 
	 * key, the door remains closed.
	 */
	@Test
	void testNoKey() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Door door = new Door(1,0);
		player.addObserver((playerObserver)door);
		assertEquals(player.getKey(),false, "Door Locked Test - player does not have key");
		player.moveRight();
		assertEquals(player.getX(), 1, "Door Locked Test");
		assertEquals(player.getY(), 0, "Door Locked Test");
		assertEquals(door.getOpened(), false, "Door Locked Test - door locked");
	}
	
	/**
	 * Once the door is opened, it remains opened.
	 */
	@Test
	void testStayOpen() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Door door = new Door(2,0);
		Key key = new Key(1,0);
		player.addObserver((playerObserver)key);
		player.addObserver((playerObserver)door);
		assertEquals(player.getKey(),false, "Door Stay Open Test - player does not have key");
		player.moveRight();
		assertEquals(player.getX(), 1, "Door Stay Open Test");
		assertEquals(player.getY(), 0, "Door Stay Open Test");
		assertEquals(player.getKey(),true, "Door Stay Open Test - player has key");
		player.moveRight();
		assertEquals(player.getX(), 2, "Door Stay Open Test");
		assertEquals(player.getY(), 0, "Door Stay Open Test");
		assertEquals(door.getOpened(), true, "Door Stay Open Test - door is opened");
		player.moveRight();
		assertEquals(player.getX(), 3, "Door Stay Open Test");
		assertEquals(player.getY(), 0, "Door Stay Open Test");
		assertEquals(door.getOpened(), true, "Door Stay Open Test - door remains open");
	}
}
