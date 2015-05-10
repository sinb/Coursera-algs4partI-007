import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int N; // number of elements on queue
	private Node first; // beginning of queue, addFirst, removeFirst here
	private Node last; // end of queue, addLast, removeLast here

	// this is a deque, so better use two pointers
	private class Node {
		private Item item;
		private Node prev;
		private Node next;
	}

	public Deque() {
		first = null;
		last = null;
		N = 0;
	}

	public boolean isEmpty() {
		return (first == null && last == null);
	}

	public int size() {
		return N;
	}

	public void addFirst(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		boolean empty = isEmpty();
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		first.prev = null; //first.prev is alway null 
		if (empty) {
			last = first; //empty means last = oldFirst = null
		} else {
			oldFirst.prev = first;
		}
		N++;
	}

	public void addLast(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		boolean empty = isEmpty();
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.prev = oldLast;
		if (!empty) {
			oldLast.next = last;
		} else { // empty means first=last =oldlast = null
			first = last; //so should first be last
		}
		N++;
	}

	public Item removeFirst() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		Node next = first.next;
		Item result = first.item;
		if (next != null) {
			next.prev = null;
		}
		if (first == last) {
			last = next;
		}
		first = next;
		N--;
		return result;
	}

	public Item removeLast() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		Node prev = last.prev;
		Item result = last.item;
		if (prev != null)
			prev.next = null;
		if (first == last) {
			first = null;
		}
		last = prev;
		N--;
		return result;
	}

	public Iterator<Item> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {
		Deque<String> deque = new Deque<String>();
		deque.addLast("a");
		deque.addLast("b");
		deque.addLast("c");
		deque.addLast("d");
		deque.addFirst("xx");

		deque.removeFirst();deque.removeFirst();deque.removeFirst();deque.removeFirst();
		for (String s: deque)
			StdOut.println(s);
	}
}