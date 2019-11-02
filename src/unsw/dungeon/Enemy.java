package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

/**
 * Enemy entity
 */
public class Enemy extends Entity implements playerObserver, playerSubject{
	
	boolean deleted = false;
    List<playerObserver>observers;
    boolean canMove = true;
    ArrayList<ArrayList<Entity>> map = new ArrayList<ArrayList<Entity>>();
    
	/**
	 * Constructs an enemy object that holds x,y coordinates
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public Enemy(int x, int y) {
        super(x, y);
        this.observers = new ArrayList<playerObserver>();
    }
    
    /**
     * Moves enemy based on Dijkstra's algorithm
     * @param player: Player object
     */
    public void moveEnemy(Player obj) {
    	//run Dijkstra's algorithm on the map
    	Movement m = new Movement(map.size(), map.get(0).size(), map);
    	int prev[] = m.dijkstra();
    	int curr = obj.getY()*map.get(0).size()+obj.getX();
    	int next = prev[curr];
    	int counter = 0;
    	
    	//backtrack the traceback array
    	while (next != getY()*map.get(0).size()+getX() && counter < map.size()*map.get(0).size()) {
    		curr = next;
    		if (curr == -1) break;
    		next = prev[curr];
    		counter++;
    	}
    	if (curr == -1) return;
    	//make sure no infinite loop occurs
    	if (counter == map.size()*map.get(0).size()) {
    		return;
    	}
    	
    	//calculate next coordinate, move the enemy and update the map
    	int nextY = curr/map.get(0).size();
    	int nextX = curr%(map.get(0).size());
    	map.get(getY()).set(getX(),null);
    	if (obj.getPotion() == null) {
	    	x().set(nextX);
	    	y().set(nextY);
	    	map.get(getY()).set(getX(),(Entity)this);
    	}
    	else {
    		int oldX = getX();
    		int oldY = getY();
    		int graph[][] = m.getGraph(); 
    		if (oldX < nextX) {
    			nextX-=2;
    			if (nextX >= 0 && graph[nextY*map.get(0).size()+nextX][oldY*map.get(0).size()+oldX] == 1) {
    		    	x().set(nextX);
    		    	y().set(nextY);
    			}
    			else if (oldY+1 < map.size() && graph[(oldY+1)*map.get(0).size()+oldX][oldY*map.get(0).size()+oldX] == 1) {
    		    	x().set(oldX);
    		    	y().set(oldY+1);
    			}
    			else if (oldY-1 >= 0 && graph[(oldY-1)*map.get(0).size()+oldX][oldY*map.get(0).size()+oldX] == 1) {
    				x().set(oldX);
    		    	y().set(oldY-1);
    			}
    			else if (oldX+1 < map.get(0).size() && graph[oldY*map.get(0).size()+(oldX+1)][oldY*map.get(0).size()+oldX] == 1){
    				x().set(oldX+1);
    		    	y().set(oldY);
    			}
    			map.get(getY()).set(getX(),(Entity)this);
    		}
    		else if (oldX > nextX) {
    			nextX+=2;
    			if (nextX < map.get(0).size() && graph[nextY*map.get(0).size()+nextX][oldY*map.get(0).size()+oldX] == 1) {
    		    	x().set(nextX);
    		    	y().set(nextY);
    			}
    			else if (oldY+1 < map.size() && graph[(oldY+1)*map.get(0).size()+oldX][oldY*map.get(0).size()+oldX] == 1) {
    		    	x().set(oldX);
    		    	y().set(oldY+1);
    			}
    			else if (oldY-1 >= 0 && graph[(oldY-1)*map.get(0).size()+oldX][oldY*map.get(0).size()+oldX] == 1) {
    				x().set(oldX);
    		    	y().set(oldY-1);
    			}
    			else if (oldX-1 >= 0  && graph[oldY*map.get(0).size()+(oldX-1)][oldY*map.get(0).size()+oldX] == 1){
    				x().set(oldX-1);
    		    	y().set(oldY);
    			}
    			map.get(getY()).set(getX(),(Entity)this);
    		}
    		else if (oldY < nextY) {
    			nextY-=2;
    			if (nextY >= 0 && graph[nextY*map.get(0).size()+nextX][oldY*map.get(0).size()+oldX] == 1) {
    		    	x().set(nextX);
    		    	y().set(nextY);
    			}
    			else if (oldX+1 < map.get(0).size() && graph[oldY*map.get(0).size()+(oldX+1)][oldY*map.get(0).size()+oldX] == 1) {
    		    	x().set(oldX+1);
    		    	y().set(oldY);
    			}
    			else if (oldX-1 >= 0 && graph[oldY*map.get(0).size()+(oldX-1)][oldY*map.get(0).size()+oldX] == 1) {
    				x().set(oldX-1);
    		    	y().set(oldY);
    			}
    			else if (oldY+1 < map.size() && graph[(oldY+1)*map.get(0).size()+oldX][oldY*map.get(0).size()+oldX] == 1){
    				x().set(oldX);
    		    	y().set(oldY+1);
    			}
    		}
        	else if (oldY > nextY) {
    			nextY+=2;
    			if (nextY < map.size() && graph[nextY*map.get(0).size()+nextX][oldY*map.get(0).size()+oldX] == 1) {
    		    	x().set(nextX);
    		    	y().set(nextY);
    			}
    			else if (oldX+1 < map.get(0).size() && graph[oldY*map.get(0).size()+(oldX+1)][oldY*map.get(0).size()+oldX] == 1) {
    		    	x().set(oldX+1);
    		    	y().set(oldY);
    			}
    			else if (oldX-1 >= 0 && graph[oldY*map.get(0).size()+(oldX-1)][oldY*map.get(0).size()+oldX] == 1) {
    				x().set(oldX-1);
    		    	y().set(oldY);
    			}
    			else if (oldY-1 >= 0 && graph[(oldY-1)*map.get(0).size()+oldX][oldY*map.get(0).size()+oldX] == 1){
    				x().set(oldX);
    		    	y().set(oldY-1);
    			}
        	}
    		map.get(getY()).set(getX(),(Entity)this);
    	}
    }  		
    
    public void setCanMove(boolean flag) {
    	this.canMove = flag;
    }
    
    public void setMap(ArrayList<ArrayList<Entity>> map) {
    	this.map = map;
    }
    
    /**
    * Moves enemy, then updates the player on the map.
    * Checks if player has collided with it and checks to see what 
    * battle strategy is deployed
    * @param player: a subject that is observed
    * @param dX: the subject's change in X direction
    * @param dY: the subject's change in Y direction
    */
    @Override
    public void update(playerSubject obj, int dX, int dY) {
    	if(isDeleted()) return;
		if (obj instanceof Player) {
	    	moveEnemy((Player)obj);
	    	
			if (!(map.get(((Player)obj).getY()+dY).get(((Player)obj).getX()+dX) instanceof Wall)) {
				map.get(((Player)obj).getY()+dY).set(((Player)obj).getX()+dX, (Entity)obj);
				map.get(((Player)obj).getY()).set(((Player)obj).getX(), null);
			}
			this.notifyEntities(0,0);
    	}
    }
    

	@Override
	public void delete() {
		deleted = true;
		
	}

	@Override
	public boolean isDeleted() {
		return deleted;
		
	}

    @Override
    public void notifyEntities(int dX, int dY) {
    	
    	for(playerObserver e: observers) {
    		e.update(this,dX, dY);
    	}
    }
    
    @Override
    public void addObserver(playerObserver obj) {
    	this.observers.add(obj);
    }
    
    @Override
    public void deleteObserver(playerObserver obj){
    	this.observers.remove(obj);
    }
}