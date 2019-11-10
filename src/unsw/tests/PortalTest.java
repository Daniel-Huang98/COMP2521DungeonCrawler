package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;
import unsw.dungeon.playerObserver;

class PortalTest {
	
	/**
	 * When a player steps in one of the portal pairs, they are 
	 * immediately transported to the other portal in the pair
	 * Each level has pairs of portals that only work within the 
	 * bounds of the room
	 */
	@Test
	void testMatchingPortal() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Portal portal1 = new Portal(1,0,1);
		Portal portal2 = new Portal(3,3,1);
		portal1.setPortalPair(portal2);
		portal2.setPortalPair(portal1);
		player.addObserver((playerObserver)portal1);
		player.addObserver((playerObserver)portal2);
		player.moveRight();
		assertEquals(player.getX(), 3, "Portal Test");
		assertEquals(player.getY(), 3, "Portal Test");
	}
	
	/**
	 * When there is no other portal with the same id, the
	 * player stays where they are
	 */
	void testNotMatchingPortal() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Portal portal1 = new Portal(1,0,1);
		Portal portal2 = new Portal(3,3,2);
		portal1.setPortalPair(portal2);
		portal2.setPortalPair(portal1);
		player.addObserver((playerObserver)portal1);
		player.addObserver((playerObserver)portal2);
		player.moveRight();
		assertEquals(player.getX(), 1, "Portal Test");
		assertEquals(player.getY(), 0, "Portal Test");
	}
}