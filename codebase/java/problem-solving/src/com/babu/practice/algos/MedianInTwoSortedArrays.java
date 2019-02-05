package com.babu.practice.algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedianInTwoSortedArrays {

	public double findMedianSortedArraysOld(final List<Integer> a, final List<Integer> b) {
		if (a.size() == 0) {
			if (b.size() % 2 != 0)
				return b.get(b.size() / 2);
			else
				return ((double) b.get(b.size() / 2) + b.get((b.size() - 1) / 2)) / 2;
		}

		if (b.size() == 0) {
			if (a.size() % 2 != 0)
				return a.get(a.size() / 2);
			else
				return ((double) a.get(a.size() / 2) + a.get((a.size() - 1) / 2)) / 2;
		}

		int s1 = 0, e1 = a.size() - 1;
		int s2 = 0, e2 = b.size() - 1;

		while (e1 - s1 > 1 || e2 - s2 > 1) {
			int mid1 = s1 + (e1 - s1) / 2;
			int mid2 = s2 + (e2 - s2) / 2;

			if (a.get(mid1) == b.get(mid2))
				return a.get(mid1);
			if (a.get(mid1) < b.get(mid2)) {
				if (e1 - s1 > 1)
					s1 = mid1;
				if (e2 - s2 > 1)
					e2 = mid2;
			} else {
				if (e1 - s1 > 1)
					e1 = mid1;
				if (e2 - s2 > 1)
					s2 = mid2;
			}
		}

		return ((double) Math.max(a.get(s1), b.get(s2)) + Math.min(a.get(e1), b.get(e2))) / 2;

		/*
		 * if((a.size() + b.size())%2 != 0) return Math.min(a.get(s1), b.get(s2)); else
		 * return ((double)a.get(s1)+b.get(s2))/2;
		 */

	}

	
	public double findMedianForUnequalLength(List<Integer> a, List<Integer> b) {
		if(a.size() > b.size()) {
			List<Integer> temp = a;
			a = b;
			b = temp;
		}
		
		int s = 0, e = a.size();
		//Final median position
		int n = (a.size() + b.size() + 1)/2;
		
		while(s <= e) {
			int partitionA = s + (e-s)/2;
			int partitionB = n-partitionA;
			
			int leftMaxA = (partitionA == 0?Integer.MIN_VALUE:a.get(partitionA-1));
			int rightMinA = (partitionA == a.size()?Integer.MAX_VALUE:a.get(partitionA));
			
			int leftMaxB = (partitionB == 0?Integer.MIN_VALUE:b.get(partitionB-1));
			int rightMinB = (partitionB == b.size()?Integer.MAX_VALUE:b.get(partitionB));
			
			if(leftMaxA <= rightMinB && leftMaxB <= rightMinA) {
				if((a.size() + b.size())%2 == 0) {
					return ((double)(Math.max(leftMaxA, leftMaxB)) + Math.min(rightMinA, rightMinB))/2;
				}
				return (double)Math.max(leftMaxA, leftMaxB);
			} else if(leftMaxA > rightMinB) {
				e = partitionA - 1;
			} else {
				s = partitionA + 1;
			}
		}
		return (-1);
	}
	
	public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
		if (a.isEmpty()) {
			if (b.size() % 2 != 0)
				return b.get(b.size() / 2);
			else
				return ((double) b.get(b.size() / 2) + b.get((b.size() - 1) / 2)) / 2;
		}

		if (b.isEmpty()) {
			if (a.size() % 2 != 0)
				return a.get(a.size() / 2);
			else
				return ((double) a.get(a.size() / 2) + a.get((a.size() - 1) / 2)) / 2;
		}

		if (a.size() == b.size()) {
			return findMedianForEqualLength(a, b, a.size());
		} else {
			return findMedianForUnequalLength(a, b);
			
			/*int i = 0, j = 0;
			int n = (a.size() + b.size());
			int k = n / 2 + 1;
			int mid1 = 0, mid2 = 0;
			while (i < a.size() && j < b.size() && k > 0) {
				if (a.get(i) < b.get(j)) {
					mid1 = mid2;
					mid2 = a.get(i++);
				}
				else {
					mid1 = mid2;
					mid2 = b.get(j++);
				}
				k--;
			}

			while (i < a.size() && k > 0) {
				mid1 = mid2;
				mid2 = a.get(i++);
				k--;
			}

			while (j < b.size() && k > 0) {
				mid1 = mid2;
				mid2 = b.get(j++);
				k--;
			}

			if (n % 2 == 0) {
				return ((double)mid1 + mid2) / 2;
			} else {
				return mid2;
			}*/
		}

	}
	
	private double getMedian(final List<Integer> a, int size) {
		if(size%2 == 0) {
			return ((double)a.get(size/2-1) + a.get(size/2))/2;
		} else {
			return a.get(size/2);
		}
	}

	private double findMedianForEqualLength(final List<Integer> a, final List<Integer> b, int size) {
		if(size <= 0)
			return -1;
		if(size == 1)
			return ((double)a.get(0) + b.get(0))/2;
		if(size == 2)
			return ((double)Math.max(a.get(0), b.get(0)) + Math.min(a.get(1), b.get(1)))/2;
		
		double m1 = getMedian(a, size);
		double m2 = getMedian(b, size);
		
		if(m1 == m2)
			return m1;
		if(m1 < m2) {
			if(size % 2 == 0)
				return findMedianForEqualLength(a.subList(size/2-1, size), b, size-size/2+1);
			return findMedianForEqualLength(a.subList(size/2, size), b, size-size/2);
		}
		
		if(size % 2 == 0)
			return findMedianForEqualLength(b.subList(size/2-1, size), a, size-size/2+1);
		return findMedianForEqualLength(b.subList(size/2, size), a, size-size/2);
	}

	public static void main(String[] args) {
		List<Integer> list1 = Arrays.asList(new Integer[]{-35, -30, 22, 27, 33, 35, 40, 50});
		List<Integer> list2 = Arrays.asList(new Integer[] {-39, -28, -25, -17, 1, 30, 33, 37});
		
		MedianInTwoSortedArrays medianInTwoSortedArrays = new MedianInTwoSortedArrays();
		System.out.println(medianInTwoSortedArrays.findMedianSortedArrays(list1, list2));
	}

}
