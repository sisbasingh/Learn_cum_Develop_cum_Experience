package com.babu;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution1 {
	
	public void print(String s){
		System.out.println(s);
	}

    public static void main(String[] args) {
    	Solution1 sol = new Solution1();
        BinaryTree bt = sol.new BinaryTree();
        bt.root = sol.new Node(50);
        bt.root.left = sol.new Node(30);
        bt.root.right = sol.new Node(70);
        bt.root.left.left = sol.new Node(10);
        bt.root.left.right = sol.new Node(35);
        bt.root.left.right.left = sol.new Node(32);
        bt.root.left.right.right = sol.new Node(38);
        bt.root.right.right = sol.new Node(80);
        bt.root.right.left = sol.new Node(60);
        
        //myPair mp = sol.new myPair();
        sol.print(bt.findSum(bt.root, bt.root, 60)+"");
        //sol.print("a: "+mp.a+" b: "+mp.b);
        
        /*int minLevel = bt.getMinLevel(bt.root);
        sol.print(minLevel + "");
        int minLevelSum = bt.minLevelSum(bt.root, minLevel);
        sol.print(minLevelSum + "");*/
    }
    
    public class Node {
    	public int data;
    	public Node left, right;
    	
    	public Node(int d) {
    		data = d;
    		left = right = null;
    	}
    }
    
    public class BinaryTree {
    	public Node root;
    	
    	public BinaryTree() {
    		root = null;
    	}
    	
    	public int getMinLevel(Node root) {
    		if(root == null)
    			return -1;
    		return getMinLevelUtil(root);
    	}
    	
    	public int getMinLevelUtil(Node root) {
    		if(root == null || (root.left == null && root.right == null))
    			return 0;
    		else
    			return 1+Math.min(getMinLevelUtil(root.left), getMinLevelUtil(root.right));
    	}
    	
    	public int minLevelSum(Node root, int l){
    		if(l < 0)
    			return -1;
    		return minLevelSumUtil(root, l, 0);
    	}
    	
    	public int minLevelSumUtil(Node root, int l, int sum) {
    		if(root == null)
    			return 0;
    		if(l == 0 && root.left == null && root.right == null)
    			return sum+root.data;
    		return minLevelSumUtil(root.left, l-1, sum) + minLevelSumUtil(root.right, l-1, sum);
    	}
    	
    	public boolean findSum(Node root, Node origRoot, int sum){
    		if(sum < 0 || root == null)
    			return false;
			if(findKInBST(origRoot, sum-root.data) && sum != 2*root.data){
				print("a: " + (sum-root.data)+ ", b: " + root.data);
				return true;
			}
			else return findSum(root.left, origRoot, sum) || findSum(root.right, origRoot, sum);
    	}
    	
    	public boolean findKInBST(Node root, int k){
    		if(k < 0 || root == null)
    			return false;
    		if(root.data == k)
    			return true;
    		else if(k < root.data)
    			return findKInBST(root.left, k);
    		else return findKInBST(root.right, k);
    	}
    	
    	public boolean findKInBT(Node root, int k){
    		if(k < 0 || root == null)
    			return false;
    		if(root.data == k)
    			return true;
    		else
    			return findKInBT(root.left, k) || findKInBT(root.right, k);
    	}
    }
    	
    public class myPair{
    	public Integer a,b;
    	public myPair(){
    		a = new Integer(-1);
    		b = new Integer(-1);
    	}
    }
}
    

