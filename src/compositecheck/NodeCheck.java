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
	@Override
	public boolean check() {
		return checker.canWin(SubChecks);
	}

	@Override
	public void addCheck(CompositeCheck obj) {
		SubChecks.add(obj);
		
	}

}
