package com.babu.practice.java.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args){
		//runProducerConsumer();
		/*try {
			runDeadlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

		//runSemaphore();
		runCallableAndFuture();
	}

	private static void runCallableAndFuture() {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> future = executor.submit(new Callable<Integer> () {
			@Override
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				System.out.println("Starting ....");
				Thread.sleep(duration);
				System.out.println("Finished ....");
				return duration;
			}
		});
		
		executor.shutdown();
		/**
		 * Note that here executor.awaitTermination(timeout, unit) is not required 
		 * as future.get() waits for complete thread execution
		 * Even exceptions can be thrown and cached by using Future and Callable
		 * For multiple thread return values can be stored in ArrayList or any other collection
		 */
		try {
			System.out.println("Returned value: " + future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static void runSemaphore() {
		ExecutorService executor = Executors.newCachedThreadPool();

		for(int i=0;i<200;i++) {
			executor.submit(new Runnable () {
				@Override
				public void run() {
					Connection.getInstance().connect();
				}
			});
		}

		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void runDeadlock() throws InterruptedException {
		final Deadlock deadlock = new Deadlock();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					deadlock.taskOne();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					deadlock.taskTwo();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		deadlock.finished();
	}

	private static void runProducerConsumer() {
		final ProducerConsumer runner = new ProducerConsumer();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				runner.produce();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				runner.consume();;
			}
		});

		t1.start();
		t2.start();
	}
}