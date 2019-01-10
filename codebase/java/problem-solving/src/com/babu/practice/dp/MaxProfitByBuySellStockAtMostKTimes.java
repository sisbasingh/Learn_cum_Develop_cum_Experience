package com.babu.practice.dp;

public class MaxProfitByBuySellStockAtMostKTimes {
	
	/**
	 * Computes max profit by buying and selling the stocks at most t times within the span of n days
	 * @param prices - Prices for stocks for n days
	 * @param t - most number of txns allowed
	 * @return
	 */
	private int maxProfitOkn2(int[] prices, int t) {
		int d = prices.length;
		int [][] profit = new int[t+1][d+1];
		
		//For 0 txn profit is 0
		for(int i=0;i<=t;i++) {
			profit[i][0] = 0;
		}
		
		//For 0th day profit is 0
		for(int i=0;i<=d;i++) {
			profit[0][i] = 0;
		}
		
		for(int i=1;i<=t;i++) {
			for(int j=1;j<d;j++) {
				int maxProfit = Integer.MIN_VALUE;
				for(int k=0;k<j;k++) {
					/**
					 * Making t'th txn on dth day and t-1 txns before till that
					 */
					maxProfit = Math.max(maxProfit, prices[j]-prices[k]+profit[i-1][k]);
				}
				/**
				 * Max profit would be:
				 * Max of :
				 * 1. not doing any txn on dth day i.e. profit[i][j-1] OR
				 * 2. doing t-1 txns till d-1 day and making t'th txn on dth day i.e. maxProfit
				 */
				profit[i][j] = Math.max(maxProfit, profit[i][j-1]);
			}
		}
		return profit[t][d-1];
	}
	
	/**
	 * From above solutioin:
	 * 
	 * profit[t][d] : 
	 * 1. = 0 if t=0 or d=0
	 * 2. = max(profit[t][d-1], profit[t-1][j] + price[d]-price[j] for 0<=j<=d-1)
	 * 
	 * since : max(profit[t-1][j] + price[d]-price[j] for 0<=j<=d-1) = price[d]+max(profit[t-1][j] - price[j] for 0<=j<=d-1 as
	 * only j is varying from 0 to d-1)
	 * = price[d] + max(preProfit, profit[t-1][d-1]-price[d-1]) where preProfit = max profit from 0 till d-2 days,
	 * as d-1 days is taken into consideration
	 * 
	 * Thereby removing the innermost loop and time complexity being O(kn) with space complexity O(kn)
	 * 
	 * @param prices
	 * @param t
	 * @return
	 */
	private int maxProfitOkn(int[] prices, int t) {
		int d = prices.length;
		int [][] profit = new int[t+1][d+1];
		
		//For 0 txn profit is 0
		for(int i=0;i<=t;i++) {
			profit[i][0] = 0;
		}
		
		//For 0th day profit is 0
		for(int i=0;i<=d;i++) {
			profit[0][i] = 0;
		}
		
		for(int i=1;i<=t;i++) {
			int maxProfit = Integer.MIN_VALUE;
			for(int j=1;j<d;j++) {
				maxProfit = Math.max(maxProfit, profit[i-1][j-1]-prices[j-1]);
				/**
				 * Max profit would be:
				 * Max of :
				 * 1. not doing any txn on dth day i.e. profit[i][j-1] OR
				 * 2. doing t-1 txns till d-1 day and making t'th txn on dth day i.e. maxProfit
				 */
				profit[i][j] = Math.max(maxProfit+prices[j], profit[i][j-1]);
			}
		}
		return profit[t][d-1];
	}
	
	
	public static void main(String[] args) {
		MaxProfitByBuySellStockAtMostKTimes mpbstocks = new MaxProfitByBuySellStockAtMostKTimes();
		int prices[] = { 12, 14, 17, 10, 14, 13, 12, 15 };;
		//System.out.println(mpbstocks.maxProfitOkn2(prices, 3));
		System.out.println(mpbstocks.maxProfitOkn(prices, 3));
	}

}
