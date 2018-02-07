package jri.agile.game;

public class GameEntity {
	
	private BoardPosition position;
	private boolean isAlive;
	
	public GameEntity (int xPos, int yPos) {
		isAlive = true;
		position = new BoardPosition(xPos, yPos);
	}

	public BoardPosition getCurrentPosition () {
		return position;
	}
	
	public boolean isAlive () {
		return isAlive;
	}
	
	public void die () {
		isAlive = false;
	}

}
