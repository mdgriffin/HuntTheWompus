package jri.agile.game;

import java.util.LinkedList;

public class GameEntity {
	
	private BoardPosition position;
	private boolean isAlive;
	protected LinkedList<String> actionLog;
	protected Game game;
	
	public GameEntity (Game game, int yPos, int XPos) {
		isAlive = true;
		position = new BoardPosition(yPos, XPos);
		this.game = game;
		
		actionLog = new LinkedList<>();
	}
	
	public LinkedList<String> getActionLog () {
		return actionLog;
	}

	public BoardPosition getCurrentPosition () {
		return position;
	}
	
	public void moveRandom () {
		int randX = (int)(Math.random() * game.getWidth());
		int randY = (int)(Math.random() * game.getHeight());
		
		if (randX == getCurrentPosition().getXPos() && randY == getCurrentPosition().getXPos()) {
			moveRandom();
		}
		
		getCurrentPosition().setXPos(randX);
		getCurrentPosition().setYPos(randY);
	}
	
	public boolean move (char direction) {
		boolean moved = false;
		
		if (direction == 'E' && position.getXPos() < game.getWidth() - 1) {
			position.setXPos (position.getXPos() + 1);
			moved = true;
		} else if (direction == 'S' && position.getYPos() < game.getHeight() - 1) {
			position.setYPos(position.getYPos() + 1);
			moved = true;
		} else if (direction == 'W' && position.getXPos() > 0) {
			position.setXPos (position.getXPos() - 1);
			moved = true;
		} else if (direction == 'N' && position.getYPos() > 0) {
			position.setYPos(position.getYPos() - 1);
			moved = true;
		}
		
		return moved;
	}
	
	public boolean isAlive () {
		return isAlive;
	}
	
	public void die () {
		isAlive = false;
	}
	


}
