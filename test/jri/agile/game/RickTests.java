package jri.agile.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RickTests {

	private Game game;
	
	@Before
	public void setup () {
		this.game = new Game(5,5);
	}
	
	@Test
	public void canMoveRick () {
		Game game = GameTestUtil.buildEmptyMap(5, 5);
		
		GameEntity rick = game.getRick();
		
		BoardPosition position = rick.getCurrentPosition();
		
		assertEquals(4, position.getXPos());
		assertEquals(4, position.getYPos());
		
		rick.move('N');
		
		assertEquals(4, position.getXPos());
		assertEquals(3, position.getYPos());
		
		rick.move('W');
		
		assertEquals(3, position.getXPos());
		assertEquals(3, position.getYPos());
		
		rick.move('S');
		
		assertEquals(3, position.getXPos());
		assertEquals(4, position.getYPos());
		
		rick.move('E');
		
		assertEquals(4, position.getXPos());
		assertEquals(4, position.getYPos());
	}
	
	@Test
	public void rickMovesRandomly () {
		Game game = GameTestUtil.buildEmptyMap(3, 3);
		GameEntity rick = game.getRick();
		BoardPosition position = rick.getCurrentPosition();
		int movedNorth = 0;
		int movedSouth = 0;
		int movedEast = 0;
		int movedWest = 0;
		
		for (int i = 0; i < 1000; i++) {
			position.setXPos(1);
			position.setYPos(1);
			
			rick.moveRandom();
			
			if (position.getYPos() == 0) {
				movedNorth++;
			} else if (position.getYPos() == 2) {
				movedSouth++;
			} else  if (position.getXPos() == 0) {
				movedWest++;
			} else if (position.getXPos() == 2) {
				movedEast++;
			}
		}
	
		assertEquals(1000, movedNorth + movedSouth + movedEast + movedWest);
		
		assertTrue (movedNorth > 200);
		assertTrue (movedSouth > 200);
		assertTrue (movedEast > 200);
		assertTrue (movedWest > 200);
	}
	
	@Test
	public void checkRickAndPlayerInRoom () {
		Game game = GameTestUtil.buildEmptyMap(5, 5);
		Player player = game.getPlayer();
		game.getRick().freeze();
		
		player.move('S');
		player.move('S');
		player.move('S');
		player.move('S');
		
		assertTrue(!game.isPlayerInRoomWithRick());
		
		player.move('E');
		player.move('E');
		player.move('E');
		player.move('E');
		
		assertTrue(game.isPlayerInRoomWithRick());
		
		assertTrue(!player.isAlive());
	}
	
	@Test
	public void rickMovesAfterPlayerShoot () {
		int numTimesRickMoved = 0;
		int numShots = 100;
		game.setPlayer(new Player(numShots, game, 0, 0));
		Player player = game.getPlayer();
		int positionXBeforeShoot;
		int positionYBeforeShoot;
		int positionXAfterShoot;
		int positionYAfterShoot;
		
		for (int i = 0; i < numShots; i++) {
			positionXBeforeShoot = game.getRick().getCurrentPosition().getXPos();
			positionYBeforeShoot = game.getRick().getCurrentPosition().getYPos();
			
			player.shoot('S');
			
			positionXAfterShoot = game.getRick().getCurrentPosition().getXPos();
			positionYAfterShoot = game.getRick().getCurrentPosition().getYPos();
			
			if (positionXBeforeShoot != positionXAfterShoot || positionYBeforeShoot != positionYAfterShoot) {
				numTimesRickMoved++;
			}
		}
		assertEquals(numShots, numTimesRickMoved);
	}
	
}
