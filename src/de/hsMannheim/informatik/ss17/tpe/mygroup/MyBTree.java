package de.hsMannheim.informatik.ss17.tpe.mygroup;

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
				if (node.getChildran(i) == null) {
					// we can just add it to the node
					insertToNode(o, node, i);
					return true;
				} else {
					// there is an deeper node
					return insertRecursive(o, node.getChildran(i));
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
	public String insert(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Integer o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
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
