package movement;

import java.util.ArrayList;

import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;

public class RedGhostFurther implements Movement{
	@Override
	public ArrayList<ArrayList<Entity>> moveCharacter(Enemy e, Entity dest,int height, int width, ArrayList<ArrayList<Entity>> map) {
		Dijkstra pathing = new Dijkstra(height, width, map);
		pathing.dijkstra(e);
    	int curr = 29*map.get(0).size()+0;
    	int next = pathing.getFrom()[curr];
    	int counter = 0;
    	
    	//backtrack the traceback array
    	while (next != e.getY()*map.get(0).size()+e.getX() && counter < map.size()*map.get(0).size()) {
    		curr = next;
    		if (curr == -1) break;
    		next = pathing.getFrom()[curr];
    		counter++;
    	}
    	if (curr == -1) return map;
    	//make sure no infinite loop occurs
    	if (counter == map.size()*map.get(0).size()) {
    		return map;
    	}
    	
    	//calculate next coordinate, move the enemy and update the map
    	int nextY = curr/map.get(0).size();
    	int nextX = curr%(map.get(0).size());
    	map.get(e.getY()).set(e.getX(),null);
    	e.setMove(nextX, nextY);
	    map.get(e.getY()).set(e.getX(),(Entity)e);		
	    return map;
	}
}
