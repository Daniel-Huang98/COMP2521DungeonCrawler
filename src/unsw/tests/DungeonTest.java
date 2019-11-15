package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import wincheck.AndWinCheck;
import wincheck.OrWinCheck;
import wincondition.EnemyWin;
import wincondition.GoldWin;
import wincondition.SwitchWin;

class DungeonTest {
	
	
	
	/**
	 * tests that dungeon can detect when all enemies have been killed
	 */
	@Test
	void EnemyWinTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalEnemies();
		assertEquals(false,dungeon.allEnemiesKilled() , "add test 2");
	}
	
	/**
	 * tests that dungeon can detect when all enemies have been killed
	 */
	@Test
	void EnemyWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalEnemies();
		dungeon.killedEnemy();
		assertEquals(true,dungeon.allEnemiesKilled() , "add test 2");
	}
	
	/**
	 * tests that dungeon can detect when all gold has been collected
	 */
	@Test
	void GoldWinTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalGold();
		assertEquals(false,dungeon.allGoldCollected(), "add test 2");
	}
	
	/**
	 * tests that dungeon can detect when all gold has been collected
	 */
	@Test
	void GoldWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalGold();
		dungeon.addGold();
		assertEquals(true,dungeon.allGoldCollected() , "add test 2");
	}
	
	/**
	 * tests that dungeon can detect when all switches have been activated
	 */
	@Test
	void SwitchWinTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalSwitch();
		assertEquals(false,dungeon.allSwitchesActivated() , "add test 2");
	}
	
	/**
	 * tests that dungeon can detect when all switches have been activated
	 */
	@Test
	void SwitchWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalSwitch();
		dungeon.addSwitch();
		assertEquals(true,dungeon.allSwitchesActivated(), "add test 2");
	}

	/**
	 * tests that dungeon can detect when a switch has been deactivated
	 */
	@Test
	void SwitchWinTest3() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/testgoals1.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalSwitch();
		dungeon.deactivateSwitch();
		assertEquals(false,dungeon.allSwitchesActivated() , "add test 2");
	}
	
	@Test
	void ORGoalTest0() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ORTest.json",dungeon);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		assertEquals(false, dungeon == null);
		assertEquals(false,dungeon.canWin() , "enemy or gold, activate none");
	}
	
	@Test
	void ORGoalTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ORTest.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		dungeon.addGold();
		assertEquals(true,dungeon.canWin() , "enemy or gold, activate gold");
	}
	
	@Test
	void ORGoalTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ORTest.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		dungeon.killedEnemy();
		assertEquals(true,dungeon.canWin() , "enemy or gold, activate enemy");
	}
	
	@Test
	void ORGoalTest3() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ORTest.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		dungeon.addGold();
		dungeon.killedEnemy();
		assertEquals(true,dungeon.canWin() , "enemy or gold, activate both");
	}
	
	@Test
	void AndGoalWinTest0() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ANDTest.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		assertEquals(false,dungeon.canWin() , "enemy and gold, activate none");
	}
	
	@Test
	void AndGoalWinTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ANDTest.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		dungeon.addGold();
		assertEquals(false,dungeon.canWin() , "enemy and gold, activate gold");
	}
	
	@Test
	void AndGoalWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ANDTest.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.killedEnemy();
		assertEquals(false,dungeon.canWin() , "enemy and gold, activate enemy");
	}
	
	@Test
	void AndGoalWinTest3() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ANDTest.json",dungeon);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		dungeon.addGold();
		dungeon.killedEnemy();
		assertEquals(true,dungeon.canWin() , "enemy and gold, activate both");
	}
	
	
	@Test
	void ComplexWinTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ComplexTest.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		assertEquals(false,dungeon.canWin() , "Complex goal exit or (gold and enemies), activate nothing");
	}
	
	@Test
	void ComplexWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ComplexTest.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		dungeon.exit();
		assertEquals(true,dungeon.canWin() , "Complex goal exit or (gold and enemies), exit");
	}
	
	@Test
	void ComplexWinTest3() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ComplexTest.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		dungeon.addGold();
		dungeon.killedEnemy();
		assertEquals(true,dungeon.canWin() , "Complex goal exit or (gold and enemies), gold and enemies");
	}
	
	@Test
	void ComplexWinTest4() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ComplexTest.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		dungeon.killedEnemy();
		assertEquals(false,dungeon.canWin() , "Complex goal exit or (gold and enemies), just enemies");
	}
	
	@Test
	void ComplexWinTest5() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("./../dungeons/ComplexTest.json",dungeon);
		assertEquals(false, dungeon == null);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		dungeon.addGold();
		assertEquals(false,dungeon.canWin() , "Complex goal exit or (gold and enemies), just gold");
	}
	
	
	

}
