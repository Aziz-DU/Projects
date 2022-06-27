import java.util.Random;

public class RecursionTester {
	
	// suppose you are given the following two recursive
	//   methods for finding the minimum of an array:
	// version 1:
	public static int min1(int[] array) {
		return min1(array, array.length);
	}

	public static int min1(int[] array, int n) {
		if(n <= 0) {
			// no minimum exists
			throw new IllegalArgumentException("array is empty");
		} else if(n == 1) {
			// base case: if there's a single element, that's it!
			return array[0];
		} else {
			// recursive case: it's either the last element, or the
			//                 minimum of the first n-1 elements
			if(min1(array, n-1) < array[n-1]) {
				return min1(array, n-1);
			} else {
				return array[n-1];
			}
		}
	}

	// version 2:
	public static int min2(int[] array) {
		return min2(array, array.length);
	}

	public static int min2(int[] array, int n) {
		if(n <= 0) {
			// no minimum exists
			throw new IllegalArgumentException("array is empty");
		} else if(n == 1) {
			// base case: if there's a single element, that's it!
			return array[0];
		} else {
			// recursive case: it's either the last element, or the
			//                 minimum of the first n-1 elements
			int tmp = min2(array, n-1);
			if(tmp < array[n-1]) {
				return tmp;
			} else {
				return array[n-1];
			}
		}
	}

	// main method
	public static void main(String[] args) {
		// Aziz Alkhelaiwi, Tony Pham, Serigne Kholle	
		// Based on the graphs we derived from excel, we decided that min1 is faster than min2 and is more accurate/consistant.
		// The difference between the two methods is that in min2, on line 44 there is a temp variable; 
		// Creating an extra variable will add 1 extra thing for the complier thus 
		// making the process slightly slower than min1 which does not have the temp variable.
		//
		long start;
		long end;
		double durationInM;

		// TODO: determine which of the previous two methods is faster! //We assume that min2 will take lnger
		// TODO: (1) create some test arrays (of various sizes)
		// TODO: (2) time how long it takes to find the minimum with each method
		// TODO: (3) display your results in a table, that can easily be graphed
		//Ascending
		System.out.println("Ascending");


		// insert the elements from 
		// the old array into the new array 
		// insert all elements till n 
		// then insert x at n+1 

		//Min1
		for (int i = 1; i < 34; i++) {
			int customArr[] = new int[i]; 	
			start = System.nanoTime();
			for (int j = 0; j < i; j++) {
				customArr[j]=j;  		
			} 
			min1(customArr);
			end = System.nanoTime();
			durationInM= (end-start)/1000000.0;
			System.out.println("Min1 Time with size array "+ i +" :"+ durationInM);


		}
		System.out.println("break");
		//Min 2
		for (int i = 1; i < 34; i++) {
			int customArr[] = new int[i]; 	
			start = System.nanoTime();
			for (int j = 0; j < i; j++) {
				customArr[j]=j;  		
			} 
			min2(customArr);
			end = System.nanoTime();
			durationInM= (end-start)/1000000.0;
			System.out.println("Min2 Time with size array "+ i +" :"+ durationInM);

		}
		//Descending

		System.out.println("Descending");
		// insert the elements from 
		// the old array into the new array 
		// insert all elements till n 
		// then insert x at n+1 
		//Min1
		for (int i = 33; i > 0; i--) {//for (int i = 1; i < 34; i++) {
			int customArr[] = new int[i]; 	
			start = System.nanoTime();
			for (int j = 0; j < i; j++) {//for (int j = 0; j < i; j++) {
				customArr[j]=j;  		
			} 
			min1(customArr);
			end = System.nanoTime();
			durationInM= (end-start)/1000000.0;
			System.out.println("Min1 Time with size array "+ i +" :"+ durationInM);
		}
		System.out.println("break");
		//Min 2
		for (int i = 33; i > 0; i--) {
			int customArr[] = new int[i]; 	
			start = System.nanoTime();
			for (int j = 0; j < i; j++) {
				customArr[j]=j;  		
			} 
			min2(customArr);
			end = System.nanoTime();
			durationInM= (end-start)/1000000.0;
			System.out.println("Min2 Time with size array "+ i +" :"+ durationInM);
		}

		//Random 

		System.out.println("Random");
		// insert the elements from 
		// the old array into the new array 
		// insert all elements till n 
		// then insert x at n+1 
		Random rand = new Random();
		int random_num;
		int randomArr[] = new int[33]; 
		for (int i = 0; i < 33; i++) {
			random_num = rand.nextInt(33) + 1;	
			randomArr[i]=random_num;
		}

		//Min1
		for (int i = 0; i < 33; i++) {
			int customArr[] = new int[randomArr[i]];
			start = System.nanoTime();
			for (int j = 0; j < randomArr[i]; j++) {
				customArr[j]=j;  		
			} 
			min1(customArr);
			end = System.nanoTime();
			durationInM= (end-start)/1000000.0;
			System.out.println("Min1 Time with size array "+ randomArr[i] + " "+ durationInM);
		}
		System.out.println("break");
		//Min 2
		for (int i = 0; i < 33; i++) {
			int customArr[] = new int[randomArr[i]];
			start = System.nanoTime();
			for (int j = 0; j < randomArr[i]; j++) {
				customArr[j]=j;  		
			} 
			min2(customArr);
			end = System.nanoTime();
			durationInM= (end-start)/1000000.0;
			System.out.println("Min2 Time with size array "+ randomArr[i] + " "+ durationInM);
		}
	}
}
