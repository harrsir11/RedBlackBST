/**
 * 
 */
package redblack;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests RedBlackBST methods. 
 * @author Riley Harris
 *
 */
public class RedBlackBSTTest {

	/**
	 * Tests add method with integers. 
	 */
	@Test
	public void testAddAndContains() {
		// Create empty redblackBST
		RedBlackBST<Integer> redblackBST = new RedBlackBST<>();
		assertEquals(0, redblackBST.size());
		redblackBST.add(15);
		assertEquals(1, redblackBST.size());
		assertTrue(redblackBST.contains(15));
		redblackBST.add(8);
		assertEquals(2, redblackBST.size());
		assertTrue(redblackBST.contains(8));
		redblackBST.add(18);
		assertEquals(3, redblackBST.size());
		assertTrue(redblackBST.contains(18));
	}
	
	/**
	 * Tests getMin method with integers.
	 */
	@Test
	public void testGetMin() {
		// create and setup redblackBST 
		RedBlackBST<Integer> redblackBST = new RedBlackBST<>();
		assertEquals(0, redblackBST.size());
		redblackBST.add(20);
		redblackBST.add(15);
		redblackBST.add(23);
		redblackBST.add(28);
		redblackBST.add(10);
		redblackBST.add(17);
		redblackBST.add(10);
		// test get min
		assertTrue(10 == redblackBST.getMin());
		
		// test after adding new min
		redblackBST.add(3);
		assertTrue(3 == redblackBST.getMin());
	}
	
	/**
	 * Tests deleteMin method with integers.
	 */
	@Test
	public void testDeleteMin() {
		// create and setup redblackBST 
		RedBlackBST<Integer> redblackBST = new RedBlackBST<>();
		assertEquals(0, redblackBST.size());
		redblackBST.add(20);
		redblackBST.add(10);
		redblackBST.add(11);
		redblackBST.add(25);
		redblackBST.add(5);
		assertEquals(5, redblackBST.size());
		
		// test delete min
		assertTrue(5 == redblackBST.getMin());
		assertTrue(redblackBST.contains(5));
		redblackBST.deleteMin();
		assertFalse(redblackBST.contains(5));
		assertEquals(4, redblackBST.size());
		assertTrue(10 == redblackBST.getMin());
		
		// test deleting again
		assertTrue(10 == redblackBST.getMin());
		assertTrue(redblackBST.contains(10));
		redblackBST.deleteMin();
		assertFalse(redblackBST.contains(10));
		assertEquals(3, redblackBST.size());
		assertTrue(11 == redblackBST.getMin());
		
		// test deleting again
		assertTrue(11 == redblackBST.getMin());
		assertTrue(redblackBST.contains(11));
		redblackBST.deleteMin();
		assertFalse(redblackBST.contains(11));
		assertEquals(2, redblackBST.size());
		assertTrue(20 == redblackBST.getMin());
		
		// test deleting again
		assertTrue(20 == redblackBST.getMin());
		assertTrue(redblackBST.contains(20));
		redblackBST.deleteMin();
		assertFalse(redblackBST.contains(20));
		assertEquals(1, redblackBST.size());
		assertTrue(25 == redblackBST.getMin());
		
		// test deleting again
		assertTrue(25 == redblackBST.getMin());
		assertTrue(redblackBST.contains(25));
		redblackBST.deleteMin();
		assertFalse(redblackBST.contains(25));
		assertEquals(0, redblackBST.size());
	}
	
	/**
	 * Tests delete(data) method.
	 */
	@Test
	public void testDelete() {
		// create and setup redblackBST
		RedBlackBST<Integer> redblackBST = new RedBlackBST<>();
		assertEquals(0, redblackBST.size());
		redblackBST.add(20);
		redblackBST.add(10);
		redblackBST.add(11);
		redblackBST.add(25);
		redblackBST.add(5);
		assertEquals(5, redblackBST.size());
		
		// test deleting node with no children
		redblackBST.delete(5);
		assertEquals(4, redblackBST.size());
		assertFalse(redblackBST.contains(5));
		assertTrue(10 == redblackBST.getMin());
		
		// test deleting node with one child
		redblackBST.delete(10);
		assertEquals(3, redblackBST.size());
		assertFalse(redblackBST.contains(10));
		assertTrue(11 == redblackBST.getMin());
		
		// test deleting node with two children
		redblackBST.delete(20);
		assertEquals(2, redblackBST.size());
		assertFalse(redblackBST.contains(20));
		assertTrue(11 == redblackBST.getMin());
		
	}

}
