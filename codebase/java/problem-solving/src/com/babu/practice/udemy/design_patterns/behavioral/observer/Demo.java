package com.babu.practice.udemy.design_patterns.behavioral.observer;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

class Game
{
    public ArrayList<Rat> observers = new ArrayList<>();
    
    public void subscribe(Rat rat) {
    	observers.add(rat);
    	notifyObservers();
    }
    
    public void unsubscribe(Rat rat) {
    	observers.remove(rat);
    	notifyObservers();
    }
    
    private void notifyObservers() {
    	int attacks = observers.size();
    	for(Rat r : observers) {
    		r.attack = attacks;
    	}
    }
}

class Rat implements Closeable
{
  private Game game;
  public int attack = 1;

  public Rat(Game game)
  {
     this.game = game;
     this.game.subscribe(this);
  }

  @Override
  public void close() /*throws IOException*/
  {
     game.unsubscribe(this);
  }
  
  @Override
	public String toString() {
		return "" + attack;
	}
}

public class Demo {
	public static void main(String [] args) {
		Game game = new Game();
		Rat r1 = new Rat(game);
		Rat r2 = new Rat(game);
		System.out.println("with Two Rat: " + game.observers);
		Rat r3 = new Rat(game);
		System.out.println("with Three Rat: " + game.observers);
		Rat r4 = new Rat(game);
		System.out.println("with 4 Rats: " + game.observers);
		//game.unsubscribe(r3);
		r3.close();
		System.out.println("with 3 one left: " + game.observers);
	}
}
