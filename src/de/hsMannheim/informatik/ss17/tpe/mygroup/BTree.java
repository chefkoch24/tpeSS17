package de.hsMannheim.informatik.ss17.tpe.mygroup;

public interface BTree {
	/**
	 * Insert the given object to the tree
	 * 
	 * @param object
	 *            to insert
	 * @return false if the object is already in the node, else true
	 * @throws GDIException
	 *             if the object is null
	 */
	public abstract boolean insert(Integer object);

	/**
	 * Insert all values from the file to the tree
	 * 
	 * @param filename
	 *            of the file
	 * @return false if the file not exists, else true
	 */
	public abstract boolean insert(String filename);

	public abstract boolean contains(Integer o);

	public abstract int size();

	public abstract int height();

	public abstract Integer getMax();

	public abstract Integer getMin();

	public abstract boolean isEmpty();

	public abstract void addAll(BTree otherTree);

	public abstract void printInorder();

	public abstract void printPostorder();

	public abstract void printPreorder();

	public abstract void printLevelorder();
}
