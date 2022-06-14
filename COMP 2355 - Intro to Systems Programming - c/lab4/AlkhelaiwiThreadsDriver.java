public class AlkhelaiwiThreadsDriver {	
	public static void main(String[] args) 
	{
		long startTime=System.currentTimeMillis();		
		int numThreads = 10;
		Thread[] threads = new Thread[numThreads];
		int totalCount =0;
		int rangeStart=1000;    //1000->1000000
		int rangeEnd=1000000;
		int[] primesCount = new int[numThreads];
		 for(int i = 0; i < numThreads; i++) {
	            int start = (((rangeEnd-rangeStart) / numThreads) * i)+rangeStart;
	            int stop = (((rangeEnd-rangeStart) / numThreads) * (i + 1))+rangeStart;	            
	            AlkhelaiwiThreads va = new AlkhelaiwiThreads(start, stop,i,primesCount);
	            Thread th = new Thread(va);
	            threads[i] = th;
	            th.start(); 
	        }
		 for(int i = 0; i < numThreads; i++) {
	            try {
	                threads[i].join(); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		  for(int i = 0; i < numThreads; i++) {
	            totalCount+=primesCount[i];
	        }
		 System.out.println("Total count: "+totalCount);
		long endTime=System.currentTimeMillis();
		double timeTaken=(endTime-startTime)/1000.0;
		System.out.println("Time it took: "+timeTaken);		
	}
}
