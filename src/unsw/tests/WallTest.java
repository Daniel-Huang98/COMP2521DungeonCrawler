package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;
import unsw.dungeon.Wall;

class WallTest {

	@Test
	void moveUpIntoWallTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Wall wall = new Wall(5,4);
		player.addObserver(wall);
		player.moveUp();
		assertEquals(true, player.getX() == 5 && player.getY() == 5);
	}
	
	@Test
	void moveDownIntoWallTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Wall wall = new Wall(5,6);
		player.addObserver(wall);
		player.moveDown();
		assertEquals(true, player.getX() == 5 && player.getY() == 5);
	}
	
	@Test
	void moveLeftIntoWallTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Wall wall = new Wall(4,5);
		player.addObserver(wall);
		player.moveLeft();
		assertEquals(true, player.getX() == 5 && player.getY() == 5);
	}
	
	@Test
	void moveRightIntoWallTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Wall wall = new Wall(6,5);
		player.addObserver(wall);
		player.moveRight();
		assertEquals(true, player.getX() == 5 && player.getY() == 5);
	}

}
