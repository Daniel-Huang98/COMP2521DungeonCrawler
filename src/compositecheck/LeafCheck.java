package compositecheck;

import unsw.dungeon.Dungeon;
import wincondition.WinCondition;

public class LeafCheck implements CompositeCheck{
	Dungeon dungeon;
	WinCondition checker;
	
	public LeafCheck(Dungeon obj, WinCondition checker){
		this.checker = checker;
		this.dungeon = obj;
	}

	@Override
	public boolean check() {
		return checker.canWin(dungeon);
	}

	@Override
	public void addCheck(CompositeCheck obj) {
		return;
		
	}

}
