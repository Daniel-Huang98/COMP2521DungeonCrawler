package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;
import unsw.dungeon.playerObserver;

class DoorTest {

	@Test
	void testOpen() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Door door = new Door(1,0);
		player.addObserver((playerObserver)door);
		player.moveRight();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(door.getOpened(), true);
	}
	
	void testClose() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Door door = new Door(0,0);
		player.addObserver((playerObserver)door);
		player.moveRight();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(door.getOpened(), false);
	}
}