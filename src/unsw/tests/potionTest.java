package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import battle.deathBattle;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import wincheck.OrWinCheck;

class potionTest {

	/**
	 * When a player picks up an invincibility potion, it is 
	 * immediately used
	 */
	@Test
	void pickupTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(5,4);
		player.addObserver(potion);
		assertEquals(null, player.getPotion());
		player.moveUp();
		assertEquals(potion, player.getPotion());
	}
	
	/**
	 * When an invincibility potion is picked up, the invincibility 
	 * potion on the floor disappears
	 */
	@Test
	void deleteTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(5,4);
		player.addObserver(potion);
		assertEquals(false,potion.isDeleted());
		player.moveUp();
		assertEquals(true,potion.isDeleted());
	}

	/**
	 * When a player uses the invincibility potion, their battle
	 * strategy should change
	 */
	@Test
	void battleTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(6,5);
		player.addObserver(potion);
		assertEquals(false,player.getAction().attacked(player));
	}
	
	/**
	 * When a player uses the invincibility potion, their battle
	 * strategy should change
	 */
	@Test
	void battleTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(6,5);
		player.addObserver(potion);
		assertEquals(false,potion.isDeleted());
		player.moveRight();
		assertEquals(true,player.getAction().attacked(player));
	}
	
	/**
	 * After a player has used the invincibility potion, it's effects 
	 * should stop after 15 steps
	 */
	@Test
	void battleTest3() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(6,5);
		player.addObserver(potion);
		player.setCanMove(true);
		player.moveRight();
		for(int i = 0; i < potion.getFullHealth(); i++) {
			player.decrementPotionHealth();
		}
		assertEquals(true, player.getAction() instanceof deathBattle);
		assertEquals(false,player.getAction().attacked(player));
	}
	
	/**
	 * After a player has used the invincibility potion, it's effects 
	 * should stop after health used up
	 */
	@Test
	void healthTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(6,5);
		player.addObserver(potion);
		player.moveRight();
		player.setCanMove(true);
		for(int i = 0; i < potion.getFullHealth(); i++) {
			player.decrementPotionHealth();
		}
		assertEquals(null,player.getPotion());
	}
	
	
	
	

}
