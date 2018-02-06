package jri.agile.game;

public class Game {
	
	private Room[][] gameBoard;
	private int width;
	private int height;
	private GameEntity player;
	
	public Game (int width, int height) {
		this.width = width;
		this.height = height;
		
		this.player = new Player();
		
		gameBoard = new Room[height][width];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				gameBoard[i][j] = new Room(i, j);
			}
		}
	}
	
	
	public Room[][] getRooms () {
		return gameBoard;
	}
	
	public Room getRoom (int x, int y) {
		return gameBoard[x][y];
	}
	
	public GameEntity getPlayer () {
		return player;
	}
	
	public void movePlayer (char direction) {
		BoardPosition position = player.getCurrentPosition();
		
		if (direction == 'E') {
			position.setXPos (position.getXPos() + 1);
		} else if (direction == 'S') {
			position.setYPos(position.getYPos() + 1);
		} else if (direction == 'W' && position.getXPos() > 0) {
			position.setXPos (position.getXPos() - 1);
		} else if (direction == 'N' && position.getYPos() > 0) {
			position.setYPos(position.getYPos() - 1);
		}
	}
	

}
