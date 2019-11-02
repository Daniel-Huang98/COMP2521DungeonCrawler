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
    
	/*
	 * Constructs an enemy object that holds x,y coordinates
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public Enemy(int x, int y) {
        super(x, y);
        this.observers = new ArrayList<playerObserver>();
    }
    
    /*
     * Moves boulder up, if it can't player also can't
     * @param player: Player object
     */
    public void moveUp() {
    	notifyEntities(0,-1);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
    	else
            y().set(getY() - 1);
    }

    /*
     * Moves boulder down, if it can't player also can't
     * @param player: Player object
     */
    public void moveDown() {
    	notifyEntities(0,1);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
    	else
            y().set(getY() + 1);
    }

    /*
     * Moves boulder left, if it can't player also can't
     * @param player: Player object
     */
    public void moveLeft() {
    	notifyEntities(-1,0);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
    	else
            x().set(getX() - 1);
    }

    /*
     * Moves boulder right, if it can't player also can't
     * @param player: Player object
     */
    public void moveRight() {
    	notifyEntities(1,0);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
    	else
            x().set(getX() + 1);
    }
    
    public void setCanMove(boolean flag) {
    	this.canMove = flag;
    }
    
    public void setMap(ArrayList<ArrayList<Entity>> map) {
    	this.map = map;
    }
    
    /*
    * Checks if player has collided with it and checks to see what 
    * battle strategy is deployed
    * @param player: a subject that is observed
    * @param dX: the subject's change in X direction
    * @param dY: the subject's change in Y direction
    */
    @Override
    public void update(playerSubject obj, int dX, int dY) {

		if (obj instanceof Player) {
	    	if(((Player)obj).getX()+dX == this.getX() && ((Player)obj).getY()+dY == this.getY()) {
	    		if(((Player)obj).getAction().attacked((Player)obj)) {
	    			this.delete(); 	
	    			System.out.println("Enemy has died");
	    		}
	    	}
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
    		if(!e.isDeleted())e.update(this,dX, dY);
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