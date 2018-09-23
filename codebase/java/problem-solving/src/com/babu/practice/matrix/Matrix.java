package com.babu.practice.matrix;

public class Matrix {
	
	private static int[][] multiply(int[][] m1, int[][] m2, int m, int n, int p, int q) {
		//Check whether the matrix can be multiplied or not
		if(n != p) {
			return null;
		}
		int[][] result = new int[m][q];
		for(int i=0;i<m;i++) {
			for(int j=0;j<q;j++) {
				for(int k=0;k<n;k++)
					result[i][j] += m1[i][k]*m2[k][j];
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		
		int [][] A = new int[][] { {1,2}, {3,4} };
		int [][] B = new int[][] { {1,2}, {3,4} };
		
		int [][] result = multiply(A, B, 2, 2, 2, 2);
		
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++)
				System.out.print(result[i][j] + "\t");
			System.out.println();
		}
				
	}

}
