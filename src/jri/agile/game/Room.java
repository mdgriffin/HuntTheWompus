package jri.agile.game;

public class Room {

	private int row;
	private int col;
	private boolean hasBats = false;
	private boolean hasPit = false;
	private int numArrows = 0;
	
	public enum RoomType {
		Normal, BatRoom, PitRoom
	}
	
	public Room (int row, int col, RoomType roomType) {
		this.row = row;
		this.col = col;
		
		switch (roomType) {
			case BatRoom:
				hasBats = true;
				break;
			case PitRoom:
				hasPit = true;
				break;
			default:
				break;
		}
	}
	
	
	public int getRow () {
		return row;
	}
	
	public int getColumn () {
		return col;
	}
	
	public int getNumArrows () {
		return numArrows;
	}
	
	public boolean hasBats () {
		return hasBats;
	}
	
	public boolean hasPit () {
		return hasPit;
	}
}
