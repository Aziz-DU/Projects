

public interface List<T> {
	int size();
	boolean isEmpty();
	
	void addToFront(T val);
	//void addToBack(T val);
	T getValue();
	void advanceCursor(int n);
	T deleteCursor();
	boolean remove(T e);


}
