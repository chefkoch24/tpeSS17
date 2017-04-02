package de.hsMannheim.informatik.ss17.tpe.mygroup.list;

import static gdi.MakeItSimple.*;

/***
 * This class is an simple linked list implementation
 * 
 * @author Jan Luksch
 */
public class MyLinkedList implements LinkedList {

	private ListNode head = new ListNode();
	private int size = 0;

	/***
	 * @return a new empty list object
	 */
	static MyLinkedList empty() {

		return new MyLinkedList();
	}

	/***
	 * Add all containing object of the given list to the end of the list
	 * 
	 * @param list
	 *            with containing objects to add to the list
	 */
	@Override
	public void addAll(LinkedList list) {

		if (list != null) {
			for (int i = 0; i < list.size(); ++i) {
				addLast(list.get(i));
			}
		}
	}

	/***
	 * Add the object to the list at the given index, if the index is greater
	 * than the size, the object will add to the end of the list
	 * 
	 * @param index
	 *            to add the object
	 * 
	 * @param integer
	 *            to add on the given index
	 * 
	 * @throws GDIException
	 *             if the given object is null, or the index is out of range
	 */
	@Override
	public void add(int index, Integer integer) {

		if (index < 0) {
			throw new GDIException("Out of range");
		} else if (integer == null) {
			throw new GDIException("given object is null");
		}

		if (index == 0) {
			// Add as first node
			addFirst(integer);
		} else if (index >= size) {
			// Add as last node
			addLast(integer);
		} else {
			// Points to the node behind the index
			ListNode pointer = head;
			for (int i = 0; i < index; ++i) {
				pointer = pointer.getNext();
			}

			// Set the new node between the node behind the index and the node
			// on the index
			ListNode element = new ListNode();
			element.setElement(integer);
			element.setNext(pointer.getNext());

			pointer.setNext(element);
			++size;
		}
	}

	/***
	 * Add the object as the fist object of the list
	 * 
	 * @param integer
	 *            to add at the begin of the list
	 * 
	 * @throws GDIException
	 *             if the given object is null
	 */
	@Override
	public void addFirst(Integer integer) {

		if (integer == null) {
			throw new GDIException("given object is null");
		}

		ListNode element = new ListNode();

		element.setElement(integer);
		element.setNext(head.getNext());

		head.setNext(element);
		++size;
	}

	/***
	 * Add the object as the last object of the list
	 * 
	 * @param integer
	 *            to add at the end of the list
	 * 
	 * @throws GDIException
	 *             if the given object is null
	 */
	@Override
	public void addLast(Integer integer) {

		if (integer == null) {
			throw new GDIException("given object is null");
		}

		ListNode element = new ListNode();
		element.setElement(integer);

		// Search tail of list
		ListNode pointer = head;
		while (pointer.getNext() != null) {
			pointer = pointer.getNext();
		}

		pointer.setNext(element);
		++size;
	}

	/***
	 * @return a deep copy of the list
	 */
	@Override
	public LinkedList cloneDeep() {

		MyLinkedList list = new MyLinkedList();

		// Add all object the list containing as deep copy
		ListNode pointer = head;
		for (int i = 0; i < size; ++i) {
			pointer = pointer.getNext();
			// Hands over the object as an new object that have the same state
			list.addLast(new Integer(get(i).intValue()));
		}

		return list;
	}

	/***
	 * Clear the list
	 * 
	 * @return the list
	 */
	@Override
	public LinkedList clear() {

		head.setNext(null);
		size = 0;

		return this;
	}

	/***
	 * Concatenate the list with the given list as a deep copy and return the
	 * new list
	 * 
	 * @param list
	 *            to concatenate
	 * 
	 * @throws GDIException
	 *             if the given list is null
	 */
	@Override
	public LinkedList concat(LinkedList list) {

		if (list == null) {
			throw new GDIException("Given list is empty");
		}

		LinkedList concatenate = new MyLinkedList();

		concatenate.addAll(cloneDeep());
		concatenate.addAll(list.cloneDeep());

		return concatenate;
	}

	/***
	 * @param integer
	 *            object that should equals with an object of the list
	 * 
	 * @return true if the list contains the object, else false
	 * 
	 * @throws GDIException
	 *             if the given object is null
	 */
	@Override
	public boolean contains(Integer integer) {

		if (integer == null) {
			throw new GDIException("Given object is null");
		}

		ListNode pointer = head;

		// Compare all elements with the given object
		for (int i = 0; i < size; ++i) {
			pointer = pointer.getNext();

			if (pointer.getElement().equals(integer)) {
				return true;
			}
		}

		return false;
	}

	/***
	 * Delete the object on the given index
	 * 
	 * @param index
	 *            of the object
	 */
	@Override
	public void delete(int index) {

		if (index < 0 || isEmpty() || index >= size) {
			throw new GDIException("Out of index");
		}

		if (index == 0) {
			removeFirst();
		} else {

			ListNode pointer = head;

			// Points to the node before the index
			for (int i = 0; i < index; ++i) {
				pointer = pointer.getNext();
			}

			pointer.setNext(pointer.getNext().getNext());
			--size;
		}
	}

	/***
	 * @return the first object on the list
	 * 
	 * @throws GDIException
	 *             if the list is empty
	 */
	@Override
	public Integer getFirst() {

		if (isEmpty()) {
			throw new GDIException("List is empty");
		}

		// Return the first object the list containing
		return head.getNext().getElement();
	}

	/***
	 * @return the last object on the list
	 * 
	 * @throws GDIException
	 *             if the list is empty
	 */
	@Override
	public Integer getLast() {

		if (isEmpty()) {
			throw new GDIException("List is empty");
		}

		// Search tail of list
		ListNode pointer = head;
		for (int i = 0; i < size; ++i) {
			pointer = pointer.getNext();
		}

		return pointer.getElement();
	}

	/***
	 * @param index
	 *            of the object
	 * 
	 * @return the object on the given index
	 * 
	 * @throws GDIException
	 *             if the index is out of range
	 */
	@Override
	public Integer get(int index) {

		if (index < 0 || isEmpty() || index >= size) {
			throw new GDIException("Out of range");
		}

		// Points to the element on the given index
		ListNode pointer = head;
		for (int i = 0; i < (index + 1); ++i) {
			pointer = pointer.getNext();
		}

		return pointer.getElement();
	}

	/***
	 * @return true if the list is empty, else false
	 */
	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	/***
	 * Remove the first object on the list
	 * 
	 * @return the first object on the list
	 * 
	 * @throws GDIException
	 *             if the list is empty
	 */
	@Override
	public Integer removeFirst() {

		if (isEmpty()) {
			throw new GDIException("List is empty");
		}

		// Save the state of the object to return it
		Integer object = new Integer(head.getNext().getElement().intValue());

		// The head points now to the node after the first node
		head.setNext(head.getNext().getNext());
		--size;

		return object;
	}

	/***
	 * Remove the last object on the list
	 * 
	 * @return the last object on the list
	 * 
	 * @throws GDIException
	 *             if the list is empty
	 */
	@Override
	public Integer removeLast() {

		if (isEmpty()) {
			throw new GDIException("List is empty");
		}

		ListNode pointer = head;

		// Points to to node before the last node
		for (int i = 0; i < size - 1; ++i) {
			pointer = pointer.getNext();
		}

		// Save the state of the object to return it
		Integer object = new Integer(pointer.getNext().getElement().intValue());

		pointer.setNext(null);
		--size;

		return object;
	}

	/***
	 * @return the size of the list
	 */
	@Override
	public int size() {

		return size;
	}

	/***
	 * @return all object that the list contains as array
	 */
	@Override
	public Integer[] toArray() {

		Integer[] array = new Integer[size];

		// hands over all elements to the new array
		ListNode pointer = head;
		for (int i = 0; i < size; ++i) {
			pointer = pointer.getNext();
			array[i] = pointer.getElement();
		}

		return array;
	}

	/***
	 * @return the Object containing the list as string
	 */
	@Override
	public String toString() {

		ListNode pointer = head;
		String output = "";

		// write all elements the list containing in the string
		for (int i = 0; i < size; ++i) {
			pointer = pointer.getNext();
			output += pointer.getElement().toString() + "\n";
		}

		return output;
	}

	/***
	 * @return a shallow copy of the list
	 */
	@Override
	public MyLinkedList clone() {

		MyLinkedList list = new MyLinkedList();

		for (int i = 0; i < size; ++i) {
			list.addLast(get(i));
		}

		return list;
	}
}
