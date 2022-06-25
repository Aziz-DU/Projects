package lab2;

public interface List<T> {
	int size();
	boolean isEmpty();
	T getValue();
	T deleteCursor();
	void addAfterCursor(T val);
	void advanceCursor(int n);

}
