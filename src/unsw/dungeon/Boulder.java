package unsw.dungeon;

import java.util.ArrayList;

/**
 * Boulder entity, observes players and is observed by walls
 * and other boulders
 * Moves boulder with player movement
 */
public class Boulder extends Entity implements playerSubject, playerObserver{
	boolean deleted = false;
    boolean canMove = true;
    ArrayList<playerObserver>observers;
	
    /*
     * Constructs the Bolder object which contains x and y
     * coordinates as well as an empty list of observers
     */
    public Boulder(int x, int y) {
        super(x, y);
        this.observers = new ArrayList<playerObserver>();
    }
    
    /*
     * Moves boulder up, if it can't player also can't
     * @param player: Player object
     */
    public void moveUp(Player player) {
    	notifyEntities(0,-1);
    	if(!canMove) {
    		player.setCanMove(false);
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
    public void moveDown(Player player) {
    	notifyEntities(0,1);
    	if(!canMove) {
    		player.setCanMove(false);
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
    public void moveLeft(Player player) {
    	notifyEntities(-1,0);
    	if(!canMove) {
    		player.setCanMove(false);
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
    public void moveRight(Player player) {
    	notifyEntities(1,0);
    	if(!canMove) {
    		player.setCanMove(false);
    		canMove = true;
    		return;
    	}
    	else
            x().set(getX() + 1);
    }
    
    public void setCanMove(boolean flag) {
    	this.canMove = flag;
    }
    
    @Override
    public void notifyEntities(int dX, int dY) {
    	for(playerObserver e: observers) {
    		e.update(this,dX, dY);
    	}
    }
    
    /*
     * Moves the boulder depending on the change in direction
     * of the player given by dX and dY
     * Also checks if the boulder is being pushed into another
     * boulder and sets all movement to false
     * @param obj: a subject that is observed
     * @param dX: the subject's change in X direction
     * @param dY: the subject's change in Y direction
     */
	@Override
	public void update(playerSubject obj, int dX, int dY) {
		if (obj instanceof Player) {
	    	if((dX + ((Player)obj).getX()) == this.getX() && (((Player)obj).getY()+dY) == this.getY()) {
	    		if (dX == 1) {
	    			moveRight((Player)obj);
	    		}
	    		else if (dY == -1) {
	    			moveUp((Player)obj);
	    		}
	    		else if (dY == 1) {
	    			moveDown((Player)obj);
	    		}
	    		else if (dX == -1) {
	    			moveLeft((Player)obj);
	    		}
	    	}
    	}
		else if (obj instanceof Boulder) {
	    	if((dX + ((Boulder)obj).getX()) == this.getX() && (((Boulder)obj).getY()+dY) == this.getY()) {
	    		this.canMove = false;
	    		((Boulder)obj).setCanMove(false);
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
	public void deleteObserver(playerObserver obj) {
		this.observers.remove(obj);
	}

	@Override
    public void addObserver(playerObserver obj) {
    	this.observers.add(obj);
    }
}
