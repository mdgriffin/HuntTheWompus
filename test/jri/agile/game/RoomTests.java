package jri.agile.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoomTests {
	
	@Test
	public void canCreateRoom() {
		Room room = new Room(0, 0, Room.RoomType.Normal);
		
		assertEquals(0, room.getRow());
		assertEquals(0,room.getColumn());
		
		assertEquals(0, room.getNumArrows());
		
		assertTrue(!room.hasBats());
		assertTrue(!room.hasPit());
		
		room =  new Room(1, 1, Room.RoomType.BatRoom);
		
		assertEquals(1, room.getRow());
		assertEquals(1,room.getColumn());
		
		assertEquals(0, room.getNumArrows());
		
		assertTrue(room.hasBats());
		assertTrue(!room.hasPit());
		
		room =  new Room(2, 2, Room.RoomType.PitRoom);
		
		assertEquals(2, room.getRow());
		assertEquals(2,room.getColumn());
		
		assertEquals(0, room.getNumArrows());
		
		assertTrue(!room.hasBats());
		assertTrue(room.hasPit());
	}

}
