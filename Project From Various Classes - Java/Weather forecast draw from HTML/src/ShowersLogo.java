import edu.princeton.cs.introcs.StdDraw;

public class ShowersLogo extends WeatherLogo{

    public ShowersLogo(double x,double y, String temp,String _day, String weatherT) {
    	super(x,y,temp,_day,weatherT);
    }
    public void draw() {
		StdDraw.setPenColor(StdDraw.CYAN);
	    StdDraw.line(xcord, ycord+30, xcord, ycord+10);
	    StdDraw.line(xcord-50, ycord-20, xcord-50, ycord-40);
	    StdDraw.line(xcord+50, ycord-20, xcord+50, ycord-40);	
    }
}
