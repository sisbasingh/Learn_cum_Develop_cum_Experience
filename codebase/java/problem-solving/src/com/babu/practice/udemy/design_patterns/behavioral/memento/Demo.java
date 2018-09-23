package com.babu.practice.udemy.design_patterns.behavioral.memento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Token
{
  public int value = 0;

  public Token(int value)
  {
    this.value = value;
  }
  
  @Override
	public String toString() {
		return "" + value;
	}
}

class Memento
{
  public List<Token> tokens = new ArrayList<>();
}

class TokenMachine
{
  public List<Token> tokens = new ArrayList<>();

  public Memento addToken(int value)
  {
    Token token = new Token(value);
    tokens.add(token);
    Memento memento = new Memento();
    //Do the deep copy for Token
    for(Token t : tokens) {
    	memento.tokens.add(new Token(t.value));
    }
    return memento;
  }

  public Memento addToken(Token token)
  {
	tokens.add(token);
    Memento memento = new Memento();
    //Do the deep copy for Token
    for(Token t : tokens) {
    	memento.tokens.add(new Token(t.value));
    }
    return memento;
  }

  public void revert(Memento m)
  {
	tokens.clear();
	for(Token t : m.tokens) {
		tokens.add(new Token(t.value));
	}
  }
}

public class Demo {
	public static void main(String [] args) {
		Token t1 = new Token(10);
		Token t2 = new Token(20);
		TokenMachine tm = new TokenMachine();
		
		Memento m1 = tm.addToken(t1);
		Memento m2 = tm.addToken(t2);
		
		System.out.println("Token Machine: " + tm.tokens);
		System.out.println("M1 : " + m1.tokens);
		System.out.println("M2 : " + m2.tokens);
		
		m1.tokens.add(new Token(30));
		System.out.println("After change in M1");
		System.out.println("Token Machine : " + tm.tokens);
		System.out.println("M1 : " + m1.tokens);
		System.out.println("M2 : " + m2.tokens);
		
		Memento m3 = tm.addToken(40);
		System.out.println("After adding token:");
		System.out.println("Token Machine : " + tm.tokens);
		System.out.println("M3 : " + m3.tokens);
		
		System.out.println("Reverting TM to M1");
	    tm.revert(m1);
	    System.out.println(tm.tokens);
	}
}
