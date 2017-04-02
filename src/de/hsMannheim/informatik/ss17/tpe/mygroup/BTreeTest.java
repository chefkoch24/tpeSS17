package de.hsMannheim.informatik.ss17.tpe.mygroup;

import static org.junit.Assert.*;

import org.junit.Test;

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
}
