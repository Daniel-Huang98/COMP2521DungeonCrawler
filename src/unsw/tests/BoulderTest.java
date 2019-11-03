package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;
import unsw.dungeon.playerObserver;

class BoulderTest {

	/**
	 * When a player is touching the boulder and walks in the 
	 * direction of the boulder, the boulder moves with the player
	 */
	@Test
	void testPush() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Boulder boulder = new Boulder(1,0);
		player.addObserver((playerObserver)boulder);
		player.moveRight();
		assertEquals(boulder.getX(), 2, "Boulder Push Test");
		assertEquals(boulder.getY(), 0, "Boulder Push Test");
		assertEquals(player.getX(), 1, "Boulder Push Test");
		assertEquals(player.getY(), 0, "Boulder Push Test");
	}

	/**
	 * Pushing a boulder against another boulder does nothing
	 */
	@Test
	void testTwoBoulders() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Boulder boulder = new Boulder(1,0);
		Boulder boulder2 = new Boulder(2,0);
		player.addObserver((playerObserver)boulder);
		player.addObserver((playerObserver)boulder2);
		boulder.addObserver((playerObserver)boulder2);
		boulder2.addObserver((playerObserver)boulder);
		player.moveRight();
		assertEquals(boulder.getX(), 1, "Two Boulder Test");
		assertEquals(boulder.getY(), 0, "Two Boulder Test");
		assertEquals(boulder2.getX(), 2, "Two Boulder Test");
		assertEquals(boulder2.getY(), 0, "Two Boulder Test");
		assertEquals(player.getX(), 0, "Two Boulder Test");
		assertEquals(player.getY(), 0, "Two Boulder Test");
	}
	
	/**
	 * Pushing a boulder against a wall will do nothing
	 */
	@Test
	void testBoulderNextToWall() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Boulder boulder = new Boulder(1,0);
		Wall wall = new Wall(2,0);
		player.addObserver((playerObserver)boulder);
		player.addObserver((playerObserver)wall);
		boulder.addObserver((playerObserver)wall);
		player.moveRight();
		assertEquals(boulder.getX(), 1, "Boulder Against Wall Test");
		assertEquals(boulder.getY(), 0, "Boulder Against Wall Test");
		assertEquals(player.getX(), 0, "Boulder Against Wall Test");
		assertEquals(player.getY(), 0, "Boulder Against Wall Test");
	}	
}
