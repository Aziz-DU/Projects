package assignment5;
import java.util.Random;
import java.util.Set;


public class StandardHangman extends Hangman{	//malicious class has explaination since it includes same functions but more in-depth
	Set<String> words;
	String word;
	
	public StandardHangman(Set<String> dictionary, int length, int guess) {
		super(length,guess);
		this.guessesRemaining=guess;
		this.state=new char[length]; 
		for(int i=0; i<length;i++) {
			this.state[i]=BLANK;
		}
		words=dictionary;
	}
	@Override
	protected boolean makeNewGuess(char c) {
		boolean correctGuess=false;
		int index = word.indexOf(c);
		while (index >= 0) {		    
		    this.state[index]=c;
		    index = word.indexOf(c, index + 1);    
		    correctGuess=true;
		}
		return correctGuess;
	}

	@Override
	public String getWord() {
		Random rnd = new Random();
		int rn = rnd.nextInt(words.size());	
		int i = 0;
		for(String w : words)
		{
			if (i == rn) {
				word=w;
				break;
			}
			i++;
		}
		return word;
	}

}
