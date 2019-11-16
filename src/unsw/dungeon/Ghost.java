package unsw.dungeon;

import javafx.scene.image.Image;
import movement.FrightenMovement;
import movement.Movement;

/**
 * Ghost is a subclass of enemy for Pacman
 */
public class Ghost extends Enemy{
	float counter = 0;
	protected Image normal;
	protected Image frighten;
	protected Movement frightenMovement;
	protected double tick;
	boolean flag = false;
	int startX;
	int startY;
	int endCounter = 0;
	
	/**
	 * Constructs a ghost object that contains x,y coordinates
	 * and a tick for movement timing
	 * @param x
	 * @param y
	 * @param tick
	 */
	Ghost(int x, int y, double tick){
		super(x, y);
		this.startX = x;
		this.startY = y;
		this.tick = tick;
	}
	
	
	public void setNormal(Image normal) {
		this.normal = normal;
	}
	
	public void setFrighten(Image frighten) {
		this.frighten = frighten;
	}
	
	public void changeNormal() {
		this.view.setImage(normal);
	}
	
	public void changeFrighten(){
		this.view.setImage(frighten);
	}
	
	public void setFrightenMovement(Movement frighten) {
		this.frightenMovement = frighten;
	}
	
	/**
	 * Ghost died, respawn at the start
	 */
	@Override
	public void delete() {
		this.x().set(startX);
		this.y().set(startY);
		int endCounter = (int) (counter+(5*(1/tick)));
		//this.gone().set(true);
	}

	
	/**
	 * Decide movement strategy of the ghost and move it
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
				if((counter)*tick >= 30) counter = 0;
				if(counter*tick < 7) {
					movement = further;
				} else {
					movement = closer;
				}
				if(counter > endCounter)movement.moveCharacter(this, player,map.size(), map.get(0).size(), map);
				
				this.changeNormal();
			}
			counter++;
	    	this.notifyEntities(0,0);
		}
    }
}
