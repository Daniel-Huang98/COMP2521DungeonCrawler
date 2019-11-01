package unsw.dungeon;

public class Wall extends Entity implements playerObserver {

    public Wall(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void update(playerSubject player, int dX, int dY) {
    	
    	if((dX + ((Player)player).getX()) == this.getX() && (((Player)player).getY()+dY) == this.getY()) {
    		((Player)player).setCanMove(false);
    	}
    	
    }

}
