package unsw.dungeon;

public class Exit extends Entity implements playerObserver{
	boolean deleted = false;
	
	/**
	 * Constructor for the Exit entity
	 * @param x The x coordinate of the exit in the dungeon
	 * @param y The y coordinate of the exit in the dungeon
	 */
    public Exit(int x, int y) {
        super(x, y);
    }
    
    /**
     * When the player enters the exit, if the dungeon goals have been 
     * reached the player can complete the dungeon, else the player must 
     * continue playing
     * @param obj: a subject that is observed
     * @param dX: the subject's change in X direction
     * @param dY: the subject's change in Y direction
     */
    @Override
    public void update(playerSubject obj, int dX, int dY) {
    	if (obj instanceof Player) {
    		if ((dX + ((Player)obj).getX()) == this.getX() && (((Player)obj).getY()+dY) == this.getY() && !((Player)obj).canWin()){
	    		((Player)obj).setCanMove(false);
	    		((Player)obj).exit();
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