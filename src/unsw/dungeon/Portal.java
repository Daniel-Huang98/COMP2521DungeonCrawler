package unsw.dungeon;

/**
 * Portal entity which contains the other portal in its pair
 *
 */
public class Portal extends Entity implements playerObserver {
	boolean deleted = false;
	Portal destination = null;
	int id;
	
	/**
	 * Constructs portal at given x,y coordinates
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public Portal(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }

    public void setPortalPair(Portal portal) {
    	this.destination = portal;
    }
    
    public Portal getPortalPair() {
    	return destination;
    }
    
    public int getId() {
    	return id;
    }
    
    /**
     * Checks if player is over a portal and if so, moves them to
     * the coordinates of the other portal in the pair
     * @param obj: a subject that is observed
     * @param dX: the subject's change in X direction
     * @param dY: the subject's change in Y direction
     */
    @Override
    public void update(playerSubject obj, int dX, int dY) {
    	if (obj instanceof Player) {
	    	if(((dX + ((Player)obj).getX()) == this.getX() && (((Player)obj).getY()+dY) == this.getY()) && destination != null) {
	    		((Player)obj).setCoordinates(destination.getX(), destination.getY());
	    		((Player)obj).setCanMove(false);
	    	}
    	}
    	/*else if (obj instanceof Enemy) {
    		if(((dX + ((Enemy)obj).getX()) == this.getX() && (((Enemy)obj).getY()+dY) == this.getY()) && destination != null) {
	    		((Enemy)obj).setCoordinates(destination.getX(), destination.getY());
	    		((Enemy)obj).setCanMove(false);
	    		System.out.println("here");
	    	}
    	}*/
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