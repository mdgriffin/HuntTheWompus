package jri.agile.game;

public class Player extends GameEntity {
	
	private int numArrows;
	private Game game;

	public Player (Game game, int yPos, int xPos) {
		super(yPos, xPos);
		this.game = game;
		this.numArrows = 5;
	}
	
	public int getNumArrows () {
		return numArrows;
	}

	public void shoot (char direction) {
		if (numArrows > 0) {
			numArrows--;
			
			BoardPosition position = getCurrentPosition();
			
			if ((direction == 'E' && position.getXPos() == game.getWidth() - 1) ||
				(direction == 'S' && position.getYPos() == game.getHeight() - 1) ||
				(direction == 'W' && position.getXPos() == 0) ||
				(direction == 'N' && position.getYPos() == 0)) {
				die();
				actionLog.push("You took an arrow to the knee!");
			}
		}
	}
	
	public String toString () {
		String res = "";
		
		while(actionLog.size() > 0) {
			res += "\n" + actionLog.pop();
		}
		
		return res;
	}
}
