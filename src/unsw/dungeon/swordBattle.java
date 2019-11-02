package unsw.dungeon;

public class swordBattle implements battle{

	@Override
	public boolean attacked(Player player) {
		player.decrementSwordHealth();
		return true;
	}

}
