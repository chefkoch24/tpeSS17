package de.hsMannheim.informatik.ss17.tpe.mygroup;

import static gdi.MakeItSimple.*;

public class BTreeNode {
	
	private Integer[] values;
	
	private BTreeNode[] children;
	
	public BTreeNode(int degree) {
		values = new Integer[degree*2+1];
		children = new BTreeNode[degree*2+2];
	}
	
	public Integer getValue(int pos) {
		if(pos < 0 || pos > values.length - 1) {
			throw new GDIException("pos out of range");
		}
		
		return values[pos];
	}
	
	public BTreeNode getChildren(int pos) {
		if(pos < 0 || pos > children.length - 1) {
			throw new GDIException("pos out of range");
		}
		
		return children[pos];
	}
	
	public void setValue(int pos, Integer value) {
		if(pos < 0 || pos > values.length - 1) {
			throw new GDIException("pos out of range");
		}
		
		values[pos] = value;
	}
	
	public void setchildren(int pos, BTreeNode node) {
		if(pos < 0 || pos > children.length - 1) {
			throw new GDIException("pos out of range");
		}
		
		children[pos] = node;
	}
	
	public int getValuesCount() {
		return values.length;
	}
	
	public int getChildrenCount() {
		return children.length;
	}
}
