package unsw.dungeon;

/**
 * Portal entity which contains the other portal in its pair
 *
 */
public class Portal extends Entity implements playerObserver {
	boolean deleted = false;
	Portal destination = null;
	
	/*
	 * Constructs portal at given x,y coordinates
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public Portal(int x, int y) {
        super(x, y);
    }

    public void setPortalPair(Portal portal) {
    	this.destination = portal;
    }
    
    /*
     * Checks if player is over a portal and if so, moves them to
     * the coordinates of the other portal in the pair
     */
    @Override
    public void update(playerSubject obj, int dX, int dY) {
    	if (obj instanceof Player) {
	    	if(((dX + ((Player)obj).getX()) == this.getX() && (((Player)obj).getY()+dY) == this.getY()) && destination != null) {
	    		((Player)obj).setCoordinates(destination.getX(), destination.getY());
	    		((Player)obj).setCanMove(false);
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