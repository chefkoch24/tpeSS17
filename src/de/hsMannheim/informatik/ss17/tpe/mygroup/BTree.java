package de.hsMannheim.informatik.ss17.tpe.mygroup;

public interface BTree {
	public abstract boolean insert(Integer o);
	
	public abstract String insert(String filename);
	
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
