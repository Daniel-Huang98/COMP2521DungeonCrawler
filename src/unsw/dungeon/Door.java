package unsw.dungeon;

public class Door extends Entity implements playerObserver{

	private boolean isOpened = false;
	boolean deleted = false;
	
    public Door(int x, int y) {
        super(x, y);
    }

	@Override
	public void update(playerSubject obj, int dX, int dY) {
		if (obj instanceof Player) {
    		if((dX + ((Player)obj).getX()) == this.getX() && (((Player)obj).getY()+dY) == this.getY()) {
    			isOpened = true;
    		}
    		else
    			isOpened = false;
    	}
    	return;
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