package unsw.dungeon;

public class Potion extends Entity implements playerObserver{
	boolean deleted = false;
	int health;
	 
	
    public Potion(int x, int y) {
        super(x, y);
        this.health = 15;
    }
    
    public boolean decrementHealth(){
    	this.health--;
    	System.out.println("health: " + this.health);
    	if(this.health == 0) {
    		return true;
    	}
    	return false;
    }
    
    @Override
    public void update(playerSubject player, int dX, int dY) {
    	if(((Player)player).getX()+dX == this.getX() && ((Player)player).getY()+dY == this.getY()) {
    		System.out.println("picked up potion");
    		((Player)player).setPotion(this);
    		((Player)player).setAction(new potionBattle());
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