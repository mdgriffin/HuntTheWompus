package jri.agile.driver;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jri.agile.game.Game;
import jri.agile.game.Player;

public class GameDriver extends JFrame {

	private static final long serialVersionUID = 1L;
	private static String userInput;

	public static void main (String[] args) {
		GameDriver gameDriver = new GameDriver();
		Container panel = gameDriver.getContentPane();
		userInput = "Y";
		
		gameDriver.setVisible(true);
		
		gameDriver.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JTextArea textArea = new JTextArea();
		
		textArea.setEditable(false);
		textArea.setPreferredSize(new Dimension(800, 725));
		textArea.setBackground(Color.RED);
		
		textArea.setFont(new Font("Courier", Font.BOLD, 24));
		
		System.setOut(new PrintStream(new OutputStream() {
	      @Override
	      public void write(int b) throws IOException {
	        textArea.append(String.valueOf((char) b));
	      }
	    }));
		
		panel.add(textArea);
		
		JTextField textInput = new JTextField();
		
		textInput.setBackground(Color.GREEN);
		
		textInput.setFont(new Font("Courier", Font.BOLD, 24));
		
		textInput.addActionListener(new AbstractAction()
			{
			    @Override
			    public void actionPerformed(ActionEvent e)
			    {
			        userInput = textInput.getText();
			        textInput.setText("");
			    }
			}
		);
		
		panel.add(textInput);
		
		gameDriver.setSize(800, 800);
		
		gameDriver.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/*
		while (userInput.length() > 0 && userInput.charAt(0) == 'Y') {
			playGame();
			
			System.out.print("\nEnter (Y)es to play another game: ");
		}
		*/
		
		System.out.println("\n\nGoodbye, thanks for playing");
	}
	
	private static void playGame (String input) {
		Game game = new Game(5, 5);
		Player player = game.getPlayer();
		char command = ' ';
		
		printWelcome();
		printHelp();

		while (command != 'Q' && !game.isOver()) {
			
			//userInput = input.getText();
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
