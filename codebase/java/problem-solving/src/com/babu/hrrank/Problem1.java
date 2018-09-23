package com.babu.hrrank;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Problem1 {

    // Complete the minimumTimeToEnd function below.
    static void minimumTimeToEnd(long[] x, long w, long v, int q) {
        // Take the descriptions of the people from standard input and print the answers to standard output
    	int n = x.length;
    	double[] firstBusTiming = new double[n];
    	for(int i=0;i<n;i++) {
    		firstBusTiming[i] = x[i]/v;
    	}
    	Scanner in = new Scanner(System.in);
    	for(int i=0;i<q;i++) {
    		int p, t, u;
    		p = in.nextInt();
    		t = in.nextInt();
    		u = in.nextInt();
    		//Calculate reaching time without bus
			double distance = x[n-1] - p;
			double timestamp = t + distance/u;
			
			//If speed of person is greater than the bus
    		if(u >= v) {
    			System.out.println(timestamp);
    		} else {
    			//Find nearest bus stop
    			int nearestStop = findNearestBusStop(x, p);
    			double tsToReachNearestStop = t + (double)Math.abs(x[nearestStop] - p)/u;
    			double timeStampToReachLastStop = w*(Math.ceil(tsToReachNearestStop/w)) + firstBusTiming[n-1];
    			double finalTsToReachTheLastStop = timestamp < timeStampToReachLastStop ? timestamp : timeStampToReachLastStop;
    			System.out.println(finalTsToReachTheLastStop);
    		}
    	}
    }
    
    static int findNearestBusStop(long[] stops, int point) {
    	//Using binary search
    	int l = 0;
    	int h = stops.length - 1;
    	while(l < h) {
    		int mid = l + (h-l)/2;
    		if(stops[mid] == point)
    			return mid;
    		else if(stops[mid] < point) {
    			if(mid == h) { //No more stops
    				return mid;
    			}else if(mid < h && stops[mid+1] > point) { //turning point 
    				return (point - stops[mid]) < (stops[mid+1] - point) ? mid : mid+1;
    			} else { //no turning point
    				l = mid+1;
    			}
    		} else if(stops[mid] > point) {
    			if(mid == l) //No more stops
    				return mid;
    			else if(mid > l && stops[mid-1] < point) { //turning point
    				return (point - stops[mid-1]) < (stops[mid] - point) ? mid-1 : mid;
    			} else { //not a turning point
    				h = mid-1;
    			}
    		}
    	}
    	return l;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] x = new long[n];

        String[] xItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int xItr = 0; xItr < n; xItr++) {
            long xItem = Long.parseLong(xItems[xItr]);
            x[xItr] = xItem;
        }

        String[] wv = scanner.nextLine().split(" ");

        long w = Long.parseLong(wv[0]);

        long v = Long.parseLong(wv[1]);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        minimumTimeToEnd(x, w, v, q);

        scanner.close();
    }
}

