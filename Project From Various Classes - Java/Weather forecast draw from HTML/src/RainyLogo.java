import edu.princeton.cs.introcs.StdDraw;

public class RainyLogo extends WeatherLogo{

    public RainyLogo(double x,double y, String temp,String _day, String weatherT) {
    	super(x,y,temp,_day,weatherT);
    }
    public void draw() {

		StdDraw.setPenColor(StdDraw.DARK_GRAY);
 	    StdDraw.filledCircle(xcord,ycord+50, 25);
 	    StdDraw.filledCircle(xcord+20,ycord+45, 20);
 	    StdDraw.filledCircle(xcord-20,ycord+45, 20);
 	    StdDraw.filledCircle(xcord-50,ycord, 25);
 	    StdDraw.filledCircle(xcord-30,ycord-5, 20);
 	    StdDraw.filledCircle(xcord-70,ycord-5, 20);	   
 	    StdDraw.filledCircle(xcord+50,ycord, 25);
 	    StdDraw.filledCircle(xcord+70,ycord-5, 20);
 	    StdDraw.filledCircle(xcord+30,ycord-5, 20);
		   
		StdDraw.setPenColor(StdDraw.CYAN);
	    StdDraw.line(xcord, ycord+30, xcord-10, ycord+10);
	    StdDraw.line(xcord-50, ycord-20, xcord-60, ycord-40);
	    StdDraw.line(xcord+50, ycord-20, xcord+40, ycord-40);
	
    }
}
