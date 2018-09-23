package com.babu.practice.java.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProducerConsumer {
	private List<Integer> queue = new ArrayList<Integer>();
	private static final int MAX_LEN = 10;
	private int count = 0;
	
	public void produce() {
		Random random = new Random();
		while(true) {
			synchronized (this) {
				while(count == MAX_LEN) {
					//wait
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(random.nextInt(1000000) == 0) {
					queue.add(random.nextInt(100));
					count++;
					notify();
				}
			}
		}
	}
	
	public void consume() {
		Random random = new Random();
		while(true){
			synchronized (this) {
				while(count == 0){
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(random.nextInt(1000000) == 0) {
					System.out.print("List Size: " + queue.size());
					int value = queue.remove(0);
					count--;
					System.out.println(" ;got value: " + value);
					notify();
				}	
			}
		}
	}
}
