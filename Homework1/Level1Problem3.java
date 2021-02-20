//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 19, 2021
// Description:	Unit test with command line parameters
//			    for iterative and recursive sum/product of array elements
//-----------------------------------------------------
import java.util.Arrays;
public class Level1Problem3 {
    //-------------------------------------
    // Main: 		unit test
    // Input:		command line params: an array length followed by the array elements
    // Output:	    none
    //			    prints the sum/product of the array elements
    // Method:	    invokes findSumElementsI and findSumElementsR, prints the sum of the array elements
    //              invokes findProductElementsI and findProductElementsR, prints the product of the array elements
    //-------------------------------------
    public static void main(String[] args) {
        int[] arr = new int[Integer.parseInt(args[0])];
        for (int i = 0; i < Integer.parseInt(args[0]); i++){
            arr[i] = Integer.parseInt(args[i+1]);
        }
        int sumI = findSumElementsI(arr);
        int sumR = findSumElementsR(arr);
        int productI = findProductElementsI(arr);
        int productR = findProductElementsR(arr);
        System.out.println("arr="+Arrays.toString(arr));
        System.out.println("sumI="+sumI);
        System.out.println("sumR="+sumR);
        System.out.println("productI="+productI);
        System.out.println("productR="+productR);
    }

    //-------------------------------------
    // Function
    // Name:    findSumElementsI
    // Input: 	array
    // Output:	the sum of elements in the array
    // Method:	iterative
    //-------------------------------------
    public static int findSumElementsI(int[] arr) {
		int sum = 0;
		for (int elem : arr) {
			sum += elem;
		}
		return sum;
	}

    //-------------------------------------
    // Function
    // Name:    findSumElementsR
    // Input: 	array
    // Output:	the sum of elements in the array
    // Method:	recursive
    //-------------------------------------
    public static int findSumElementsR(int[] arr) {
		if (arr.length <= 0) {
			return 0;
		}
		else if (arr.length == 1) {
			return arr[0];
		}
		else {
			int[] newArray = new int[arr.length-1];
			for (int i = 0; i < newArray.length; i++) {
				newArray[i] = arr[i+1];
			}
			return arr[0]+findSumElementsR(newArray);
		}
	}

    //-------------------------------------
    // Function
    // Name:    findProductElementsI
    // Input: 	array
    // Output:	the product of elements in the array
    // Method:	iterative
    //-------------------------------------
	public static int findProductElementsI(int[] arr) {
		if (arr.length <= 0) {
			return 0;
		}
		int product = 1;
		for (int elem : arr) {
			product *= elem;
		}
		return product;
	}
	
    //-------------------------------------
    // Function
    // Name:    findProductElementsR
    // Input: 	array
    // Output:	the product of elements in the array
    // Method:	recursive
    //-------------------------------------
	public static int findProductElementsR(int[] arr) {
		if (arr.length <= 0) {
			return 0;
		}
		else if (arr.length == 1) {
			return arr[0];
		}
		else {
			int[] newArray = new int[arr.length-1];
			for (int i = 0; i < newArray.length; i++) {
				newArray[i] = arr[i+1];
			}
			return arr[0]*findProductElementsR(newArray);
		}
	}
}
