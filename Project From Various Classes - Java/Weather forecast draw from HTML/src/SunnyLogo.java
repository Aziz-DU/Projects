import edu.princeton.cs.introcs.StdDraw;

public class SunnyLogo extends WeatherLogo{

    public SunnyLogo(double x,double y, String temp,String _day, String weatherT) {
    	super(x,y,temp,_day,weatherT);
    }
    public void draw() {
		StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(xcord,ycord, 50);
	    for (int i = 0; i < 12; i++) {
            double markers = Math.toRadians(i * 30);
            StdDraw.filledCircle(xcord+70 * Math.cos(markers),ycord+70 * Math.sin(markers), 2);
        }
    }
}
