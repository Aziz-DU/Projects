import java.awt.Color;

import edu.princeton.cs.introcs.Draw;

public class PowerUps extends Food{
	   boolean pickOnlyOnce;
	   boolean useOnce;
	   //Color color;
	   int increaseAmount;

	   public PowerUps() {
		  pickOnlyOnce=true;
	   }
	   public PowerUps(double centerX, double centerY,double height_, double width_,Color c){
		   xCoord=centerX;
		   yCoord=centerY;
		   height=height_;
		   width=width_;
		   pickOnlyOnce=true;
		   color=c;

		   }  
	   public void increaseSpeed() {	//increase the velocity only once when picked
		   if(pickOnlyOnce) {
			   Player.Velocity+=4;
			   pickOnlyOnce=false;
		   }
	   }
	   public void killEnemyByClick() {	//kill enemies when picked
		   if(pickOnlyOnce) {
			   GameState.killEnemyByClicking=true;
			   pickOnlyOnce=false;
		   }
	   }
}
   

