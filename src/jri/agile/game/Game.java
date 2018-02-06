package jri.agile.game;

public class Game {
	
	private Room[][] gameBoard;
	private int width;
	private int height;
	private GameEntity player;
	private GameEntity rick;
	
	public Game (int width, int height) {
		this.width = width;
		this.height = height;
		
		this.player = new Player(0, 0);
		this.rick = new Rick(width - 1, height - 1);
		
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
	
	public GameEntity getRick () {
		return rick;
	}
	
	public void movePlayer (char direction) {
		moveEntity (player, direction);
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
	

}
