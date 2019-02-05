package com.babu.practice.algos.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
	
	public boolean isValidPlace(List<Integer> cols) {
        int size = cols.size()-1;
        for(int i=0;i<size;i++) {
            int diff = Math.abs(cols.get(i) - cols.get(size));
            //if same row or same diagonal
            if(diff == 0 || diff == size-i) {
                return false;
            }
        }
        return true;
    }
    
    public void nq(int n, ArrayList<Integer> curCol, ArrayList<ArrayList<String>> res) {
        if(curCol.size() == n) {
            ArrayList<String> curPlacement = new ArrayList<>();
            for(int i=0;i<n;i++) {
                StringBuilder sbr = new StringBuilder("");
                for(int j=0;j<n;j++) {
                    if(curCol.get(i) == j) {
                        sbr.append("Q");
                    } else {
                        sbr.append(".");
                    }
                }
                curPlacement.add(sbr.toString());
            }
            res.add(curPlacement);
        }
        
        for(int i=0;i<n;i++) {
            curCol.add(i);
            if(isValidPlace(curCol)){
                nq(n, curCol, res);
            }
            curCol.remove(curCol.size()-1);
        }
    }
    
	public ArrayList<ArrayList<String>> solveNQueens(int a) {
	    ArrayList<ArrayList<String>> res = new ArrayList<>();
	    ArrayList<Integer> cur = new ArrayList<>();
	    nq(a, cur, res);
	    return res;
	}

}
