package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;

class SwordTest {
	
	
	
	@Test
	void deleteTestUp() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,4);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveUp();
		assertEquals(sword, player.getSword());
	}
	
	@Test
	void deleteTestDown() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,6);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveDown();
		assertEquals(sword, player.getSword());
	}
	
	@Test
	void deleteTestLeft() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,5);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveLeft();
		assertEquals(sword, player.getSword());
	}
	
	@Test
	void deleteTestRight() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(6,5);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveRight();
		assertEquals(sword, player.getSword());
	}
	
	@Test
	void deleteTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		sword.delete();
		assertEquals(true,sword.isDeleted() , "check if deleted 1");
	}
	
	@Test
	void deleteTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		assertEquals(false,sword.isDeleted() , "check if deleted 1");
	}
	
	@Test
	void addTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		assertEquals(null,player.getSword() , "add test 1");
	}
	
	@Test
	void addTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		player.setSword(sword);
		assertEquals(sword,player.getSword() , "add test 2");
	}
	
	
	

}
