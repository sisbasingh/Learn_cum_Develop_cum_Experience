package com.babu.practice.ds.trees;

import java.util.LinkedList;
import java.util.List;

public class PrintExteriorOfBinaryTree {
	
	List<Integer> printLeftBoundaryUtil(Node root, boolean isBoundary) {
        List<Integer> leftBoundary = new LinkedList<>();
        if(root != null) {
            if(isBoundary || (root.left == null && root.right == null))
                leftBoundary.add(root.data);
            leftBoundary.addAll(printLeftBoundaryUtil(root.left, isBoundary));
            leftBoundary.addAll(printLeftBoundaryUtil(root.right, root.left == null && isBoundary));
        }
        return leftBoundary;
    }
    
    List<Integer> printRightBoundaryUtil(Node root, boolean isBoundary) {
        List<Integer> rightBoundary = new LinkedList<>();
        if(root != null) {
            rightBoundary.addAll(printRightBoundaryUtil(root.left, root.right == null && 
                isBoundary));
            rightBoundary.addAll(printRightBoundaryUtil(root.right, isBoundary));
            if(isBoundary || (root.left == null && root.right == null))
                rightBoundary.add(root.data);
        }
        return rightBoundary;
    }
    
	void printBoundary(Node node)
	{
	    if(node == null)
	        return;
	    List<Integer> results = new LinkedList<>();
	    results.add(node.data);
	    results.addAll(printLeftBoundaryUtil(node.left, true));
	    results.addAll(printRightBoundaryUtil(node.right, true));
	    
	    for(int n : results) {
	        System.out.print(n + " ");
	    }
	}
	
	
	class Node
	{
	    int data;
	    Node left, right;
	    Node(int item)
	    {
	        data = item;
	        left = right = null;
	    }
	}
}
