package jri.agile.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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
	public void gameHasWombus () {
		GameEntity rick = game.getRick();
		BoardPosition position = rick.getCurrentPosition();
		
		assertEquals(4, position.getXPos());
		assertEquals(4, position.getYPos());
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
	public void gameHasTwoBatRooms () {
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
	
	@Test
	public void batsCanMovePlayer () {
		int timesPlayerMoved = 0;
		Game game;
		Player player;
				
		for (int i = 0; i < 100; i++) {
			game = new Game(5, 5);
			player = game.getPlayer();
			
			BoardPosition playerPos = game.getPlayer().getCurrentPosition();
			game.setRoom(0, 1, new Room(0, 1, Room.RoomType.BatRoom));
			player.move('E');
			
			if (playerPos.getXPos() != 1 || playerPos.getYPos() != 1) {
				timesPlayerMoved++;
			}
		}
		
		assertTrue(timesPlayerMoved > 90);
	}
	
	@Test
	public void arrowFliesEast () {
		Player player = game.getPlayer();
		
		int numArrowsInLastRoomAfterShoot = game.getRoom(0, 4).getNumArrows();
		
		assertEquals(0, numArrowsInLastRoomAfterShoot);
		
		player.shoot('E');
		
		numArrowsInLastRoomAfterShoot = game.getRoom(0, 4).getNumArrows();
		
		assertTrue(numArrowsInLastRoomAfterShoot > 0);
	}
	
	@Test
	public void arrowFliesWest () {
		Game game = GameTestUtil.buildEmptyMap(5, 5);
		
		Player player = game.getPlayer();
		
		player.move('E');
		player.move('E');
		
		int numArrowsInLastRoomAfterShoot = game.getRoom(0, 0).getNumArrows();
		
		assertEquals(0, numArrowsInLastRoomAfterShoot);
		
		player.shoot('W');
		
		numArrowsInLastRoomAfterShoot = game.getRoom(0, 0).getNumArrows();
		
		assertTrue(numArrowsInLastRoomAfterShoot > 0);
	}
	
	@Test
	public void arrowFliesSouth () {
		Player player = game.getPlayer();
		
		int numArrowsInLastRoomAfterShoot = game.getRoom(4, 0).getNumArrows();
		
		assertEquals(0, numArrowsInLastRoomAfterShoot);
		
		player.shoot('S');
		
		numArrowsInLastRoomAfterShoot = game.getRoom(4, 0).getNumArrows();
		
		assertTrue(numArrowsInLastRoomAfterShoot > 0);
	}
	
	@Test
	public void arrowFliesNorth () {
		Game game = GameTestUtil.buildEmptyMap(5, 5);
		
		Player player = game.getPlayer();
		
		player.move('S');
		player.move('S');
		
		int numArrowsInLastRoomAfterShoot = game.getRoom(0, 0).getNumArrows();
		
		assertEquals(0, numArrowsInLastRoomAfterShoot);
		
		player.shoot('N');
		
		numArrowsInLastRoomAfterShoot = game.getRoom(0, 0).getNumArrows();
		
		assertTrue(numArrowsInLastRoomAfterShoot > 0);
	}
	
}
