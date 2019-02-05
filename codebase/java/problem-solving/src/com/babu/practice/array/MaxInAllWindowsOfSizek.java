package com.babu.practice.array;

import java.util.Deque;
import java.util.LinkedList;

public class MaxInAllWindowsOfSizek {

	public static void printMaxOfWindow(int a[], int k, int n) {
		if (n < k) {
			return;
		}
		Deque<Integer> q = new LinkedList<>();
		q.add(a[0]);
		for (int i = 1; i < k; i++) {
			if (q.isEmpty() || a[i] <= q.peekLast()) {
				q.add(a[i]);
			} else {
				while (!q.isEmpty() && q.peekLast() < a[i]) {
					q.removeLast();
				}
				q.add(a[i]);
			}
		}
		System.out.print(q.peek() + " ");
		for (int i = k; i < n; i++) {
			if (a[i - k] == q.peek()) {
				q.removeFirst();
			}
			if (q.isEmpty() || a[i] <= q.peekLast()) {
				q.add(a[i]);
			} else {
				while (!q.isEmpty() && q.peekLast() < a[i]) {
					q.removeLast();
				}
				q.add(a[i]);
			}
			System.out.print(q.peek() + " ");
		}
	}
	
	public static void main(String[] args) {
		int n = 10, k = 4;
		int[] a = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
		printMaxOfWindow(a, k, n);
	}

}
