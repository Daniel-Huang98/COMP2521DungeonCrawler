package unsw.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;
import unsw.dungeon.playerObserver;

class PortalTest {
	
	/*
	 * When a player steps in one of the portal pairs, they are 
	 * immediately transported to the other portal in the pair
	 * Each level has pairs of portals that only work within the 
	 * bounds of the room
	 */
	@Test
	void testPortal() {
		Player player = new Player(new Dungeon(4,4), 0, 0);
		Portal portal1 = new Portal(1,0);
		Portal portal2 = new Portal(3,3);
		portal1.setPortalPair(portal2);
		portal2.setPortalPair(portal1);
		player.addObserver((playerObserver)portal1);
		player.addObserver((playerObserver)portal2);
		player.moveRight();
		assertEquals(player.getX(), 3);
		assertEquals(player.getY(), 3);
	}
}