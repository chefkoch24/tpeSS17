package de.hsMannheim.informatik.ss17.tpe.mygroup;

public class ListNode {
	
	private BTreeNode element;
	private ListNode next;

	/**
	 * @return the value of the current node
	 */
	public BTreeNode getElement() {
		return element;
	}

	/**
	 * @param value the new value of the current node
	 */
	public void setValue(BTreeNode node) {
		this.element = node;
	}

	/**
	 * @return the next element
	 */
	public ListNode getNext() {
		return next;
	}

	/**
	 * @param next the new next element
	 */
	public void setNext(ListNode next) {
		this.next = next;
	}

}

