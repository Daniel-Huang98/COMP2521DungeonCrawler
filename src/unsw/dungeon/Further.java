package unsw.dungeon;
import java.util.*; 
import java.lang.*; 
import java.io.*; 

/**
 * A class that tells the enemy to move away from a certain entity.
 * Contains a 1 to 1 entity map as well as height and width of the
 * dungeon.
 */
class Further implements Movement{
	private int height;
	private int width;
	ArrayList<ArrayList<Entity>> map = new ArrayList<ArrayList<Entity>>();
	
	/**
	 * Constructs a Further object
	 * @param height : height of the dungeon
     * @param width : width of the dungeon
     * @param map : 1 to 1 entity map of the dungeon
	 */
	public Further(int height, int width, ArrayList<ArrayList<Entity>> map) {
		this.height = height;
		this.width = width;
		this.map = map;
	}
	
    /**
     * Check if the new movement is a valid index
     * @param x : x coordinate
     * @param y : y coordinate
     * @return true if it is within the index bounds
     */
    public boolean checkBounds(int x, int y) {
    	return (x < map.get(0).size() && x >= 0 && y < map.size() && y >= 0);
    }
	
    /**
     * Move the character away from the entity by inverting the output of
     * the Dijkstra algorithm. If no reachable, try all the other directions
     * @param e : the enemy that is moving
     * @param dest : the destination entity that the enemy is moving away from
     * @return : updated map after the enemy has moved
     */
	@Override
	public ArrayList<ArrayList<Entity>> moveCharacter(Enemy e, Entity dest) {
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
		int oldX = e.getX();
		int oldY = e.getY(); 
		int dX = nextX-oldX;
		int dY = nextY-oldY;
		
		//check if the move opposite to Dijkstra is reachable
		if (checkBounds((oldX + (dX)*-1), (oldY + (dY)*-1)) && map.get(oldY + (dY)*-1).get(oldX + (dX)*-1) == null) {
			e.setMove(oldX + (dX)*-1, oldY + (dY)*-1);
		}
		//try other directions
		else if (dY == 0) {
			if (checkBounds(oldX, oldY+1) && map.get(oldY+1).get(oldX) == null) {
				e.setMove(oldX,oldY+1);
			}
			else if (checkBounds(oldX, oldY-1) && map.get(oldY - 1).get(oldX) == null) {
				e.setMove(oldX,oldY-1);
			}
			else {
				e.setMove(nextX, nextY);
			}
		}
		//try other directions
		else if (dX == 0) {
			if (checkBounds(oldX+1, oldY) && map.get(oldY).get(oldX+1) == null) {
				e.setMove(oldX+1,oldY);
			}
			else if (checkBounds(oldX-1, oldY) && map.get(oldY).get(oldX-1) == null) {
				e.setMove(oldX-1,oldY);
			}
			else {
				e.setMove(nextX, nextY);
			}
		}	  		
		map.get(e.getY()).set(e.getX(),(Entity)e);
		return map;
	}
	
	@Override
	public void setMap(ArrayList<ArrayList<Entity>> map) {
		this.map = map;	
	}
}
