package com.babu;

public class Temp {
	
	// Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int minCount = 0;
        int i = 0;
        while(i<arr.length) {
            if(arr[i] != i+1) {
                int temp = arr[i];
                arr[i] = arr[arr[i]-1];
                arr[temp-1] = temp;
                minCount++;
            } else {
                i++;
            }
        }
        return minCount;
    }
    
 // Complete the isValid function below.
    static String isValid(String s) {
        int[] alphabets = new int[26];
        for(int i=0;i<s.length();i++)
            alphabets[s.charAt(i) - 'a']++;
        
        for(int i=0;i<26;i++)
        	if(alphabets[i] > 0)
        		System.out.println(Character.toString((char)('a' + i)) + " : " + alphabets[i]);
        int preCharCount = 0;
        boolean isRemovalDone = false;
        int i=0;
        while(alphabets[i] == 0 && i<26)
            i++;
        preCharCount = alphabets[i++];
        System.out.println("PreCharCount : " + preCharCount);
        while(i<26) {
            if(alphabets[i] == 0)
            {
                i++; 
                continue; 
            }
            int diff = Math.abs(alphabets[i]-preCharCount);
            if(diff > 1)
                return "NO";
            else if(diff == 1) {
                if(isRemovalDone)
                    return "NO";
                else
                    isRemovalDone = true;
            }
            i++;
        }
        return "YES";
    }
    
    public static void main(String[] args) {
    	/*int[] A = {4, 3, 1, 2};
    	System.out.println(minimumSwaps(A));*/
    	
    	String testStr = "ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd";
    	System.out.println(isValid(testStr));
    }
	
	/*public static void main(String [] args) {
		outer:
		for (int i = 2; i < 1000; i++) {
		    for (int j = 2; j < i; j++) {
		        if (i % j == 0)
		            continue outer;
		    }
		    System.out.println (i);
		}
	}*/
}
