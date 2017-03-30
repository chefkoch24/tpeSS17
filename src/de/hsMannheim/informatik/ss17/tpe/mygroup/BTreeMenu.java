package de.hsMannheim.informatik.ss17.tpe.mygroup;

import static gdi.MakeItSimple.*;

public class BTreeMenu {
	public static void main(String[] args) {
		MyBTree tree = new MyBTree(2);
		
		tree.insert("src/de/hsMannheim/informatik/ss17/tpe/mygroup/values.txt");
		
		tree.printPostorder();
		println();
		tree.printLevelorder();
		println();
		println("Min " + tree.getMin());
		println("Height " + tree.height());
		println("Max " + tree.getMax());
		println("Size " + tree.size());

	}
	
	private static void printMenu() {
		println("1\t-> insert(Integer o)");
		println("2\t-> insert(String filename)");
		println("3\t-> contains(Integer o)");
		println("4\t-> size()");
		println("5\t-> height()");
		println("6\t-> getMax()");
		println("7\t-> getMin()");
		println("8\t-> isEmpty()");
		println("9\t-> addAll(BTree otherTree)");
		println("10\t-> printInorder()");
		println("11\t-> printPostorder()");
		println("12\t-> printPreorder()");
		println("13\t-> printLevelorder()");
		println("14\t-> clone()");
		println("30\t-> exit");
	}
}
