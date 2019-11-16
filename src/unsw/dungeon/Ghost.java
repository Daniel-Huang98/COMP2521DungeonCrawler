package unsw.dungeon;

import javafx.scene.image.Image;
import movement.FrightenMovement;
import movement.Movement;

public class Ghost extends Enemy{
	float counter = 0;
	private Image normal;
	private Image frighten;
	private Movement frightenMovement;
	protected double tick;
	boolean flag = false;
	
	Ghost(int x, int y, double tick){
		super(x, y);
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
				movement.moveCharacter(this, player,map.size(), map.get(0).size(), map);
				this.changeNormal();
			}
			counter++;
	    	this.notifyEntities(0,0);
		}
    }
}
