///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 2370: Introduction to Algorithms and Data Structures
//Project info: Homework 2 - insertion vs merge sort Algorithms 
///////////////////////////////////////////////////
import java.util.Random;

public class HW2 {
	private static void insertionSort(int[] a) {
		int key,i;
		for(int j = 2; j < a.length;j++) {
			key=a[j];
			i=j-1;
			while(i >= 0 && a[i] > key) {
				a[i+1] = a[i];
				i = i-1;
			}
			a[i+1]=key;
		}
	}
	private static void mergeSort(int[] a,int p, int r) {
		if(p==r) {
			return;
		}
		else {
			int q= (p+r)/2;
			mergeSort(a,p,q);
			mergeSort(a,q+1,r);
			merge(a,p,q,r);

		}
	}
	private static void merge(int[] a,int p, int q, int r) {
		int nOne = q - p +1;
		int nTwo = r - q;
		int[] lArr=new int[nOne+1];
		int[] rArr=new int[nTwo+1];

		for(int i = 0; i < nOne;i++) {
			lArr[i]=a[p+i];
		}
		for(int i = 0; i < nTwo;i++) {
			rArr[i]=a[q+1+i];
		}

		lArr[nOne]=Integer.MAX_VALUE;
		rArr[nTwo]=Integer.MAX_VALUE;

		int i=0;
		int j=0;

		for(int k = p; k <= r;k++) {
			if(lArr[i] <= rArr[j]) {
				a[k]=lArr[i];
				i+=1;
			}
			else {
				a[k]=rArr[j];
				j+=1;
			}
		}
	}
	
	// demonstrating that merge sort and insertion sort work	
//	public static void main(String[] args){
//		Random rnd = new Random();
//		int[] arr= new int[20];					
//
//		for(int i = 0; i < arr.length;i++) {
//			int rn = rnd.nextInt(50); 
//			arr[i]=rn;
//		}
//		System.out.println("Array before sorting:");
//		System.out.println(Arrays.toString(arr));
//		insertionSort(arr);
//		System.out.println("\nArray After using insertion sort:");
//		System.out.println(Arrays.toString(arr));
//		System.out.println();
//
//		for(int i = 0; i < arr.length;i++) {
//			int rn = rnd.nextInt(50); 
//			arr[i]=rn;
//		}
//		System.out.println("\n\n\nArray before sorting:");
//		System.out.println(Arrays.toString(arr));
//		mergeSort(arr ,0, arr.length - 1);
//		System.out.println("\nArray After using merge sort:");
//		System.out.println(Arrays.toString(arr));
//
//
//	}

		public static void main(String[] args) 
		{
			long startTime,endTime;
			double timeTaken;
			Random rnd = new Random();
			//B)
			System.out.println("Insetion sort of arrays of random numbers of increasing size: ");	
			for(int j = 2; j <= 16;j*=2) {
				startTime=System.currentTimeMillis();	
				int[] arr= new int[10000*j];						//To take an accurate averege, i changed the size to the size being tested and take the average
				for(int i = 0; i < arr.length;i++) {				//Did the for below sections aswell
					int rn = rnd.nextInt(arr.length + i); 
					arr[i]=rn;
				}
				//mergeSort(arr ,0, arr.length - 1);		 // uncomment  merge sort and comment insertion sort to show results for merge sort
				insertionSort(arr);
				endTime=System.currentTimeMillis();			
				timeTaken=endTime-startTime;				
				System.out.println(timeTaken);						
			}	
			//insertion Sort													  |Merge Sort
			//n = 20000 , time taken= 220.  t/n^2 = 0.00000055  	       |n = 20000 , time taken= 13.  t/n^2 = 0.0000000325 									
			//n = 40000 , time taken= 828.  t/n^2 = 0.0000005175           |n = 40000 , time taken= 20.  t/n^2 = 0.0000000125												
			//n = 80000 , time taken= 1049.  t/n^2 = 0.00000016390625 	   |n = 80000 , time taken= 29.  t/n^2 = 0.00000000453125 
			//n = 160000 , time taken= 4257.  t/n^2 = 0.0000001662890625   |n = 160000 , time taken= 55.  t/n^2 = 0.0000000021484375 
	
			System.out.println("Insetion sort of arrays of sorted values: ");	
	
	
			//C)
			for(int j = 2; j <= 16;j*=2) {
				startTime=System.currentTimeMillis();	
				int[] arr= new int[10000*j];
				for(int i = 0; i < arr.length;i++) {
					arr[i]=i;
				}
				//mergeSort(arr ,0, arr.length - 1);			 // uncomment  merge sort and comment insertion sort to show results for merge sort
				insertionSort(arr);
				endTime=System.currentTimeMillis();			
				timeTaken=endTime-startTime;				
				System.out.println(timeTaken);	
			}	
	
	
			//		//insertion Sort													  |Merge Sort
			//		//n = 20000 , time taken= 1.  t/n = 0.00005        |n = 20000 , time taken= 9.  t/n = 0.00045 									
			//		//n = 40000 , time taken= 1.  t/n = 0.000025	   |n = 40000 , time taken= 15.  t/n = 0.000375												
			//		//n = 80000 , time taken= 2.  t/n = 0.000025 	   |n = 80000 , time taken= 18.  t/n = 0.00045	
			//		//n = 160000 , time taken= 4.  t/n = 0.000025      |n = 160000 , time taken= 26.  t/n = 0.0001625
	
	
			System.out.println("Merge sort of arrays of randomly generated values: ");	
			//E)
	
			for(int j = 2; j <= 16;j*=2) {
				startTime=System.currentTimeMillis();	
				int[] arr= new int[10000*j];
				for(int i = 0; i < arr.length;i++) {
					int rn = rnd.nextInt();
					arr[i]=rn;
				}
				mergeSort(arr ,0, arr.length - 1);			
				//insertionSort(arr);
				endTime=System.currentTimeMillis();			
				timeTaken=endTime-startTime;
				System.out.println(timeTaken);						
			}		
			//insertion Sort													  |Merge Sort
			//n = 20000 , time taken= 275.  t/logn = 41.61793807 	   |n = 20000 , time taken= 12.  t/logn = 2.790029368								
			//n = 40000 , time taken= 686. t/logn = 149.0636805        |n = 40000 , time taken= 20. t/logn = 4.345879897											
			//n = 80000 , time taken= 2,705.  t/logn = 551.6929135 	   |n = 80000 , time taken= 29.  t/logn  =5.91463752
			//n = 160000 , time taken= 9,729 .  t/logn = 1868.903875   |n = 160000 , time taken= 55.  t/logn = 10.56854957
		}
}
