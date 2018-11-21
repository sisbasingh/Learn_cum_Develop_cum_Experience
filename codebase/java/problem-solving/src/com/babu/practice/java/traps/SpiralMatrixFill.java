package com.babu.practice.java.traps;

import java.util.ArrayList;
import java.util.Collections;

public class SpiralMatrixFill {
    public static ArrayList<ArrayList<Integer>> generateMatrix(int a) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>(a);
        if(a <= 0) {
            return matrix;
        }
        /**
         * IMP:
         * ArrayList in java can only be accessed in increasing order of index starting from 0, one-by-one,
         * It doesn't provide filling at a later index in an empty list, whereas Arrays does.
         * To access random locations in ArrayList initialize it with default values as follows and then reset the
         * values using set method instead of add method
         */
        for(int i=0;i<a;i++)
            matrix.add(new ArrayList<Integer>(Collections.nCopies(a, 0)));
        
        int rowStart=0, rowEnd=a-1, colStart=0, colEnd=a-1;
        
        int n=1;
        while(rowStart<=rowEnd && colStart<=colEnd) {
            //process first row
            for(int t=colStart;t<=colEnd;t++)
                matrix.get(rowStart).set(t, n++);
            rowStart++;
            //process last column
            for(int t=rowStart;t<=rowEnd;t++)
                matrix.get(t).set(colEnd, n++);
            colEnd--;
            //process last row
            for(int t=colEnd;t>=colStart;t--)
                matrix.get(rowEnd).set(t, n++);
            rowEnd--;
            //process first column
            for(int t=rowEnd;t>=rowStart;t--)
                matrix.get(t).set(colStart, n++);
            colStart++;
        }
        return matrix;
    }
    
    public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> results = generateMatrix(10);
		for(int i=0;i<results.size();i++) {
			System.out.print(results.get(i));
			/*for(int j=0;j<results.get(i).size();j++)
				System.out.print(results.get(i).get(j) + " ");*/
			System.out.println();
		}
	}
}

