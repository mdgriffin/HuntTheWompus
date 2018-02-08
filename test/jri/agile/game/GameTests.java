package jri.agile.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;

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
	
	public static Game buildEmptyMap (int height, int width) {
		Game game = new Game(height, width);
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				game.setRoom(i, j, new Room (height, width, Room.RoomType.Normal));
			}
		}
		
		return game;
	}
	
	public static Game buildMap () {
		int width = 5;
		int height = 5;
		
		Game game = new Game(height, width);
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				game.setRoom(i, j, new Room (i, j, Room.RoomType.Normal));
			}
		}
		
		game.setRoom(0, 4, new Room (0, 4, Room.RoomType.BatRoom));
		game.setRoom(4, 0, new Room (4, 0, Room.RoomType.BatRoom));
		game.setRoom(2, 2, new Room (2, 2, Room.RoomType.PitRoom));
		
		return game;
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
		Game game = buildEmptyMap(5, 5);
		GameEntity player = game.getPlayer();
		
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
		Player player = game.getPlayer();
		BoardPosition position = game.getPlayer().getCurrentPosition();
		
		player.move('W');
		
		assertEquals(0, position.getXPos());
		assertEquals(0, position.getYPos());
		
		player.move('N');
		
		assertEquals(0, position.getXPos());
		assertEquals(0, position.getYPos());
		
		player.move('S');
		player.move('S');
		player.move('S');
		player.move('S');
		player.move('S');
		
		assertEquals(0, position.getXPos());
		assertEquals(4, position.getYPos());
		
		player.move('E');
		player.move('E');
		player.move('E');
		player.move('E');
		player.move('E');
		
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
		Game game = new Game(3, 3);
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
		Game game = buildEmptyMap(5, 5);
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
	}
	
	
	@Test
	public void playerCanDie () {
		Game game = buildEmptyMap(5, 5);
		GameEntity player = game.getPlayer();
		game.getRick().freeze();
		
		player.move('S');
		player.move('S');
		player.move('S');
		player.move('S');
		
		assertTrue(player.isAlive());
		
		player.move('E');
		player.move('E');
		player.move('E');
		player.move('E');
		
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
		
		player.move('E');
		
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
	public void playerHasArrows () {
		int numArrows = game.getPlayer().getNumArrows();

		assertEquals(5, numArrows);
	}
	
	@Test
	public void playerHasFewerArrowsAfterShooting () {
		int numArrows = game.getPlayer().getNumArrows();
		
		game.getPlayer().shoot('S');
		
		int numArrowsAfterShooting = game.getPlayer().getNumArrows();
		
		assertTrue(numArrows > numArrowsAfterShooting);
	}
	
	@Test
	public void playerDiesIfArrowBouncesBack () {
		Player player = game.getPlayer();
		
		assertTrue(player.isAlive());
		
		player.shoot('W');
		
		assertTrue(!player.isAlive());
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
		Game game = buildEmptyMap(5, 5);
		
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
		Game game = buildEmptyMap(5, 5);
		
		Player player = game.getPlayer();
		
		player.move('S');
		player.move('S');
		
		int numArrowsInLastRoomAfterShoot = game.getRoom(0, 0).getNumArrows();
		
		assertEquals(0, numArrowsInLastRoomAfterShoot);
		
		player.shoot('N');
		
		numArrowsInLastRoomAfterShoot = game.getRoom(0, 0).getNumArrows();
		
		assertTrue(numArrowsInLastRoomAfterShoot > 0);
	}
	
	@Test
	public void playerCanKillRick () {
		Game game = buildEmptyMap(5, 5);
		Player player = game.getPlayer();
		game.getRick().freeze();
		
		
		player.move('S');
		player.move('S');
		player.move('S');
		player.move('S');
		
		player.shoot('E');
		
		assertTrue(game.isOver());
		assertTrue(!game.getRick().isAlive());
		assertTrue(player.isAlive());
	}
	
	@Test
	public void playerCanPickUpArrows () {
		Game game = buildEmptyMap(5, 5);
		
		Player player = game.getPlayer();
		
		player.move('E');
		
		assertEquals(5, player.getNumArrows());
		
		player.shoot('W');
		
		assertEquals(4, player.getNumArrows());
		
		player.move('W');
		
		assertEquals(5, player.getNumArrows());
	}
	
	/*
	@Test
	public void rickMovesAfterPlayerShoot () {
		int numTimesRickMove = 0;
		game.setPlayer(new Player(game, 1000, 0, 0));
		Player player = game.getPlayer();
		BoardPosition currentRickPosition;
		BoardPosition lastRickPosition;
		
		for (int i = 0; i < 100; i++) {
			
			currentRickPosition = game.getRick().getCurrentPosition();
			
			player.shoot('S');
			
			lastRickPosition = game.getRick().getCurrentPosition();
			
			if (!lastRickPosition.equals(game.getRick().getCurrentPosition())) {
				numTimesRickMove++;
			}
			
			System.out.println(lastRickPosition + " " + game.getRick().getCurrentPosition());
		}
		
		System.out.println("Num times rick move: " + numTimesRickMove);
		
		assertTrue(numTimesRickMove > 75);
	}
	*/
	
	@Test
	public void playerCanSense () {
		Game game = buildMap();
		Player player = game.getPlayer();
		game.getRick().freeze();
		LinkedList<String> log = player.getActionLog();
		String lastLogItem;
		
		player.move('E');
		player.move('E');
		player.move('E');
		
		lastLogItem = log.getLast();
		
		assertEquals("You hear the flapping of wings nearby.", lastLogItem);
		
		player.move('S');
		player.move('S');
		
		lastLogItem = log.getLast();
		
		assertEquals("You feel a cool breeze nearby.", lastLogItem);
		
		player.move('E');
		player.move('S');
		
		lastLogItem = log.getLast();
		
		assertEquals("You hear something near by, \"....give.....up\"", lastLogItem);
	}
	
}
