package de.hsMannheim.informatik.ss17.tpe.mygroup;

import static gdi.MakeItSimple.*;

public class MyBTree implements BTree {

	private final int m;
	private BTreeNode root;

	public MyBTree(int m) {
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
			rightNode.setchildren(i, node.getchildren(m + 2 + i));
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

	// Sonderfall falls es die Wurzel ist fehlt noch
	@Override
	public boolean contains(Integer o) {
		BTreeNode temp = root;
		// while the temp Node is not null is it possible that the Integer is in
		// the tree
		while (temp != null) {
			// Initialize with 0 in every loop, because we have to search from
			// the first to the last object in one node
			int posCounter = 0;
			// get the right element at the first position
			if (temp.getValue(posCounter).intValue() == o.intValue()) {
				return true;
				// search in the left child of the object because our value is
				// smaller than the current value
			} else if (temp.getValue(posCounter).intValue() > o.intValue()) {
				temp = temp.getchildren(posCounter);
			} else {
				// search in the same node further because our value is bigger
				// than the current value in the node
				while (posCounter < 2 * m) {
					posCounter++;
					// find it
					if (temp.getValue(posCounter).intValue() == o.intValue()) {
						return true;
						// search in the left child because our value is smaller
						// than the current value
					} else if (temp.getValue(posCounter).intValue() > o.intValue()) {
						temp = temp.getchildren(posCounter);
					}
				}
				// value is bigger than all elements in the node we need the
				// right child
				if (posCounter == 2 * m) {
					temp = temp.getchildren(posCounter);
				}
			}
		}
		return false;

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

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

	@Override
	public Integer getMax() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getMin() {
		BTreeNode node = root;
		while(node.getchildren(0)!= null){
			node = node.getchildren(0);
		}
		return node.getValue(0);
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void addAll(BTree otherTree) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printInorder() {
		printInorder(root);

	}
	
	private void printInorder(BTreeNode node){
		if(node == null){
			return;
		} else {
			int pos = 0;
			while (pos <= node.getValuesCount()){
				if(pos < node.getValuesCount()){
					printInorder(node.getchildren(pos));
					pos++;
				}
				printNode(node);
				if(pos == node.getValuesCount()){
					printInorder(node.getchildren(pos));
				}
			}
		}
	}

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
			while(!queue.isEmpty()){
				temp = queue.leave();	
				printNode(temp);
				for (int i = 0; i < temp.getValuesCount(); i++) {
						if(temp.getchildren(i)!= null){
						queue.enter(temp.getchildren(i));
					} else {
						if(temp.getchildren(i) != null)
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

	@Override
	public BTree clone() {
		return this;
	}
}
