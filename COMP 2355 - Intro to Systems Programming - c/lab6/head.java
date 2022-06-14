import java.io.*;
import java.net.*;
import java.io.IOException;
public class head 
{
	public static void main(String[] args) throws IOException 
	{
		ServerSocket ss = new ServerSocket(8888);
		int threadN=0;
		int threadsTotal = 10;
		int totalCount =0;
		int rangeStart=1000;    //1000->1000000
		int rangeEnd=1000000;
		int[] primesCount = new int[threadsTotal];
		Thread[] threads = new Thread[threadsTotal];						
		while (true) 
		{
			Socket s = null;             
			try 
			{
				s = ss.accept();                  
				DataInputStream dis = new DataInputStream(s.getInputStream());
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());                 
				threads[threadN] = new ThreadsCaller(s, dis, dos,threadN,threadsTotal,rangeStart, rangeEnd,primesCount);               
				threads[threadN].start();                                               
				if(threadN>=threadsTotal-1) {                	
					for(int i = 0; i < threadsTotal; i++) {
						try {
							threads[i].join(); 
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					for(int i = 0; i < threadsTotal; i++) {
						totalCount+=primesCount[i];
					}
					System.out.println("Total count: "+totalCount);
					return;
				}
				threadN++;   
			}
			catch (Exception e){
				s.close();
				e.printStackTrace();
			}
		}
	}
}

class ThreadsCaller extends Thread 
{
	final DataInputStream dis;
	final DataOutputStream dos;
	final Socket s;
	int threadN; 
	int start;
	int stop;
	int[] threadPrimeCount;
	boolean active=true;
	public ThreadsCaller(Socket s, DataInputStream dis, DataOutputStream dos,int threadN,int threadsTotal,int rangeStart,int rangeEnd,int[] threadPrimeCount) 
	{
		this.threadN=threadN;
		this.start= (((rangeEnd-rangeStart) / threadsTotal) * threadN)+rangeStart;
		this.stop= (((rangeEnd-rangeStart) / threadsTotal) * (threadN + 1))+rangeStart;
		this.s = s;
		this.dis = dis;
		this.dos = dos;
		this.threadPrimeCount=threadPrimeCount;
	}

	@Override
	public void run() 
	{
		while (active) 
		{
			try {
				dos.writeInt(start);
				dos.writeInt(stop);
				threadPrimeCount[threadN] = dis.readInt();
				System.out.println("thread "+threadN+ " found "+  threadPrimeCount[threadN]);
				active=false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try
		{
			this.dis.close();
			this.dos.close();

		}catch(IOException e){
			e.printStackTrace();
		} 
	}

}
