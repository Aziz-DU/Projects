package lab2;
import java.util.Random; 
public class Driver {

	public static void main(String[] args) {
		CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<Integer>();
        Random rand = new Random(); 

		for(int i=1;i<11;i++) {
			list.addAfterCursor(i);	
		}		
		while(!list.isEmpty()) {
			int rand_int1 = rand.nextInt(20); 
			list.advanceCursor(rand_int1);	
			System.out.println("Cursor value is: "+list.getValue());
			list.deleteCursor();	
			//System.out.println("List After removing cursor: "+list); //added it in case you wanted to
																	   //check the list after deletion
		}
	}
}
