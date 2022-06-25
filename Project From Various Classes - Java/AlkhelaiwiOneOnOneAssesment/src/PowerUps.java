import edu.princeton.cs.introcs.StdDraw;

public class PowerUps extends Food{
	   boolean increaseOnce;
	   int increaseAmount;
	   public PowerUps() {
		  increaseOnce=true;
	   }
	   public PowerUps(double centerX, double centerY,double height_, double width_){
		   xCoord=centerX;
		   yCoord=centerY;
		   height=height_;
		   width=width_;
		   increaseOnce=true;
		   color=StdDraw.CYAN;

		   }  
	   public void ActivatePowerUp() {	//increase the velocity only once when picked
		   if(increaseOnce) {
			   Player.Velocity+=4;
			   increaseOnce=false;
		   }
	   }
}
   

