package unsw.dungeon;

public class Gold extends Entity implements playerObserver{
	boolean deleted = false;
	
    public Gold(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(playerSubject obj, int dX, int dY) {
    	if (obj instanceof Player) {
	    	if((dX + ((Player)obj).getX()) == this.getX() && (((Player)obj).getY()+dY) == this.getY()) {
	    		System.out.println("collected the gold");
	    		((Player)obj).collectGold();
	    		this.delete();
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
