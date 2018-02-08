package jri.agile.game;

import java.util.LinkedList;

public class GameEntity {
	
	private BoardPosition position;
	private boolean isAlive;
	protected LinkedList<String> actionLog;
	
	public GameEntity (int yPos, int XPos) {
		isAlive = true;
		position = new BoardPosition(yPos, XPos);
		
		actionLog = new LinkedList<>();
	}
	
	public LinkedList<String> getActionLog () {
		return actionLog;
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
