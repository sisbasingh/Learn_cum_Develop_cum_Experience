package com.babu.practice.java.concurrency;

public class Account {
	private int balance;
	
	public Account(int balance) {
		this.balance = balance;
	}
	
	public void deposit(int amount) {
		balance += amount;
	}
	
	public void withdraw(int amount) {
		balance -= amount;
	}
	
	public void transfer(Account acc, int amount) {
		this.withdraw(amount);
		acc.deposit(amount);
	}
	
	public int getBalance() {
		return balance;
	}
}
