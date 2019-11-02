package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Sword;

class EnemyTest {

	@Test
	void deathBattleTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,4);
		Enemy enemy = new Enemy(5,6);
		enemy.addObserver(player);
		player.addObserver(enemy);
		player.moveDown();
		player.moveDown();
		assertEquals(false,player.isAlive(), "add test 2");
	}
	
	@Test
	void healthTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,4);
		Enemy enemy = new Enemy(5,6);
		enemy.addObserver(player);
		player.addObserver(enemy);
		player.moveDown();
		player.moveDown();
		assertEquals(false,enemy.isDeleted(), "add test 2");
	}
	
	@Test
	void potionBattleTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(5,6);
		Enemy enemy = new Enemy(5,7);
		enemy.addObserver(player);
		player.addObserver(enemy);
		player.addObserver(potion);
		player.moveDown();
		player.moveDown();
		assertEquals(true,player.isAlive() , "add test 2");
	}
	
	@Test
	void healthTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(5,6);
		Enemy enemy = new Enemy(5,7);
		enemy.addObserver(player);
		player.addObserver(enemy);
		player.addObserver(potion);
		player.moveDown();
		player.moveDown();
		assertEquals(true,enemy.isDeleted(), "add test 2");
	}
	
	@Test
	void swordBattleTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,6);
		Enemy enemy = new Enemy(5,7);
		enemy.addObserver(player);
		player.addObserver(enemy);
		player.addObserver(sword);
		player.moveDown();
		player.moveDown();
		assertEquals(true,player.isAlive() , "add test 2");
	}
	
	@Test
	void healthTest3() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,6);
		Enemy enemy = new Enemy(5,7);
		enemy.addObserver(player);
		player.addObserver(enemy);
		player.addObserver(sword);
		player.moveDown();
		player.moveDown();
		assertEquals(true,enemy.isDeleted() , "add test 2");
	}


}
