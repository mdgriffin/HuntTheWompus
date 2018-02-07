package jri.agile.game;

public class Player extends GameEntity {

	public Player (int yPos, int xPos) {
		super(yPos, xPos);
	}
	
	public String toString () {
		String res = "";
		
		while(actionLog.size() > 0) {
			res += "\n" + actionLog.pop();
		}
		
		return res;
		
		/*
		if (isAlive()) {
			return "Player is at: " + getCurrentPosition().getYPos() + "," +  getCurrentPosition().getXPos();
		} else {
			return "Player died: " + deathMethod;
		}
		*/
	}
	
}
