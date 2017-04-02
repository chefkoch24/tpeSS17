package de.hsMannheim.informatik.ss17.tpe.mygroup;

import static gdi.MakeItSimple.*;

import de.hsMannheim.informatik.ss17.tpe.mygroup.list.MyLinkedList;
import de.hsMannheim.informatik.ss17.tpe.mygroup.queue.*;

public class MyBTree implements BTree {

	private final int m;
	private BTreeNode root;

	/**
	 * @param m
	 *            is the order of the tree
	 * @throws GDIException
	 *             if m is not a natural value
	 */
	public MyBTree(int m) {
		if (m < 1) {
			throw new GDIException("m is not a natural number");
		}

		this.m = m;
	}

	/**
	 * Insert the given object to the tree
	 * 
	 * @param object
	 *            to insert
	 * @return false if the object is already in the node, else true
	 * @throws GDIException
	 *             if the object is null
	 */
	@Override
	public boolean insert(Integer object) {
		if (object == null) {
			throw new GDIException("The given object is null");
		}

		if (isEmpty()) {
			root = new BTreeNode(m);
			root.setValue(0, object);
			return true;
		} else {
			return insertRecursive(object, root);
		}
	}

	private boolean insertRecursive(Integer object, BTreeNode node) {
		for (int i = 0; i < node.getValuesCount(); ++i) {
			if (node.getValue(i) == null || node.getValue(i).compareTo(object) > 0) {
				if (node.getchildren(i) == null) {
					// we can just add it to the node
					insertToNode(object, node, i);

					if (isNodeBursted(node)) {
						split(node);
					}

					return true;
				} else {
					// there is an deeper node
					return insertRecursive(object, node.getchildren(i));
				}
			} else if (node.getValue(i).equals(object)) {
				// the same object is already in the tree
				return false;
			}
		}

		throw new GDIException("The node is full, there is someting wrong on the logic");
	}

	private boolean isNodeBursted(BTreeNode node) {
		// if the node has an object on 2m+1 the node is bursted
		return node.getValue(node.getValuesCount() - 1) != null;
	}

	private void insertToNode(Integer object, BTreeNode node, int pos) {
		// is an object already on this position?
		if (node.getValue(pos) == null) {
			// we can just add it to the given position
			node.setValue(pos, object);
		} else {
			// jump to the second last position and move all values than bigger
			// as our object one to the right to make space for the new object
			for (int i = node.getValuesCount() - 2; i >= pos; --i) {
				node.setValue(i + 1, node.getValue(i));
				node.setchildren(i + 2, node.getchildren(i + 1));

				if (i == 0) {
					// the first children has to be moved also if the inserted
					// value is the smallest in the node
					node.setchildren(1, node.getchildren(1));
				}
			}
			// set the object to the right position
			node.setValue(pos, object);
		}
	}

	private void split(BTreeNode node) {
		BTreeNode leftNode = new BTreeNode(m);
		BTreeNode rightNode = new BTreeNode(m);

		if (node == root) {
			BTreeNode newRoot = new BTreeNode(m);

			// add the object in the middle to the new root
			newRoot.setValue(0, root.getValue(m));

			newRoot.setchildren(0, leftNode);
			newRoot.setchildren(1, rightNode);

			// split the root objects into two new nodes
			splitIntoNodes(node, leftNode, rightNode);

			root = newRoot;
		} else {
			BTreeNode motherNode = findMotherNode(root, node);

			if (motherNode == null) {
				throw new GDIException("no mother node found");
			}

			// find the right position to insert the object in the middle
			for (int i = 0; i < motherNode.getValuesCount(); ++i) {
				if (motherNode.getchildren(i) == node) {
					insertToNode(node.getValue(m), motherNode, i);
					motherNode.setchildren(i, leftNode);
					motherNode.setchildren(i + 1, rightNode);
				}
			}

			// split the root objects into two new nodes
			splitIntoNodes(node, leftNode, rightNode);

			// we have to split again if the mother node is bursted to
			if (isNodeBursted(motherNode)) {
				split(motherNode);
			}
		}
	}

	private void splitIntoNodes(BTreeNode node, BTreeNode leftNode, BTreeNode rightNode) {
		// split the objects from the root into two new nodes
		for (int i = 0; i < m; ++i) {
			leftNode.setValue(i, node.getValue(i));
			leftNode.setchildren(i, node.getchildren(i));
			rightNode.setValue(i, node.getValue(m + 1 + i));
			rightNode.setchildren(i + 1, node.getchildren(m + 2 + i));
		}

		// the children m and m+1 are between the object for the new root
		leftNode.setchildren(m, node.getchildren(m));
		rightNode.setchildren(0, node.getchildren(m + 1));
	}

	private BTreeNode findMotherNode(BTreeNode node, BTreeNode children) {
		if (node == null) {
			return null;
		}

		// search in all children of the node to identify the mother node
		for (int i = 0; i < node.getChildrenCount(); ++i) {
			// we have found the mother node
			if (node.getchildren(i) == children) {
				return node;
			} else {
				// we have to search also children of the children of the node
				BTreeNode motherNode = findMotherNode(node.getchildren(i), children);

				// we have found the mother node
				if (motherNode != null) {
					return motherNode;
				}
			}
		}

		// mother node not found in this branch
		return null;
	}

	/**
	 * Insert all values from the file to the tree
	 * 
	 * @param filename
	 *            of the file
	 * @return false if the file not exists, else true
	 */
	@Override
	public boolean insert(String filename) {
		// check if the file exist if not there is nothing to insert
		if (!isFilePresent(filename)) {
			// file not exist
			return false;
		}

		Object file = openInputFile(filename);

		// insert all values from the file in the current tree
		while (!isEndOfInputFile(file)) {
			int value = readInt(file);
			insert(value);
		}
		closeInputFile(file);

		return true;
	}

	/**
	 * Search through the complete tree to find the given object
	 * 
	 * @param object
	 *            to find in the tree
	 * @return true if the object is in the tree, else fase
	 */
	@Override
	public boolean contains(Integer object) {
		return containsRecursive(object, root);
	}

	private boolean containsRecursive(Integer object, BTreeNode node) {
		// the object is not in the tree
		if (node == null) {
			return false;
		}

		// check every value in the node until 2m - the last object in the node
		// is null
		for (int i = 0; i < node.getChildrenCount() - 1; ++i) {
			if (node.getValue(i) == null || node.getValue(i).compareTo(object) > 0) {
				// we have to go deeper in the tree
				return containsRecursive(object, node.getchildren(i));
			} else if (node.getValue(i).equals(object)) {
				// we found the object in the tree
				return true;
			}
		}

		// the last object is null so we have to search on link 2m
		return containsRecursive(object, node.getchildren(2 * m));
	}

	/**
	 * Get you the number of elements they are in the tree
	 * 
	 * @return Integer the size
	 */
	@Override
	public int size() {
		BTreeNode node = root;
		int size = 0;
		// tree is empty
		if (node == null) {
			return size;
		} else {
			// Save all nodes in a queue to count all nodesizes and the sum of
			// them are the treesize
			Queue queue = new Queue();
			queue.enter(node);
			while (!queue.isEmpty()) {
				node = queue.leave();
				size += countElementsInNode(node);
				for (int i = 0; i < node.getValuesCount(); i++) {
					if (node.getchildren(i) != null) {
						queue.enter(node.getchildren(i));
					} else {
						if (node.getchildren(i) != null)
							queue.enter(node.getchildren(++i));
					}
				}
			}
		}
		return size;
	}

	private int countElementsInNode(BTreeNode node) {
		int elementsInNode = 0;
		// count all elements in the node
		for (int i = 0; i < node.getValuesCount(); i++) {
			if (node.getValue(i) != null) {
				elementsInNode++;
			}
		}
		return elementsInNode;

	}

	/**
	 * Get you the height of the tree
	 * 
	 * @return Integer the height
	 */
	@Override
	public int height() {
		return height(root);
	}

	private int height(BTreeNode node) {
		if (node == null)
			return 0;
		else {
			return 1 + height(node.getchildren(0));
		}
	}

	/**
	 * Get you the maximal object in the tree
	 * 
	 * @return Integer maximal object
	 * 
	 * @throws GDIException
	 *             if the tree is empty
	 */
	@Override
	public Integer getMax() {
		BTreeNode node = root;
		// tree is empty
		if (node == null) {
			throw new GDIException("the tree is empty");
		} else {
			Integer value = 0;
			// check if we are on a leaf of the tree
			boolean onLeaf = false;
			// search in every node for the biggest element an the children of
			// it
			for (int i = 0; i < node.getValuesCount() && onLeaf == false; i++) {
				if (node.getValue(i) == null || i == node.getValuesCount() - 1) {
					// node is full of element, the max is in the right node of
					// it
					if (node.getchildren(i) != null) {
						node = node.getchildren(i);
						i = 0;
						// the node is not full of elements, we step back to get
						// the right child
					} else if (node.getchildren(i - 1) != null) {
						node = node.getchildren(i - 1);
						i = 0;
						// on the right leaf and get the maximal element
					} else {
						value = node.getValue(i - 1);
						onLeaf = true;

					}
				}
			}
			return value;
		}
	}

	/**
	 * Get you the minimal object in the tree
	 * 
	 * @return Integer minimal object
	 * 
	 * @throws GDIException
	 *             if the tree is empty
	 */
	@Override
	public Integer getMin() {
		BTreeNode node = root;
		// empty tree
		if (node == null) {
			throw new GDIException("the tree is empty");
		}
		// search for the value at the smallest value on the left side in the
		// tree
		while (node.getchildren(0) != null) {
			node = node.getchildren(0);
		}
		return node.getValue(0);
	}

	/**
	 * check if the tree is empty or not
	 * 
	 * @return true if the tree is empty else false
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Insert all objects of the given tree to the tree as an shallow copy
	 * 
	 * @param otherTree
	 *            with the object to insert
	 */
	@Override
	public void addAll(BTree otherTree) {
		MyLinkedList objects = otherTree.getAllElements();

		for (int i = 0; i < objects.size(); ++i) {
			insert(objects.get(i));
		}
	}

	@Override
	public void printInorder() {
		printInorder(root);

	}

	private void printInorder(BTreeNode node) {
		if (node == null) {
			return;
		} else {
			for (int i = 0; i <= node.getValuesCount(); i++) {
				printPostorder(node.getchildren(i));
			}
			printNode(node);
		}
	}

	// Funktioniert wenn Baum richtig definiert ist
	@Override
	public void printPostorder() {
		printPostorder(root);
	}

	private void printPostorder(BTreeNode node) {
		if (node == null) {
			return;
		} else {
			for (int i = 0; i <= node.getValuesCount(); i++) {
				printPostorder(node.getchildren(i));
			}
			printNode(node);
		}
	}

	@Override
	public void printPreorder() {
		// TODO Auto-generated method stub

	}

	// Funktioniert wenn Baum richtig definiert ist
	@Override
	public void printLevelorder() {
		printLevelorder(root);

	}

	private void printLevelorder(BTreeNode node) {

		if (node == null) {
			return;
		} else {
			BTreeNode temp = node;
			Queue queue = new Queue();
			queue.enter(node);
			while (!queue.isEmpty()) {
				temp = queue.leave();
				printNode(temp);
				for (int i = 0; i < temp.getValuesCount(); i++) {
					if (temp.getchildren(i) != null) {
						queue.enter(temp.getchildren(i));
					} else {
						if (temp.getchildren(i) != null)
							queue.enter(temp.getchildren(++i));
					}
				}
			}
		}

	}

	private void printNode(BTreeNode node) {
		for (int i = 0; i < node.getValuesCount(); i++) {
			if (node.getValue(i) != null) {
				Integer value = node.getValue(i);
				print(value + ", ");
			}
		}
	}

	/**
	 * Return all Objects in the tree in a list
	 * 
	 * @return MyLinkedList containing all objects in the tree
	 */
	@Override
	public MyLinkedList getAllElements() {
		MyLinkedList list = new MyLinkedList();

		// insert all objects to the list
		getAllElementsRecursive(list, root);

		return list;
	}

	private void getAllElementsRecursive(MyLinkedList list, BTreeNode node) {
		if (node == null) {
			return;
		}

		// add all objects to the list
		for (int i = 0; i < node.getValuesCount(); ++i) {
			if (node.getValue(i) != null) {
				list.addLast(node.getValue(i));
			}
		}

		// go one level deeper in the tree
		for (int i = 0; i < node.getChildrenCount(); ++i) {
			getAllElementsRecursive(list, node.getchildren(i));
		}

	}

	/**
	 * Return a deep copy of the tree
	 * 
	 * @return MyBTree a copy of the tree
	 */
	@Override
	public MyBTree clone() {
		MyLinkedList objects = getAllElements();

		MyBTree tree = new MyBTree(m);

		for (int i = 0; i < objects.size(); ++i) {
			// insert all objects as an deep copy
			tree.insert(new Integer(objects.get(i).intValue()));
		}

		return tree;
	}
}
