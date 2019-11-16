package unsw.dungeon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import battle.battle;
import battle.deathBattle;
import battle.swordBattle;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

/**
 * The player entity, contains sword reference, a boolean representing
 * if the player is able to make a legal move, a boolean representing if
 * the player has died or not, a boolean representing the key and a battle
 * strategy
 *
 */
public class Player extends Entity implements playerSubject, playerObserver {

    protected Dungeon dungeon;
    boolean canMove = true;
    List<playerObserver>observers;
    Sword sword;
    Potion potion;
    boolean alive;
    Key key;
    battle action;
    int dy = 0;
    int dx = 0;
    String facing = "east";
    FloatProperty keyStatus = new SimpleFloatProperty((float)0);
    FloatProperty swordStatus = new SimpleFloatProperty((float)0);
    FloatProperty potionStatus = new SimpleFloatProperty((float)0);

    /**
     * Create a player positioned in square (x,y)
     * @param dungeon : dungeon entity
     * @param x : x coordinate
     * @param y : y coordinate
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.observers = new ArrayList<playerObserver>();
        this.alive = true;
        this.sword = null;
        this.key = null;
        this.action = new deathBattle();
    }

    /**
     * Checks if player can legally move up by notifying
     * all observer
     */
    public void moveUp() {
    	notifyEntities(0,-1);
    	if(!canMove) {
    		canMove = true;
    		return;
    	} 
    	facing = "north";
    	
        if (getY() > 0)
            y().set(getY() - 1);
        updateStatus();
    }

    /**
     * Checks if player can legally move down by notifying
     * all observer
     */
    public void moveDown() {
    	notifyEntities(0,1);
    	if(!canMove) {
    		canMove = true;
    		return;
    	} 
    	facing = "south";
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
        updateStatus();
    }

    /**
     * Checks if player can legally move left by notifying
     * all observer
     */
    public void moveLeft() {
    	notifyEntities(-1,0);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
    	facing = "west";
    	
        if (getX() > 0)
            x().set(getX() - 1);
        updateStatus();
    }

    /**
     * Checks if player can legally move right by notifying
     * all observer
     */
    public void moveRight() {
    	notifyEntities(1,0);
    	if(!canMove) {
    		canMove = true;
    		return;
    	} 
    	facing = "east";	
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
        updateStatus();
    }
    
    public String getFacing() {
    	return facing;
    }
    
    /**
     * Sets if the player can move
     * @param flag Set flag to true if the player can move, set to false if it can't move
     */
    public void setCanMove(boolean flag) {
    	this.canMove = flag;
    }
    
    public boolean getCanMove() {
    	return this.canMove;
    }
   
    public Key getKey() {
    	return key;
    }
    
    public void setKey(Key key) {
    	keyStatus.set((float)1);
    	this.key = key;
    }
    
    /**
     * Sets the coordinate of the player in the dungeon
     * @param x x coordinate of the player
     * @param y y coordinate of the player
     */
    public void setCoordinates(int x, int y) {
    	x().set(x);
    	y().set(y);
    }
    
    public void setSword(Sword obj) {
    	this.sword = obj;
    	swordStatus.set((float)1);
    	System.out.println("I has the sword now");
    }
    
    public Sword getSword() {
    	return this.sword;
    }
    
    public void setPotion(Potion obj) {
    	potionStatus.set((float)1);
    	this.potion = obj;
    }
    
    public Potion getPotion() {
    	return this.potion;
    }
    
    public void setDelta(int dx, int dy) {
    	this.dx = dx;
    	this.dy = dy;
    }
    
    public int getdx() {
    	return this.dx;
    }
    
    public int getdy() {
    	return this.dy;
    }
    
    /**
     * Will set alive to false and notifies the dungeon of the player's death
     */
    public void die() {
    	this.alive = false;
    	System.out.println("you have died");
    	dungeon.die();
    }
    
    /**
     * returns booleans based on whether the player is still alive
     * @return returns true if player is alive else returns false
     */
    public boolean isAlive() {
    	return this.alive;
    }
    


    /** 
     * When sword breaks, change battle strategy
     */
    public void decrementSwordHealth() {
    	if(sword.decrementHealth()) {
    		this.sword = null;
    		this.action = new deathBattle();
    	}
    }
    

    /** 
     * When invincibility runs out, change battle strategy
     */
    public void decrementPotionHealth() {
    	if(potion.decrementHealth()) {
    		this.potion = null;
    		System.out.println("potion set to null");
    		if(sword != null) {
    			this.action = new swordBattle();
    		} else {
    			this.action = new deathBattle();
    			System.out.println("deathbattle set");
    		}
    	}
    }
   
    public void setAction(battle action) {
    	this.action = action;
    }
    
    public battle getAction() {
    	return this.action;
    }
    
    /**
     * notifies the dungeon that gold has been collected
     */
    public void collectGold() {
    	this.dungeon.collectGold();
    }
    
    /**
     * notifies the dungeon that an enemy has been killed
     */
    public void killEnemy() {
    	this.dungeon.killEnemy();
    }
    
    /**
     * notifies the dungeon that the player has tried to exit
     */
    public void exit() {
    	this.dungeon.exit();
    }
    
    public FloatProperty getKeyStatus() {
    	return keyStatus;
    }

    public FloatProperty getSwordStatus() {
    	return swordStatus;
    }
    
    public FloatProperty getPotionStatus() {
    	return potionStatus;
    }
    
    public void updateStatus() {
    	if (key == null) {
    		keyStatus.set((float)0);
    	}
    	if (sword != null) {
    		int swordHealth = sword.getHealth();
    		swordStatus.set((float)swordHealth/5);
    	}
    }
    
    /**
     * notifies all observers of its change in coordinates
     */
    @Override
    public void notifyEntities(int dX, int dY) {
    	this.setDelta(dX, dY);
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


   /**
    * Checks if enemy has moved into the player, employs
    * which strategy to see who dies
    * @param obj: a subject that is observed
    * @param dX: the subject's change in X direction
    * @param dY: the subject's change in Y direction
    */
	@Override
	public void update(playerSubject obj, int dX, int dY) {
		//System.out.println("check player");

		if (obj instanceof Enemy) {
			//check if enemy is about to move onto the player
			/*if (((Enemy)obj).getX()+dX == this.getX()+this.dx && ((Enemy)obj).getY()+dY == this.getY()+this.dy) {
	    		if(getAction().attacked(this)) {
	    			((Enemy)obj).delete(); 	
	    			System.out.println("Enemy has died");
	    		}
	    	} */if (((Enemy)obj).getX()+dX == this.getX() && ((Enemy)obj).getY()+dY == this.getY()) {
	    		if(getAction().attacked(this)) {
	    			((Enemy)obj).delete(); 	
	    			System.out.println("Enemy has died");
	    		}
	    	}
		}
		else if (obj instanceof TimelineObject) {
			if(this.potion != null) {
				int potionHealth = potion.getHealth();
				potionStatus.set((float)potionHealth/this.potion.getFullHealth());
				this.decrementPotionHealth();
			}
		}
		updateStatus();
	}

	@Override
	public void delete() {
		return;
		
	}

	@Override
	public boolean isDeleted() {
		return false;
	}
}
