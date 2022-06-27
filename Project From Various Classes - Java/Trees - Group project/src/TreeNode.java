
public class TreeNode<T> {
	private TreeNode<T> left;
	private TreeNode<T> right;
	private TreeNode<T> parent;
	private T data;
	
	public TreeNode(T val) {
		data = val;
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public TreeNode<T> getRight() {
		return right;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	//Invariants:
	//if n.left or n.right = m, then m.parent = n
	//if n.parent = m, then either m.right = n, or m.left = n
	public void setLeft(TreeNode<T> l) {
		left = l;
		
		//maintain the invariant!
		if(l != null) l.parent = this;
	}
	
	public void setRight(TreeNode<T> r) {
		right = r;
		
		if(r != null) r.parent = this;
	}
	
	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	public boolean isRoot() {
		return parent == null;
	}
	
	public int getSize() {
		if(isLeaf()) return 1;
		
		return 1 + (left == null? 0 : left.getSize()) + (right == null? 0 : right.getSize());
	}
	
	public int getHeight() {
		if(isLeaf()) return 0;
		
		return 1 + Math.max((left == null ? 0 : left.getHeight()), (right == null ? 0 : right.getHeight()));
	}
	
	public int getDepth() {
		if(isRoot()) return 0;
		
		return 1 + parent.getDepth();
	}
	
//	public String preorder() {
//		return data + " " + (left == null ? "" : left.preorder()) + " " + (right == null? "" : right.preorder());
//	}
	
	public String preorder() {
		StringBuilder b = new StringBuilder();
		
		preorder(b);
		
		return b.toString();
	}
	
	private void preorder(StringBuilder b) {
		b.append(data);
		b.append(" ");
		
		if(left != null) left.preorder(b);
		if(right != null) right.preorder(b);
	}
	
	public String inorder() {
		StringBuilder b = new StringBuilder();
		
		inorder(b);
		
		return b.toString();
	}
	
	private void inorder(StringBuilder b) {
		if(left != null) left.inorder(b);
		
		b.append(data);
		b.append(" ");
		
		if(right != null) right.inorder(b);
	}
	
	public String postorder() {
		StringBuilder b = new StringBuilder();
		
		postorder(b);
		
		return b.toString();
	}
	
	private void postorder(StringBuilder b) {
		if(left != null) left.postorder(b);
		if(right != null) right.postorder(b);
		
		b.append(data);
		b.append(" ");
	}
	
	
	
	
}
