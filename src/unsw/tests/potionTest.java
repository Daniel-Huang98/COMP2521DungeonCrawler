package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import wincheck.OrWinCheck;

class potionTest {

	@Test
	void pickupTestUp() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(5,4);
		player.addObserver(potion);
		assertEquals(null, player.getPotion());
		player.moveUp();
		assertEquals(potion, player.getPotion());
	}
	
	@Test
	void pickupTestDown() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(5,6);
		player.addObserver(potion);
		assertEquals(null, player.getPotion());
		player.moveDown();
		assertEquals(potion, player.getPotion());
	}
	
	@Test
	void pickupTestLeft() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(4,5);
		player.addObserver(potion);
		assertEquals(null, player.getPotion());
		player.moveLeft();
		assertEquals(potion, player.getPotion());
	}
	
	@Test
	void pickupTestRight() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(6,5);
		player.addObserver(potion);
		assertEquals(null, player.getPotion());
		player.moveRight();
	}
	
	@Test
	void deleteTestUp() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(5,4);
		player.addObserver(potion);
		assertEquals(false,potion.isDeleted());
		player.moveUp();
		assertEquals(true,potion.isDeleted());
	}
	
	@Test
	void deleteTestDown() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(5,6);
		player.addObserver(potion);
		assertEquals(false,potion.isDeleted());
		player.moveDown();
		assertEquals(true,potion.isDeleted());
	}
	
	@Test
	void deleteTestLeft() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(4,5);
		player.addObserver(potion);
		assertEquals(false,potion.isDeleted());
		player.moveLeft();
		assertEquals(true,potion.isDeleted());
	}
	
	@Test
	void deleteTestRight() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(6,5);
		player.addObserver(potion);
		assertEquals(false,potion.isDeleted());
		player.moveRight();
		assertEquals(true,potion.isDeleted());
	}
	
	@Test
	void battleTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(6,5);
		player.addObserver(potion);
		assertEquals(false,player.getAction().attacked(player));
	}
	
	@Test
	void battleTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(6,5);
		player.addObserver(potion);
		assertEquals(false,potion.isDeleted());
		player.moveRight();
		assertEquals(true,player.getAction().attacked(player));
	}
	
	@Test
	void battleTest3() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(6,5);
		player.addObserver(potion);
		player.moveRight();
		for(int i = 0; i < 15; i++) {
			player.moveRight();
		}
		assertEquals(false,player.getAction().attacked(player));
	}
	
	@Test
	void healthTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(6,5);
		player.addObserver(potion);
		player.moveRight();
		for(int i = 0; i < 15; i++) {
			player.moveRight();
		}
		assertEquals(null,player.getPotion());
	}
	
	
	
	

}
