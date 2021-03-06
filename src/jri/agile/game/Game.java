package jri.agile.game;

public class Game {
	
	private Room[][] gameBoard;
	private int width;
	private int height;
	private Player player;
	private Rick rick;
	private RickVideoPlayer videoPlayer;
	
	public Game (int height, int width, RickVideoPlayer videoPlayer) {
		this.height = height;
		this.width = width;
		this.videoPlayer = videoPlayer;
		
		this.player = new Player(this, 0, 0);
		this.rick = new Rick(this, height - 1, width - 1);
		
		generateBoard();
	}
	
	public boolean isOver () {
		return !player.isAlive() || !rick.isAlive();
	}
	
	private void generateBoard () {
		gameBoard = new Room[height][width];
		int roomNumLogic = (int) ((height*width)*.08);
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				setRoom(row, col, new Room(row, col, Room.RoomType.Normal));
			}
		}
		
		generateRandomRooms(roomNumLogic, Room.RoomType.BatRoom);
		generateRandomRooms(roomNumLogic, Room.RoomType.PitRoom);
	}
	
	private void generateRandomRooms (int numRoomsToGenerate, Room.RoomType roomType) {
		BoardPosition rickPos = rick.getCurrentPosition();
		BoardPosition playerPos = player.getCurrentPosition();
		int numRoomsGenerated = 0;
		
		while (numRoomsGenerated < numRoomsToGenerate) {
			int randX = (int)(Math.random() * width);
			int randY = (int)(Math.random() * height);
			BoardPosition randPos = new BoardPosition(randY, randX);
			
			if (!playerPos.equals(randPos) && !rickPos.equals(randPos) && !getRoom(randY, randX).hasPit() && !getRoom(randY, randX).hasBats()) {
				setRoom(randY, randX, new Room(randY, randX, roomType));
				numRoomsGenerated++;
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
			player.actionLog.addLast(GameText.killedByRick);
			
			videoPlayer.play();
			
		} else if (isPlayerInRoomWithPit()) {
			player.die();
			player.actionLog.addLast(GameText.killedByPit);
		} else if (isPlayerInRoomWithBats()) {
			player.moveRandom();
			player.actionLog.addLast(GameText.movedByBats);
			afterPlayerMove(true);
		} else {
			if (playerDidMove) {
				player.actionLog.addLast(GameText.movedToNewRoom);
			} else {
				player.actionLog.addLast(GameText.notMoved);
			}
			
			player.sense();
			
			if (currentRoom.getNumArrows() > 0) {
				player.actionLog.addLast(String.format(GameText.pickedUpArrows,  currentRoom.getNumArrows()));
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
		String res = "--------------------------------\n|";
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
			
			res += "|\n--------------------------------\n|";
		}
		
		return res.substring(0, res.length() - 1);
	}
	
	public String toString () {
		return player.toString();
	}
	
}
