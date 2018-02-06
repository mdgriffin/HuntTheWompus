package jri.agile.game;

public class GameEntity {
	
	private BoardPosition position;
	
	public GameEntity () {
		position = new BoardPosition(0, 0);
	}

	public BoardPosition getCurrentPosition () {
		return position;
	}

}
