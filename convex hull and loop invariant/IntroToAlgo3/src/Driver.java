///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 2370: Introduction to Algorithms and Data Structures
//Project info: Homework 3 - Convex Hull / loop invariant 
///////////////////////////////////////////////////

import java.util.ArrayList;
import java.awt.Point;
public class Driver {

	public static ArrayList<Point> convexHull = new ArrayList<Point>();

	private static void quickHullStart(ArrayList<Point> a) { //function is void because the arraylist is 
		double rightMost=a.get(0).x; 						//accessible publicly and dont need to return it
		double leftMost=a.get(0).x;
		Point rightMostPoint=a.get(0);
		Point lefttMostPoint=a.get(0);
		ArrayList<Point> upperPoints = new ArrayList<Point>();
		ArrayList<Point> lowerPoints = new ArrayList<Point>();

		for (Point p: a)
		{
			if(p.x>rightMost) {
				rightMost=p.x;
				rightMostPoint=p;
			}
			if(p.x<leftMost) {
				leftMost=p.x;
				lefttMostPoint=p;
			}			
		}
		for (Point p: a)
		{						
			if(leftTurn(lefttMostPoint,rightMostPoint,p)) {
				upperPoints.add(p);
			}
			else {
				lowerPoints.add(p);
			}	
		}		
		quickHull(lefttMostPoint,rightMostPoint,upperPoints);
		quickHull(rightMostPoint,lefttMostPoint,lowerPoints);
	}

	private static ArrayList<Point> quickHull(Point i,Point j,ArrayList<Point> a) {
		double farthest =0;
		int farthestIndex=0;
		ArrayList<Point> newSetRight = new ArrayList<Point>();
		ArrayList<Point> newSetLeft = new ArrayList<Point>();		
		for(int e=0; e<a.size();e++) {			
			double currentP=valueBasedOnLineDistance(i,j,a.get(e));
			if(currentP>farthest) {
				farthest=currentP;
				farthestIndex=e;
			}	
		}
		for (Point p: a)
		{							
			if(leftTurn(i,a.get(farthestIndex),p)) {
				newSetLeft.add(p);
			}
			else if(leftTurn(a.get(farthestIndex),j,p)) {
				newSetRight.add(p);
			}
		}

		if(!a.isEmpty()) {
			convexHull.add(a.get(farthestIndex));

		}
		if(!newSetLeft.isEmpty()) {
			quickHull(i,a.get(farthestIndex),newSetLeft);

		}
		if(!newSetRight.isEmpty()) {
			quickHull(a.get(farthestIndex),j,newSetRight);

		}
		return convexHull ;
	}
	private static double valueBasedOnLineDistance(Point a,Point b,Point i) {
		return (Math.abs((b.x - a.x)*(i.y - a.y) - (b.y - a.y)*(i.x - a.x)));
	}
	private static boolean leftTurn(Point a,Point b,Point i) {
		return ((b.x - a.x)*(i.y - a.y) - (b.y - a.y)*(i.x - a.x)) > 0;
	}
	private static ArrayList<Point> bruteForceConvexHull(ArrayList<Point> a) {
		ArrayList<Point> ch = new ArrayList<Point>();
		int leftCount=0;
		for (Point i: a)
		{					
			for (Point j: a)
			{		
				if (i==j) {continue;}
				leftCount=0;
				for (Point k: a)
				{				
					if (k==i || k==j) {continue;}
					if(leftTurn(i,j,k)) {
						leftCount++;
					}
				}
				if((leftCount == 0) || (leftCount == a.size())) {
					ch.add(i);
					ch.add(j);
				}
			}			
		}
		return ch;	
	}
	public static void main(String[] args) 
	{
		ArrayList<Point> points = new ArrayList<Point>();
		int canvasSize=600;
		long startTime,endTime;
		double timeTaken;
		
		//quickHull function)
		System.out.println("Timing brute force Convex hull: ");	
		for(int j = 1; j <= 5;j++) {
			System.out.println("Array size: "+ j*50);	
			startTime=System.currentTimeMillis();	
			for( int i = 0; i < 50*j; i++) {
				Double o =Math.random() * canvasSize;
				Double u =Math.random() * canvasSize ;
				points.add(new Point(o.intValue(), u.intValue()));
			}
			bruteForceConvexHull(points);
			endTime=System.currentTimeMillis();			
			timeTaken=endTime-startTime;				
			System.out.println("Time taken ="+timeTaken);
		}
		System.out.println();

		//quickHull function)
		System.out.println("Timing quickHull: ");	
		for(int j = 1; j <= 5;j++) {
			System.out.println("Array size: "+ j*50);	
			startTime=System.currentTimeMillis();	
			for( int i = 0; i < 50*j; i++) {
				Double o =Math.random() * canvasSize;
				Double u =Math.random() * canvasSize ;
				points.add(new Point(o.intValue(), u.intValue()));
			}
			quickHullStart(points);
			endTime=System.currentTimeMillis();			
			timeTaken=endTime-startTime;				
			System.out.println("Time taken ="+timeTaken);
		}	
	}
}
