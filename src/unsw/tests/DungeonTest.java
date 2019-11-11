package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;

import state.CanWinState;
import state.CantWinState;
import state.EndState;
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
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalEnemies();
		assertEquals(false,dungeon.canWin() , "add test 2");
	}
	
	/**
	 * tests that dungeon can detect when all enemies have been killed
	 */
	@Test
	void EnemyWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalEnemies();
		dungeon.killedEnemy();
		assertEquals(true,dungeon.canWin() , "add test 2");
	}
	
	/**
	 * tests that dungeon can detect when all gold has been collected
	 */
	@Test
	void GoldWinTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalGold();
		assertEquals(false,dungeon.canWin(), "add test 2");
	}
	
	/**
	 * tests that dungeon can detect when all gold has been collected
	 */
	@Test
	void GoldWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalGold();
		dungeon.addGold();
		assertEquals(true,dungeon.canWin() , "add test 2");
	}
	
	/**
	 * tests that dungeon can detect when all switches have been activated
	 */
	@Test
	void SwitchWinTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalSwitch();
		assertEquals(false,dungeon.canWin() , "add test 2");
	}
	
	/**
	 * tests that dungeon can detect when all switches have been activated
	 */
	@Test
	void SwitchWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new SwitchWin());
		dungeon.incTotalSwitch();
		dungeon.addSwitch();
		assertEquals(true,dungeon.canWin(), "add test 2");
	}

	/**
	 * tests that dungeon can detect when a switch has been deactivated
	 */
	@Test
	void SwitchWinTest3() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalSwitch();
		dungeon.deactivateSwitch();
		assertEquals(false,dungeon.canWin() , "add test 2");
	}

	/**
	 * tests dungeon CantWinState
	 */
	@Test
	void CantWinTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new EnemyWin());
		dungeon.addWinCondition(new GoldWin());
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		assertEquals(true,dungeon.getState() instanceof CantWinState , "add test 2");
	}
	
	/**
	 * tests complex goals AND case
	 */
	@Test
	void CantWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalSwitch();
		dungeon.activateSwitch();
		dungeon.setState(dungeon.getCanWinState());
		dungeon.deactivateSwitch();
		assertEquals(false,dungeon.canWin(), "add test 2");
	}

	/**
	 * tests dungeon CanWinState
	 * Win state is reached when goals completed
	 */
	@Test
	void CanWinTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		Player player = new Player(dungeon,5,4);
		assertEquals(true,dungeon.getState() instanceof CantWinState , "add test 2");
		player.collectGold();
		player.killEnemy();
		assertEquals(true,dungeon.getState() instanceof CanWinState , "add test 2");

	}

	/**
	 * tests dungeon EndState
	 * End state is reached when goals completed + player exits
	 */
	@Test
	void ExitTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		Player player = new Player(dungeon,5,4);
		player.collectGold();
		player.killEnemy();
		player.exit();	
		assertEquals(true,dungeon.getState() instanceof EndState , "add test 2");
	}
	
	/**
	 * tests complex goals OR case
	 * tests some goals reached
	 */
	@Test
	void OrTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		Player player = new Player(dungeon,5,4);
		player.killEnemy();
		player.exit();	
		assertEquals(true,dungeon.getState() instanceof EndState , "add test 2");
	}
	
	/**
	 * tests complex goals OR case
	 * tests some goals reached
	 */
	@Test
	void OrTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		Player player = new Player(dungeon,5,4);
		player.collectGold();
		player.exit();	
		assertEquals(true,dungeon.getState() instanceof EndState , "add test 2");
	}
	
	/**
	 * tests complex goals AND case
	 * tests all goals reached
	 */
	@Test
	void AndTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		Player player = new Player(dungeon,5,4);
		player.collectGold();
		player.killEnemy();
		player.exit();	
		assertEquals(true,dungeon.getState() instanceof EndState , "add test 2");
	}
	
	/**
	 * tests complex goals AND case
	 * Not all goals reached
	 */
	@Test
	void AndTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		TestDungeonLoader test = new TestDungeonLoader("",dungeon);
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		Player player = new Player(dungeon,5,4);
		player.collectGold();
		player.exit();	
		assertEquals(true,dungeon.getState() instanceof CantWinState , "add test 2");
	}
	

}
