package jri.agile.game;

public class GameFixture {
	
	public static GameContext gc;
	
	public GameFixture () {
		if (gc == null) {
			gc = new GameContext();
		}
	}
	
	public int getPlayerPositionX () {
		GameEntity player = gc.game.getPlayer();
		
		return  player.getCurrentPosition().getXPos();
	}

}
