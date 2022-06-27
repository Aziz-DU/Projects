import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public abstract class MovingCircle {
protected double xpos;
protected double ypos;
protected double xVel;
protected double yVel;
protected double radius;
protected int xdir;
protected int ydir;
protected double speedyFactor=1;
private Color color;
protected boolean speedy=false;
public MovingCircle(double r) {
	radius=r;
	double max=0.01;
	double min= -0.01;
    xpos=0.7; 
    ypos=0.7;	
    xdir=1;
    ydir=1;
    xVel = 0.01;
    yVel = 0.01;

}
public abstract void move();
public void draw() {
	
	StdDraw.setPenColor(color);
	StdDraw.filledCircle(xpos, ypos, radius);
}
public void forward() {
	xpos=xpos+xVel*xdir;
	ypos=ypos+yVel*ydir;
}

public boolean bounce() {

	if((xpos >= 1)||(xpos <= 0))
    {
		xdir=xdir * -1;
		if(speedy) { //only if it was a speedy circle apply the vel increase
			xVel*=1.05 ;
		}
   return true;
    }
	else if((ypos >= 1)||(ypos <= 0)){
	ydir=ydir * -1;
	if(speedy) {
		yVel*=1.05 ;
	}
   return true;
	}
  return false;  
}
public void setColor(Color c) {
	this.color=c;
}
}

