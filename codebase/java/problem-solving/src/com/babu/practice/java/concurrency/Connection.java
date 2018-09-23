package com.babu.practice.java.concurrency;

import java.util.concurrent.Semaphore;

public class Connection {
	
	private static Connection connection = new Connection();
	
	private int count;
	
	/**
	 * Semaphore to control current connection to max 10 at a time
	 */
	private Semaphore sem = new Semaphore(10);
	
	private Connection() {
		
	}
	
	public static Connection getInstance() {
		return connection;
	}
	
	public void connect() {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			doConnect();
		} finally {
			sem.release();
		}
	}
	
	public void doConnect() {
		synchronized (this) {
			count++;
			System.out.println("Current Connections: " + count);
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (this) {
			count--;
		}
	}
	
}
