import edu.princeton.cs.introcs.StdDraw;

public class SpeedyCircle extends NormalCircle{
	public SpeedyCircle() {
		super.setColor(StdDraw.GREEN);
	}
	public boolean bounce() {
    super.forward();
	super.bounce();
	speedy=true;

	return true;
	}
}
