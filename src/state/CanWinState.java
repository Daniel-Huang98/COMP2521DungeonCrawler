package state;

import unsw.dungeon.Dungeon;

public class CanWinState implements State{
	
	Dungeon dungeon;
	
	/**
	 * Constructor for the win state for the dungeon
	 * @param dungeon Reference of the dungeon
	 */
	public CanWinState(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	/**
	 * When in win state and player exits, the dungeon enters end state and player wins
	 */
	@Override
	public void exit() {
		System.out.println("won the game");
		dungeon.setState(dungeon.getEndState());
		
	}

	/**
	 * Increments gold counter
	 */
	@Override
	public void collectGold() {
		dungeon.addGold();
		System.out.println("Shouldn't be able to add gold or don't need to add gold");
		
	}
	
	/**
	 * Increments switch counter
	 */
	@Override
	public void activateSwitch() {
		dungeon.addSwitch();
		System.out.println("Shouldn't be able to add switches or don't need to add gold");
		
	}

	/**
	 * Decrement switch counter. If switch was part of winning condition, dungeon reenters 
	 */
	@Override
	public void deactivateSwitch() {
		dungeon.removeSwitch();
		if(!dungeon.canWin()) {
			System.out.println("Can't win now");
			dungeon.setState(dungeon.getCantWinState());
		}
		
	}

	/**
	 * If player dies, the player loses and dungeon enters end state.
	 */
	@Override
	public void die() {
		System.out.println("Died");
		dungeon.setState(dungeon.getEndState());
		
	}

	/**
	 * Increment the killed enemy counter
	 */
	@Override
	public void killEnemy() {
		dungeon.killedEnemy();
		System.out.println("Shouldn't be able to kill enemy or don't need to kill enemy");
	}

}
