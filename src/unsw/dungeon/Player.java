package unsw.dungeon;
import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements playerSubject {

    private Dungeon dungeon;
    boolean canMove = true;
    ArrayList<playerObserver>observers;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.observers = new ArrayList<playerObserver>();
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
    
    public void addObserver(playerObserver obj) {
    	this.observers.add(obj);
    }
    
    @Override
    public void notifyEntities(int dX, int dY) {
    	for(playerObserver e: observers) {
    		e.update(this,dX, dY);
    	}
    }
}
