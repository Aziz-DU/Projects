import edu.princeton.cs.introcs.StdDraw;

public class WobblyCircle extends MovingCircle {
	public WobblyCircle() {
		super(0.05);
		super.setColor(StdDraw.RED);
	}
	public void move() {
		super.forward();
		super.bounce();
		double max=0.005;
		double min= -0.005;
	    double wobble = Math.random() * (max - min) + min;

		xpos+=wobble;
		ypos+=wobble;

		}
}
