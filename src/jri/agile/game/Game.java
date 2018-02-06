package jri.agile.game;

public class Game {
	
	private Room[][] gameBoard;
	
	
	public Game (int width, int height) {
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
	

}
