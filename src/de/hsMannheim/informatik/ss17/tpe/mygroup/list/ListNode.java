package de.hsMannheim.informatik.ss17.tpe.mygroup.list;

/**
 * The class ListNode is a helper class for every kind of LinkedLists
 * 
 */
class ListNode {

	private Integer element;
	private ListNode next;

	/**
	 * @return the value of the current node
	 */
	public Integer getElement() {
		return element;
	}

	/**
	 * @param value
	 *            the new value of the current node
	 */
	public void setElement(Integer value) {
		this.element = value;
	}

	/**
	 * @return the next element
	 */
	public ListNode getNext() {
		return next;
	}

	/**
	 * 
	 * @param next
	 *            the new next element
	 */
	public void setNext(ListNode next) {
		this.next = next;
	}
}
