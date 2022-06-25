
import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;

public class Food    
{
 
   protected double xCoord;	//protected because GameState access it to throw it out of the screen once it's been touched
   protected double yCoord;
   protected double height;
   protected double width;
   protected boolean isInterSecting;
   protected Color color;
   int numberOfFoods;

   public Food() {
		numberOfFoods=5;
	}
   public Food(double centerX, double centerY,double height_, double width_){
   xCoord=centerX;
   yCoord=centerY;
   height=height_;
   width=width_;
   color=StdDraw.GREEN;
   }  
   public void draw()
   {
   StdDraw.setPenColor(color);
   StdDraw.filledRectangle(xCoord,yCoord,height,width);
   }

   public boolean isIntersecting(Player p) {  //same method of detection in enemy
	   double rightEdgeRec1 =xCoord + width/2;
	   double leftEdgeRec1 =xCoord - width/2;
	   double topEdgeRec1 =yCoord + height/2;
	   double bottomEdgeRec1 =yCoord - height/2;

	   double rightEdgeRec2 =p.playerXPos + p.radius;	//taking the player variables and using them for the detection
	   double leftEdgeRec2 =p.playerXPos - p.radius;
	   double topEdgeRec2 =p.playerYPos + p.radius;
	   double bottomEdgeRec2 =p.playerYPos - p.radius;   
	     
	  if((rightEdgeRec1) < (leftEdgeRec2) || (rightEdgeRec2) < (leftEdgeRec1) || 
	  (topEdgeRec1) < (bottomEdgeRec2)|| (topEdgeRec2) < (bottomEdgeRec1)){
	     isInterSecting=false; 
	     }  
	  else {
	     isInterSecting=true;
	   } 
	   return isInterSecting;
	 } 
}
   

