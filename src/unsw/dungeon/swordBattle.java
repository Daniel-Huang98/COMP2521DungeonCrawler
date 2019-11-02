package unsw.dungeon;

/**
 * Strategy for when player holds a sword
 */
public class swordBattle implements battle{

	@Override
	public boolean attacked(Player player) {
		player.decrementSwordHealth();
		return true;
	}

}
