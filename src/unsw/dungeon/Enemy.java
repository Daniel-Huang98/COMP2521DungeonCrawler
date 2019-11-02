package unsw.dungeon;

/**
 * Enemy entity
 */
public class Enemy extends Entity implements playerObserver{
	boolean deleted = false;
	
	/*
	 * Constructs an enemy object that holds x,y coordinates
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public Enemy(int x, int y) {
        super(x, y);
    }
    
    /*
    * Checks if player has collided with it and checks to see what 
    * battle strategy is deployed
    * @param player: a subject that is observed
    * @param dX: the subject's change in X direction
    * @param dY: the subject's change in Y direction
    */
    @Override
    public void update(playerSubject player, int dX, int dY) {
    	if(((Player)player).getX()+dX == this.getX() && ((Player)player).getY()+dY == this.getY()) {
    		if(((Player)player).getAction().attacked((Player)player)) {
    			this.delete(); 	
    			System.out.println("Enemy has died");
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
}