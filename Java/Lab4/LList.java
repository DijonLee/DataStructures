
public class myList<E> {
	private E element; // Value for this node
	private myList<E> next; // Pointer to next node in list
	// Constructors

	myList(E it, myList<E> nextval) {
		element = it;
		next = nextval;
	}

	myList(myList<E> nextval) {
		next = nextval;
	}

	myList<E> next() {
		return next;
	} // Return next field

	myList<E> setNext(myList<E> nextval) // Set next field
	{
		return next = nextval;
	} // Return element field

	E element() {
		return element;
	} // Set element field

	E setElement(E it) {
		return element = it;
	}

	public char[] getValue() {
		// TODO Auto-generated method stub
		return null;
	}
}
