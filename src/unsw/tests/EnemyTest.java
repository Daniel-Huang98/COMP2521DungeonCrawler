package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Sword;
import unsw.dungeon.Wall;
import wincheck.AndWinCheck;

class EnemyTest {
	
	/**
	 * A player that is not holding a sword who collides with an enemy 
	 * should be immediately defeated by the enemy
	 */
	@Test
	void deathBattleTest1() {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(10, 10);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,5,4);
		Enemy enemy = new Enemy(5,6);
		enemy.addObserver(player);
		player.addObserver(enemy);
		entities.add(player);
		entities.add(enemy);
		setMap(entities, 10, 10, enemy);
		player.moveDown();
		assertEquals(false,player.isAlive(), "Battle Test 1");
	}
	
	/**
	 * A player that is not holding a sword who collides with an enemy 
	 * should be immediately defeated by the enemy
	 */
	@Test
	void deathBattleTest2() {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(10, 10);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,5,4);
		Enemy enemy = new Enemy(5,7);
		enemy.addObserver(player);
		player.addObserver(enemy);
		entities.add(player);
		entities.add(enemy);
		setMap(entities, 10, 10, enemy);
		player.moveDown();
		assertEquals(enemy.getX(), 5);
		assertEquals(enemy.getY(), 6);
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 5);
		assertEquals(true,player.isAlive(), "Battle Test 2");
	}
	
	/**
	 * An enemy that kills the player is still alive
	 */
	@Test
	void healthTest() {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(10, 10);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,5,3);
		Enemy enemy = new Enemy(5,6);
		enemy.addObserver(player);
		player.addObserver(enemy);
		entities.add(player);
		entities.add(enemy);
		setMap(entities, 10, 10, enemy);
		player.moveDown();
		assertEquals(enemy.getX(), 5);
		assertEquals(enemy.getY(), 5);
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 4);
		assertEquals(false,enemy.isDeleted(), "Health Test");
	}
	
	/**
	 * If the player is invincible, enemies walk away from them
	 */
	@Test
	void potionBattleTest() {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(10, 10);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,5,5);
		Potion potion = new Potion(5,6);
		Enemy enemy = new Enemy(5,8);
		enemy.addObserver(player);
		player.addObserver(enemy);
		player.addObserver(potion);
		entities.add(player);
		entities.add(enemy);
		entities.add(potion);
		setMap(entities, 10, 10, enemy);
		player.moveDown();
		player.moveDown();
		//assertEquals(enemy.getX(), 5);
		//assertEquals(enemy.getY(), 7);
		//assertEquals(player.getX(), 5);
		//assertEquals(player.getY(), 6);
		assertEquals(true,player.isAlive() , "Potion Test - player alive");
		assertEquals(false,enemy.isDeleted(), "Potion Test - enemy is alive");
	}
	
	
	/**
	 * When an invincible player collides with an enemy, the enemy is 
	 * defeated
	 */
	@Test
	void potionBattleTest2() {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(1,4);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,0,0);
		Potion potion = new Potion(0,1);
		Enemy enemy = new Enemy(0,3);
		enemy.addObserver(player);
		player.addObserver(enemy);
		player.addObserver(potion);
		entities.add(player);
		entities.add(enemy);
		entities.add(potion);
		setMap(entities, 4, 1, enemy);
		player.moveDown();
		player.moveDown();
		player.moveDown();
		assertEquals(true,player.isAlive() , "Potion Test 2 - player alive");
		assertEquals(true,enemy.isDeleted(), "Potion Test 2 - enemy dead");
	}
	
	/**
	 * When a player who is holding a sword collides with an enemy, the 
	 * enemy dies
	 */
	@Test
	void swordBattleTest() {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(10, 10);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(5,6);
		Enemy enemy = new Enemy(5,9);
		enemy.addObserver(player);
		player.addObserver(enemy);
		player.addObserver(sword);
		entities.add(player);
		entities.add(enemy);
		entities.add(sword);
		setMap(entities, 10, 10, enemy);
		player.moveDown();
		player.moveDown();
		assertEquals(true,player.isAlive() , "Sword Test - player alive");
		assertEquals(true,enemy.isDeleted(), "Sword Test - enemy dead");
	}
	
	/**
	 * The enemy must always walk closer to the player
	 */
	void wallTest() {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(10, 10);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,5,5);
		Enemy enemy = new Enemy(3,6);
		Wall wall = new Wall(4,6);
		enemy.addObserver(player);
		player.addObserver(enemy);
		player.addObserver(wall);
		entities.add(player);
		entities.add(enemy);
		entities.add(wall);
		setMap(entities, 10, 10, enemy);
		player.moveRight();
		assertEquals(enemy.getX(), 3);
		assertEquals(enemy.getY(), 5);
		assertEquals(player.getX(), 6);
		assertEquals(player.getY(), 5);
		assertEquals(true,player.isAlive() , "Wall Test - player alive");
		assertEquals(false,enemy.isDeleted(), "wall Test - enemy alive");
	}
	
	/**
	 * 	If the enemy cannot move any closer to the player, it stops
	 */
	void wallTest2() {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(5, 1);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		Player player = new Player(dungeon,3,0);
		Enemy enemy = new Enemy(1,0);
		Wall wall = new Wall(2,0);
		enemy.addObserver(player);
		player.addObserver(enemy);
		player.addObserver(wall);
		entities.add(player);
		entities.add(enemy);
		entities.add(wall);
		setMap(entities, 1, 5, enemy);
		player.moveRight();
		assertEquals(enemy.getX(), 0);
		assertEquals(enemy.getY(), 0);
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 4);
		assertEquals(true,player.isAlive() , "Wall Test 2 - player alive");
		assertEquals(false,enemy.isDeleted(), "Wall Test 2 - enemy alive");
	}
	
	/**
	 * Sets up the 1 to 1 entity map so enemies know the layout
	 * of the map
	 * @param entities : a list of all generated entities
	 * @param height : height of dungeon
	 * @param width : width of the dungeon
	 * @param enemy : the enemy entity
	 */
	void setMap(ArrayList<Entity> entities, int height, int width, Enemy enemy) {
		ArrayList<ArrayList<Entity>> map = new ArrayList<ArrayList<Entity>>();
		for (int i = 0; i < height; i++) {
			ArrayList<Entity> inner = new ArrayList <Entity>();
			for (int j = 0; j < width; j++) {
				inner.add(null);
			}
			map.add(inner);
		}
		for(Entity e: entities) {
        	map.get(e.getY()).set(e.getX(), e);
		}
		enemy.setMap(map);
	}
}
