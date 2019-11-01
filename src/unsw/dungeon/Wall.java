package unsw.dungeon;

/**
 * Wall entity, observes subjects to make sure they cannot 
 * walk through it
 */
public class Wall extends Entity implements playerObserver {

    public Wall(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void update(playerSubject obj, int dX, int dY) {
    	if (obj instanceof Player) {
	    	if((dX + ((Player)obj).getX()) == this.getX() && (((Player)obj).getY()+dY) == this.getY()) {
	    		((Player)obj).setCanMove(false);
	    	}
    	}
    	else if (obj instanceof Boulder) {
	    	if((dX + ((Boulder)obj).getX()) == this.getX() && (((Boulder)obj).getY()+dY) == this.getY()) {
	    		((Boulder)obj).setCanMove(false);
	    	} 
    	}
    }

}
