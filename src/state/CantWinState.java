package state;

import unsw.dungeon.Dungeon;

public class CantWinState implements State{

	Dungeon dungeon;
	/**
	 * Constructor for the state when a player is unable to exit
	 * @param dungeon reference of the reference
	 */
	public CantWinState(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	/**
	 * if player can't win and tries to exit, it fails
	 */
	@Override
	public void exit() {
		System.out.println("Can't exit yet");
	}

	/**
	 * Increments gold counter. If the player has met the win requirements, dungeon enters win state
	 */
	@Override
	public void collectGold() {
		System.out.println("Added gold");
		dungeon.addGold();
		if(dungeon.canWin()) {
			System.out.println("Can win now");
			dungeon.setState(dungeon.getCanWinState());
		}
	}

	/**
	 * Increments switch counter. If the player has met the win requirements, dungeon enters win state
	 */
	@Override
	public void activateSwitch() {
		System.out.println("Added switch");
		dungeon.addSwitch();
		if(dungeon.canWin()) {
			System.out.println("Can win now");
			dungeon.setState(dungeon.getCanWinState());
		}
		
	}

	/**
	 * Decrements switch counter.
	 */
	@Override
	public void deactivateSwitch() {
		System.out.println("Removed Switch");
		dungeon.removeSwitch();
	}

	/**
	 * If player dies, dungeon enters end state
	 */
	@Override
	public void die() {
		System.out.println("Died");
		dungeon.setState(dungeon.getEndState());
	}

	/**
	 * If player has killed enemy, increment killed enemy counter and if the player has met winning requirements, dungeon enters win state.
	 */
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
