package jri.agile.game;

public class Rick extends GameEntity {
	private boolean frozen;
	
	
	public Rick (int yPos, int xPos) {
		super(yPos, xPos);
		frozen = false;
	}
	
	
	public void freeze () {
		frozen = true;
	}
	
	public boolean isFrozen () {
		return frozen;
	}

}
