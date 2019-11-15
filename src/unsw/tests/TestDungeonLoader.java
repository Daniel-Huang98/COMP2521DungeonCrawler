package unsw.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import compositecheck.CompositeCheck;
import compositecheck.LeafCheck;
import compositecheck.NodeCheck;
import unsw.dungeon.Dungeon;
import wincheck.AndWinCheck;
import wincheck.OrWinCheck;
import wincondition.EnemyWin;
import wincondition.ExitWin;
import wincondition.GoldWin;
import wincondition.SwitchWin;

public class TestDungeonLoader {
	JSONObject json = null;
	Dungeon dungeon = null;
	public TestDungeonLoader(String filename, Dungeon dungeon){
		try {
	        json = new JSONObject(new JSONTokener(new FileReader("testfiles/" + filename)));
	        JSONObject goalCondition = json.getJSONObject("goal-condition");
	        this.dungeon = dungeon;
	        this.dungeon.setWinCheck(loadGoals(goalCondition));
		} catch(Exception e) {
			System.out.println("failed to load file");
		}
    }
	
	private CompositeCheck loadGoals(JSONObject goalCondition) {
    	CompositeCheck obj = null;
    	String goal = goalCondition.getString("goal");
    	JSONArray subgoals = null;
    	if(goalCondition.has("subgoals")) {
    		subgoals = goalCondition.getJSONArray("subgoals");
    	} 
        if(subgoals == null) {
        	System.out.println("goal: " + goal);
        	switch(goal) {
        		case "enemy":
        			obj = new LeafCheck(this.dungeon,new EnemyWin());
        			break;
        		case "treasure":
        			obj = new LeafCheck(dungeon,new GoldWin());
        			break;
        		case "exit":
        			obj = new LeafCheck(dungeon,new ExitWin());
        			break;
        		case "switch":
        			obj = new LeafCheck(dungeon,new SwitchWin());
        			break;
        	}
        } else {
        	switch(goal) {
        		case "AND":
        			obj = new NodeCheck(dungeon, new AndWinCheck());
        			break;
        		case "OR":
        			obj = new NodeCheck(dungeon, new OrWinCheck());
        			break;
        	}
        	for(int i = 0; i < subgoals.length(); i++) {
				obj.addCheck(loadGoals(subgoals.getJSONObject(i)));
			}
        }
        return obj;
    }
	
	
}
