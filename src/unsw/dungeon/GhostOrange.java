package unsw.dungeon;

import movement.FrightenMovement;

/**
 * Orange ghost is a subclass of Ghost
 */
public class GhostOrange extends Ghost{

	/**
	 * Constructs an orange ghost object that contains x,y coordinates
	 * @param x
	 * @param y
	 */
	GhostOrange(int x, int y, double tick) {
		super(x, y, tick);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Decide movement strategy of the orange ghost and move it
	 * @param obj : the timeline object
	 * @param dX : change in horizontal direction
	 * @param dY : change in vertical direction
	 */
	@Override
    public void update(playerSubject obj, int dX, int dY) {
    	if(isDeleted()) return;
		else if (obj instanceof TimelineObject) {
			if (player.getPotion() != null) {
				if (!(movement instanceof FrightenMovement))
					((FrightenMovement)frightenMovement).setCheckBehind(true);
				movement = frightenMovement;
				//System.out.println(getX()+ " " + getY());
				((FrightenMovement)frightenMovement).setPrev(getX(),getY());
				if(counter % 2 == 0) {
					movement.moveCharacter(this, player,map.size(), map.get(0).size(), map);
				}
				this.changeFrighten();
			}
			else {
				int distance = (this.getX()-player.getX())*(this.getX()-player.getX()) + (this.getY()-player.getY())*(this.getY()-player.getY());
				if(counter > 7 && distance <= 64) {
					counter = 0;
				}
				if((counter)*tick >= 30) counter = 0;
				if(counter*tick < 7) {
					movement = further;
				} else {
					movement = closer;
				}
				movement.moveCharacter(this, player,map.size(), map.get(0).size(), map);
				this.changeNormal();
			}
			counter++;
	    	this.notifyEntities(0,0);
		}
    }
}
