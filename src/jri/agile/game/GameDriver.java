package jri.agile.game;

import java.util.Scanner;

public class GameDriver {

	public static void main (String[] args) {
		
		Game game = new Game(5, 5);
		GameEntity player = game.getPlayer();
		String userInput;
		char command = ' ';
		
		Scanner input = new Scanner(System.in);
		
		printWelcome();
		printHelp();

		while (command != 'q' && player.isAlive()) {
			
			System.out.print("\n> ");
			
			userInput = input.nextLine();
			command = userInput.toLowerCase().charAt(0);
			
			if (command == 'h') {
				printHelp();
			} else if (command == 'm') {
				System.out.println("You want to move");
				String[] moveDirection = userInput.split("\\s+");
				
				if (moveDirection.length != 2) {
					System.out.println("Invalid command please enter again");
				} else {
					char direction = moveDirection[1].toLowerCase().charAt(0);
					game.movePlayer(direction);
				}
				
			} else if (command == 's') {
				System.out.println("You want to shoot");
			} else if (command != 'q') {
				System.out.print("\nInvalid Command, please enter again: ");
			}
			
		}
		
		System.out.println("\n\nGoodbye, thanks for playing");
		
		System.exit(0);
		
	}
	
	private static void printWelcome () {
		System.out.println(
			"Welcome to Hunt the Wombus"
		);
	}
	
	private static void printHelp () {
		System.out.println(
			"\nTo Move enter (M)ove followed by the direction (N, S, E, W)" +
			"\nTo Shoot enter (S)hoot followed by the direction (N, S, E, W)" +
			"\nTo see these options again, type (H)elp" +
			"\nTo quit the game enter (Q)uit"
		);
	}
}
