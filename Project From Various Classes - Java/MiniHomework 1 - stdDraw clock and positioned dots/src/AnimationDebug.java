
import edu.princeton.cs.introcs.StdDraw;

public class AnimationDebug {

	public static void main(String[] args) {
		double xPosition, yPosition; //changed int to double
		int width = 200;
		int height = 200; //removed a double ";"
	//	int count = 0;    commented out and deleted it from below because we're not using it
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);

	
		StdDraw.enableDoubleBuffering();
		for (int i = 0; i < 5000; i++) //removed ";"
		{
			xPosition =  Math.random() * width; //removed (int)
			yPosition =  Math.random() * height;

				//rearranged the if statement to two sections and added two possibilities for each section
				// (x>y) -> (x>width/2) & (x<width/2)... vice versa
			if (xPosition > yPosition) {
				if(xPosition < width / 2) {
					StdDraw.setPenColor(0, 255, 255); // lower left triangle - cyan
	
				}
				else if(xPosition >= width / 2) {
					StdDraw.setPenColor(255, 0, 255); // upper left trapezoid - yellow

				}
			}
			if (xPosition < yPosition) {
				if(yPosition < height / 2) {
					StdDraw.setPenColor(255, 255, 0); // lower right trapezoid - magenta
	
				}
				else if(yPosition > height / 2) {
					StdDraw.setPenColor(0, 100, 100); } // upper right triangle - dark green

				}



			StdDraw.filledCircle(xPosition, yPosition, 5);

			StdDraw.show();;
			StdDraw.pause(5);
		}

	}
}



/*

import edu.princeton.cs.introcs.StdDraw;

public class AnimationDebug {

	public static void main(String[] args) {
		double xPosition, yPosition; //changed int to double
		int width = 200;
		int height = 200;;
		int count = 0;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);

	
		StdDraw.enableDoubleBuffering();
		for (int i = 0; i < 5000; i++) //removed ;
		{
			xPosition =  Math.random() * width; //removed (int)
			yPosition =  Math.random() * height;


			if (xPosition > yPosition && xPosition < width / 2) {
				StdDraw.setPenColor(0, 255, 255); // lower left triangle - cyan
			}  else if (xPosition < yPosition && yPosition > width / 2) {
				StdDraw.setPenColor(255, 0, 255); // upper left trapezoid - yellow
				 if (xPosition > yPosition && yPosition < width / 2) {
				StdDraw.setPenColor(255, 255, 0); // lower right trapezoid - magenta
			}  else if (xPosition < yPosition && xPosition > width / 2) {
				StdDraw.setPenColor(0, 100, 100); } // upper right triangle - dark green
			}
			StdDraw.filledCircle(xPosition, yPosition, 5);

			StdDraw.show();;
			StdDraw.pause(5);
			count++;
		}

	}

}
*/