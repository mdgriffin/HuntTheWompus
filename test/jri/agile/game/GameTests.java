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
	public void playerCanMove () {
		Player player = new Player();
		
		BoardPosition position = player.getCurrentPosition();
		
		assertEquals(0, position.getXPos());
		assertEquals(0, position.getYPos());
		
		player.move('E');
		
		assertEquals(1, position.getXPos());
		assertEquals(0, position.getYPos());
		
		player.move('S');
		
		assertEquals(1, position.getXPos());
		assertEquals(1, position.getYPos());
		
		player.move('W');
		
		assertEquals(0, position.getXPos());
		assertEquals(1, position.getYPos());
		
		player.move('N');
		
		assertEquals(0, position.getXPos());
		assertEquals(0, position.getYPos());
	}
	
	@Test
	public void playerCannotMoveIfAtEdge () {
		Player player = new Player();
		
		BoardPosition position = player.getCurrentPosition();
		
		player.move('W');
		
		assertEquals(0, position.getXPos());
		assertEquals(0, position.getYPos());
		
		player.move('N');
		
		assertEquals(0, position.getXPos());
		assertEquals(0, position.getYPos());
	}
	



}
