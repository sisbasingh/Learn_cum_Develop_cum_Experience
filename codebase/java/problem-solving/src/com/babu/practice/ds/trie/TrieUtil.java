package com.babu.practice.ds.trie;

import java.util.ArrayList;

public class TrieUtil {
	    
    public ArrayList<String> prefix(ArrayList<String> A) {
        
        Trie trie = new Trie();
        for(String str : A) {
            trie.insertWord(str);
        }
        
        ArrayList<String> results = new ArrayList<>();
        for(String str : A) {
            results.add(trie.shortestUniquePrefix(str));
        }
        
        return results;
    }
    
    
    class Trie {
        TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void insertWord(String str) {
            char[] word = str.toCharArray();
            TrieNode temp = root;
            for(char c : word) {
                temp = temp.insertChar(c);
            }
        }
        
        public String shortestUniquePrefix(String str) {
            char[] word = str.toCharArray();
            StringBuilder sbr = new StringBuilder("");
            TrieNode temp = root;
            for(char c : word) {
                sbr.append(c + "");
                temp = temp.alphabets[c - 'a'];
                if(temp == null || temp.count <= 1) {
                    return sbr.toString();
                }
            }
            return sbr.toString();
        }
    }
    
    class TrieNode {
        int count;
        /**
         * NOTE: Map<Character, TrieNode> can be used instead of the array to save the space (in case only few characters are being used)
         * and to support more generic charactes (e.g. including lowercase/uppercase or any other characters)
         */
        TrieNode[] alphabets;
        
        public TrieNode() {
            count = 0;
            alphabets = new TrieNode[26];
        }
        
        public TrieNode insertChar(char c) {
            if(alphabets[c - 'a'] == null) {
                alphabets[c - 'a'] = new TrieNode();
            }
            count++;
            return alphabets[c - 'a'];
        }
    } 
}
