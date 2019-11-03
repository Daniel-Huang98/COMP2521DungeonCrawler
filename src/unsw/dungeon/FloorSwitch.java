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
	
	/**
	 * Constructs a floor switch object that holds x,y coordinates
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public FloorSwitch(int x, int y, Dungeon dungeon) {
        super(x, y);
        this.dungeon = dungeon;
    }
    
    public Boulder getBoulder() {
    	return boulder;
    }
    
    /**
     * Checks if boulder is moved onto switch, then sets the boulder
     * reference within the class if so.
     * Otherwise if the boulder was on the switch, checks the boulder
     * reference to see if it moved off the switch
     * @param obj: a subject that is observed
     * @param dX: the subject's change in X direction
     * @param dY: the subject's change in Y direction
     */
    @Override
    public void update(playerSubject obj, int dX, int dY) {
    	if (obj instanceof Boulder) {
    		if(boulder == null && ((dX + ((Boulder)obj).getX()) == this.getX() && (((Boulder)obj).getY()+dY) == this.getY())) {
    			boulder = (Boulder)obj;
    			dungeon.activateSwitch();
    		}

    		else if (boulder != null && boulder.equals((Boulder)obj)) {
    			boulder = null;
    			System.out.println("switch moved off");
    			dungeon.deactivateSwitch();
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