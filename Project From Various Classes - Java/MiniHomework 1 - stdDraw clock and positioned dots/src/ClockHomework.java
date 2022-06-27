
import edu.princeton.cs.introcs.StdDraw;
import java.time.LocalTime;

public class ClockHomework {
	   
	   public static final int CANVAS_SIZE = 800;

	   public static void main(String[] args)
	   {
		   StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
		   StdDraw.setXscale(0, CANVAS_SIZE);
		   StdDraw.setYscale(0, CANVAS_SIZE);
		   
	
	
			boolean active=true;
			
		    StdDraw.enableDoubleBuffering();		   
			StdDraw.pause(50);
	
			   while(active)
		        {
					StdDraw.clear();
				   LocalTime now = LocalTime.now();

				
					double hours=now.getHour();
					double minutes=now.getMinute();
					double seconds=now.getSecond();

				    StdDraw.setPenColor(StdDraw.BLACK);
		            StdDraw.filledCircle(CANVAS_SIZE/2,CANVAS_SIZE/2, 200);
		            
		            //clock markers
		            StdDraw.setPenColor(StdDraw.RED);
		            for (int i = 0; i < 12; i++) {
		                double markers = Math.toRadians(i * 30);
		                StdDraw.filledCircle(CANVAS_SIZE/2 + 170 * Math.cos(markers),CANVAS_SIZE/2+ 170 * Math.sin(markers), 5);
		            }
		            
					StdDraw.setPenColor(StdDraw.BLUE);
			        //seconds line
					StdDraw.setPenRadius(0.004);
					double secondsAngle = Math.toRadians(6 * seconds);
		            double r1 = 170;
		            StdDraw.line(CANVAS_SIZE/2, CANVAS_SIZE/2, CANVAS_SIZE/2 + r1 * Math.sin(secondsAngle), CANVAS_SIZE/2 + r1 * Math.cos(secondsAngle));
		            
					//minutes line
				    StdDraw.setPenRadius(0.007);
					double minutesAngle = Math.toRadians(6 * minutes);
		            double r2 = 160;
		            StdDraw.line(CANVAS_SIZE/2, CANVAS_SIZE/2, CANVAS_SIZE/2 + r2 * Math.sin(minutesAngle), CANVAS_SIZE/2 + r2 * Math.cos(minutesAngle));
		            
		            //hours line
					StdDraw.setPenRadius(0.01);
					double hoursAngle = Math.toRadians(30 * hours);
					double r3 = 140;
			        StdDraw.line(CANVAS_SIZE/2, CANVAS_SIZE/2, CANVAS_SIZE/2 + r3 * Math.sin(hoursAngle), CANVAS_SIZE/2 + r3 * Math.cos(hoursAngle));
			        

					StdDraw.show();
		        }
	   }
}
