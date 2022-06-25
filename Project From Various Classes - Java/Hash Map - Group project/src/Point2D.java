


import java.util.Random;

public class Point2D {

	private int x;

	private int y;

	//param. constructor

	public Point2D(int x, int y) {

		this.x = x;

		this.y = y;

	}

	//def constructor

	public Point2D() {

	}

	public int getX() {

		return x;

	}

	public void setX(int x) {

		this.x = x;

	}

	public int getY() {

		return y;

	}

	public void setY(int y) {

		this.y = y;

	}

	@Override

	public boolean equals(Object other) {

		//checking if other is an instance of Point2D

		if (other instanceof Point2D) {

			Point2D p = (Point2D) other; // safe type casting

			if (p.x == this.x && p.y == this.y) {

				//coordinates are same

				return true;

			}

		}

		return false;
	}

	@Override

	// return 1
			
//		public int hashCode()	// This is a bad hash function since it takes an extremely long time
//								//for example when we ran it for a hash set of 0,0 to 200,200 it took 16.47 seconds (with other we used 900,900 and the other hash
//								//code implementations ran faster compared to this)
//
//		{
//			return 1;
//		}

	// return random integer

		public int hashCode()  // 0.256 seconds
		{  
			
			Random rnd = new Random();
	        return rnd.nextInt();
	        
		}

	//return coordinates

//	public int hashCode()  // it took 8.766 seconds
//
//	{   
//		int k;
//		Random rnd = new Random();
//
//		k = rnd.nextInt(2);
//		if (k == 0) return this.x;
//		else return this.y;
//
//
//	}

	//	 sum

//		public int hashCode()   // it took 7.711 seconds
//		{
//			return this.x + this.y;
//		}

	// product


//	public int hashCode() {  	//	it took 0.38 seconds
//	//	System.out.println(this.x + " "+ this.y);
//		return this.x * this.y;
//
//	}
}


