import java.util.Arrays;
import java.util.Random;
public class Driver {
	public static void main(String[] args) {
        Random rand = new Random(); 		
	    long start,end;
	    double durationInM;

	    for (int j = 1; j < 17; j*=2) {
	    	start = System.nanoTime();
	        int Arr[] = new int[25000*j];
		      for (int i = 0; i < Arr.length; i++) {
		    	  int rand_int1 = rand.nextInt(1000000)-20;
		    	  Arr[i]=rand_int1;
		    }		      
		      Arrays.sort(Arr);
		     System.out.println("In array size "+Arr.length+" it got: "+Search.algorithm_1(Arr));
		    // System.out.println("In array size "+Arr.length+" it got: "+Search.Algorithm_2(Arr));
		    //   System.out.println("In array size "+Arr.length+" it got: "+Search.Algorithm_3(Arr)); 		      
		      end = System.nanoTime();
		      durationInM= (end-start)/1000000.0;
		      System.out.println("Time it took: "+ durationInM);		        	    	
	    }
	    
	    //While testing all 3 functions, i tried to take the average outcome by running it multiple times
	    //of course the time is dependent on whether the function finds the number and return the value
	    //without having to finish the loop, so my average which i used in excel found that in case
	    //it found a matching negative number(mostly in 200k&400k due to having higher chance of finding one)
	    //in this case algorithm 1 is the fastest followed by 2 and 3
	    //this happens because algorithm 1 gets in the loop fast and finishes it fast
	    //without having many variables that delay it like algorithm 2&3
	    // BUT!!! in the worst case scenario where the function actually has to loop over the full array
	    //(when there isn't a matching negative number)the results are vastly different. 
	    //in the worst case scenario algorithm 3 is fastest closely followed by algorithm 2
	    //but algorithm 1 takes way too long compared to 2 & 3.
	    //that's because algorithm 1 grows exponentially relative to the arraysize and it has
	    //to loop through the whole array while algorithm 2&3 are structured in a practical way 
	    //that makes it not have to loop through the whole array to find the result.
	    //constants aside the big o notation for each algorithm is :-
	    // algorithm 1 is (n)^2 and both algorithm 2 & 3 are O(log n) 

	}
}
