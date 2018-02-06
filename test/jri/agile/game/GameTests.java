package jri.agile.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

public class GameTests {
	
	
	@Test
	public void GameBoardCreates () {
		
		Game game = new Game();

		assertTrue(true);
	}

	
	@Test
	public void PlayerCanMove () {
		assertEquals(true, false);
	}
}
