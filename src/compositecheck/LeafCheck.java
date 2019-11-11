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
		
		try {
			if(dungeon == null) {
				throw new AssertionError("dungon null");
			}
			if(checker == null) {
				throw new AssertionError("checker null");
			}
			return checker.canWin(dungeon);
		} catch(NullPointerException e) {
			System.out.println("null exception at: " + this.checker.getClass());
		} catch(AssertionError e) {
			System.out.println("Assertion fail" + e.getMessage());
		}
		return false;
	}

	@Override
	public void addCheck(CompositeCheck obj) {
		return;
		
	}

}
