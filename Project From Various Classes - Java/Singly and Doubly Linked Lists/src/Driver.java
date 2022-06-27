
public class Driver {
	public static void main(String[] args) {
	GameCharacter gm1 = new GameCharacter("Velkoz");
	GameCharacter gm2 = new GameCharacter("Jayce");
	GameCharacter gm3 = new GameCharacter("illaoi");
	GameCharacter gm4 = new GameCharacter("Fizz");
	GameCharacter gm5 = new GameCharacter("Jayce");
	GameCharacter gm6 = new GameCharacter("Blitzcrank");
	GameCharacter gm7 = new GameCharacter("Jax");
	SinglyLinkedList<GameCharacter> sl1 = new SinglyLinkedList<GameCharacter>();
	DoublyLinkedList<GameCharacter> dl1 = new DoublyLinkedList<GameCharacter>();
	sl1.addToBack(gm1);
	sl1.addToBack(gm2);
	sl1.addToBack(gm3);
	sl1.addToBack(gm4);
	sl1.addToBack(gm5);
	sl1.addToBack(gm6);
	
	dl1.addToFront(gm1);
	dl1.addToFront(gm2);
	dl1.addToFront(gm3);
	dl1.addToFront(gm4);
	dl1.addToFront(gm5);
	dl1.addToFront(gm6);
	
	//Removing elements from the head, middle, and tail of the list.
	System.out.println("-Lists before removal:");
	System.out.println(sl1);
	System.out.println(dl1);
	sl1.remove(gm1);
	dl1.remove(gm1);
	sl1.remove(gm3);
	dl1.remove(gm5);
	sl1.remove(gm6);
	dl1.remove(gm2);
	System.out.println("-Lists After removal:");
	System.out.println(sl1);
	System.out.println(dl1);
	
	//Removing elements from a single-element list.
	sl1.remove(gm2);
	sl1.remove(gm5);
	dl1.remove(gm6);
	dl1.remove(gm4);
	// now they all have 1 element
	System.out.println("-Removing from a single element list:");
	System.out.println(sl1);
	System.out.println(dl1);
	sl1.remove(gm4);
	dl1.remove(gm3);
	
	//Removing elements from an empty list.
	System.out.println("-Removing from an empty list:");
	sl1.remove(gm4);
	dl1.remove(gm3);
	
	//Attempting to remove elements that are not in the list.
	sl1.addToBack(gm1);	
	dl1.addToFront(gm1);
	System.out.println("-Removing from elements that are not in the list:");
	sl1.remove(gm7);
	dl1.remove(gm7);

	//Attempting to remove elements that are in the list.
	sl1.remove(gm1);
	dl1.remove(gm1);

	//Attempting to remove an element that appears multiple times in the list.
	sl1.addToBack(gm1);	
	sl1.addToBack(gm2);
	sl1.addToBack(gm1);	
	dl1.addToFront(gm1);
	dl1.addToFront(gm2);
	dl1.addToFront(gm1);
	System.out.println("-Removing from elements that appear multiple times in the list:");
	System.out.println(sl1);
	System.out.println(dl1);	
	sl1.remove(gm1);
	dl1.remove(gm1);
	sl1.remove(gm1);
	dl1.remove(gm1);
	System.out.println(sl1);
	System.out.println(dl1);

	
	
	}
}
