package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;

class EnemyTest {

	@Test
	void deathBattleTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		player.setSword(sword);
		assertEquals(sword,player.getSword() , "add test 2");
	}
	
	@Test
	void potionBattleTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		player.setSword(sword);
		assertEquals(sword,player.getSword() , "add test 2");
	}
	
	@Test
	void swordBattleTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon,5,5);
		Sword sword = new Sword(4,4);
		player.setSword(sword);
		assertEquals(sword,player.getSword() , "add test 2");
	}

}
