/**
 * 
 */
package redblack;

import java.util.NoSuchElementException;


/**
 * Left-leaning, red-black binary search tree implementation.
 * Citation: Implementation based on Algorithms course by Robert Sedgewick and Kevin Wayne.
 * @param <E> Generic type
 * @author Riley Harris
 *
 */
public class RedBlackBST<E extends Comparable<E>> {
	/** Boolean value representing red link color */
	private static final boolean RED = true;
	/** Boolean value representing black link color */
	private static final boolean BLACK = false; 
	/** Size of tree */ 
	private int size;
	/** Root of tree */
	private Node root;
	
	/**
	 * Constructs the Red-Black BST.
	 */
	public RedBlackBST() {
		size = 0;
		root = null;
	}
	
	/**
	 * Size of BST. 
	 * @return size as an integer
	 */
	public int size() {
		return size; 
	}
	
	/**
	 * Adds specified data into the tree.
	 * @param data Data to add
	 * @throws IllegalArgumentException if the data to add is null 
	 */
	public void add(E data) {
		if (data == null) {
			throw new IllegalArgumentException("Cannot add null data.");
		}
		root = add(root, data);
		size++;
	}
	
	/**
	 * Helper method to recursively search for the position to add the data.
	 * @param node Node to search with 
	 * @param data Data to add 
	 * @return new reference to Node 
	 */
	private Node add(Node node, E data) {
		if (node == null) {
			return new Node(data);
		}
		int comp = data.compareTo(node.data);
		Node copy = node;
		if (comp < 0) {
			node.left = add(node.left, data);
		} else if (comp > 0) {
			node.right = add(node.right, data);
		}
		if (isRed(copy.right) && !isRed(copy.left)) {
			copy = rotateLeft(copy);
		}
		if (isRed(copy.left) && isRed(copy.left.left)) {
			copy = rotateRight(copy);
		}
		if (isRed(copy.right) && isRed(copy.left)) {
			flipColors(copy);
		}
//		if (isRed(node.left) && isRed(node.left.right)) {
//			//node = rotateLeft(node);
//			//node = rotateRight(node);
//			return rotateLeft(node);
//		}
		return copy;
	}
	
	/**
	 * Returns whether or not a node's parent link is RED. 
	 * Note: does allow for a null parameter, important as part of the check. 
	 * @param node Node to evaluate
	 * @return true if Node's parent link is RED, false if not
	 */
	private boolean isRed(Node node) {
		if (node == null) {
			return false;
		}
		return node.color == RED;
	}

	/**
	 * Rotates a nodes right link to the left.
	 * @param node Node to rotate
	 * @return rotated node
	 */
	private Node rotateRight(Node node) {
		Node copyLeft = node.left;
		node.left = copyLeft.right;
		copyLeft.right = node;
		copyLeft.color = node.color;
		node.color = RED;
		return copyLeft;
	}

	/**
	 * Flips a nodes left and right colors to black. 
	 * @param node Node to flip
	 * @return reference to flipped node 
	 */
	private void flipColors(Node node) {
		node.color = RED;
		node.right.color = BLACK;
		node.left.color = BLACK;
		//return node;
	}

	/**
	 * Rotates a nodes right link to left.
	 * @param node Node to rotate
	 * @return rotated node
	 */
	private Node rotateLeft(Node node) {
		Node copyRight = node.right;
		node.right = copyRight.left;
		copyRight.left = node;
		copyRight.color = node.color;
		node.color = RED;
		return copyRight;
	}

	/**
	 * Returns whether or not the specified data is contained in the tree.
	 * @param data Data to search for
	 * @return true if found, false if not
	 */
	public boolean contains(E data) {
		return contains(root, data);
	}
	
	/**
	 * Helper method to recursively search for data. 
	 * @param node Current node
	 * @param data Data to search for
	 * @return true if found, false if not
	 * @throws IllegalArgumentException if data is null
	 */
	private boolean contains(Node node, E data) {
		if (data == null) {
			throw new IllegalArgumentException("Data specified is null.");
		}
		if (node == null) { // Base case
			return false;
		} else if (data.compareTo(node.data) == 0) { // Base case 2 [found node]
			return true;
		} else { // Recursive case
			if (data.compareTo(node.data) < 0) { // go left
				return contains(node.left, data);
			} else { // greater than 0, go right
				return contains(node.right, data);
			}
		}
	}
	

	/**
	 * Finds and returns the minimum element in the BST.
	 * @return Minimum element
	 * @throws IllegalArgumentException if called with no elements in tree
	 */
	public E getMin() {
		if (size == 0) {
			throw new IllegalArgumentException("No elements in tree.");
		}
		return getMin(root);
	}
	
	/**
	 * Helper method that recursively searches for minimum element. 
	 * @param node Node to begin search with
	 * @return E minimum element
	 */
	private E getMin(Node node) {
		if (node.left == null) { // Base case 
			return node.data;
		} else { // Recursive case 
			return getMin(node.left);
		}
	}
	

	/**
	 * Deletes the min element of the BST.
	 * @throws IllegalArgumentException if there are no elements in the tree
	 */
	public void deleteMin() {
		if (size == 0) {
			throw new IllegalArgumentException("No elements in tree.");
		}
		root = deleteMin(root);
		size--;
	}
	
	/**
	 * Helper method that recursively searches for min to delete. 
	 * @param node Node to search with 
	 * @return updated node reference 
	 */
	private Node deleteMin(Node node) {
		if (node.left == null) { // Base case
			return node.right;
		} else { // Recursive case 
			node.left = deleteMin(node.left);
			return node;
		}
	}
	
	/**
	 * Deletes the specified data.
	 * @param data Data to delete
	 * @throws NoSuchElementException if data does not exist
	 */
	public void delete(E data) {
		if (!contains(data)) {
			throw new NoSuchElementException("There is no such element in tree.");
		}
		root = delete(root, data);
		size--;
	}
	
	/**
	 * Utilizes Hibbard deletion to delete the specified data.
	 * @param node Node to search with
	 * @param data Data to search with
	 * @return new Node reference
	 */
	private Node delete(Node node, E data) {
		if (data.compareTo(node.data) == 0) { // Base case 
			if (node.right == null && node.left == null) { // No children
				return null;
			} else if (node.right == null) { // One left child
				return node.left;
			} else if (node.left == null) { // One right child
				return node.right;
			} else { // Two children
				E temp = getMin(node.right);
				node.data = temp;
				node.right = deleteMin(node.right);
				return node;
			}
		} else if (data.compareTo(node.data) < 0) { // Recursive Case
			node.left = delete(node.left, data);
			return node;
		} else { // data greater than node data (Also Recursive Case) 
			node.right = delete(node.right, data);
			return node;
		}
	}
	
	/**
	 * Node containing references to left and right children. 
	 * Additionally, features boolean color value. 
	 * Contains generic data. 
	 * @author Riley Harris
	 *
	 */
	private class Node {

		/** Reference to left node */
		private Node left; 
		/** Reference to right node */
		private Node right; 
		/** Data of node */ 
		private E data; 
		/** Color of parent link */
		private boolean color;
		
		/**
		 * Constructs node with null right/left references and specified data.
		 * @param data Data of node
		 */
		private Node(E data) {
			this.data = data;
			left = null;
			right = null;
			color = RED;
		}
		
	}
}
