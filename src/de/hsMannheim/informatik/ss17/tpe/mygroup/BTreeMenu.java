package de.hsMannheim.informatik.ss17.tpe.mygroup;

import static gdi.MakeItSimple.*;

public class BTreeMenu {

	private static MyBTree[] trees = new MyBTree[3];
	private static MyBTree workingTree;
	private static final String FILE_PATH = "src/de/hsMannheim/informatik/ss17/tpe/mygroup/values.txt";

	public static void main(String[] args) {
		// Initalize BTree array
		trees[0] = new MyBTree(1);
		trees[1] = new MyBTree(2);
		trees[2] = new MyBTree(3);
		workingTree = trees[0];

		int input = -1;
		while (input != 30) {
			printMenu();
			try {
				input = readInt();
				executeCommand(input);
			} catch (Exception ex) {
				println(ex.getMessage());
				readLine();
				input = -1;
			}
		}
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
		println("15\t-> change tree");
		println("30\t-> exit");
	}

	private static void executeCommand(int cmd) {
		switch (cmd) {
		case 1:
			print("value: ");
			print(workingTree.insert(readInt()));
			break;
		case 2:
			print(workingTree.insert(FILE_PATH));
			break;
		case 3:
			print("value to search: ");
			print(workingTree.contains(readInt()));
			break;
		case 4:
			print(workingTree.size());
			break;
		case 5:
			print(workingTree.height());
			break;
		case 6:
			print(workingTree.getMax());
			break;
		case 7:
			print(workingTree.getMin());
			break;
		case 8:
			print(workingTree.isEmpty());
			break;
		case 9:
			print("Index of the tree to add: ");
			workingTree.addAll(trees[readInt()]);
			break;
		case 10:
			workingTree.printInorder();
			break;
		case 11:
			workingTree.printPostorder();
			break;
		case 12:
			workingTree.printPreorder();
			break;
		case 13:
			workingTree.printLevelorder();
			break;
		case 14:
			print("index to clone: ");
			int index = readInt();
			if(index > 0 && index < 3)
				trees[index] = workingTree.clone();
			else
				print("other index");
			break;
		case 15:
			print("index: ");
			workingTree = trees[readInt()];
			break;
		case 30: // exit the program
			break;
		default:
			println("unknown command");
		}
		readLine();
		readLine();
	}
}
