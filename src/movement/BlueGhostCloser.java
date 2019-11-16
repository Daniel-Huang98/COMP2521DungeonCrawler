package movement;

import java.util.ArrayList;

import unsw.dungeon.Boulder;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Ghost;
import unsw.dungeon.Wall;

public class BlueGhostCloser implements Movement{
	
	private int height;
	private int width;
	private int lastX = -1;
	private int lastY = -1;
	private int dx = 0;
	private int dy = 0;
	int nextX;
	int nextY;
	private ArrayList<ArrayList<Entity>> map = new ArrayList<ArrayList<Entity>>();
	private Ghost redghost;
	
	public BlueGhostCloser(Ghost redghost) {
		this.redghost = redghost;
	}
	
	private void setNext(int enemyX, int enemyY, int playerX, int playerY, ArrayList<ArrayList<Entity>> map) {
		double dy = enemyY - playerY;
		double dx = enemyX - playerX;
		double gradient;
		double constant;
		if(dy == 0){ //vertical
			nextY = enemyY;
			nextX = (int)(playerX+(-dx));
			if(nextX > 26) {
				nextX = 26;
			}else if(nextX < 0) {
				nextX = 0;
			}
			while((map.get(nextY).get(nextX) instanceof Wall || map.get(nextY).get(nextX) instanceof Boulder) && nextX != playerX) {
				if(nextX > playerX) {
					nextX--;
				} else {
					nextX++;
				}
			}
			return;
		} else if(dx == 0) { //horizontal
			nextX = enemyX;
			nextY = (int)(playerY+(-dy));
			if(nextY > 29) {
				nextY = 29;
			}else if(nextY < 0) {
				nextY = 0;
			}
			while((map.get(nextY).get(nextX) instanceof Wall || map.get(nextY).get(nextX) instanceof Boulder) && nextY != playerY) {
				if(nextY > playerY) {
					nextY--;
				} else {
					nextY++;
				}
			}
			return;
		} else { //everything else y = mx+b
			gradient = dy/dx;
			constant = -gradient*enemyX+enemyY;
			nextX = (int)(playerX+(-dx));
			nextY = (int)(nextX*gradient+constant);
			
			if(nextX > 26) {
				nextX = 26;
			}else if(nextX < 1) {
				nextX = 1;
			}
			while(nextX != playerX) {
				nextY = (int)(nextX*gradient+constant);
				if(nextY > 29) {
					nextY = 29;
				}else if(nextY < 1) { 
					nextY = 1;
				}
				if(!(map.get(nextY).get(nextX) instanceof Boulder) && !(map.get(nextY).get(nextX) instanceof Wall)) {
					System.out.println("found");
					System.out.println("Found: " + nextX + " " +nextY);
					System.out.println("Player: " + playerX + " " +playerY);
					return;
				}
				if(nextX > playerX) nextX--;
				else nextX++;
			}
			
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
		if(this.redghost.isDeleted()) {
			nextX = dest.getX();
			nextY = dest.getY();
		}else {
			setNext(this.redghost.getX(),this.redghost.getY() , dest.getX(), dest.getY(),map);
		}
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
