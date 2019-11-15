package movement;
import java.util.*;

import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;

import java.lang.*; 
import java.io.*; 

/**
 * A class that tells the enemy to move towards a certain entity.
 * Contains a 1 to 1 entity map as well as height and width of the
 * dungeon.
 */
public class Closer implements Movement{
	
	
	/**
	 * Constructs a Closer object
	 * @param height : height of the dungeon
     * @param width : width of the dungeon
     * @param map : 1 to 1 entity map of the dungeon
	 */
	public Closer() {
		
	}

    /**
     * Move the character away from the entity by using the output of
     * the Dijkstra algorithm.
     * @param e : the enemy that is moving
     * @param dest : the destination entity that the enemy is moving away from
     * @return : updated map after the enemy has moved
     */
	@Override
	public ArrayList<ArrayList<Entity>> moveCharacter(Enemy e, Entity dest,int height, int width, ArrayList<ArrayList<Entity>> map) {
		Dijkstra pathing = new Dijkstra(height, width, map);
		pathing.dijkstra(e);
    	int curr = dest.getY()*map.get(0).size()+dest.getX();
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
    	int g[][] = pathing.getGraph();
    	g[e.getY()*map.get(0).size()+e.getX()][nextY*map.get(0).size()+nextX] = 0;
    	g[nextY*map.get(0).size()+nextX][e.getY()*map.get(0).size()+e.getX()] = 0;
    	e.setMove(nextX, nextY);
	    map.get(e.getY()).set(e.getX(),(Entity)e);		
	    return map;
	}


}
