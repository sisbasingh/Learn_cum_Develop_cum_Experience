package com.babu.practice.array;

public class SearchInRowNColSortedMatrix {

	private static int searchMatrix(int mat[][], int n, int m, int x) {
		if(x < mat[0][0] || x > mat[n-1][m-1])
	        return 0;
	    int row = 0, col = m-1;
	    while(row < n && col >=0) {
	        if(x == mat[row][col])
	            return 1;
	        else if(x > mat[row][col])
	            row++;
	        else
	            col--;
	    }
	    return 0;
	}
	
	public static void main(String[] args) {
		
	}

}
