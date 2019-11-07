package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

/**
 * Enemy entity
 */
public class Enemy extends Entity implements playerObserver, playerSubject{
	
	private boolean deleted = false;
    private List<playerObserver>observers;
    private boolean canMove = true;
    private ArrayList<ArrayList<Entity>> map = new ArrayList<ArrayList<Entity>>();
    private Movement movement;
    
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
/*    	//run Dijkstra's algorithm on the map
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
    		setMove(nextX, nextY);
	    	map.get(getY()).set(getX(),(Entity)this);
    	}
    	else {
    		int oldX = getX();
    		int oldY = getY(); 
    		int dX = nextX-oldX;
    		int dY = nextY-oldY;
    		//check if the move opposite to Dijkstra is reachable
    		if (checkBounds((oldX + (dX)*-1), (oldY + (dY)*-1)) && map.get(oldY + (dY)*-1).get(oldX + (dX)*-1) == null) {
    			setMove(oldX + (dX)*-1, oldY + (dY)*-1);
    		}
    		//try other directions
    		else if (dY == 0) {
    			if (checkBounds(oldX, oldY+1) && map.get(oldY+1).get(oldX) == null) {
    				setMove(oldX,oldY+1);
    			}
    			else if (checkBounds(oldX, oldY-1) && map.get(oldY - 1).get(oldX) == null) {
    				setMove(oldX,oldY-1);
    			}
    			else {
    				setMove(nextX, nextY);
    			}
    		}
    		//try other directions
    		else if (dX == 0) {
    			if (checkBounds(oldX+1, oldY) && map.get(oldY).get(oldX+1) == null) {
    				setMove(oldX+1,oldY);
    			}
    			else if (checkBounds(oldX-1, oldY) && map.get(oldY).get(oldX-1) == null) {
    				setMove(oldX-1,oldY);
    			}
    			else {
    				setMove(nextX, nextY);
    			}
    		}	  		
    		map.get(getY()).set(getX(),(Entity)this);
    	}*/
    }	
    
    public void setCanMove(boolean flag) {
    	this.canMove = flag;
    }
    
    public void setMap(ArrayList<ArrayList<Entity>> map) {
    	this.map = map;
    	this.movement = new Closer(map.size(), map.get(0).size(), map);
    }
    
    public void setMove(int x, int y) {
    	x().set(x);
    	y().set(y);
    }
    
    public boolean checkBounds(int x, int y) {
    	return (x < map.get(0).size() && x >= 0 && y < map.size() && y >= 0);
    }
    
    public void setMovement(Movement m) {
    	this.movement = m;
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
			if (((Player)obj).getPotion() != null)
				movement = new Further(map.size(), map.get(0).size(), map);
			else 
				movement = new Closer(map.size(), map.get(0).size(), map);
	    	map = movement.moveCharacter(this, (Entity)obj);
	    	
			if (!(map.get(((Player)obj).getY()+dY).get(((Player)obj).getX()+dX) instanceof Wall)) {
				map.get(((Player)obj).getY()+dY).set(((Player)obj).getX()+dX, (Entity)obj);
				map.get(((Player)obj).getY()).set(((Player)obj).getX(), null);
				movement.setMap(map);
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