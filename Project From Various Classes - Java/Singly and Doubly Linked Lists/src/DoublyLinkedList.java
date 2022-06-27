
public class DoublyLinkedList<T> implements List<T> {
	
	private Node cursor; 
	private int size;
	public DoublyLinkedList() {
		size = 0;		
		cursor = new Node(null, null, null);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	@Override
	public T getValue() {
		if(isEmpty()) throw new IllegalStateException("list is empty");

		return cursor.data;
	}
	@Override
	public void addToFront(T val) {
		Node n = new Node(val, null, null );
		if(isEmpty()) {
			cursor= n;
			cursor.next = cursor;
			cursor.prev=cursor;
		}
		else {
			n.prev=cursor;
			n.next=cursor.next;
			cursor.next.prev=n;
			cursor.next=n;
		}
		++size;		
	}

	@Override
	public void advanceCursor(int n) {
		if(isEmpty()) {
			return;
		}
		else {
			for(int i=0;i<n;i++) {
				cursor=cursor.next;
			}
		}
	}
	
	@Override
	public T deleteCursor() {
	if(isEmpty()) throw new IllegalStateException("Nothing to remove");
	
	Node temp=cursor;
	cursor.prev.next=cursor.next;
	cursor.next.prev=cursor.prev;	
	--size;
	if(size<=0) {
		cursor=null;	
	}
	else {
		cursor=cursor.next;
	}
		return temp.data;
	}
	public boolean remove(T e) {
		if(size == 0) {
			System.out.println("Cannot remove from empty list");
			return false;
		}
		if (cursor.data == e) {
			cursor = cursor.next;
			size--;
		    return true;
		  }
		Node temp = cursor;
		while(temp.next != null) {
			if(temp.next.data.equals(e)) {
				temp.next=temp.next.next;
				size--;
				return true;
			}
			temp=temp.next;
			if(temp.next==temp) {
				System.out.println(e+" is not in the list");
				return false;
			}
		}	
		return false;
	}
	
	public String toString() {
		Node current = cursor;
		String ret = "";
		int s=1;
		while(s <=size) {
			ret += current.data + " ";
			current = current.next;
			s+=1;
		}
		return ret;	
	}
	private class Node {
		private Node prev;
		private Node next;
		private T data;		
		public Node(T d, Node p, Node n) {
			data = d;
			prev = p;
			next = n;
		}

	}

}
