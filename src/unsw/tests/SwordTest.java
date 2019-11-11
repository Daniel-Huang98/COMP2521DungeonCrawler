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
	
	/**
	 * A sword is picked up when the player walks 
	 * over the sword
	 */
	@Test
	void pickupTestUp() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,4);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveUp();
		assertEquals(sword, player.getSword());
	}
	
	/**
	 * When a player walks over a new sword while holding a sword
	 * ,the player does not pick up a new sword. 
	 */
	@Test
	void pickupTestUp2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,4);
		Sword sword2 = new Sword(5,3);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveUp();
		player.moveUp();
		player.moveUp();
		assertEquals(sword, player.getSword());
	}
	
	/**
	 * When a sword is picked up, 
	 * the sword on the floor disappears
	 */
	@Test
	void deleteTestUp2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,4);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveUp();
		assertEquals(true, sword.isDeleted());
	}
	
	/**
	 * A sword is picked 
	 * up when the player walks over the sword
	 */
	@Test
	void addTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		assertEquals(null,player.getSword() , "add test 1");
	}
	
	/**
	 * A sword is picked
	 *  up when the player walks over the sword
	 */
	@Test
	void addTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		player.setSword(sword);
		assertEquals(sword,player.getSword() , "add test 2");
	}
	
	/**
	 * When a player who is holding a sword 
	 * collides with an enemy, the enemy dies
	 */
	@Test
	void swordActionTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		player.setSword(sword);
		assertEquals(false,player.getAction().attacked(player) , "add test 2");
	}
	
	/**
	 * When a player who is holding a sword 
	 * collides with an enemy, the enemy dies
	 */
	@Test
	void swordActionTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,4);
		player.addObserver(sword);
		assertEquals(null, player.getSword());
		player.moveUp();
		assertEquals(true,player.getAction().attacked(player) , "add test 2");
	}
	
	/**
	 * After the sword has been used to
	 * kill 5 enemies, the sword disappears
	 */
	@Test
	void swordHealthTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
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
