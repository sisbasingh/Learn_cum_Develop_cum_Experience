package com.babu.practice.ds.trees;

public class AVLTree {
	Node root;
	
	public AVLTree() {
		root = null;
	}
	
	public AVLTree(Node root) {
		this.root = root;
	}
	
	class Height {
		int height;
	}
	
	/**
	 * IMP : Can't use Integer Object in place of Height objects as Integer object is immutable as String object
	 * IMP : May be all the prime data type objects are immutable, threby making them thread safe
	 * @param root
	 * @param h
	 * @return
	 */
	public boolean isTreeBalanced(Node root, Height h) {
		if(root == null) {
			h.height = 0;
			return true;
		}
		Height lh = new Height(), rh = new Height();
		boolean lb = isTreeBalanced(root.left, lh);
		boolean rb = isTreeBalanced(root.right, rh);
		
		h.height = (lh.height > rh.height ? lh.height : rh.height) + 1;
		if(lh.height - rh.height > 1 || lh.height - rh.height < -1)
			return false;
		return lb && rb;
	}
	
	/**
	 * Class to represent AVL tree node
	 * @author sisba01
	 *
	 */
	class Node {
		int value, height;
		Node left, right;
		
		Node(int value) {
			this.value = value;
			height = 1;
			left = right = null;
		}
	}
	
	private void inorderTraversal(Node root) {
		if(root == null)
			return;
		inorderTraversal(root.left);
		System.out.print(root.value + " ");
		inorderTraversal(root.right);
	}
	
	private void preOrderTraversal(Node root) {
		if(root == null)
			return;
		System.out.print(root.value + " ");
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}
	
	private int getBalance(Node x) {
		if(x == null)
			return 0;
		return getHeight(x.left)-getHeight(x.right);
	}
	
	private int getHeight(Node x) {
		return x != null?x.height:0;
	}
	
	private Node leftRotate(Node x) {
		Node y = x.right;
		Node z = y.left;
		
		y.left = x;
		x.right = z;
		
		//Balance the heights after rotation
		x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
		y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
		
		return y;
	}
	
	private Node rightRotate(Node x) {
		Node y = x.left;
		Node z = y.right;
		
		y.right = x;
		x.left = z;
		
		//Balance the heights after rotation
		x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
		y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
		
		return y;
	}
	
	public Node insertIntoAVLTree(Node root, int value) {
		//Do the BST insertion
		if(root == null)
			return new Node(value);
		else if(value < root.value)
			root.left = insertIntoAVLTree(root.left, value);
		else if(value > root.value)
			root.right = insertIntoAVLTree(root.right, value);
		else //No duplicates are permitted
			return root;
		
		root.height = 1+Math.max(getHeight(root.left), getHeight(root.right));
		int hbalance = getBalance(root);
		
		//Left-Left case //value < root.left.value
		if(hbalance > 1 && getBalance(root.left) >= 0) {
			//Right rotate
			return rightRotate(root);
		}
		//Left-Right case //value > root.left.value
		if(hbalance > 1 && getBalance(root.left) <= 0) {
			//Left rotate and then right rotate
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}
		//Right-Right case //value > root.right.value
		if(hbalance < -1 && getBalance(root.right) <= 0) {
			//Left rotate
			return leftRotate(root);
		}
		//Right-Left case //value < root.right.value
		if(hbalance < -1 && getBalance(root.right) >= 0) {
			//Right rotate and then Left rotate
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}
		
		//No rotation requires
		return root;
	}
	
	private Node getMinValueNode(Node root) {
		Node temp = root;
		while(temp.left != null)
			temp = temp.left;
		return temp;
	}
	
	public Node deleteFromAVLTree(Node root, int value) {
		if(root == null) return root;
		else if(value < root.value) {
			root.left = deleteFromAVLTree(root.left, value);
		} else if(value > root.value) {
			root.right = deleteFromAVLTree(root.right, value);
		} else {
			//Node found
			//If only one or no child
			if(root.left == null || root.right == null) {
				Node temp = root.left != null?root.left:root.right;
				if(temp == null) {
					//No child case
					root = null;
				} else {
					//One child case
					root = temp;
				}
			} else {
				//Both child case
				//Replace the Node value with the min value from right subtree
				Node minValNode = getMinValueNode(root.right);
				root.value = minValNode.value;
				root.right = deleteFromAVLTree(root.right, minValNode.value);
			}
		}
		
		//Check for single node tree
		if(root == null)
			return root;
		
		root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
		int hbalance = getBalance(root);
		
		//Left-Left case
		if(hbalance > 1 && getBalance(root.left) >= 0) {
			//Right rotate
			return rightRotate(root);
		}
		//Left-Right case
		if(hbalance > 1 && getBalance(root.left) <= 0) {
			//Left rotate then Right rotate
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}
		//Right-Right case
		if(hbalance < -1 && getBalance(root.right) <= 0) {
			//Left rotate
			return leftRotate(root);
		}
		//Right-Left case
		if(hbalance < -1 && getBalance(root.right) >= 0) {
			//Right rotate then Left rotate
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}
		//No changes to balance
		return root;
	}
	
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		
		tree.root = tree.insertIntoAVLTree(tree.root, 1);
		tree.root = tree.insertIntoAVLTree(tree.root, 2);
		tree.root = tree.insertIntoAVLTree(tree.root, 3);
		tree.root = tree.insertIntoAVLTree(tree.root, 4);
		tree.root = tree.insertIntoAVLTree(tree.root, 5);
		tree.root = tree.insertIntoAVLTree(tree.root, 6);
		tree.root = tree.insertIntoAVLTree(tree.root, 7);
		tree.root = tree.insertIntoAVLTree(tree.root, 8);
		tree.root = tree.insertIntoAVLTree(tree.root, 9);
		tree.root = tree.insertIntoAVLTree(tree.root, 10);
		
		System.out.println("Is Tree balanced: " + tree.isTreeBalanced(tree.root, tree.new Height()));
		tree.inorderTraversal(tree.root);
		//System.out.println();
		//tree.preOrderTraversal(tree.root);
		System.out.println();
		System.out.println("Left Height:" + tree.root.left.height + "\tRight Height:" + tree.root.right.height);
		tree.root = tree.deleteFromAVLTree(tree.root, 5);
		tree.root = tree.deleteFromAVLTree(tree.root, 6);
		tree.root = tree.deleteFromAVLTree(tree.root, 7);
		System.out.println("After Deletion of 5, 6, 7:");
		tree.inorderTraversal(tree.root);
		System.out.println();
		System.out.println("Left Height:" + tree.root.left.height + "\tRight Height:" + tree.root.right.height);
		System.out.println("Is Tree balanced: " + tree.isTreeBalanced(tree.root, tree.new Height()));
	}

}
