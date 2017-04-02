package de.hsMannheim.informatik.ss17.tpe.mygroup.queue;

import de.hsMannheim.informatik.ss17.tpe.mygroup.BTreeNode;
import gdi.MakeItSimple.*;

public class Queue {

	static QueueNode head;

	public Queue() {
		head = null;
	}


	/**
	 * add a new integer element at the last position of the queue
	 * @param value is the value of the element, that will be insert
	 * @return value of the inserted element
	 */
	public void enter(BTreeNode e) {
		QueueNode element = new QueueNode();
		if(e != null){
			element.setValue(e);
		}else{
			return;
		}
		element.setNext(null);
		QueueNode temp = head;
		while (temp != null && temp.getNext() != null) {
			temp = temp.getNext();
		}
		// append at tail
		// if the queue is empty
		if (temp == null) { 
			head = element;
		} 
		// queue contains elements
		else 
			temp.setNext(element);
	}

	/**
	 * remove the first element of the queue
	 * @return the value of the deleted element
	 */
	public BTreeNode leave() {
		// if the queue is empty
		if (head == null)
			throw new GDIException("Die Schlange enthält keine Elemente");
		// get the second element to connect it with the head
		QueueNode temp = head;
		// save the value of the element that will be deleted
		BTreeNode value = temp.getElement();
		temp = temp.getNext();	
		// head get the new value of the second element
		head = temp;
		return value;
	}

	/**
	 * you get the first position of the queue
	 * @return the int value of the first element
	 */
	public BTreeNode front() {
		// if queue is empty
		if (head == null) {
			throw new GDIException("Die Schlange enthält keine Elemente");
		} else
			// if queue is not empty
			return head.getElement();
	}

	/**
	 * create a new empty queue
	 * @return empty queue
	 */
	public Queue empty() {
		
		Queue empty = new Queue();
		return empty;
	}

	/**
	 * is a boolean if the queue is empty
	 * @return if the queue is empty or not
	 */
	public boolean isEmpty() {
		// if the queue is empty
		if (head == null){
			return true;
		}
		// if the queue is not empty
		else
			return false;
	}
	
	/**
	 * returns the queue as a string with all int values
	 * @return the queue as a string
	 */
	public String toString() {
		// create a empty string
		String printQueue = "";
		// create an list node to get all element values 
		QueueNode temp = head;
		// print the queue from the first to the last element
		while (temp != null) {
			printQueue = printQueue + temp.getElement() + ", ";
			temp = temp.getNext();
		}
		return printQueue;
	}
	
	public int size() {

		int size = 0;
		QueueNode temp = head;
		if(head == null){
			return 0;
		}
		while (temp != null) {
			size++;
			temp = temp.getNext();
		}
		
		return size;
	}

}
