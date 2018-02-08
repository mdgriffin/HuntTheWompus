package jri.agile.fixtures;

import jri.agile.game.Game;

public class AreEntitiesInCorrectStartPosition {

	private int numColumns;
	private int numRows;
	private Game game;
	
	public void execute() {
		game = new Game(getNumRows(), getNumColumns());
	}
	
	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getRickXPos() {
		return game.getRick().getCurrentPosition().getXPos();
	}

	public int getRickYPos() {
		return game.getRick().getCurrentPosition().getYPos();
	}

	public int getPlayerXPos() {
		return game.getPlayer().getCurrentPosition().getXPos();
	}

	public int getPlayerYPos() {
		return game.getPlayer().getCurrentPosition().getYPos();
	}

}
