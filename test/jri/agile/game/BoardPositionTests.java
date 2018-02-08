package jri.agile.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardPositionTests {

	@Test
	public void sameCoordsShouldBeEquals() {
		BoardPosition boardPosition = new BoardPosition(3, 3);
		BoardPosition otherPosition = new BoardPosition(3, 3);
		
		assertTrue(boardPosition.equals(otherPosition));
	}

	@Test
	public void differentCoordsShouldNotBeEquals() {
		BoardPosition boardPosition = new BoardPosition(2, 3);
		BoardPosition otherPosition = new BoardPosition(3, 3);
		
		assertTrue(!boardPosition.equals(otherPosition));
	}
	
}
