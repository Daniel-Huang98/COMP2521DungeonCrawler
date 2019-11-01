package unsw.dungeon;

public class Enemy extends Entity implements playerObserver{

    public Enemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(playerSubject player, int dX, int dY) {
    	
    }
}