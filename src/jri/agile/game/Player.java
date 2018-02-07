package jri.agile.game;

public class Player extends GameEntity {

	private String deathMethod;
	
	public Player (int yPos, int xPos) {
		super(yPos, xPos);
	}
	
	public void die (String deathMethod) {
		super.die();
		this.deathMethod = deathMethod;
	}
	
	public String toString () {
		if (isAlive()) {
			return "Player is at: " + getCurrentPosition().getYPos() + "," +  getCurrentPosition().getXPos();
		} else {
			return "Player died: " + deathMethod;
		}
	}
	
}
