package jri.agile.game;

public class Game {
	
	private Room[][] gameBoard;
	private int width;
	private int height;
	private Player player;
	private GameEntity rick;
	
	public Game (int height, int width) {
		this.height = height;
		this.width = width;
		
		this.player = new Player(0, 0);
		this.rick = new Rick(height - 1, width - 1);
		
		generateBoard();
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
	
	public Player getPlayer () {
		return player;
	}
	
	public GameEntity getRick () {
		return rick;
	}
	
	public void movePlayer (char direction) {
		moveEntity (player, direction);
		
		if (isPlayerInRoomWithRick()) {
			player.die();
			player.actionLog.push("You were killed by Rick");
		} else if (isPlayerInRoomWithPit()) {
			player.die();
			player.actionLog.push("You fell into the Pit");
		} else if (isPlayerInRoomWithBats()) {
			movePlayerRandom();
			player.actionLog.push("You were moved to a random position by bats");
		} else {
			player.actionLog.push("You moved " + direction);
		}
	}
	
	public void movePlayerRandom () {
		int randX = (int)(Math.random() * width);
		int randY = (int)(Math.random() * height);
		
		player.getCurrentPosition().setXPos(randX);
		player.getCurrentPosition().setYPos(randY);
	}
	
	public void moveRick (char direction) {
		moveEntity (rick, direction);
	}
	
	public void moveRickRandom () {
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
		
		moveRick(direction);
		
	}
	
	private void moveEntity (GameEntity entity, char direction) {
		BoardPosition position = entity.getCurrentPosition();
		
		if (direction == 'E' && position.getXPos() < width - 1) {
			position.setXPos (position.getXPos() + 1);
		} else if (direction == 'S' && position.getYPos() < height - 1) {
			position.setYPos(position.getYPos() + 1);
		} else if (direction == 'W' && position.getXPos() > 0) {
			position.setXPos (position.getXPos() - 1);
		} else if (direction == 'N' && position.getYPos() > 0) {
			position.setYPos(position.getYPos() - 1);
		}
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
