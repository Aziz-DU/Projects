import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import edu.princeton.cs.introcs.StdDraw;
// click and drag to move the query circle, space bar adds new points, q exists the program
// up and down arrow keys change the radius.
public class Driver {
	public static void main(String[] args) 
	{
		SpatialTree t = new SpatialTree();
		double radius = 22.0;
		for (int i = 0; i < 100; i++)
		{
			t.add(new Point2D.Double(Math.random() * SpatialTree.canvasSize , Math.random() * SpatialTree.canvasSize ));
		}
		t.makeCanvas();
		StdDraw.enableDoubleBuffering();
		double startClickX = 0.0;
		double startClickY = 0.0;
		System.out.println("Use the UP and DOWN arrow keys to change the query circle radius");
		System.out.println("Press 'Space' bar to add random points");
		System.out.println("Press 'Q' to quit");
		while (true)
		{
			StdDraw.clear();	
			t.draw();
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.circle(StdDraw.mouseX(), StdDraw.mouseY(),radius);
			if (StdDraw.isMousePressed()) {				
				startClickX=StdDraw.mouseX();
				startClickY=StdDraw.mouseY();
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.circle(startClickX, startClickY,radius);
				Point2D center = new Point2D.Double(startClickX,startClickY);
				t.query(center, radius);
			}

			if(StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
				if(radius<400) {
					radius+=5;
				}
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
				if(radius>5) {
					radius-=5;
				}
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
				System.exit(0);
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
				t.add(new Point2D.Double(Math.random() * SpatialTree.canvasSize , Math.random() * SpatialTree.canvasSize ));
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
	}
}


