package com.babu.practice.java.concurrency;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {
	private Account acc1 = new Account(10000);
	private Account acc2 = new Account(10000);
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private void acquireSafeLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
		while(true) {
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			try {
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			} finally {
				if(gotFirstLock && gotSecondLock) {
					return;
				}
				if(gotFirstLock) {
					firstLock.unlock();
				}
				if(gotSecondLock) {
					secondLock.unlock();
				}
			}
			Thread.sleep(1); 
		}
	}
	
	public void taskOne() throws InterruptedException { 
		Random random = new Random();
		for(int i=0; i<10000; i++) {
			lock1.lock();
			lock2.lock();
			/**
			 * Uncomment the above two lines comment below call to acquireSafeLocks() to produce deadlocks
			 * it produces 50% of the time
			 */
			//acquireSafeLocks(lock1, lock2);
			try {
				/**
				 * perform some thread synchronization task here
				 */
				acc1.transfer(acc2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		} 
	}
	
	public void taskTwo() throws InterruptedException {
		Random random = new Random();
		for(int i=0; i<10000; i++) {
			lock2.lock();
			lock1.lock();
			/**
			 * Uncomment the above two lines comment below call to acquireSafeLocks() to produce deadlocks
			 * it produces 50% of the time
			 */
			//acquireSafeLocks(lock2, lock1);
			try {
				/**
				 * perform some thread synchronization task here
				 */
				acc2.transfer(acc1, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		} 
	}
	
	public void finished() {
		System.out.println("Account1 balance: " + acc1.getBalance());
		System.out.println("Account2 balance: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
}
