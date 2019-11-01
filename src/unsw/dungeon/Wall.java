package unsw.dungeon;

public class Wall extends Entity implements playerObserver {

    public Wall(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void update(playerSubject player, int x, int y) {
    	
    	if(x == this.getX() && y == this.getY()) {
    		((Player)player).setCanMove(false);
    	}
    	
    }

}
