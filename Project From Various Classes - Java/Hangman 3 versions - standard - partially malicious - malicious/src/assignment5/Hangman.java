package assignment5;

import java.util.Set;
import java.util.HashSet;

public abstract class Hangman {
	// constants
	public static final char BLANK = '-';
	
	protected int guessesRemaining;
	protected int length;
	protected char[] state;
	protected Set<Character> guesses;
	
	public Hangman(int length, int guessCount) {
		this.guessesRemaining = guessCount;
		this.length = length;
		
		state = new char[length];
		for(int i = 0; i < state.length; i++) {
			state[i] = BLANK;
		}
		
		guesses = new HashSet<>();
	}
	
	public final boolean isGameWon() {
		return (getBlanksRemaining() <= 0);
	}
	
	public final boolean isGameLost() {
		return (getGuessesRemaining() <= 0);
	}
	
	public boolean isGameOver() {
		return isGameWon() || isGameLost();
	}
	
	public final int getGuessesRemaining() {
		return guessesRemaining;
	}
	
	public final int getBlanksRemaining() {
		int blankCount = 0;
		for(int i = 0; i < state.length; i++) {
			if(state[i] == BLANK) {
				blankCount++;
			}
		}
		return blankCount;
	}
	
	public final boolean makeGuess(char c) {
		c = Character.toLowerCase(c);
		if(!Character.isAlphabetic(c) || guesses.contains(c) || isGameOver()) {
			return false;
		}
		
		guesses.add(c);
		
		if(makeNewGuess(c)) {
			return true;
		} else {
			guessesRemaining--;
			return false;
		}
	}
	
	public String toString() {
		return new String(state);
	}
	
	protected abstract boolean makeNewGuess(char c);
	public abstract String getWord();
}
