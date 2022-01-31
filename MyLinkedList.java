/**
 * Name: Joshua Yang
 * Email: jwyang@ucsd.edu
 * Sources used: Lecture slides, monday 2pm discussion
 * This file contains MyLinkedLIst class and the Node class.It has getters,
 * setters, and a variety of other methods. 
 */

import java.util.AbstractList;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

/** 
 * Class implementing linked list. Has instance variables size, head, and tail.
 * Also has proteced class Node with gettes and setters. 
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	/**
	 * A Node class that holds data and references to previous and next Nodes.
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		/** 
		 * Constructor to create singleton Node 
		 * @param element Element to add, can be null	
		 */
		public Node(E element) {
			// Initialize the instance variables
			this.data = element;
			this.next = null;
			this.prev = null;
		}

		/** 
		 * Set the parameter prev as the previous node
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/** 
		 * Accessor to get the next Node in the list 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/** 
		 * Accessor to get the prev Node in the list
		 * @return the previous node  
		 */
		public Node getPrev() {
			return this.prev;
		}

		/** 
		 * Accessor to get the Nodes Element 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	public MyLinkedList() {
		// head and tial new dummy nodes
		this.head = new Node(null);
		this.tail = new Node(null);
		// head and tail point to each other
		this.head.setNext(this.tail);
		this.tail.setPrev(this.head);
		// default size
		this.size = 0;
	}

	/**
	 * gives the size of the Linked List
	 * @return the size
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Gets the data at the given index
	 * @param index index where the data will be retrived
	 * @return data of the given index
	 */
	@Override
	public E get(int index) {
		// index should stay in bounds
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}
		
		// Create a new refrence to the first node with data (not head node)
		Node curr = this.head.getNext();
	
		// Iterate through list to get to given index
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}
		// data of the node
		return (E) curr.getElement();  
		
	}

	/**
	 * Adds new node with given data at given index
	 * @param index where the new node will be added
	 * @param data what goes int he new node
	 */
	@Override
	public void add(int index, E data) {
		// data should not be null
		if (data == null) {
			throw new NullPointerException();
		}

		// index should stay in bounds
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}

		// Create new node with given data
		Node nodeData = new Node(data);

		// Make curr node the head and iterate through until index is reached
		Node curr = this.head.getNext();
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}

		// curr node is the first node in the list
		if (curr == this.head.getNext()) {
			curr.setPrev(nodeData);
			nodeData.setNext(curr);
			this.head.setNext(nodeData);
			this.size++;
		}
		// curr node is the last node in the list
		else if (curr == this.tail.getPrev()) {
			curr.setNext(nodeData);
			nodeData.setPrev(curr);
			this.tail.setPrev(nodeData);
			this.size++;
		}
		// All other nodes in middle of list
		else {
			curr.next.setPrev(nodeData);
			nodeData.setNext(curr.next);
			nodeData.setPrev(curr);
			curr.setNext(nodeData);
			this.size++;
		}
		
		

		
	}

	/**
	 * Adds new node with element to end of Linked List
	 * @param data the data in the new node
	 * @return true when the new node is added
	 */
	public boolean add(E data) {
		// data should not be null
		if (data == null) {
			throw new NullPointerException();
		}

		// Create a new nude with given data
		Node nodeData = new Node(data);

		// list has no elements
		if (this.size == 0) {
			this.head.setNext(nodeData);
			this.tail.setPrev(nodeData);
			this.size++;
		}

		// list has elements
		else {
			nodeData.setNext(this.tail);
			nodeData.setPrev(this.tail.getPrev());
			this.tail.getPrev().setNext(nodeData);
			this.tail.setPrev(nodeData);
			this.size++;
		}

		return true; 
	}

	/**
	 * Sets the node at the given index to contain given data
	 * @param index where node will be editted
	 * @param data what will replace the data
	 * @return the old data	
	 */
	public E set(int index, E data) {
		// data should not be null
		if (data == null) {
			throw new NullPointerException();
		}

		// index should be in bounds
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}

		// Create curr node
		Node curr = this.head.getNext();
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}

		// temp get old elem, curr gets new elem, reutrn old elem
		E temp = curr.getElement();
		curr.setElement(data);;
		return (E) temp; 
	}

	/**
	 * deletes the node at given index
	 * @param index index where node will be deleted
	 * @return the data of node that was deleted
	 */
	public E remove(int index) {
		// index should be in bounds
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}

		// Create curr node
		Node curr = this.head.getNext();
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}

		// Get the data of the node that will be deleted
		E temp = curr.getElement();

		// delete node
		curr.getNext().setPrev(curr.getPrev());
		curr.getPrev().setNext(curr.getNext());
		curr.setNext(null);
		curr.setPrev(null);
		this.size--;

		// return the deleted data
		return (E) temp; 
	}

	/**
	 * Removes all the elements from the list
	 */
	public void clear() {
		// Create new curr node
		Node curr = this.head.getNext();

		// when the next node is not tail
		while(curr.getNext() != null) {
			// the next node's prev pointer points to head
			curr.getNext().setPrev(curr.getPrev());
			// curr next pointer moves on
			curr.setNext(curr.getNext().getNext());
			this.size--;
		}

		// set the head and tail pointers to each other
		this.head.setNext(this.tail);
		this.tail.setPrev(this.head);
		
	}

	/**
	 * Determiens if the list is empty
	 * @return True if the list is empty
	 * @return False if the list is not empty
	 */
	public boolean isEmpty() {
		if ((this.head.getNext() != this.tail || this.tail.getPrev() != 
			this.head) || this.size != 0) {
			return false;
		} 
		else {
			return true;
		}
	}

	/**
	 * gets node at the given index
	 * @param index index where the node is retrieved
	 * @return the node that is retreived
	 */
	protected Node getNth(int index) {
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}
		// Create curr node
		Node curr = this.head.getNext();

		// iterate through list
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}

		return (Node) curr; 
	}

	protected class MyListIterator implements ListIterator<E> {


        // class variables here
		Node left;
		Node right;
		int idx;
		boolean forward;
		boolean canRemoveOrSet;

        

        /**
		 * Constructor for MyListIterator
		 */
		public MyListIterator() {
			this.left = head;
			this.idx = 0;
			this.forward = true;
			this.right = head.getNext();
			this.canRemoveOrSet = false;
		}

		/**
		 * determines if there is a forward onde
		 * @return ture if there is forward node
		 */
        public boolean hasNext() {
			return this.idx < size;
        }

		/**
		 * gets next element in list going in forward direction
		 * @return next element in list
		 */
		public E next() {
			// the next node has no element
			if (this.right.getElement() == null) {
				throw new NoSuchElementException();
			}

			this.forward = true;
			this.canRemoveOrSet = true;
			this.idx++;
			return this.right.getElement();
		}

		/**
		 * Determines if there is a previous node
		 * @return true if there is a node previous to current one
		 */
		public boolean hasPrevious() {
			return this.idx > 0;
		}

		/**
		 * get previous element
		 * @return previous element
		 */
		public E previous() {
			if (this.left.getElement() == null) {
				throw new NoSuchElementException();
			}

			this.forward = false;
			this.canRemoveOrSet = true;
			this.idx--;
			return this.left.getElement();
		}

		/**
		 * Get next index, or get the size if at the end
		 * @return next index or size if at the end of list
		 */
		public int nextIndex() {
			if (this.hasNext()) {
				return this.idx + 1;
			} 
			
			// There is no next index, at the tail
			else {
				return size;
			}
		}

		/**
		 * Get previous index 
		 * @return previous index or -1 if at beginning of list
		 */
		public int previousIndex() {
			if (this.hasPrevious()) {
				return this.idx - 1;
			} 
			
			// There is no previous index, at the head
			else {
				return -1;
			}
		}

		/**
		 * adds node with element to list before the node given by next()
		 * throws nullpointer
		 * @param element data of the node
		 */
		public void add(E element) {
			// element cannot be null
			if (element == null) {
				throw new NullPointerException();
			}

			
		}
		
		/**
		 * replaces node element from the most recent previous/next call
		 * @param element new data replacing the old data
		 */
		public void set(E element) {
			// element cannot be null
			if (element == null) {
				throw new NullPointerException();
			}

			// node cannot be set
			if (!this.canRemoveOrSet) {
				throw new IllegalStateException();
			}
		}

		/**
		 * remove last element node returned by most recent previous/next call
		 */
		public void remove() {
			// node cannot be removed
			if (!this.canRemoveOrSet) {
				throw new IllegalStateException();
			}

		
		}
 }


}
