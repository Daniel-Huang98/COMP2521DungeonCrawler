package unsw.dungeon;
import java.util.ArrayList;

/**
 * Floor switch entity which holds a reference to the boulder
 * object that is currently on it, null otherwise
 */
public class FloorSwitch extends Entity implements playerObserver{
	boolean deleted = false;
	Boulder boulder = null;
	Dungeon dungeon;
	
    public FloorSwitch(int x, int y, Dungeon dungeon) {
        super(x, y);
        this.dungeon = dungeon;
    }
    
    public Boulder getBoulder() {
    	return boulder;
    }

    @Override
    public void update(playerSubject obj, int dX, int dY) {
    	if (obj instanceof Boulder) {
    		if(boulder == null && ((dX + ((Boulder)obj).getX()) == this.getX() && (((Boulder)obj).getY()+dY) == this.getY())) {
    			boulder = (Boulder)obj;
    			dungeon.activateSwitch();
    		}
    		else if (this.boulder != null && this.boulder.equals((Boulder)obj))
    			this.boulder = null;
    			System.out.println("switch moved off");
    			dungeon.deactivateSwitch();
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