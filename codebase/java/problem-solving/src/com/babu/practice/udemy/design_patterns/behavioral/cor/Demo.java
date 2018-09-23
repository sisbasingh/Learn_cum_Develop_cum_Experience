package com.babu.practice.udemy.design_patterns.behavioral.cor;

class Base {
	int i;
	protected Base next;
	
	public Base(int n) {
		i = n;
	}
	
	public void add(Base b) {
		if(next == null)
			next = b;
		else
			next.add(b);
	}
	
	public void handle() {
		System.out.println("In Base handle");
		if(null != next)
			next.handle();
	}
	
	public void printChain() {
		System.out.println(i);
		if(next != null)
			next.printChain();
	}
}

class Additive extends Base {

	public Additive(int n) {
		super(n);
	}
	
	@Override
	public void handle() {
		System.out.println("In additive handle");
		i += 2;
		super.handle();
	}
}

class Multiplicative extends Base {

	public Multiplicative(int n) {
		super(n);
	}
	
	@Override
	public void handle() {
		System.out.println("In multiplicative handle");
		i *= 2;
		super.handle();
	}	
}

public class Demo {
	public static void main(String [] args) {
		Base base = new Base(1);
		Additive add = new Additive(2);
		Multiplicative mul = new Multiplicative(3);
		base.add(add);
		base.add(mul);
		base.handle();
		base.printChain();
	}
}
