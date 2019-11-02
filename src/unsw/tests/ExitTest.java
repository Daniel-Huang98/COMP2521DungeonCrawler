package unsw.tests;

import org.junit.jupiter.api.Test;

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
	void AndTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new AndWinCheck());
		dungeon.addWinCondition(new EnemyWin());
		dungeon.addWinCondition(new GoldWin());
		Player player = new Player(dungeon,5,4);
		player.collectGold();
		player.killEnemy();
		player.exit();	
		player.moveDown();
	}
	
	@Test
	void OrTest() {
		Dungeon dungeon = new Dungeon(100, 100);
		dungeon.setWinCheck(new OrWinCheck());
		Player player = new Player(dungeon,5,4);
		Potion potion = new Potion(5,6);
		Sword sword = new Sword(5,6);
		player.addObserver(sword);
		player.moveDown();
	}
	
	
}
