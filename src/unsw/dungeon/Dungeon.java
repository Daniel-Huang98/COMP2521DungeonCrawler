/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import state.CanWinState;
import state.CantWinState;
import state.EndState;
import state.State;
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
    WinCondition check;
    State canWinState; 
    State cantWinState; 
    State endState; 
    
    State state; 

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
    
    public void setWinCondition(WinCondition obj) {
    	this.check = obj;
    }
    
    public boolean canWin() {
    	return this.check.canWin(this);
    }
    
    public State getCanWinState() {
    	return this.canWinState;
    }
    
    public State getCantWinState() {
    	return this.cantWinState;
    }
    
    public State getEndState() {
    	return this.endState;
    }
    
    public void setState(State state) {
    	this.state = state;
    }
    
	public void exit() {
		this.state.exit();
	}

	public void collectGold() {
		this.state.collectGold();
		
	}
	
	public void activateSwitch() {
		this.state.activateSwitch();
		
	}

	public void deactivateSwitch() {
		this.state.deactivateSwitch();
		
	}

	public void die() {
		this.state.die();
	}
    
    public boolean allGoldCollected() {
    	return(collected == totalGold);
    }
    
    public boolean allSwitchesActivated() {
    	return(activated == switchTotal);
    }
    
    public void incTotalGold() {
    	this.totalGold++;
    }
    
    public void incTotalSwitch() {
    	this.switchTotal++;
    }
    
    public void addGold() {
    	this.collected++;
    }
    
    public void addSwitch() {
    	this.activated++;
    }
    
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
