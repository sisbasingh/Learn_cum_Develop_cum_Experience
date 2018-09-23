package com.babu.practice.udemy.design_patterns.structural.decorator;

class Bird
{
  public int age;

  public String fly()
  {
    return age < 10 ? "flying" : "too old";
  }
}

class Lizard
{
  public int age;

  public String crawl()
  {
    return (age > 1) ? "crawling" : "too young";
  }
}

class Dragon
{
  private int age;
  private Bird bird;
  private Lizard lizard;
  
  public Dragon() {
	  bird = new Bird();
	  lizard = new Lizard();
  }
  
  public void setAge(int age)
  {
    this.age = age;
    bird.age = age;
    lizard.age = age;
  }
  public String fly()
  {
    return bird.fly();
  }
  public String crawl()
  {
    return lizard.crawl();
  }
}

public class Demo {
	public static void main(String args[]) {
		Dragon dragon = new Dragon();
		System.out.println(dragon.crawl());
		System.out.println(dragon.fly());
		dragon.setAge(5);
		System.out.println(dragon.crawl());
		System.out.println(dragon.fly());
		dragon.setAge(10);
		System.out.println(dragon.crawl());
		System.out.println(dragon.fly());
	}
}
