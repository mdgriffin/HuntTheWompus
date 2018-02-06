package jri.agile.game;

public class GameEntity {
	
	private BoardPosition position;
	
	public GameEntity (int xPos, int yPos) {
		position = new BoardPosition(xPos, yPos);
	}

	public BoardPosition getCurrentPosition () {
		return position;
	}

}
