
public class AlkhelaiwiNoThreads {
	private static boolean primeNumberFinder(int x) {
		boolean isPrime=true;
		int i=2;
		while(isPrime&&i<x) {
			if(x%i==0) {
				isPrime =false;
				i=x+1;
			}
			i++;
		}
		return isPrime;
	}
	private static int primeByRange(int start,int end) {
		int primeCount=0;
		int size =end-start+1;
		int[] arr= new int[size];
		int index=0;
		for(int i = start; i <= end;i++) {
			arr[index]=i;
			index++;
		}	
		for(int i=1;i<arr.length;i++) {
			if(primeNumberFinder(arr[i])) {
				primeCount++;
			}
		}
		return primeCount;
	}
	public static void main(String[] args) 
	{		
		long startTime=System.currentTimeMillis();		
		System.out.println("Total count: "+primeByRange(10,100));
		long endTime=System.currentTimeMillis();
		double timeTaken=(endTime-startTime)/1000.0;
		System.out.println("Time it took: "+timeTaken);		
	}
}
