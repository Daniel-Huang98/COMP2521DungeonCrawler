package unsw.dungeon;

public class deathBattle implements battle{

	@Override
	public boolean attacked(Player player) {
		player.die();
		return false;
	}

}
