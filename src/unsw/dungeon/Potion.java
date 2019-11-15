package unsw.dungeon;

import battle.potionBattle;

/**
 * Potion entity, contains a timer of how long the invincibility will last
 */
public class Potion extends Entity implements playerObserver{
	boolean deleted = false;
	int health;
	 
	/**
	 * Constructs a potion object at x,y coordinates with 15
	 * steps of use
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public Potion(int x, int y) {
        super(x, y);
        this.health = 15;
    }
    
    /**
     * Decrements health by 1
     */
    public boolean decrementHealth(){
    	this.health--;
    	System.out.println("health: " + this.health);
    	if(this.health == 0) {
    		return true;
    	}
    	return false;
    }
    
    public int getHealth() {
    	return this.health;
    }
    
    /**
     * Checks if player is standing over the potion, if so set the boolean
     * in player and change the battle strategy
     * @param player: a subject that is observed
     * @param dX: the subject's change in X direction
     * @param dY: the subject's change in Y direction
     */
    @Override
    public void update(playerSubject player, int dX, int dY) {
    	if(((Player)player).getX()+dX == this.getX() && ((Player)player).getY()+dY == this.getY()) {
    		System.out.println("picked up potion");
    		((Player)player).setPotion(this);
    		((Player)player).setAction(new potionBattle());
    		this.delete(); 		
    	}
    }
    

	@Override
	public void delete() {
		deleted = true;
		this.gone().set(true);
		
	}

	@Override
	public boolean isDeleted() {
		return deleted;
		
	}
}