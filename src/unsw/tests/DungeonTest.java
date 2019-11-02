package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

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
	
	@Test
	void EnemyWinTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new EnemyWin());
		dungeon.incTotalEnemies();
		assertEquals(false,dungeon.canWin() , "add test 2");
	}
	
	@Test
	void EnemyWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new EnemyWin());
		dungeon.incTotalEnemies();
		dungeon.killedEnemy();
		assertEquals(true,dungeon.canWin() , "add test 2");
	}
	
	@Test
	void GoldWinTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new GoldWin());
		dungeon.incTotalGold();
		assertEquals(false,dungeon.canWin(), "add test 2");
	}
	
	@Test
	void GoldWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new GoldWin());
		dungeon.incTotalGold();
		dungeon.addGold();
		assertEquals(true,dungeon.canWin() , "add test 2");
	}
	
	@Test
	void SwitchWinTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new SwitchWin());
		dungeon.incTotalSwitch();
		assertEquals(false,dungeon.canWin() , "add test 2");
	}
	
	@Test
	void SwitchWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new SwitchWin());
		dungeon.incTotalSwitch();
		dungeon.addSwitch();
		assertEquals(true,dungeon.canWin(), "add test 2");
	}

	@Test
	void SwitchWinTest3() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new SwitchWin());
		dungeon.incTotalSwitch();
		dungeon.deactivateSwitch();
		assertEquals(false,dungeon.canWin() , "add test 2");
	}

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
	
	@Test
	void CantWinTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new SwitchWin());
		dungeon.incTotalSwitch();
		dungeon.activateSwitch();
		dungeon.setState(dungeon.getCanWinState());
		dungeon.deactivateSwitch();
		assertEquals(false,dungeon.canWin(), "add test 2");
	}

	@Test
	void CanWinTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new EnemyWin());
		dungeon.addWinCondition(new GoldWin());
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		Player player = new Player(dungeon,5,4);
		assertEquals(true,dungeon.getState() instanceof CantWinState , "add test 2");
		player.collectGold();
		player.killEnemy();
		assertEquals(true,dungeon.getState() instanceof CanWinState , "add test 2");

	}

	@Test
	void ExitTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new EnemyWin());
		dungeon.addWinCondition(new GoldWin());
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		Player player = new Player(dungeon,5,4);
		player.collectGold();
		player.killEnemy();
		player.exit();	
		assertEquals(true,dungeon.getState() instanceof EndState , "add test 2");
	}
	
	@Test
	void OrTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		dungeon.addWinCondition(new EnemyWin());
		dungeon.addWinCondition(new GoldWin());
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		Player player = new Player(dungeon,5,4);
		player.killEnemy();
		player.exit();	
		assertEquals(true,dungeon.getState() instanceof EndState , "add test 2");
	}
	
	@Test
	void OrTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		dungeon.addWinCondition(new EnemyWin());
		dungeon.addWinCondition(new GoldWin());
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		Player player = new Player(dungeon,5,4);
		player.collectGold();
		player.exit();	
		assertEquals(true,dungeon.getState() instanceof EndState , "add test 2");
	}
	
	@Test
	void AndTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new EnemyWin());
		dungeon.addWinCondition(new GoldWin());
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		Player player = new Player(dungeon,5,4);
		player.collectGold();
		player.killEnemy();
		player.exit();	
		assertEquals(true,dungeon.getState() instanceof EndState , "add test 2");
	}
	
	@Test
	void AndTest2() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new EnemyWin());
		dungeon.addWinCondition(new GoldWin());
		dungeon.incTotalGold();
		dungeon.incTotalEnemies();
		Player player = new Player(dungeon,5,4);
		player.collectGold();
		player.exit();	
		assertEquals(true,dungeon.getState() instanceof CantWinState , "add test 2");
	}
	

}
