package movement;
import java.util.*;

import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;

import java.lang.*; 
import java.io.*; 

/**
 * A movement entity which contains a 1 to 1 entity map as 
 * well as an adjacency matrix generated from this map. The
 * class has a method which calculates the shortest path
 * from the source attribute.
 *
 */
public interface Movement {
	public ArrayList<ArrayList<Entity>> moveCharacter(Enemy e, Entity dest);
	public void setMap(ArrayList<ArrayList<Entity>> map);
}
