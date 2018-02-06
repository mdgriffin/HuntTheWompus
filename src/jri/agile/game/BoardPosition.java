package jri.agile.game;

public class BoardPosition {
	private int xPos;
	private int yPos;
	
	public BoardPosition (int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getXPos () {
		return xPos;
	}
	
	
	public int getYPos() {
		return yPos;
	}
	
	public void setXPos (int xPos) {
		this.xPos = xPos;
	}
	
	public void setYPos (int yPos) {
		this.yPos = yPos;
	}
}
