package jri.agile.fixtures;

import jri.agile.game.Game;
import jri.agile.game.RickVideoPlayerMock;

public class GameContext {
	
	public static Game game;
	
	public GameContext () {
		if (game == null) {
			game = new Game(5, 5, new RickVideoPlayerMock());
		}
	}

}
