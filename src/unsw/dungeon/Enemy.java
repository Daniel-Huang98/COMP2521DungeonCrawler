package unsw.dungeon;

public class Enemy extends Entity implements playerObserver{
	boolean deleted = false;
	
    public Enemy(int x, int y) {
        super(x, y);
    }

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