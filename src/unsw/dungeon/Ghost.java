package unsw.dungeon;

import javafx.scene.image.Image;

public class Ghost extends Enemy{
	private Image normal;
	private Image frighten;
	
	Ghost(int x, int y){
		super(x, y);
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
	
	
    @Override
    public void update(playerSubject obj, int dX, int dY) {
    	if(isDeleted()) return;
		else if (obj instanceof TimelineObject) {
			if (player.getPotion() != null) {
				movement = further;
				movement.moveCharacter(this, player,map.size(), map.get(0).size(), map);
				this.changeFrighten();
			}
			else {
				movement = closer;
				movement.moveCharacter(this, player,map.size(), map.get(0).size(), map);
				this.changeNormal();
			}
	    	this.notifyEntities(0,0);
		}
    }
}
