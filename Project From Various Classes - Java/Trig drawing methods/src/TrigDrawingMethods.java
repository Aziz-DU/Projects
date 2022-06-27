import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class TrigDrawingMethods {
	
	private static int ngonCounter=0;
	private static int spiralCounter=0;
	private static int timestableCounter=0;
	
	public static final Color NEON_YELLOW = new Color(255,255,130);
	public static final Color NEON_BLUE = new Color(77, 77, 255);
	public static final Color NEON_PINK = new Color(255,110,199);

	public static void filledRegularNgon(double centerX,double centerY,int radius,int angleCount){

		double angle = 2 * Math.PI / angleCount;
		double[] xVert = new double[angleCount];
	    double[] yVert = new double[angleCount];
	        
	    for (int i = 0; i < angleCount; i++) {
	            xVert[i] = centerX + radius * Math.cos(i * angle);
	            yVert[i] = centerY + radius * Math.sin(i * angle);
	        }
	      
	    StdDraw.filledPolygon(xVert, yVert);
	    ngonCounter++;
	}
	
	public static void spiral(double centerX,double centerY,double maxRadius,int numTurns,int numSegments){

	            double deltaR = maxRadius/numSegments;
	            double angle= Math.PI/2*numSegments;          
	            double x1=centerX;
	            double y1=centerY;
	            
	            for (int i = 0; i < numSegments; ++i) {
	                double x2 =  centerX + deltaR*Math.cos(angle);
	                double y2 = centerY + deltaR*Math.sin(angle); 
	                StdDraw.line(x1, y1, x2, y2);
	                deltaR += maxRadius/numSegments;
	                angle += 2 * Math.PI * numTurns/numSegments;
	                x1=x2;
		            y1=y2;
		}
                spiralCounter++;
	}
	public static void timesTable(double centerX,double centerY,int radius,int numPoints,int factor){

		 double[] xVert = new double[numPoints];
	     double[] yVert = new double[numPoints];
         double angle= Math.PI*2;

	     
	     for (int i = 0; i < numPoints; i++) {	
	         xVert[i] = centerX + radius * Math.cos(angle);
	         yVert[i] = centerY + radius * Math.sin(angle); 
	         angle += 2* Math.PI/numPoints;
	        }
	     
	     for(int i=0; i<numPoints;i++) {
	    	 int vertIndex= i * factor %  numPoints; 
	         StdDraw.filledCircle(xVert[i],yVert[i], 1);
             StdDraw.line(xVert[i],yVert[i],xVert[vertIndex],yVert[vertIndex]); 
	       }
	     timestableCounter++;
	}
	public static int getNgonCounter() {
		   return ngonCounter;
	   }
   public static int getSpiralCounter() {
	   return spiralCounter;
   }
   public static int getTimestableCounter() {
	   return timestableCounter;
   }
}
	


