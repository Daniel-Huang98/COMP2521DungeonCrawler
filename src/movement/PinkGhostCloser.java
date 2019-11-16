package movement;

import java.util.ArrayList;

import unsw.dungeon.Boulder;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

public class PinkGhostCloser implements Movement{
	private int height;
	private int width;
	private int lastX = -1;
	private int lastY = -1;
	private int dx = 0;
	private int dy = 0;
	int nextX;
	int nextY;
	private ArrayList<ArrayList<Entity>> map = new ArrayList<ArrayList<Entity>>();
	
	private void setNext(int dx, int dy, int currX, int currY, ArrayList<ArrayList<Entity>> map) {
		// test if it exceed the map array
		if(currY + dy < 0) {
			if(dy == -4) dy = -1;
			if(currY + dy < 0) {
				dy = 0;
			}
		}
		if(currX+ dx < 0) {
			if(dx == -4) dx = -1;
			if(currX + dx < 0) {
				dx = 0;
			}
		}
		
		// check if the next position is a wall
		if(map.get(currY+dy).get(currX+dx) instanceof Wall || map.get(currY+dy).get(currX+dx) instanceof Boulder) {
			if(dy == -4) dy = -1;
			if(dx == -4) dx = -1;
			if(map.get(currY+dy).get(currX+dx) instanceof Wall || map.get(currY+dy).get(currX+dx) instanceof Boulder) {
				nextX = currX;
				nextY = currY;
			} else {
				nextX = currX+dx;
				nextY = currY+dy;
			}
			
		} else {
			nextX = currX+dx;
			nextY = currY+dy;
		}
		
	}

	@Override
	public ArrayList<ArrayList<Entity>> moveCharacter(Enemy e, Entity dest,int height, int width, ArrayList<ArrayList<Entity>> map) {
		
		Dijkstra pathing = new Dijkstra(height, width, map);
		int g[][] = pathing.getGraph();
		if (lastX != -1 && lastY != -1) {
	    	g[e.getY()*map.get(0).size()+e.getX()][lastY*map.get(0).size()+lastX] = 0;
	    	g[lastY*map.get(0).size()+lastX][e.getY()*map.get(0).size()+e.getX()] = 0;
		}
		pathing.dijkstra(e);
		if(dest instanceof Player) {
			switch(((Player)dest).getFacing()) {
				case "north":
						dy = -4;
						dx = 0;
					break;
				case "south":
						dy = 1;
						dx = 0;
					break;
				case "west":
						dy = 0;
						dx = -4;
					break;
				case "east":
						dy = 0;
						dx = 1;
					break;
			}
			setNext(dx, dy, dest.getX(), dest.getY(),map);
		}
    	//int curr = dest.getY()*map.get(0).size()+dest.getX();
		int curr = nextY*map.get(0).size()+nextX;
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
    	lastX = e.getX();
    	lastY = e.getY();
    	e.setMove(nextX, nextY);
	    map.get(e.getY()).set(e.getX(),(Entity)e);		
	    return map;
	}

	

}
