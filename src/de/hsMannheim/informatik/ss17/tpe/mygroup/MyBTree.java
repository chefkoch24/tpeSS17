package de.hsMannheim.informatik.ss17.tpe.mygroup;

public class MyBTree implements BTree {

	private final int m;
	
	public MyBTree(int m) {
		this.m = m;
	}
	
	@Override
	public boolean insert(Integer o) {
		// TODO Auto-generated method stub
		return false;
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
		return false;
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
