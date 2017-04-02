package de.hsMannheim.informatik.ss17.tpe.mygroup;

import gdi.MakeItSimple.GDIException;

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

	/**
	 * Search through the complete tree to find the given object
	 * 
	 * @param object
	 *            to find in the tree
	 * @return true if the object is in the tree, else fase
	 */
	public abstract boolean contains(Integer object);

	/**
	 * Get you the number of elements they are in the tree
	 * 
	 * @return Integer the size
	 */
	public abstract int size();

	/**
	 * Get you the height of the tree
	 * 
	 * @return Integer the height
	 */
	public abstract int height();

	/**
	 * Get you the maximal object in the tree
	 * 
	 * @return Integer maximal object
	 * 
	 * @throws GDIException
	 *             if the tree is empty
	 */
	public abstract Integer getMax();

	/**
	 * Get you the minimal object in the tree
	 * 
	 * @return Integer minimal object
	 * 
	 * @throws GDIException
	 *             if the tree is empty
	 */
	public abstract Integer getMin();

	/**
	 * check if the tree is empty or not
	 * 
	 * @return true if the tree is empty else false
	 */
	public abstract boolean isEmpty();

	public abstract void addAll(BTree otherTree);

	public abstract void printInorder();

	public abstract void printPostorder();

	public abstract void printPreorder();

	public abstract void printLevelorder();
}
