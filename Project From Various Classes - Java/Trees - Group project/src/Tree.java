
public class Tree<T extends Comparable<T>> {
	private TreeNode<T> root;
	private int size;
	
	public Tree() {
		root = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private TreeNode<T> search(T val, TreeNode<T> node) {
		if(node.getData().equals(val)) return node;
		
		if(node.getData().compareTo(val) < 0) {
			if(node.getRight() == null) {
				return node;
			}
			
			return search(val, node.getRight());
		} else {
			if(node.getLeft() == null) return node;
			
			return search(val, node.getLeft());
		}
	}
	
	public boolean contains(T val) {
		return search(val, root).getData().equals(val);
	}
	
	public boolean add(T val) {
		TreeNode<T> insert = new TreeNode<>(val);
		
		if(size == 0) {
			root = insert;
			++size;
			return true;
		}
		
		TreeNode<T> node = search(val, root);
		
		if(node.getData().equals(val)) return false;
		
		if(node.getData().compareTo(val) < 0) {
			node.setRight(insert);
		} else {
			node.setLeft(insert);
		}
		
		++size;
		
		return true;
	}
	
	public String toString() {
		return root.inorder();
	}
}

