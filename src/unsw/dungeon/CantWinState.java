package unsw.dungeon;

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
		if(dungeon.allGoldCollected() && dungeon.allSwitchesActivated()) {
			System.out.println("Can win now");
			dungeon.setState(dungeon.getCanWinState());
		}
	}

	@Override
	public void activateSwitch() {
		System.out.println("Added switch");
		dungeon.addSwitch();
		if(dungeon.allGoldCollected() && dungeon.allSwitchesActivated()) {
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

}
