package unsw.dungeon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements playerSubject {

    private Dungeon dungeon;
    boolean canMove = true;
    List<playerObserver>observers;
    Sword sword;
    Potion potion;
    boolean alive;
    battle action;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.observers = new ArrayList<playerObserver>();
        this.alive = true;
        this.sword = null;
        this.action = new deathBattle();
    }

    public void moveUp() {
    	notifyEntities(0,-1);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
        if (getY() > 0)
            y().set(getY() - 1);
        if(this.potion != null)this.decrementPotionHealth();
    }

    public void moveDown() {
    	notifyEntities(0,1);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
        if(this.potion != null)this.decrementPotionHealth();
    }

    public void moveLeft() {
    	notifyEntities(-1,0);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
        if (getX() > 0)
            x().set(getX() - 1);
        if(this.potion != null)this.decrementPotionHealth();
    }

    public void moveRight() {
    	notifyEntities(1,0);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
        if(this.potion != null)this.decrementPotionHealth();
    }
    
    public void setCanMove(boolean flag) {
    	this.canMove = flag;
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
    
    public void setPotion(Potion obj) {
    	this.potion = obj;
    }
    
    public Potion getPotion() {
    	return this.potion;
    }
    
    public void die() {
    	this.alive = false;
    	System.out.println("you have died");
    }
    
    public boolean isAlive() {
    	return this.alive;
    }
    
    public void decrementSwordHealth() {
    	if(sword.decrementHealth()) {
    		this.sword = null;
    		this.action = new deathBattle();
    	}
    }
    
    public void decrementPotionHealth() {
    	if(potion.decrementHealth()) {
    		this.potion = null;
    		if(sword != null) {
    			this.action = new swordBattle();
    		} else {
    			this.action = new deathBattle();
    		}
    	}
    }
    
    public void setAction(battle action) {
    	this.action = action;
    }
    
    public battle getAction() {
    	return this.action;
    }
    
    @Override
    public void notifyEntities(int dX, int dY) {
    	for(playerObserver e: observers) {
    		if(!e.isDeleted())e.update(this,dX, dY);
    	}
    }
}
