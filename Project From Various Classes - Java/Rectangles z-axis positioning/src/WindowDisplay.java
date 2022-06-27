import java.awt.Color;
import java.util.ArrayList;
import edu.princeton.cs.introcs.Draw;
import edu.princeton.cs.introcs.DrawListener;


public class WindowDisplay implements DrawListener{
	private Draw draw;
	private ArrayList<Window> WindowsArr=new ArrayList<>();
	boolean updateDraws=false;
	
	public WindowDisplay(int xScale,int yScale) {
		draw = new Draw();
		draw.setCanvasSize(xScale,xScale);
		draw.setXscale(0, xScale);
		draw.setYscale(0, yScale);
		draw.addListener(this);
	
	}
	public void addWindow(double x,double y,double h,double w, Color c) {
		WindowsArr.add(new Window(x, y, h, w, c));	
	}
	public void draw() {
		for(Window w: WindowsArr) {	//for each loop that draws all the windows
		w.drawSingleWindow();	
		}
	}

	public void reset() {
		draw.clear();	//clearing the screen then drawing the windows in their original position
		WindowsArr.set(0,new Window(50, 50, 60, 80, Draw.BLUE));
		WindowsArr.set(1,new Window(100, 130, 80, 80, Draw.RED));
		WindowsArr.set(2,new Window(80, 80, 60, 80, Draw.GREEN));
		WindowsArr.set(3,new Window(120, 60, 100, 80, Draw.BLACK));
		draw();
	}

	
	@Override
	public void mousePressed(double x, double y) {
		// TODO Auto-generated method stub
		
		for(int i=WindowsArr.size()-1; i>=0;i--) { //for loop that deletes the clicked window of the array and re-adds it to the end...
			if(WindowsArr.get(i).withinWindow(draw.mouseX(),draw.mouseY())) { //... making it go on top of other windows
				Window temp=WindowsArr.get(i);
				int tempPos=WindowsArr.indexOf(temp);
				WindowsArr.remove(tempPos);
				WindowsArr.add(temp);
				draw();
				break;
			}
		}
	}

	@Override
	public void mouseDragged(double x, double y) {		
			WindowsArr.get(WindowsArr.size() - 1).x=draw.mouseX();//when the window is dragged make it's x/y...
			WindowsArr.get(WindowsArr.size() - 1).y=draw.mouseY();//... position the mouse's position
			draw.clear();
			draw();
		
	}
	
	@Override
	public void mouseReleased(double x, double y) {
		// TODO Auto-generated method stub
		//clickedWindow=false;
	}
	
	@Override
	public void mouseClicked(double x, double y) {

	}
	
	@Override
	public void keyTyped(char c) {
		// TODO Auto-generated method stub
	if(c=='q') {	//quit program when q is clicked, call reset function when r is clicked
		System.exit(0);		
		}
	else if(c=='r') {
		reset();		
	}
	}
	
	@Override
	public void keyPressed(int keycode) {
		// TODO Auto-generated method stub
	
	}
	
	@Override
	public void keyReleased(int keycode) {
		// TODO Auto-generated method stub
			
		}
	public class Window{
		private double x,y,height,width;
		private Color color;

		public Window(double x,double y,double w,double h, Color c) {
			this.x=x;
			this.y=y;
			width=w;
			height=h;
			color=c;		
		}
		public void drawSingleWindow() {
			draw.setPenColor(color);
			draw.filledRectangle(x,y,width,height);
		}
		public boolean withinWindow(double mouseX,double mouseY) { //function that checks if you clicked on a window

			if(mouseX>this.x -this.width && mouseX < this.x +this.width 
			&& mouseY>this.y-this.height && mouseY < this.y +this.height ) {
				return true;
			}
		return false;
		}
	}
}
