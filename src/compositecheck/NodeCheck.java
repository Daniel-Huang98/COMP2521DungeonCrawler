package compositecheck;

import java.util.ArrayList;
import unsw.dungeon.Dungeon;
import wincheck.WinCheck;

/**
 * handles ANDs and ORs
 */
public class NodeCheck implements CompositeCheck{
	Dungeon dungeon;
	WinCheck checker;
	ArrayList<CompositeCheck> SubChecks;
	
	/**
	 * Constructs a node object
	 * @param obj : A Dungeon object
	 * @param checker : A Wincheck object
	 */
	public NodeCheck(Dungeon obj, WinCheck checker){
		this.checker = checker;
		this.SubChecks = new ArrayList<CompositeCheck>();
		this.dungeon = obj;
	}
	
	public ArrayList<CompositeCheck> getSubCheck(){
		return this.SubChecks;
	}
	
	/**
	 * Recursively checks if user has achieved all goals
	 * @return : boolean that represent if the player has won or not
	 */
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
