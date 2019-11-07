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
    
    public void setCanMove(boolean flag) {
    	this.canMove = flag;
    }
    
    /**
     * Set the map and set default movement strategy to be move closer
     * @param map : map of all entities in a 2D array
     */
    public void setMap(ArrayList<ArrayList<Entity>> map) {
    	this.map = map;
    	this.movement = new Closer(map.size(), map.get(0).size(), map);
    }
    
    public void setMove(int x, int y) {
    	x().set(x);
    	y().set(y);
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