package unsw.dungeon;

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
		System.out.println("Shouldn't be able to add gold");
		
	}

	@Override
	public void activateSwitch() {
		System.out.println("Shouldn't be able to add switches");
		
	}

	@Override
	public void deactivateSwitch() {
		if(dungeon.allGoldCollected() && dungeon.allSwitchesActivated()) {
			System.out.println("Can't win now");
			dungeon.setState(dungeon.getCantWinState());
		}
		dungeon.removeSwitch();
	}

	@Override
	public void die() {
		System.out.println("Died");
		dungeon.setState(dungeon.getEndState());
		
	}

}
