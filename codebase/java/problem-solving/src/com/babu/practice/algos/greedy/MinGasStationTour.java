package com.babu.practice.algos.greedy;

import java.util.List;

public class MinGasStationTour {
	
	/**
	 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
	 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
	 * You begin the journey with an empty tank at one of the gas stations.
	 * Return the minimum starting gas station’s index if you can travel around the circuit once, otherwise return -1.
	 * You can only travel in one direction. i to i+1, i+2, ... n-1, 0, 1, 2..
	 * Completing the circuit means starting at i and ending up at i again.
	 * @param a
	 * @param b
	 * @return
	 */
	public int canCompleteCircuit(final List<Integer> a, final List<Integer> b) {
        int minSum = Integer.MAX_VALUE;
        int runningSum = 0, startPosition = 0;
        for(int i=0;i<a.size();i++) {
            runningSum += a.get(i) - b.get(i);
            if(runningSum < minSum) {
                minSum = runningSum;
                startPosition = (i+1)%a.size();
            }
        }
        /**
         * if overall gas-cost is negative, tour is not possible
         */
        if(runningSum < 0) {
            return -1;
        } else if(minSum > 0) {
        	/**
        	 * If running gas-cost for all stations is positive, the least station to start is 0
        	 */
            return 0;
        }
        /**
         * Start from the station just next to the station where running gas-cost is minimum
         */
        return startPosition;
    }

}
