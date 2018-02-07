package jri.agile.game;

public class GameEntity {
	
	private BoardPosition position;
	private boolean isAlive;
	
	public GameEntity (int yPos, int XPos) {
		isAlive = true;
		position = new BoardPosition(yPos, XPos);
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
