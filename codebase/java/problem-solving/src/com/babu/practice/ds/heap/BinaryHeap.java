package com.babu.practice.ds.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Array based implementation of Binary Heap
 * @author sisba01
 *
 */
public class BinaryHeap {
	int [] heap;
	int capacity;
	int size;
	
	public BinaryHeap(int capacity) {
		heap = new int[capacity];
		this.capacity = capacity;
		size = capacity;
	}
	
	private void add(int e) {
		if(size == capacity) {
			int [] temp = heap;
			//Increase heap size by 50%
			Double increasedCapacity = (double)capacity * 3/2;
			capacity = increasedCapacity.intValue();
			heap = Arrays.copyOf(temp, capacity);
		}
		heap[size++] = e;
		int i = size-1;
		while(i != 0 && heap[getParent(i)] < heap[i]) {
			int temp = heap[i];
			heap[i] = heap[getParent(i)];
			heap[getParent(i)] = temp;
			i = getParent(i);
		}
	}
	
	/**
	 * Array index for which value to be deleted
	 * @param i
	 * @return
	 */
	private int delete(int i) {
		/**
		 * Validations
		 */
		if(size <= 0) 
			return -1;
		int valueToBeDeleted;
		if(size == 1) {
			valueToBeDeleted = heap[0];
			size--;
			return valueToBeDeleted;
		}
		/**
		 * To delete key at index i, first move index i as an empty position to top and then
		 * place last value to this empty position and heapify
		 */
		valueToBeDeleted = heap[i];
		heap[i] = Integer.MIN_VALUE;
		int j = i;
		//Move the position to be deleted to top (for Max heapify)
		while(j != 0 && heap[getParent(j)] < heap[j]) {
			int temp = heap[getParent(j)];
			heap[getParent(j)] = heap[j];
			heap[j] = temp;
			j = getParent(j);
		}
		//replace top value with last value
		heap[0] = heap[size-1];
		size--;
		maxHeapify(0, size);
		return valueToBeDeleted;
	}
	
	private int deleteUpdated(int i) {
		//If the last item to be deleted then just decrease the size of heap
		int itemTobeDeleted;
		if(i == size-1) {
			itemTobeDeleted = heap[i];
			size--;
		} else {
			//Item to be deleted is either head or mid item
			itemTobeDeleted = heap[i];
			heap[i] = heap[size-1];
			size--;
			
			//Check if the newly inserted item to be shifted up or down based on minHeap or maxHeap
			//Currently assuming it is maxHeap
			if(i == 0) { //If item to be removed is first item
				//then max heapify down
				maxHeapify(i, size);
			} else {
				//Item to be deleted is in mid
				//Check for down shift
				if(heap[i] < heap[getParent(i)]) {
					maxHeapify(i, size);
				} else {
					//Shift up in the max heap
					int j = i;
					while(j != 0 && heap[getParent(j)] < heap[j]) {
						int temp = heap[j];
						heap[j] = heap[getParent(j)];
						heap[getParent(j)] = temp;
						j = getParent(j);
					}
				}
			}
		}
		return itemTobeDeleted;
	}
	
	private int getParent(int i) {
		return (i-1)/2;
	}
	
	private int getLeft(int i) {
		return 2*i+1;
	}
	
	private int getRight(int i) {
		return 2*i+2;
	}
	
	private void maxHeapify(int i, int n) {
		int largest = i;
		int left = getLeft(i);
		int right = getRight(i);
		if(left < n && heap[largest] < heap[left])
			largest = left;
		if(right < n && heap[largest] < heap[right])
			largest = right;
		if(largest != i) {
			//swap and heapify
			int temp = heap[i];
			heap[i] = heap[largest];
			heap[largest] = temp;
			maxHeapify(largest, n);
		}
	}
	
	private void minHeapify(int i, int n) {
		int smallest = i;
		int left = getLeft(i);
		int right = getRight(i);
		if(left < n && heap[smallest] > heap[left])
			smallest = left;
		if(right < n && heap[smallest] > heap[right])
			smallest = right;
		if(smallest != i) {
			//swap and heapify
			int temp = heap[i];
			heap[i] = heap[smallest];
			heap[smallest] = temp;
			minHeapify(smallest, n);
		}
	}
	
	private void heapSort() {
		for(int i=size/2-1;i>=0;i--)
			maxHeapify(i, size);
		
		for(int i=size-1;i>=0;i--) {
			int temp = heap[0];
			heap[0] = heap[i];
			heap[i] = temp;
			maxHeapify(0, i);
		}
	}
	
	public static void main(String [] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int t = Integer.parseInt(br.readLine());
			for(int i=1;i<=t;i++) {
				StringTokenizer tokenizer = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(tokenizer.nextToken());
				BinaryHeap bh = new BinaryHeap(n);
				bh.heap = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
				bh.heapSort();
				System.out.println(Arrays.toString(bh.heap));
				bh.add(4);
				bh.add(11);
				System.out.println("After add: " + Arrays.toString(bh.heap));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
