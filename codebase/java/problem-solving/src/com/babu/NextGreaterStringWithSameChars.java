package com.babu;

import java.util.Arrays;
import java.util.Scanner;

public class NextGreaterStringWithSameChars {

	/*
	 * Generate next greater number with same set of digits
	 */
	public String nextGreaterString(String n) {
		char[] number = n.toCharArray();
		int len = number.length;
		for (int i = len - 1; i > 0; i--) {
			if (number[i] > number[i - 1]) {
				// Reverse the remaining digits to sort in increasing order
				int j = i, k = len - 1;
				while (j < k) {
					char temp = number[j];
					number[j] = number[k];
					number[k] = temp;
					j++;
					k--;
				}
				// Get the next greater digit of number[i] in the remaining
				// array using binary search
				int index = nextGreaterIndex(number, i, len - 1, number[i - 1]);
				if (index >= 0) {
					char t = number[index];
					number[index] = number[i - 1];
					number[i - 1] = t;
					return String.valueOf(number);
				} else {
					System.out.println("Invalid Index returned");
					break;
				}
			}
		}
		return "";
	}

	/*
	 * Function to find the next greater digit to d in char array num from s to
	 * e
	 */
	private int nextGreaterIndex(char[] num, int s, int e, char d) {
		int lastIndex = -1;
		while (s <= e) {
			int mid = s + (e - s) / 2;
			if (num[mid] > d) {
				/*
				 * while(mid >= s && num[mid] > d) { lastIndex = mid; mid--; }
				 * return lastIndex;
				 */
				lastIndex = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return lastIndex;
	}
	
	private long factorial(long n) {
		if(n == 0) return 1;
		long fact = 1;
		for(int i=2;i<=n;i++)
			fact *= i;
		return fact;
	}
	
	/*
	 * Customized print function
	 */
	private void print(String s) {
		System.out.print(s+" ");
	}
	
	/*
	 * Function to print all permutations of a string in sorted order
	 */
	public void printSortedPermutations(String s) {
		char[] str = s.toCharArray();
		Arrays.sort(str);
		long l = s.length();
		print(String.valueOf(str));
		long fact = factorial(l);
		for(long i=1;i<fact;i++) {
			String greater = nextGreaterString(String.valueOf(str));
			str = greater.toCharArray();
			print(greater);
		}
	}

	/*
	 * main function
	 */
	public static void main(String args[]) {
		NextGreaterStringWithSameChars obj = new NextGreaterStringWithSameChars();
		Scanner in = new Scanner(System.in);
		String str = in.next();
		obj.printSortedPermutations(str);
		System.out.println();
		perm2(str);
		System.out.println();
		obj.permute(str);
	}
	
	public void permute(String s)
    {
    	recurPermute("", s);
    }
    
    /*
     * Recursive Permutation 
     * NOTE: if initial string is sorted gives sorted permutations
     */
    public void recurPermute(String prefix, String s)
    {
    	int n = s.length();
    	if(n == 0)
    		System.out.print(prefix + " ");
    	else {
    		for(int i=0; i<n; i++)
    			recurPermute(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, n));
    	}
    }
    
 // print n! permutation of the elements of array a (ordered/unordered)
    public static void perm2(String s) {
        int n = s.length();
        char[] a = new char[n];
        for (int i = 0; i < n; i++)
            a[i] = s.charAt(i);
        //perm2(a, n);
        perm3(a, 0, n-1);
    }
    
    /*
     * Ordered Permutations of an character array
     */
    private static void perm3(char[] a, int l, int n) {
        if (n == l) {
            System.out.print(String.valueOf(a) + " ");
            return;
        }
        for (int i = l; i <= n; i++) {
            swap(a, i, l);
            perm3(a, l+1, n);
            swap(a, i, l);
        }
    }  

    /*
     * Unordered permutations of an character array.
     */
    private static void perm2(char[] a, int n) {
        if (n == 1) {
            System.out.println(a);
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(a, i, n-1);
            perm2(a, n-1);
            swap(a, i, n-1);
        }
    }  

    // swap the characters at indices i and j of an array
    private static void swap(char[] a, int i, int j) {
        char c = a[i];
        a[i] = a[j];
        a[j] = c;
    }

}
