/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import compositecheck.CompositeCheck;
import state.CanWinState;
import state.CantWinState;
import state.EndState;
import state.State;
import wincheck.WinCheck;
import wincondition.WinCondition;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private ArrayList<Entity> entities;
    private Player player;
    int totalGold = 0;
    int switchTotal = 0;
    int collected = 0;
    int activated = 0;
    int enemiesKilled = 0;
    int totalEnemies = 0;
    State canWinState; 
    State cantWinState; 
    State endState; 
    State state; 
    
    CompositeCheck check;
    /**
     * Contructor for the dungeon class
     * @param width Is the width of the game window
     * @param height Is the height of the game window
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.canWinState = new CanWinState(this);
        this.cantWinState = new CantWinState(this);
        this.endState = new EndState(this);
        this.state = this.cantWinState;
    }
    
    
    /**
     * add the check condition OR or AND
     * @param check reference to the check strategy object
     */
    public void setWinCheck(CompositeCheck check) {
    	this.check = check;
    }
    
    
    /**
     * Returns true or false based on whether the player has satisfied the dungeon goal
     * @return true or false boolean 
     */
    public boolean canWin() {
    	boolean result = false;
    	try {
    		assert(this.check != null);
    		result = this.check.check();
    	} catch(NullPointerException e) {
    		System.out.println("There is a null exception here");
    	}
    	return result;
    }
    
    
    /**
     * Returns the canWinState Reference
     * @return reference to the canWinState object
     */
    public State getCanWinState() {
    	return this.canWinState;
    }
    
    /**
     * Returns the cantWinState Reference
     * @return reference to the cantWinState object
     */
    public State getCantWinState() {
    	return this.cantWinState;
    }
    
    /**
     * Returns the endState reference
     * @return reference to the endState object
     */
    public State getEndState() {
    	return this.endState;
    }
    
    /**
     * Sets the dungeons current state
     * @param state reference to a state object
     */
    public void setState(State state) {
    	this.state = state;
    }
    
    public State getState() {
    	return this.state;
    }
    
    /**
     * invokes exit function of current state
     */
	public void exit() {
		this.state.exit();
	}

	/**
	 * invokes the collect gold function of the current state
	 */
	public void collectGold() {
		this.state.collectGold();
		
	}
	
	/**
	 * invokes the activate switch function of the current state
	 */
	public void activateSwitch() {
		this.state.activateSwitch();
		
	}

	/**
	 * invokes the deactivate switch function of the current state
	 */
	public void deactivateSwitch() {
		this.state.deactivateSwitch();
		
	}
	
	/**
	 * invokes the kill enemy function of the current state
	 */
	void killEnemy() {
		this.state.killEnemy();
	}

	/**
	 * invokes the die function of the current state
	 */
	public void die() {
		this.state.die();
	}
    
	/**
	 * returns booleans based on whether all the gold has been collected
	 * @return true is all gold collect, false otherwise
	 */
    public boolean allGoldCollected() {
    	return(collected == totalGold);
    }
    
    /**
	 * returns booleans based on whether all the switches has been collected
	 * @return true is all switches collect, false otherwise
	 */
    public boolean allSwitchesActivated() {
    	return(activated == switchTotal);
    }
    
    /**
	 * returns booleans based on whether all the enemies has been collected
	 * @return true is all enemies collect, false otherwise
	 */
    public boolean allEnemiesKilled() {
    	return(enemiesKilled == totalEnemies);
    }
    
    /**
     * increments the total gold counter
     */
    public void incTotalGold() {
    	this.totalGold++;
    }
    
    /**
     * increments the total switch counter
     */
    public void incTotalSwitch() {
    	this.switchTotal++;
    }
    
    /**
     * increments the total enemies counter
     */
    public void incTotalEnemies() {
    	this.totalEnemies++;
    }
    
    /**
     * increments the killed enemies counter
     */
    public void killedEnemy() {
    	this.enemiesKilled++;
    }
    
    /**
     * increments the collected gold counter
     */
    public void addGold() {
    	this.collected++;
    }
    
    /**
     * increments the activated switches counter
     */
    public void addSwitch() {
    	this.activated++;
    }
    
    /**
     * decrements the activated switches counter
     */
    public void removeSwitch() {
    	this.activated--;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public ArrayList<Entity> getEntities() {
    	return this.entities;
    }
}
