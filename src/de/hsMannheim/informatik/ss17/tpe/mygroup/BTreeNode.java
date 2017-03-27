package de.hsMannheim.informatik.ss17.tpe.mygroup;

import static gdi.MakeItSimple.*;

public class BTreeNode {
	
	private Integer[] values;
	
	private BTreeNode[] childran;
	
	public BTreeNode(int m) {
		values = new Integer[m*2+1];
		childran = new BTreeNode[m*2+1];
	}
	
	public Integer getValue(int pos) {
		if(pos < 0 || pos > values.length - 1) {
			throw new GDIException("pos out of range");
		}
		
		return values[pos];
	}
	
	public BTreeNode getChildran(int pos) {
		if(pos < 0 || pos > childran.length - 1) {
			throw new GDIException("pos out of range");
		}
		
		return childran[pos];
	}
	
	public void setValue(int pos, Integer value) {
		if(pos < 0 || pos > values.length - 1) {
			throw new GDIException("pos out of range");
		}
		
		values[pos] = value;
	}
	
	public void setChildran(int pos, BTreeNode node) {
		if(pos < 0 || pos > childran.length - 1) {
			throw new GDIException("pos out of range");
		}
		
		childran[pos] = node;
	}
}
