package state;

import unsw.dungeon.Dungeon;

public class CanWinState implements State{
	
	Dungeon dungeon;
	
	public CanWinState(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public void exit() {
		System.out.println("won the game");
		dungeon.setState(dungeon.getEndState());
		
	}

	@Override
	public void collectGold() {
		dungeon.addGold();
		System.out.println("Shouldn't be able to add gold or don't need to add gold");
		
	}

	@Override
	public void activateSwitch() {
		dungeon.addSwitch();
		System.out.println("Shouldn't be able to add switches or don't need to add gold");
		
	}

	@Override
	public void deactivateSwitch() {
		dungeon.removeSwitch();
		if(!dungeon.canWin()) {
			System.out.println("Can't win now");
			dungeon.setState(dungeon.getCantWinState());
		}
		
	}

	@Override
	public void die() {
		System.out.println("Died");
		dungeon.setState(dungeon.getEndState());
		
	}

	@Override
	public void killEnemy() {
		dungeon.killedEnemy();
		System.out.println("Shouldn't be able to kill enemy or don't need to kill enemy");
	}

}
