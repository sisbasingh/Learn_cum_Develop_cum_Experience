package com.babu.practice.udemy.design_patterns.structural.flyweight;

import java.util.*;

class Sentence
{
	private List<WordToken> wordTokens;
	
  public Sentence(String plainText)
  {
	wordTokens = new ArrayList<>();
    Arrays.asList(plainText.split(" ")).forEach(token -> {
    	wordTokens.add(new WordToken(token));
    });
  }

  public WordToken getWord(int index)
  {
    return wordTokens.get(index);
  }

  @Override
  public String toString()
  {
	  StringBuffer sb = new StringBuffer();
	  for(int i=0; i<wordTokens.size(); i++) {
		  if(wordTokens.get(i).capitalize) {
	    		sb.append(wordTokens.get(i).token.toUpperCase());
	    	} else {
	    		sb.append(wordTokens.get(i).token);
	    	}
	    	if(i < wordTokens.size() - 1) {
	    		sb.append(" ");
	    	}
	  }
	  return sb.toString();
  }

  class WordToken
  {
  	public String token;
    public boolean capitalize;
    
    public WordToken(String token) {
    	this.token = token;
    }
  }
}

public class Demo {
	public static void main(String [] args) {
		Sentence sentence = new Sentence("hello world");
		sentence.getWord(1).capitalize = true;
		System.out.println(sentence);
	}
}
