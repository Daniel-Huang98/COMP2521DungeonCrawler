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
	
	/**
	 * When a boulder is on the same tile as a switch, the switch is 
	 * considered to be pressed
	 */
	@Test
	void testBoulderOnSwitch () {
		Dungeon dungeon = new Dungeon(4,4);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		Player player = new Player(dungeon, 0, 0);
		Boulder boulder = new Boulder(1,0);
		FloorSwitch floorSwitch = new FloorSwitch(2,0, dungeon);
		player.addObserver((playerObserver)boulder);
		player.addObserver((playerObserver)floorSwitch);
		boulder.addObserver((playerObserver)floorSwitch);
		player.moveRight();
		assertEquals(boulder.getX(), 2, "Boulder on Switch Test - boulder x");
		assertEquals(boulder.getY(), 0, "Boulder on Switch Test - boulder y");
		assertEquals(player.getX(), 1, "Boulder on Switch Test - player x");
		assertEquals(player.getY(), 0, "Boulder on Switch Test - player y");
		assertEquals(boulder, floorSwitch.getBoulder(), "Boulder on Switch Test - boulder is on switch");
	}
	
	/**
	 * When a boulder is not on the same tile as a switch, the switch is 
	 * considered to be not pressed
	 */
	@Test
	void testNotOnSwitch () {
		Dungeon dungeon = new Dungeon(4,4);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		Player player = new Player(dungeon, 0, 0);
		Boulder boulder = new Boulder(1,0);
		FloorSwitch floorSwitch = new FloorSwitch(3,1, dungeon);
		player.addObserver((playerObserver)boulder);
		player.addObserver((playerObserver)floorSwitch);
		boulder.addObserver((playerObserver)floorSwitch);
		player.moveRight();
		assertEquals(boulder.getX(), 2, "Boulder Not on Switch Test - boulder x");
		assertEquals(boulder.getY(), 0, "Boulder Not on Switch Test - boulder y");
		assertEquals(player.getX(), 1, "Boulder Not on Switch Test - player x");
		assertEquals(player.getY(), 0, "Boulder Not on Switch Test - player y");
		assertEquals(floorSwitch.getBoulder(), null, "Boulder Not on Switch Test - boulder not on switch");
	}
	
	/**
	 * Moving another boulder should not have any effect on a
	 * switch being pressed by another boulder
	 */
	@Test
	void testMoveAnotherBoulder () {
		Dungeon dungeon = new Dungeon(4,4);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		Player player = new Player(dungeon, 0, 0);
		Boulder boulder = new Boulder(1,0);
		Boulder boulder2 = new Boulder(1,1);
		FloorSwitch floorSwitch = new FloorSwitch(2,0, dungeon);
		player.addObserver((playerObserver)boulder);
		player.addObserver((playerObserver)boulder2);
		player.addObserver((playerObserver)floorSwitch);
		boulder.addObserver((playerObserver)floorSwitch);
		player.moveRight();
		assertEquals(boulder.getX(), 2, "Move Another Boulder Test - boulder 1 x (right)");
		assertEquals(boulder.getY(), 0, "Move Another Boulder Test - boulder 1 y (right)");
		assertEquals(player.getX(), 1, "Move Another Boulder Test - player x (right)");
		assertEquals(player.getY(), 0, "Move Another Boulder Test - player y (right)");
		assertEquals(boulder, floorSwitch.getBoulder(), "Move Another Boulder Test - boulder off switch");
		player.moveDown();
		assertEquals(boulder.getX(), 2, "Move Another Boulder Test - boulder 1 x (down)");
		assertEquals(boulder.getY(), 0, "Move Another Boulder Test - boulder 1 y (down)");
		assertEquals(boulder2.getX(), 1, "Move Another Boulder Test - boulder 2 x");
		assertEquals(boulder2.getY(), 2, "Move Another Boulder Test - boulder 2 y");
		assertEquals(player.getX(), 1, "Move Another Boulder Test - player x (right)");
		assertEquals(player.getY(), 1, "Move Another Boulder Test - player y (right)");
		assertEquals(boulder, floorSwitch.getBoulder(), "Move Another Boulder Test - boulder on switch");
	}
	
	/**
	 * When boulder is pushed off the switch, the switch is no longer 
	 * considered pressed
	 */
	@Test
	void testMoveOffSwitch() {
		Dungeon dungeon = new Dungeon(4,4);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		Player player = new Player(dungeon, 0, 0);
		Boulder boulder = new Boulder(1,0);
		FloorSwitch floorSwitch = new FloorSwitch(1,0, dungeon);
		player.addObserver((playerObserver)boulder);
		player.addObserver((playerObserver)floorSwitch);
		boulder.addObserver((playerObserver)floorSwitch);
		player.moveRight();
		assertEquals(boulder.getX(), 2, "Move Off Switch Test - boulder x");
		assertEquals(boulder.getY(), 0, "Move Off Switch Test - boulder y");
		assertEquals(player.getX(), 1, "Move Off Switch Test - player x");
		assertEquals(player.getY(), 0, "Move Off Switch Test - player y");
		assertEquals(null, floorSwitch.getBoulder(), "Move Off Switch Test - boulder off switch");
	}
}