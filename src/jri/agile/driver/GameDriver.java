package jri.agile.driver;

import java.util.Scanner;

import jri.agile.game.Game;
import jri.agile.game.Player;

public class GameDriver {
	
	private static Scanner input;
	
	
	public static void main (String[] args) {
		String userInput = "Y";
		input = new Scanner(System.in);
		
		while (userInput.length() > 0 && userInput.toUpperCase().charAt(0) == 'Y') {
			playGame();
			
			System.out.print("\nEnter (Y)es to play another game: ");
			
			userInput = input.nextLine();
		}
		
		System.out.println("\n\nGoodbye, thanks for playing");
		
		System.exit(0);
		
	}
	
	private static void playGame () {
		Game game = new Game(5, 5);
		Player player = game.getPlayer();
		String userInput;
		char command = ' ';
		
		printWelcome();
		printHelp();

		while (command != 'Q' && !game.isOver()) {
			
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
					player.move(direction);
				}
				
				System.out.println(game.toString());
				
			} else if (command == 'S') {
				String[] shootDirection = userInput.split("\\s+");
				
				if (shootDirection.length != 2) {
					System.out.println("Invalid command please enter again");
				} else {
					char direction = shootDirection[1].charAt(0);
					player.shoot(direction);
				}
				
				System.out.println(game.toString());
			} else if (command == 'P') {
				System.out.println(game.printMap());
			} else if (command == 'R') {
				player.rest();
				System.out.println(game.toString());
			} else if (command != 'Q') {
				System.out.print("\nInvalid Command, please enter again: ");
			}
			
		}
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
