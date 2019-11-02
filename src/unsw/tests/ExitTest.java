package unsw.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import state.CanWinState;
import state.EndState;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Exit;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Sword;
import wincheck.AndWinCheck;
import wincheck.OrWinCheck;
import wincondition.EnemyWin;
import wincondition.GoldWin;
import wincondition.SwitchWin;

public class ExitTest {
	@Test
	void ExitTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		dungeon.addWinCondition(new EnemyWin());
		dungeon.addWinCondition(new GoldWin());
		Player player = new Player(dungeon,5,4);
		player.addObserver(new Exit(5,5));
		player.moveDown();
		assertEquals(false,dungeon.getState() instanceof EndState , "Trying to exit when win state not entered");
	}
	
	@Test
	void ExitTest1() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		dungeon.addWinCondition(new EnemyWin());
		dungeon.addWinCondition(new GoldWin());
		Player player = new Player(dungeon,5,4);
		player.addObserver(new Exit(5,5));
		player.collectGold();
		player.killEnemy();
		player.moveDown();
		assertEquals(true,dungeon.getState() instanceof EndState , "Trying to exit when win state entered");
	}
	
	
}
