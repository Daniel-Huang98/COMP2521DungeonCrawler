package unsw.dungeon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The player entity
 *
 */
public class Player extends Entity implements playerSubject {

    private Dungeon dungeon;
    boolean canMove = true;
    List<playerObserver>observers;
    Sword sword;
    boolean alive;
    boolean hasKey = false;

    /*
     * Create a player positioned in square (x,y)
     * @param x : x coordinate
     * @param y : y coordinate
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.observers = new ArrayList<playerObserver>();
        this.alive = true;
        this.sword = null;
    }

    public void moveUp() {
    	notifyEntities(0,-1);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
    	notifyEntities(0,1);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
    	notifyEntities(-1,0);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
    	notifyEntities(1,0);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
    
    public void setCanMove(boolean flag) {
    	this.canMove = flag;
    }
    
    public boolean getKey() {
    	return hasKey;
    }
    
    public void setKey(boolean flag) {
    	this.hasKey = flag;
    }
    
    public void setCoordinates(int x, int y) {
    	x().set(x);
    	y().set(y);
    }
    
    public void addObserver(playerObserver obj) {
    	this.observers.add(obj);
    }
    
    public void setSword(Sword obj) {
    	this.sword = obj;
    	System.out.println("I has the sword now");
    }
    
    public Sword getSword() {
    	return this.sword;
    }
    
    @Override
    public void notifyEntities(int dX, int dY) {
    	for(playerObserver e: observers) {
    		if(!e.isDeleted())e.update(this,dX, dY);
    	}
    }
}
