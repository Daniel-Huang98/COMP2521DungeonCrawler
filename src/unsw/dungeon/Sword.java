package unsw.dungeon;

import battle.swordBattle;

/**
 * Sword entity that starts with 5 hits left
 *
 */
public class Sword extends Entity implements playerObserver{
	int health;
	boolean deleted = false;

	/*
	 * Constructs a sword object that holds x,y coordinates
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public Sword(int x, int y) {
        super(x, y);
        this.health = 5;
    }
    
    /*
     * Decrements the amount of hits left by 1
     */
    public boolean decrementHealth() {
    	this.health--;
    	if(this.health == 0) {
    		return true;
    	}
    	return false;
    }
    
    public int getHealth() {
    	return this.health;
    }

    /*
     * Checks if player is standing over a sword, if so a reference
     * to it is added to the player and the battle strategy is changed
     * @param player: a subject that is observed
     * @param dX: the subject's change in X direction
     * @param dY: the subject's change in Y direction
     */
    @Override
    public void update(playerSubject player, int dX, int dY) {
    	if(((Player)player).getX()+dX == this.getX() && ((Player)player).getY()+dY == this.getY()) {
    		System.out.println("picked up sword");
    		((Player)player).setSword(this);
    		((Player)player).setAction(new swordBattle());
    		this.delete(); 		
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
}
