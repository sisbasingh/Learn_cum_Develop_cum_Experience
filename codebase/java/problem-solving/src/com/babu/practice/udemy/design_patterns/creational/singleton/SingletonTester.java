package com.babu.practice.udemy.design_patterns.creational.singleton;

import java.util.function.Supplier;

class Singleton{
	private final static Singleton INSTANCE = new Singleton();
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		return INSTANCE;
	}
}

class NonSingleton {
	private NonSingleton() {}
	
	public static NonSingleton getInstance() {
		return new NonSingleton();
	}
}

class SingletonSupplier implements Supplier<Object> {

	@Override
	public Singleton get() {
		return Singleton.getInstance();
	}
}

class NonSingletonSupplier implements Supplier<Object> {

	@Override
	public NonSingleton get() {
		return NonSingleton.getInstance();
	}
	
}

class SingletonTester
{
  public static boolean isSingleton(Supplier<Object> func)
  {
	  if(func.get() == func.get())
		  return true;
	  return false;
  }
  
  public static void main(String[] args) {
	  System.out.println(isSingleton(new SingletonSupplier()));
	  System.out.println(isSingleton(new NonSingletonSupplier()));
  }
}
