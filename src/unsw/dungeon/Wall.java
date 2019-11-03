package unsw.dungeon;

/**
 * Wall entity, observes subjects to make sure they cannot 
 * walk through it
 */
public class Wall extends Entity implements playerObserver {
	boolean deleted = false;

	/**
	 * Constructs a wall object that has x,y coordinates
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public Wall(int x, int y) {
        super(x, y);
    }
    
    /**
     * Checks if player, boulder or enemy will go into the wall and
     * sets the corresponding movement to false if so
     * @param obj: a subject that is observed
     * @param dX: the subject's change in X direction
     * @param dY: the subject's change in Y direction
     */
    @Override
    public void update(playerSubject obj, int dX, int dY) {
    	if (obj instanceof Player) {
	    	if((dX + ((Player)obj).getX()) == this.getX() && (((Player)obj).getY()+dY) == this.getY()) {
	    		((Player)obj).setCanMove(false);
	    	}
    	}
    	else if (obj instanceof Boulder) {
	    	if((dX + ((Boulder)obj).getX()) == this.getX() && (((Boulder)obj).getY()+dY) == this.getY()) {
	    		((Boulder)obj).setCanMove(false);
	    	} 
    	}
    	else if (obj instanceof Enemy) {
	    	if((dX + ((Enemy)obj).getX()) == this.getX() && (((Enemy)obj).getY()+dY) == this.getY()) {
	    		((Enemy)obj).setCanMove(false);
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
