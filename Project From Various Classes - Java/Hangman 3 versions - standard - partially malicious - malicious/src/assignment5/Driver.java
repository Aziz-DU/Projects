package assignment5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Driver {			
	static Scanner keyboard = new Scanner(System.in);
	static String word;			
	public static Set<String> loadWords(int length) { //loading the word
		Set<String> ts = new TreeSet<String>();
		try 
		{ 
			File file = new File("dictionary.txt");     
			Scanner fileIn = new Scanner(file);		
			while(fileIn.hasNext()) {
				String word = fileIn.next();
				if(word.length()==length) {
					ts.add(word);
				}
			}
			fileIn.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return ts;
	}
	private static void pickPhase() {		//function for picking phase, used for playing again too
		int wordLength,guesses;
		while(true) {
			try {
				System.out.println ("Enter word length: ");
				wordLength = keyboard.nextInt();
				while(wordLength<2||wordLength>18)
				{
					System.out.println("Word length should be between 2 - 18 ");
					wordLength=keyboard.nextInt();
				}
				System.out.println ("Enter number of guesses: ");
				guesses = keyboard.nextInt();					
				Set<String> words = loadWords(wordLength);
				gamePhase(words,wordLength,guesses);					
			}
			catch(InputMismatchException | NumberFormatException ex ) {
				keyboard.next();
				continue;
			}
		}
	}

	private static void gamePhase(Set<String> w, int l,int g) {	

		//StandardHangman game =new StandardHangman(w,l,g);	
		PartialMaliciousHangman game =new PartialMaliciousHangman(w,l,g);		
		//MaliciousHangman game =new MaliciousHangman(w,l,g);	

		word=game.getWord();
		System.out.println(Arrays.toString(game.state));
		while(!game.isGameOver()) {
			System.out.println ("You have "+game.guessesRemaining + " guesses left.");
			System.out.println ("Type in a letter: " );
			char c = keyboard.next().charAt(0);
			game.makeGuess(c);			
			System.out.println(Arrays.toString(game.state));			
		}
		if(game.isGameOver()) {
			if(game.isGameWon()) {
				System.out.println("You won!");
			}
			else if(game.isGameLost()) {
				System.out.println("You lost!");				
			}
			System.out.println("The word was: "+ word);
			System.out.println();
			System.out.println("Type 0 or Simply exit if you dont want to play again.");
			System.out.println("type anything else to play again.");
			int choice = keyboard.nextInt();			
			if(choice==0) {				
				System.exit(0);
			}
			pickPhase();
		}		
	}

	public static void main(String[] args){
		pickPhase();
	}
}