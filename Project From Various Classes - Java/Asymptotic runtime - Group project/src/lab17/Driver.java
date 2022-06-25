package lab17;
import java.util.TreeSet;
import java.io.*; 
import java.util.Hashtable;
import java.util.Random;
import java.util.LinkedList;

public class Driver {

	// Aziz Alkhelaiwi , Serigne kholle , Tony Pham

	public static void main(String[] args) {
		int capacity = 200000;
		int collision=0;
		long startTime=System.currentTimeMillis();


		// Tree set
		//timing   : 32.661
		//asymptotic runtime: O(N) 

		//						TreeSet<Integer> ts = new TreeSet<Integer>();
		//						for(int i =0; i < capacity; i++) {
		//							Random rnd = new Random();
		//							int rn = rnd.nextInt();
		//							ts.add(rn);
		//						}

		//LinkedList	
		//timing   : 181.705
		//asymptotic runtime: O(N^2) 

		//		LinkedList<Integer> ls = new LinkedList<Integer>();
		//		for(int i =0; i < capacity; i++) {
		//			Random rnd = new Random();
		//			int rn = rnd.nextInt();
		//			if(!ls.contains(rn)) {
		//				ls.add(rn);
		//			}
		//		}

		// Hash Tables
		//timing   :  0.077
		//collision : 44487 times
		//asymptotic runtime: O(N) 

//		TreeSet<Integer>[] hashTree = (TreeSet<Integer>[])new TreeSet[capacity];
//		for(int i =0; i < capacity; i++) {
//			hashTree[i] = new TreeSet<Integer>();
//		}
//
//		for(int i =0; i < capacity * 0.75; i++) {
//			Random rnd = new Random();
//			int rn = rnd.nextInt(capacity);
//			int hashIndex=rn%capacity;						
//			if(hashTree[hashIndex].isEmpty()) {
//				hashTree[hashIndex].add(rn);
//			}
//			else {
//				collision++;
//			}
//		}					

		//DoublyLinked         
		//timing   : 0.056
		//collision : 21150 times
		//asymptotic runtime: O(N) 

		//		LinkedList<Integer>[] linkedList = (LinkedList<Integer>[])new LinkedList[capacity];
		//		for(int i =0; i < capacity; i++) {
		//			linkedList[i] = new LinkedList<Integer>();
		//		}
		//		for(int i =0; i < capacity/2; i++) {
		//			Random rnd = new Random();
		//			int rn = rnd.nextInt(capacity-1); 
		//			int hashIndex=rn%capacity;	
		//			if(linkedList[hashIndex].contains(new Integer(rn))) {
		//				collision++;							
		//			}
		//			else {
		//				linkedList[hashIndex].add(rn);
		//			}
		//		}


		// LinearProbingArray 
		//timing : 0.211
		//collision :
		//asymptotic runtime: O(N^2) 

		Boolean[] LinearProbingArray = new Boolean[capacity];		
		for(int i =0; i < capacity; i++) {
			LinearProbingArray[i]=false;
		}
		for(int i =0; i < capacity/2; i++) {
			Random rnd = new Random();
			int rn = rnd.nextInt(capacity-1);

			if(LinearProbingArray[rn] == false) {
				LinearProbingArray[rn]=true;
			}
			else {
				int j = rn;
				while(LinearProbingArray[j] == true) {
					collision++;					
					j++;					
				}
				LinearProbingArray[j]=true;
			}
		}

		System.out.println("We got collision "+collision);
		long endTime=System.currentTimeMillis();
		double timeTaken=(endTime-startTime)/1000.0;
		System.out.println("It took: "+ timeTaken);
	}  
	//According to our lab results, LinkedList took the longest because it had to iterate 
	//through 200000 for it's contain method, where as an array of the linked list didn't 
	//have to iterate that much due to the size of the array.  linked list is the fastest
	//because of the usage of index accessing and it not having to iterate that much.
}
