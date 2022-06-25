import edu.princeton.cs.introcs.StdDraw;

public class Enemy    
{
   protected double xCoord;		//protected because GameState access it to throw it out of the screen once it's been touched
   private double yCoord;
   private double xVelocity;
   private double yVelocity;
   public double height;
   public double width;
   private double xDirection;
   private double yDirection;   
   private boolean isInterSecting;

   public Enemy() {
	   xDirection=1;
	   yDirection=1;
   }
  public Enemy(double centerX, double centerY,double height_, double width_, 
  double xVel, double yVel){
   xCoord=centerX;
   yCoord=centerY;
   height=height_;
   width=width_;
   yVelocity=yVel;
   xVelocity=xVel;
   xDirection=1;
   yDirection=1;
   }  
  public void draw(){
   StdDraw.setPenColor(StdDraw.RED);
   StdDraw.filledRectangle(xCoord,yCoord,height,width);
   }
  public void vel(){
   xVelocity= xVelocity+1;
   yVelocity= yVelocity+1;
   }
  public void move(){      //move and bounce when you hit the edge of canvas
   xCoord=xCoord+xVelocity *xDirection;
   yCoord=yCoord+yVelocity *yDirection;

   if(xCoord >= GameState.CANVAS_SIZE){
     xDirection = xDirection * -1;
     }
   else if(xCoord <= 0){
    xDirection = xDirection * -1;
     }
   if(yCoord >= GameState.CANVAS_SIZE){
   yDirection = yDirection * -1;
     }
  else if(yCoord <= 0){
   yDirection = yDirection * -1;
     }
 }
 
  public boolean isIntersecting(Player p) {//same method of detection in food
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
   

