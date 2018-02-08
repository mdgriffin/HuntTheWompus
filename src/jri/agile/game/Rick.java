package jri.agile.game;

public class Rick extends GameEntity {
	private boolean frozen;
	
	
	public Rick (Game game, int yPos, int xPos) {
		super(game, yPos, xPos);
		frozen = false;
	}
	
	
	public void freeze () {
		frozen = true;
	}
	
	public void moveRick (char direction) {
		if (!isFrozen()) {
			move (direction);
		}
	}
	
	public void moveRandom () {
		if (!isFrozen()) {
			int random = (int)(Math.random() * 5);
			char direction;
			
			switch(random) {
				case 0:
					direction = 'N';
					break;
				case 1:
					direction = 'E';
					break;
				case 2:
					direction = 'S';
					break;
				case 3:
					direction = 'W';
					break;
				default:
					direction = 'R';
					break;
			}
			
			if (!move(direction)) {
				moveRandom();
			}
		}
		
	}
	
	public boolean isFrozen () {
		return frozen;
	}

}
