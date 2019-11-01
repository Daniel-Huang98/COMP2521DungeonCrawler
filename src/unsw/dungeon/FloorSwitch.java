package unsw.dungeon;
import java.util.ArrayList;

/**
 * Floor switch entity which holds a reference to the boulder
 * object that is currently on it, null otherwise
 */
public class FloorSwitch extends Entity implements playerObserver{
	boolean deleted = false;
	Boulder boulder = null;
	
    public FloorSwitch(int x, int y) {
        super(x, y);
    }
    
    public Boulder getBoulder() {
    	return boulder;
    }

    @Override
    public void update(playerSubject obj, int dX, int dY) {
    	if (obj instanceof Boulder) {
    		if(boulder == null && ((dX + ((Boulder)obj).getX()) == this.getX() && (((Boulder)obj).getY()+dY) == this.getY())) {
    			boulder = (Boulder)obj;
    		}
    		else if (boulder != null && boulder.equals((Boulder)obj))
    			boulder = null;
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