package com.babu.practice.algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.*;
import java.util.stream.Collectors;

public class KnapSack {
	
	private static Scanner scanner = new Scanner(System.in);
	
	@SuppressWarnings("unused")
	private static int knapSack(int [] w, int [] v, int n, int capacity) {
		int [] maxValues = new int[capacity + 1];
		Arrays.fill(maxValues, 0);
		for(int i=0; i<n; i++) {
			for(int j=w[i]; j<=capacity; j++) {
				maxValues[j] = Math.max(maxValues[j], v[i] + maxValues[j-w[i]]);
			}
		}
		return maxValues[capacity];
	}
	
	private static int knapSackDP(int [] w, int [] v, int n, int capacity) {
		int [][] DP = new int[n+1][capacity+1];
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=capacity;j++) {
				if(i==0 || j==0) {
					DP[i][j] = 0;
				} else {
					if(w[i-1] > j) {
						DP[i][j] = DP[i-1][j];
					} else {
						DP[i][j] = Math.max(DP[i-1][j], v[i-1] + DP[i-1][j-w[i-1]]);
					}
				}
			}
		}
		return DP[n][capacity];
	}
	
	private static double fractionalKnapSack(List<Item> items, int capacity) {
		//Using Comparable interface
		Collections.sort(items);
		//Use Greedy approach to get the max value in knapsack
		double maxValue = 0;
		for(Item item : items) {
			//Take the whole item
			if(capacity >= item.getWeight()) {
				capacity -= item.getWeight();
				maxValue += item.getValue();
			} else { //Take the fraction of Item
				maxValue += ((double)capacity/item.getWeight()) * item.getValue();
				break;
			}
		}
		return maxValue;
	}
	
	public static void main (String[] args) {
		//code
		int t = scanner.nextInt();
		for(int i=1;i<=t;i++) {
			int n = scanner.nextInt();
			int capacity = scanner.nextInt();
			int [] v = new int[n];
			for(int j=0;j<n;j++)
			    v[j] = scanner.nextInt();
			int [] w = new int[n];
			for(int j=0;j<n;j++)
			    w[j] = scanner.nextInt();
			List<Item> items = new ArrayList<Item>();
			for(int j=0;j<n;j++) {
			    items.add(new Item(v[j], w[j]));
			}
			System.out.println(fractionalKnapSack(items, capacity));
		}
	}
	
	/**
	 * Main stub for different input format
	 */
	/*public static void main(String []args) {
		int t = scanner.nextInt();
		for(int i=1;i<=t;i++) {
			int n = scanner.nextInt();
			int capacity = scanner.nextInt();
			scanner.nextLine();
			int [] v = new int[n];
			v = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
			int [] w = new int[n];
			//scanner.nextLine();
			w = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
			//System.out.println(knapSackDP(w, v, n, capacity));
			System.out.println(fractionalKnapSackDP(w, v, n, capacity));
		}
	}*/
	
	
	/**
	 * Inner class for computing Fractional KnapSack problem
	 */
	public static class Item implements Comparable<Item> {
		int value, weight;
		
		public Item(int v, int w) {
			this.value = v;
			this.weight = w;
		}
		
		public int getValue() {
			return value;
		}
		
		public int getWeight() {
			return weight;
		}

		@Override
		public int compareTo(Item item) {
			//For descending order sort
			return Double.compare((double)item.value/item.weight, (double)this.value/this.weight);
		}
		
	}

}
