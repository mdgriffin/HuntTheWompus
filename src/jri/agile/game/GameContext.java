package jri.agile.game;

public class GameContext {
	
	public static Game game;
	
	public GameContext () {
		if (game == null) {
			game = new Game(5, 5);
		}
	}

}
