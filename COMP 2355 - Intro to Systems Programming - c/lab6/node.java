
import java.io.IOException;
import java.io.*;
import java.net.*;

public class node{
	int start;
	int stop;
	static int totalCount;
	static boolean active=true;

	public static void main(String[] args) throws IOException 
	{
		try
		{
			InetAddress ip = InetAddress.getByName("localhost");
			Socket s = new Socket(ip, 8888);      
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());      
			while (active) 
			{
				int start = dis.readInt();
				int stop =  dis.readInt();                
				int count = primeByRange(start,stop);
				dos.writeInt(count);
				active=false;
			}
			dis.close();
			dos.close();
		}catch (IOException e) {

		} catch (ClassCastException e){
			e.printStackTrace();
		}
	}
	public int getCount() {
		return totalCount;
	}

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
}

