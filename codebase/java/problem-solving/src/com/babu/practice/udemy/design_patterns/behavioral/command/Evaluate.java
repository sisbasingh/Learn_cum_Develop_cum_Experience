package com.babu.practice.udemy.design_patterns.behavioral.command;

import static org.junit.Assert.assertEquals;

public class Evaluate {
	
	@org.junit.Test
	public void evaluate() {
		Account account = new Account();
		account.balance = 500;
		
		Command command = new Command(Command.Action.WITHDRAW, 600);
		account.process(command);
		assertEquals(false, command.success);
		
		Command command2= new Command(Command.Action.DEPOSIT, 100);
		account.process(command2);
		assertEquals(true, command2.success);
		
		account.process(command);
		assertEquals(true, command.success);
		
		account.process(command);
		assertEquals(false, command.success);
	}

}
