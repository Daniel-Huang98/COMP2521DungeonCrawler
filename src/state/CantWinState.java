package state;

import unsw.dungeon.Dungeon;

public class CantWinState implements State{

	Dungeon dungeon;
	
	public CantWinState(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	@Override
	public void exit() {
		System.out.println("Can't exit yet");
	}

	@Override
	public void collectGold() {
		System.out.println("Added gold");
		dungeon.addGold();
		if(dungeon.canWin()) {
			System.out.println("Can win now");
			dungeon.setState(dungeon.getCanWinState());
		}
	}

	@Override
	public void activateSwitch() {
		System.out.println("Added switch");
		dungeon.addSwitch();
		if(dungeon.canWin()) {
			System.out.println("Can win now");
			dungeon.setState(dungeon.getCanWinState());
		}
		
	}

	@Override
	public void deactivateSwitch() {
		System.out.println("can;t win Removed Switch");
		dungeon.removeSwitch();
	}

	@Override
	public void die() {
		System.out.println("Died");
		dungeon.setState(dungeon.getEndState());
	}

	@Override
	public void killEnemy() {
		System.out.println("killed enemy");
		dungeon.killedEnemy();
		if(dungeon.canWin()) {
			System.out.println("Can win now");
			dungeon.setState(dungeon.getCanWinState());
		}
	}

}
