package binary_tree;

import java.util.Stack;

import javax.xml.soap.Node;

/**
 * 
 * @author CPSC 3200
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {
	private BTNode<E> root;
	private int size = 0;

	public LinkedBinaryTree() {
		root = null;
		size = 0;
	}

	/**
	 * This method gets the height of the tree from the current up to the root
	 * 
	 * @param aNode used for recursion. Pass this method root
	 * @return the height
	 */
	public int getHeight(BTNode<E> aNode) {
		if (aNode == null)
			return 0;
		else
			return 1 + Math.max(getHeight(aNode.getLeft()),
					getHeight(aNode.getRight()));
	}

	/**
	 * Method will search the tree for a node with a given element, and return the BTNode object containing that element
	 * @param elem the element we want to find
	 * @param aNode used for recursion. pass this method root
	 * @return the BTNode object if it is found, null if it is not found
	 */
	public BTNode<E> search(E elem, BTNode<E> aNode) {
		Comparable<E> elemTemp = (Comparable<E>) elem;
		
		if (isExternal(aNode)) 
			return aNode; 
		if (elemTemp.compareTo(aNode.getElement()) < 0)
			return search(elem, aNode.getLeft()); 
		else if (elemTemp.compareTo(aNode.getElement())==0)
			return aNode; 
		else if (elemTemp.compareTo(aNode.getElement()) > 0)
			return search(elem, aNode.getRight());
		else {
			return null;
		}
		
	}

	/**
	 * Nonrecursively prints all the items in the tree in order
	 * @param node pass this method the root
	 */
	public void inOrder(BTNode<E> node) {
		if (root == null)
			return;
		Stack<BTNode<E>> stack = new Stack<BTNode<E>>();
		BTNode<E> current = root;
		while (!stack.isEmpty() || current != null) {
			if (current != null) {
				stack.push(current);
				current = current.getLeft();
			} else {
				current = stack.pop();
				System.out.print(current.getElement() + " ");
				current = current.getRight();
			}
		}
		System.out.println("");

	}

	/**
	 * recursively prints all the items in the tree pre order
	 * @param node pass this root
	 */
	public void preOrder(BTNode<E> node) {
		if (node != null) {
			System.out.print(node.getElement() + " ");
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}
	
	/**
	 * 
	 * @param node the node we want to delete
	 * @return The node that was deleted, null if the node passed was null (for example, if you search for it and 
	 */
	public BTNode<E> deleteNode(BTNode<E> aNode) {
		BTNode<E> node=aNode;
		
		//case 1: node has no children
		if (node.getLeft()==null && node.getRight()==null) {
			node=null;
			size++;
			aNode=null;
			return node;
		}
		//case 2: node has a right child
		else if (node.getRight()!=null && node.getLeft()==null) {
			node.getParent().setRight(node.getRight());
			node=null;
			size++;
			aNode=null;
			return node;
		}
		//case 3: node has a left child
		else if (node.getLeft()!=null && node.getRight()==null) {
			node.getParent().setLeft(node.getLeft());
			node=null;
			aNode=null;
			size++;
			return node;
		}
		//case 4: node has both a left and a right child
		else if (node.getLeft()!=null && node.getRight()!=null) {
			BTNode<E> parent=node.getParent();
			BTNode<E> newLeft=node.getLeft();
			BTNode<E> newRight=node.getRight();
			
			Comparable<E> elemTemp = (Comparable<E>) node.getElement();
			
			if (elemTemp.compareTo(parent.getElement()) >= 0) { //the node is greater than the parent
				parent.setRight(newLeft);
				newLeft.setParent(parent);
				
				BTNode<E> parent2=findMaximum(newLeft);
	
				parent2.setRight(newRight);
				newRight.setParent(parent2);
			}
			else { //the node is less than the parent
				parent.setLeft(newRight);
				newRight.setParent(parent);
				
				BTNode<E> parent2=findMinimum(newRight);
				
				parent2.setLeft(newLeft);
				newLeft.setParent(parent2);
			}
			size++;
			aNode=null;
			return node;
		}
		else
			return null;
	}
	
	/**
	 * Counts the number of internal nodes without using recursion
	 * @param node pass this root
	 * @return the number of internal nodes
	 */
	public int internalCounter(BTNode<E> node) {
		int counter=0;
		if (root == null)
			return 0;
		Stack<BTNode<E>> stack = new Stack<BTNode<E>>();
		BTNode<E> current = root;
		while (!stack.isEmpty() || current != null) {
			if (current != null) {
				stack.push(current);
				current = current.getLeft();
			} else {
				current = stack.pop();
				counter++;
				current = current.getRight();
			}
		}
		return counter;
	}
	
	/**
	 * Traverses the tree from a node and finds the smallest value
	 * @param node the node we want to check
	 * @return the smallest object
	 */
	public BTNode<E> findMinimum(BTNode<E> node) {
		if (node.getRight()==null)
			return node;
		return findMinimum(node.getLeft());
	}

	/**
	 * Traverses the tree from a node and finds the largest value
	 * @param node the node we want to check
	 * @return the largest object
	 */

	public BTNode<E> findMaximum(BTNode<E> node) {
		if (node.getLeft()==null)
			return node;
		return findMinimum(node.getRight());
	}


	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Check if a node is a valid binary tree node
	 * 
	 * @param v
	 *            node to be checked
	 * @throws InvalidPositionException
	 */
	public void checkNode(BTNode<E> v) throws InvalidPositionException {
		if (v == null)
			throw new InvalidPositionException("The node is invalid");
	}

	/**
	 * Check if a node is an internal node
	 * 
	 * @param v
	 *            the node to be checked
	 * @return
	 * @throws InvalidPositionException
	 */
	public boolean isInternal(BTNode<E> v) throws InvalidPositionException {
		checkNode(v);
		if (hasLeft(v) || hasRight(v))
			return true;

		return false;
	}

	/**
	 * Check if a node has a left child
	 * 
	 * @param v
	 *            node to be checked
	 * @return boolean value
	 * @throws InvalidPositionException
	 */
	public boolean hasLeft(BTNode v) throws InvalidPositionException {
		checkNode(v);
		if (v.getLeft() != null)
			return true;
		return false;
	}

	/**
	 * Check if a node has a right child
	 * 
	 * @param v
	 *            node to be checked
	 * @return boolean value
	 * @throws InvalidPositionException
	 */
	public boolean hasRight(BTNode v) throws InvalidPositionException {
		checkNode(v);
		if (v.getRight() != null)
			return true;
		return false;
	}

	/**
	 * Check if the node is an External (leaf) node
	 * 
	 * @param v
	 *            aNode to check
	 * @return boolean value
	 * @throws InvalidPositionException
	 */
	public boolean isExternal(BTNode v) throws InvalidPositionException {
		return !isInternal(v);
	}

	/**
	 * Check is a root node
	 * 
	 * @param v
	 *            a node to check
	 * @return boolean value
	 * @throws InvalidPositionException
	 */
	public boolean isRoot(BTNode<E> v) throws InvalidPositionException {
		checkNode(v);
		if (v == getRoot())
			return true;
		return false;
	}

	/**
	 * Gets the root node
	 * 
	 * @return the root node
	 * @throws EmptyTreeException
	 */
	public BTNode<E> getRoot() throws EmptyTreeException {
		if (root == null)
			throw new EmptyTreeException("The tree is empty!");
		return root;
	}

	/**
	 * Get the left child of a specific node in the tree
	 * 
	 * @param v
	 *            node to be used
	 * @return the left child of this node
	 * @throws InvalidPositionException
	 * @throws BoundaryViolationException
	 */
	public BTNode<E> getThisLeft(BTNode<E> v) throws InvalidPositionException,
			BoundaryViolationException {
		checkNode(v);
		BTNode<E> leftNode = v.getLeft();
		if (leftNode == null)
			throw new BoundaryViolationException("No Left Child");
		return leftNode;
	}

	/**
	 * Get the right child of a specific node in the tree
	 * 
	 * @param v
	 *            node to be used
	 * @return the right child of this node
	 * @throws InvalidPositionException
	 * @throws BoundaryViolationException
	 */
	public BTNode<E> getThisRight(BTNode<E> v) throws InvalidPositionException,
			BoundaryViolationException {
		checkNode(v);
		BTNode<E> rightNode = v.getRight();
		if (rightNode == null)
			throw new BoundaryViolationException("No right child");
		return rightNode;
	}

	/**
	 * Get the parent of a specific node in the tree
	 * 
	 * @param v
	 *            node to be used
	 * @return the parent of this node
	 * @throws InvalidPositionException
	 * @throws BoundaryViolationException
	 */
	public BTNode<E> getThisParent(BTNode<E> v)
			throws InvalidPositionException, BoundaryViolationException {
		checkNode(v);
		BTNode<E> parentNode = v.getParent();
		if (parentNode == null)
			throw new BoundaryViolationException("No parent");
		return parentNode;
	}

	/**
	 * Creates a new node
	 * 
	 * @param elem
	 * @param parent
	 * @param left
	 * @param right
	 * @return the new created node
	 */
	public BTNode<E> createNode(E elem, BTNode<E> parent, BTNode<E> left,
			BTNode<E> right) {
		return new BTNode(elem, parent, left, right);
	}

	/**
	 * Insert a root node to the tree
	 * 
	 * @param elem
	 * @throws NonEmptyTreeException
	 */
	public void addRoot(E elem) throws NonEmptyTreeException {
		if (!isEmpty())
			throw new NonEmptyTreeException("The tree has a root");
		size = 1;
		root = createNode(elem, null, null, null);
	}

	/**
	 * Inserts a left child
	 * 
	 * @param v
	 *            parent node
	 * @param o
	 *            child node's element
	 * @throws InvalidPositionException
	 */
	public void insertLeft(BTNode<E> v, E o) throws InvalidPositionException {
		checkNode(v);
		BTNode<E> leftPos = v.getLeft();

		if (leftPos != null)
			throw new InvalidPositionException("Node has a left child");
		BTNode<E> newLeft = createNode(o, v, null, null);
		v.setLeft(newLeft);
		size++;
	}

	/**
	 * Inserts a right child
	 * 
	 * @param v
	 *            parent node
	 * @param o
	 *            child node's element
	 * @throws InvalidPositionException
	 */
	public void insertRight(BTNode<E> v, E o) throws InvalidPositionException {
		checkNode(v);
		BTNode<E> rightPos = v.getRight();

		if (rightPos != null)
			throw new InvalidPositionException("Node has a right child");
		BTNode<E> newRight = createNode(o, v, null, null);
		v.setRight(newRight);
		size++;
	}

	/**
	 * Insert an element in the Binary Search Tree (Build a Binary Search Tree)
	 * 
	 * @param newElem
	 *            value to be inserted
	 */
	public void insertBST(E newElem) {
		BTNode<E> current;
		BTNode<E> tailCurrent = null;

		if (root == null) {
			addRoot(newElem);
			System.out.println("Add as root");
		} else {
			current = root;

			while (current != null) {
				tailCurrent = current;

				Comparable<E> temp = (Comparable<E>) current.getElement();

				if (temp.compareTo(newElem) > 0) {
					current = current.getLeft();
				} else
					current = current.getRight();
			}
			Comparable<E> temp2 = (Comparable<E>) tailCurrent.getElement();

			if (temp2.compareTo(newElem) > 0) {
				insertLeft(tailCurrent, newElem);
				System.out.println("Add left");
			} else {
				insertRight(tailCurrent, newElem);
				System.out.println("Add right");
			}
		}
	}

}
