package jri.agile.game;

import java.util.Scanner;

public class GameDriver {

	public static void main (String[] args) {
		
		Game game = new Game(5, 5);
		String userInput;
		char command = ' ';
		
		Scanner input = new Scanner(System.in);
		
		printWelcome();
		printHelp();

		while (command != 'q' && !game.isOver()) {
			
			System.out.print("\n> ");
			
			userInput = input.nextLine().toUpperCase();
			command = userInput.charAt(0);
			
			if (command == 'H') {
				printHelp();
			} else if (command == 'M') {
				String[] moveDirection = userInput.split("\\s+");
				
				if (moveDirection.length != 2) {
					System.out.println("Invalid command please enter again");
				} else {
					char direction = moveDirection[1].charAt(0);
					game.movePlayer(direction);
				}
				
				System.out.println(game.toString());
				
			} else if (command == 'S') {
				String[] shootDirection = userInput.split("\\s+");
				
				if (shootDirection.length != 2) {
					System.out.println("Invalid command please enter again");
				} else {
					char direction = shootDirection[1].charAt(0);
					game.getPlayer().shoot(direction);
				}
				
				System.out.println(game.toString());
			} else if (command == 'P') {
				System.out.println(game.printMap());
			} else if (command != 'Q') {
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
