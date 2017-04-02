package de.hsMannheim.informatik.ss17.tpe.mygroup;

import static gdi.MakeItSimple.*;
import static org.junit.Assert.*;

import org.junit.Test;

import de.hsMannheim.informatik.ss17.tpe.mygroup.list.*;

public class BTreeTest {
	@Test
	public final void contains() {
		MyBTree tree = new MyBTree(1);

		assertEquals(false, tree.contains(-2));

		tree.insert(5);
		tree.insert(10);
		tree.insert(4);
		tree.insert(55);
		tree.insert(76);
		tree.insert(100);
		tree.insert(1);
		tree.insert(43);
		tree.insert(22);
		tree.insert(63);
		tree.insert(23);
		tree.insert(123);
		tree.insert(5645);
		tree.insert(45);
		tree.insert(1000);
		tree.insert(12);
		tree.insert(77);
		tree.insert(137);

		assertEquals(true, tree.contains(23));
		assertEquals(true, tree.contains(123));
		assertEquals(true, tree.contains(4));
		assertEquals(false, tree.contains(24));
	}

	@Test
	public final void size() {
		MyBTree tree = new MyBTree(1);

		assertEquals(0, tree.size());

		tree.insert(50);
		tree.insert(25);
		tree.insert(100);
		tree.insert(75);
		tree.insert(70);
		tree.insert(65);
		tree.insert(20);
		tree.insert(21);
		tree.insert(22);
		tree.insert(13);
		tree.insert(12);
		tree.insert(11);
		tree.insert(101);
		tree.insert(110);
		tree.insert(113);
		tree.insert(114);
		tree.insert(55);
		tree.insert(56);
		tree.insert(57);
		tree.insert(58);

		assertEquals(20, tree.size());
		assertNotEquals(12, tree.size());
	}

	@Test
	public final void height() {
		MyBTree tree = new MyBTree(1);
		assertEquals(0, tree.size());

		tree.insert(100);
		tree.insert(110);
		tree.insert(120);
		tree.insert(150);
		tree.insert(130);
		tree.insert(140);
		tree.insert(125);
		tree.insert(160);

		assertNotEquals(6, tree.height());

		tree.insert(165);
		tree.insert(161);
		tree.insert(170);
		tree.insert(175);
		tree.insert(180);
		tree.insert(185);
		tree.insert(190);
		tree.insert(195);
		tree.insert(200);
		tree.insert(101);
		tree.insert(102);

		assertEquals(4, tree.height());

		tree.insert(201);
		tree.insert(202);
		tree.insert(203);
		tree.insert(204);
		tree.insert(205);
		tree.insert(206);
		tree.insert(207);
		tree.insert(208);
		tree.insert(209);
		tree.insert(210);
		tree.insert(211);
		tree.insert(212);
		tree.insert(213);
		tree.insert(214);
		tree.insert(215);
		tree.insert(216);
		tree.insert(217);
		tree.insert(218);
		tree.insert(219);
		tree.insert(220);

		assertEquals(5, tree.height());
	}

	@Test(expected = GDIException.class)
	public final void getMaxException() {
		MyBTree tree = new MyBTree(1);

		tree.getMax();
	}

	@Test
	public final void getMax() {
		MyBTree tree = new MyBTree(2);

		tree.insert(50);

		assertEquals(new Integer(50), tree.getMax());

		tree.insert(75);
		tree.insert(100);
		tree.insert(125);
		tree.insert(25);
		tree.insert(15);
		tree.insert(5);
		tree.insert(35);
		tree.insert(45);
		tree.insert(55);
		tree.insert(110);

		assertNotEquals(new Integer(110), tree.getMax());

		tree.insert(1050);
		tree.insert(1001);
		tree.insert(1010);

		assertEquals(new Integer(1050), tree.getMax());
	}

	@Test(expected = GDIException.class)
	public final void getMinException() {
		MyBTree tree = new MyBTree(1);

		tree.getMin();
	}

	@Test
	public final void getMin() {
		MyBTree tree = new MyBTree(2);

		tree.insert(50);

		assertEquals(new Integer(50), tree.getMin());

		tree.insert(100);
		tree.insert(40);

		assertEquals(new Integer(40), tree.getMin());

		tree.insert(24);
		tree.insert(15);
		tree.insert(43);
		tree.insert(21);
		tree.insert(12);
		tree.insert(33);
		tree.insert(22);
		tree.insert(4);
		tree.insert(3);
		tree.insert(2);
		tree.insert(1);
		tree.insert(700);

		assertEquals(new Integer(1), tree.getMin());
	}

	@Test
	public final void isEmpty() {
		MyBTree tree = new MyBTree(1);

		assertEquals(true, tree.isEmpty());

		tree.insert(12);

		assertEquals(false, tree.isEmpty());
	}

	@Test
	public final void addAll() {
		MyBTree firstTree = new MyBTree(2);
		MyBTree secondTree = new MyBTree(1);

		firstTree.addAll(secondTree);

		assertEquals(true, firstTree.isEmpty());

		secondTree.insert(12);
		secondTree.insert(20);
		secondTree.insert(2);
		secondTree.insert(3);
		secondTree.insert(5);
		secondTree.insert(6);
		secondTree.insert(10);
		secondTree.insert(13);
		secondTree.insert(17);

		firstTree.addAll(secondTree);

		assertEquals(firstTree.size(), secondTree.size());
		assertEquals(true, firstTree.contains(12));
		assertEquals(true, firstTree.contains(20));
		assertEquals(true, firstTree.contains(2));
		assertEquals(true, firstTree.contains(3));
		assertEquals(true, firstTree.contains(5));
		assertEquals(true, firstTree.contains(6));
		assertEquals(true, firstTree.contains(10));
		assertEquals(true, firstTree.contains(13));
		assertEquals(true, firstTree.contains(17));

		secondTree.addAll(firstTree);

		assertEquals(firstTree.size(), secondTree.size());

		MyBTree otherTree = new MyBTree(1);

		otherTree.insert(100);
		otherTree.insert(200);
		otherTree.insert(300);

		otherTree.addAll(secondTree);

		assertEquals(otherTree.size(), secondTree.size() + 3);
	}

	@Test
	public final void getAllElements() {
		MyBTree tree = new MyBTree(1);

		LinkedList list = tree.getAllElements();

		assertEquals(true, list.isEmpty());

		tree.insert(5);
		tree.insert(10);
		tree.insert(15);
		tree.insert(2);
		tree.insert(20);
		tree.insert(22);
		tree.insert(50);
		tree.insert(55);
		tree.insert(14);
		tree.insert(13);
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);

		list = tree.getAllElements();

		assertEquals(tree.size(), list.size());
		assertEquals(true, list.contains(5));
		assertEquals(true, list.contains(10));
		assertEquals(true, list.contains(15));
		assertEquals(true, list.contains(2));
		assertEquals(true, list.contains(20));
		assertEquals(true, list.contains(22));
		assertEquals(true, list.contains(50));
		assertEquals(true, list.contains(55));
		assertEquals(true, list.contains(14));
		assertEquals(true, list.contains(13));
		assertEquals(true, list.contains(1));
		assertEquals(true, list.contains(2));
		assertEquals(true, list.contains(3));
	}
	
	@Test
	public final void cloneTest() {
		//TODO
	}
}
