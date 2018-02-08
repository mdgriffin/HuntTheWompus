package jri.agile.game;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Game {
	
	private Room[][] gameBoard;
	private int width;
	private int height;
	private Player player;
	private Rick rick;
	
	public Game (int height, int width) {
		this.height = height;
		this.width = width;
		
		this.player = new Player(this, 0, 0);
		this.rick = new Rick(this, height - 1, width - 1);
		
		generateBoard();
	}
	
	public boolean isOver () {
		return !player.isAlive() || !rick.isAlive();
	}
	
	private void generateBoard () {
		gameBoard = new Room[height][width];
		int numPits = 0;
		int numBats = 0;
		int roomNumLogic = (int) ((height*width)*.08);
		BoardPosition rickPos = rick.getCurrentPosition();
		BoardPosition playerPos = player.getCurrentPosition();
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				setRoom(row, col, new Room(row, col, Room.RoomType.Normal));
			}
		}
		
		while (numPits < roomNumLogic ) {
			int randX = (int)(Math.random() * width);
			int randY = (int)(Math.random() * height);
			
			if (playerPos.getXPos() != randX && playerPos.getYPos() != randY 
				&& rickPos.getXPos() != randX && rickPos.getYPos() != randY
				&& !getRoom(randY, randX).hasPit()) {
				setRoom(randY, randX, new Room(randY, randX, Room.RoomType.PitRoom));
				numPits++;
			}
		}
		
		while (numBats < roomNumLogic) {
			int randX = (int)(Math.random() * width);
			int randY = (int)(Math.random() * height);
			
			if (playerPos.getXPos() != randX && playerPos.getYPos() != randY 
				&& rickPos.getXPos() != randX && rickPos.getYPos() != randY
				&& !getRoom(randY, randX).hasPit()
				&& !getRoom(randY, randX).hasBats()) {
				setRoom(randY, randX, new Room(randY, randX, Room.RoomType.BatRoom));
				numBats++;
			}
		}
	}
	
	public Room[][] getRooms () {
		return gameBoard;
	}
	
	public Room getRoom (int row, int col) {
		return gameBoard[row][col];
	}
	
	public void setRoom (int row, int col, Room room) {
		if (row < height && col < width) {
			gameBoard[row][col] = room;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Player getPlayer () {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Rick getRick () {
		return rick;
	}
	
	public void afterPlayerMove (boolean playerDidMove) {
		BoardPosition playerPos = getPlayer().getCurrentPosition();
		Room currentRoom  = getRoom(playerPos.getYPos(), playerPos.getXPos());
		
		rick.moveRandom();
		
		if (isPlayerInRoomWithRick()) {
			player.die();
			player.actionLog.addLast("You were killed by Rick");
			
			// throws IOException, URISyntaxException
			/*
			try {
				Desktop d = Desktop.getDesktop();
				d.browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			} catch (Exception exc) {
				
			}
			*/
			
		} else if (isPlayerInRoomWithPit()) {
			player.die();
			player.actionLog.addLast("You fell into the Pit");
		} else if (isPlayerInRoomWithBats()) {
			player.moveRandom();
			player.actionLog.addLast("You were moved to a random position by bats");
			// TODO: Check that player actually moved
			afterPlayerMove(true);
		} else {
			if (playerDidMove) {
				player.actionLog.addLast("You moved to a new room");
			} else {
				player.actionLog.addLast("This direction is blocked");
			}
			
			
			player.sense();
			
			if (currentRoom.getNumArrows() > 0) {
				player.actionLog.addLast("You picked up " + currentRoom.getNumArrows() + " arrow(s)");
				player.pickUpArrows(currentRoom.getNumArrows());
				currentRoom.removeArrows();
			}
		}
	}
	
	public void afterPlayerShoot () {
		rick.moveRandom();
	}
	
	
	public boolean isPlayerInRoomWithRick () {
		return player.getCurrentPosition().equals(rick.getCurrentPosition());
	}
	
	public boolean isPlayerInRoomWithPit () {
		Room room = gameBoard[player.getCurrentPosition().getYPos()][player.getCurrentPosition().getXPos()];
		return room.hasPit();
	}
	
	public boolean isPlayerInRoomWithBats () {
		Room room = gameBoard[player.getCurrentPosition().getYPos()][player.getCurrentPosition().getXPos()];
		return room.hasBats();
	}
	
	public String printMap () {
		String res = "|";
		int playerX = player.getCurrentPosition().getXPos();
		int playerY = player.getCurrentPosition().getYPos();
		int rickX = rick.getCurrentPosition().getXPos();
		int rickY = rick.getCurrentPosition().getYPos();
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				String inner = "";
				
				inner += getRoom(row, col).hasBats()? "B" : "" ;
				inner += getRoom(row, col).hasPit()? "O" : "" ;
				inner += row == playerY && col == playerX ? "P" : "";
				inner += row == rickY && col == rickX ? "R" : "";
				
				res += String.format("|%-4s|", inner);
			}
			
			res += "|\n|";
		}
		
		return res.substring(0, res.length() - 1);
	}
	
	public String toString () {
		return player.toString();
	}
	
}
