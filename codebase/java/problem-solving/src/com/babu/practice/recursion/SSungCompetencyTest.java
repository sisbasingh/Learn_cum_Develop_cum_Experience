package com.babu.practice.recursion;

import java.util.Arrays;
import java.util.Scanner;

public class SSungCompetencyTest {
	
	private static int minTouchesRecur(int wn, int[] psn, int[] ops, int m) {
		if(wn < 0 || wn > 999) {
			return Integer.MAX_VALUE;
		}
		if(wn == 0) {
			return 0;
		}
		if(isElementPresent(psn, wn)) {
			return String.valueOf(wn).length();
		}
		//Else recur for available ops
		int plusMinOps = Integer.MAX_VALUE;
		int minusMinOps = Integer.MAX_VALUE;
		int multMinOps = Integer.MAX_VALUE;
		int divMinOps = Integer.MAX_VALUE;
		
		int minOps = Integer.MAX_VALUE;
		
		for(int i=0;i<ops.length;i++) {
			if(ops[i] == 1) {
				//Do the Addition
				for(int j=0;j<psn.length && psn[j] <= wn;j++) {
					if(psn[j] == 0) {
						continue;
					}
					plusMinOps = 1 + String.valueOf(psn[j]).length() + minTouchesRecur(wn-psn[j], psn, ops, m);
					minOps = min(minOps, plusMinOps);
				}
			} else if(ops[i] == 2) {
				//Do the subtraction
				for(int j=psn.length-1;j>=0 && psn[j]>=wn;j--) {
					if(psn[j] == 0) {
						continue;
					}
					minusMinOps = 1 + String.valueOf(psn[j]).length() + minTouchesRecur(psn[j]-wn, psn, ops, m);
					minOps = min(minOps, minusMinOps);
				}
			} else if(ops[i] == 3) {
				//Do the multiplicaiton
				for(int j=0;j<psn.length && psn[j]<=wn;j++) {
					if(psn[j] == 0) {
						continue;
					}
					if(wn % psn[j] == 0) {
						multMinOps = 1 + String.valueOf(psn[j]).length() + minTouchesRecur(wn/psn[j], psn, ops, m);
						minOps = min(minOps, multMinOps);
					}
				}
			} else {
				//Do the division
				for(int j=psn.length-1;j>=0 && psn[j]>= wn;j--) {
					if(psn[j] == 0) {
						continue;
					}
					if(psn[j] % wn == 0 && isElementPresent(psn, psn[j]/wn)) {
						divMinOps = 1 + String.valueOf(psn[j]).length() + minTouchesRecur(psn[j]/wn, psn, ops, m);
						minOps = min(minOps, divMinOps);
					}
				}
			}
		}
		
		return minOps;
	}
	
	private static int min(int a, int b) {
		return a<b?a:b;
	}
	
	private static boolean isElementPresent(int[] psn, int n) {
		int l = 0, h = psn.length-1;
		while(l<h) {
			int mid = l + (h-l)/2;
			if(psn[mid] == n)
				return true;
			else if(psn[mid] < n)
				l = mid+1;
			else
				h = mid-1;
		}
		return false;
	}
	
	private static int getPermutationSize(int[] narr) {
		int n = narr.length;
		boolean isZeroElement = false;
		for(int e : narr) {
			if(e == 0) {
				isZeroElement = true;
				break;
			}
		}
		if(isZeroElement) {
			return n*(1+(n-1)*(1+n));
		}
		return n*(1+n*(1+n));
	}
	
	private static int[] computeAllPossibleNumbers(int[] narr) {
		Arrays.sort(narr);
		int[] psn = new int[getPermutationSize(narr)];
		for(int j=0;j<narr.length;j++) {
			psn[j] = narr[j];
		}
		int k = narr.length;
		for(int i=0;i<narr.length;i++) {
			for(int j=0;j<narr.length;j++) {
				if(psn[i] == 0) {
					break;
				}
				psn[k++] = psn[i]*10 + psn[j];
			}
		}
		int l = k;
		for(int j=narr.length;j<l;j++) {
			for(int i=0;i<narr.length;i++){
				psn[k++] = psn[j]*10 + psn[i];
			}
		}
		return psn;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = Integer.parseInt(in.nextLine());
		for(int i=0;i<t;i++) {
			String[] nom = in.nextLine().split("\\s+");
			int n = Integer.parseInt(nom[0]);
			int o = Integer.parseInt(nom[1]);
			int m = Integer.parseInt(nom[2]);
			
			String[] nsarr = in.nextLine().split("\\s+");
			int[] narr = new int[n];
			for(int j=0;j<n;j++) {
				narr[j] = Integer.parseInt(nsarr[j]);
			}
			
			String[] soarr = in.nextLine().split("\\s+");
			int[] oarr = new int[o];
			for(int j=0;j<o;j++) {
				oarr[j] = Integer.parseInt(soarr[j]);
			}
			
			int wn = Integer.parseInt(in.nextLine());
			int[] psn = computeAllPossibleNumbers(narr);
			int minOps = Integer.MAX_VALUE;
			
			if(isElementPresent(psn, wn)) {
				minOps = String.valueOf(wn).length();
			} else {
				minOps = minTouchesRecur(wn, psn, oarr, m) + 1;
			}
			
			if(minOps > m) {
				System.out.println("-1");
			} else {
				System.out.println(minOps);
			}
		}
	}

}
