package com.babu.practice.udemy.design_patterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

class Node<T>
{
  public T value;
  public Node<T> left, right, parent;

  public Node(T value)
  {
    this.value = value;
  }

  public Node(T value, Node<T> left, Node<T> right)
  {
    this.value = value;
    this.left = left;
    this.right = right;

    left.parent = right.parent = this;
  }

  public Iterator<Node<T>> preOrder()
  {
     ArrayList<Node<T>> preOrderList = new ArrayList<>();
     Stack<Node<T>> stack = new Stack<>();
     stack.push(this);
     while(!stack.isEmpty()) {
    	 Node<T> currNode = stack.pop();
    	 preOrderList.add(currNode);
    	 if(currNode.right != null) {
    		 stack.push(currNode.right);
    	 }
    	 if(currNode.left != null) {
    		 stack.push(currNode.left);
    	 }
     }
     return preOrderList.iterator();
  }
}

public class Demo {
	public static void main(String [] args) {
		Node<Integer> root = new Node<Integer>(1);
		Node<Integer> left = new Node<>(2);
		Node<Integer> right = new Node<>(3);
		
		root.left = left;
		root.right = right;
		
		left = new Node<>(4);
		right = new Node<>(5);
		
		root.left.left = left;
		root.left.right = right;
		
		Iterator<Node<Integer>> it = root.preOrder();
		while(it.hasNext()) {
			System.out.print(it.next().value + " ");
		}
	}
}
