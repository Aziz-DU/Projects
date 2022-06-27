
import edu.princeton.cs.introcs.Draw;

public class WindowDriver {
	   public static void main(String[] args)
	   {	
		WindowDisplay wd = new WindowDisplay(600,600);		
		wd.addWindow(50, 50, 60, 80, Draw.BLUE);
		wd.addWindow(100, 130, 80, 80, Draw.RED);
		wd.addWindow(80, 80, 60, 80, Draw.GREEN);
		wd.addWindow(120, 60, 100, 80, Draw.BLACK);
		wd.draw();
		
		}	 
}
