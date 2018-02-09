package jri.agile.fixtures;

import jri.agile.game.Game;
import jri.agile.game.GameTestUtil;
import jri.agile.game.Player;
import jri.agile.game.RickVideoPlayerMock;

public class DoesRickAndPlayerMoveToCorrectRooms {

	private Game game;
	private int numRows;
	private int numCols;
	private int posX;
	private int posY;
	private String moveCommand;
	
	public void execute() {
		game = GameTestUtil.buildEmptyMap(5, 5);
		this.numRows = 5;
		this.numCols = 5;
		this.posX = 0;
		this.posY = 0;
	}
	
	public void setNumRows (int numRows) {
		this.numRows = numRows;
	}
	
	public void setNumColumns (int numCols) {
		this.numCols = numCols;
	}
	
	public void setPlayerPositionX (int posX) {
		this.posX = posX;
		game.getPlayer().getCurrentPosition().setXPos(posX);
	}
	
	public void setPlayerPositionY (int posY) {
		this.posY = posY;
		game.getPlayer().getCurrentPosition().setYPos(posY);
		
	}
	
	public void setMoveCommand (String moveCommand) {
		this.moveCommand = moveCommand;
	}
	
	public int playerExpectedPositionX () {
		char direction = moveCommand.charAt(0);
		game.getPlayer().move(direction);
		
		return game.getPlayer().getCurrentPosition().getXPos();
	}
	
	public int playerExpectedPositionY () {
		return game.getPlayer().getCurrentPosition().getYPos();
	}
}
