package jri.agile.game;

public class GameDriver {

	public static void main (String[] args) {
		
		Game game = new Game(5, 5);
		GameEntity player = game.getPlayer();
		
		while (player.isAlive()) {
			
			System.out.println(
				"Welcome to Hunt the Wombus" +
				"\n\nTo Move enter (M)ove followed by the direction (N, S, E, W)" +
				"\nTo Shoot enter (S)hoot followed by the direction (N, S, E, W)" +
				"\nTo see these options again, type (H)elp"
			);
			
			player.die();
		}
		
		System.out.println("\n\nGoodbye, thanks for playing");
		
		System.exit(0);
		
	}
}
