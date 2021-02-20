//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 19, 2021
// Description:	Unit test with command line parameter
//			    for iterative and recursive sum of first n positive integers
//-----------------------------------------------------
public class Level1Problem4 {
    //-------------------------------------
    // Main: 		unit test
    // Input:		command line param: a positive number n
    // Output:	    none
    //			    prints the sum of the first n positive integers
    // Method:	    invokes findSumNIntsI and findSumNIntsR, prints the sum of the first n positive integers
    //-------------------------------------
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int resultI = findSumNIntsI(n);
        int resultR = findSumNIntsR(n);
        System.out.println("n="+n);
        System.out.println("resultI="+resultI);
        System.out.println("resultR="+resultR);
    }

    //-------------------------------------
    // Function
    // Name:    findSumNIntsI
    // Input: 	positive number n
    // Output:	the sum of the first n positive integers
    // Method:	iterative
    //-------------------------------------
    public static int findSumNIntsI(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += i;
		}
		return sum;
    }
	
    //-------------------------------------
    // Function
    // Name:    findSumNIntsIR
    // Input: 	positive number n
    // Output:	the sum of the first n positive integers
    // Method:	recursive
    //-------------------------------------
	public static int findSumNIntsR(int n) {
		if (n <= 0) {
			return 0;
		}
		else if (n == 1) {
			return 1;
		}
		else {
			return findSumNIntsR(n-1)+n;
		}
	}
}
