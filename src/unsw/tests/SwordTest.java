package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;
import wincheck.OrWinCheck;

class SwordTest {
	
	
	
	@Test
	void pickupTestUp() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,4);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveUp();
		assertEquals(sword, player.getSword());
	}
	
	@Test
	void pickupTestDown() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,6);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveDown();
		assertEquals(sword, player.getSword());
	}
	
	@Test
	void pickupTestLeft() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,5);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveLeft();
		assertEquals(sword, player.getSword());
	}
	
	@Test
	void pickupTestRight() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(6,5);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveRight();
		assertEquals(true, sword.isDeleted());
	}
	
	@Test
	void deleteTestUp2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,4);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveUp();
		assertEquals(true, sword.isDeleted());
	}
	
	@Test
	void deleteTestDown2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,6);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveDown();
		assertEquals(true, sword.isDeleted());
	}
	
	@Test
	void deleteTestLeft2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,5);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveLeft();
		assertEquals(true, sword.isDeleted());
	}
	
	@Test
	void deleteTestRight2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(6,5);
		player.addObserver(sword);
		player.moveRight();
		assertEquals(true, sword.isDeleted());
	}
	
	@Test
	void deleteTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		sword.delete();
		assertEquals(true,sword.isDeleted() , "check if deleted 1");
	}
	
	@Test
	void deleteTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
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
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		player.setSword(sword);
		assertEquals(sword,player.getSword() , "add test 2");
	}
	
	@Test
	void swordActionTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		player.setSword(sword);
		assertEquals(false,player.getAction().attacked(player) , "add test 2");
	}
	
	@Test
	void swordActionTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,4);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveUp();
		assertEquals(true,player.getAction().attacked(player) , "add test 2");
	}
	
	@Test
	void swordHealthTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,4);
		player.addObserver(sword);
		//assertEquals(null, player.getSword());
		player.moveUp();
		System.out.println(sword.getHealth());
		
		assertEquals(true,player.getAction().attacked(player) , "Decrement sword health 1");
		System.out.println(sword.getHealth());
		
		assertEquals(true,player.getAction().attacked(player) , "Decrement sword health 2");
		System.out.println(sword.getHealth());
		
		assertEquals(true,player.getAction().attacked(player) , "Decrement sword health 3");
		System.out.println(sword.getHealth());
		
		assertEquals(true,player.getAction().attacked(player) , "Decrement sword health 4");
		System.out.println(sword.getHealth());
		
		assertEquals(true,player.getAction().attacked(player) , "Decrement sword health 5");
		System.out.println(sword.getHealth());
		
		assertEquals(false,player.getAction().attacked(player) ,"check if player dies");
		
		//assertEquals(false,player.getAction().attacked(player) , "add test 2");
		assertEquals(null,player.getSword() , "check sword has been deleted");
	}
	
	
	

}
