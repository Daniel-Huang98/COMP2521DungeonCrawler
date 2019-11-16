package movement;

import java.util.ArrayList;

import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Wall;

/**
 * Pink ghost scatter movement in Pacman
 */
public class PinkGhostFurther implements Movement{
	private int lastX = -1;
	private int lastY = -1;
	
    /**
     * Check if the new movement is a valid index
     * @param x : x coordinate
     * @param y : y coordinate
     * @return true if it is within the index bounds
     */
    public boolean checkBounds(int x, int y,ArrayList<ArrayList<Entity>> map) {
    	return (x < map.get(0).size() && x >= 0 && y < map.size() && y >= 0);
    }
	
	/**
	 * Moves to a set wall on the map, once it gets to the map, it moves in a circle
     * @param e : the enemy that is moving
     * @param dest : the destination entity that the enemy is moving away from
     * @param height : height of dungeon
     * @param width : width of dungeon
     * @param map : entity map
     * @return : updated map after the enemy has moved
     */
	@Override
	public ArrayList<ArrayList<Entity>> moveCharacter(Enemy e, Entity dest,int height, int width, ArrayList<ArrayList<Entity>> map) {
		Dijkstra pathing = new Dijkstra(height, width, map);
		int g[][] = pathing.getGraph();
		if (lastX != -1 && lastY != -1) {
	    	g[e.getY()*map.get(0).size()+e.getX()][lastY*map.get(0).size()+lastX] = 0;
	    	g[lastY*map.get(0).size()+lastX][e.getY()*map.get(0).size()+e.getX()] = 0;
		}
		pathing.dijkstra(e);
		int curr = 1*map.get(0).size()+27;
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
    	int oldX = e.getX();
		int oldY = e.getY(); 
		int dX = nextX-oldX;
		int dY = nextY-oldY;
    	lastX = oldX;
    	lastY = oldY;
		if (map.get(nextY).get(nextX) instanceof Wall) {
			//try other directions
			if (dY == 0) {
				if (checkBounds(oldX, oldY+1,map) && map.get(oldY+1).get(oldX) == null && pathing.isReachable(oldY+1, oldX, oldY, oldX)) {
					e.setMove(oldX,oldY+1);
				}
				else if (checkBounds(oldX, oldY-1,map) && map.get(oldY - 1).get(oldX) == null && pathing.isReachable(oldY-1, oldX, oldY, oldX)) {
					e.setMove(oldX,oldY-1);
				}
				else {
					e.setMove(oldX + (dX)*-1, oldY + (dY)*-1);
				}
			}
			
			//try other directions
			else if (dX == 0) {
				if (checkBounds(oldX+1, oldY,map) && map.get(oldY).get(oldX+1) == null && pathing.isReachable(oldY, oldX+1, oldY, oldX)) {
					e.setMove(oldX+1,oldY);
				}
				else if (checkBounds(oldX-1, oldY,map) && map.get(oldY).get(oldX-1) == null && pathing.isReachable(oldY, oldX-1, oldY, oldX)) {
					e.setMove(oldX-1,oldY);
				}
				else {
					e.setMove(oldX + (dX)*-1, oldY + (dY)*-1);
				}
			}
		}
		else {
			e.setMove(nextX, nextY);
		}
		map.get(e.getY()).set(e.getX(),(Entity)e);
	    return map;
	}
}
