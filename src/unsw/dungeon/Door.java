package unsw.dungeon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Door entity that has a boolean which indicates opened or closed
 */
public class Door extends Entity implements playerObserver{

	private boolean isOpened = false;
	boolean deleted = false;
	int id;
	ImageView view;
	Image open;
	
	/**
	 * Constructs a door object that has x,y coordinates
	 * @param x : x coordinate
     * @param y : y coordinate
	 */
    public Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }
    
    public boolean getOpened() {
    	return isOpened;
    }

    public void setImage(ImageView view){
    	this.view = view;
    }
    
    public void setOpen(Image open) {
    	this.open = open;
    }
    /**
     * Checks if player is on the door and opens only if they have the
     * corresponding key
     * @param obj: a subject that is observed
     * @param dX: the subject's change in X direction
     * @param dY: the subject's change in Y direction
     */
	@Override
	public void update(playerSubject obj, int dX, int dY) {
		if (obj instanceof Player) {
			if (((dX + ((Player)obj).getX()) == this.getX() && (((Player)obj).getY()+dY) == this.getY())) {
				Key k = ((Player)obj).getKey();
	    		if((k == null || k.getId() != this.id) && !getOpened()) {
	    			isOpened = false;
	    			((Player)obj).setCanMove(false);
	    			System.out.println("Cannot open the door");
	    		}
	    		else if (!getOpened() && k.getId() == this.id){
	    			isOpened = true;
	    			((Player)obj).setKey(null);
	    			System.out.println("Door unlocked");
	    			if(view != null)this.view.setImage(open);
	    		}
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