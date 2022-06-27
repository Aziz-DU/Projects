import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;
import edu.princeton.cs.introcs.StdDraw;

public class SpatialTree {
	private SpatialTreeNode root;
	private int size;
	private ArrayList<Point2D> pointsArrayList = new ArrayList<>();
	static int canvasSize = 600;

	public SpatialTree() {
		this.root=null;
		this.size=0;
	}
	public boolean add(Point2D point2D) {	// function adds points to the tree
		SpatialTreeNode n = findNode(point2D,root);
		if(n==null) {	//create root if no points are there
			this.root = new SpatialTreeNode(point2D,null,true);
			this.size++;
			return true;
		}
		else if(n.equals(point2D)) { 	//return false if we have the point already
			return false;
		}
		else if(n.getIsNodeX()) {		//adding and comparing nodes based on their value and x/y node position
			if(point2D.getX() < n.getPoint2D().getX()) {
				n.setLeft(new SpatialTreeNode(point2D,n,false));
				this.size++;
			}
			if (point2D.getX() >= n.getPoint2D().getX())
			{
				n.setRight(new SpatialTreeNode(point2D,n,false));
				this.size++;	
			}
		}
		else if(!n.getIsNodeX()) {
			if(point2D.getY() < n.getPoint2D().getY()) {
				n.setLeft(new SpatialTreeNode(point2D,n,true));
				this.size++;
			}
			if (point2D.getY() >= n.getPoint2D().getY())
			{
				n.setRight(new SpatialTreeNode(point2D,n,true));
				this.size++;	
			}
		}
		return true;
	}
	private SpatialTreeNode findNode(Point2D point,SpatialTreeNode root ) {
		if(root==null) { 		//finding a node by recursively comparing x/y values
			return null;
		}
		if((root.getPoint2D().getX() == point.getX() && root.getPoint2D().getY() == point.getY() || root.isLeaf())) {
			return root;
		}
		SpatialTreeNode leftChild = root.getLeft();
		SpatialTreeNode rightChild = root.getRight();
		if(root.getIsNodeX()) {
			if(leftChild !=null) {
				if(point.getX() < root.getPoint2D().getX()) {
					return findNode(point,leftChild);
				}
			}
			if(rightChild !=null) {
				if(point.getX() >= root.getPoint2D().getX()) {
					return findNode(point,rightChild);
				}
			}
		}
		if(!root.getIsNodeX()) {
			if(leftChild !=null) {
				if(point.getY() < root.getPoint2D().getY()) {
					return findNode(point,leftChild);
				}
			}
			if(rightChild !=null) {
				if(point.getY() >= root.getPoint2D().getY()) {
					return findNode(point,rightChild);
				}
			}
		}
		return root;
	}

	public void draw() {   //using rectangle2d to use edges coordinates
		Rectangle2D area = new Rectangle2D.Double(0, 0, canvasSize, canvasSize);
		draw(root,area);
	}
	public void makeCanvas() {	//making canvas
		StdDraw.setXscale(0,canvasSize);
		StdDraw.setYscale(0,canvasSize);
	}
	private void draw(SpatialTreeNode n, Rectangle2D a) {
		StdDraw.setPenColor(StdDraw.BLACK); //recursively drawing the lines and points jumping between
		if(n == root) {						//x and y nodes
			double x = n.getPoint2D().getX();
			double y =  n.getPoint2D().getY();
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(x, y, canvasSize/200);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(x, 0, x, canvasSize);

			if(n.getLeft() != null) {
				Rectangle2D area = new Rectangle2D.Double(0,0, x ,canvasSize);
				draw(n.getLeft(),area);
			}
			if(n.getRight() != null) {
				Rectangle2D area = new Rectangle2D.Double(x, 0, (canvasSize - x), canvasSize);
				draw(n.getRight(),area);
			}
		}
		else if (n != null) {
			if(n.getIsNodeX()) {
				double x = n.getPoint2D().getX();
				StdDraw.filledCircle(x, n.getPoint2D().getY(), canvasSize/200);
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.line(x, a.getMinY(), x, a.getMaxY());

				if (n.getLeft() != null) 
				{
					Rectangle2D newA = new Rectangle2D.Double(a.getMinX(), a.getMinY(), (x - a.getMinX()), (a.getMaxY() - a.getMinY()));
					draw(n.getLeft(), newA);
				}

				if (n.getRight() != null)
				{
					Rectangle2D newA = new Rectangle2D.Double(x,  a.getMinY(), (a.getMaxX() - x), (a.getMaxY() - a.getMinY()));
					draw(n.getRight(), newA);
				}	
			}
			else if (!n.getIsNodeX())
			{
				double y =  n.getPoint2D().getY();
				StdDraw.filledCircle(n.getPoint2D().getX(), y, canvasSize/200);
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(a.getMinX(), y, a.getMaxX(), y);

				if (n.getLeft() != null)
				{
					Rectangle2D newA = new Rectangle2D.Double(a.getMinX(), a.getMinY(), (a.getMaxX() - a.getMinX()), (y - a.getMinY()));
					draw(n.getLeft(), newA);
				}

				if (n.getRight() != null) 
				{
					Rectangle2D newA = new Rectangle2D.Double(a.getMinX(), y, (a.getMaxX() - a.getMinX()), (a.getMaxY() - y));
					draw(n.getRight(), newA);
				}
			}
		}
	}

	public ArrayList<Point2D> query(Point2D center, double radius){ //adding then returning the arraylist
		ArrayList<Point2D> points = new ArrayList<>();				//of the points inside the circle
		pointsArrayList = addPointsToAL(center, radius, root, points);
		getClosest(pointsArrayList,center);
		return pointsArrayList;
	}
	private ArrayList<Point2D> addPointsToAL(Point2D center, double r, SpatialTreeNode n,ArrayList<Point2D> points) {
		if (n == null) { //recursively adding the points to the arraylist by using distance and 
			return null; //comparing it to the radius
		} else {
			if (Point2D.distance(center.getX(), center.getY(), n.getPoint2D().getX(), n.getPoint2D().getY()) <= r) {
				StdDraw.setPenColor(StdDraw.ORANGE);
				StdDraw.filledCircle(n.getPoint2D().getX(),n.getPoint2D().getY(), canvasSize/150);
				points.add(n.getPoint2D());
			}
		}

		if(n.getLeft() == null && n.getRight() == null) {
			return null;
		} else { 
			if(n.getLeft() != null) {
				addPointsToAL(center, r, n.getLeft(), points);
			}
			if(n.getRight() != null) {
				addPointsToAL(center, r, n.getRight(), points);
			}
		}
		return points;
	}
	void getClosest(ArrayList<Point2D> list,Point2D center) {
		//using the arraylist of points to get the closest point to the
		//center of circle and highlighting it plus drawing a line from it
		if(!list.isEmpty()) {
			Point2D closest = list.get(0);		
			for (Point2D p: pointsArrayList)
			{
				if(p.distance(center)<closest.distance(center)) {
					closest=p;
				}
			}
			StdDraw.setPenColor(StdDraw.CYAN);
			StdDraw.line(closest.getX(), closest.getY(), center.getX(),center.getY());
			StdDraw.setPenColor(StdDraw.YELLOW);
			StdDraw.filledCircle(closest.getX(),  closest.getY(), canvasSize/90);
		}

	}
	public int size()
	{
		return size;
	}
}
