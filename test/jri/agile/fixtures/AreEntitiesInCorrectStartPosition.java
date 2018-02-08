package jri.agile.fixtures;

import java.util.List;

import jri.agile.game.Game;

public class AreEntitiesInCorrectStartPosition {

	private int numColumns;
	private int numRows;
	private int rickXPos;
	private int rickYPos;
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

	public void setRickXPos(int rickXPos) {
		this.rickXPos = rickXPos;
	}

	public int getRickYPos() {
		return game.getRick().getCurrentPosition().getYPos();
	}

	public void setRickYPos(int rickYPos) {
		this.rickYPos = rickYPos;
	}

}
