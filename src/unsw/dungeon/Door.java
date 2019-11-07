package unsw.dungeon;

/**
 * Door entity that has a boolean which indicates opened or closed
 */
public class Door extends Entity implements playerObserver{

	private boolean isOpened = false;
	boolean deleted = false;
	
	/**
	 * Constructs a door object that has x,y coordinates
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public Door(int x, int y) {
        super(x, y);
    }
    
    public boolean getOpened() {
    	return isOpened;
    }

    /**
     * Checks if player is on the door and opens only if they have the
     * corresponding key
     * @param obj: a subject that is observed
     * @param dX: the subject's change in X direction
     * @param dY: the subject's change in Y direction
     */
	@Override
	public void update(playerSubject obj, int dX, int dY) {
		if (obj instanceof Player) {
    		if(((dX + ((Player)obj).getX()) == this.getX() && (((Player)obj).getY()+dY) == this.getY()) && !((Player)obj).getKey() && !getOpened()) {
    			isOpened = false;
    			System.out.println("Cannot open the door");
    		}
    		else if (((dX + ((Player)obj).getX()) == this.getX() && (((Player)obj).getY()+dY) == this.getY()) && !getOpened()){
    			isOpened = true;
    			((Player)obj).setKey(false);
    			System.out.println("Door unlocked");
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