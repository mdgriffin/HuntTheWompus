package jri.agile.game;

public class Room {

	private int row;
	private int col;
	
	
	public Room (int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	
	public int getRow () {
		return row;
	}
	
	public int getColumn () {
		return col;
	}
}
