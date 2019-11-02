package unsw.dungeon;

/*
 * Key entity that matches to a single door
 */
public class Key extends Entity implements playerObserver{
	boolean deleted = false;
	
	/*
	 * Constructs key object that contains x, y coordinates
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public Key(int x, int y) {
        super(x, y);
    }

    /*
     * Checks if player is on key and sets a boolean within the player
     * indicating if he has the key or not
     * @param obj: a subject that is observed
     * @param dX: the subject's change in X direction
     * @param dY: the subject's change in Y direction
     */
    @Override
    public void update(playerSubject obj, int dX, int dY) {
		if (obj instanceof Player) {
    		if(((dX + ((Player)obj).getX()) == this.getX() && (((Player)obj).getY()+dY) == this.getY()) && !isDeleted()) {
    			((Player)obj).setKey(true);
    			delete();
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