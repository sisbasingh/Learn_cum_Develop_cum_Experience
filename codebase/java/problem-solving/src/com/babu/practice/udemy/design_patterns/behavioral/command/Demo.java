package com.babu.practice.udemy.design_patterns.behavioral.command;

import static org.junit.Assert.assertEquals;

class Command
{
  enum Action
  {
    DEPOSIT, WITHDRAW
  }

  public Action action;
  public int amount;
  public boolean success;

  public Command(Action action, int amount)
  {
    this.action = action;
    this.amount = amount;
  }
}

class Account
{
  public int balance;

  public void process(Command c)
  {
    switch(c.action) {
    case DEPOSIT:
    	balance += c.amount;
    	c.success = true;
    	break;
    case WITHDRAW:
    	if(balance - c.amount >= 0) {
    		balance -= c.amount;
    		c.success = true;
    	} else {
    		c.success = false;
    	}
    	break;
    }
  }

	@Override
	public String toString() {
		return "Account [balance=" + balance + "]";
	}
  
}

public class Demo {
	public static void main(String [] args) {
		Account account = new Account();
		account.balance = 500;
		System.out.println(account);
		Command command = new Command(Command.Action.WITHDRAW, 600);
		account.process(command);
		System.out.println(account);
		Command command2= new Command(Command.Action.DEPOSIT, 100);
		account.process(command2);
		System.out.println(account);
		account.process(command);
		System.out.println(account);
	}
}
