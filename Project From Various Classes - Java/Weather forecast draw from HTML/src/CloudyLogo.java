import edu.princeton.cs.introcs.StdDraw;

public class CloudyLogo extends WeatherLogo{

    public CloudyLogo(double x,double y, String temp,String _day, String weatherT) {
    	super(x,y,temp,_day,weatherT);
    }
    public void draw() {	
		StdDraw.setPenColor(StdDraw.GRAY);
 	    StdDraw.filledCircle(xcord,ycord+50, 25);
 	    StdDraw.filledCircle(xcord+20,ycord+45, 20);
 	    StdDraw.filledCircle(xcord-20,ycord+45, 20);   
 	    StdDraw.filledCircle(xcord-50,ycord, 25);
 	    StdDraw.filledCircle(xcord-30,ycord-5, 20);
 	    StdDraw.filledCircle(xcord-70,ycord-5, 20); 	   
 	    StdDraw.filledCircle(xcord+50,ycord, 25);
 	    StdDraw.filledCircle(xcord+70,ycord-5, 20);
 	    StdDraw.filledCircle(xcord+30,ycord-5, 20);
    }
}
