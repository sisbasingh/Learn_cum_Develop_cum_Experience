package com.babu.practice.java.concurrency.test;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

public class ConcurrencyTest {

	/**
	 * Thread safe class
	 * @author sisba01
	 *
	 */
	@ThreadSafe
	public static final class Counter {
		@GuardedBy("this")
		private long value = 0;

		public synchronized long getValue() {
			return value;
		}

		public synchronized long increment() {
			if (value == Long.MAX_VALUE)
				throw new IllegalStateException("counter overflow");
			return ++value;
		}
	}
	
	public static void main(String[] args) {
		Counter counter = new Counter();
		for(int i=1;i<=5;i++) {
			System.out.println(counter.getValue());
			counter.increment();
		}
	}

}
