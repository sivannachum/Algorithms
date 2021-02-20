//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 19, 2021
// Description:	Unit test with command line parameters
//			    for iterative and recursive min value in array
//-----------------------------------------------------
import java.util.Arrays;
public class Level1Problem1 {
    //-------------------------------------
    // Main: 		unit test
    // Input:		command line params: an array length followed by the array elements
    // Output:	    none
    //			    prints min value in array
    // Method:	    invokes findMinValueI and findMinValueR, prints min value in array
    //-------------------------------------
    public static void main(String[] args) {
        int[] arr = new int[Integer.parseInt(args[0])];
        for (int i = 0; i < Integer.parseInt(args[0]); i++){
            arr[i] = Integer.parseInt(args[i+1]);
        }
        int resultI = findMinValueI(arr);
        int resultR = findMinValueR(arr);
        System.out.println("arr="+Arrays.toString(arr));
        System.out.println("resultI="+resultI);
        System.out.println("resultR="+resultR);
    }

    //-------------------------------------
    // Function
    // Name:    findMinValueI
    // Input: 	array
    // Output:	the min value in the array
    // Method:	iterative
    //-------------------------------------
	public static int findMinValueI(int[] arr) {
		int min = Integer.MAX_VALUE;
		for(int elem : arr) {
			if (elem < min) {
				min = elem;
			}
		}
		return min;
	}
	
    //-------------------------------------
    // Function
    // Name:    findMinValueR
    // Input: 	array
    // Output:	the min value in the array
    // Method:	recursive
    //-------------------------------------
	public static int findMinValueR(int[] arr) {
		if (arr.length <= 0) {
			return Integer.MAX_VALUE;
		} else if (arr.length == 1) {
			return arr[0];
		} else {
			int[] newArray = new int[arr.length-1];
			for (int i = 0; i < newArray.length; i++) {
				newArray[i] = arr[i+1];
			}
			return Math.min(arr[0], findMinValueR(newArray));
		}
	}
}
