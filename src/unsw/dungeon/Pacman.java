package unsw.dungeon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pacman extends Player{
	Image closed = new Image("/pacmanClosed.png");
	Image open = new Image("/pacman.png");
	ImageView view;
	boolean moved = false;

	public Pacman(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		System.out.println("pacman");
	}
	
	public void setImage(ImageView view) {
		this.view = view;
	}
	
	/**
     * Checks if player can legally move up by notifying
     * all observer
     */
	@Override
    public void moveUp() {
		moved = !moved;
		if(moved) {
			this.view.setImage(closed);
		} else {
			this.view.setImage(open);
		}
		this.view.setRotate(-90); 
    	notifyEntities(0,-1);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
    	this.facing = "north";
        if (getY() > 0)
            //y().set(getY() - 1);
        	dy = -1;
        updateStatus();
    }

    /**
     * Checks if player can legally move down by notifying
     * all observer
     */
	@Override
    public void moveDown() {
		moved = !moved;
		if(moved) {
			this.view.setImage(closed);
		} else {
			this.view.setImage(open);
		}
		this.view.setRotate(90); 
    	notifyEntities(0,1);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
    	this.facing = "south";
        if (getY() < dungeon.getHeight() - 1)
            //y().set(getY() + 1);
        	dy = 1;
        updateStatus();
    }

    /**
     * Checks if player can legally move left by notifying
     * all observer
     */
	@Override
    public void moveLeft() {
		moved = !moved;
		if(moved) {
			this.view.setImage(closed);
		} else {
			this.view.setImage(open);
		}
		this.view.setRotate(180); 
    	notifyEntities(-1,0);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
    	this.facing = "west";
        if (getX() > 0)
            //x().set(getX() - 1);
        	dx = -1;
        updateStatus();
    }

    /**
     * Checks if player can legally move right by notifying
     * all observer
     */
	@Override
    public void moveRight() {
		moved = !moved;
		if(moved) {
			this.view.setImage(closed);
		} else {
			this.view.setImage(open);
		}
		this.view.setRotate(0); 
    	notifyEntities(1,0);
    	if(!canMove) {
    		canMove = true;
    		return;
    	}
    	this.facing = "east";
        if (getX() < dungeon.getWidth() - 1)
            //x().set(getX() + 1);
        	dx = 1;
        updateStatus();
    }
	
	@Override
	public void update(playerSubject obj, int dX, int dY) {
		//System.out.println("check player");

		if (obj instanceof Enemy) {
			//check if enemy is about to move onto the player
			if (((Enemy)obj).getX()+dX == this.getX()+this.dx && ((Enemy)obj).getY()+dY == this.getY()+this.dy) {
	    		if(getAction().attacked(this)) {
	    			((Enemy)obj).delete(); 	
	    			System.out.println("Enemy has died");
	    		}
	    	} if (((Enemy)obj).getX()+dX == this.getX() && ((Enemy)obj).getY()+dY == this.getY()) {
	    		if(getAction().attacked(this)) {
	    			((Enemy)obj).delete(); 	
	    			System.out.println("Enemy has died");
	    		}
	    	}
		}
		else if (obj instanceof TimelineObject) {
			notifyEntities(dx,dy);
			if(canMove) {
				x().set(getX() + dx);
				y().set(getY() + dy);
			} else {
				canMove = true;
				
			}
			dx = 0;
			dy = 0;
			if(this.potion != null) {
				int potionHealth = potion.getHealth();
				potionStatus.set((float)potionHealth/this.potion.getFullHealth());
				this.decrementPotionHealth();
			}
		}
		updateStatus();
	}


}
