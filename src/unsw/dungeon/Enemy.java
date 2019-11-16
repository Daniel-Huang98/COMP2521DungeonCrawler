package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

import movement.*;

/**
 * Enemy entity
 */
public class Enemy extends Entity implements playerObserver, playerSubject{
	
	private boolean deleted = false;
    private List<playerObserver>observers;
    private boolean canMove = true;
    protected ArrayList<ArrayList<Entity>> map = new ArrayList<ArrayList<Entity>>();
    protected Movement movement;
    protected Player player;
    protected Movement further;
    protected Movement closer;
    
	/**
	 * Constructs an enemy object that holds x,y coordinates
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public Enemy(int x, int y) {
        super(x, y);
        this.observers = new ArrayList<playerObserver>();
        this.further = new Further();
        this.closer = new Closer();
    }
    
    public Enemy(int x, int y, Movement further, Movement closer) {
        super(x, y);
        this.further = further;
        this.closer = closer;
        this.observers = new ArrayList<playerObserver>();
    }
    
    public void setCanMove(boolean flag) {
    	this.canMove = flag;
    }
    
    public void setCloser(Movement closer) {
    	this.closer = closer;
    }
    
    public void setFurther(Movement further) {
    	this.further = further;
    }
    
    /**
     * Set the map and set default movement strategy to be move closer
     * @param map : map of all entities in a 2D array
     */
    public void setMap(ArrayList<ArrayList<Entity>> map) {
    	this.map = map;
    	this.movement = new Closer();
    }
    
    public void setMove(int x, int y) {
    	x().set(x);
    	y().set(y);
    }
    
    public void setMovement(Movement m) {
    	this.movement = m;
    }
    
    public void setPlayer(Player player) {
    	this.player = player;
    }
    
    /**
     * Sets the coordinate of the enemy in the dungeon
     * @param x x coordinate of the player
     * @param y y coordinate of the player
     */
    public void setCoordinates(int x, int y) {
    	x().set(x);
    	y().set(y);
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
		else if (obj instanceof TimelineObject) {
			if (player.getPotion() != null) {
				movement = further;
				movement.moveCharacter(this, player,map.size(), map.get(0).size(), map);
			}
			else {
				movement = closer;
				movement.moveCharacter(this, player,map.size(), map.get(0).size(), map);
			}
	    	this.notifyEntities(0,0);
		}
    }

	@Override
	public void delete() {
		deleted = true;
		this.gone().set(true);
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