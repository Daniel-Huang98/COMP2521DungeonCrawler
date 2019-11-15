package compositecheck;

import java.util.ArrayList;
import unsw.dungeon.Dungeon;
import wincheck.WinCheck;

public class NodeCheck implements CompositeCheck{
	Dungeon dungeon;
	WinCheck checker;
	ArrayList<CompositeCheck> SubChecks;
	
	public NodeCheck(Dungeon obj, WinCheck checker){
		this.checker = checker;
		this.SubChecks = new ArrayList<CompositeCheck>();
		this.dungeon = obj;
	}
	
	public ArrayList<CompositeCheck> getSubCheck(){
		return this.SubChecks;
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
			if(SubChecks == null) {
				throw new AssertionError("SubChecks null");
			}
			return checker.canWin(this);
		} catch(NullPointerException e) {
			System.out.println("null exception at: " + this.checker.getClass());
		} catch(AssertionError e) {
			System.out.println("assert failed  " + e.getMessage());
		}
		return false;
		
	}

	@Override
	public void addCheck(CompositeCheck obj) {
		this.SubChecks.add(obj);
		
	}

}
