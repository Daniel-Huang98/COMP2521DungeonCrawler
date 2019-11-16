package unsw.dungeon;

import movement.FrightenMovement;

public class GhostRed extends Ghost{
	GhostRed(int x, int y, double tick) {
		super(x, y, tick);
		// TODO Auto-generated constructor stub
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
				if((counter)*tick >= 30) counter = 10;
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
