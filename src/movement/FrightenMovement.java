package movement;
import java.util.*;

import unsw.dungeon.Door;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Portal;
import unsw.dungeon.Wall;

import java.lang.*; 
import java.io.*; 

/**
 * A class that tells the enemy to move away from a certain entity.
 * Contains a 1 to 1 entity map as well as height and width of the
 * dungeon.
 */
public class FrightenMovement implements Movement{
	private int lastX = -1;
	private int lastY = -1;
	private int dX = 0;
	private int dY = 0;
	boolean checkBehind = true;

	/**
	 * Constructs a Further object
	 * @param height : height of the dungeon
     * @param width : width of the dungeon
     * @param map : 1 to 1 entity map of the dungeon
	 */
	public FrightenMovement() {
	}
	
    /**
     * Check if the new movement is a valid index
     * @param x : x coordinate
     * @param y : y coordinate
     * @return true if it is within the index bounds
     */
    public boolean checkBounds(int x, int y,ArrayList<ArrayList<Entity>> map) {
    	return (x < map.get(0).size() && x >= 0 && y < map.size() && y >= 0);
    }
    
    public boolean getCheckBehind() {
    	return checkBehind;
    }
    
    public void setCheckBehind(boolean flag) {
    	checkBehind = flag;
    }
    
    public void setPrev(int x, int y) {
    	lastX = x;
    	lastY = y;
    }
	
    /**
     * Move the character away from the entity by inverting the direction after the
     * player has the potion. At intersection, move in pseudo-random fashion. Ghost
     * can't move backwards.
     * @param e : the enemy that is moving
     * @param dest : the destination entity that the enemy is moving away from
     * @param height : height of dungeon
     * @param width : width of dungeon
     * @param map : entity map
     * @return : updated map after the enemy has moved
     */
	@Override
	public ArrayList<ArrayList<Entity>> moveCharacter(Enemy e, Entity dest,int height, int width, ArrayList<ArrayList<Entity>> map) {
    	if (checkBehind) {
    		map = checkAlternatives(e,dest,height,width,map);
    	}
    	else if (lastX == 0 && lastY == 14) {
    		System.out.println("portal");
    		map.get(lastY).set(lastX,null);
    		e.setMove(26, 14);
    		map.get(e.getY()).set(e.getX(),(Entity)e);
    	}
    	else if (lastX == 27 && lastY == 14) {
    		System.out.println("portal");
    		map.get(lastY).set(lastX,null);
    		e.setMove(1, 14);
    		map.get(e.getY()).set(e.getX(),(Entity)e);
    	}
    	else if (checkIntersection(map)) {
    		map = pseudoRandom(e,map);   			
    	}
    	else {
    		map.get(e.getY()).set(e.getX(),null);
    		e.setMove(lastX+dX,lastY+dY);
    		setChange(lastX+dX, lastY+dY);
    		map.get(e.getY()).set(e.getX(),(Entity)e);
    	}
		return map;
	}
	
	/**
	 * Set previous direction
	 * @param newX : new X coordinate to move to
	 * @param newY : new Y coordinate to move to
	 */
	public void setChange(int newX, int newY) {
		dX = newX - lastX;
		dY = newY - lastY;
	}
	
	/**
	 * Check if the ghost is at an intersection
	 * @param map : entity map
	 * @return : boolean representing if it is an intersection or not
	 */
	public boolean checkIntersection(ArrayList<ArrayList<Entity>> map) {
		if (dX == 0) {
			if (!(map.get(lastY).get(lastX+1) instanceof Wall) || !(map.get(lastY).get(lastX-1) instanceof Wall))
				return true;
		}
		else {
			if (!(map.get(lastY+1).get(lastX) instanceof Wall) || !(map.get(lastY-1).get(lastX) instanceof Wall))
				return true;
		}
		return false;
	}
	
	/**
	 * Figure out where to move after the player picks up the potion
     * @param e : the enemy that is moving
     * @param dest : the destination entity that the enemy is moving away from
     * @param height : height of dungeon
     * @param width : width of dungeon
     * @param map : entity map
     * @return : updated map after the enemy has moved
	 */
	public ArrayList<ArrayList<Entity>> checkAlternatives(Enemy e, Entity dest,int height, int width, ArrayList<ArrayList<Entity>> map) {
		Dijkstra pathing = new Dijkstra(height, width, map);
		int g[][] = pathing.getGraph();
		if (lastX != -1 && lastY != -1) {
	    	g[e.getY()*map.get(0).size()+e.getX()][lastY*map.get(0).size()+lastX] = 0;
	    	g[lastY*map.get(0).size()+lastX][e.getY()*map.get(0).size()+e.getX()] = 0;
		}
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
    	int oldX = e.getX();
		int oldY = e.getY(); 
		int dX = nextX-oldX;
		int dY = nextY-oldY;
    	lastX = oldX;
    	lastY = oldY;
		int oppositeX = oldX + (dX)*-1;
		int oppositeY = oldY + (dY)*-1;
    	
		if (checkBounds(oppositeX, oppositeY, map) && map.get(oppositeY).get(oppositeX) == null && pathing.isReachable(oppositeY, oppositeX, oldY, oldX)) {
			e.setMove(oppositeX, oppositeY);
			setChange(oppositeX,oppositeY);
		}
		//try other directions
		else if (dY == 0) {
			if (checkBounds(oldX, oldY+1,map) && map.get(oldY+1).get(oldX) == null && !(map.get(oldY+1).get(oldX) instanceof Wall)) {
				e.setMove(oldX,oldY+1);
				setChange(oldX,oldY+1);
			}
			else if (checkBounds(oldX, oldY-1,map) && map.get(oldY - 1).get(oldX) == null && !(map.get(oldY-1).get(oldX) instanceof Wall)) {
				e.setMove(oldX,oldY-1);
				setChange(oldX,oldY-1);
			}
			else {
				e.setMove(nextX, nextY);
				setChange(nextX, nextY);
			}
		}
		
		//try other directions
		else if (dX == 0) {
			if (checkBounds(oldX+1, oldY,map) && map.get(oldY).get(oldX+1) == null && !(map.get(oldY).get(oldX+1) instanceof Wall)) {
				e.setMove(oldX+1,oldY);
				setChange(oldX+1,oldY);
			}
			else if (checkBounds(oldX-1, oldY,map) && map.get(oldY).get(oldX-1) == null && !(map.get(oldY).get(oldX-1) instanceof Wall)) {
				e.setMove(oldX-1,oldY);
				setChange(oldX-1,oldY);
			}
			else {
				e.setMove(nextX, nextY);
				setChange(nextX, nextY);
			}
		}
		checkBehind = false;
		map.get(e.getY()).set(e.getX(),(Entity)e);
		return map;
	}
	
	public static int getRandomNumber(){
	    return (int)(Math.round(Math.random()*3));
	}
	
	/**
	 * Choose a pseudoRandom direction base of a random number generator. If
	 * unreachable, run the random number generator and try again
	 * @param e : enemy object that needs to move
	 * @param map : entity map
	 * @return : updated map after the enemy has moved
	 */
	public ArrayList<ArrayList<Entity>> pseudoRandom(Enemy e, ArrayList<ArrayList<Entity>> map) {
		int rand = getRandomNumber();
		//System.out.println(rand);
		map.get(e.getY()).set(e.getX(),null);
		switch (rand) {
		case 0:
			if ((lastX+(dX*-1) == lastX+1 && lastY+(dY*-1) == lastY) || !checkBounds(lastX+1,lastY,map) || 
					map.get(lastY).get(lastX+1) instanceof Wall) {
				pseudoRandom(e, map);
			}
			else {
	    		e.setMove(lastX+1,lastY);
	    		setChange(lastX+1,lastY);
	    		map.get(e.getY()).set(e.getX(),(Entity)e);
			}
			break;
		case 1:
			if ((lastX+(dX*-1) == lastX-1 && lastY+(dY*-1) == lastY) || !checkBounds(lastX-1,lastY,map) || 
					map.get(lastY).get(lastX-1) instanceof Wall) {
				pseudoRandom(e, map);
			}
			else {
	    		e.setMove(lastX-1,lastY);
	    		setChange(lastX-1,lastY);
	    		map.get(e.getY()).set(e.getX(),(Entity)e);
			}
			break;
		case 2:
			if ((lastX+(dX*-1) == lastX && lastY+(dY*-1) == lastY+1) || !checkBounds(lastX,lastY+1,map) || 
					map.get(lastY+1).get(lastX) instanceof Wall) {
				pseudoRandom(e, map);
			}
			else {
	    		e.setMove(lastX,lastY+1);
	    		setChange(lastX,lastY+1);
	    		map.get(e.getY()).set(e.getX(),(Entity)e);
			}
			break;
		case 3:
			if ((lastX+(dX*-1) == lastX && lastY+(dY*-1) == lastY-1) || !checkBounds(lastX,lastY-1,map) 
					|| map.get(lastY-1).get(lastX) instanceof Wall) {
				pseudoRandom(e, map);
			}
			else {
	    		e.setMove(lastX,lastY-1);
	    		setChange(lastX,lastY-1);
	    		map.get(e.getY()).set(e.getX(),(Entity)e);
			}
			break;
		default:
			pseudoRandom(e, map);
			break;
		}
		return map;
	}
}
