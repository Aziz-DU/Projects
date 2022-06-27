import edu.princeton.cs.introcs.StdDraw;

public class Driver {
	public static void main(String[] args) {
		 int canvasSize=800;
		 boolean active=true;
		StdDraw.setCanvasSize(canvasSize, canvasSize);
		StdDraw.setXscale(0, 1);
		StdDraw.setYscale(0, 1);
	    StdDraw.enableDoubleBuffering();		   
	    Integer int1 = 7;     
	    Integer int2 = 9;    
	    int1 = int2.intValue(); 
	    System.out.print(int1);
	    MovingCircle[] normalCircle= new NormalCircle[2];
	    MovingCircle[] speedyCircle= new SpeedyCircle[2];
	    MovingCircle[] wobblyCircle= new WobblyCircle[2];
	    normalCircle[0]= new NormalCircle();
	    normalCircle[1]= new NormalCircle();
	    speedyCircle[0]= new SpeedyCircle();
	    speedyCircle[1]= new SpeedyCircle();
	    wobblyCircle[0]= new WobblyCircle();
	    wobblyCircle[1]= new WobblyCircle();

		while(active) {
			StdDraw.clear();
		    normalCircle[0].draw();
		    normalCircle[1].draw();
		    speedyCircle[0].draw();
		    speedyCircle[1].draw();
		    wobblyCircle[0].draw();
		    wobblyCircle[1].draw();
		    normalCircle[0].move();
		    normalCircle[1].move();
		    wobblyCircle[0].move();
		    wobblyCircle[1].move();
			speedyCircle[0].bounce();
			speedyCircle[1].bounce();

			StdDraw.show();
			StdDraw.pause(10);
		}
			

	}
}
