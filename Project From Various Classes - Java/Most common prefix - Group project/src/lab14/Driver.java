package lab14;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
				// Aziz alkhelaiwi , Tony pham, serigne kholle
public class Driver {
	public static void mostCommonPrefix(String filename, int length){
		Map<String, Integer> hm = new HashMap<String, Integer>();
		String prefix="";
		int mostUsedVal=0;
		String mostUsedPre="";

		try 
		{
			File file = new File(filename);     
			Scanner fileIn = new Scanner(file);

			while(fileIn.hasNext()) {
				boolean hasLength=false;
				String word = fileIn.next();
				
				if(word.length()> length-1) {
					prefix=word.substring(0,length);
					hasLength=true;
				}
				else {
					hasLength=false;
				}
				
				if(!hm.containsKey(prefix) && hasLength) {
					hm.put(prefix, 1);
				}
				else if(hm.containsKey(prefix) && hasLength){
					int temp = hm.get(prefix);
					temp+=1;
					hm.replace(prefix, temp);
				}
			}		 
			fileIn.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		for (Map.Entry<String, Integer> me : hm.entrySet()) { 
			int currentVal=me.getValue();
			if(currentVal>mostUsedVal) {
				mostUsedVal=currentVal;
				mostUsedPre=me.getKey();
			}
		} 
		System.out.println(mostUsedPre + " was used " + mostUsedVal + " times");	
	}

	public static void main(String[] args) {
		mostCommonPrefix("dictionary.txt",2);
		mostCommonPrefix("dictionary.txt",3);
		mostCommonPrefix("dictionary.txt",4);
		mostCommonPrefix("dictionary.txt",5);

	}
}



