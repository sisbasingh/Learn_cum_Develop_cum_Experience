package com.babu.practice.udemy.design_patterns.behavioral.state;

import java.util.Arrays;

enum state {
	LOCKED,
	ERORR,
	OPEN
}

class CombinationLock
{
  private int [] combination;
  public String status;

  public CombinationLock(int[] combination)
  {
    this.combination = combination;
    status = "LOCKED";
  }

  public void enterDigit(int digit)
  {
	  if(status.equals(state.OPEN.name())) {
		  return;
	  } else if (status.equals(state.ERORR.name())) {
		  return;
	  } else if(status.equals(state.LOCKED.name()) && digit == combination[0]) {
		  status = "" + digit;
	  } else if(digit == combination[status.length()]) {
		  if(combination.length - 1 == status.length()) {
			  status  = state.OPEN.name();
		  } else {
			  status += digit;
		  } 
	  } else {
		  status = state.ERORR.name();
	  }
  }
}

public class Demo {
	public static void main(String [] args) {
		CombinationLock cl = new CombinationLock(new int[] {1, 2, 3, 4});
		System.out.println(cl.status);
		cl.enterDigit(1);
		System.out.println(cl.status);
		cl.enterDigit(2);
		System.out.println(cl.status);
		cl.enterDigit(3);
		System.out.println(cl.status);
		cl.enterDigit(4);
		System.out.println(cl.status);
	}
}
