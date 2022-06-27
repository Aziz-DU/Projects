
public class SinglyLinkedList<T> {

	private Node head;
	private Node tail;
	private int size;

	public SinglyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public void addToFront(T data) {
		Node n = new Node(data, head);
		head = n;
		if(size == 0) tail = n;
		++size;
	}

	public T removeFront() {
		if(size == 0) throw new IllegalStateException("Cannot remove from empty list");
		T temp = head.data;
		head = head.next;
		if(size == 1) tail = null;
		--size;
		return temp;
	}

	public void addToBack(T data) {
		Node n = new Node(data, null);
		if(size == 0) {
			head = n;
		} else {
			tail.next = n;
		}
		tail = n;
		++size;
	}

	public String toString() {
		Node current = head;
		String ret = "";

		while(current != null) {
			ret += current.data + " ";
			current = current.next;
		}
		return ret;	
	}
	public boolean remove(T e) {
		if(size == 0) {
			System.out.println("Cannot remove from empty list");
			return false;
		}
		if (head.data == e) {
		    head = head.next;
		    size--;
		    return true;
		  }
		Node temp = head;
		while(temp.next != null) {
			if(temp.next.data.equals(e)) {
				temp.next=temp.next.next;
				size--;
				return true;
			}
			temp=temp.next;
		}	
		System.out.println(e+" is not in the list");
		return false;
	}
	private class Node {
		private T data;
		private Node next;

		public Node(T d, Node n) {
			data = d;
			next = n;
		}
	}
}
