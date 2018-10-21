
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/** Linked list implementation */
public class Node<E> implements List<E> {
	private myList<E> head; // Pointer to list header
	private myList<E> tail; // Pointer to last element
	protected myList<E> curr; // Access to current element
	private int cnt; // Size of list

	/** Constructors */
	Node(int size) {
		this();
	} // Constructor -- Ignore size

	Node() {
		curr = tail = head = new myList<E>(null); // Create header
		cnt = 0;
	}

	/** Remove all elements */
	public void clear() {
		head.setNext(null); // Drop access to links
		curr = tail = head = new myList<E>(null); // Create header
		cnt = 0;
	}

	/** Insert "it" at current position */
	public void insert(E it) {
		curr.setNext(new myList<E>(it, curr.next()));
		if (tail == curr)
			tail = curr.next(); // New tail
		cnt++;
	}

	/** Append "it" to list */
	public void append(E it) {
		tail = tail.setNext(new myList<E>(it, null));
		cnt++;
	}

	/** Remove and return current element */
	public E remove() {
		if (curr.next() == null)
			return null; // Nothing to remove
		E it = curr.next().element(); // Remember value
		if (tail == curr.next())
			tail = curr; // Removed last
		curr.setNext(curr.next().next()); // Remove from list
		cnt--; // Decrement count
		return it; // Return value
	}

	/** Set curr at list start */
	public void moveToStart() {
		curr = head;
	}

	/** Set curr at list end */
	public void moveToEnd() {
		curr = tail;
	}

	/** Set print at list end */

	public E  print() {
		String result = " ";
		myList<E> temp = head.next();
		
		if (head == null) {
			return null;
		}		while (temp != null) {
			result += temp.element();
			temp = temp.next();
		}
		
		System.out.print(result);
		return null;

	}

	/** Set transcribe at list end */

	@SuppressWarnings("unchecked")
	public void transcribe() {

		// T to U
		// A to U
		// C to G
		myList<E> temp = head.next();
		while (temp != null) {
			if (temp.element().equals("T")) {
				System.out.println("T to U");
				temp.setElement((E) "U");
			}

			else if (temp.element().equals("A")) {
				temp.setElement((E) "U");
				System.out.println("A to U");

			} else if (temp.element().equals("C")) {
				System.out.println("C to G");

				temp.setElement((E) "G");

			}

			temp = temp.next();
		}

	}

	/** Move curr one step left; no change if now at front */
	public void prev() {
		if (curr == head)
			return; // No previous element
		myList<E> temp = head;
		// March down list until we find the previous element
		while (temp.next() != curr)
			temp = temp.next();
		curr = temp;
	}

	public E clip(int start, int end) {

		int counter = 1;
		myList<E> temp = head;
		// If head is null do nothing
		if (head == null) {
			return null;
		}
		// ELse transverse list
		while (temp.next() != null) {
			temp = temp.next();
			counter++;
			if (counter == start) {
				head = temp;

			}
			if (counter > end) {
				temp.setNext(null);
			}

		}
		return null;

	}

	/** Move curr one step right; no change if now at end */
	public void next() {
		if (curr != tail)
			curr = curr.next();
	}

	/** @return List length */
	public int length() {
		return cnt;
	}

	/** Move Check is Sequence is Null */

	public boolean isSqNull() {
		myList<E> temp = head.next();

		while (temp != null) {

			if (!(temp.element().equals("") || temp.element().equals(" "))) {
				return false;
			}
		}
		return true;
	}

	/** @return The position of the current element */
	public int currPos() {
		myList<E> temp = head;
		int i;
		for (i = 0; curr != temp; i++)
			temp = temp.next();
		return i;
	}

	/** Move down list to "pos" position */
	public void moveToPos(int pos) {
		assert (pos >= 0) && (pos <= cnt) : "Position out of range";
		curr = head;
		for (int i = 0; i < pos; i++)
			curr = curr.next();
	}

	/** @return Current element value */
	public E getValue() {
		if (curr.next() == null)
			return null;
		return curr.next().element();
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

}
