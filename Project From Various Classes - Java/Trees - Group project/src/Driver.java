
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
		//Aziz alkhelaiwi, Tony pham, Sergne kholle
		// Test 2 was faster than test 1, since in test 1 when you add in ascending order, every node will be 
		//on the right side and it will take longer and longer to go down each level
		//where test 2 with a random value it could go right or left making it more balanced.

		//test 3 was faster than test 4, similar to the differences between test 1 and 2, test 3 is arranged
		// alphabetically therefore when it is added to the binary tree all the nodes will be placed on the right subtree.
		//test 3 has a similar case to test 2

public class Driver {

	public static void main(String[] args) {
		long start,end;
		double durationInM;
		Tree<Integer> tree1 = new Tree<>();
		Tree<Integer> tree2 = new Tree<>();
		Tree<String> tree3 = new Tree<>();
		Tree<String> tree4 = new Tree<>();

		//test 1
		int n = 50000;
		start = System.nanoTime();
		for( int i =0; i < n ; i++)
		{
			tree1.add(i);
		}
		end = System.nanoTime();
		durationInM= (end-start)/1000000.0;
		System.out.println("this is test 1: " +durationInM);

		// test 2 	
		int k = 50000;
		Random rng = new Random() ;

		start = System.nanoTime();
		for(int i = 0; i < k; i++) {
			int temp = rng.nextInt(k);
			tree2.add(temp);
		}
		end = System.nanoTime();
		durationInM= (end-start)/1000000.0;
		System.out.println("this is test 2: " +durationInM);


		//test 3
		start = System.nanoTime();
		File file = new File("warAndPeace.txt");
		try {
			Scanner fileIn = new Scanner(file);
			while(fileIn.hasNext()) {
				String word = fileIn.next();
				String filteredWords = word.replaceAll("[\\W]", "");
				{       
					tree3.add(filteredWords);
				}
			}
			fileIn.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		end = System.nanoTime();
		durationInM= (end-start)/1000000.0;
		System.out.println("this is test 3: " +durationInM);



		//test 4
		start = System.nanoTime();
		File file2 = new File("wordlist.txt");
		try {
			Scanner fileIn2 = new Scanner(file2);			
			while(fileIn2.hasNext()) {
				String word = fileIn2.next();
				String filteredWords = word.replaceAll("[\\W]", "");
				{       
					tree4.add(filteredWords);
				}
			}
			fileIn2.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		end = System.nanoTime();
		durationInM= (end-start)/1000000.0;
		System.out.println("this is test 4: " +durationInM);
	}
}

