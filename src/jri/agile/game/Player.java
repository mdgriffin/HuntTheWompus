package jri.agile.game;

import java.util.ArrayList;

public class Player extends GameEntity {
	
	private int numArrows;
	private Game game;

	public Player (Game game, int yPos, int xPos) {
		this(game, 5, yPos, xPos);
	}
	
	public Player (Game game, int numArrows, int yPos, int xPos) {
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
			GameEntity rick = game.getRick();
			
			actionLog.push(">>-Whoosh--->");
			
			int numRoomsMoved = 0;
			
			if (direction == 'E') {
				for (int col = position.getXPos(); col < game.getWidth(); col++) {
					if (rick.getCurrentPosition().getYPos() == position.getYPos() && rick.getCurrentPosition().getXPos() == col) {
						actionLog.push("You killed Rick!");
						game.getRick().die();
					} else if (col == game.getWidth() - 1) {
						game.getRoom(position.getYPos(), col).addArrow();
					}
					
					numRoomsMoved++;
				}
			} else if (direction == 'W') {
				for (int col = position.getXPos(); col >= 0; col--) {
					if (rick.getCurrentPosition().getYPos() == position.getYPos() && rick.getCurrentPosition().getXPos() == col) {
						actionLog.push("You killed Rick!");
						game.getRick().die();
					}else if (col == 0) {
						game.getRoom(position.getYPos(), col).addArrow();
					}
					numRoomsMoved++;
				}
				
			} else if (direction == 'S') {
				for (int row = position.getYPos(); row < game.getHeight(); row++) {
					if (rick.getCurrentPosition().getXPos() == position.getXPos() && rick.getCurrentPosition().getYPos() == row) {
						actionLog.push("You killed Rick!");
						game.getRick().die();
					}else if (row == game.getHeight() - 1) {
						game.getRoom(row, position.getXPos()).addArrow();
					}
					numRoomsMoved++;
				}
			} else if (direction == 'N') {
				for (int row = position.getYPos(); row >= 0; row--) {
					if (rick.getCurrentPosition().getXPos() == position.getXPos() && rick.getCurrentPosition().getYPos() == row) {
						actionLog.push("You killed Rick!");
						game.getRick().die();
					}else if (row == 0) {
						game.getRoom(row, position.getXPos()).addArrow();
					}
					numRoomsMoved++;
				}
			}
			
			if (numRoomsMoved == 1) {
				die();
				actionLog.push("You took an arrow to the knee!");
			}
			
			
		} else {
			actionLog.push("Oh, no arrows left");
		}
		
		game.afterPlayerShoot();
	}
	
	public void sense () {
		
		// Check all adjacent rooms
		
		// if rick = ".......given.....up" || ".....left......down" || ".......run....." || "........ Hurt You" || "....say goodbye"
		// if bats = "hear flapping of wings"
		// if pit = "Feel a cool breeze nearby"
		
		ArrayList<Room> adjacentRooms = new ArrayList<>();
	}
	
	public String toString () {
		String res = "";
		
		while(actionLog.size() > 0) {
			res += "\n" + actionLog.removeLast();
		}
		
		return res;
	}
}
