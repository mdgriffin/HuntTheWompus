package jri.agile.game;

import java.util.ArrayList;
import java.util.Iterator;

public class Player extends GameEntity {
	
	private int numArrows;
	
	public Player (Game game, int yPos, int xPos) {
		this(5, game, yPos, xPos);
	}
	
	public Player (int numArrows, Game game,  int yPos, int xPos) {
		super(game, yPos, xPos);
		this.numArrows = 5;
	}
	
	public int getNumArrows () {
		return numArrows;
	}
	
	public void pickUpArrows (int numArrows) {
		this.numArrows += numArrows;
	}
	
	public boolean move (char direction) {
		boolean moved = super.move(direction);

		game.afterPlayerMove(moved);
		
		return moved;
	}

	public void shoot (char direction) {
		if (numArrows > 0) {
			numArrows--;
			BoardPosition position = getCurrentPosition();
			GameEntity rick = game.getRick();
			
			actionLog.addLast(GameText.arrowShoot);
			
			int numRoomsMoved = 0;
			
			if (direction == 'E') {
				for (int col = position.getXPos(); col < game.getWidth(); col++) {
					if (rick.getCurrentPosition().getYPos() == position.getYPos() && rick.getCurrentPosition().getXPos() == col) {
						actionLog.addLast(GameText.killedRick);
						game.getRick().die();
					} else if (col == game.getWidth() - 1) {
						game.getRoom(position.getYPos(), col).addArrow();
					}
					
					numRoomsMoved++;
				}
			} else if (direction == 'W') {
				for (int col = position.getXPos(); col >= 0; col--) {
					if (rick.getCurrentPosition().getYPos() == position.getYPos() && rick.getCurrentPosition().getXPos() == col) {
						actionLog.addLast(GameText.killedRick);
						game.getRick().die();
					}else if (col == 0) {
						game.getRoom(position.getYPos(), col).addArrow();
					}
					numRoomsMoved++;
				}
				
			} else if (direction == 'S') {
				for (int row = position.getYPos(); row < game.getHeight(); row++) {
					if (rick.getCurrentPosition().getXPos() == position.getXPos() && rick.getCurrentPosition().getYPos() == row) {
						actionLog.addLast(GameText.killedRick);
						game.getRick().die();
					}else if (row == game.getHeight() - 1) {
						game.getRoom(row, position.getXPos()).addArrow();
					}
					numRoomsMoved++;
				}
			} else if (direction == 'N') {
				for (int row = position.getYPos(); row >= 0; row--) {
					if (rick.getCurrentPosition().getXPos() == position.getXPos() && rick.getCurrentPosition().getYPos() == row) {
						actionLog.addLast(GameText.killedRick);
						game.getRick().die();
					}else if (row == 0) {
						game.getRoom(row, position.getXPos()).addArrow();
					}
					numRoomsMoved++;
				}
			}
			
			if (numRoomsMoved == 1) {
				die();
				actionLog.addLast(GameText.killedByArrow);
			}
		} else {
			actionLog.addLast(GameText.playerNoArrows);
		}
		
		game.afterPlayerShoot();
	}
	
	public void sense () {
		ArrayList<Room> adjacentRooms = new ArrayList<>();
		
		BoardPosition pos = getCurrentPosition();
		BoardPosition rickPos = game.getRick().getCurrentPosition();
		
		// Room to North
		if (pos.getYPos() > 0 ) {
			adjacentRooms.add(game.getRoom(pos.getYPos() - 1, pos.getXPos()));
		}
		
		// Room to south
		if (pos.getYPos() < game.getHeight() - 1) {
			adjacentRooms.add(game.getRoom(pos.getYPos() + 1, pos.getXPos()));
		}
		
		if (pos.getXPos() > 0) {
			adjacentRooms.add(game.getRoom(pos.getYPos(), pos.getXPos() - 1));
		}
		
		if (pos.getXPos() < game.getWidth() - 1) {
			adjacentRooms.add(game.getRoom(pos.getYPos(), pos.getXPos() + 1));
		}
		
		Iterator<Room> roomIterator = adjacentRooms.iterator();
		
		while (roomIterator.hasNext()) {
			Room room = roomIterator.next();
			
			if (room.hasBats()) {
				actionLog.addLast(GameText.senseBats);
			} else if (room.hasPit()) {
				actionLog.addLast(GameText.sensePit);
			} else if (rickPos.equals(room.getPosition())) {
				actionLog.addLast(GameText.rickText);
			}
		}
	}
	
	public void rest () {
		actionLog.addLast(GameText.playerResting);
		game.afterPlayerMove(false);
	}
	
	public String toString () {
		String res = "";
		
		while(actionLog.size() > 0) {
			res += "\n" + actionLog.removeFirst();
		}
		
		return res;
	}
}
