package assignment5;
import java.util.Iterator;
import java.util.Random;

import java.util.Set;

public class PartialMaliciousHangman extends Hangman{
	Set<String> words;
	String word;
	boolean canCheat=true;
	public PartialMaliciousHangman(Set<String> dictionary, int length, int guess) {
		super(length,guess);
		this.guessesRemaining=guess;			//malicious class has explaination since it includes same functions but more in-depth
		this.state=new char[length]; 			
		for(int i=0; i<length;i++) {
			this.state[i]=BLANK;
		}
		words=dictionary;
	}
	@Override
	protected boolean makeNewGuess(char c) {
		boolean correctGuess=false;
		String charecter=String.valueOf(c); 

		if(!canCheat) {

			int index = word.indexOf(c);
			while (index >= 0) {		    
				this.state[index]=c;
				index = word.indexOf(c, index + 1);    
				correctGuess=true;
			}
		}
		else {
			boolean foundAWord=false;
			Iterator<String> itr = words.iterator();			
			while (itr.hasNext())
			{
				if (itr.next().contains(charecter)) {
					itr.remove();
					foundAWord=true;
				}			    			    
			}
			if(!foundAWord||guessesRemaining==1) {
				canCheat=false;
				Driver.word=getWord();
			}
		}				
		return correctGuess;
	}

	@Override
	public String getWord() {

		if(!canCheat) {
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
		return "";
	}

}
