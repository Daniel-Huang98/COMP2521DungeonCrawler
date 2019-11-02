package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Player;
import unsw.dungeon.playerObserver;
import wincheck.AndWinCheck;

class SwitchTest {
	
	/*
	 * When a boulder is on the same tile as a switch, the switch is 
	 * considered to be pressed
	 */
	@Test
	void testBoulderOnSwitch () {
		Dungeon dungeon = new Dungeon(4,4);
		dungeon.setWinCheck(new AndWinCheck());
		Player player = new Player(dungeon, 0, 0);
		Boulder boulder = new Boulder(1,0);
		FloorSwitch floorSwitch = new FloorSwitch(2,0, dungeon);
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
	
	/*
	 * When a boulder is not on the same tile as a switch, the switch is 
	 * considered to be not pressed
	 */
	@Test
	void testNotOnSwitch () {
		Dungeon dungeon = new Dungeon(4,4);
		dungeon.setWinCheck(new AndWinCheck());
		Player player = new Player(dungeon, 0, 0);
		Boulder boulder = new Boulder(1,0);
		FloorSwitch floorSwitch = new FloorSwitch(3,1, dungeon);
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
	
	/*
	 * Moving another boulder should not have any effect on a
	 * switch being pressed by another boulder
	 */
	@Test
	void testMoveAnotherBoulder () {
		Dungeon dungeon = new Dungeon(4,4);
		dungeon.setWinCheck(new AndWinCheck());
		Player player = new Player(dungeon, 0, 0);
		Boulder boulder = new Boulder(1,0);
		Boulder boulder2 = new Boulder(1,1);
		FloorSwitch floorSwitch = new FloorSwitch(2,0, dungeon);
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
	
	/*
	 * When boulder is pushed off the switch, the switch is no longer 
	 * considered pressed
	 */
	@Test
	void testMoveOffSwitch() {
		Dungeon dungeon = new Dungeon(4,4);
		dungeon.setWinCheck(new AndWinCheck());
		Player player = new Player(dungeon, 0, 0);
		Boulder boulder = new Boulder(1,0);
		FloorSwitch floorSwitch = new FloorSwitch(1,0, dungeon);
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