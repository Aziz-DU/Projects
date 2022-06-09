///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 2370: Introduction to Algorithms and Data Structures
//Project info: Homework 4 - Maximum Subarray Sum with different Algorithms
///////////////////////////////////////////////////

import java.util.Random;

public class HW4 {

	public static int bruteForce(int[] arr) {
		int firstIndex = 0;
		int lastIndex = 0;
		int n = arr.length;
		int subMax = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum += arr[j];
				if (sum >= subMax) {
					subMax = sum;
					firstIndex = i;
					lastIndex = j;
				}
			}
		}
		System.out.println("The first index is: " +firstIndex);
		System.out.println("The last index is: " +lastIndex);
		return subMax;
	}

	public static int[] divideAndConquer(int arr[], int low, int high)
	{   
		int output[] = new int[3];
		if (low == high) 
		{
			output[0]= arr[low];
			output[1]= low;
			output[2]= high;
			return output;
		}
		int m = (low + high)/2;
		int subL[] = divideAndConquer(arr, low, m);
		int subM[] = divideAndConquer(arr, m+1, high);
		int subR[] = maxSubSum(arr, low, m, high);

		if(subL[0]>=subM[0] && subL[0]>=subR[0]) {
			return subL;
		}
		else if(subM[0]>=subL[0] && subM[0]>=subR[0]) {
			return subM;
		}
		else  {
			return subR;
		}
	}
	public static int[] maxSubSum(int arr[], int l, int m, int h)
	{
		int sum = 0;
		int leftSum = Integer.MIN_VALUE;
		int maxLeft=0;
		int maxRight=0;
		int output[] = new int[3];
		for (int i = m; i >= l; i--)
		{
			sum = sum + arr[i];
			if (sum > leftSum) {
				leftSum = sum;
				maxLeft=i;	
			}				
		}
		sum = 0;
		int rightSum = Integer.MIN_VALUE;

		for (int i = m + 1; i <= h; i++)
		{
			sum = sum + arr[i];
			if (sum > rightSum) {
				rightSum = sum;
				maxRight=i;
			}			
		}
		output[1]=maxLeft;
		output[2]=maxRight;
		output[0]=leftSum + rightSum;						
		return output;
	}


	public static int[] kadane(int[] array) {
		int result[] = new int[3];
		int start=0;
		int startIndex=0;
		int endIndex = 0;
		int currMax = 0;
		int maxSub = array[0];
		
		for (int i = 0; i < array.length; i++)
		{
			currMax = currMax + array[i];
			if (currMax < 0) {
				currMax = 0;
				start=i+1;
			}else if (currMax > maxSub) {
				endIndex=i;
				startIndex=start;
				maxSub=currMax;
			}
		}
		result[0] = maxSub  ;
		result[1] = startIndex;
		result[2] = endIndex;
		return result;
	}

	public static void main(String[] args) 
	{
		int n=20;
		Random rnd = new Random();
		int[] array=new int[n];
		System.out.println("The randomly selected array is: ");
		for(int i = 0; i < array.length;i++) {
			int rn = rnd.nextInt(100 - -100) + -100;
			array[i]=rn;
			System.out.print("("+array[i]+")");
		}

		System.out.println();
		System.out.println();
		System.out.println("Brute Force Algorithm: ");			

		int brute_force = bruteForce(array);
		System.out.println("The maximum subarray sum : "+brute_force);
		System.out.println();

		System.out.println("Divide and Conquer Algorithm :");
		int[] divideAndConquer = divideAndConquer(array, 0, n-1);
		System.out.println("The maximum subarray sum: "+divideAndConquer[0]);
		System.out.println("The first index is: "+divideAndConquer[1]);
		System.out.println("The last index is: "+divideAndConquer[2]);
		System.out.println();

		System.out.println("Kadane's Algorithm: ");
		int[] kadaneMax = kadane(array);
		System.out.println("The maximum subarray sum: "+kadaneMax[0]);
		System.out.println("The first index is: "+kadaneMax[1]);
		System.out.println("The last index is: "+kadaneMax[2]);
	}
}
