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
	
	@Test
	public void checkRickAndPlayerInRoom () {
		game.movePlayer('S');
		game.movePlayer('S');
		game.movePlayer('S');
		game.movePlayer('S');
		
		assertTrue(!game.isPlayerInRoomWithRick());
		
		game.movePlayer('E');
		game.movePlayer('E');
		game.movePlayer('E');
		game.movePlayer('E');
		
		assertTrue(game.isPlayerInRoomWithRick());
	}
	
	
	@Test
	public void playerCanDie () {
		GameEntity player = game.getPlayer();
		
		game.movePlayer('S');
		game.movePlayer('S');
		game.movePlayer('S');
		game.movePlayer('S');
		
		assertTrue(player.isAlive());
		
		game.movePlayer('E');
		game.movePlayer('E');
		game.movePlayer('E');
		game.movePlayer('E');
		
		assertTrue(!player.isAlive());
	}
	
	@Test 
	public void roomCanBeSet () {
		Room expectedRoom = new Room(0, 1, Room.RoomType.PitRoom);
		game.setRoom(0, 1, expectedRoom);
		Room actualRoom = game.getRoom(0, 1);
		
		assertEquals(1, expectedRoom.getColumn());
		assertEquals(0, expectedRoom.getRow());
		
		assertEquals(expectedRoom, actualRoom);
		
		expectedRoom = new Room (1, 1, Room.RoomType.Normal);
		game.setRoom(1, 1, expectedRoom);
		actualRoom = game.getRoom(1, 1);
		
		assertEquals(1, expectedRoom.getColumn());
		assertEquals(1, expectedRoom.getRow());
		
		assertEquals(expectedRoom, actualRoom);
	}
	
	@Test
	public void playerCanDieInPit () {
		int row = 0;
		int col = 1;
		GameEntity player = game.getPlayer();
		
		assertEquals(0, player.getCurrentPosition().getXPos());
		assertEquals(0, player.getCurrentPosition().getYPos());
		
		Room pitRoom = new Room(row, col, Room.RoomType.PitRoom);
		
		assertTrue(game.getPlayer().isAlive());
		
		game.setRoom(row, col, pitRoom);
		
		game.movePlayer('E');
		
		assertTrue(!game.getPlayer().isAlive());
	}
	
	@Test
	public void gameHasTwoPits () {
		int width = 5;
		int height = 5;
		Game game  = new Game(height, width);
		int numPits = 0;
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (game.getRoom(row, col).hasPit()) {
					numPits++;
				}
			}
		}
			
		assertEquals(2, numPits);
	}
	
	@Test
	public void gameHasTwoBats () {
		int width = 5;
		int height = 5;
		Game game  = new Game(height, width);
		int numBats = 0;
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (game.getRoom(row, col).hasBats()) {
					numBats++;
				}
			}
		}
			
		assertEquals(2, numBats);
	}
	
	@Test
	public void gameHasVariableBats () {
		int expectedBats = 8;
		int width = 10;
		int height = 10;
		Game game  = new Game(height, width);
		int numBats = 0;
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (game.getRoom(row, col).hasBats()) {
					numBats++;
				}
			}
		}
			
		assertEquals(expectedBats, numBats);
	}
	
	public void gameHasVariablePits () {
		int expectedPits = 8;
		int width = 10;
		int height = 10;
		Game game  = new Game(height, width);
		int numPits = 0;
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (game.getRoom(row, col).hasBats()) {
					numPits++;
				}
			}
		}
			
		assertEquals(expectedPits, numPits);
	}

	
	@Test
	public void playerInNormalRoom () {
		int playerX = game.getPlayer().getCurrentPosition().getXPos();
		int playerY = game.getPlayer().getCurrentPosition().getYPos();
		Room room = game.getRoom(playerY, playerX);
		
		assertTrue(!room.hasBats());
		assertTrue(!room.hasPit());
	}
	
	@Test
	public void rickInNormalRoom () {
		int rickX = game.getRick().getCurrentPosition().getXPos();
		int rickY = game.getRick().getCurrentPosition().getYPos();
		Room room = game.getRoom(rickY, rickX);
		
		assertTrue(!room.hasBats());
		assertTrue(!room.hasPit());
	}
	
}
