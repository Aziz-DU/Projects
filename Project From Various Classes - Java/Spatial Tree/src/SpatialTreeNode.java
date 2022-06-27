import java.awt.geom.Point2D;
public class SpatialTreeNode {
	private SpatialTreeNode left;
	private SpatialTreeNode right;
	private SpatialTreeNode parent;
	private Point2D point2D;
	private boolean isX;


	public SpatialTreeNode(Point2D point2D ,SpatialTreeNode parent, boolean isX) {
		this.point2D = point2D; //basic treenode node class with getters and setters and overridden
		this.setParent(parent);//equals method
		this.setxNode(isX);
		this.setLeft(null);
		this.setRight(null);
	}

	public SpatialTreeNode getLeft() {
		return left;
	}

	public SpatialTreeNode getRight() {
		return right;
	}

	public Point2D getPoint2D() {
		return point2D;
	}
	public boolean getIsNodeX() {
		return isX;
	}
	public void setLeft(SpatialTreeNode l) {
		left = l;

		if(l != null) l.parent = this;
	}

	public void setRight(SpatialTreeNode r) {
		right = r;

		if(r != null) r.parent = this;
	}
	public void setParent(SpatialTreeNode parent) 
	{
		this.parent = parent;
	}
	public void setxNode(boolean isX) {
		this.isX=isX;
	}
	public boolean isLeaf() {
		return left == null && right == null;
	}
	public boolean equals(Point2D point2D) 
	{
		if (point2D != null && this.point2D != null)
		{
			return this.point2D.getX() == point2D.getX() && this.point2D.getY() == point2D.getY();
		}
		return false;
	}
}
