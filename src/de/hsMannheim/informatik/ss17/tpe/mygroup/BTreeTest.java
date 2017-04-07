package de.hsMannheim.informatik.ss17.tpe.mygroup;

import static gdi.MakeItSimple.*;
import static org.junit.Assert.*;
import org.junit.*;
import de.hsMannheim.informatik.ss17.tpe.mygroup.list.*;

public class BTreeTest {
	
	BTree tree;
	BTree emptyTree;

	@Before
	public void setUp(){
		tree = new MyBTree(2);
		emptyTree = new MyBTree(1);
		
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
	}
	
	@Test
	public final void contains() {

		assertEquals(false, tree.contains(-2));

		assertEquals(true, tree.contains(23));
		assertEquals(true, tree.contains(123));
		assertEquals(true, tree.contains(4));
		assertEquals(false, tree.contains(24));
	}

	@Test
	public final void size() {

		assertEquals(0, emptyTree.size());
		assertNotEquals(0, tree.size());
		assertEquals(18, tree.size());
		assertNotEquals(12, tree.size());
	}

	@Test
	public final void height() {
		
		assertEquals(0, emptyTree.height());
		assertNotEquals(0, tree.height());
		assertNotEquals(6, tree.height());
		assertEquals(2, tree.height());

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

		assertEquals(3, tree.height());

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

		assertEquals(3, tree.height());
	}

	@Test(expected = GDIException.class)
	public final void getMaxException() {

		emptyTree.getMax();
	}

	@Test
	public final void getMax() {

		assertEquals(new Integer(5645), tree.getMax());

		tree.insert(6000);
		tree.insert(99);
		tree.insert(1250);
		tree.insert(25);

		assertNotEquals(new Integer(99), tree.getMax());

		assertEquals(new Integer(6000), tree.getMax());
	}

	@Test(expected = GDIException.class)
	public final void getMinException() {

		emptyTree.getMin();
	}

	@Test
	public final void getMin() {

		assertEquals(new Integer(1), tree.getMin());
		assertNotEquals(new Integer(40), tree.getMin());
		tree.insert(0);
		assertNotEquals(new Integer(1), tree.getMin());
		assertEquals(new Integer(0), tree.getMin());
		
	}

	@Test
	public final void isEmpty() {

		assertEquals(true, emptyTree.isEmpty());
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

		LinkedList list = emptyTree.getAllElements();

		assertEquals(true, list.isEmpty());

		list = tree.getAllElements();

		assertEquals(tree.size(), list.size());
		assertEquals(true, list.contains(4));
		assertEquals(true, list.contains(10));
		assertEquals(true, list.contains(1));
		assertEquals(true, list.contains(5645));
	}
	
	@Test
	public final void cloneTest() {
		
		MyBTree tree = new MyBTree(2);
		MyBTree copiedTree = new MyBTree(1);
		copiedTree = tree.clone();
		
		assertEquals(true, copiedTree.isEmpty());
		tree.insert(1000);
		tree.insert(10);
		tree.insert(100);
		tree.insert(2);
		tree.insert(5);
		
		copiedTree = tree.clone();
		assertEquals(tree.size(), copiedTree.size());
		assertEquals(tree.getMin(), copiedTree.getMin());
		assertEquals(tree.getMax(), copiedTree.getMax());
		assertEquals(true, tree.contains(new Integer (1000)));
		assertEquals(true, copiedTree.contains(new Integer (1000)));
		
		tree.insert(47);
		assertNotEquals(tree.size(), copiedTree.size());
		assertEquals(false, copiedTree.contains(new Integer (47)));
		
	}
}
