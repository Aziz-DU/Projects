import java.util.HashSet;

public class Driver {
	// Aziz alkhelaiwi, Tony pham, serigne kholle

	public static void main(String[] args) {

		//size of square

		int n=900;

		//defining a hashset

		HashSet<Point2D> hS=new HashSet<Point2D>();

		//recording start time

		long startTime=System.currentTimeMillis();

		//inserting n*n points

		for(int i=0;i<=n;i++){

			for(int j=0;j<=n;j++){

				Point2D point2d=new Point2D(i, j);

				hS.add(point2d);

			}

		}

		//recording end time

		long endTime=System.currentTimeMillis();

		//finding and displaying time taken in seconds

		double timeTaken=(endTime-startTime)/1000.0;

		System.out.println("Time taken to insert "+n+"*"+n+" points: "+timeTaken+" seconds");

	}

}


