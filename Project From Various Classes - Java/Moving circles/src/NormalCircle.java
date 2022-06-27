import edu.princeton.cs.introcs.StdDraw;

public class NormalCircle extends MovingCircle{
	public NormalCircle() {
		super(0.05);
		super.setColor(StdDraw.BLUE);
	}
	public void move() {
		super.forward();
		super.bounce();
}

}
