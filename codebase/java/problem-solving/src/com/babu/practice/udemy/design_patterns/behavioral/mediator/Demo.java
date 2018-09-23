package com.babu.practice.udemy.design_patterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

class Participant
{
  
  public int value;
  public Mediator mediator;
  
  public Participant(Mediator mediator)
  {
	  this.mediator = mediator;
	  this.mediator.join(this);
  }

  public void say(int n)
  {
	  mediator.broadcast(this, n);
  }
}

class Mediator
{
	public List<Participant> participants;
	
	public Mediator() {
		participants = new ArrayList<>();
	}
	
	public void join(Participant person) {
		participants.add(person);
	}
	
	public void broadcast(Participant person, int value) {
		for(Participant p : participants) {
			if(p != person) {
				p.value += value;
			}
		}
	}
	
	public void printValues() {
		participants.stream().forEach(p -> {
			System.out.println(p.value);
		});
	}
}

//TODO : Also see Reactive Extension in Java
public class Demo {
	public static void main(String [] args) {
		Mediator mediator = new Mediator();
		Participant p1 = new Participant(mediator);
		Participant p2 = new Participant(mediator);
		
		p1.say(3);
		mediator.printValues();
		p2.say(2);
		mediator.printValues();
	}
}
