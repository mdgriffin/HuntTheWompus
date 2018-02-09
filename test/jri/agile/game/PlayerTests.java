package jri.agile.game;

import static org.junit.Assert.*;

import java.util.LinkedList;

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
	
	@Test
	public void playerCanMove () {
		Game game = GameTestUtil.buildEmptyMap(5, 5);
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
		Game game = GameTestUtil.buildEmptyMap(5, 5);
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
	public void playerCanDie () {
		Game game = GameTestUtil.buildEmptyMap(5, 5);
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
	public void playerCanDieInPit () {
		Game game = GameTestUtil.buildEmptyMap(5, 5);
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
	public void playerCanKillRick () {
		Game game = GameTestUtil.buildEmptyMap(5, 5);
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
		Game game = GameTestUtil.buildEmptyMap(5, 5);
		
		Player player = game.getPlayer();
		
		player.move('E');
		
		assertEquals(5, player.getNumArrows());
		
		player.shoot('W');
		
		assertEquals(4, player.getNumArrows());
		
		player.move('W');
		
		assertEquals(5, player.getNumArrows());
	}
	
	@Test
	public void playerCanSense () {
		Game game = GameTestUtil.buildMap();
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
