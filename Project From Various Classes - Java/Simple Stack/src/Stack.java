import java.util.ArrayList;

public class Stack<E> {
	ArrayList<E> stackE=new ArrayList<>();
	
	public void push(E element) {
		stackE.add(stackE.size(),element);
	}
	public E pop() {
		int lastE=stackE.size() - 1;
		E temp=stackE.get(lastE);
		stackE.remove(lastE);	
		return temp;
	}
	public E top() {
		int lastE=stackE.size() - 1;	
		return stackE.get(lastE);
	
	}
	public boolean isEmpty() {
		if(stackE.isEmpty()==true) {
			return true;
		}
		else {
			return false;	
		}
	}
}
