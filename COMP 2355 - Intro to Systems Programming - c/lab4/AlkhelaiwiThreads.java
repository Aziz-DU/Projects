public class AlkhelaiwiThreads implements Runnable{
    int start;
    int stop;
    int threadNum;
    int[] threadPrimeCount;
    int totalCount;
    public AlkhelaiwiThreads(int start, int stop, int threadNum,int[] threadPrimeCount) {
        this.start = start;
        this.stop = stop;
        this.threadNum = threadNum;
        this.threadPrimeCount=threadPrimeCount;
    }
	private boolean primeNumberFinder(int x) {
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
	private int primeByRange(int start,int end) {
		int[] arr= new int[end-start+1];
		int index=0;
		for(int i = start; i <= end;i++) {
			arr[index]=i;
			index++;
		}	
		for(int i=1;i<arr.length;i++) {
			if(primeNumberFinder(arr[i])) {
				totalCount++;
			}
		}
		return totalCount;
	}
	public int getCount() {
		return totalCount;
	}
	  public void run() {
	        threadPrimeCount[threadNum]= primeByRange(start,stop);
	    }
}
