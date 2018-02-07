package jri.agile.game;

public class Player extends GameEntity {

	public Player (int yPos, int xPos) {
		super(yPos, xPos);
	}
	
	public String toString () {
		if (actionLog.size() > 0) {
			return actionLog.pop();
		} else {
			return "";
		}
		
		/*
		if (isAlive()) {
			return "Player is at: " + getCurrentPosition().getYPos() + "," +  getCurrentPosition().getXPos();
		} else {
			return "Player died: " + deathMethod;
		}
		*/
	}
	
}
