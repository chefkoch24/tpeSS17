package de.hsMannheim.informatik.ss17.tpe.mygroup;

import static gdi.MakeItSimple.*;

public class MyBTree implements BTree {

	private final int m;
	private BTreeNode root;

	public MyBTree(int m) {
		this.m = m;
	}

	@Override
	public boolean insert(Integer o) {
		if (isEmpty()) {
			root = new BTreeNode(m);
			root.setValue(0, o);
			return true;
		} else {
			return insertRecursive(o, root);
		}
	}

	private boolean insertRecursive(Integer o, BTreeNode node) {
		for (int i = 0; i < node.getValuesCount(); ++i) {
			if (node.getValue(i) == null || node.getValue(i).compareTo(o) > 0) {
				if (node.getchildren(i) == null) {
					// we can just add it to the node
					insertToNode(o, node, i);
					return true;
				} else {
					// there is an deeper node
					return insertRecursive(o, node.getchildren(i));
				}
			} else if (node.getValue(i).equals(o)) {
				// the same object is already in the tree
				return false;
			}
		}
		// this will never happen :/
		return false;
	}

	private void insertToNode(Integer o, BTreeNode node, int pos) {
		// is an object already on this position?
		if (node.getValue(pos) == null) {
			// we can just add it to the given position
			node.setValue(pos, o);
		} else {
			// jump to the second last position and move all values than bigger
			// as our object one to the right to make space for the new object
			for (int i = node.getValuesCount() - 2; i >= pos; --i) {
				if (node.getValue(i) != null) {
					node.setValue(i + 1, node.getValue(i));
				}
			}
			// set the object to the right position
			node.setValue(pos, o);
		}
	}

	@Override
	public boolean insert(String filename) {
		// check if the file exist if not there is nothing to insert
		if (!isFilePresent(filename)) {
			// file not exist
			return false;
		} else {
			Object file = openInputFile(filename);
			// insert all integer from the file in the current tree
			while (isEndOfInputFile(file) == false) {
				int value = readInt(file);
				insert(value);
			}
			return true;
		}
	}

	// Sonderfall falls es die Wurzel ist fehlt noch 
	@Override
	public boolean contains(Integer o) {
		BTreeNode temp = root;
		// while the temp Node is not null is it possible that the Integer is in the tree
		while (temp != null) {
			// Initialize with 0 in every loop, because we have to search from the first to the last object in one node 
			int posCounter = 0;
			// get the right element at the first position
			if (temp.getValue(posCounter).intValue() == o.intValue()) {
				return true;
				// search in the left child of the object because our value is smaller than the current value
			} else if (temp.getValue(posCounter).intValue() > o.intValue()) {
				temp = temp.getchildren(posCounter);
			} else {
				// search in the same node further because our value is bigger than the current value in the node
				while (posCounter < 2*m) {
					posCounter++;
					// find it
					if (temp.getValue(posCounter).intValue() == o.intValue()) {
						return true;
						// search in the left child because our value is smaller than the current value
					} else if (temp.getValue(posCounter).intValue() > o.intValue()) {
						temp = temp.getchildren(posCounter);
					}
				}
				// value is bigger than all elements in the node we need the right child
				if (posCounter == 2*m) {
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
	
	private int height(BTreeNode node){
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return root == null;
	}

	@Override
	public void addAll(BTree otherTree) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printInorder() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printPostorder() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printPreorder() {
		// TODO Auto-generated method stub

	}
	

	@Override
	public void printLevelorder() {
		// TODO Auto-generated method stub

	}

	@Override
	public BTree clone() {
		return this;
	}
}
