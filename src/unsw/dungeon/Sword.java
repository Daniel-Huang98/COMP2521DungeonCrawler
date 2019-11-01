package unsw.dungeon;

public class Sword extends Entity implements playerObserver{
	int health;
	boolean deleted = false;

    public Sword(int x, int y) {
        super(x, y);
        this.health = 5;
    }
    
    public boolean decrementHealth() {
    	this.health--;
    	if(this.health == 0) {
    		return true;
    	}
    	return false;
    }

    @Override
    public void update(playerSubject player, int dX, int dY) {
    	if(((Player)player).getX()+dX == this.getX() && ((Player)player).getY()+dY == this.getY()) {
    		((Player)player).setSword(this);
    		this.delete(); 		
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
