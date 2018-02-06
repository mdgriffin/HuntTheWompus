package jri.agile.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

public class GameTests {
	
	private Game game;
	
	@Before
	public void setup () {
		this.game = new Game(5,5);
	}
	
	@Test
	public void gameBoardCreates () {
		
		Room[][] rooms = game.getRooms();

		assertEquals(rooms[0].length, 5);
	}
	
	@Test
	public void getSingleRoom () {
		Room room = game.getRoom(0, 0);
		
		assertEquals(room.getRow(), 0);
		assertEquals(room.getColumn(), 0);
	}
	
	@Test
	public void gameHasPlayer () {
		GameEntity player = game.getPlayer();
		BoardPosition position = player.getCurrentPosition();
		
		assertEquals(0, position.getXPos());
		assertEquals(0, position.getYPos());
	}
	
	@Test
	public void playerCanMove () {
		GameEntity player = game.getPlayer();
		
		BoardPosition position = player.getCurrentPosition();
		
		assertEquals(0, position.getXPos());
		assertEquals(0, position.getYPos());
		
		game.movePlayer('E');
		
		assertEquals(1, position.getXPos());
		assertEquals(0, position.getYPos());
		
		game.movePlayer('S');
		
		assertEquals(1, position.getXPos());
		assertEquals(1, position.getYPos());
		
		game.movePlayer('W');
		
		assertEquals(0, position.getXPos());
		assertEquals(1, position.getYPos());
		
		game.movePlayer('N');
		
		assertEquals(0, position.getXPos());
		assertEquals(0, position.getYPos());
	}
	
	@Test
	public void playerCannotMoveIfAtEdge () {
		BoardPosition position = game.getPlayer().getCurrentPosition();
		
		game.movePlayer('W');
		
		assertEquals(0, position.getXPos());
		assertEquals(0, position.getYPos());
		
		game.movePlayer('N');
		
		assertEquals(0, position.getXPos());
		assertEquals(0, position.getYPos());
		
		game.movePlayer('S');
		game.movePlayer('S');
		game.movePlayer('S');
		game.movePlayer('S');
		game.movePlayer('S');
		
		assertEquals(0, position.getXPos());
		assertEquals(4, position.getYPos());
		
		game.movePlayer('E');
		game.movePlayer('E');
		game.movePlayer('E');
		game.movePlayer('E');
		game.movePlayer('E');
		
		assertEquals(4, position.getXPos());
		assertEquals(4, position.getYPos());
	}
	
	@Test
	public void gameHasWombus () {
		GameEntity rick = game.getRick();
		BoardPosition position = rick.getCurrentPosition();
		
		assertEquals(4, position.getXPos());
		assertEquals(4, position.getYPos());
	}
	
	@Test
	public void canMoveRick () {
		GameEntity rick = game.getRick();
		
		BoardPosition position = rick.getCurrentPosition();
		
		assertEquals(4, position.getXPos());
		assertEquals(4, position.getYPos());
		
		
		game.moveRick('N');
		
		assertEquals(4, position.getXPos());
		assertEquals(3, position.getYPos());
		
		
		game.moveRick('W');
		
		assertEquals(3, position.getXPos());
		assertEquals(3, position.getYPos());
		
		game.moveRick('S');
		
		assertEquals(3, position.getXPos());
		assertEquals(4, position.getYPos());
		
		game.moveRick('E');
		
		assertEquals(4, position.getXPos());
		assertEquals(4, position.getYPos());
	}
	
	@Test
	public void rickMovesRandomly () {
		Game game = new Game(3, 3);
		GameEntity rick = game.getRick();
		BoardPosition position = rick.getCurrentPosition();
		int movedNorth = 0;
		int movedSouth = 0;
		int movedEast = 0;
		int movedWest = 0;
		int rested = 0;
		
		for (int i = 0; i < 1000; i++) {
			position.setXPos(1);
			position.setYPos(1);
			
			game.moveRickRandom();
			
			if (position.getYPos() == 0) {
				movedNorth++;
			} else if (position.getYPos() == 2) {
				movedSouth++;
			} else  if (position.getXPos() == 0) {
				movedWest++;
			} else if (position.getXPos() == 2) {
				movedEast++;
			} else if (position.getXPos() == 1 && position.getYPos() == 1) {
				rested++;
			}
		}
	
		assertEquals(1000, movedNorth + movedSouth + movedEast + movedWest + rested);
		
		assertTrue (movedNorth > 150);
		assertTrue (movedSouth > 150);
		assertTrue (movedEast > 150);
		assertTrue (movedWest > 150);
		assertTrue (rested > 150);
	}
	
}
