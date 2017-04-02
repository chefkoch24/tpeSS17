package de.hsMannheim.informatik.ss17.tpe.mygroup.list;

/***
 * This interface define all required methods for a linked list
 * 
 * @author Jan Luksch
 */
public interface LinkedList {
	/***
	 * Add all containing object of the given list to the end of the list
	 * 
	 * @param list
	 *            with containing objects to add to the list
	 */
	public void addAll(LinkedList list);

	/***
	 * Add the object to the list to the given index
	 * 
	 * @param index
	 *            to add the object
	 * 
	 * @param integer
	 *            to add on the given index
	 */
	public void add(int index, Integer integer);

	/***
	 * Add the object as the fist object of the list
	 * 
	 * @param integer
	 *            to add at the begin of the list
	 */
	public void addFirst(Integer integer);

	/***
	 * Add the object as the object of the list
	 * 
	 * @param integer
	 *            to add at the end of the list
	 */
	public void addLast(Integer integer);

	/***
	 * @return a deep copy of the list
	 */
	public LinkedList cloneDeep();

	/***
	 * Clear the list
	 * 
	 * @return the list
	 */
	public LinkedList clear();

	/***
	 * Concatenate the list with the given list as a deep copy and return the
	 * new list
	 * 
	 * @param list
	 *            to concatenate
	 */
	public LinkedList concat(LinkedList list);

	/***
	 * @param integer
	 *            object that should equals with an object of the list
	 * 
	 * @return true if the list contains the object, else false
	 */
	public boolean contains(Integer integer);

	/***
	 * Delete the object on the given index
	 * 
	 * @param index
	 *            of the object
	 */
	public void delete(int index);

	/***
	 * @return the first object on the list
	 */
	public Integer getFirst();

	/***
	 * @return the last object on the list
	 */
	public Integer getLast();

	/***
	 * @param index
	 *            of the object
	 * 
	 * @return the object on the given index
	 */
	public Integer get(int index);

	/***
	 * @return true if the list is empty, else false
	 */
	public boolean isEmpty();

	/***
	 * Remove the first object on the list
	 * 
	 * @return the first object on the list
	 */
	public Integer removeFirst();

	/***
	 * Remove the last object on the list
	 * 
	 * @return the last object on the list
	 */
	public Integer removeLast();

	/***
	 * @return the size of the list
	 */
	public int size();

	/***
	 * @return all object that the list contains as array
	 */
	public Integer[] toArray();

	/***
	 * @return a shallow copy of the list
	 */
	public LinkedList clone();

	/***
	 * @return the Object containing the list as string
	 */
	public String toString();
}
