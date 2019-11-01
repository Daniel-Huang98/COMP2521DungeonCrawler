package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Player;
import unsw.dungeon.playerObserver;

class SwitchTest {
	
	@Test
	void testBoulderOnSwitch () {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Boulder boulder = new Boulder(1,0);
		FloorSwitch floorSwitch = new FloorSwitch(2,0);
		player.addObserver((playerObserver)boulder);
		player.addObserver((playerObserver)floorSwitch);
		boulder.addObserver((playerObserver)floorSwitch);
		player.moveRight();
		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 0);
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(boulder, floorSwitch.getBoulder());
	}
	
	void testNotOnSwitch () {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Boulder boulder = new Boulder(1,0);
		FloorSwitch floorSwitch = new FloorSwitch(3,1);
		player.addObserver((playerObserver)boulder);
		player.addObserver((playerObserver)floorSwitch);
		boulder.addObserver((playerObserver)floorSwitch);
		player.moveRight();
		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 0);
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(floorSwitch.getBoulder(), null);
	}
	
	void testMoveAnotherBoulder () {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Boulder boulder = new Boulder(1,0);
		Boulder boulder2 = new Boulder(1,1);
		FloorSwitch floorSwitch = new FloorSwitch(2,0);
		player.addObserver((playerObserver)boulder);
		player.addObserver((playerObserver)boulder2);
		player.addObserver((playerObserver)floorSwitch);
		boulder.addObserver((playerObserver)floorSwitch);
		player.moveRight();
		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 0);
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(boulder, floorSwitch.getBoulder());
		player.moveDown();
		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 0);
		assertEquals(boulder2.getX(), 1);
		assertEquals(boulder2.getY(), 2);
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
		assertEquals(boulder, floorSwitch.getBoulder());
	}
	
	void testMoveOffSwitch() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Boulder boulder = new Boulder(1,0);
		FloorSwitch floorSwitch = new FloorSwitch(1,0);
		player.addObserver((playerObserver)boulder);
		player.addObserver((playerObserver)floorSwitch);
		boulder.addObserver((playerObserver)floorSwitch);
		player.moveRight();
		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 0);
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(null, floorSwitch.getBoulder());
	}
}