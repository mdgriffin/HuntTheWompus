package jri.agile.game;

import java.util.ArrayList;
import java.util.Iterator;

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
		
		BoardPosition pos = getCurrentPosition();
		BoardPosition rickPos = game.getRick().getCurrentPosition();
		
		if (pos.getYPos() > 0) {
			adjacentRooms.add(game.getRoom(pos.getYPos() - 1, pos.getXPos()));
		}
		
		if (pos.getYPos() < game.getHeight()) {
			adjacentRooms.add(game.getRoom(pos.getYPos() + 1, pos.getXPos()));
		}
		
		if (pos.getXPos() > 0) {
			adjacentRooms.add(game.getRoom(pos.getYPos(), pos.getXPos() - 1));
		}
		
		if (pos.getXPos() < game.getWidth()) {
			adjacentRooms.add(game.getRoom(pos.getYPos(), pos.getXPos() + 1));
		}
		
		Iterator<Room> roomIterator = adjacentRooms.iterator();
		
		while (roomIterator.hasNext()) {
			Room room = roomIterator.next();
			
			if (room.hasBats()) {
				actionLog.push("You hear the flapping of wings nearby.");
			} else if (room.hasPit()) {
				actionLog.push("You feel a cool breeze nearby.");
			} else if (rickPos.equals(room.getPosition())) {
				actionLog.push("You hear something near by, \"....give.....up\"");
			}
		}
	}
	
	public String toString () {
		String res = "";
		
		while(actionLog.size() > 0) {
			res += "\n" + actionLog.removeLast();
		}
		
		return res;
	}
}
