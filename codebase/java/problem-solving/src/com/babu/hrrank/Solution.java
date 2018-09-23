package com.babu.hrrank;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Solution {

    // Complete the findTheAbsentStudents function below.
    static int[] findTheAbsentStudents(int n, int[] a) {
        // Return the list of student IDs of the missing students in increasing order.
    	//Approach - 1
        /*List<Integer> callRecord = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
        for(int id : a){
            callRecord.remove(new Integer(id));
        }
        return callRecord.stream().mapToInt(i -> i).sorted().toArray();*/
        
    	//Approach - 2
        /*List<Integer> alist = Arrays.stream(a).boxed().collect(Collectors.toList());
        int[] result = IntStream.rangeClosed(1, n).filter(num -> !alist.contains(num)).toArray();
        //System.out.println(result.toString());
        return result;*/
    	
    	//Approach - 3 Using Sets
    	Set<Integer> inputs = Arrays.stream(a).boxed().collect(Collectors.toSet());
    	Set<Integer> allNumSet = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toSet());
    	allNumSet.removeAll(inputs);
    	//Unbox and convert to array
    	return allNumSet.stream().mapToInt(d -> d).toArray();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
    	/*List<String> l = new ArrayList<String>(Arrays.asList("one", "two"));
        Stream<String> sl = l.stream();
        l.add("three");
        //String s = sl.filter(str -> str.startsWith("t")).collect(Collectors.joining(" "));  //sl.sorted().collect(Collectors.joining(" "));
              //OR
        String s = sl.filter(str -> str.startsWith("t")).map(st -> st + " ").reduce("", String::concat);
        long count = l.stream().filter(str -> str.startsWith("t")).count();
        System.out.println("Count is: " + count + "\nList is: " + s);*/
    	
    	
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] array = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        int[] results = findTheAbsentStudents(n, array);
        System.out.println(Arrays.toString(results));
        scanner.close();
    }
}

