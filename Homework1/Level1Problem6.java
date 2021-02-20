//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 19, 2021
// Description:	Unit test with command line parameters
//			    for iterative and recursive average of array elements
//-----------------------------------------------------
import java.util.Arrays;
public class Level1Problem6 {
    //-------------------------------------
    // Main: 		unit test
    // Input:		command line params: an array length followed by the array elements
    // Output:	    none
    //			    prints the average of the elements in the array
    // Method:	    invokes findAverageI and findAverageR, prints the average of the elements in the array
    //-------------------------------------
    public static void main(String[] args) {
        int[] arr = new int[Integer.parseInt(args[0])];
        for (int i = 0; i < Integer.parseInt(args[0]); i++){
            arr[i] = Integer.parseInt(args[i+1]);
        }
        double resultI = findAverageI(arr);
        double resultR = findAverageR(arr);
        System.out.println("arr="+Arrays.toString(arr));
        System.out.println("resultI="+resultI);
        System.out.println("resultR="+resultR);
    }

    //-------------------------------------
    // Function
    // Name:    findAverageI
    // Input: 	array
    // Output:	the average of the elements in the array
    // Method:	iterative
    //-------------------------------------
	public static double findAverageI(int[] arr) {
        if (arr.length <= 0){
            return 0;
        }
        double sum = 0;
        for (int elem : arr){
            sum += elem;
        }
		return sum/arr.length;
	}

    //-------------------------------------
    // Function
    // Name:    findAverageR
    // Input: 	array
    // Output:	the average of the elements in the array
    // Method:	recursive
    //-------------------------------------
	public static double findAverageR(int[] arr) {
        if (arr.length <= 0){
            return 0;
        } else if (arr.length == 1) {
            return arr[0];
        } else {
            int mid = arr.length/2;
            int[] left = new int[mid];
			for (int i = 0; i < mid; i++) {
				left[i] = arr[i];
			}
            int[] right = new int[mid];
			for (int i = 0; i < mid; i++) {
				right[i] = arr[mid+i];
			}
            double leftResult = findAverageR(left);
            double rightResult = findAverageR(right);
            double finalResult = (leftResult+rightResult)/2;
            if (arr.length % 2 != 0){
                finalResult = finalResult*(arr.length-1)/(arr.length) + (double)arr[arr.length-1]/(arr.length);
            }
            return finalResult;
        }
	}
}
