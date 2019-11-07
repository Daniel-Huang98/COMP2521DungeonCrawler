package unsw.dungeon;
import java.util.*; 
import java.lang.*; 
import java.io.*; 

/**
 * A class that tells the enemy to move towards a certain entity.
 * Contains a 1 to 1 entity map as well as height and width of the
 * dungeon.
 */
class Closer implements Movement{
	private int height;
	private int width;
	ArrayList<ArrayList<Entity>> map = new ArrayList<ArrayList<Entity>>();
	
	/**
	 * Constructs a Closer object
	 * @param height : height of the dungeon
     * @param width : width of the dungeon
     * @param map : 1 to 1 entity map of the dungeon
	 */
	public Closer(int height, int width, ArrayList<ArrayList<Entity>> map) {
		this.height = height;
		this.width = width;
		this.map = map;
	}

    /**
     * Move the character away from the entity by using the output of
     * the Dijkstra algorithm.
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
    	e.setMove(nextX, nextY);
	    map.get(e.getY()).set(e.getX(),(Entity)e);		
	    return map;
	}

	@Override
	public void setMap(ArrayList<ArrayList<Entity>> map) {
		this.map = map;		
	}
}
