///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 2370: Introduction to Algorithms and Data Structures
//Project info: Homework 1 - Implementation of Algorithms and Measuring Execution Time 
///////////////////////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class HW1 {
	private static int naive_polynomial(int[] a,int x) {
		int n = a.length;
		double p;
		int pOfX=0;
		for(int i = 0; i < n;i++) {
			p=a[i] * (Math.pow(x,i));			
			pOfX+=p;
		}
		return pOfX;
	}
	private static int horner_polynomial(int[] a,int x) {
		int p=a[a.length-1];
		for(int i = a.length-2; i >= 0;i--) {
			p=a[i] + (x * p);	
		}	
		return p;
	}
	private static long consecutiveIntegerChecking(long m, long n) {
		long t = Math.min(m, n);
		for(long i = t; i > 0;i--) {
			if(t%i==0 && n%i==0) {
				return i;
			}						
		}
		return 1;
	}
	private static long euclidGCD(long m, long n) {
		if (m == 0) {
			return n; 
		}		
		return euclidGCD(n%m, m); 	    
	}

	private static long[] fibFileForGCD(String fileName) {
		long[] fibArr= new long[42];
		File file2 = new File(fileName);
		try {
			Scanner fileIn2 = new Scanner(file2);			
			for(int i = 0; i < 42;i++) {
				fibArr[i]= fileIn2.nextLong();
			}		
			fileIn2.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(0);
		}

		return fibArr;
	}
	private static int bruteForce(int x,int n) {
		int result=1;
		for(int i = 0; i < n;i++) {
			result*=x;	
		}
		return result;
	}
	private static int decreaseAndConquer(int x,int n) {
		int result=1;
		if(n% 2 == 0) {
			x=x*x;
			for(int i = 0; i < n/2;i++) {
				result*=x;	
			}
		}
		else {
			int originalX=x;
			x=x*x;
			for(int i = 0; i < (n-1)/2;i++) {
				result*=x;	
			}
			result*=originalX;
		}
		return result;
	}


	public static void main(String[] args) 
	{

		//Q1
				int[] polyArr1= new int[500];
				int[] polyArr2= new int[1000];
				int[] polyArr3= new int[1500];
				
				for(int i = 0; i < 500;i++) {
					Random rnd = new Random();
					int rn = rnd.nextInt(500 - -500) + -500;
					polyArr1[i]=rn;
		
				}
				for(int i = 0; i < 1000;i++) {
					Random rnd = new Random();
					int rn = rnd.nextInt(500 - -500) + -500;
					polyArr2[i]=rn;
		
				}
				for(int i = 0; i < 1500;i++) {
					Random rnd = new Random();
					int rn = rnd.nextInt(500 - -500) + -500;
					polyArr3[i]=rn;	
				}			
		
				long startTime=System.currentTimeMillis();			
		
				for(int i = 0; i < 1000;i++) {
					horner_polynomial(polyArr3,2);	
//					naive_polynomial(polyArr3,2);	
				}		
				long endTime=System.currentTimeMillis();
				double timeTaken=(endTime-startTime)/1000.0;
				System.out.println(timeTaken);



		//Q2
		//		long[] fibs = fibFileForGCD("fib.txt");	
		//		for(int i = 0; i < 42;i+=2) {
		//			long startTime=System.currentTimeMillis();	
		//			euclidGCD(fibs[i],fibs[i+1]);
		//			long endTime=System.currentTimeMillis();
		//			double timeTaken=endTime-startTime;
		//			System.out.println(timeTaken);
		//		}

		//Q3
		//		System.out.println(bruteForce(2,13));
		//		System.out.println(decreaseAndConquer(2,12));


	}
}
