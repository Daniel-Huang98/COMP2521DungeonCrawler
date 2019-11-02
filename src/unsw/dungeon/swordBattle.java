package unsw.dungeon;

public class swordBattle implements battle{

	@Override
	public boolean attacked(Player player) {
		player.getSword().decrementHealth();
		return true;
	}

}
