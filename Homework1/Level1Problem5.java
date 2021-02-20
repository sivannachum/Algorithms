//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 19, 2021
// Description:	Unit test with command line parameter
//			    for iterative and recursive n!
//-----------------------------------------------------
public class Level1Problem5 {
    //-------------------------------------
    // Main: 		unit test
    // Input:		command line param: a nonnegative number n
    // Output:	    none
    //			    prints n!
    // Method:	    invokes findNFactorialI and findNFactorialR, prints n!
    //-------------------------------------
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int resultI = findNFactorialI(n);
        int resultR = findNFactorialR(n);
        System.out.println("n="+n);
        System.out.println("resultI="+resultI);
        System.out.println("resultR="+resultR);
    }

    //-------------------------------------
    // Function
    // Name:    findNFactorialI
    // Input: 	nonnegative number n
    // Output:	n!
    // Method:	iterative
    //-------------------------------------
    public static int findNFactorialI(int n) {
		if (n < 0) {
			return 0;
		}
		int factorial = 1;
		for (int i = 2; i <= n; i++) {
			factorial *= i;
		}
		return factorial;
	}
	
    //-------------------------------------
    // Function
    // Name:    findNFactorialR
    // Input: 	nonnegative number n
    // Output:	n!
    // Method:	recursive
    //-------------------------------------
	public static int findNFactorialR(int n) {
		if (n < 0) {
			return 0;
		}
		else if (n == 0) {
			return 1;
		}
		else {
			return findNFactorialR(n-1)*n;
		}
	}
}
