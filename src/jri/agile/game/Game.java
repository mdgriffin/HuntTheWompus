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
		BoardPosition rickPos = rick.getCurrentPosition();
		BoardPosition playerPos = player.getCurrentPosition();
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				setRoom(row, col, new Room(row, col, Room.RoomType.Normal));
			}
		}
		
		while (numPits < 2) {
			int randX = (int)(Math.random() * width);
			int randY = (int)(Math.random() * height);
			
			if (playerPos.getXPos() != randX && playerPos.getYPos() != randY 
				&& rickPos.getXPos() != randX && rickPos.getYPos() != randY) {
				setRoom(randY, randX, new Room(randY, randX, Room.RoomType.PitRoom));
				numPits++;
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
			player.die("You were killed by Rick");
		} else if (isPlayerInRoomWithPit()) {
			player.die("You fell into the Pit");
		}
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
	
	public String toString () {
		return player.toString();
	}
	

}
