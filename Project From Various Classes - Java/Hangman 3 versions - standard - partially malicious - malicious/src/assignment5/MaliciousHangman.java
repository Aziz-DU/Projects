package assignment5;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MaliciousHangman extends Hangman{
	Set<String> words;
	String word;
	boolean canCheat=true;						//stops cheating and picks a word when cheating is not possible anymore
	int firstMostCountIndex;							//saving the index that included the guessed letter the most
	int secondMostCountIndex;				//saving the index that was included the most with the most index
	Set<String> updatedWords = new TreeSet<String>();	//a set to add words that have the guessed letter once
	Set<String> updatedWordsWithDuplicates = new TreeSet<String>();//a set to add words that have the guessed letter twice

	public MaliciousHangman(Set<String> dictionary, int length, int guess) {
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

		if(!canCheat) {				// play fairly when you can't cheat
			int index = word.indexOf(c);
			while (index >= 0) {		    //add all guessed letter if the guess was correct
				this.state[index]=c;
				index = word.indexOf(c, index + 1);    
				correctGuess=true;
			}
		}

		else {
			Iterator<String> itrDelGuessed = words.iterator();	//iterator used to remove words that have the guessed letter if...
			String guessedC=String.valueOf(c); 					//there are more than once with the letter				
			int withoutGuessedL=0;								//counting words without guessed					
			int mostCount=indexCounter(1,c);				//counting the index that included the guessed letter the most
			firstMostCountIndex=secondMostCountIndex;	//saving the first counted index before resetting it below
			int mostCountDuplicatesL=indexCounter(2,c);		//counting the index that was included the most with the most index
			boolean DuplicatesLetters=false;	//control whether words with one or two of guessed letter were found the most
			int finalMostCount=mostCount;		//compare for the DuplicatesLetters boolean above

			for(String w : words)
			{
				if (!w.contains(guessedC)) {
					withoutGuessedL+=1;
				}									
			}
			if(mostCount<mostCountDuplicatesL) {//see if one or two of guessed letters are in words
				DuplicatesLetters=true;
				finalMostCount=mostCountDuplicatesL;
			}

			if(finalMostCount<withoutGuessedL && words.size() > 5) { //if there are more words that don't include the letter
				while (itrDelGuessed.hasNext()) 						//delete the letter from words,not if only less than 5 words left
				{
					if (itrDelGuessed.next().contains(guessedC)) {
						itrDelGuessed.remove();
					}	
				}
			}
			else {

				if(!DuplicatesLetters) {//words with only 1 of the guessed letter
					words.clear();
					for(String w : updatedWords) {  // update the word set
						words.add(w);
					}
					this.state[firstMostCountIndex]=c;
				}
				else if(DuplicatesLetters) {
					words.clear();
					for(String w : updatedWordsWithDuplicates) {
						words.add(w);
					}
					this.state[firstMostCountIndex]=c;
					this.state[secondMostCountIndex]=c;	
				}

				correctGuess=true;
			}

		}
		if((guessesRemaining == 1 && word == null)||(words.size() < 5 //get word if in the end of game or only 5 words left
			&& words.size() >= 1) && word == null) {
			canCheat=false;
			Driver.word=getWord();
		}
		return correctGuess;
	}

	@Override
	public String getWord() {
		if(!canCheat) {		//only get the word when you cant cheat anymore
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

	public int indexCounter(int occurrence,char c) {
		int[] indexCounter = new int[length];
		int optimalIndexCount=0;
		int index=0;
		
		if(occurrence==1) {		//lettes that occured once in the word
			updatedWords.clear();
			for (String s : words) {
				int repeats=0;			
				for(int i=0; i<length;i++) {				//make sure not to have words with more occurance of the letter
					if(s.charAt(i)==c) {
						repeats+=1;
					}
				}
				if(repeats<2) {
					for(int i=0; i<length;i++) {			//count each index with letter occurance
						if(s.charAt(i)==c) {
							indexCounter[i]+=1;
						}
					}
				}
			}
			for(int i=0; i<length;i++) {			//get the index with the most occurance
				if(optimalIndexCount<indexCounter[i]) {
					optimalIndexCount=indexCounter[i];
					index=i;
				}
			}
			for (String s : words) {			//like above
				int repeats=0;
				for(int i=0; i<length;i++) {			
					if(s.charAt(i)==c) {
						repeats+=1;
					}
				}
				if(repeats<2) {					//add the words with the most index we got from above
					if(s.charAt(index)==c) {
						updatedWords.add(s);
					}
				}
			}
		}
		else {
			updatedWordsWithDuplicates.clear();
			for (String s : words) {
				int repeats=0;
				for(int i=0; i<length;i++) {			
					if(s.charAt(i)==c&&s.charAt(secondMostCountIndex)==c) {
						repeats+=1;
					}
				}
				if(repeats<3) {
					for(int i=0; i<length;i++) {			
						if(s.charAt(i)==c&&s.charAt(secondMostCountIndex)==c&&i!=secondMostCountIndex) {
							indexCounter[i]+=1;		//like above but this time get the other most index with the most
						}
					}
				}
			}		
			for(int i=0; i<length;i++) {
				if(optimalIndexCount<indexCounter[i]) {
					optimalIndexCount=indexCounter[i];
					index=i;
				}
			}
			for (String s : words) {
				int repeats=0;
				for(int i=0; i<length;i++) {			
					if(s.charAt(i)==c&&s.charAt(secondMostCountIndex)==c) {
						repeats+=1;
					}
				}
				if(repeats<3) {
					if(s.charAt(index)==c&&s.charAt(secondMostCountIndex)==c&&index!=secondMostCountIndex) {
						updatedWordsWithDuplicates.add(s);
					}
				}
			}													
		}
		secondMostCountIndex=index;			//index of most
		return optimalIndexCount;			//count of most

	}
}
