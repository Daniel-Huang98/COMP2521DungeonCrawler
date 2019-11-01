package unsw.dungeon;

public class Sword extends Entity implements playerObserver{
	int health;

    public Sword(int x, int y) {
        super(x, y);
        this.health = 5;
    }

    @Override
    public void update(playerSubject player, int dX, int dY) {
    	
    }
}
