//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 19, 2021
// Description:	Unit test with command line parameters
//			    for iterative and recursive index of min value in array
//-----------------------------------------------------
import java.util.Arrays;
public class Level1Problem2 {
    //-------------------------------------
    // Main: 		unit test
    // Input:		command line params: an array length followed by the array elements
    // Output:	    none
    //			    prints the index of the min value in array
    // Method:	    invokes findMinIndexI and findMinIndexR, prints index of min value in array
    //-------------------------------------
    public static void main(String[] args) {
        int[] arr = new int[Integer.parseInt(args[0])];
        for (int i = 0; i < Integer.parseInt(args[0]); i++){
            arr[i] = Integer.parseInt(args[i+1]);
        }
        int resultI = findMinIndexI(arr);
        int[] resultR = findMinIndexR(arr);
        System.out.println("arr="+Arrays.toString(arr));
        System.out.println("resultI="+resultI);
        System.out.println("resultR="+resultR[0]);
    }

    //-------------------------------------
    // Function
    // Name:    findMinIndexI
    // Input: 	array
    // Output:	the position (index) of the min value in the array
    // Method:	iterative
    //-------------------------------------
	public static int findMinIndexI(int[] arr) {
        if (arr.length <= 0){
            return -1;
        }
		int min = arr[0];
		int index = 0;
		for(int i = 1; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
				index = i;
			}
		}
		return index;
	}
	
    //-------------------------------------
    // Function
    // Name:    findMinIndexR
    // Input: 	array
    // Output:	the position (index) of the min value in the array and the min value itself
    // Method:	recursive
    //-------------------------------------
	public static int[] findMinIndexR(int[] arr) {
        if (arr.length <= 0) {
            return new int[] {-1, -1};
        } else if (arr.length == 1) {
          return new int[] {0, arr[0]};
        } else{
            int mid = arr.length/2;
            int[] left = new int[mid];
			for (int i = 0; i < mid; i++) {
				left[i] = arr[i];
			}
            int length = 0;
            if (arr.length % 2 == 0){
                length = mid;
            } else {
                length = mid+1;
            }
            int[] right = new int[length];
			for (int i = 0; i < length; i++) {
				right[i] = arr[mid+i];
			}
            int[] leftResult = findMinIndexR(left);
            int[] rightResult = findMinIndexR(right);
            if (leftResult[1] < rightResult[1]){
                return leftResult;
            } else {
                rightResult[0] = rightResult[0]+left.length;
                return rightResult; 
            }
        }
    }
}
