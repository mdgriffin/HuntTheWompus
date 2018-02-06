package jri.agile.game;

public class GameEntity {
	
	private BoardPosition position;
	
	public GameEntity () {
		position = new BoardPosition(0, 0);
	}

	public BoardPosition getCurrentPosition () {
		return position;
	}

	public void move (char direction) {
		
		if (direction == 'E') {
			position.setXPos (position.getXPos() + 1);
		} else if (direction == 'S') {
			position.setYPos(position.getYPos() + 1);
		} else if (direction == 'W' && position.getXPos() > 0) {
			position.setXPos (position.getXPos() - 1);
		} else if (direction == 'N' && position.getYPos() > 0) {
			position.setYPos(position.getYPos() - 1);
		}
		
	}
}
