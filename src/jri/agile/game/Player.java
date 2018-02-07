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
	
	public void pickUpArrows (int numArrows) {
		this.numArrows += numArrows;
	}

	public void shoot (char direction) {
		if (numArrows > 0) {
			numArrows--;
			
			BoardPosition position = getCurrentPosition();
			
			// ToDo: Replace with loop
			/*
			if ((direction == 'E' && position.getXPos() == game.getWidth() - 1) ||
				(direction == 'S' && position.getYPos() == game.getHeight() - 1) ||
				(direction == 'W' && position.getXPos() == 0) ||
				(direction == 'N' && position.getYPos() == 0)) {
				die();
				actionLog.push("You took an arrow to the knee!");
			}
			*/
			
			// find the last room
			int numRoomsMoved = 0;
			
			if (direction == 'E') {
				for (int col = position.getXPos(); col < game.getWidth(); col++) {
					
					if (col == game.getWidth() - 1) {
						game.getRoom(position.getYPos(), col).addArrow();
					}
					
					numRoomsMoved++;
				}
			} else if (direction == 'W') {
				for (int col = position.getXPos(); col >= 0; col--) {
					
					if (col == 0) {
						game.getRoom(position.getYPos(), col).addArrow();
					}
					numRoomsMoved++;
				}
				
			} else if (direction == 'S') {
				for (int row = position.getYPos(); row < game.getHeight(); row++) {
					if (row == game.getHeight() - 1) {
						game.getRoom(row, position.getXPos()).addArrow();
					}
					numRoomsMoved++;
				}
			} else if (direction == 'N') {
				for (int row = position.getYPos(); row >= 0; row--) {
					if (row == 0) {
						game.getRoom(row, position.getXPos()).addArrow();
					}
					numRoomsMoved++;
				}
			}
			
			if (numRoomsMoved == 1) {
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
