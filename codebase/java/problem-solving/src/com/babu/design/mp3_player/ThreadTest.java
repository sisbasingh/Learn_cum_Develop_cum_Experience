package com.babu.design.mp3_player;

import java.util.Scanner;

public class ThreadTest {
	volatile boolean doPlay = false;
	volatile boolean runPlayer = false;

	Object player = new Object();
	Thread pthread;

	public void play() {
		while (runPlayer) {
			while (doPlay && runPlayer) {
				try {
					Thread.sleep(1000);
					System.out.println("Player is running...");
				} catch (InterruptedException e) {
					// e.printStackTrace();
				}
			}
			while(!doPlay && runPlayer) {
				synchronized (player) {
					try {
						System.out.println("Player is paused");
						player.wait();
					} catch (InterruptedException e) {
						// e.printStackTrace();
					}
				}
			}
		}
	}

	public void start() {
		if (doPlay) {
			return;
		}
		runPlayer =true;
		doPlay = true;
		if (pthread != null && pthread.isAlive()) {
			synchronized (player) {
				player.notify();
			}
		} else {
			pthread = new Thread(new Runnable() {
				@Override
				public void run() {
					play();
				}
			});
			pthread.start();
		}
	}

	public void pause() {
		doPlay = false;
	}

	public void stop() {
		runPlayer = false;
		synchronized (player) {
			player.notify();
		}
		try {
			/**
			 * End the player thread after notifying it
			 */
			pthread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Player is stopped");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ThreadTest test = new ThreadTest();
		while (true) {
			System.out.println("Enter choice");
			int choice = Integer.parseInt(in.nextLine());
			if (choice == 1) {
				test.start();
			} else if (choice == 2) {
				test.pause();
			} else {
				test.stop();
				return;
			}
		}
	}

}
