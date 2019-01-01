package com.babu.practice.dp;

import java.util.Stack;

public class MaxAreaRectangleOfOnes {
	
	public static void main(String[] args) {
		MaxAreaRectangleOfOnes maxAreaRectangleOfOnes = new MaxAreaRectangleOfOnes();
		int [][] a = { {0,1,1,0},
					   {1,1,1,1},
					   {1,1,1,1},
					   {1,1,0,0}
					 };
		System.out.println(maxAreaRectangleOfOnes.maxArea(a, 4, 4));
	}
	
	public int maxArea(int a[][], int m, int n) {
		if(m == 0 && n == 0) {
    		return 0;
    	}
        int mx_area = maxAreaUtil(a[0]);
        for(int i=1;i<m;i++) {
        	for(int j=0;j<n;j++) {
        		if(a[i][j] == 1) {
        			a[i][j] += a[i-1][j];
        		}
        	}
        	mx_area = Math.max(mx_area, maxAreaUtil(a[i]));
        }
        return mx_area;
    }
    
    private int maxAreaUtil(int a[]){
    	int n = a.length;
        int mx_area = 0, area_with_top = 0;
        Stack<Integer> s = new Stack<Integer>();
        int i=0;
        while(i<n){
            if(s.isEmpty() || a[s.peek()] <= a[i]){
                s.push(i++);
            } else {
            	int h = a[s.peek()];
            	s.pop();
            	area_with_top = h*(s.isEmpty()?i:i-s.peek()-1);
            	mx_area = Math.max(mx_area, area_with_top);
            }
        }
        
        while(!s.isEmpty()) {
        	int h = a[s.peek()];
        	s.pop();
        	area_with_top = h*(s.isEmpty()?i:i-s.peek()-1);
        	mx_area = Math.max(mx_area, area_with_top);
        }
        return mx_area;
    }
}
