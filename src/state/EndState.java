package state;

import unsw.dungeon.Dungeon;

public class EndState implements State {
	
	Dungeon dungeon;
	
	/**
	 * Constructor for dungeon end state
	 * @param dungeon reference for the dungeon
	 */
	public EndState(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	/**
	 * does nothing for now
	 */
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * does nothing for now
	 */
	@Override
	public void collectGold() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * does nothing for now
	 */
	@Override
	public void activateSwitch() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * does nothing for now
	 */
	@Override
	public void deactivateSwitch() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * does nothing for now
	 */
	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * does nothing for now
	 */
	@Override
	public void killEnemy() {
		// TODO Auto-generated method stub
		
	}

}
