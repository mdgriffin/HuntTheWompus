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
	

}
