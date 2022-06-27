import edu.princeton.cs.introcs.StdDraw;

public class Driver {

	  public static final int CANVAS_SIZE = 800;

	   public static void main(String[] args)
	   {
		   StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
		   StdDraw.setXscale(0, CANVAS_SIZE);
		   StdDraw.setYscale(0, CANVAS_SIZE);
		   
		    StdDraw.enableDoubleBuffering();		   
		  
				StdDraw.clear();
				StdDraw.setPenColor(TrigDrawingMethods.NEON_YELLOW);
				TrigDrawingMethods.filledRegularNgon(400,400, 300,12);
				
				StdDraw.setPenColor(TrigDrawingMethods.NEON_BLUE);
				TrigDrawingMethods.spiral(500, 600, 100, 6, 100);		
				TrigDrawingMethods.spiral(250, 600, 90, 3, 100);
				
				StdDraw.setPenColor(TrigDrawingMethods.NEON_PINK);
				TrigDrawingMethods.filledRegularNgon(400,400, 50,3);				
				TrigDrawingMethods.timesTable(220, 220, 200, 500, 2);
				System.out.println("Number of calls to regularNgon: "+TrigDrawingMethods.getNgonCounter());
				System.out.println("Number of calls to spiral: "+TrigDrawingMethods.getSpiralCounter());
				System.out.println("Number of calls to timestable: "+TrigDrawingMethods.getTimestableCounter());

				StdDraw.show();
	       
	   }
}
