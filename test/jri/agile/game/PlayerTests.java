package jri.agile.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTests {
	
	private Game game;

	@Before
	public void setup () {
		game = new Game(5, 5);
	}
	
	@Test
	public void playerCanRest() {
		Player player = game.getPlayer();
		
		int beforeRestingPositionX = player.getCurrentPosition().getXPos();
		int beforeRestingPositionY = player.getCurrentPosition().getYPos();
		
		player.rest();
		
		int afterRestingPositionX = player.getCurrentPosition().getXPos();
		int afterRestingPositionY = player.getCurrentPosition().getYPos();
	
		assertEquals(beforeRestingPositionX, afterRestingPositionX);
		assertEquals(beforeRestingPositionY, afterRestingPositionY);
	}

}
