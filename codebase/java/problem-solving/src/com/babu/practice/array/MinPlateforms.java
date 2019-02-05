package com.babu.practice.array;

import java.util.Arrays;
import java.util.Scanner;

public class MinPlateforms {

	public static int minPlateforms(int[] arr, int[] dep) {
		Arrays.sort(arr);
		Arrays.sort(dep);
		int minPfs = 0, arrdep = 0;
		int i = 0, j = 0;
		while (i < arr.length) {
			/**
			 * If the train arrives early then the other train or the train arrives and leaves at the same time then
			 * also increase the number of trains at this point of time
			 */
			if (arr[i] < dep[j] || (arr[i] == dep[j] && i == j)) {
				arrdep++;
				if (minPfs < arrdep) {
					minPfs = arrdep;
				}
				i++;
			} else {
				/**
				 * Train leaves decrease the number of trains at this point of time by 1
				 */
				arrdep--;
				j++;
			}
		}
		return minPfs;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = Integer.parseInt(in.nextLine());
		while (t > 0) {
			int n = Integer.parseInt(in.nextLine());
			int[] arr = Arrays.stream(in.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
			int[] dep = Arrays.stream(in.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
			System.out.println(minPlateforms(arr, dep));
			t--;
		}
	}

}
