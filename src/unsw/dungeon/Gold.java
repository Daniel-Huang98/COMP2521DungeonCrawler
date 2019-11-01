package unsw.dungeon;

public class Gold extends Entity implements playerObserver{
	boolean deleted = false;
	
    public Gold(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(playerSubject player, int dX, int dY) {
    	
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
