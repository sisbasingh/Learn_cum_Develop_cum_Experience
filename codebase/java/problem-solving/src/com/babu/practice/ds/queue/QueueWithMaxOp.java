package com.babu.practice.ds.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueWithMaxOp <T extends Comparable<T>> implements Comparable<T>{
	
	Queue<T> main_q;
	Deque<T> max_q;
	
	public static void main(String[] args) {
		QueueWithMaxOp<Integer> qop = new QueueWithMaxOp<Integer>();
		qop.insert(5);
		qop.insert(2);
		System.out.println(qop.getMin());
		qop.delete();
		qop.insert(7);
		qop.insert(1);
		System.out.println(qop.getMin());
		qop.delete();
		qop.delete();
		qop.delete();
		qop.insert(3);
		qop.insert(0);
		qop.insert(4);
		System.out.println(qop.getMin());
	}
	
	public QueueWithMaxOp() {
		main_q = new LinkedList<T>();
		max_q = new LinkedList<T>();
	}
	
	public void insert(T e) {
		main_q.add(e);
		while(!max_q.isEmpty() && max_q.peekLast().compareTo(e) < 0) {
			max_q.removeLast();
		}
		max_q.addLast(e);
	}
	
	public T delete() {
		T item = main_q.poll();
		if(item == null) {
			return item;
		}
		if(max_q.peekFirst().equals(item)) {
			max_q.removeFirst();
		}
		return item;
	}
	
	public T getMin() {
		if(max_q.isEmpty()) {
			return null;
		}
		return max_q.peekFirst();
	}

	@Override
	public int compareTo(T o) {
		return this.compareTo(o);
	}

}
