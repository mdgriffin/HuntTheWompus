package jri.agile.game;

public class Player extends GameEntity {
	
	private BoardPosition position;
	
	
	
	public Player () {
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
		} else if (direction == 'W') {
			position.setXPos (position.getXPos() - 1);
		} else if (direction == 'N') {
			position.setYPos(position.getYPos() - 1);
		}
		
	}
}
